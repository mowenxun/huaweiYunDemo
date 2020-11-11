package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOrderItemApi;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.dd.middle.order.service.SaleOrderItemService;
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
 * SaleOrderItemApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOrderItemApiImpl implements SaleOrderItemApi {

    @Autowired
    private SaleOrderItemService saleOrderItemService;

    @Override
    @ApiOperation("查询销售订单明细表列表")
    public List<SaleOrderItemMiddleResponseDTO> listSaleOrderItems(SaleOrderItemMiddleRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleOrderItemService
                    .listSaleOrderItems(query.clone(SaleOrderItemQuery.class, CloneDirection.OPPOSITE)),
                                    SaleOrderItemMiddleResponseDTO.class, CloneDirection.OPPOSITE);
    }

    /**
     * @Description:  分页查询销售订单明细表列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.dto.SaleOrderItemResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @Override
    @ApiOperation("分页查询销售订单明细表列表")
    public PageBean<SaleOrderItemMiddleResponseDTO> listSaleOrderItemsPage(SaleOrderItemMiddleRequestQuery query)  throws Exception{
        return saleOrderItemService.listSaleOrderItemsPage(query);
    }

    @Override
    @ApiOperation("批量删除销售订单明细表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleOrderItemService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增销售订单明细表")
    public SaleOrderItemMiddleResponseDTO insert(@RequestBody SaleOrderItemMiddleRequestDTO record) {
        return saleOrderItemService.insert(record.clone(SaleOrderItemDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleOrderItemMiddleResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询销售订单明细表")
    public SaleOrderItemMiddleResponseDTO selectById(@PathVariable Long id) {
        SaleOrderItemDTO result = saleOrderItemService.selectById(id);
        return result != null ? result.clone(SaleOrderItemMiddleResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新销售订单明细表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleOrderItemMiddleRequestDTO record) {
        return saleOrderItemService.updateById(id, record.clone(SaleOrderItemDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("根据ID批量更新销售订单明细表")
    public Boolean updateBatchById(@RequestBody List<SaleOrderItemMiddleRequestDTO> records) {
        return saleOrderItemService.updateBatchById(ObjectCloneUtils.convertList(records, SaleOrderItemDTO.class));
    }
}
