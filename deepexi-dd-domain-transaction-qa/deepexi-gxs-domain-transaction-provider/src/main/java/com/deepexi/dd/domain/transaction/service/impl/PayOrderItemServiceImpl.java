package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.transaction.enums.gxs.GxsPayOrderCollectionStatusEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.PayOrderItemRemoteService;
import com.deepexi.dd.domain.transaction.remote.gxs.PayOrderRemoteService;
import com.deepexi.dd.domain.transaction.service.PayOrderItemService;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * 付款单service
 *
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class PayOrderItemServiceImpl implements PayOrderItemService {

    @Autowired
    PayOrderItemRemoteService payOrderItemRemoteService;
    @Autowired
    PayOrderRemoteService payOrderRemoteService;

    @Override
    @ApiOperation("查询付款单")
    public List<PayOrderItemResponseDTO> list(PayOrderItemRequestDTO query) {
        return ObjectCloneUtils
                .convertList(payOrderItemRemoteService.list(query),
                        PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询付款单")
    public PageBean<PayOrderItemResponseDTO> listPage(PayOrderItemRequestPageQuery query) {
        return ObjectCloneUtils
                .convertPageBean(payOrderItemRemoteService.listPage(query),
                        PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除付款单")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return payOrderItemRemoteService.deleteByIds(id);
    }

    /**
     * 供应商财务收款<br>
     * 1.创建收款单
     * 2.修改应收单的已收和待收金额
     */
    @Override
    @ApiOperation("新增付款单")
    public PayOrderItemResponseDTO insert(@RequestBody PayOrderItemRequestDTO record) {
        PayOrderResponseDTO payOrder = payOrderRemoteService.selectById(record.getOrderId());
        // 收款时间取财务手动收款的时间
        record.setPayTime(new Date());
        PayOrderItemResponseDTO item = payOrderItemRemoteService.insert(record);

        // 修改应收单
        PayOrderRequestDTO updatePayOrder = new PayOrderRequestDTO();
        updatePayOrder.setId(record.getOrderId());
        updatePayOrder.setPaidMoney(payOrder.getPaidMoney().add(record.getPayMoney()));
        updatePayOrder.setUnpayMoney(payOrder.getUnpayMoney().subtract(record.getPayMoney()));
        updatePayOrder.setCollectionStatus(Integer.parseInt(GxsPayOrderCollectionStatusEnum.PARTIAL_RECEIVED.getCode()));
        if (updatePayOrder.getUnpayMoney().doubleValue() == 0
                && updatePayOrder.getPaidMoney().doubleValue() == payOrder.getTotalAmount().doubleValue()) {
            updatePayOrder.setCollectionStatus(Integer.parseInt(GxsPayOrderCollectionStatusEnum.RECEIVED.getCode()));
        }
        payOrderRemoteService.updateById(updatePayOrder);
        return item.clone(PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询付款单")
    public PayOrderItemResponseDTO selectById(@PathVariable Long id) {
        PayOrderItemResponseDTO result = payOrderItemRemoteService.selectById(id);
        return result != null ? result.clone(PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新付款单")
    public boolean updateById(@RequestBody PayOrderItemRequestDTO record) {
        return payOrderItemRemoteService.updateById(record);
    }


}
