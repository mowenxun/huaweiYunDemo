package com.deepexi.dd.domain.transaction.api.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/11/17:51
 * @Description:
 */

import com.deepexi.dd.domain.transaction.api.SaleOrderOperationRecordDomainApi;
import com.deepexi.dd.domain.transaction.service.SaleOrderOperationRecordService;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SaleOrderOperationRecordDomainApiImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/9/11
 * @Version V1.0
 **/
@RestController
public class SaleOrderOperationRecordDomainApiImpl implements SaleOrderOperationRecordDomainApi {

    @Autowired
    private SaleOrderOperationRecordService saleOrderOperationRecordService;

    @Override
    public Payload<OrderOperationRecordResponseDTO> getOrderRejectOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        return new Payload(saleOrderOperationRecordService.getOrderRejectOperationRecord(orderOperationRecordRequestDTO));
    }
}
