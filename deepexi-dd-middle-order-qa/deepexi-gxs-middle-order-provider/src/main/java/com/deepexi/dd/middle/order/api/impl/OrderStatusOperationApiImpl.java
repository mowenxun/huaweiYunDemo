package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderStatusOperationApi;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusOperationDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusOperationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusOperationResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationQuery;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationRequestQuery;
import com.deepexi.dd.middle.order.service.OrderStatusOperationService;
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
 * OrderStatusOperationApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderStatusOperationApiImpl implements OrderStatusOperationApi {

    @Autowired
    private OrderStatusOperationService orderStatusOperationService;

    @Override
    @ApiOperation("查询订单 状态-操作表列表")
    public List<OrderStatusOperationResponseDTO> listOrderStatusOperations(OrderStatusOperationRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderStatusOperationService
                    .listOrderStatusOperations(query.clone(OrderStatusOperationQuery.class, CloneDirection.OPPOSITE)),
                                    OrderStatusOperationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询订单 状态-操作表列表")
    public PageBean<OrderStatusOperationResponseDTO> listOrderStatusOperationsPage(OrderStatusOperationRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderStatusOperationService
                    .listOrderStatusOperationsPage(query.clone(OrderStatusOperationQuery.class, CloneDirection.OPPOSITE)),
                        OrderStatusOperationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除订单 状态-操作表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderStatusOperationService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增订单 状态-操作表")
    public OrderStatusOperationResponseDTO insert(@RequestBody OrderStatusOperationRequestDTO record) {
        return orderStatusOperationService.insert(record.clone(OrderStatusOperationDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderStatusOperationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询订单 状态-操作表")
    public OrderStatusOperationResponseDTO selectById(@PathVariable Long id) {
        OrderStatusOperationDTO result = orderStatusOperationService.selectById(id);
        return result != null ? result.clone(OrderStatusOperationResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }


}
