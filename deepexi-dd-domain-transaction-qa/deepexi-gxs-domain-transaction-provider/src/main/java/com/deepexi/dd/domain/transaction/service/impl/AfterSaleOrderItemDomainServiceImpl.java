package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.transaction.remote.gxs.AfterSaleOrderItemRemoteService;
import com.deepexi.dd.domain.transaction.service.AfterSaleOrderItemDomainService;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class AfterSaleOrderItemDomainServiceImpl implements AfterSaleOrderItemDomainService {

    @Autowired
    AfterSaleOrderItemRemoteService afterSaleOrderItemRemoteService;

    @Override
    @ApiOperation("查询售后订单明细表列表")
    public List<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItems(AfterSaleOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertList(afterSaleOrderItemRemoteService
                                .listAfterSaleOrderItems(query.clone(AfterSaleOrderItemRequestQuery.class, CloneDirection.OPPOSITE)),
                        AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询售后订单明细表列表")
    public PageBean<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItemsPage(AfterSaleOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(afterSaleOrderItemRemoteService
                                .listAfterSaleOrderItemsPage(query.clone(AfterSaleOrderItemRequestQuery.class, CloneDirection.OPPOSITE)),
                        AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除售后订单明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return afterSaleOrderItemRemoteService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增售后订单明细表")
    public AfterSaleOrderItemResponseDTO insert(@RequestBody AfterSaleOrderItemRequestDTO record) {
        return afterSaleOrderItemRemoteService.insert(record.clone(AfterSaleOrderItemRequestDTO.class, CloneDirection.OPPOSITE))
                .clone(AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询售后订单明细表")
    public AfterSaleOrderItemResponseDTO selectById(@PathVariable Long id) {
        AfterSaleOrderItemResponseDTO result = afterSaleOrderItemRemoteService.selectById(id);
        return result != null ? result.clone(AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新售后订单明细表")
    public Boolean updateById(@PathVariable Long id,@RequestBody AfterSaleOrderItemRequestDTO record) {
        return afterSaleOrderItemRemoteService.updateById(id, record.clone(AfterSaleOrderItemRequestDTO.class, CloneDirection.OPPOSITE));
    }

}
