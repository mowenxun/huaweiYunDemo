package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderDeliveryInfoApi;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoQuery;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderDeliveryInfoService;
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
 * OrderDeliveryInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderDeliveryInfoApiImpl implements OrderDeliveryInfoApi {

    @Autowired
    private OrderDeliveryInfoService orderDeliveryInfoService;

    @Override
    @ApiOperation("查询销售出库单物流信息列表")
    public List<OrderDeliveryInfoResponseDTO> listOrderDeliveryInfos(OrderDeliveryInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderDeliveryInfoService
                    .listOrderDeliveryInfos(query.clone(OrderDeliveryInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderDeliveryInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询销售出库单物流信息列表")
    public PageBean<OrderDeliveryInfoResponseDTO> listOrderDeliveryInfosPage(OrderDeliveryInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderDeliveryInfoService
                    .listOrderDeliveryInfosPage(query.clone(OrderDeliveryInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderDeliveryInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除销售出库单物流信息")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderDeliveryInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增销售出库单物流信息")
    public OrderDeliveryInfoResponseDTO insert(@RequestBody OrderDeliveryInfoRequestDTO record) {
        return orderDeliveryInfoService.insert(record.clone(OrderDeliveryInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderDeliveryInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询销售出库单物流信息")
    public OrderDeliveryInfoResponseDTO selectById(@PathVariable Long id) {
        OrderDeliveryInfoDTO result = orderDeliveryInfoService.selectById(id);
        return result != null ? result.clone(OrderDeliveryInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新销售出库单物流信息")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderDeliveryInfoRequestDTO record) {
        return orderDeliveryInfoService.updateById(id, record.clone(OrderDeliveryInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
