package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.OrderPayApi;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderCollectionNotifyDto;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderPayNotifyDto;
import com.deepexi.dd.domain.transaction.mq.FinancePaymentCollectionListener;
import com.deepexi.dd.domain.transaction.mq.SaleOrderPayNotifyListener;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderPayNotifyClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderPayNotifySearchQuery;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : OrderPayApiImpl
 * @Description : 订单支付、流水确认接口
 * @Author : yuanzaishun
 * @Date: 2020-08-19 19:56
 */
@RestController
public class OrderPayApiImpl implements OrderPayApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPayApiImpl.class);
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    private static Integer SURE=1;
    private static Integer CANCEL=0;
    private static final String SUCCESS="0";
    @Autowired
    SaleOrderPayNotifyClient saleOrderPayNotifyClient;


    @Override
    public void paySure(@RequestBody  SaleOrderPayNotifyDto saleOrderPayNotifyDto) {
        LOGGER.info("收到交易确认消息 received:{}", JsonUtil.bean2JsonString(saleOrderPayNotifyDto));
        List<SaleOrderPayNotifyResponseDTO> records=getSaleOrderPayNotify(saleOrderPayNotifyDto);
        if(records!=null && records.size()>0) {
            LOGGER.info("交易记录已经存在:{}",saleOrderPayNotifyDto.getPayNo());

        }else {
            //如果确认
            if (SURE.equals(saleOrderPayNotifyDto.getType())) {
                try {
                    saleOrderInfoService.payOrderNextStep(saleOrderPayNotifyDto);
                } catch (Exception e) {
                    LOGGER.error("扭转链路异常:{}", JsonUtil.bean2JsonString(saleOrderPayNotifyDto));
                    LOGGER.error("扭转链路异常:{}", e);
                    throw new ApplicationException("扭转链路异常:", e);
                }
            } else {
                try {
                    saleOrderInfoService.cancelOrderPayAmount(saleOrderPayNotifyDto);
                }catch(Exception e){
                    LOGGER.error("撤销金额失败:{}", JsonUtil.bean2JsonString(saleOrderPayNotifyDto));
                    LOGGER.error("撤销金额失败:{}", e);
                    throw new ApplicationException("撤销金额失败");
                }
            }
        }

    }

    @Override
    public void paymentCollection(@RequestBody  SaleOrderCollectionNotifyDto saleOrderCollectionNotifyDto) {
        LOGGER.info("收到按单收款消息received:{}", JsonUtil.bean2JsonString(saleOrderCollectionNotifyDto));
        saleOrderInfoService.saleOrdercollection(saleOrderCollectionNotifyDto);
    }


    private List<SaleOrderPayNotifyResponseDTO> getSaleOrderPayNotify(SaleOrderPayNotifyDto payNotifyDto)  {
        SaleOrderPayNotifySearchQuery query=new SaleOrderPayNotifySearchQuery();
        query.setOrderId(payNotifyDto.getOrderId());
        query.setPayNo(payNotifyDto.getPayNo());
        try {
            Payload<List<SaleOrderPayNotifyResponseDTO>> payload = saleOrderPayNotifyClient.querySaleOrderNotify(query);
            if (SUCCESS.equals(payload.getCode())) {
                return GeneralConvertUtils.convert2List(payload.getPayload(), SaleOrderPayNotifyResponseDTO.class);
            }
        }catch(Exception e){
            LOGGER.error("查询支付记录异常:{}",JsonUtil.bean2JsonString(payNotifyDto));
            LOGGER.error("查询支付记录异常:",e);
        }
        return null;
    }
}
