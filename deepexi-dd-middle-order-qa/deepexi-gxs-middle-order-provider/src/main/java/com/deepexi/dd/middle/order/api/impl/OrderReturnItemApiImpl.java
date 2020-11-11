package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderReturnItemApi;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemQuery;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemRequestQuery;
import com.deepexi.dd.middle.order.service.OrderReturnItemService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * OrderReturnItemApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderReturnItemApiImpl implements OrderReturnItemApi {

    @Autowired
    private OrderReturnItemService orderReturnItemService;

    @Override
    @ApiOperation("查询销售订单退货明细表列表")
    public List<OrderReturnItemResponseDTO> listOrderReturnItems(OrderReturnItemRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderReturnItemService
                    .listOrderReturnItems(query.clone(OrderReturnItemQuery.class, CloneDirection.OPPOSITE)),
                                    OrderReturnItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询销售订单退货明细表列表")
    public PageBean<OrderReturnItemResponseDTO> listOrderReturnItemsPage(OrderReturnItemRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderReturnItemService
                    .listOrderReturnItemsPage(query.clone(OrderReturnItemQuery.class, CloneDirection.OPPOSITE)),
                        OrderReturnItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除销售订单退货明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderReturnItemService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增销售订单退货明细表")
    public OrderReturnItemResponseDTO insert(@RequestBody OrderReturnItemRequestDTO record) {
        return orderReturnItemService.insert(record.clone(OrderReturnItemDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderReturnItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询销售订单退货明细表")
    public OrderReturnItemResponseDTO selectById(@PathVariable Long id) {
        OrderReturnItemDTO result = orderReturnItemService.selectById(id);
        return result != null ? result.clone(OrderReturnItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新销售订单退货明细表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderReturnItemRequestDTO record) {
        return orderReturnItemService.updateById(id, record.clone(OrderReturnItemDTO.class, CloneDirection.OPPOSITE));
    }
}
