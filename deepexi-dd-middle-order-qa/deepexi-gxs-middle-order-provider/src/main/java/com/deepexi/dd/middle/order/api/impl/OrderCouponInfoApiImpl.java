package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderCouponInfoApi;
import com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoQuery;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderCouponInfoService;
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
 * OrderCouponInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderCouponInfoApiImpl implements OrderCouponInfoApi {

    @Autowired
    private OrderCouponInfoService orderCouponInfoService;

    @Override
    @ApiOperation("查询优惠信息表列表")
    public List<OrderCouponInfoResponseDTO> listOrderCouponInfos(OrderCouponInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderCouponInfoService
                    .listOrderCouponInfos(query.clone(OrderCouponInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderCouponInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询优惠信息表列表")
    public PageBean<OrderCouponInfoResponseDTO> listOrderCouponInfosPage(OrderCouponInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderCouponInfoService
                    .listOrderCouponInfosPage(query.clone(OrderCouponInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderCouponInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除优惠信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderCouponInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增优惠信息表")
    public OrderCouponInfoResponseDTO insert(@RequestBody OrderCouponInfoRequestDTO record) {
        return orderCouponInfoService.insert(record.clone(OrderCouponInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderCouponInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询优惠信息表")
    public OrderCouponInfoResponseDTO selectById(@PathVariable Long id) {
        OrderCouponInfoDTO result = orderCouponInfoService.selectById(id);
        return result != null ? result.clone(OrderCouponInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新优惠信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderCouponInfoRequestDTO record) {
        return orderCouponInfoService.updateById(id, record.clone(OrderCouponInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
