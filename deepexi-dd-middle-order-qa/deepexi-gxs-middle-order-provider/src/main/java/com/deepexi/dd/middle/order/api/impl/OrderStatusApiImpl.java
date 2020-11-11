package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderStatusApi;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusQuery;
import com.deepexi.dd.middle.order.domain.query.OrderStatusRequestQuery;
import com.deepexi.dd.middle.order.service.OrderStatusService;
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
 * OrderStatusApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderStatusApiImpl implements OrderStatusApi {

    @Autowired
    private OrderStatusService orderStatusService;

    @Override
    @ApiOperation("查询状态表列表")
    public List<OrderStatusResponseDTO> listOrderStatuss(OrderStatusRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderStatusService
                    .listOrderStatuss(query.clone(OrderStatusQuery.class, CloneDirection.OPPOSITE)),
                                    OrderStatusResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询状态表列表")
    public PageBean<OrderStatusResponseDTO> listOrderStatussPage(OrderStatusRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderStatusService
                    .listOrderStatussPage(query.clone(OrderStatusQuery.class, CloneDirection.OPPOSITE)),
                        OrderStatusResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除状态表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderStatusService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增状态表")
    public OrderStatusResponseDTO insert(@RequestBody OrderStatusRequestDTO record) {
        return orderStatusService.insert(record.clone(OrderStatusDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderStatusResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询状态表")
    public OrderStatusResponseDTO selectById(@PathVariable Long id) {
        OrderStatusDTO result = orderStatusService.selectById(id);
        return result != null ? result.clone(OrderStatusResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新状态表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderStatusRequestDTO record) {
        return orderStatusService.updateById(id, record.clone(OrderStatusDTO.class, CloneDirection.OPPOSITE));
    }
}
