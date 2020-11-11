package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.ShopSupplerRelationApi;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationResponseDTO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationQuery;
import com.deepexi.dd.middle.order.domain.query.ShopSupplerRelationRequestQuery;
import com.deepexi.dd.middle.order.service.ShopSupplerRelationService;
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
 * ShopSupplerRelationApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class ShopSupplerRelationApiImpl implements ShopSupplerRelationApi {

    @Autowired
    private ShopSupplerRelationService shopSupplerRelationService;

    @Override
    @ApiOperation("查询已分发订单和店铺订单关系表列表")
    public List<ShopSupplerRelationResponseDTO> listShopSupplerRelations(ShopSupplerRelationRequestQuery query) {
        return ObjectCloneUtils
                .convertList(shopSupplerRelationService
                                .listShopSupplerRelations(query.clone(ShopSupplerRelationQuery.class,
                                        CloneDirection.OPPOSITE)),
                        ShopSupplerRelationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询已分发订单和店铺订单关系表列表")
    public PageBean<ShopSupplerRelationResponseDTO> listShopSupplerRelationsPage(ShopSupplerRelationRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(shopSupplerRelationService
                                .listShopSupplerRelationsPage(query.clone(ShopSupplerRelationQuery.class,
                                        CloneDirection.OPPOSITE)),
                        ShopSupplerRelationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除已分发订单和店铺订单关系表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return shopSupplerRelationService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增已分发订单和店铺订单关系表")
    public ShopSupplerRelationResponseDTO insert(@RequestBody ShopSupplerRelationRequestDTO record) {
        return shopSupplerRelationService.insert(record.clone(ShopSupplerRelationDTO.class, CloneDirection.OPPOSITE))
                .clone(ShopSupplerRelationResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询已分发订单和店铺订单关系表")
    public ShopSupplerRelationResponseDTO selectById(@PathVariable Long id) {
        ShopSupplerRelationDTO result = shopSupplerRelationService.selectById(id);
        return result != null ? result.clone(ShopSupplerRelationResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新已分发订单和店铺订单关系表")
    public Boolean updateById(@PathVariable Long id, @RequestBody ShopSupplerRelationRequestDTO record) {
        return shopSupplerRelationService.updateById(id, record.clone(ShopSupplerRelationDTO.class,
                CloneDirection.OPPOSITE));
    }

    @Override
    public boolean saveBatch(@RequestBody List<ShopSupplerRelationRequestDTO> list) {
        List<ShopSupplerRelationDTO> shopSupplerRelationDTOS = ObjectCloneUtils.convertList(list,
                ShopSupplerRelationDTO.class);
        return shopSupplerRelationService.saveBatch(shopSupplerRelationDTOS);
    }
}
