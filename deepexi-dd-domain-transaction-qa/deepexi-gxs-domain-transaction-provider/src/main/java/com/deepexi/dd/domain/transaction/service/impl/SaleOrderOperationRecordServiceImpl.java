package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.OrderRejectOperationRecordRequestDTO;
import com.deepexi.dd.domain.transaction.remote.order.OrderOperationRecordClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderOperationRecordService;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author aofeng
 * @version 1.0
 * @date 2020-08-18 19:53
 */
@Service
@Slf4j
public class SaleOrderOperationRecordServiceImpl implements SaleOrderOperationRecordService {

    @Autowired
    private OrderOperationRecordClient orderOperationRecordClient;

    /***
     * 查询订单下的操作记录
     * @param orderOperationRecordRequestDTO
     * @return
     */
    @Override
    public PageBean<OrderOperationRecordResponseDTO> getOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        
        if ((null == orderOperationRecordRequestDTO.getOrderId() || null == orderOperationRecordRequestDTO.getOrderCode()) && null == orderOperationRecordRequestDTO.getOperationType()) {

            return new PageBean<>();
        }
        return orderOperationRecordClient.listOrderOperationRecordsPage(orderOperationRecordRequestDTO.clone(OrderOperationRecordRequestQuery.class));
    }

    /**
     * 获取最新的驳回信息
     * @param orderOperationRecordRequestDTO
     * @return
     */
    @Override
    public OrderOperationRecordResponseDTO getOrderRejectOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        orderOperationRecordRequestDTO.setPage(1);
        orderOperationRecordRequestDTO.setSize(100);
        orderOperationRecordRequestDTO.setOperationType(1);
//        orderOperationRecordRequestDTO.setOperation("审批驳回成功");
        PageBean<OrderOperationRecordResponseDTO> list=this.getOrderOperationRecord(orderOperationRecordRequestDTO);
        if(CollectionUtil.isEmpty(list.getContent())){
            return null;
        }
        List<OrderOperationRecordResponseDTO> orderOperationRecordResponseDTOS = list.getContent().stream().filter(f->f.getOperation().equals("接单驳回成功")||f.getOperation().equals("审批驳回成功")).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(orderOperationRecordResponseDTOS)){
            return null;
        }
        return list.getContent().stream().skip(list.getContent().size() - 1).findFirst().orElse(null);
    }

    /**
     * 查询操作记录集合
     *
     * @param orderOperationRecordRequestDTO
     * @return
     */
    @Override
    public List<OrderOperationRecordResponseDTO> listOrderOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        if (null == orderOperationRecordRequestDTO.getOrderId() || null == orderOperationRecordRequestDTO.getOperationType()) {
            return null;
        }
        return orderOperationRecordClient.listOrderOperationRecords(orderOperationRecordRequestDTO.clone(OrderOperationRecordRequestQuery.class));
    }

    /**
     * 新增操作记录
     * @param orderOperationRecordRequestDTO
     * @return
     */
    @Override
    public Payload add(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        return new Payload<>(orderOperationRecordClient.insert(GeneralConvertUtils.conv(orderOperationRecordRequestDTO,OrderOperationRecordRequestDTO.class)));
    }

}
