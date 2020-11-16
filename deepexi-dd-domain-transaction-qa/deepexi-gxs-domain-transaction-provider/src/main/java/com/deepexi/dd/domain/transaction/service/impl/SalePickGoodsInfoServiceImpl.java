package com.deepexi.dd.domain.transaction.service.impl;

import com.alibaba.fastjson.JSON;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.*;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusActionRequestQuery;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.controller.AppRunEnvForPick;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderSkuRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineOrderInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineSkuRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryItemInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryOrderInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.StockFindAllPostQuery;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.enums.SalePickGoodsEnum;
import com.deepexi.dd.domain.transaction.mq.gl.producter.GlMqProducter;
import com.deepexi.dd.domain.transaction.remote.order.*;
import com.deepexi.dd.domain.transaction.service.IamUserService;
import com.deepexi.dd.domain.transaction.service.OrderRefundReasonService;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsInfoService;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.dd.domain.transaction.service.impl.send.PlanOrderSendServiceImpl;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.*;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.sdk.storage.api.DepotSdkApi;
import com.deepexi.sdk.storage.model.DepotFindPagePostByTypeRequestDTO;
import com.deepexi.sdk.storage.model.DepotFindPagePostResponseDTO;
import com.deepexi.sdk.storage.model.DepotFindPagePostResponseDTODepot;
import com.deepexi.sdk.storage.model.PayloadDepotFindPagePostResponseDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.DateUtils;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.CustomerPickUpWarehouseDomainDTO;
import domain.dto.CustomerPickUpWarehouseDomainQuery;
import domain.dto.MerchantResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import domain.query.MerchantRequestQuery;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SalePickGoodsInfoServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 12 19:18:27 CST 2020
 */
@Service
@Slf4j
public class SalePickGoodsInfoServiceImpl implements SalePickGoodsInfoService {

    @Autowired
    SalePickGoodsInfoClient salePickGoodsInfoClient;

    @Autowired
    SalePickGoodsOrderSkuClient salePickGoodsOrderSkuClient;

    @Autowired
    SaleOrderInfoClient saleOrderInfoClient;

    @Autowired
    SaleOutTaskDetailInfoClient saleOutTaskDetailInfoClient;

    @Autowired
    SaleCloudInterfaceRecordClient saleCloudInterfaceRecordClient;

    @Autowired
    SaleOrderItemClient saleOrderItemClient;

    @Autowired
    PlanOrderSendServiceImpl planOrderSendService;

    @Value("${appId}")
    private Long appId;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    IdentifierGenerator identifierGenerator;

    @Autowired
    private ToolLinkClient toolLinkClient;

    @Autowired
    private ToolLinkService toolLinkService;

    @Autowired
    private SaleOutTaskClient saleOutTaskClient;

    private static final String SUCCESS = "0";

    private static final String SAVE = "save";

    private static final String UPDATE = "update";

    private static final String CANCEL = "cancel";

    private static final String CLOSE = "close";

    @Autowired
    private ToolStatusClient toolStatusClient;

    @Autowired
    BusinessPartnerClient businessPartnerClient;

    @Autowired
    private IamUserService iamUserService;

    @Autowired
    MerchantClient merchantClient;

    @Autowired
    ToolCommoditytypeAuthorizeClient toolCommoditytypeAuthorizeClient;

    @Autowired
    OrderOperationRecordClient orderOperationRecordClient;

    @Autowired
    SalePickGoodsOrderClient salePickGoodsOrderClient;

    @Autowired
    SaleOrderInfoService saleOrderInfoService;

    @Autowired
    GlMqProducter glMqProducter;

    @Autowired
    CustomerPickUpWarehouseClient customerPickUpWarehouseClient;

    @Autowired
    private DepotSdkApi depotSdkApi;

    @Autowired
    private OrderRefundReasonService orderRefundReasonService;


    /**
     * 进行保存提货订单的操作
     * 1.保存提货订单
     * 2.保存提货订单关联订单信息
     * 3.保存提货订单sku
     * 4.减少销售订单sku的剩余可申请提货数量
     *
     * @param record
     */
    @Override
    public void savePickGoods(SalePickGoodsPlanInfoRequestDTO record) {
        SalePickGoodsInfoRequestDTO saveObj = record.clone(SalePickGoodsInfoRequestDTO.class);
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO responseDTO = salePickGoodsInfoClient.savePickGoods(saveObj);
        record.setId(responseDTO.getId());
    }


    /**
     * 对要创建或要修改的提货计划单进行校验
     *
     * @param record
     */
    public void checkDate(SalePickGoodsPlanInfoRequestDTO record, String type) throws Exception {
        if (record == null) {
            throw new ApplicationException("不能保存或更新空的提货计划");
        }
        if (type.equals(UPDATE)) {
            if (StringUtils.isBlank(record.getPickGoodsCode())) {
                throw new ApplicationException("更新提货计划必须指定提货计划编号");
            }
        }
        if (record.getItems() == null || record.getItems().size() == 0) {
            throw new ApplicationException("请选择提货计划关联的订单");
        }
        if (record.getPickGoodsWayZt() == null) {
            throw new ApplicationException("请选择提货方式");
        }
        if(record.getProductId() == null || record.getProductId().equals(-1L)){
            if(!"其他".equals(record.getProductName())){
                throw new ApplicationException("系统错误，产品线id为null或-1时，产品线名称必须为‘其他’");
            }
        }
        if (record.getOperaterIsBuyer() == null) {
            throw new ApplicationException("系统错误，获取参数operaterIsBuyer失败");
        }
        if (!record.getOperaterIsBuyer()) {
            if (record.getCustomerName() == null) {
                throw new ApplicationException("卖家账号编辑提货单,必须要指定客户名称");
            }
            if (record.getCustomerId() == null) {
                throw new ApplicationException("卖家账号编辑提货单,系统无法获取客户id");
            }
        } else {
            if(record.getSellerName() == null){
                throw new ApplicationException("请先选择上游供应商 ");
            }
            if(record.getSellerId() == null){
                throw new ApplicationException("系统错误无法获取供应商SellerId字段");
            }
        }
        HashSet<Long> saleItemIdSet = new HashSet<>();
        HashSet<String> orderSet = new HashSet<>();
        for (SalePickGoodsOrderRequestDTO orderdto : record.getItems()) {
            boolean add = orderSet.add(orderdto.getSaleOrderCode());
            if (!add) {
                throw new ApplicationException("系统错误，一个提货订单中关联的订单编号不能重复，请检查。订单编号：" + orderdto.getSaleOrderCode());
            }
            if (orderdto.getSaleOrderCode() == null) {
                throw new ApplicationException("系统错误，无法获取关联订单的编号");
            }
            if (orderdto.getItems() == null || orderdto.getItems().size() == 0) {
                throw new ApplicationException("每条关联的订单必须要有至少1条明细");
            }
            for (SalePickGoodsOrderSkuRequestDTO skudto : orderdto.getItems()) {
                if (skudto.getPickNum() == null) {
                    throw new ApplicationException("请选择编号为" + orderdto.getSaleOrderCode() + "的订单下明细的提货数量");
                }
                if (skudto.getPickNum().equals(Long.valueOf(0))) {
                    throw new ApplicationException("编号为" + orderdto.getSaleOrderCode() + "订单下明细的提货数量至少为1件");
                }
                if (skudto.getSaleOrderItemId() == null) {
                    throw new ApplicationException("系统错误，编号为" + orderdto.getSaleOrderCode() + "的订单下明细的saleOrderItemId无法获取");
                }
                if (!saleItemIdSet.add(skudto.getSaleOrderItemId())) {
                    throw new ApplicationException("一张提货单不能同时有两个相同的订单明细，请检查关联的订单，编号：" + orderdto.getSaleOrderCode());
                }
            }
        }

    }


    /**
     * 创建提货单
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public Payload<SalePickGoodsInfoResponseDTO> createPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        //校验：前端输入数据
        this.checkDate(record, SAVE);
        //获取数据：销售订单数据和订单详情数据和其他数据
        HashMap<String, Object> map = this.getDate(record, SAVE);
        //校验：是否能创建提货订单
        this.checkDate2(record, map, SAVE);
        //封装数据：封装关联订单数和关联提货sku数据
        this.packDate(record, map, SAVE);
        //保存数据：提货计划保存进入数据库
        this.savePickGoods(record);
        //响应：转化为响应返回前端
        SalePickGoodsInfoResponseDTO payload = record.clone(SalePickGoodsInfoResponseDTO.class);
        return new Payload<>(payload);
    }


    /**
     * 更新并提交提货订单
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public Payload<SalePickGoodsInfoResponseDTO> editPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        //更新校验
        checkDate(record, UPDATE);
        //获取数据：销售订单数据和订单详情数据和其他数据
        HashMap<String, Object> map = this.getDate(record, UPDATE);
        //校验：是否能创建提货订单
        this.checkDate2(record, map, UPDATE);
        //封装数据：封装关联订单数和关联提货sku数据
        this.packDate(record, map, UPDATE);
        //进行数据库修改操作
        SalePickGoodsInfoResponseDTO responseDTO = doEditPick(record, map);
        //响应：转化为响应返回前端
        SalePickGoodsInfoResponseDTO payload = record.clone(SalePickGoodsInfoResponseDTO.class);
        return new Payload<>(payload);
    }


    /**
     * 对提货订单进行修改并提交
     * 1.更新旧的提货订单计划
     * 2.删除旧提货订单的订单
     * 3.删除旧提货订单的sku
     * 4.新增提货订单关联的订单
     * 5.新增提货订单关联的sku
     * 6.减少销售订单的剩余的可申请提货数量
     *
     * @param editObj
     * @param map
     * @return
     */
    private SalePickGoodsInfoResponseDTO doEditPick(SalePickGoodsPlanInfoRequestDTO editObj, HashMap<String, Object> map) {
        SalePickGoodsInfoTransferDTO dateDTO = (SalePickGoodsInfoTransferDTO) map.get("dateDTO");
        SalePickGoodsInfoRequestDTO editObjClone = editObj.clone(SalePickGoodsInfoRequestDTO.class);
        dateDTO.setRecordObj(editObjClone);
        dateDTO.setType(UPDATE);
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO response = salePickGoodsInfoClient.editPickGoods(dateDTO);
        SalePickGoodsInfoResponseDTO responseDTO = response.clone(SalePickGoodsInfoResponseDTO.class);
        editObj.setId(response.getId());
        return responseDTO;
    }


    /**
     * 取消提货订单
     *
     * @param record
     * @return
     */
    @Override
    public Payload<Boolean> cancelPickGoods(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        //取消提货订单需进行校验
        checkForCancel(record);
        //封装信息
        record = packDateForCancel(record);
        //执行数据库操作
        doCancelPickGoods(record);
        //推送云仓
        pushCloudStorehouse(record.getPickGoodsCode());
        return new Payload<>(true);
    }




    /**
     * 取消或关闭提货单时，需要通知云仓
     * 接口名称	一体化系统计划单取消后集成云仓取消订单接口
     * 功能描述	云仓接到取消时，需要判断是否已有出库单时，是否已出库
     * 服务名称	一体化系统
     * topic	gsale_cancel_to_wms_sso
     */
    private void pushCloudStorehouse(String pickGoodsCode) {
        SalePickGoodsCancelDTO dto = new SalePickGoodsCancelDTO();
        dto.setSourceOrderNo(pickGoodsCode);
        dto.setBusinessType(1);
        //推送云仓
        try {
            glMqProducter.sendMqMsg("gsale_cancel_to_wms_sso",JSON.toJSONString(dto));
        } catch (Exception e) {
            log.error("取消提货订单的时候推送云仓报错，入参dto:" + JSON.toJSONString(dto));
        }
        //记录日志
        SaleCloudInterfaceRecordRequestDTO record = new SaleCloudInterfaceRecordRequestDTO();
        record.setInterfaceName("一体化系统计划单取消接口");
        record.setInput(JSON.toJSONString(dto));
        record.setOutput(null);//需要封装返回
        record.setInterfaceUrl("gsale_cancel_to_wms_sso");
        record.setType(null);
        saleCloudInterfaceRecordClient.insert(record);


    }


    //取消提货订单时，封装要更新的数据库参数，删除无用的前端参数
    private SalePickGoodsPlanInfoRequestDTO packDateForCancel(SalePickGoodsPlanInfoRequestDTO record) {
        SalePickGoodsPlanInfoRequestDTO newRecord = new SalePickGoodsPlanInfoRequestDTO();
        newRecord.setPickGoodsCode(record.getPickGoodsCode());
        newRecord.setCancelCause(record.getCancelCause());
        newRecord.setCancelRemarks(record.getCancelRemarks());
        String closeType = record.getCloseType();
        if ("close".equals(closeType)) {
            newRecord.setStatus(StatusCodeEnum.TransactionClose.getCode());
        }
        if ("cancel".equals(closeType)) {
            newRecord.setStatus(StatusCodeEnum.TransactionCancel.getCode());
        }
        newRecord.setCloseType(closeType);
        Long userId = appRuntimeEnv.getUserId();
        newRecord.setUpdatedBy(userId == null ? null : userId.toString());
        newRecord.setUpdatedTime(new Date());
        return newRecord;
    }


    //取消提货订单时需要进行的校验
    private void checkForCancel(SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        if (record == null) {
            throw new ApplicationException("系统错误，没有请求参数，请联系管理员");
        }
        if (!"close".equals(record.getCloseType()) && !"cancel".equals(record.getCloseType())) {
            throw new ApplicationException("系统错误，获取参数错误，参数名CloseType。请传入close或cancel");
        }
        if (record.getPickGoodsCode() == null) {
            throw new ApplicationException("系统错误，无法获取提货单编号");
        }
        if (record.getCancelCause() == null) {
            throw new ApplicationException("取消提货原因必选");
        }
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO pickInfo = getPickByCode(record.getPickGoodsCode());
        if (pickInfo == null) {
            throw new ApplicationException("系统错误，该提货计划不存在，请检查");
        }
        Integer status = pickInfo.getStatus();
        if (status == null) {
            throw new ApplicationException("系统错误，无法获取提货单的状态");
        }
        if ("close".equals(record.getCloseType())) {
            if (status.equals(StatusCodeEnum.TransactionClose.getCode())) {
                throw new ApplicationException("该提货单已关闭，无需再次关闭");
            }
            if (status.equals(StatusCodeEnum.TransactionCancel.getCode())) {
                throw new ApplicationException("该提货单已取消，无需关闭");
            }
            if (status.equals(StatusCodeEnum.TransactionComplete.getCode())) {
                throw new ApplicationException("该提货单已完成，不能关闭");
            }
        }
        if ("cancel".equals(record.getCloseType())) {
            if (!status.equals(StatusCodeEnum.Rejected.getCode()) && !status.equals(StatusCodeEnum.ToPayment.getCode()) && !status.equals(StatusCodeEnum.ToConfirm.getCode())) {
                throw new ApplicationException("当前提货单的状态不允许进行取消提货操作");
            }
        }
        SaleOutTaskDetailInfoRequestQuery query = new SaleOutTaskDetailInfoRequestQuery();
        query.setSalePickGoodsId(pickInfo.getId());
        List<SaleOutTaskDetailInfoResponseDTO> outTaskDetails = saleOutTaskDetailInfoClient.listSaleOutTaskDetailInfos(query);
        long deliveryAmount = 0L;
        if(outTaskDetails != null && outTaskDetails.size() != 0){
            deliveryAmount = outTaskDetails.stream().mapToLong(item -> item.getSkuShipmentQuantity()).sum();
        }
        long totalSignQuantity = pickInfo.getTotalSignQuantity() == null ? 0 : pickInfo.getTotalSignQuantity();
        if(deliveryAmount > totalSignQuantity){
            throw new ApplicationException("当前提货单存在待签收的出库单，签收后才能关闭");
        }
    }


    /*
        1.增加销售订单sku的剩余的可申请提货数量
        2.修改提货订单状态为交易取消
        3.保存取消原因和取消备注
    */
    private SalePickGoodsInfoResponseDTO doCancelPickGoods(SalePickGoodsPlanInfoRequestDTO record) {
        SalePickGoodsInfoTransferDTO dateDTO = new SalePickGoodsInfoTransferDTO();
        SalePickGoodsInfoRequestDTO recordObj = record.clone(SalePickGoodsInfoRequestDTO.class);
        dateDTO.setRecordObj(recordObj);
        dateDTO.setType(record.getCloseType());
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO response = salePickGoodsInfoClient.doCancelPickGoods(dateDTO);
        SalePickGoodsInfoResponseDTO responseDTO = null;
        if (response != null) {
            responseDTO = response.clone(SalePickGoodsInfoResponseDTO.class);
        }
        return responseDTO;
    }


    @Override
    public PageBean<SalePickGoodsOrderSkuResponseDTO> searchPickGoodsList(Long saleOrderId,
                                                                          SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception {
        Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> pageBeanPayload =
                salePickGoodsInfoClient.searchPickGoodsList(saleOrderId, salePickGoodsOrderSkuRequestQuery.getPage(),
                        salePickGoodsOrderSkuRequestQuery.getSize());
        if (null != pageBeanPayload.getPayload()) {
            return GeneralConvertUtils.convert2PageBean(pageBeanPayload.getPayload(),
                    SalePickGoodsOrderSkuResponseDTO.class);
        }
        return new PageBean<>();
    }


    /**
     * 在线订购运营端列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<SalePickGoodsInfoResponseDTO> listPage(SalePickGoodsInfoRequestQuery query) throws Exception {
        //数据初始化
        if (query.getPage() == null) {
            query.setPage(1);
        }
        if (query.getSize() == null) {
            query.setSize(10);
        }
        if(StringUtils.isNotEmpty(query.getCreateDayBeginStr())){
            Date date = changDayStr(query.getCreateDayBeginStr(),0);
            query.setCreateTimeBegin(date);
        }
        if(StringUtils.isNotEmpty(query.getCreateDayEndStr())){
            Date date = changDayStr(query.getCreateDayEndStr(),1);
            query.setCreateTimeEnd(date);
        }
        //获取列表信息
        query.setAppId(null);
        query.setTenantId(null);
        query.setIsolationId(null);
        query.setBuyerId(appRuntimeEnv.getTopOrganization().getId());
        PageBean<com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO> pickPageBean = salePickGoodsInfoClient.listSalePickGoodsInfosPage(query);
        //获取每个提货订单的关联的所有提货sku
        Map<Long, List<SalePickGoodsOrderSkuResponseDTO>> skuMap = null;
        List<Long> saleOrderItemIds = new ArrayList<>();
        if (pickPageBean.getContent() != null && pickPageBean.getContent().size() != 0) {
            List<Long> pickIds = pickPageBean.getContent().stream().map(pick -> pick.getId()).collect(Collectors.toList());
            SalePickGoodsOrderSkuRequestQuery query2 = new SalePickGoodsOrderSkuRequestQuery();
            query2.setPickGoodsInfoIdList(pickIds);
            List<SalePickGoodsOrderSkuResponseDTO> skuList = salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(query2);
            if (skuList != null && skuList.size() != 0) {
                skuList.stream().map(sku -> sku.getMajorPicture()).collect(Collectors.toList());
                saleOrderItemIds = skuList.stream().map(sku -> sku.getSaleOrderItemId()).collect(Collectors.toList());
                skuMap = skuList.stream().collect(Collectors.groupingBy(sku -> sku.getPickGoodsInfoId()));
            }
        }
        //封装列表信息
        PageBean<SalePickGoodsInfoResponseDTO> returnPageBean = GeneralConvertUtils.convert2PageBean(pickPageBean, SalePickGoodsInfoResponseDTO.class);
        //封装每个提货订单关联的所有sku的图片
        if (skuMap != null && skuMap.size() != 0) {
            for (SalePickGoodsInfoResponseDTO pickDto : returnPageBean.getContent()) {
                Long id = pickDto.getId();
                List<SalePickGoodsOrderSkuResponseDTO> matchSkus = skuMap.get(id);
                if (CollectionUtils.isNotEmpty(matchSkus)) {
                    pickDto.setMajorPictureList(matchSkus.stream().map(sku -> sku.getMajorPicture()).collect(Collectors.toList()));
                    Map<String, List<SalePickGoodsOrderSkuResponseDTO>> skMap =
                            matchSkus.stream().collect(Collectors.groupingBy(skuDto -> skuDto.getSkuCode()==null?"":skuDto.getSkuCode()));
                    pickDto.setCommodityType(skMap.size());
                } else {
                    pickDto.setCommodityType(0);
                }
            }
        }
        //封装每个提货订单关联的订单信息
        List<Long> pickIds = returnPageBean.getContent().stream().map(item -> item.getId()).collect(Collectors.toList());
        List<SalePickGoodsOrderResponseDTO> pickOrderArr = getPickOrder(pickIds);
        Map<Long, List<SalePickGoodsOrderResponseDTO>> pickOrderMap = null;
        if(pickOrderArr != null){
            pickOrderMap = pickOrderArr.stream().collect(Collectors.groupingBy(SalePickGoodsOrderResponseDTO::getPickGoodsInfoId));
        }
        if(pickOrderMap != null){
            for (SalePickGoodsInfoResponseDTO pickDto : returnPageBean.getContent()) {
                Long id = pickDto.getId();
                List<SalePickGoodsOrderResponseDTO> pickGoodsOrderDTOS = pickOrderMap.get(id);
                if(pickGoodsOrderDTOS != null){
                    List<SalePickGoodsOrderRespDTO> pickOrderDTOS = ObjectCloneUtils.convertList(pickGoodsOrderDTOS, SalePickGoodsOrderRespDTO.class);
                    pickDto.setItems(pickOrderDTOS);
                }
            }
        }
        //封装已发货金额，用于判断是否显示退款按钮
        /*List<SaleOutTaskDetailInfoRequestDTO> outItems = salePickGoodsOrderSkuClient.getOutItemBySaleItemId(saleOrderItemIds);
        Map<Long, List<SaleOutTaskDetailInfoRequestDTO>> outItemsMap = null;
        if(outItems != null && outItems.size() != 0){
            outItemsMap = outItems.stream().collect(Collectors.groupingBy(SaleOutTaskDetailInfoRequestDTO::getSaleOrderItemId));
        }*/
        for (SalePickGoodsInfoResponseDTO pickDto : returnPageBean.getContent()) {
            pickDto.setDeliveryAmount(BigDecimal.ZERO);
        }
        if (skuMap != null && skuMap.size() != 0) {
            for (SalePickGoodsInfoResponseDTO pickDto : returnPageBean.getContent()) {
                Long id = pickDto.getId();
                List<SalePickGoodsOrderSkuResponseDTO> matchSkus = skuMap.get(id);
                BigDecimal deliveryAmount = BigDecimal.ZERO;//已发货金额
                if (matchSkus != null) {
                    for (SalePickGoodsOrderSkuResponseDTO sku : matchSkus) {
                        Long alreadySendNum = null;//sku已发货数量
                        Long deliveryQuantity = sku.getDeliveryQuantity() == null ? 0L : sku.getDeliveryQuantity();//该提货明细在该仓库下的提货数量
                        Long waitSendNum = sku.getWaitSendNum() == null ? 0L : sku.getWaitSendNum();//待发货数量
                        alreadySendNum = deliveryQuantity - waitSendNum;//该仓库下的提货数量-待发货数量 = 已发货数量
                        BigDecimal price = sku.getPrice() == null ? BigDecimal.ZERO : sku.getPrice();
                        BigDecimal skuMoney = price.multiply(new BigDecimal(alreadySendNum + ""));
                        deliveryAmount = deliveryAmount.add(skuMoney);
                    }
                }
                pickDto.setDeliveryAmount(deliveryAmount);
            }
        }
        //封装数据链路中的按钮信息
        packAction(query, returnPageBean, 1);
        return returnPageBean;
    }


    //转化字符串为Date类型
    private Date changDayStr(String dateStr, int addDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            throw new ApplicationException("系统错误，传入了错误格式的日期字符串："+dateStr);
        }
        if(addDay != 0 && date != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,addDay);
            date = calendar.getTime();
        }
        return date;
    }


    /**
     * 运营中心提货列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<SalePickGoodsInfoOperationResponseDTO> listOperationPage(SalePickGoodsInfoOperationRequestQuery query) throws Exception {
        //数据初始化
        if (query.getPage() == null) {
            query.setPage(1);
        }
        if (query.getSize() == null) {
            query.setSize(10);
        }
        query.setIsolationId(null);
        query.setSellerId(appRuntimeEnv.getTopOrganization().getId());
        if(!appRuntimeEnv.getUserOrganization().getId().equals(appRuntimeEnv.getTopOrganization().getId())){
            query.setAscriptionOrgId(appRuntimeEnv.getUserOrganization().getId());
        }
        //获取列表信息,提交后的订单
        List<Integer> exceptStatusList = new ArrayList<>();
        //exceptStatusList.add(0);
        query.setExceptStatusList(exceptStatusList);
        PageBean<com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO> pickPageBean =
                salePickGoodsInfoClient.listSalePickGoodsInfosPage(query.clone(SalePickGoodsInfoRequestQuery.class));
        if(pickPageBean.getContent()==null||pickPageBean.getContent().size()==0){
            return new PageBean<SalePickGoodsInfoOperationResponseDTO>(CollectionUtil.createArrayList());
        }
        //获取每个提货订单的关联的所有提货sku
        List<Long> pickIds = pickPageBean.getContent().stream().map(pick -> pick.getId()).collect(Collectors.toList());
        SalePickGoodsOrderSkuRequestQuery query2 = new SalePickGoodsOrderSkuRequestQuery();
        query2.setPickGoodsInfoIdList(pickIds);
        List<SalePickGoodsOrderSkuResponseDTO> skuResponseDTOList =
                salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(query2);
        Map<Long, List<SalePickGoodsOrderSkuResponseDTO>> skuMap =
                skuResponseDTOList.stream().collect(Collectors.groupingBy(sku -> sku.getPickGoodsInfoId()));
        //封装列表信息
        PageBean<SalePickGoodsInfoOperationResponseDTO> returnPageBean =
                GeneralConvertUtils.convert2PageBean(pickPageBean, SalePickGoodsInfoOperationResponseDTO.class);


        //封装数据链路中的按钮信息
        PageBean<SalePickGoodsInfoResponseDTO> pg = ObjectCloneUtils.convertPageBean(pickPageBean,
                SalePickGoodsInfoResponseDTO.class);
        packAction(query.clone(SalePickGoodsInfoRequestQuery.class), pg, 2);

        returnPageBean.getContent().forEach(item -> {
            List<SalePickGoodsOrderSkuResponseDTO> skuResponseDTOS =
                    skuMap.get(item.getId());
            if (CollectionUtils.isNotEmpty(skuResponseDTOS)) {
                Map<String, List<SalePickGoodsOrderSkuResponseDTO>> skMap =
                        skuResponseDTOS.stream().collect(Collectors.groupingBy(skuDto -> skuDto.getSkuCode()));
                item.setCommodityType(skMap.size());
                item.setCommodityNum(item.getTotalGoodsNumber());
                // item.setCommodityType(skuResponseDTOS.size());
                // item.setCommodityNum(skuResponseDTOS.stream().mapToLong(p -> p.getPickNum()).sum());
                //item.setCommodityNum(skuResponseDTOS.stream().mapToLong(p -> p.getPickNum()).sum());
            } else {
                item.setCommodityType(0);
                item.setCommodityNum(0l);
            }
            pg.getContent().forEach(pp -> {
                if (item.getId().equals(pp.getId())) {
                    item.setActions(pp.getActions());
                }
            });
        });

        return returnPageBean;
    }

    /**
     * 封装数据链路中的按钮信息
     *
     * @param type:1=在线订购，2=运营中心
     * @param query
     * @param returnPageBean
     * @throws Exception
     */
    private void packAction(SalePickGoodsInfoRequestQuery query, PageBean<SalePickGoodsInfoResponseDTO> returnPageBean, Integer type) throws Exception {
        ToolStatusActionRequestQuery toolStatusActionRequestQuery = new ToolStatusActionRequestQuery();
        toolStatusActionRequestQuery.setAppId(query.getAppId());
        toolStatusActionRequestQuery.setTenantId(query.getTenantId());
        if (type == 2) {//运营中心
            toolStatusActionRequestQuery.setListType("PickApply");
        } else if (type == 1) {//在线订购
            toolStatusActionRequestQuery.setListType("MallPickApply");
        }
        toolStatusActionRequestQuery.setItems(getItemList(returnPageBean, "PickPlan", type));
        Payload<List<ToolStatusActionResponesDTO>> listPayload = toolStatusClient.listToolStatusAction(toolStatusActionRequestQuery);
        if (listPayload != null && listPayload.getPayload() != null) {
            List<ToolStatusActionResponesDTO> toolStatusActionResponesDTOS = GeneralConvertUtils.convert2List(listPayload.getPayload(), ToolStatusActionResponesDTO.class);
            for (int i = 0; i < returnPageBean.getContent().size(); i++) {
                returnPageBean.getContent().get(i).setActions(GeneralConvertUtils.convert2List(toolStatusActionResponesDTOS.get(i).getActions(), OrderActionResponseDTO.class));
            }
        }
        //在线订购列表需要判断是否已退款或正在退款，然后去除申请退款的按钮
        if (type == 1) {
            List<SalePickGoodsInfoResponseDTO> pickArr = returnPageBean.getContent();
            List<String> pickCodes = pickArr.stream().map(item -> item.getPickGoodsCode()).collect(Collectors.toList());
            List<OrderRefundReasonFindResponseDTO> refunds = null;
            if(pickCodes.size() != 0){
                refunds = orderRefundReasonService.findOrderRefundReason(pickCodes);
            }
            Map<String, List<OrderRefundReasonFindResponseDTO>> refundMap = null;
            if(refunds != null && refunds.size() != 0){
                refundMap = refunds.stream().collect(Collectors.groupingBy(OrderRefundReasonFindResponseDTO::getOrderCode));
            }
            if(refundMap != null){
                for(SalePickGoodsInfoResponseDTO pickInfo : pickArr){
                    List<OrderRefundReasonFindResponseDTO> matchRefund = refundMap.get(pickInfo.getPickGoodsCode());
                    if(matchRefund != null && matchRefund.size() != 0){
                        List<OrderActionResponseDTO> newActions = pickInfo.getActions().stream().filter(action -> !"ApplyRefund".equals(action.getActionCode())).collect(Collectors.toList());
                        pickInfo.setActions(newActions);
                    }
                }
            }
        }
    }



    /**
     * 校验：是否能创建或更新提货订单
     *
     * @param record
     * @param map
     */
    private void checkDate2(SalePickGoodsPlanInfoRequestDTO record, HashMap<String, Object> map, String type) {
        SalePickGoodsInfoTransferDTO dateDTO = (SalePickGoodsInfoTransferDTO) map.get("dateDTO");
        if(dateDTO.getBuyerId() == null){
            throw new ApplicationException("系统错误，无法获取客户id,这可能是上游单据没有客户id造成的或页面传值错误造成");
        }
        Map<Long, List<SalePickGoodsOrderSkuResponseDTO>> oldSkusMap = null;
        if (type.equals(UPDATE)) {
            com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO oldPickInfo = dateDTO.getOldPickInfo();
            if (oldPickInfo == null) {
                throw new ApplicationException("系统错误，无法根据提货单编号获取提货单信息");
            }
            Integer status = oldPickInfo.getStatus();
            if (status == null) {
                throw new ApplicationException("系统错误，无法获取提货单的状态");
            }
            if (
                    !status.equals(StatusCodeEnum.Rejected.getCode())
                            && !status.equals(StatusCodeEnum.ToPayment.getCode())
                            && !status.equals(StatusCodeEnum.ToConfirm.getCode() ))
            {
                throw new ApplicationException("当前提货单的状态不允许进行更新操作");
            }
            List<SalePickGoodsOrderSkuResponseDTO> oldSkus = dateDTO.getOldSkus();
            if (oldSkus == null || oldSkus.size() == 0) {
                throw new ApplicationException("系统错误，无法获取提货单的商品明细,请联系管理员");
            }
            List<SaleOrderItemMiddleResponseDTO> oldSaleSkus = dateDTO.getOldSaleSkus();
            if (oldSaleSkus == null || oldSaleSkus.size() == 0) {
                throw new ApplicationException("系统错误，无法获取提货单的关联的销售订单订单明细,请联系管理员！");
            }
            oldSkusMap = oldSkus.stream().collect(Collectors.groupingBy(sku -> sku.getSaleOrderItemId()));
        }
        HashMap<String, SaleOrderResponseDTO> saleOrderMap = dateDTO.getSaleOrderMap();
        if (saleOrderMap == null || saleOrderMap.size() == 0) {
            throw new ApplicationException("系统错误，无法获取提货单的关联的销售订单数据,请联系管理员！");
        }
        HashSet<String> skuCodeSet = new HashSet<>();
        for (SalePickGoodsOrderRequestDTO orderdto : record.getItems()) {
            String saleOrderCode = orderdto.getSaleOrderCode();
            SaleOrderResponseDTO saleOrderResponseDTO = saleOrderMap.get(saleOrderCode);
            if (saleOrderResponseDTO == null) {
                throw new ApplicationException("系统错误，无法获取关联的订单信息，请联系管理员！订单编号：" + saleOrderCode);
            }
            for (SalePickGoodsOrderSkuRequestDTO skudto : orderdto.getItems()) {
                Long saleOrderItemId = skudto.getSaleOrderItemId();
                List<SaleOrderItemMiddleResponseDTO> saleOrderItems = saleOrderResponseDTO.getSaleOrderItems();
                if (saleOrderItems == null || saleOrderItems.size() == 0) {
                    throw new ApplicationException("系统错误，无法获取销售订单的明细，销售订单编号：" + saleOrderCode);
                }
                List<SaleOrderItemMiddleResponseDTO> filterList = saleOrderItems.stream().filter(item -> saleOrderItemId.equals(item.getId())).collect(Collectors.toList());
                if (filterList.size() == 0) {
                    throw new ApplicationException("系统错误，销售订单明细不存在，saleOrderItemId:" + saleOrderItemId + ";参考参数：" + saleOrderItems.get(0).getId() + ";订单编号：" + saleOrderCode);
                }
                if (filterList.size() > 1) {
                    throw new ApplicationException("系统错误，根据1个saleOrderItemId获取了多条销售单明细，saleOrderItemId:" + saleOrderItemId + "，订单编号：" + saleOrderCode);
                }
                SaleOrderItemMiddleResponseDTO matchSkuItem = filterList.get(0);
                if(matchSkuItem.getSkuCode() == null){
                    throw new ApplicationException("系统错误，销售订单的明细中无法获取商品标号字段，销售订单编号：" + saleOrderCode+";明细商品编号："+matchSkuItem.getSkuCode());
                }
                if(!skuCodeSet.add(matchSkuItem.getSkuCode())){
                    throw new ApplicationException("请去除拥有相同的商品编码的提货明细："+matchSkuItem.getSkuCode());
                }
                Long skuQuantity = matchSkuItem.getSkuQuantity();
                if(skuQuantity == null){
                    throw new ApplicationException("系统错误，销售订单的明细中无法获取商品数量字段，销售订单编号：" + saleOrderCode+";明细商品编号："+matchSkuItem.getSkuCode());
                }
                Long pickNum = skudto.getPickNum();//本次申请的提货数量
                if(pickNum > skuQuantity){
                    throw new ApplicationException("商品编码" + matchSkuItem.getSkuCode() + "提货数量过多,不能超过采购数量"+skuQuantity);
                }
                Long availablePickNum = matchSkuItem.getAvailablePickNum();//剩余的可申请提货数量
                if (availablePickNum == null) {
                    throw new ApplicationException("系统错误，暂时无法获取订单的sku的可提货数量,sku编码：" + matchSkuItem.getSkuCode() + "，订单编号：" + saleOrderCode);
                }
                Long applyedNum = 0L;//已申请的旧的提货数量
                if (type.equals(UPDATE)) {
                    List<SalePickGoodsOrderSkuResponseDTO> oldSkuList = oldSkusMap.get(skudto.getSaleOrderItemId());
                    if (oldSkuList != null) {
                        if (oldSkuList.size() > 1) {
                            throw new ApplicationException("系统错误，根据1个saleOrderItemId获取了多条销售单明细，saleOrderItemId:" + saleOrderItemId + "，订单编号：" + saleOrderCode);
                        }
                        if (oldSkuList.size() > 0) {
                            applyedNum = oldSkuList.get(0).getPickNum();
                        }
                    }
                }
                Long lockedNum = 0L;//目前已占用（已锁定）的提货数量
                if (type.equals(UPDATE)) {
                    com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO oldPickInfo = dateDTO.getOldPickInfo();
                    Integer status = oldPickInfo.getStatus();
                    if(status.equals(StatusCodeEnum.Rejected.getCode())){
                        lockedNum = 0L;//当前提货单状态为已驳回，则当前已锁定数量一定为零
                    }else{
                        lockedNum = applyedNum;
                    }
                }
                Long addPickNum = pickNum - lockedNum;//追加申请提货数量
                if (availablePickNum - addPickNum < 0) {
                    throw new ApplicationException("商品编码" + matchSkuItem.getSkuCode() + "提货数量过多，本次最多申请提货数量为" + (applyedNum + availablePickNum));
                }
            }
        }
    }


    //封装新建的提货订单信息
    public void packDate(SalePickGoodsPlanInfoRequestDTO record, HashMap<String, Object> map, String type) {
        //获取部分数据
        SalePickGoodsInfoTransferDTO dateDTO = (SalePickGoodsInfoTransferDTO) map.get("dateDTO");
        HashMap<String, SaleOrderResponseDTO> saleOrderMap = dateDTO.getSaleOrderMap();
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO oldPickInfo = dateDTO.getOldPickInfo();
        Long userId = appRuntimeEnv.getUserId();
        //保存操作去掉前端传入无用数据
        if (type.equals(UPDATE)) {
            record.setId(dateDTO.getOldPickInfo().getId());
        }
        if (type.equals(SAVE)) {
            for (SalePickGoodsOrderRequestDTO orderdto : record.getItems()) {
                orderdto.setId(null);
                orderdto.setSaleOrderId(null);
                for (SalePickGoodsOrderSkuRequestDTO skudto : orderdto.getItems()) {
                    skudto.setId(null);
                }
            }
        }
        //封装提货sku的信息
        Map<Long, List<SalePickGoodsOrderSkuResponseDTO>> oldSkusMap = null;
        if (type.equals(UPDATE)) {
            List<SalePickGoodsOrderSkuResponseDTO> skus = dateDTO.getOldSkus();
            oldSkusMap = skus.stream().collect(Collectors.groupingBy(sku -> sku.getSaleOrderItemId()));
        }
        for (SalePickGoodsOrderRequestDTO orderdto : record.getItems()) {
            SaleOrderResponseDTO matchOrder = saleOrderMap.get(orderdto.getSaleOrderCode());
            List<SaleOrderItemMiddleResponseDTO> skuItems = matchOrder.getSaleOrderItems();
            Map<Long, List<SaleOrderItemMiddleResponseDTO>> skuMap = skuItems.stream().collect(Collectors.groupingBy(sku -> sku.getId()));
            for (SalePickGoodsOrderSkuRequestDTO skudto : orderdto.getItems()) {
                skudto.setAppId(appId);
                SaleOrderItemMiddleResponseDTO matchSkuItem = skuMap.get(skudto.getSaleOrderItemId()).get(0);
                skudto.setMajorPicture(matchSkuItem.getMajorPicture());
                skudto.setPrice(matchSkuItem.getPrice());
                skudto.setSkuId(matchSkuItem.getSkuId());
                skudto.setSkuName(matchSkuItem.getSkuName());
                skudto.setPurchaseQuantity(matchSkuItem.getSkuQuantity());
                skudto.setSkuFormat(matchSkuItem.getSkuFormat());
                BigDecimal pickNumBig = new BigDecimal(skudto.getPickNum().toString());
                BigDecimal skuItemSubtotal = pickNumBig.multiply(matchSkuItem.getPrice());
                skudto.setSkuItemSubtotal(skuItemSubtotal);
                skudto.setUnitId(matchSkuItem.getUnitId());
                skudto.setUnitName(matchSkuItem.getUnitName());
                skudto.setWaitSendNum(0L);//由于修改操作只能在发货之前进行，所以暂时认为此时订单还没能发货，能进行修改操作的商品明细待发货数量都为0
                Long applyedNum = 0L;//已申请的可提货数量
                Long pickNum = skudto.getPickNum();//本次申请的提货数量
                if (type.equals(UPDATE)) {
                    List<SalePickGoodsOrderSkuResponseDTO> oldSkuList = oldSkusMap.get(skudto.getSaleOrderItemId());
                    if (oldSkuList != null) {
                        if (oldSkuList.size() > 0) {
                            applyedNum = oldSkuList.get(0).getPickNum();
                        }
                    }
                }
                Long addPickNum = pickNum - applyedNum;//追加申请提货数量
                Long lockNum = addPickNum;
                skudto.setLockNum(lockNum);//本次对销售订单明细表的可申请剩余提货数量的修改值
            }
        }
        //封装关联订单信息
        for (SalePickGoodsOrderRequestDTO orderdto : record.getItems()) {
            orderdto.setAppId(appId);
            SaleOrderResponseDTO matchOrder = saleOrderMap.get(orderdto.getSaleOrderCode());
            orderdto.setSaleOrderId(matchOrder.getId());
            orderdto.setOrderType(matchOrder.getTicketType() == null ? null : matchOrder.getTicketType()+"");
            orderdto.setCreatedBy(userId == null ? null : userId.toString());
            long sum = orderdto.getItems().stream().mapToLong(sku -> sku.getPickNum()).sum();
            orderdto.setGoodsNumber(sum);
            BigDecimal goodsMoney = orderdto.getItems()
                    .stream()
                    .map(sku -> sku.getSkuItemSubtotal())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            orderdto.setGoodsMoney(goodsMoney);
        }
        //封装提货订单数据
        record.setAppId(appId);
        long goodsNumberSum = record.getItems().stream().mapToLong(order -> order.getGoodsNumber()).sum();
        record.setTotalGoodsNumber(goodsNumberSum);
        BigDecimal totalGoodsMoney = record.getItems().stream().map(order -> order.getGoodsMoney()).reduce(BigDecimal.ZERO, BigDecimal::add);
        record.setTotalGoodsMoney(totalGoodsMoney);
        if (type.equals(SAVE)) {
            String code = identifierGenerator.getIdentifier("pink_goods", "GLTH" + DateUtils.format(new Date(), "yyyyMMdd"), 6);
            record.setPickGoodsCode(code);
            record.setCreatedBy(userId == null ? null : userId.toString());
            record.setCreatedTime(new Date());
            if (SalePickGoodsEnum.OUTER_WAREHOUSE.getCode().equals(record.getPickGoodsWayZt())) {
                record.setStatus(StatusCodeEnum.ToConfirm.getCode());
                record.setPaymentStatus(StatusCodeEnum.Blank.getCode());//产品：如果提货方式为外仓，则付款状态一定是空
            } else {
                record.setStatus(StatusCodeEnum.ToPayment.getCode());
                record.setPaymentStatus(StatusCodeEnum.Blank.getCode());
            }
            record.setPayAmount(BigDecimal.ZERO);
            record.setTotalDeliveryQuantity(0L);
        }
        if (type.equals(UPDATE)) {
            record.setUpdatedBy(userId == null ? null : userId.toString());
            record.setUpdatedTime(new Date());
            if (SalePickGoodsEnum.OUTER_WAREHOUSE.getCode().equals(record.getPickGoodsWayZt())) {
                record.setStatus(StatusCodeEnum.ToConfirm.getCode());
                record.setPaymentStatus(StatusCodeEnum.Blank.getCode());//产品：如果提货方式为外仓，则付款状态一定是空
            } else {
                BigDecimal payAmount = oldPickInfo.getPayAmount() == null ? BigDecimal.ZERO : oldPickInfo.getPayAmount();
                if (payAmount.equals(BigDecimal.ZERO)) {//付款金额为零
                    record.setStatus(StatusCodeEnum.ToPayment.getCode());
                    record.setPaymentStatus(StatusCodeEnum.ToPayment.getCode());
                } else if (payAmount.compareTo(totalGoodsMoney) >= 0) {//付款金额和总金额一样或者更多
                    record.setStatus(StatusCodeEnum.ToConfirm.getCode());
                    record.setPaymentStatus(StatusCodeEnum.ReceivedPayments.getCode());
                } else {//付款金额不为零但是少于总金额
                    record.setStatus(StatusCodeEnum.ToPayment.getCode());
                    record.setPaymentStatus(StatusCodeEnum.PartPayment.getCode());
                }
            }
        }
        //封装客户信息
        Long customerId = dateDTO.getCustomerId();
        String customerName = dateDTO.getCustomerName();
        record.setCustomerId(customerId);
        record.setCustomerName(customerName);
        //封装卖家顶级组织信息
        Long sellerId = dateDTO.getSellerId();
        String sellerName = dateDTO.getSellerName();
        record.setSellerId(sellerId);
        record.setSellerName(sellerName);
        //封装买家顶级组织信息
        Long buyerId = dateDTO.getBuyerId();
        String buyerName = dateDTO.getBuyerName();
        record.setBuyerId(buyerId);
        record.setBuyerName(buyerName);
        //封装归属组织（可能是一级组织也可能为顶级组织）
        Long ascriptionOrgId = dateDTO.getAscriptionOrgId();
        record.setAscriptionOrgId(ascriptionOrgId);
        if(record.getAscriptionOrgId() == null){
            record.setAscriptionOrgId(sellerId);
        }
        //封装经手人、经手人名称
        record.setHandler(appRuntimeEnv.getUserId());
        record.setHandlerName(appRuntimeEnv.getUsername());

    }


    /**
     * 获取提货单保存或更新需要的信息
     */
    private HashMap<String, Object> getDate(SalePickGoodsPlanInfoRequestDTO record, String type) throws Exception {
        SalePickGoodsInfoTransferDTO dateDTO = new SalePickGoodsInfoTransferDTO();
        if (UPDATE.equals(type)) {
            //根据未修改前提货计划编号查找提货订单
            com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO pickInfo = getPickByCode(record.getPickGoodsCode());
            dateDTO.setOldPickInfo(pickInfo);
            //查询未修改该提货订单关联的提货sku
            List<SalePickGoodsOrderSkuResponseDTO> oldSkus = getSkuByInfoId(pickInfo == null ? null : pickInfo.getId());
            dateDTO.setOldSkus(oldSkus);
            //查询旧sku关联的销售订单项目
            List<Long> saleOrderItemIds = null;
            if (oldSkus != null) {
                saleOrderItemIds = oldSkus.stream().map(sku -> sku.getSaleOrderItemId()).collect(Collectors.toList());
            }
            if (saleOrderItemIds != null && saleOrderItemIds.size() > 0) {
                List<SaleOrderItemMiddleResponseDTO> orderItems = getSaleItem(saleOrderItemIds);
                dateDTO.setOldSaleSkus(orderItems);
            }
        }
        //获取关联订单信息
        HashMap<String, SaleOrderResponseDTO> saleOrderMap = new HashMap<>();
        for (SalePickGoodsOrderRequestDTO orderDto : record.getItems()) {
            String saleOrderCode = orderDto.getSaleOrderCode();
            Payload<SaleOrderResponseDTO> payload = saleOrderInfoClient.selectSaleOrder(saleOrderCode);
            if (!SUCCESS.equals(payload.getCode())) {
                throw new ApplicationException("该提货单关联的编号为" + saleOrderCode + "订单信息获取失败，请稍后再试");
            }
            SaleOrderResponseDTO saleOrderDTO = GeneralConvertUtils.conv(payload.getPayload(), SaleOrderResponseDTO.class);
            if (saleOrderDTO == null || saleOrderDTO.getCode() == null) {
                throw new ApplicationException("该提货单关联的编号为" + saleOrderCode + "订单信息获取失败，请稍后再试！");
            }
            saleOrderMap.put(saleOrderDTO.getCode(), saleOrderDTO);
        }
        ArrayList<SaleOrderResponseDTO> saleOrderArr = new ArrayList<>();
        for (Map.Entry<String, SaleOrderResponseDTO> entry : saleOrderMap.entrySet()) {
            saleOrderArr.add(entry.getValue());
        }

        //获取销售订单明细
        for (Map.Entry<String, SaleOrderResponseDTO> entry : saleOrderMap.entrySet()) {
            SaleOrderItemMiddleRequestQuery query = new SaleOrderItemMiddleRequestQuery();
            SaleOrderResponseDTO saleOrderDto = entry.getValue();
            query.setSaleOrderId(saleOrderDto.getId());
            List<SaleOrderItemMiddleResponseDTO> orderItems = saleOrderItemClient.listSaleOrderItems(query);
            saleOrderDto.setSaleOrderItems(orderItems);
        }
        dateDTO.setSaleOrderMap(saleOrderMap);

        //获取卖家所属一级组织ID
        Long ascriptionOrgId = null;
        Long productId = record.getProductId();
        if (productId != null) {
            ascriptionOrgId = getOrg(record.getTenantId(), this.appId, productId + "");
        } else {
            ascriptionOrgId = record.getOrgId();
        }
        if (ascriptionOrgId != null) {
            dateDTO.setAscriptionOrgId(ascriptionOrgId);
        } else {
            log.error("无法获取一级组织 ：ascriptionOrgId：null");
        }

        GroupResultVO topOrganization = appRuntimeEnv.getTopOrganization();
        if (topOrganization == null || topOrganization.getId() == null) {
            log.error("无法获取顶级组织：topOrganization ：" + topOrganization);
        }


        //获取买家信息
        HashMap<String, Object> buyerDate = getBuyerDate(saleOrderArr, record.getOperaterIsBuyer(), topOrganization);
        dateDTO.setBuyerId((Long) buyerDate.get("buyerId"));
        dateDTO.setBuyerName((String) buyerDate.get("buyerName"));
        if (dateDTO.getBuyerId() == null) {
            log.info("无法封装买家id");
        }
        if (dateDTO.getBuyerName() == null) {
            log.info("无法封装买家名称");
        }


        //获取业务伙伴（当前账号的伙伴）
        BusinessPartnerResponseDTO businessPartner = null;
        if (topOrganization != null && topOrganization.getId() != null) {
            businessPartner = getBusinessPartner(record.getTenantId(), record.getAppId(), topOrganization.getId(), null);
        } else {
            log.error("无法获取业务伙伴,因为无法获取顶级组织,topOrganization：" + topOrganization);
        }

        //获取对应客户信息
        HashMap<String, Object> customerDate = getCustomerDate(saleOrderArr, ascriptionOrgId, businessPartner, record.getTenantId(), record.getAppId(), record);
        dateDTO.setCustomerId((Long) customerDate.get("customerId"));
        dateDTO.setCustomerName((String) customerDate.get("customerName"));
        if (dateDTO.getCustomerId() == null) {
            log.info("无法封装客户id");
        }
        if (dateDTO.getCustomerName() == null) {
            log.info("无法封装客户名称");
        }

        //获取卖家的信息
        HashMap<String, Object> sellerDate = getSellerDate(saleOrderArr, ascriptionOrgId,record);
        dateDTO.setSellerId((Long) sellerDate.get("sellerId"));
        dateDTO.setSellerName((String) sellerDate.get("sellerName"));
        if (dateDTO.getSellerId() == null) {
            log.info("无法封装卖家顶级组织详情");
        }
        if (dateDTO.getSellerName() == null) {
            log.info("无法封装卖家顶级组织详情");
        }

        //返回结果
        HashMap<String, Object> map = new HashMap<>();
        map.put("dateDTO", dateDTO);
        return map;
    }


    /**
     * 启动流程（业务链路）
     *
     * @return
     * @throws Exception
     */
    @Deprecated
    private void startProcess(SalePickGoodsPlanInfoRequestDTO record, HashMap<String, Object> dateMap) throws Exception {
        //封装参数
        ToolLinkStartLinkRequestDTO toolLinkStartLinkRequestDTO = new ToolLinkStartLinkRequestDTO();
        toolLinkStartLinkRequestDTO.setListId(record.getId());
        toolLinkStartLinkRequestDTO.setLinkType("mall");
        toolLinkStartLinkRequestDTO.setListNo(record.getPickGoodsCode());
        toolLinkStartLinkRequestDTO.setTenantId(record.getTenantId());
        toolLinkStartLinkRequestDTO.setAppId(record.getAppId());
        HashMap<String, Object> map = new HashMap();
        map.put("ListType", record.getStatus());
        map.put("Customer", record.getCreatedBy());
        String json = JsonUtil.bean2JsonString(map);
        toolLinkStartLinkRequestDTO.setListFormData(json);
        toolLinkStartLinkRequestDTO.setListType(ListTypeEnum.SalesOrder.name());
        //调用链路
        Payload<ToolLinkStartLinkResponseDTO> responseDTO = null;
        try {
            responseDTO = toolLinkClient.startLink(toolLinkStartLinkRequestDTO);
        } catch (Exception e) {
            log.error("创建并提交提货计划时，调用链路接口出错", e);
        }
        //根据链路接口封装状态数据
        if (responseDTO != null && SUCCESS.equals(responseDTO.getCode()) && responseDTO.getPayload() != null) {
            ToolLinkStartLinkResponseDTO linkDTO = GeneralConvertUtils.conv(responseDTO.getPayload(),
                    ToolLinkStartLinkResponseDTO.class);
            if (linkDTO.getDisabledBusinessCodes().contains(LinkBusinessCodeEnum.ArtificialOrder.getMsg())) {
                record.setStatus(StatusCodeEnum.ToPayment.getCode());
            }
            if (linkDTO.getDisabledBusinessCodes().contains(LinkBusinessCodeEnum.ArtificialOrder.getMsg())) {
                record.setStatus(StatusCodeEnum.ToPayment.getCode());
            }
            if (linkDTO.getDisabledBusinessCodes().contains(LinkBusinessCodeEnum.ReceiptConfirm.getMsg())) {
                record.setStatus(StatusCodeEnum.ReceivedPayments.getCode());
            }
        }
        if (record.getStatus() == null) {
            record.setStatus(StatusCodeEnum.ToPayment.getCode());
        }
        //更新主单状态
        SalePickGoodsInfoRequestDTO updatePickGoods = new SalePickGoodsInfoRequestDTO();
        updatePickGoods.setStatus(record.getStatus());
        salePickGoodsInfoClient.updateById(record.getId(), updatePickGoods);
    }


    /**
     * 拼装业务链路接口的items参数
     *
     * @param result
     */
    private List<ToolStatusActionRequestQuery.Items> getItemList(PageBean<SalePickGoodsInfoResponseDTO> result, String listType, Integer type) {
        List<ToolStatusActionRequestQuery.Items> itemsList = new ArrayList<>();
        for (SalePickGoodsInfoResponseDTO dto : result.getContent()) {
            ToolStatusActionRequestQuery.Items item = new ToolStatusActionRequestQuery.Items();
            item.setStatusCode(dto.getStatus());
            if (type == 1) {//在线订购
                item.setStatusType(null);
                if (dto.getTotalDeliveryQuantity() == null) {
                    dto.setTotalDeliveryQuantity(0L);
                }
                if (dto.getTotalGoodsNumber() == null) {//总申请提货数量
                    dto.setTotalGoodsNumber(0L);
                }
                if (dto.getTotalSignQuantity() == null) {//签收数量
                    dto.setTotalSignQuantity(0l);
                }
                if (dto.getTotalGoodsMoney() == null) {
                    dto.setTotalGoodsMoney(BigDecimal.ZERO);
                }
                if (dto.getPayAmount() == null) {
                    dto.setPayAmount(BigDecimal.ZERO);
                }
                if(dto.getDeliveryAmount() == null){
                    dto.setDeliveryAmount(BigDecimal.ZERO);
                }
                if(dto.getRefundAmount() == null){
                    dto.setRefundAmount(BigDecimal.ZERO);
                }
                HashMap<String, Object> map = new HashMap();
                Long unPickQuantity = dto.getTotalGoodsNumber() - dto.getTotalDeliveryQuantity();
                Long unDeliverQuantity = dto.getTotalGoodsNumber() - dto.getTotalDeliveryQuantity();
                Long unSignQuantity = dto.getTotalDeliveryQuantity() - dto.getTotalSignQuantity();
                Long confirmedStatus = 15L;//确认状态，意思是收款记录的确认状态（默认为已确认状态）
                if(
                        (StatusCodeEnum.ToPayment.getCode().equals(dto.getPaymentStatus()) || StatusCodeEnum.Blank.getCode().equals(dto.getPaymentStatus()))
                                &&
                                dto.getPayAmount().compareTo(BigDecimal.ZERO) > 0
                                &&
                                StatusCodeEnum.ToPayment.getCode().equals(dto.getStatus())
                ){
                    confirmedStatus = -15L;//如果提货单的付款状态为待付款，并且提货单付款金额大于0，则认为是线下付款类型且收款记录状态为待确认的情况，此时confirmedStatus为未确认
                }
                map.put("unPickQuantity", unPickQuantity);//未提货
                map.put("confirmedStatus", confirmedStatus);//确认状态
                map.put("unDeliverQuantity", unDeliverQuantity);//未发货
                map.put("unSignQuantity", unSignQuantity);//未签收
                map.put("totalGoodsNumber", dto.getTotalGoodsNumber());//商品总数
                map.put("paymentStatus", dto.getPaymentStatus());//付款状态
                String pickGoodsWay = dto.getPickGoodsWayZt();
                BigDecimal refundableAmount = null;
                if ("OUTER_WAREHOUSE".equals(pickGoodsWay)) {
                    refundableAmount = dto.getPayAmount().subtract(dto.getRefundAmount());//外仓不需要付款，若外仓付款了，则可退金额为已付款金额
                }else{
                    refundableAmount = dto.getPayAmount().subtract(dto.getRefundAmount()).subtract(dto.getDeliveryAmount());//可退款金额=已付金额-出库金额-已退金额
                }
                map.put("refundableAmount", refundableAmount.doubleValue());//可退款金额
                dto.setRefundableAmount(refundableAmount);//暂时在这里封装可退款金额
                map.put("nonPaymentAmount", dto.getTotalGoodsMoney().subtract(dto.getPayAmount()).doubleValue()); //未付款
                map.put("pickGoodsCode", dto.getPickGoodsCode()); //提货单编号（只用于打印日志不筛选）
                log.info("工具类参数：" + JsonUtil.bean2JsonString(map));
                item.setRuleData(JsonUtil.bean2JsonString(map));
            } else if (type == 2) {//运营中心
                item.setStatusType("OrderStatus");
                //根据条件获得按钮
                if (dto.getTotalDeliveryQuantity() == null) {
                    dto.setTotalDeliveryQuantity(0L);
                }
                if (dto.getTotalGoodsNumber() == null) {//总申请提货数量
                    dto.setTotalGoodsNumber(0L);
                }
                if (dto.getTotalSignQuantity() == null) {//签收数量
                    dto.setTotalSignQuantity(0l);
                }
                HashMap<String, Object> map = new HashMap();
                Long unPickQuantity = dto.getTotalGoodsNumber() - dto.getTotalDeliveryQuantity();
                Long unDeliverQuantity = dto.getTotalGoodsNumber() - dto.getTotalDeliveryQuantity();
                Long unSignQuantity = dto.getTotalDeliveryQuantity() - dto.getTotalSignQuantity();
                map.put("unPickQuantity", unPickQuantity);//未提货
                map.put("confirmedStatus", 15);//确认状态
                map.put("unDeliverQuantity", unDeliverQuantity);//未发货
                map.put("unSignQuantity", unSignQuantity);//未签收
                map.put("totalGoodsNumber", dto.getTotalGoodsNumber());//商品总数
                log.info("工具类参数：" + JsonUtil.bean2JsonString(map));
                item.setRuleData(JsonUtil.bean2JsonString(map));
            }
            itemsList.add(item);
        }
        return itemsList;
    }


    //查询客户信息
    private List<MerchantResponseDTO> getMerchat(String tenantId, Long appId, Long partnerId, String orgId) throws ApplicationException {
        MerchantRequestQuery query = new MerchantRequestQuery();
        query.setTenantId(tenantId);
        query.setAppId(appId);
        query.setPartnerId(partnerId);
        query.setOrgId(orgId);
        try {
            Payload<List<MerchantResponseDTO>> payload = merchantClient.findList(query);
            List<MerchantResponseDTO> merchantResponseDTOS = null;
            if ("0".equals(payload.getCode()) && payload.getPayload() != null) {
                merchantResponseDTOS = GeneralConvertUtils.convert2List(payload.getPayload(), MerchantResponseDTO.class);
            } else {
                log.error("获取客户失败：payload.getCode()：" + payload.getCode() + ";payload.getMsg()" + payload.getMsg() + ";tenantId：{}，AppId：{}，PartnerId：{}，orgId：{}", tenantId, appId, partnerId, orgId);
            }
            if (merchantResponseDTOS == null || merchantResponseDTOS.size() == 0) {
                log.error("无法获取客户信息:merchantResponseDTOS：{}，tenantId：{}，AppId：{}，PartnerId：{}，orgId：{}", merchantResponseDTOS, tenantId, appId, partnerId, orgId);
            }
            return merchantResponseDTOS;
        } catch (Exception e) {
            log.error("查询客户信息失败:", e);
            return null;
            //throw new ApplicationException("查询客户信息失败:{}", JsonUtil.bean2JsonString(query));
        }
    }


    //根据产品线id， 查询一级组织
    private Long getOrg(String tenantId, Long appId, String productLine) throws Exception {
        try {
            ToolCategoryRequestQuery toolCategoryRequestQuery = new ToolCategoryRequestQuery();
            toolCategoryRequestQuery.setAppId(appId);
            toolCategoryRequestQuery.setTenantId(tenantId);
            toolCategoryRequestQuery.setCategoryId(productLine);
            log.info("即将调用根据产品线id查询一级组织接口：toolCategoryRequestQuery:"+JSON.toJSONString(toolCategoryRequestQuery));
            Payload<Long> payload = toolCommoditytypeAuthorizeClient.getAuthorizeOrgByCategoryId(toolCategoryRequestQuery);
            log.info("根据产品线id查询一级组织接口返回payload："+JSON.toJSONString(payload));
            if (payload != null) {
                Long ascriptionOrgId = payload.getPayload();
                if (ascriptionOrgId == null) {
                    log.error("无法获取一级组织id,ascriptionOrgId:null;" + tenantId + ";appId:" + appId + ";productLine:" + productLine);
                }
                return ascriptionOrgId;
            }
        } catch (Exception e) {
            log.error("根据产品线获取关联一级组织出现异常tenantId:" + tenantId + ";appId:" + appId + ";productLine:" + productLine, e);
        }
        return null;
    }


    //获取当前用户所属业务伙伴
    private BusinessPartnerResponseDTO getBusinessPartner(String tenantId, Long appId, Long topOrganizationId, Long userId) throws Exception {
        BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
        businessPartnerRequestQuery.setTenantId(tenantId);
        businessPartnerRequestQuery.setAppId(appId);
        businessPartnerRequestQuery.setOrgId(topOrganizationId);
        businessPartnerRequestQuery.setUserId(userId);
        Payload<BusinessPartnerResponseDTO> partner = null;
        log.info("即将调用获取业务伙伴接口：参数businessPartnerRequestQuery"+JSON.toJSONString(businessPartnerRequestQuery));
        try {
            partner = businessPartnerClient.getPartner(businessPartnerRequestQuery);
        } catch (Exception e) {
            log.error("试图获取当前用户所属业务伙伴,请求businessPartnerClient出现异常，businessPartner：" + JSON.toJSONString(businessPartnerRequestQuery), e);
            return null;
        }
        if (partner == null) {
            log.error("试图获取当前用户所属业务伙伴，但是请求businessPartnerClient失败，businessPartner：" + JSON.toJSONString(businessPartnerRequestQuery) + "partner ：null");
            return null;
        }
        BusinessPartnerResponseDTO businessPartner = GeneralConvertUtils.conv(partner.getPayload(), BusinessPartnerResponseDTO.class);
        if (businessPartner == null || businessPartner.getId() == null) {
            log.error("无法获取所属业务伙伴，businessPartner：" + JSON.toJSONString(businessPartner));
        }
        return businessPartner;
    }

    /**
     * @param requestDTO
     * @Description: 审批通过.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    @Override
    public Boolean examinePass(ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception {
        log.info("审批通过,入参:{}",JSON.toJSONString(requestDTO));
        if (Objects.isNull(requestDTO)) {
            return Boolean.FALSE;
        }
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                salePickGoodsInfoClient.selectById(requestDTO.getId());
        if (Objects.isNull(salePickGoodsInfoResponseDTO)) {
            return Boolean.FALSE;
        }
        //校验库存
        checkInventory(requestDTO);
        //审批通过 处理商品信息
        SalePickGoodsInfoRequestDTO salePickGoodsInfoRequestDTO = requestDTO.clone(SalePickGoodsInfoRequestDTO.class);
        salePickGoodsInfoRequestDTO.setItems(ObjectCloneUtils.convertList(requestDTO.getSaleOrderList(),
                com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderRequestDTO.class));
        Payload<Boolean> payload = salePickGoodsInfoClient.examinePass(salePickGoodsInfoRequestDTO);
        //记录操作
        if (payload.getPayload()) {
            OrderOperationRecordRequestDTO recordRequestDTO = new OrderOperationRecordRequestDTO();
            recordRequestDTO.setOrderId(requestDTO.getId());//提货单id
            recordRequestDTO.setOperationType(2);//操作类型1 订单 2 提货单
            recordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            recordRequestDTO.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
            recordRequestDTO.setAppId(salePickGoodsInfoResponseDTO.getAppId());
            recordRequestDTO.setOperation("审批通过");
            orderOperationRecordClient.insert(recordRequestDTO);
            //获取订单的信息
            List<String> saleOrderCodeList = requestDTO.getSaleOrderList().stream().map(ExamineOrderInfoRequestDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
            List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOList = GeneralConvertUtils.convert2List(saleOrderInfoClient.batchSearchSaleOrderInfo(saleOrderCodeList).getPayload(), SaleOrderInfoResponseDTO.class);
            if (CollectionUtils.isNotEmpty(saleOrderInfoResponseDTOList)) {
                log.info("走业务链路的订单数据:{}", JSON.toJSONString(saleOrderInfoResponseDTOList));
                List<Long> saleOrderIdList = saleOrderInfoResponseDTOList.stream().map(SaleOrderInfoResponseDTO::getId).collect(Collectors.toList());
                ToolLinkBatchNextBusinessRequestDTO toolLink = new ToolLinkBatchNextBusinessRequestDTO();
                toolLink.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
                toolLink.setAppId(salePickGoodsInfoResponseDTO.getAppId());
                toolLink.setListIds(saleOrderIdList);//订单ID
                toolLink.setListType("SalesOrder");//类型 例如销售订单：SalesOrder
                toolLink.setBusinessCode(LinkBusinessCodeEnum.PickGoodsApproval.name());
                Payload<ToolLinkNextBusinessResponseDTO> toolLinkNextBusinessResponseDTOPayload = toolLinkClient.batchNextLinkBusiness(toolLink);
                log.info("获取环节状态:{}", JsonUtil.bean2JsonString(toolLinkNextBusinessResponseDTOPayload));
            }
            //修改提货单状态
            SalePickGoodsInfoRequestDTO goodsInfoRequestDTO = new SalePickGoodsInfoRequestDTO();
            goodsInfoRequestDTO.setStatus(StatusCodeEnum.ToDelivery.getCode());//写死 待发货
            //发送到云仓
            planOrderSendService.sendMsg(requestDTO);
            return salePickGoodsInfoClient.updateById(salePickGoodsInfoRequestDTO.getId(), goodsInfoRequestDTO);
        }
        //发送到云仓
        planOrderSendService.sendMsg(requestDTO);
        return Boolean.TRUE;
    }

    /**
     * 校验实时库存
     *
     * @param requestDTO
     */
    private void checkInventory(ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception{
        List<ExamineOrderInfoRequestDTO> saleOrderList = requestDTO.getSaleOrderList();
        for(ExamineOrderInfoRequestDTO saleOrder : saleOrderList){
            List<ExamineSkuRequestDTO> items = saleOrder.getItems();
            if (!CollectionUtil.isEmpty(items)) {
                //获取当前账号可操作仓库，并且判断是否能对仓库进行操作
                StockFindAllPostQuery stockFindAllPostQuery = new StockFindAllPostQuery();
                stockFindAllPostQuery.setAppId(saleOrder.getAppId());
                stockFindAllPostQuery.setTenantId(appRuntimeEnv.getTenantId());
                PickGoodStoreHouseReq pickReq = new PickGoodStoreHouseReq();
                pickReq.setPickGoodsCode(requestDTO.getPickGoodsCode());
                pickReq.setConditionType("sendGoods");
                stockFindAllPostQuery.setPickGoodStoreHouseReq(pickReq);
                List<DepotFindPagePostResponseDTODepot> content = saleOrderInfoService.getOperateDepot(stockFindAllPostQuery);
                if(CollectionUtil.isEmpty(content)){
                    throw new ApplicationException("您暂时没有仓库的相关权限");
                }
                List<String> depotNoArr = content.stream().map(DepotFindPagePostResponseDTODepot::getDepotNo).collect(Collectors.toList());
                for(ExamineSkuRequestDTO item : items){
                    if (!depotNoArr.contains(item.getWarehouseCode())) {
                        throw new ApplicationException("编号为"+item.getWarehouse() + "的仓库不存在或您没有权限");
                    }
                }
                //对上云仓的仓库的sku校验库存
                List<ExamineSkuRequestDTO> collect = items.stream().filter(item -> item.getIsJoin() == 1).collect(Collectors.toList());
                if(CollectionUtil.isNotEmpty(collect)){
                    //根据skuNo 获取仓库列表
                    for(ExamineSkuRequestDTO item : collect){
                        //获取当前sku的仓库信息
                        stockFindAllPostQuery.setSkuId(item.getSkuId());
                        stockFindAllPostQuery.setSkuCode(item.getSkuCode());
                        List<StockResponseDTO> realTimeStock = saleOrderInfoService.getRealTimeStock(stockFindAllPostQuery);
                        if (!CollectionUtil.isEmpty(realTimeStock)) {
                            List<String> depotNos = realTimeStock.stream().map(StockResponseDTO::getDepotNo).collect(Collectors.toList());
                            //判断对应仓库的对应库存
                            realTimeStock.forEach(real -> {
                                if (depotNos.contains(item.getWarehouseCode())) {
                                    //校验仓库库存
                                    if (real.getDepotNo().equals(item.getWarehouseCode())) {
                                        if (real.getAvailableStockQty() -  item.getDeliveryQuantity() < 0) {
                                            throw new ApplicationException("出货数量大于" + real.getName() + "库存数量");
                                        }
                                    }
                                } else {
                                    throw new ApplicationException(item.getWarehouse() + "不存在或没有库存");
                                }
                            });
                        } else {
                            throw new ApplicationException("加载仓库失败");
                        }
                    }
                }
            } else {
                throw new ApplicationException("请选择仓库和出库数");
            }
        }
    }

    /**
     * @param requestDTO
     * @Description: 审批驳回.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    @Override
    public Boolean examineReject(ExamineRejectInfoRequestDTO requestDTO) throws Exception {
        if (Objects.isNull(requestDTO)) {
            return Boolean.FALSE;
        }
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                salePickGoodsInfoClient.selectById(requestDTO.getId());
        if (Objects.isNull(salePickGoodsInfoResponseDTO)) {
            return Boolean.FALSE;
        }
        SalePickGoodsInfoRequestDTO salePickGoodsInfoRequestDTO = requestDTO.clone(SalePickGoodsInfoRequestDTO.class);
        Payload<Boolean> payload = salePickGoodsInfoClient.examineReject(salePickGoodsInfoRequestDTO);
        if (payload.getPayload()){
            //记录操作
            OrderOperationRecordRequestDTO recordRequestDTO = new OrderOperationRecordRequestDTO();
            recordRequestDTO.setOrderId(requestDTO.getId());//提货单id
            recordRequestDTO.setOperationType(2);//操作类型1 订单 2 提货单
            recordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            recordRequestDTO.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
            recordRequestDTO.setAppId(salePickGoodsInfoResponseDTO.getAppId());
            recordRequestDTO.setOperation("审批驳回");
            recordRequestDTO.setRemark(requestDTO.getRemark());
            orderOperationRecordClient.insert(recordRequestDTO);
            //修改提货单状态
            SalePickGoodsInfoRequestDTO salePickGoodsRequestDTO = new SalePickGoodsInfoRequestDTO();
            salePickGoodsRequestDTO.setStatus(StatusCodeEnum.Rejected.getCode());//写死 已驳回
            return salePickGoodsInfoClient.updateById(requestDTO.getId(), salePickGoodsRequestDTO);
        }
        return Boolean.FALSE;
    }

    /**
     * @param dto
     * @Description: 提货计划中的发货.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/13
     */
    @SneakyThrows
    @Override
    public Boolean pickPlanDeliveryGoods(SalePickDeliveryInfoRequestDTO dto) throws Exception {
        if (Objects.isNull(dto)) {
            throw new ApplicationException("发货参数为空");
        }
        log.info("发货参数:{}",JSON.toJSONString(dto));
        Long skuTotalQuantity = dto.getSalePickDeliveryOrderInfoList().stream().map(SalePickDeliveryOrderInfoRequestDTO::getSalePickDeliveryItemInfoList).flatMap(Collection::stream).mapToLong(map -> map.getSkuShipmentQuantity()).sum();
        if (skuTotalQuantity == 0L) {
            throw new ApplicationException("所有商品的【本次出库数量】之合必须大于0");
        }
        //过滤掉订单+仓库里总发货数量 = 0 的数据
        List<SalePickDeliveryOrderInfoRequestDTO> list = dto.getSalePickDeliveryOrderInfoList().stream().filter(f -> !ObjectUtils.equals(f.getSalePickDeliveryItemInfoList().stream().
                mapToLong(SalePickDeliveryItemInfoRequestDTO::getSkuShipmentQuantity).sum(), 0L)).collect(Collectors.toList());
        dto.setSalePickDeliveryOrderInfoList(list);
        //生成出库单
        SalePickDeliveryInfoMiddleRequestDTO deliverInfoDTO = GeneralConvertUtils.conv(dto, SalePickDeliveryInfoMiddleRequestDTO.class);
        deliverInfoDTO.getSalePickDeliveryOrderInfoList().stream().filter(Objects::nonNull).forEach(f -> {
            String saleOutTaskCode = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER_OUT_CODE.getType(), IdentifierTypeEnum.ORDER_OUT_CODE.getPrefix() + DateUtils.format(new Date(), "yyyyMMdd"), IdentifierTypeEnum.ORDER_OUT_CODE.getLen());
            f.setSaleOutTaskCode(saleOutTaskCode);
            f.getSalePickDeliveryItemInfoList().stream().forEach(sku -> {
                sku.setSaleOrderCode(f.getSaleOrderCode());
                sku.setDeliveryWareHouseName(f.getDeliveryWareHouseName());
            });
        });
        //当前登录人
        deliverInfoDTO.setCreatedBy(appRuntimeEnv.getUsername());
        deliverInfoDTO.setIsolationId(appRuntimeEnv.getTopOrganization().getId()+"");
        deliverInfoDTO.setAscriptionOrgId(appRuntimeEnv.getUserOrganization().getId());//一级组织
        //发货
        Payload<Boolean> payload = salePickGoodsInfoClient.pickPlanDeliveryGoods(deliverInfoDTO);
        if (payload.getPayload()) {
            //获取提货单信息
            com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(dto.getPickGoodsId());
            log.info("提货单发货--总数量数量: {}--已发货数量: {}", salePickGoodsInfoResponseDTO.getTotalGoodsNumber(), salePickGoodsInfoResponseDTO.getTotalDeliveryQuantity());
            //未发货数量
            SalePickGoodsInfoRequestDTO salePickGoodsInfoRequestDTO = new SalePickGoodsInfoRequestDTO();
            salePickGoodsInfoRequestDTO.setStatus(ObjectUtils.equals(salePickGoodsInfoResponseDTO.getTotalGoodsNumber() - salePickGoodsInfoResponseDTO.getTotalDeliveryQuantity(), 0L) ?
                    StatusCodeEnum.AllDelivery.getCode() : StatusCodeEnum.PartDelivery.getCode());
            //更改提货单状态
            salePickGoodsInfoClient.updateById(dto.getPickGoodsId(), salePickGoodsInfoRequestDTO);
            //记录发货操作
            OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
            orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
            orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderOperationRecordRequestDTO.setCreatedTime(new Date());
            orderOperationRecordRequestDTO.setOrderId(dto.getPickGoodsId());
            orderOperationRecordRequestDTO.setOperation("提货单发货成功");
            orderOperationRecordRequestDTO.setOperationType(2);
            orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
            //获取订单的信息
            List<String> saleOrderCodeList = dto.getSalePickDeliveryOrderInfoList().stream().map(SalePickDeliveryOrderInfoRequestDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
            List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOList = GeneralConvertUtils.convert2List(saleOrderInfoClient.batchSearchSaleOrderInfo(saleOrderCodeList).getPayload(), SaleOrderInfoResponseDTO.class);
            Map<Boolean, List<SaleOrderInfoResponseDTO>> map = saleOrderInfoResponseDTOList.stream().collect(Collectors.groupingBy(e -> ObjectUtils.equals(e.getQuantity() - e.getTotalQuantity(), 0L)));
            List<SaleOrderInfoResponseDTO> nextStepList = map.get(true);//走下一步链路的订单数据
            if (CollectionUtils.isNotEmpty(nextStepList)) {
                log.info("走业务链路的订单数据:{}", JSON.toJSONString(nextStepList));
                List<Long> saleOrderIdList = nextStepList.stream().map(SaleOrderInfoResponseDTO::getId).collect(Collectors.toList());
                ToolLinkBatchNextBusinessRequestDTO toolLink = new ToolLinkBatchNextBusinessRequestDTO();
                toolLink.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());
                toolLink.setAppId(salePickGoodsInfoResponseDTO.getAppId());
                toolLink.setListIds(saleOrderIdList);//订单ID
                toolLink.setListType("SalesOrder");//类型 例如发货：SalesOrder
                toolLink.setBusinessCode(LinkBusinessCodeEnum.Delivery.name());
                Payload<ToolLinkNextBusinessResponseDTO> toolLinkNextBusinessResponseDTOPayload = toolLinkClient.batchNextLinkBusiness(toolLink);
                ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO = GeneralConvertUtils.conv(toolLinkNextBusinessResponseDTOPayload.getPayload(),
                        ToolLinkNextBusinessResponseDTO.class);
                log.info("获取环节状态:{}", JsonUtil.bean2JsonString(toolLinkNextBusinessResponseDTOPayload));
//                Integer status = toolLinkNextBusinessResponseDTO.getStatus();
//                //批量修改订单的状态
//                List<SaleOrderInfoRequestDTO> dtoList = nextStepList.stream().map(li -> {
//                    SaleOrderInfoRequestDTO saleOrderInfoRequestDTO = new SaleOrderInfoRequestDTO();
//                    saleOrderInfoRequestDTO.setId(li.getId());
//                    saleOrderInfoRequestDTO.setStatus(status);//业务链路返回的状态
//                    return saleOrderInfoRequestDTO;
//                }).collect(Collectors.toList());
//                if (CollectionUtils.isNotEmpty(dtoList)) {
//                    saleOrderInfoClient.updateBatchById(dtoList);
//                }
            }
           /*List<SaleOrderInfoResponseDTO> getStatusList = map.get(false);//获取当前状态的订单数据
            if (CollectionUtils.isNotEmpty(getStatusList)) {
                log.info("获取当前状态的订单数据:{}", JSON.toJSONString(getStatusList));
                //批量修改订单的状态
                List<SaleOrderInfoRequestDTO> SaleOrderInfoRequestDTOList = getStatusList.stream().map(li -> {
                    SaleOrderInfoRequestDTO saleOrderInfoRequestDTO = new SaleOrderInfoRequestDTO();
                    saleOrderInfoRequestDTO.setId(li.getId());
                    saleOrderInfoRequestDTO.setStatus(StatusCodeEnum.PartDelivery.getCode());//一定是部分签收
                    return saleOrderInfoRequestDTO;
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(SaleOrderInfoRequestDTOList)) {
                    saleOrderInfoClient.updateBatchById(SaleOrderInfoRequestDTOList);
                }
            }*/
        }
        return Boolean.TRUE;
    }


    /**
     * 根据提货单id获取提货单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public SalePickGoodsInfoDetailResponseDTO getDetailById(Long id) throws Exception {
        if (id == null || id == 0) {
            throw new ApplicationException(ResultEnum.ORDER_ID_IS_NULL);
        }
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                salePickGoodsInfoClient.selectById(id);
        if (salePickGoodsInfoResponseDTO == null) {
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        SalePickGoodsInfoDetailResponseDTO salePickGoodsInfoDetailResponseDTO =
                salePickGoodsInfoResponseDTO.clone(SalePickGoodsInfoDetailResponseDTO.class);
        salePickGoodsInfoDetailResponseDTO.setSalePickGoodsConsigneeResponseDTOS(salePickGoodsInfoResponseDTO.getSalePickGoodsConsigneeResponseDTOS());
        //查询提货单和订单关系
        List<SalePickGoodsOrderResponseDTO> salePickGoodsOrderResponseDTOList = getSalePickGoodsOrderById(id);
        //获得提货单对应的sku集合
        List<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuResponseDTOList = getSalePickGoodsOrderSkuById(id);
        Map<Long, List<SalePickGoodsOrderSkuResponseDTO>> skuMap =
                salePickGoodsOrderSkuResponseDTOList.stream().collect(
                        Collectors.groupingBy(SalePickGoodsOrderSkuResponseDTO::getPickGoodsOrderId));
        //种类
        Map<String, List<SalePickGoodsOrderSkuResponseDTO>> typeMap =
                salePickGoodsOrderSkuResponseDTOList.stream().collect(
                        Collectors.groupingBy(SalePickGoodsOrderSkuResponseDTO::getSkuCode));
        salePickGoodsInfoDetailResponseDTO.setCommodityType(typeMap.size());
        Long sum = salePickGoodsInfoResponseDTO.getTotalGoodsNumber();
        //salePickGoodsOrderSkuResponseDTOList.stream().mapToLong(p -> p.getPickNum()).sum();
        salePickGoodsInfoDetailResponseDTO.setCommodityNum(sum);
        salePickGoodsOrderResponseDTOList.forEach(item -> {
            item.setItems(skuMap.get(item.getId()));
        });
        //关联销售单明细，获取可申请提货数量
        List<Long> saleOrderItemIds =
                salePickGoodsOrderSkuResponseDTOList.stream().map(sku -> sku.getSaleOrderItemId()).collect(Collectors.toList());
        List<SaleOrderItemMiddleResponseDTO> saleItems = getSaleItem(saleOrderItemIds);
        if (saleItems != null) {
            Map<Long, List<SaleOrderItemMiddleResponseDTO>> saleItemsMap =
                    saleItems.stream().collect(Collectors.groupingBy(SaleOrderItemMiddleResponseDTO::getId));
            for (SalePickGoodsOrderSkuResponseDTO sku : salePickGoodsOrderSkuResponseDTOList) {
                List<SaleOrderItemMiddleResponseDTO> saleItemArr = saleItemsMap.get(sku.getSaleOrderItemId());
                if (saleItemArr == null || saleItemArr.size() == 0) {
                    continue;
                }
                SaleOrderItemMiddleResponseDTO matchItem = saleItemArr.get(0);
                sku.setAvailablePickNum(matchItem.getAvailablePickNum());
            }
        }

        salePickGoodsInfoDetailResponseDTO.setItems(GeneralConvertUtils.convert2List(salePickGoodsOrderResponseDTOList,
                SalePickGoodsOrderRespDTO.class));
        return salePickGoodsInfoDetailResponseDTO;
    }


    /**
     * 获取组织（根据组织id）
     */
    private GroupResultVO getGroup(Long orgId) {
        GroupResultVO userOrganization = null;
        try {
            userOrganization = iamUserService.getGroup(orgId, appRuntimeEnv.getTenantId(), orgId, null);
        } catch (Exception e) {
            log.error("根据组织id获取组织出现异常：orgId:"+orgId+";TenantId:" + appRuntimeEnv.getTenantId() + ";UserId：" + orgId + ";Username：" + null, e);
        }
        if (userOrganization == null) {
            log.error("根据组织id获取组织返回为null：orgId:"+orgId+";TenantId:" + appRuntimeEnv.getTenantId() + ";UserId：" + orgId + ";Username：" + null);
        }
        return userOrganization;
    }

    /**
     * @param id 提货单id
     * @return
     */
    public List<SalePickGoodsOrderResponseDTO> getSalePickGoodsOrderById(Long id) {
        SalePickGoodsOrderRequestQuery salePickGoodsOrderRequestQuery = new SalePickGoodsOrderRequestQuery();
        salePickGoodsOrderRequestQuery.setPickGoodsInfoId(id);
        List<SalePickGoodsOrderResponseDTO> salePickGoodsOrderResponseDTOList =
                salePickGoodsOrderClient.listSalePickGoodsOrders(salePickGoodsOrderRequestQuery);
        if (CollectionUtils.isEmpty(salePickGoodsOrderResponseDTOList)) {
            throw new ApplicationException(ResultEnum.ORDER_RELATION_IS_NULL);
        }
        return salePickGoodsOrderResponseDTOList;
    }

    /**
     * @param id 提货单id
     * @return
     */
    public List<SalePickGoodsOrderSkuResponseDTO> getSalePickGoodsOrderSkuById(Long id) {
        SalePickGoodsOrderSkuRequestQuery query = new SalePickGoodsOrderSkuRequestQuery();
        query.setPickGoodsInfoId(id);
        List<SalePickGoodsOrderSkuResponseDTO> list =
                salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(query);
        if (CollectionUtils.isEmpty(list)) {
            throw new ApplicationException(ResultEnum.ORDER_SKU_IS_NULL);
        }
        return list;
    }


    /**
     * @Description: 提货单的签收.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    @Override
    public Boolean sign(SaleOutTaskSignRequestDTO dto) throws Exception {
        log.info("提货单签收参数:{}", JSON.toJSONString(dto));
        SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO = dto.clone(SaleOutTaskMiddleRequestDTO.class);
        saleOutTaskMiddleRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());//当前登录人
        Payload<Boolean> payload = salePickGoodsInfoClient.sign(saleOutTaskMiddleRequestDTO);
        if (payload.getPayload()) {
            //获取提货单信息
            SaleOutTaskMiddleResponseDTO middleResponseDTO = saleOutTaskClient.selectById(dto.getIdList().get(0));//拿到相关出库单信息 1个提货单对应多个出库单
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = GeneralConvertUtils.conv(salePickGoodsInfoClient.selectById(middleResponseDTO.getSalePickGoodsId()), SalePickGoodsInfoResponseDTO.class);
            Long unSignQuantity = salePickGoodsInfoResponseDTO.getTotalGoodsNumber().longValue() - salePickGoodsInfoResponseDTO.getTotalSignQuantity().longValue();//未签收数量
            log.info("未签收数量: {}", unSignQuantity.toString());
            //待签收数 = 0 && 提货单下的订单发完 &&提货单下的订单待签收 = 0 条件满足才能提货单的订单走下一步链路
            if (unSignQuantity == 0L) {
                SalePickGoodsInfoRequestDTO pickGoodsInfoRequestDTO = new SalePickGoodsInfoRequestDTO();
                pickGoodsInfoRequestDTO.setStatus(StatusCodeEnum.TransactionComplete.getCode());//交易完成状态
                salePickGoodsInfoClient.updateById(salePickGoodsInfoResponseDTO.getId(), pickGoodsInfoRequestDTO);//更改提货单状态
                //获取提货单下的订单信息
                SalePickGoodsOrderRequestQuery query = new SalePickGoodsOrderRequestQuery();
                query.setPickGoodsInfoId(salePickGoodsInfoResponseDTO.getId());
                List<SalePickGoodsOrderResponseDTO> list = salePickGoodsOrderClient.listSalePickGoodsOrders(query);
                //获取订单的信息
                List<String> saleOrderCodeList = list.stream().map(SalePickGoodsOrderResponseDTO::getSaleOrderCode).distinct().collect(Collectors.toList());
                List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOList = GeneralConvertUtils.convert2List(saleOrderInfoClient.batchSearchSaleOrderInfo(saleOrderCodeList).getPayload(),
                        SaleOrderInfoResponseDTO.class);
                List<SaleOrderInfoResponseDTO> saleOrderList = saleOrderInfoResponseDTOList.stream().collect(Collectors.groupingBy(e -> ObjectUtils.equals(e.getQuantity() - e.getTotalSignQuantity(), 0L))).get(true);
                if (CollectionUtils.isNotEmpty(saleOrderList)) {
                    //调用业务链路下一步接口获取状态 更新订单主状态
                    log.info("走业务链路的订单数据 : {}", JSON.toJSONString(saleOrderInfoResponseDTOList));
                    List<Long> saleOrderIdList = saleOrderInfoResponseDTOList.stream().map(SaleOrderInfoResponseDTO::getId).collect(Collectors.toList());
                    ToolLinkBatchNextBusinessRequestDTO toolLink = new ToolLinkBatchNextBusinessRequestDTO();
                    toolLink.setTenantId(salePickGoodsInfoResponseDTO.getTenantId());//tenantId
                    toolLink.setAppId(salePickGoodsInfoResponseDTO.getAppId());//appId
                    toolLink.setListIds(saleOrderIdList);//订单ID
                    toolLink.setListType(ListTypeEnum.SalesOrder.name());//类型
                    toolLink.setBusinessCode(LinkBusinessCodeEnum.BuyerReceive.name());//买家签收
                    Payload<ToolLinkNextBusinessResponseDTO> dtoPayload = toolLinkClient.batchNextLinkBusiness(toolLink);
                    ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO = GeneralConvertUtils.conv(dtoPayload.getPayload(),
                            ToolLinkNextBusinessResponseDTO.class);
                    log.info("获取环节状态:{}", JsonUtil.bean2JsonString(dtoPayload));
                    Integer status = toolLinkNextBusinessResponseDTO.getStatus();
                    //批量修改订单的状态
                    List<SaleOrderInfoRequestDTO> dtoList = saleOrderInfoResponseDTOList.stream().map(li -> {
                        SaleOrderInfoRequestDTO saleOrderInfoRequestDTO = new SaleOrderInfoRequestDTO();
                        saleOrderInfoRequestDTO.setId(li.getId());
                        saleOrderInfoRequestDTO.setStatus(status);//业务链路返回的状态
                        return saleOrderInfoRequestDTO;
                    }).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(dtoList)) {
                        saleOrderInfoClient.updateBatchById(dtoList);
                        //记录提货单签收操作
                        OrderOperationRecordRequestDTO orderOperationRecordRequestDTO = new OrderOperationRecordRequestDTO();
                        orderOperationRecordRequestDTO.setAppId(appRuntimeEnv.getAppId());
                        orderOperationRecordRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
                        orderOperationRecordRequestDTO.setCreatedTime(new Date());
                        orderOperationRecordRequestDTO.setOrderId(query.getPickGoodsInfoId());
                        orderOperationRecordRequestDTO.setOperation("提货单签收成功");
                        orderOperationRecordRequestDTO.setOperationType(2);
                        orderOperationRecordRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
                    }
                }
            }
        }
        return Boolean.TRUE;
    }


    /**
     * 根据编号获取提货订单主表信息
     *
     * @param pickGoodsCode
     * @return
     * @throws Exception
     */
    private com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO getPickByCode(String pickGoodsCode) throws Exception {
        Payload<com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoClient.getSalePickGoodsInfoByCode(pickGoodsCode);
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO pickInfo = null;
        if (payload != null) {
            pickInfo = GeneralConvertUtils.conv(payload.getPayload(), com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO.class);
        }
        return pickInfo;
    }


    /**
     * 根据提货订单的id获取提货订单sku
     *
     * @param id 提货订单表id
     */
    public List<SalePickGoodsOrderSkuResponseDTO> getSkuByInfoId(Long id) {
        if (id == null) {
            return null;
        }
        SalePickGoodsOrderSkuRequestQuery query = new SalePickGoodsOrderSkuRequestQuery();
        query.setPickGoodsInfoId(id);
        List<SalePickGoodsOrderSkuResponseDTO> list = salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(query);
        return list;
    }


    /**
     * @param salePickGoodsId
     * @Description: 根据提货单ID查询发货的sku详情.
     * @Param: [salePickGoodsId]
     * @return: java.util.List<com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/21
     */
    @Override
    public List<SalePickGoodsDetailInfoResponseDTO> searchSkuDetailList(Long salePickGoodsId) throws Exception {
        if (Objects.isNull(salePickGoodsId)) {
            throw new ApplicationException("id为空.");
        }
        Payload<List<SalePickGoodsDetailInfoMiddleResponseDTO>> listPayload = salePickGoodsInfoClient.searchSkuDetailList(salePickGoodsId);
        List<SalePickGoodsDetailInfoMiddleResponseDTO> list = GeneralConvertUtils.convert2List(listPayload.getPayload(), SalePickGoodsDetailInfoMiddleResponseDTO.class);
        //判断发货页面是否能编辑数量
        jugeEditAble(list);
        return GeneralConvertUtils.convert2List(list, SalePickGoodsDetailInfoResponseDTO.class);
    }

    //判断发货页面是否能编辑数量
    private void jugeEditAble(List<SalePickGoodsDetailInfoMiddleResponseDTO> list) {
        List<DepotFindPagePostResponseDTODepot> depotList = findDepotPageByType(null);
        if (depotList != null && list != null) {
            depotList.forEach(item -> System.out.println(item.getId()));
            for (SalePickGoodsDetailInfoMiddleResponseDTO order : list) {
                Map<Long, List<DepotFindPagePostResponseDTODepot>> depotMap = depotList.stream().collect(Collectors.groupingBy(item -> item.getId()));
                List<DepotFindPagePostResponseDTODepot> depotArr = depotMap.get(order.getDeliveryWareHouseId());
                if (depotArr != null && depotArr.size() != 0 && depotArr.get(0) != null) {
                    DepotFindPagePostResponseDTODepot depot = depotArr.get(0);
                    Integer isJoin = depot.getIsJoin();
                    if (Integer.valueOf(1).equals(isJoin)) {
                        order.setEditable(false);
                    } else {
                        order.setEditable(true);
                    }
                }
            }
        }
    }

    /**
     * 根据pickCode查提货单
     *
     * @param query
     * @return
     */
    @Override
    public SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query) throws Exception {
        return GeneralConvertUtils.conv(salePickGoodsInfoClient.selectByPickCode(query), SalePickGoodsInfoResponseDTO.class);
    }



    //根据id获取销售订单明细
    public List<SaleOrderItemMiddleResponseDTO> getSaleItem(List<Long> saleOrderItemIds) throws Exception {
        if (saleOrderItemIds != null && saleOrderItemIds.size() > 0) {
            SaleOrderItemMiddleRequestQuery query = new SaleOrderItemMiddleRequestQuery();
            query.setIdList(saleOrderItemIds);
            List<SaleOrderItemMiddleResponseDTO> orderItems = saleOrderItemClient.listSaleOrderItems(query);
            return orderItems;
        }
        return null;
    }




    //根据提货订单id获取关联的提货计划订单明细
    public List<SalePickGoodsOrderResponseDTO> getPickOrder(List<Long> pickIds) throws Exception {
        if (pickIds != null && pickIds.size() > 0) {
            SalePickGoodsOrderRequestQuery query = new SalePickGoodsOrderRequestQuery();
            query.setPickGoodsInfoIdList(pickIds);
            List<SalePickGoodsOrderResponseDTO> pickOrders = salePickGoodsOrderClient.listSalePickGoodsOrders(query);
            return pickOrders;
        }
        return null;
    }


    //获取卖家信息
    private HashMap<String, Object> getSellerDate(ArrayList<SaleOrderResponseDTO> saleOrderArr, Long ascriptionOrgId, SalePickGoodsPlanInfoRequestDTO record) {
        Long sellerId = record.getSellerId();
        String sellerName = record.getSellerName();

        Set<Long> saleOrderSellerIdSet = saleOrderArr.stream().map(order -> order.getSellerId()).collect(Collectors.toSet());
        if (saleOrderSellerIdSet.size() != 1) {
            log.error("本提货订单对应的销售订单所对应的sellerId不唯一，这可能是个错误");
        }
        List<Long> saleOrderIdArr = saleOrderArr.stream().map(order -> order.getSellerId()).distinct().filter(item-> item != null).collect(Collectors.toList());
        List<String> saleOrderNameArr = saleOrderArr.stream().map(order -> order.getSellerName()).distinct().filter(item-> item != null).collect(Collectors.toList());

        if (sellerId == null && saleOrderIdArr != null && saleOrderIdArr.size() == 1) {
            sellerId = saleOrderIdArr.get(0);
        }
        if (sellerName == null && saleOrderNameArr != null && saleOrderNameArr.size() == 1) {
            sellerName = saleOrderNameArr.get(0);
        }
        Boolean operaterIsBuyer = record.getOperaterIsBuyer();
        if(!operaterIsBuyer){
            if (sellerId == null) {
                sellerId = appRuntimeEnv.getTopOrganization().getId();
            }
            if(sellerName == null){
                sellerName = appRuntimeEnv.getTopOrganization().getName();
            }
        }
        /*
        if (sellerId == null && ascriptionOrgId != null) {
            GroupResultVO oneLevelOrgOfGeli = getGroup(ascriptionOrgId);
            if (oneLevelOrgOfGeli != null && oneLevelOrgOfGeli.getParentId() != null) {
                GroupResultVO parentOrg = getGroup(oneLevelOrgOfGeli.getParentId());
                if (parentOrg != null && parentOrg.getId() != null) {
                    sellerId = parentOrg.getId();
                    sellerName = parentOrg.getName();
                } else {
                    log.info("无法获取卖家一级组织的顶级组织详情");
                }
            } else {
                log.info("无法获取卖家一级组织的顶级组织详情！");
            }
        }
        */
        if (sellerId == null && saleOrderIdArr != null && saleOrderIdArr.size() != 0) {
            sellerId = saleOrderIdArr.get(0);
        }
        if (sellerName == null && saleOrderNameArr != null && saleOrderNameArr.size() != 0) {
            sellerName = saleOrderNameArr.get(0);
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sellerId", sellerId);
        hashMap.put("sellerName", sellerName);
        return hashMap;
    }


    //根据其他信息获取客户信息
    private HashMap<String, Object> getCustomerDate(ArrayList<SaleOrderResponseDTO> saleOrderArr, Long ascriptionOrgId, BusinessPartnerResponseDTO businessPartner, String tenantId, Long appId, SalePickGoodsPlanInfoRequestDTO record) {
        Long customerId = null;
        String customerName = null;
        if (!record.getOperaterIsBuyer()) {//当前卖家操作这个提货单，肯定是代提货订单，前端会封装客户id和客户名称
            customerId = record.getCustomerId();
            customerName = record.getCustomerName();
        }

        Set<Long> customerIdsSet = saleOrderArr.stream().map(order -> order.getCustomerId()).distinct().filter(item-> item != null).collect(Collectors.toSet());
        if (customerIdsSet.size() != 1) {
            log.error("本提货订单对应的销售订单所对应的customerId不唯一，这可能是个错误");
        }

        List<Long> customerIdArr = saleOrderArr.stream().map(order -> order.getCustomerId()).distinct().filter(item-> item != null).collect(Collectors.toList());
        List<String> customerNameArr = saleOrderArr.stream().map(order -> order.getCustomerName()).distinct().filter(item-> item != null).collect(Collectors.toList());
        if (customerIdArr != null && customerIdArr.size() == 1) {
            customerId = customerIdArr.get(0);
        }
        if (customerNameArr != null && customerNameArr.size() == 1) {
            customerName = customerNameArr.get(0);
        }
        if (record.getOperaterIsBuyer()) {
            if (customerId == null && tenantId != null && appId != null && businessPartner != null && ascriptionOrgId != null) {
                //获取对应客户
                if (businessPartner != null && businessPartner.getId() != null) {
                    List<MerchantResponseDTO> merchants = getMerchat(tenantId, appId, businessPartner.getId(), ascriptionOrgId + "");
                    if(merchants == null || merchants.size() == 0){
                        merchants = getMerchat(tenantId, appId, businessPartner.getId(), businessPartner.getIsolationId() + "");
                    }
                    if (merchants != null && merchants.size() != 0) {
                        MerchantResponseDTO merchantResponseDTO = merchants.get(0);
                        customerId = merchantResponseDTO.getId();
                        customerName = merchantResponseDTO.getName();
                    }
                }

            }
        }
        if (customerId == null && customerIdArr != null && customerIdArr.size() != 0) {
            customerId = customerIdArr.get(0);
        }
        if (customerName == null && customerNameArr != null && customerNameArr.size() != 0) {
            customerName = customerNameArr.get(0);
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("customerId", customerId);
        hashMap.put("customerName", customerName);
        return hashMap;
    }


    //根据其他数据获取买家信息
    private HashMap<String, Object> getBuyerDate(ArrayList<SaleOrderResponseDTO> saleOrderArr, Boolean operaterIsBuyer, GroupResultVO topOrganization) {
        Long buyerId = null;
        String buyerName = null;

        Set<Long> buyerIdsSet = saleOrderArr.stream().map(order -> order.getBuyerId()).distinct().filter(item-> item != null).collect(Collectors.toSet());
        if (buyerIdsSet.size() != 1) {
            log.error("本提货订单对应的销售订单所对应的buyerId不唯一，这可能是个错误");
        }

        List<Long> buyerIdArr = saleOrderArr.stream().map(order -> order.getBuyerId()).distinct().filter(item-> item != null).collect(Collectors.toList());
        List<String> buyerNameArr = saleOrderArr.stream().map(order -> order.getBuyerName()).distinct().filter(item-> item != null).collect(Collectors.toList());
        if (buyerIdArr != null && buyerIdArr.size() == 1) {
            buyerId = buyerIdArr.get(0);
        }
        if (buyerNameArr != null && buyerNameArr.size() == 1) {
            buyerName = buyerNameArr.get(0);
        }

        if (buyerId == null && operaterIsBuyer && topOrganization != null && topOrganization.getId() != null) {
            buyerId = topOrganization.getId();
            buyerName = topOrganization.getName();
        }

        if (buyerId == null && buyerIdArr != null && buyerIdArr.size() != 0) {
            buyerId = buyerIdArr.get(0);
        }
        if (buyerName == null && buyerNameArr != null && buyerNameArr.size() != 0) {
            buyerName = buyerNameArr.get(0);
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("buyerId", buyerId);
        hashMap.put("buyerName", buyerName);
        return hashMap;

    }


    /**
     * 用于临时判断当前登陆账号是否能获取所有产品线
     * @return
     * @throws Exception
     */
    @Override
    public Boolean judgeGetAll() throws Exception {
        String tenantId = appRuntimeEnv.getTenantId();
        Long userId = appRuntimeEnv.getUserId();
        BusinessPartnerResponseDTO businessPartner = getBusinessPartner(tenantId, appId, null, userId);
        //不知道当前用户是谁，暂时返回true
        if(businessPartner == null || businessPartner.getCompanyTypeId() == null){
            return true;
        }
        Long companyTypeId = businessPartner.getCompanyTypeId();
        if(Long.valueOf(1).equals(companyTypeId)){
            return true;
        }
        if(Long.valueOf(2).equals(companyTypeId)){
            return true;
        }
        return true;//暂时放开全部产品线
    }


    /**
     * 根据产品线、供应商id、客户等等数据展示仓库列表
     * 目前有3中情况用到这个接口：1.提货申请选择仓库  2.审核选择仓库    3.待提货申请选择仓库
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public PickGoodStoreHouseResponseDTO getStorehouseList(PickGoodStoreHouseReq req) throws Exception {
        //数据校验
        if(req.getConditionType() == null || req.getConditionType().equals("")){
            throw new ApplicationException("conditionType字段不能为空");//在线订购申请：pickApply,代提货申请replaceApply,发货sendGoods
        }
        if ("pickApply".equals(req.getConditionType())) {
            if(req.getSellerId() == null){
                throw new ApplicationException("请先选择上游供应商，字段sellerId");
            }
        }
        if("replaceApply".equals(req.getConditionType())){
            if(req.getBuyerId() == null){
                throw new ApplicationException("代提货申请，请先选择客户，字段buyerId");
            }
            if(appRuntimeEnv.getUserOrganization().getId() == null){
                throw new ApplicationException("代提货申请，获取当前供应商账号组织失败");
            }
            if(appRuntimeEnv.getTopOrganization().getId() == null){
                throw new ApplicationException("代提货申请，获取当前供应商账号组织失败");
            }
        }
        com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO pickInfo = null;
        if("sendGoods".equals(req.getConditionType())){
            if(req.getPickGoodsCode() == null){
                throw new ApplicationException("审核时选择发货仓库情况，获取仓库列表接口，获取字段pickGoodsCode失败");
            }
            pickInfo = getPickByCode(req.getPickGoodsCode());
            if(pickInfo == null){
                throw new ApplicationException("获取仓库列表接口，根据提货单单号获取提货单信息为空：req.getPickGoodsCode()："+req.getPickGoodsCode());
            }
            if(pickInfo.getSellerId() == null){
                throw new ApplicationException("审核时选择发货仓库情况，获取仓库列表接口，获取字段sellerId失败");
            }
            if(pickInfo.getBuyerId() == null){
                throw new ApplicationException("审核时选择发货仓库情况，获取仓库列表接口，获取字段buyerId失败");
            }
            if(pickInfo.getPickGoodsWayZt() == null){
                throw new ApplicationException("审核时选择发货仓库情况，获取仓库列表接口，获取字段pickGoodsWayZt失败");
            }
        }
        log.info("展示仓库列表获取到的req:"+JSON.toJSONString(req));
        //获取供应商的顶级组织
        Long topOrgId = null;
        if ("pickApply".equals(req.getConditionType())) {
            topOrgId = req.getSellerId();
        } else if ("replaceApply".equals(req.getConditionType())) {
            topOrgId = appRuntimeEnv.getTopOrganization().getId();
        } else if ("sendGoods".equals(req.getConditionType())) {
            topOrgId = pickInfo.getSellerId();
        } else {
            throw new ApplicationException("conditionType字段参数有误" + req.getConditionType());
        }
        log.info("获取供应商的顶级组织为："+topOrgId);
        //判断供应商顶级组织类型  true表示是格力
        boolean isGeLi = false;
        String tenantId = appRuntimeEnv.getTenantId();
        BusinessPartnerResponseDTO businessPartner = getBusinessPartner(tenantId, appId, topOrgId, null);
        log.info("获取的业务伙伴为："+JSON.toJSONString(businessPartner));
        Long companyTypeId = null;
        if(businessPartner != null){
            companyTypeId = businessPartner.getCompanyTypeId();
        }
        if (companyTypeId == null) {
            GroupResultVO group = getGroup(topOrgId);
            log.info("根据topOrgId获取的组织为"+JSON.toJSONString(group)+";参数topOrgId"+topOrgId);
            if(group != null && "gree-deparent".equals(group.getCode())){
                isGeLi = true;
            }
        } else {
            if (Long.valueOf(1).equals(companyTypeId)) {
                isGeLi = true;
            }
        }
        log.info("计算的isGeLi结果:"+isGeLi);
        //获取上游授权组织的id
        Long upOrgId = null;
        if("pickApply".equals(req.getConditionType()) || "replaceApply".equals(req.getConditionType())){
            if(isGeLi){//根据产品流程图，只有当顶级组织为格力时，才需要考虑产品线的影响
                if(req.getProductId() != null){
                    //根据产品线获取授权组织
                    Long ascriptionOrgId = getOrg(appRuntimeEnv.getTenantId(), this.appId, req.getProductId() + "");
                    log.info("获取的ascriptionOrgId："+ascriptionOrgId);
                    if(ascriptionOrgId != null){
                        upOrgId = ascriptionOrgId;
                    }
                }
            }
            if(upOrgId == null){
                upOrgId = topOrgId;
            }
        }
        if("sendGoods".equals(req.getConditionType())){
            upOrgId = pickInfo.getSellerId();
        }
        log.info("计算出来的upOrgId："+upOrgId);
        //获取下游被授权方的顶级组织id
        Long downOrgId = null;
        if ("pickApply".equals(req.getConditionType())) {
            downOrgId = appRuntimeEnv.getUserOrganization().getId();
        } else if ("replaceApply".equals(req.getConditionType())) {
            downOrgId = req.getBuyerId();
        } else if ("sendGoods".equals(req.getConditionType())) {
            downOrgId = pickInfo.getBuyerId();
        }
        log.info("方法getStorehouseList的upOrgId:"+upOrgId);
        log.info("方法getStorehouseList的downOrgId:"+downOrgId);
        //获取仓库列表
        List<DepotFindPagePostResponseDTODepot> depotList = null;
        if("sendGoods".equals(req.getConditionType())){
            if("PICK_MYSELF".equals(pickInfo.getPickGoodsWayZt()) && !isGeLi){
                depotList = getHouseListByAuthority(upOrgId, downOrgId);
            }else{
                depotList = null;
            }
        }
        else {
            depotList = getHouseListByAuthority(upOrgId, downOrgId);
        }
        //返回数据
        PickGoodStoreHouseResponseDTO response = new PickGoodStoreHouseResponseDTO();
        List<DepotPostResponseDTODepot> depotList2 = ObjectCloneUtils.convertList(depotList, DepotPostResponseDTODepot.class);
        if(depotList2 == null){
            depotList2 = new ArrayList<>();
        }
        response.setDepotList(depotList2);
        log.info("方法getStorehouseList返回的depotList2.size:"+(depotList2 == null ? null : depotList2.size()));
        return response;
    }

    /**
     * 用于查看appRuntimeEnv信息
     * @return
     */
    @Override
    public AppRunEnvForPick getAppRuntimeEnvByToken() {
        GroupResultVO topOrganization = appRuntimeEnv.getTopOrganization();
        GroupResultVO userOrganization = appRuntimeEnv.getUserOrganization();
        String tenantId = appRuntimeEnv.getTenantId();
        Long userId = appRuntimeEnv.getUserId();
        String username = appRuntimeEnv.getUsername();
        Long appId = appRuntimeEnv.getAppId();

        AppRunEnvForPick appRunEnvForPick = new AppRunEnvForPick();
        appRunEnvForPick.setTopOrganization(topOrganization);
        appRunEnvForPick.setUserOrganization(userOrganization);
        appRunEnvForPick.setTenantId(tenantId);
        appRunEnvForPick.setUserId(userId);
        appRunEnvForPick.setUsername(username);
        appRunEnvForPick.setAppId(appId);
        return appRunEnvForPick;
    }


    //获取授权的仓库列表
    private List<DepotFindPagePostResponseDTODepot> getHouseListByAuthority(Long upOrgId, Long downOrgId) {
        //获取授权仓库id列表
        List<PickGoodStoreHouseItem> items = null;
        List<Long> warehouseIds = null;
        try {
            CustomerPickUpWarehouseDomainQuery query = new CustomerPickUpWarehouseDomainQuery();
            query.setAuthOrgId(upOrgId);
            query.setMerchantOrgId(downOrgId);
            query.setTenantId(appRuntimeEnv.getTenantId());
            log.info("即将调用方法customerPickUpWarehouseClient.listAll(query)。参数upOrgId:{},downOrgId:{},TenantId:{}",upOrgId,downOrgId,appRuntimeEnv.getTenantId());
            Payload<List<CustomerPickUpWarehouseDomainDTO>> payload = customerPickUpWarehouseClient.listAll(query);
            log.info("获取的payload.code:"+payload.getCode());
            List<CustomerPickUpWarehouseDomainDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),CustomerPickUpWarehouseDomainDTO.class);
            log.info("获取的customerPickUpWarehouseClient.listAll结果："+JSON.toJSONString(list));
            warehouseIds = list.stream().map(dto -> dto.getWarehouseId()).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("调用接口获取仓库列表出现异常，upOrgId:" + upOrgId + ";downOrgId:" + downOrgId, e);
        }
        //获取授权仓库id对应的仓库信息
        List<DepotFindPagePostResponseDTODepot> matchDepots = null;
        if(warehouseIds != null && warehouseIds.size() != 0){
            final List<Long> finalWarehouseIds = warehouseIds.stream().map(num -> num).collect(Collectors.toList());
            log.info("授权过滤用的finalWarehouseIds："+finalWarehouseIds);
            //获取中台的所有仓库数据
            List<DepotFindPagePostResponseDTODepot> depotList = null;
            try {
                depotList = findDepotPageByType(null);
            } catch (Exception e) {
                log.error("获取仓库列表失败",e);
                throw e;
            }
            if(depotList != null){
                matchDepots = depotList.stream().filter(depot -> depot != null).filter(depot -> finalWarehouseIds.contains(depot.getId())).collect(Collectors.toList());
            }
            log.info("得到授权的仓库列表结果：matchDepots："+JSON.toJSONString(matchDepots));
        }
        return matchDepots;
    }


    //中台获取仓库列表的接口
    private List<DepotFindPagePostResponseDTODepot> findDepotPageByType (DepotFindPagePostByTypeRequestDTO request){
        List<DepotFindPagePostResponseDTODepot> depotList = null;
        if(request == null){
            request = new DepotFindPagePostByTypeRequestDTO();
            request.setAppId(appId);
            request.setTenantId(appRuntimeEnv.getTenantId());
            request.setPage(-1);//不分页
        }
        log.info("即将调用方法depotSdkApi.findDepotPageByType,request:"+JSON.toJSONString(request));
        PayloadDepotFindPagePostResponseDTO depotPage = depotSdkApi.findDepotPageByType(request);
        log.info("调用方法depotSdkApi.findDepotPageByType的结果,depotPage:"+JSON.toJSONString(depotPage));
        if (SUCCESS.equals(depotPage.getCode())) {
            DepotFindPagePostResponseDTO payload = depotPage.getPayload();
            depotList = payload.getDepotList().getContent();
            if(depotList != null){
                depotList = depotList.stream().filter(depot -> Integer.valueOf(1).equals(depot.getIsEnabled())).collect(Collectors.toList());
            }
        }
        log.info("过滤掉不能用的仓库：depotList："+JSON.toJSONString(depotList));
        return depotList;
    }






}
