package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOrderItemLogisticsApi;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemLogisticsDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemLogisticsRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemLogisticsResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsRequestQuery;
import com.deepexi.dd.middle.order.service.SaleOrderItemLogisticsService;
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
 * SaleOrderItemLogisticsApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOrderItemLogisticsApiImpl implements SaleOrderItemLogisticsApi {

    @Autowired
    private SaleOrderItemLogisticsService saleOrderItemLogisticsService;

    @Override
    @ApiOperation("查询OMS物流信息列表")
    public List<SaleOrderItemLogisticsResponseDTO> listSaleOrderItemLogisticss(SaleOrderItemLogisticsRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleOrderItemLogisticsService
                    .listSaleOrderItemLogisticss(query.clone(SaleOrderItemLogisticsQuery.class, CloneDirection.OPPOSITE)),
                                    SaleOrderItemLogisticsResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询OMS物流信息列表")
    public PageBean<SaleOrderItemLogisticsResponseDTO> listSaleOrderItemLogisticssPage(SaleOrderItemLogisticsRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleOrderItemLogisticsService
                    .listSaleOrderItemLogisticssPage(query.clone(SaleOrderItemLogisticsQuery.class, CloneDirection.OPPOSITE)),
                        SaleOrderItemLogisticsResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除OMS物流信息")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleOrderItemLogisticsService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增OMS物流信息")
    public Long insert(@RequestBody SaleOrderItemLogisticsRequestDTO record) throws Exception {
        return saleOrderItemLogisticsService.insert(record.clone(SaleOrderItemLogisticsDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("根据ID查询OMS物流信息")
    public SaleOrderItemLogisticsResponseDTO selectById(@PathVariable Long id) {
        SaleOrderItemLogisticsDTO result = saleOrderItemLogisticsService.selectById(id);
        return result != null ? result.clone(SaleOrderItemLogisticsResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新OMS物流信息")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleOrderItemLogisticsRequestDTO record) {
        return saleOrderItemLogisticsService.updateById(id, record.clone(SaleOrderItemLogisticsDTO.class, CloneDirection.OPPOSITE));
    }
}
