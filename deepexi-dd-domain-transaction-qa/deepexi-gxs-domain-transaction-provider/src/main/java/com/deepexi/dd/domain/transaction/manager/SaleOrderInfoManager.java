package com.deepexi.dd.domain.transaction.manager;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.remote.order.FinancePaymentRecordClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderSplitRecordClient;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderSplitRecordResponseDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : SaleOrderInfoManager
 * @Description : 订单管理器
 * @Author : yuanzaishun
 * @Date: 2020-08-15 10:07
 */
@Component
public class SaleOrderInfoManager {
    Logger logger= LoggerFactory.getLogger(SaleOrderInfoManager.class);
    @Autowired
    FinancePaymentRecordClient financePaymentRecordClient;

    @Autowired
    SaleOrderSplitRecordClient saleOrderSplitRecordClient;

    @Autowired
    SaleOrderInfoClient saleOrderInfoClient;

    private final String SUCCESS="0";


    /**
     * 验证订单交易记录是否已经全部确认并且已确认的金额大于等于应付金额
     * @param orderId
     * @return
     * @throws Exception
     */
    public boolean validPayRecordSure(Long orderId) throws Exception{
        SaleOrderResponseDTO order=   getSaleOrderById(orderId);
        List<Long> orderIds=new ArrayList<>();
        orderIds.add(orderId);

        //查询当前订单支付记录
        FinancePaymentRecordsRequestQuery query=new FinancePaymentRecordsRequestQuery();
        query.setAppId(order.getAppId());
        query.setTenantId(order.getTenantId());
        query.setOrderCode(order.getCode());
        query.setOrderId(order.getId());
        Payload<List<FinancePaymentRecordsResponseDTO>> payload= financePaymentRecordClient.listFinancePaymentRecords(query);
        List<FinancePaymentRecordsResponseDTO> list=   GeneralConvertUtils.convert2List(payload.getPayload(),FinancePaymentRecordsResponseDTO.class);
        if(SUCCESS.equals(payload.getCode()) && CollectionUtil.isNotEmpty(list)){

                int count= list.size();
                int sureCount=0;
                BigDecimal totalPayAmount=BigDecimal.valueOf(0);
                for(FinancePaymentRecordsResponseDTO dto:list){
                    if(StatusCodeEnum.Confirmed.getCode().equals(dto.getStatus())){
                        //确认数量
                        sureCount++;
                        //确认金额
                        totalPayAmount= totalPayAmount.add(dto.getAmount());
                    }
                }
                //应收金额-已付金额<=0时才进行扭转
                return order.getAccrueAmount().subtract(totalPayAmount).compareTo(BigDecimal.valueOf(0))<=0 ;
        }else{
            //如果子单没有流水记录，则查询父单
            Payload<SaleOrderSplitRecordResponseDTO> parentOrderPayload=   saleOrderSplitRecordClient.selectByCode(order.getParentSaleOrderCode());
            if(SUCCESS.equals(parentOrderPayload.getCode())){
                SaleOrderSplitRecordResponseDTO splitRecordResponseDTO= GeneralConvertUtils.conv(parentOrderPayload.getPayload(),SaleOrderSplitRecordResponseDTO.class);
                FinancePaymentRecordsRequestQuery parentOrder=new FinancePaymentRecordsRequestQuery();
                parentOrder.setAppId(splitRecordResponseDTO.getAppId());
                parentOrder.setTenantId(splitRecordResponseDTO.getTenantId());
                parentOrder.setOrderCode(splitRecordResponseDTO.getCode());
                parentOrder.setOrderId(splitRecordResponseDTO.getId());
                Payload<List<FinancePaymentRecordsResponseDTO>> payloadFinance= financePaymentRecordClient.listFinancePaymentRecords(parentOrder);
                if(SUCCESS.equals(payloadFinance.getCode())){
                  List<FinancePaymentRecordsResponseDTO> parentFinanceList=  GeneralConvertUtils.convert2List(payloadFinance.getPayload(),FinancePaymentRecordsResponseDTO.class);
                    BigDecimal totalPayAmount=BigDecimal.valueOf(0);
                    for(FinancePaymentRecordsResponseDTO dto:parentFinanceList){
                        if(StatusCodeEnum.Confirmed.getCode().equals(dto.getStatus())){
                            //确认金额
                            totalPayAmount= totalPayAmount.add(dto.getAmount());
                        }
                    }
                    //应收金额-已付金额<=0时才进行扭转
                    return order.getAccrueAmount().subtract(totalPayAmount).compareTo(BigDecimal.valueOf(0))<=0 ;
                }

            }
        }
        return false;
    }

    public SaleOrderResponseDTO getSaleOrderById(Long id){
        Payload<SaleOrderResponseDTO>  payload=     saleOrderInfoClient.selectSaleOrder(id);
        if(SUCCESS.equals(payload.getCode())){
            try {
                SaleOrderResponseDTO order = GeneralConvertUtils.conv(payload.getPayload(), SaleOrderResponseDTO.class);
                return order;
            }catch(Exception e){
                logger.error("转换订单异常:",e);
                return null;
            }
        }
        return null;
    }
}
