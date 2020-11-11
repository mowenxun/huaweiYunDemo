package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SalePickGoodsOrderApi;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderRequestQuery;
import com.deepexi.dd.middle.order.service.SalePickGoodsOrderService;
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
 * SalePickGoodsOrderApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SalePickGoodsOrderApiImpl implements SalePickGoodsOrderApi {

    @Autowired
    private SalePickGoodsOrderService salePickGoodsOrderService;

    @Override
    @ApiOperation("查询提货单据所关联的订单的信息表列表")
    public List<SalePickGoodsOrderResponseDTO> listSalePickGoodsOrders(SalePickGoodsOrderRequestQuery query) {
        return ObjectCloneUtils
                .convertList(salePickGoodsOrderService
                    .listSalePickGoodsOrders(query.clone(SalePickGoodsOrderQuery.class, CloneDirection.OPPOSITE)),
                                    SalePickGoodsOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询提货单据所关联的订单的信息表列表")
    public PageBean<SalePickGoodsOrderResponseDTO> listSalePickGoodsOrdersPage(SalePickGoodsOrderRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(salePickGoodsOrderService
                    .listSalePickGoodsOrdersPage(query.clone(SalePickGoodsOrderQuery.class, CloneDirection.OPPOSITE)),
                        SalePickGoodsOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除提货单据所关联的订单的信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return salePickGoodsOrderService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增提货单据所关联的订单的信息表")
    public SalePickGoodsOrderResponseDTO insert(@RequestBody SalePickGoodsOrderRequestDTO record) {
        return salePickGoodsOrderService.insert(record.clone(SalePickGoodsOrderDTO.class, CloneDirection.OPPOSITE))
                .clone(SalePickGoodsOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询提货单据所关联的订单的信息表")
    public SalePickGoodsOrderResponseDTO selectById(@PathVariable Long id) {
        SalePickGoodsOrderDTO result = salePickGoodsOrderService.selectById(id);
        return result != null ? result.clone(SalePickGoodsOrderResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新提货单据所关联的订单的信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SalePickGoodsOrderRequestDTO record) {
        return salePickGoodsOrderService.updateById(id, record.clone(SalePickGoodsOrderDTO.class, CloneDirection.OPPOSITE));
    }
}
