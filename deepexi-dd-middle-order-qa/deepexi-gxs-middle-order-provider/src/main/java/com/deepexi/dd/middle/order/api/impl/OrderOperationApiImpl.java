package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderOperationApi;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationQuery;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRequestQuery;
import com.deepexi.dd.middle.order.service.OrderOperationService;
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
 * OrderOperationApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderOperationApiImpl implements OrderOperationApi {

    @Autowired
    private OrderOperationService orderOperationService;

    @Override
    @ApiOperation("查询按钮表列表")
    public List<OrderOperationResponseDTO> listOrderOperations(OrderOperationRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderOperationService
                    .listOrderOperations(query.clone(OrderOperationQuery.class, CloneDirection.OPPOSITE)),
                                    OrderOperationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询按钮表列表")
    public PageBean<OrderOperationResponseDTO> listOrderOperationsPage(OrderOperationRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderOperationService
                    .listOrderOperationsPage(query.clone(OrderOperationQuery.class, CloneDirection.OPPOSITE)),
                        OrderOperationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除按钮表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderOperationService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增按钮表")
    public OrderOperationResponseDTO insert(@RequestBody OrderOperationRequestDTO record) {
        return orderOperationService.insert(record.clone(OrderOperationDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderOperationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询按钮表")
    public OrderOperationResponseDTO selectById(@PathVariable Long id) {
        OrderOperationDTO result = orderOperationService.selectById(id);
        return result != null ? result.clone(OrderOperationResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }
}
