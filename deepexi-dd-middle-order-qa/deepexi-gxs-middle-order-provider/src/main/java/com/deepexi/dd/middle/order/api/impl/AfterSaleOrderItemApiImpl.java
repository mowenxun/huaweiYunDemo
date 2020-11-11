package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.AfterSaleOrderItemApi;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemQuery;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemRequestQuery;
import com.deepexi.dd.middle.order.service.AfterSaleOrderItemService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * AfterSaleOrderItemApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class AfterSaleOrderItemApiImpl implements AfterSaleOrderItemApi {

    @Autowired
    private AfterSaleOrderItemService afterSaleOrderItemService;

    @Override
    @ApiOperation("查询售后订单明细表列表")
    public List<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItems(AfterSaleOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertList(afterSaleOrderItemService
                    .listAfterSaleOrderItems(query.clone(AfterSaleOrderItemQuery.class, CloneDirection.OPPOSITE)),
                                    AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询售后订单明细表列表")
    public PageBean<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItemsPage(AfterSaleOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(afterSaleOrderItemService
                    .listAfterSaleOrderItemsPage(query.clone(AfterSaleOrderItemQuery.class, CloneDirection.OPPOSITE)),
                        AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除售后订单明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return afterSaleOrderItemService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增售后订单明细表")
    public AfterSaleOrderItemResponseDTO insert(@RequestBody AfterSaleOrderItemRequestDTO record) {
        return afterSaleOrderItemService.insert(record.clone(AfterSaleOrderItemDTO.class, CloneDirection.OPPOSITE))
                .clone(AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询售后订单明细表")
    public AfterSaleOrderItemResponseDTO selectById(@PathVariable Long id) {
        AfterSaleOrderItemDTO result = afterSaleOrderItemService.selectById(id);
        return result != null ? result.clone(AfterSaleOrderItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新售后订单明细表")
    public Boolean updateById(@PathVariable Long id,@RequestBody AfterSaleOrderItemRequestDTO record) {
        return afterSaleOrderItemService.updateById(id, record.clone(AfterSaleOrderItemDTO.class, CloneDirection.OPPOSITE));
    }
}
