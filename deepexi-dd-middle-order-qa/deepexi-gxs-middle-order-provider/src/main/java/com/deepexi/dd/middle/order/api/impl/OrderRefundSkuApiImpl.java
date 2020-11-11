package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderRefundSkuApi;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuRequestQuery;
import com.deepexi.dd.middle.order.service.OrderRefundSkuService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * OrderRefundSkuApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderRefundSkuApiImpl implements OrderRefundSkuApi {

    @Autowired
    private OrderRefundSkuService orderRefundSkuService;

    @Override
    @ApiOperation("查询列表")
    public Payload<List<OrderRefundSkuResponseDTO>> listOrderRefundSkus(OrderRefundSkuRequestQuery query) {
        return new Payload<>(ObjectCloneUtils
                .convertList(orderRefundSkuService
                                .listOrderRefundSkus(query.clone(OrderRefundSkuQuery.class, CloneDirection.OPPOSITE)),
                        OrderRefundSkuResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("分页查询列表")
    public PageBean<OrderRefundSkuResponseDTO> listOrderRefundSkusPage(OrderRefundSkuRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderRefundSkuService
                                .listOrderRefundSkusPage(query.clone(OrderRefundSkuQuery.class, CloneDirection.OPPOSITE)),
                        OrderRefundSkuResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderRefundSkuService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增")
    public OrderRefundSkuResponseDTO insert(@RequestBody OrderRefundSkuRequestDTO record) {
        return orderRefundSkuService.insert(record.clone(OrderRefundSkuDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderRefundSkuResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询")
    public OrderRefundSkuResponseDTO selectById(@PathVariable Long id) {
        OrderRefundSkuDTO result = orderRefundSkuService.selectById(id);
        return result != null ? result.clone(OrderRefundSkuResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新")
    public Boolean updateById(@PathVariable Long id, @RequestBody OrderRefundSkuRequestDTO record) {
        return orderRefundSkuService.updateById(id, record.clone(OrderRefundSkuDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("批量新增")
    public Payload<List<OrderRefundSkuResponseDTO>> batchInsert(@RequestBody List<OrderRefundSkuRequestDTO> list) {
        return new Payload<>(orderRefundSkuService.batchInsert(list));
    }
}
