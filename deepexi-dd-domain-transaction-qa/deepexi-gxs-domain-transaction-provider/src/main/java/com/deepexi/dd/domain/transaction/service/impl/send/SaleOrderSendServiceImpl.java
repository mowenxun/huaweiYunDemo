package com.deepexi.dd.domain.transaction.service.impl.send;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/24/16:09
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.domain.transaction.mq.gl.producter.GlMqProducter;
import com.deepexi.dd.domain.transaction.mq.gl.producter.OrderItemOMS;
import com.deepexi.dd.domain.transaction.mq.gl.producter.OrderMqToMsg;
import com.deepexi.dd.domain.transaction.mq.gl.producter.OrderOMS;
import com.deepexi.dd.domain.transaction.remote.order.MerchantClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.MerchantResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单发送到oms
 *
 * @ClassName SaleOrderSendServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/24
 * @Version V1.0
 **/
@Slf4j
@Service
public class SaleOrderSendServiceImpl {

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Autowired
    GlMqProducter glMqProducter;

    String GL_OMS_ORDER_TOPIC = "topic_qudao_order_to_oms";

    @Value("${gl.oms.is.send}")
    Boolean glOmsIsSend;

    @Autowired
    MerchantClient merchantClient;


    public void sendOrderToGl(Long id) throws Exception {
        if (glOmsIsSend) {
            if (id == null || id == 0) {
                throw new ApplicationException(ResultEnum.ORDER_ID_IS_NULL);
            }
            SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = saleOrderInfoService.selectById(id);
            if (saleOrderInfoResponseDTO == null) {
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            }
            Long customerId = saleOrderInfoResponseDTO.getCustomerId();
            if (customerId == null) {
                throw new ApplicationException(ResultEnum.ORDER_CUSTOMER_ID_IS_NULL);
            }
            Payload<MerchantResponseDTO> payload = merchantClient.detail(customerId);
            MerchantResponseDTO merchantResponseDTO = GeneralConvertUtils.conv(payload.getPayload(),
                    MerchantResponseDTO.class);
            if (merchantResponseDTO == null) {
                throw new ApplicationException(ResultEnum.ORDER_CUSTOMER_IS_NULL);
            }
            //收货地址
            OrderConsigneeInfoResponseDTO orderConsigneeInfoResponseDTO =
                    saleOrderInfoResponseDTO.getOrderConsigneeInfo();
            OrderMqToMsg orderMqToMsg = new OrderMqToMsg();
            OrderOMS orderOMS = new OrderOMS();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderOMS.setBuyerName(saleOrderInfoResponseDTO.getBuyerName());
            if (orderConsigneeInfoResponseDTO.getCityCode() != null&&!"".equals(orderConsigneeInfoResponseDTO.getCityCode())) {
                orderOMS.setCityId(Integer.valueOf(orderConsigneeInfoResponseDTO.getCityCode()));
            }
            orderOMS.setCityName(orderConsigneeInfoResponseDTO.getCityName());
            orderOMS.setConsignee(orderConsigneeInfoResponseDTO.getConsignee());
            orderOMS.setConsigneePhone(orderConsigneeInfoResponseDTO.getMobile());
            orderOMS.setDetailAddress(orderConsigneeInfoResponseDTO.getDetailedAddress());
            orderOMS.setMobile(orderConsigneeInfoResponseDTO.getMobile());
            orderOMS.setName(orderConsigneeInfoResponseDTO.getConsignee());
            if (orderConsigneeInfoResponseDTO.getProvinceCode() != null&&!"".equals(orderConsigneeInfoResponseDTO.getProvinceCode())) {
                orderOMS.setProvinceId(Integer.valueOf(orderConsigneeInfoResponseDTO.getProvinceCode()));
            }
            orderOMS.setProvinceName(orderConsigneeInfoResponseDTO.getProvinceName());
            orderOMS.setBuyerMobile(orderConsigneeInfoResponseDTO.getMobile());
            //orderOMS.setConsigneeId(orderConsigneeInfoResponseDTO.getca);
            if (orderConsigneeInfoResponseDTO.getCityCode() != null&&!"".equals(orderConsigneeInfoResponseDTO.getCityCode())) {
                orderOMS.setCountyId(Integer.valueOf(orderConsigneeInfoResponseDTO.getCityCode()));
            }
            orderOMS.setCountyName(orderConsigneeInfoResponseDTO.getCityName());
            orderOMS.setCreateTime(simpleDateFormat.format(new Date()));
            orderOMS.setExternalOrderCode(saleOrderInfoResponseDTO.getCode());
            orderOMS.setTownName(orderConsigneeInfoResponseDTO.getStreetName());
            if(orderConsigneeInfoResponseDTO.getStreetCode()!=null&&!"".equals(orderConsigneeInfoResponseDTO.getStreetCode())){
                orderOMS.setTownId(Integer.valueOf(orderConsigneeInfoResponseDTO.getStreetCode()));
            }
            // orderOMS.setLicensePlateNumber();
            //新增4个字段
            //orderOMS.setOrderTypeString(saleOrderInfoResponseDTO.getTicketType().toString());
            String orderTypeString=null;
            if(TicketTypeEnum.DIRECT.getValue().equals(saleOrderInfoResponseDTO.getTicketType())){
                orderTypeString="网批订单";
            }else if(TicketTypeEnum.ORDINARY.getValue().equals(saleOrderInfoResponseDTO.getTicketType())){
                orderTypeString="标准订单";
            }else if(TicketTypeEnum.UNORDINARY.getValue().equals(saleOrderInfoResponseDTO.getTicketType())){
                orderTypeString="非标准订单";
            }else if(TicketTypeEnum.PLAN.getValue().equals(saleOrderInfoResponseDTO.getTicketType())){
                orderTypeString="计划订单";
            }
            orderOMS.setOrderTypeString(orderTypeString);
            orderOMS.setErpPartner(merchantResponseDTO.getCode());
            orderOMS.setOrderProvideName(merchantResponseDTO.getNickname());
            orderOMS.setErpReferA(merchantResponseDTO.getNickname());
            orderMqToMsg.setOrder(orderOMS);
            List<OrderItemOMS> orderItemList = new ArrayList<>();
            List<SaleOrderItemMiddleResponseDTO> items = saleOrderInfoResponseDTO.getItems();
            if (CollectionUtils.isNotEmpty(items)) {
                items.forEach(item -> {
                    OrderItemOMS orderItemOMS = new OrderItemOMS();
                    orderItemOMS.setCategoryId(item.getId().intValue());
                    orderItemOMS.setNum(item.getSkuQuantity().intValue());
                    orderItemOMS.setPayPriceTotal(item.getTotalAmount());
                    orderItemOMS.setSellerSkuCode(item.getSkuCode());
                    orderItemOMS.setSkuName(item.getSkuName());
                    orderItemOMS.setSubExternalOrderCode(item.getRowCode());
                    orderItemList.add(orderItemOMS);
                });
            }
            orderMqToMsg.setOrderItemList(orderItemList);
            String sendMsg = JSONObject.toJSONString(orderMqToMsg, SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteMapNullValue);
            log.info("发送到格力OMS订单信息=={}", sendMsg);
            glMqProducter.sendMqMsg(GL_OMS_ORDER_TOPIC, sendMsg);

        }
    }
}
