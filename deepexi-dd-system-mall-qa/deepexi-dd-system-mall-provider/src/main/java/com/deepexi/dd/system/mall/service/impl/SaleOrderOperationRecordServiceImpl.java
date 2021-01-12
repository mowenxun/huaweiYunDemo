package com.deepexi.dd.system.mall.service.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/11/18:02
 * @Description:
 */

import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.remote.order.SaleOrderOperationRecordDomainApiRemote;
import com.deepexi.dd.system.mall.service.SaleOrderOperationRecordService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SaleOrderOperationRecordServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/9/11
 * @Version V1.0
 **/
@Service
public class SaleOrderOperationRecordServiceImpl implements SaleOrderOperationRecordService {
    @Autowired
    private SaleOrderOperationRecordDomainApiRemote saleOrderOperationRecordDomainApiRemote;

    @Override
    public Payload<OrderOperationRecordResponseDTO> getOrderRejectOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        return saleOrderOperationRecordDomainApiRemote.getOrderRejectOperationRecord(orderOperationRecordRequestDTO);
    }
}
