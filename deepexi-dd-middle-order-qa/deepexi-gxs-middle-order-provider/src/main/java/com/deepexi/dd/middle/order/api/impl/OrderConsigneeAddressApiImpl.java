package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderConsigneeAddressApi;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressResponseDTO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressQuery;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeAddressRequestQuery;
import com.deepexi.dd.middle.order.service.OrderConsigneeAddressService;
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
 * OrderConsigneeAddressApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderConsigneeAddressApiImpl implements OrderConsigneeAddressApi {

    @Autowired
    private OrderConsigneeAddressService orderConsigneeAddressService;

    @Override
    @ApiOperation("查询订单收货地址信息表列表")
    public List<OrderConsigneeAddressResponseDTO> listOrderConsigneeAddresss(OrderConsigneeAddressRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderConsigneeAddressService
                    .listOrderConsigneeAddresss(query.clone(OrderConsigneeAddressQuery.class, CloneDirection.OPPOSITE)),
                                    OrderConsigneeAddressResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询订单收货地址信息表列表")
    public PageBean<OrderConsigneeAddressResponseDTO> listOrderConsigneeAddresssPage(OrderConsigneeAddressRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderConsigneeAddressService
                    .listOrderConsigneeAddresssPage(query.clone(OrderConsigneeAddressQuery.class, CloneDirection.OPPOSITE)),
                        OrderConsigneeAddressResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除订单收货地址信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderConsigneeAddressService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增订单收货地址信息表")
    public OrderConsigneeAddressResponseDTO insert(@RequestBody OrderConsigneeAddressRequestDTO record) {
        return orderConsigneeAddressService.insert(record.clone(OrderConsigneeAddressDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderConsigneeAddressResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询订单收货地址信息表")
    public OrderConsigneeAddressResponseDTO selectById(@PathVariable Long id) {
        OrderConsigneeAddressDTO result = orderConsigneeAddressService.selectById(id);
        return result != null ? result.clone(OrderConsigneeAddressResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新订单收货地址信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderConsigneeAddressRequestDTO record) {
        return orderConsigneeAddressService.updateById(id, record.clone(OrderConsigneeAddressDTO.class, CloneDirection.OPPOSITE));
    }
}
