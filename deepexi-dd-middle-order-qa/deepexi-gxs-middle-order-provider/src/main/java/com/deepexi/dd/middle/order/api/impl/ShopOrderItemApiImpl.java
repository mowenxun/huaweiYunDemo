package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.ShopOrderItemApi;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemQuery;
import com.deepexi.dd.middle.order.domain.query.ShopOrderItemRequestQuery;
import com.deepexi.dd.middle.order.service.ShopOrderItemService;
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
 * ShopOrderItemApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class ShopOrderItemApiImpl implements ShopOrderItemApi {

    @Autowired
    private ShopOrderItemService shopOrderItemService;

    @Override
    @ApiOperation("查询店铺订单明细表列表")
    public List<ShopOrderItemResponseDTO> listShopOrderItems(ShopOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertList(shopOrderItemService
                    .listShopOrderItems(query.clone(ShopOrderItemQuery.class, CloneDirection.OPPOSITE)),
                                    ShopOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询店铺订单明细表列表")
    public PageBean<ShopOrderItemResponseDTO> listShopOrderItemsPage(ShopOrderItemRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(shopOrderItemService
                    .listShopOrderItemsPage(query.clone(ShopOrderItemQuery.class, CloneDirection.OPPOSITE)),
                        ShopOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除店铺订单明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return shopOrderItemService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增店铺订单明细表")
    public ShopOrderItemResponseDTO insert(@RequestBody ShopOrderItemRequestDTO record) {
        return shopOrderItemService.insert(record.clone(ShopOrderItemDTO.class, CloneDirection.OPPOSITE))
                .clone(ShopOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询店铺订单明细表")
    public ShopOrderItemResponseDTO selectById(@PathVariable Long id) {
        ShopOrderItemDTO result = shopOrderItemService.selectById(id);
        return result != null ? result.clone(ShopOrderItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新店铺订单明细表")
    public Boolean updateById(@PathVariable Long id,@RequestBody ShopOrderItemRequestDTO record) {
        return shopOrderItemService.updateById(id, record.clone(ShopOrderItemDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    public boolean saveBatch(@RequestBody List<ShopOrderItemRequestDTO> list) {
      List<ShopOrderItemDTO> orderItemDTOList= ObjectCloneUtils.convertList(list,ShopOrderItemDTO.class);
        return shopOrderItemService.saveBatch(orderItemDTOList);
    }
}
