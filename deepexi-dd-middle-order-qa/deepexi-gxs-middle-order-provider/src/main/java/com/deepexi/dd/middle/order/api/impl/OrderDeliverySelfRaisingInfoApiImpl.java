package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderDeliverySelfRaisingInfoApi;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoDTO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliverySelfRaisingInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderDeliverySelfRaisingInfoService;
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
 * OrderDeliverySelfRaisingInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderDeliverySelfRaisingInfoApiImpl implements OrderDeliverySelfRaisingInfoApi {

    @Autowired
    private OrderDeliverySelfRaisingInfoService orderDeliverySelfRaisingInfoService;

    @Override
    @ApiOperation("查询出库单自提地址信息表列表")
    public List<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderDeliverySelfRaisingInfoService
                    .listOrderDeliverySelfRaisingInfos(query.clone(OrderDeliverySelfRaisingInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderDeliverySelfRaisingInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询出库单自提地址信息表列表")
    public PageBean<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderDeliverySelfRaisingInfoService
                    .listOrderDeliverySelfRaisingInfosPage(query.clone(OrderDeliverySelfRaisingInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderDeliverySelfRaisingInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除出库单自提地址信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderDeliverySelfRaisingInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增出库单自提地址信息表")
    public OrderDeliverySelfRaisingInfoResponseDTO insert(@RequestBody OrderDeliverySelfRaisingInfoRequestDTO record) {
        return orderDeliverySelfRaisingInfoService.insert(record.clone(OrderDeliverySelfRaisingInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderDeliverySelfRaisingInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询出库单自提地址信息表")
    public OrderDeliverySelfRaisingInfoResponseDTO selectById(@PathVariable Long id) {
        OrderDeliverySelfRaisingInfoDTO result = orderDeliverySelfRaisingInfoService.selectById(id);
        return result != null ? result.clone(OrderDeliverySelfRaisingInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新出库单自提地址信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderDeliverySelfRaisingInfoRequestDTO record) {
        return orderDeliverySelfRaisingInfoService.updateById(id, record.clone(OrderDeliverySelfRaisingInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
