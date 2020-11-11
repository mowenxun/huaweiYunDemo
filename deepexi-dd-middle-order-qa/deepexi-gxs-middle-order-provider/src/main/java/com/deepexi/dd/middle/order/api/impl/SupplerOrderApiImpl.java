package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SupplerOrderApi;
import com.deepexi.dd.middle.order.domain.SupplerOrderDTO;
import com.deepexi.dd.middle.order.domain.SupplerOrderQuery;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderRequestQuery;
import com.deepexi.dd.middle.order.service.SupplerOrderService;
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
 * SupplerOrderApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SupplerOrderApiImpl implements SupplerOrderApi {

    @Autowired
    private SupplerOrderService supplerOrderService;

    @Override
    @ApiOperation("查询已分发订单列表")
    public List<SupplerOrderResponseDTO> listSupplerOrders(SupplerOrderRequestQuery query) {
        return ObjectCloneUtils
                .convertList(supplerOrderService
                    .listSupplerOrders(query.clone(SupplerOrderQuery.class, CloneDirection.OPPOSITE)),
                                    SupplerOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询已分发订单列表")
    public PageBean<SupplerOrderResponseDTO> listSupplerOrdersPage(SupplerOrderRequestQuery query) {
        PageBean<SupplerOrderResponseDTO> rs = ObjectCloneUtils
                .convertPageBean(supplerOrderService
                                .listSupplerOrdersPage(query.clone(SupplerOrderQuery.class, CloneDirection.OPPOSITE)),
                        SupplerOrderResponseDTO.class, CloneDirection.OPPOSITE);
        return rs;
    }

    @Override
    @ApiOperation("批量删除已分发订单")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return supplerOrderService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增已分发订单")
    public SupplerOrderResponseDTO insert(@RequestBody SupplerOrderRequestDTO record) {
        return supplerOrderService.insert(record.clone(SupplerOrderDTO.class, CloneDirection.OPPOSITE))
                .clone(SupplerOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询已分发订单")
    public SupplerOrderResponseDTO selectById(@PathVariable Long id) {
        SupplerOrderDTO result = supplerOrderService.selectById(id);
        return result != null ? result.clone(SupplerOrderResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新已分发订单")
    public Boolean updateById(@PathVariable Long id,@RequestBody SupplerOrderRequestDTO record) {
        return supplerOrderService.updateById(id, record.clone(SupplerOrderDTO.class, CloneDirection.OPPOSITE));
    }
}
