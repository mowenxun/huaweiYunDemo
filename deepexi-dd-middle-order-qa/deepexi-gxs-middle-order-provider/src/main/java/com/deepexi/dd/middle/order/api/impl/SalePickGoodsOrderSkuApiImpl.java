package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SalePickGoodsOrderSkuApi;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.service.SalePickGoodsOrderSkuService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SalePickGoodsOrderSkuApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SalePickGoodsOrderSkuApiImpl implements SalePickGoodsOrderSkuApi {

    @Autowired
    private SalePickGoodsOrderSkuService salePickGoodsOrderSkuService;

    @Override
    @ApiOperation("查询提货单据所关联的订单所关联的sku信息表列表")
    public List<SalePickGoodsOrderSkuResponseDTO> listSalePickGoodsOrderSkus(@RequestBody SalePickGoodsOrderSkuRequestQuery query) {
        return ObjectCloneUtils
                .convertList(salePickGoodsOrderSkuService
                                     .listSalePickGoodsOrderSkus(query.clone(SalePickGoodsOrderSkuQuery.class, CloneDirection.OPPOSITE)),
                             SalePickGoodsOrderSkuResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询提货单据所关联的订单所关联的sku信息表列表")
    public PageBean<SalePickGoodsOrderSkuResponseDTO> listSalePickGoodsOrderSkusPage(SalePickGoodsOrderSkuRequestQuery query) {
        PageBean<SalePickGoodsOrderSkuDTO> salePickGoodsOrderSkuDTOPageBean = salePickGoodsOrderSkuService
                .listSalePickGoodsOrderSkusPage(query.clone(SalePickGoodsOrderSkuQuery.class, CloneDirection.OPPOSITE));
        if (org.springframework.util.ObjectUtils.isEmpty(salePickGoodsOrderSkuDTOPageBean.getContent())) {
            return null;
        }
        return ObjectCloneUtils
                .convertPageBean(salePickGoodsOrderSkuDTOPageBean,
                                 SalePickGoodsOrderSkuResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除提货单据所关联的订单所关联的sku信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return salePickGoodsOrderSkuService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增提货单据所关联的订单所关联的sku信息表")
    public SalePickGoodsOrderSkuResponseDTO insert(@RequestBody SalePickGoodsOrderSkuRequestDTO record) {
        return salePickGoodsOrderSkuService.insert(record.clone(SalePickGoodsOrderSkuDTO.class, CloneDirection.OPPOSITE))
                                           .clone(SalePickGoodsOrderSkuResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询提货单据所关联的订单所关联的sku信息表")
    public SalePickGoodsOrderSkuResponseDTO selectById(@PathVariable Long id) {
        SalePickGoodsOrderSkuDTO result = salePickGoodsOrderSkuService.selectById(id);
        return result != null ? result.clone(SalePickGoodsOrderSkuResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新提货单据所关联的订单所关联的sku信息表")
    public Boolean updateById(@PathVariable Long id, @RequestBody SalePickGoodsOrderSkuRequestDTO record) {
        return salePickGoodsOrderSkuService.updateById(id, record.clone(SalePickGoodsOrderSkuDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("根据skuID查询提货单据所关联的订单所关联的sku信息")
    public SalePickGoodsOrderSkuResponseDTO selectByRowCode(@RequestParam String rowCode) {
        return salePickGoodsOrderSkuService.selectByRowCode(rowCode);
    }

    @Override
    @ApiOperation("根据销售单明细获取出库单明细")
    public List<SaleOutTaskDetailInfoRequestDTO> getOutItemBySaleItemId(@RequestBody List<Long> saleItemIds) {
        if(saleItemIds == null || saleItemIds.size() == 0){
            return null;
        }
        List<SaleOutTaskDetailInfoRequestDTO> items = salePickGoodsOrderSkuService.getOutItemBySaleItemId(saleItemIds);
        return items;
    }
}
