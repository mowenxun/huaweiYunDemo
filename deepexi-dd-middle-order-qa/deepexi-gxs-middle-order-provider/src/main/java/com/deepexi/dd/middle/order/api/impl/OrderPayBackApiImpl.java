package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderPayBackApi;
import com.deepexi.dd.middle.order.domain.OrderPayBackDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderPayBackRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderPayBackResponseDTO;
import com.deepexi.dd.middle.order.domain.OrderPayBackQuery;
import com.deepexi.dd.middle.order.domain.query.OrderPayBackRequestQuery;
import com.deepexi.dd.middle.order.service.OrderPayBackService;
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
 * OrderPayBackApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderPayBackApiImpl implements OrderPayBackApi {

    @Autowired
    private OrderPayBackService orderPayBackService;

    @Override
    @ApiOperation("查询支付回调参数表列表")
    public List<OrderPayBackResponseDTO> listOrderPayBacks(OrderPayBackRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderPayBackService
                    .listOrderPayBacks(query.clone(OrderPayBackQuery.class, CloneDirection.OPPOSITE)),
                                    OrderPayBackResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询支付回调参数表列表")
    public PageBean<OrderPayBackResponseDTO> listOrderPayBacksPage(OrderPayBackRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderPayBackService
                    .listOrderPayBacksPage(query.clone(OrderPayBackQuery.class, CloneDirection.OPPOSITE)),
                        OrderPayBackResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除支付回调参数表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderPayBackService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增支付回调参数表")
    public OrderPayBackResponseDTO insert(@RequestBody OrderPayBackRequestDTO record) {
        return orderPayBackService.insert(record.clone(OrderPayBackDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderPayBackResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询支付回调参数表")
    public OrderPayBackResponseDTO selectById(@PathVariable Long id) {
        OrderPayBackDTO result = orderPayBackService.selectById(id);
        return result != null ? result.clone(OrderPayBackResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新支付回调参数表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderPayBackRequestDTO record) {
        return orderPayBackService.updateById(id, record.clone(OrderPayBackDTO.class, CloneDirection.OPPOSITE));
    }
}
