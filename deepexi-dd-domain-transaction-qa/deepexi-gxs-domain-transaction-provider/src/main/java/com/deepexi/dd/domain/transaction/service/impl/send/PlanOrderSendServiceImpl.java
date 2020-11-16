package com.deepexi.dd.domain.transaction.service.impl.send;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/25/14:37
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.deepexi.clientiam.api.GroupControllerApi;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.clientiam.model.PayloadGroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineOrderInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineSkuRequestDTO;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.enums.SalePickGoodsEnum;
import com.deepexi.dd.domain.transaction.mq.gl.producter.GlMqProducter;
import com.deepexi.dd.domain.transaction.mq.producter.PlanOrderItemSendMqMsg;
import com.deepexi.dd.domain.transaction.mq.producter.PlanOrderSendMqMsg;
import com.deepexi.dd.domain.transaction.remote.order.BusinessPartnerClient;
import com.deepexi.dd.domain.transaction.remote.order.MerchantClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsOrderSkuClient;
import com.deepexi.dd.domain.transaction.remote.order.log.SalePickOrderYunLogApiRemote;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.MerchantResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import domain.query.MerchantRequestQuery;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName PlanOrderSendServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/25
 * @Version V1.0
 **/
@Service
@Slf4j
public class PlanOrderSendServiceImpl {

    private static final String SUCCESS = "0";


    @Value("${gl.supplyOrgCode}")
    String supplyOrgCode;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    MerchantClient merchantClient;

    @Autowired
    private SalePickGoodsInfoClient salePickGoodsInfoClient;

    @Autowired
    GlMqProducter glMqProducter;

    @Autowired
    SalePickOrderYunLogApiRemote salePickOrderYunLogApiRemote;

    String GL_YUNCANG_ORDER_TOPIC = "gsale_to_wms_sso";

    @Value("${gl.yuncang.is.send}")
    Boolean glYuncangIsSend;

    @Autowired
    SalePickGoodsOrderSkuClient salePickGoodsOrderSkuClient;

    @Autowired
    private GroupControllerApi groupControllerApi;

    @Autowired
    BusinessPartnerClient businessPartnerClient;

    public void sendMsg(ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception {
        if (glYuncangIsSend) {
            log.info("前端入参：{}", JSONObject.toJSONString(requestDTO));
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                    salePickGoodsInfoClient.selectById(requestDTO.getId());
            SalePickGoodsConsigneeResponseDTO salePickGoodsConsigneeResponseDTO = null;
            if (CollectionUtils.isNotEmpty(salePickGoodsInfoResponseDTO.getSalePickGoodsConsigneeResponseDTOS())) {
                salePickGoodsConsigneeResponseDTO =
                        salePickGoodsInfoResponseDTO.getSalePickGoodsConsigneeResponseDTOS().get(0);
            }
            Long customerId = salePickGoodsInfoResponseDTO.getCustomerId();
            if (customerId == null) {
                throw new ApplicationException(ResultEnum.ORDER_CUSTOMER_ID_IS_NULL);
            }
            //通过订单记录的一级组织id获得组织编码
            Long orgId = salePickGoodsInfoResponseDTO.getAscriptionOrgId();
            String tenantId = salePickGoodsInfoResponseDTO.getTenantId();
            Long userId = Long.valueOf(salePickGoodsInfoResponseDTO.getCreatedBy());
            log.info("通过订单记录的一级组织id获得组织编码；入参：orgId={};tenantId={};userId={};", orgId, tenantId, userId);
            PayloadGroupResultVO resultVO = groupControllerApi.getGroup(orgId, tenantId,
                    userId, null);
            log.info("通过订单记录的一级组织id获得组织编码；接口：resultVO={};", resultVO);
            GroupResultVO grouVO = null;
            if (SUCCESS.equals(resultVO.getCode()) && resultVO.getPayload() != null) {
                grouVO = resultVO.getPayload();
            } else {
                throw new ApplicationException(ResultEnum.NOT_FOUND_ORG);
            }
            Payload<MerchantResponseDTO> payload = merchantClient.detail(customerId);
            MerchantResponseDTO merchantResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                    MerchantResponseDTO.class);
            if (merchantResponseDTO == null) {
                throw new ApplicationException(ResultEnum.ORDER_CUSTOMER_IS_NULL);
            }


            //获得所有的明细集合然后按仓库id分组
            List<ExamineSkuRequestDTO> skulist = new ArrayList<>();
            List<ExamineOrderInfoRequestDTO> items = requestDTO.getSaleOrderList();
            if (CollectionUtils.isNotEmpty(items)) {
                items.forEach(item -> {
                    if (CollectionUtils.isNotEmpty(item.getItems())) {
                        skulist.addAll(item.getItems());
                    }
                });
            } else {
                throw new ApplicationException(ResultEnum.ORDER_ITEM_NOT_FOUNT);
            }
            Map<Long, List<ExamineSkuRequestDTO>> reusltMap =
                    skulist.stream().filter(p -> p.getIsJoin().equals(1)).collect(Collectors.groupingBy(sku -> sku.getWarehouseId()));
            MerchantRequestQuery merchantRequestQuery = new MerchantRequestQuery();
            merchantRequestQuery.setOrgId(salePickGoodsInfoResponseDTO.getSellerId().toString());
            merchantRequestQuery.setAppId(salePickGoodsInfoResponseDTO.getAppId());
            merchantRequestQuery.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
            BusinessPartnerResponseDTO businessPartnerResponseDTO = getPartner(salePickGoodsInfoResponseDTO.getAppId(),
                    tenantId,salePickGoodsInfoResponseDTO.getSellerId());
            merchantRequestQuery.setPartnerId(businessPartnerResponseDTO.getId());
            Payload<List<MerchantResponseDTO>> listPayload = merchantClient.findList(merchantRequestQuery);
            List<MerchantResponseDTO> merchantResponseDTOS = GeneralConvertUtils.convert2List(listPayload.getPayload(),
                    MerchantResponseDTO.class);
            if (CollectionUtils.isEmpty(merchantResponseDTOS)) {
               // throw new ApplicationException(ResultEnum.ORDER_SELLER_IS_NULL);
                log.info("查询不到卖家的客户信息=={}",merchantRequestQuery);
                merchantResponseDTOS.add(merchantResponseDTO);
            }

            for (Map.Entry<Long, List<ExamineSkuRequestDTO>> entry : reusltMap.entrySet()) {
                PlanOrderSendMqMsg planOrderSendMqMsg = new PlanOrderSendMqMsg();
                //销售部门 新增（与ERP部门编码一致，如JJB-家经部）
                planOrderSendMqMsg.setSaleDepartment(grouVO.getCode());
                //买家code
                planOrderSendMqMsg.setBrokerCode(merchantResponseDTO.getCode());
                planOrderSendMqMsg.setBusinessType(1);
                //卖家客户code
                planOrderSendMqMsg.setConsumerNo(merchantResponseDTOS.get(0).getCode());
                planOrderSendMqMsg.setInventoryType("1");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                planOrderSendMqMsg.setOrderDate(simpleDateFormat.format(new Date()));
                planOrderSendMqMsg.setOrderType("S01");//待定
                planOrderSendMqMsg.setSourceOrderNo(salePickGoodsInfoResponseDTO.getPickGoodsCode());
                planOrderSendMqMsg.setSourceWarehouseCode(entry.getValue().get(0).getWarehouseCode());
                //格力顶级卖家组织，写死
                planOrderSendMqMsg.setSupplyOrgCode(supplyOrgCode);
                planOrderSendMqMsg.setAddressTown(salePickGoodsConsigneeResponseDTO.getStreetName());
                planOrderSendMqMsg.setAddressProvince(salePickGoodsConsigneeResponseDTO.getProvinceName());
                planOrderSendMqMsg.setAddressCounty(salePickGoodsConsigneeResponseDTO.getAreaName());
                planOrderSendMqMsg.setAddressCity(salePickGoodsConsigneeResponseDTO.getCityName());
                planOrderSendMqMsg.setConsigneeName(salePickGoodsInfoResponseDTO.getReceiveGoodsMan());
                planOrderSendMqMsg.setConsigneePhone(salePickGoodsInfoResponseDTO.getContactWay());
                planOrderSendMqMsg.setConsumerRemark(salePickGoodsInfoResponseDTO.getRemark());
                planOrderSendMqMsg.setDetailAddress(salePickGoodsConsigneeResponseDTO.getDetailedAddress());
                planOrderSendMqMsg.setLicenseNumber(salePickGoodsInfoResponseDTO.getCarNumberZt());
                //planOrderSendMqMsg.setOriginOrderDate("1-15");//待定
                planOrderSendMqMsg.setPickUpGoodsMobile(salePickGoodsInfoResponseDTO.getContactWay());
                planOrderSendMqMsg.setIdCard(salePickGoodsInfoResponseDTO.getIdCardNumber());
                planOrderSendMqMsg.setPickUpGoodsName(salePickGoodsInfoResponseDTO.getReceiveGoodsMan());
                planOrderSendMqMsg.setRefA("");
                planOrderSendMqMsg.setRefB("");
                if (SalePickGoodsEnum.PICK_MYSELF.equals(salePickGoodsInfoResponseDTO.getPickGoodsWayZt())) {
                    planOrderSendMqMsg.setPickupMethod("0");
                } else {
                    planOrderSendMqMsg.setPickupMethod("1");
                }
                List<PlanOrderItemSendMqMsg> orderItems = new ArrayList<>();
                for (int x = 0; x < entry.getValue().size(); x++) {
                    ExamineSkuRequestDTO examineSkuRequestDTO = entry.getValue().get(x);
                    PlanOrderItemSendMqMsg planOrderItemSendMqMsg = new PlanOrderItemSendMqMsg();
                    planOrderItemSendMqMsg.setItemCode(examineSkuRequestDTO.getSkuCode());
                    planOrderItemSendMqMsg.setItemPrice(examineSkuRequestDTO.getPrice());
                    planOrderItemSendMqMsg.setOrderQuantity(examineSkuRequestDTO.getDeliveryQuantity().intValue());
                    planOrderItemSendMqMsg.setRemarks(examineSkuRequestDTO.getRemake());
                    planOrderItemSendMqMsg.setSetCode(examineSkuRequestDTO.getSkuCode());
                    //获得行号
                    SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery =
                            new SalePickGoodsOrderSkuRequestQuery();
                    salePickGoodsOrderSkuRequestQuery.setPickGoodsInfoId(salePickGoodsInfoResponseDTO.getId());
                    salePickGoodsOrderSkuRequestQuery.setSkuId(examineSkuRequestDTO.getSkuId());
                    salePickGoodsOrderSkuRequestQuery.setWarehouseId(examineSkuRequestDTO.getWarehouseId());
                    List<SalePickGoodsOrderSkuResponseDTO> list =
                            salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(salePickGoodsOrderSkuRequestQuery);
                    log.info("提货单sku明细={}",list);
                    if (CollectionUtils.isNotEmpty(list)) {
                        planOrderItemSendMqMsg.setSourceOrderLineId(list.get(0).getRowCode());
                    } else {
                        planOrderItemSendMqMsg.setSourceOrderLineId(examineSkuRequestDTO.getRowCode());
                    }

                    orderItems.add(planOrderItemSendMqMsg);
                }
                planOrderSendMqMsg.setOrderItems(orderItems);
                String sendMsg = JSONObject.toJSONString(planOrderSendMqMsg, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteMapNullValue);
                log.info("发送云仓的消息=={}", sendMsg);
                glMqProducter.sendMqMsg(GL_YUNCANG_ORDER_TOPIC, sendMsg);
                SalePickOrderYunLogRequestDTO salePickOrderYunLogRequestDTO = new SalePickOrderYunLogRequestDTO();
                salePickOrderYunLogRequestDTO.setAppId(appRuntimeEnv.getAppId());
                salePickOrderYunLogRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
                salePickOrderYunLogRequestDTO.setCreatedTime(new Date());
                salePickOrderYunLogRequestDTO.setPickOrderCode(planOrderSendMqMsg.getSourceOrderNo());
                salePickOrderYunLogRequestDTO.setResultCode("0");
                salePickOrderYunLogRequestDTO.setSendBody(sendMsg);
                salePickOrderYunLogRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
                salePickOrderYunLogApiRemote.insert(salePickOrderYunLogRequestDTO);
            }
        }
    }

    public void sendOrderByBody(PlanOrderSendMqMsg planOrderSendMqMsg) throws Exception {
        if (glYuncangIsSend) {
            String sendMsg = JSONObject.toJSONString(planOrderSendMqMsg, SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteMapNullValue);
            log.info("接口发送的到格力mq消息=={}", sendMsg);
            glMqProducter.sendMqMsg(GL_YUNCANG_ORDER_TOPIC, sendMsg);
            SalePickOrderYunLogRequestDTO salePickOrderYunLogRequestDTO = new SalePickOrderYunLogRequestDTO();
            salePickOrderYunLogRequestDTO.setAppId(appRuntimeEnv.getAppId());
            salePickOrderYunLogRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            salePickOrderYunLogRequestDTO.setCreatedTime(new Date());
            salePickOrderYunLogRequestDTO.setPickOrderCode(planOrderSendMqMsg.getSourceOrderNo());
            salePickOrderYunLogRequestDTO.setResultCode("0");
            salePickOrderYunLogRequestDTO.setSendBody(sendMsg);
            salePickOrderYunLogRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            salePickOrderYunLogApiRemote.insert(salePickOrderYunLogRequestDTO);
        }
    }

    public BusinessPartnerResponseDTO getPartner(Long appId, String tenantId,Long orgId) throws InvocationTargetException,
            NoSuchMethodException,
            NoSuchFieldException, IllegalAccessException, IOException {
        Payload<BusinessPartnerResponseDTO> businessPartnerResponseDTOPayload = null;
        try {
            BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
            businessPartnerRequestQuery.setTenantId(tenantId);
            businessPartnerRequestQuery.setAppId(appId);
            businessPartnerRequestQuery.setOrgId(orgId);

            businessPartnerResponseDTOPayload = businessPartnerClient.getPartner(businessPartnerRequestQuery);
            log.info("获取伙伴id:{}", JsonUtil.bean2JsonString(businessPartnerResponseDTOPayload));
            if (!businessPartnerResponseDTOPayload.getCode().equals("0")) {
                throw new ApplicationException("获取伙伴ID失败");
            }
        } catch (Exception e) {
            log.error("获取伙伴ID失败:", e);
            throw new ApplicationException("获取伙伴ID失败");
        }
        BusinessPartnerResponseDTO businessPartner =
                GeneralConvertUtils.conv(businessPartnerResponseDTOPayload.getPayload(),
                        BusinessPartnerResponseDTO.class);
        return businessPartner;
    }
}
