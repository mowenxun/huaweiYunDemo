package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.ShopOrderApi;
import com.deepexi.dd.middle.order.domain.ShopOrderDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.ShopOrderQuery;
import com.deepexi.dd.middle.order.domain.query.ShopOrderRequestQuery;
import com.deepexi.dd.middle.order.service.ShopOrderService;
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
 * ShopOrderApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class ShopOrderApiImpl implements ShopOrderApi {

    @Autowired
    private ShopOrderService shopOrderService;

    @Override
    @ApiOperation("查询店铺订单列表")
    public List<ShopOrderResponseDTO> listShopOrders(ShopOrderRequestQuery query) {
        return ObjectCloneUtils
                .convertList(shopOrderService
                    .listShopOrders(query.clone(ShopOrderQuery.class, CloneDirection.OPPOSITE)),
                                    ShopOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询店铺订单列表")
    public PageBean<ShopOrderResponseDTO> listShopOrdersPage(ShopOrderRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(shopOrderService
                    .listShopOrdersPage(query.clone(ShopOrderQuery.class, CloneDirection.OPPOSITE)),
                        ShopOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除店铺订单")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return shopOrderService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增店铺订单")
    public ShopOrderResponseDTO insert(@RequestBody ShopOrderRequestDTO record) {
        return shopOrderService.insert(record.clone(ShopOrderDTO.class, CloneDirection.OPPOSITE))
                .clone(ShopOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询店铺订单")
    public ShopOrderResponseDTO selectById(@PathVariable Long id) {
        ShopOrderDTO result = shopOrderService.selectById(id);
        return result != null ? result.clone(ShopOrderResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新店铺订单")
    public Boolean updateById(@PathVariable Long id,@RequestBody ShopOrderRequestDTO record) {
        return shopOrderService.updateById(id, record.clone(ShopOrderDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    public Boolean updateByCode(@RequestBody List<ShopOrderRequestDTO> records) {
        return shopOrderService.updateByCode(ObjectCloneUtils.convertList(records,ShopOrderDTO.class));
    }
}
