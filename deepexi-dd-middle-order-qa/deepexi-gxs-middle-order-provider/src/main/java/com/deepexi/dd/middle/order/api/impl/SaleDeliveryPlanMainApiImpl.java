package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleDeliveryPlanMainApi;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanMainDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanMainRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanMainResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainQuery;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainRequestQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryPlanMainService;
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
 * SaleDeliveryPlanMainApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleDeliveryPlanMainApiImpl implements SaleDeliveryPlanMainApi {

    @Autowired
    private SaleDeliveryPlanMainService saleDeliveryPlanMainService;

    @Override
    @ApiOperation("查询发货编排主表列表")
    public List<SaleDeliveryPlanMainResponseDTO> listSaleDeliveryPlanMains(SaleDeliveryPlanMainRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleDeliveryPlanMainService
                    .listSaleDeliveryPlanMains(query.clone(SaleDeliveryPlanMainQuery.class, CloneDirection.OPPOSITE)),
                                    SaleDeliveryPlanMainResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询发货编排主表列表")
    public PageBean<SaleDeliveryPlanMainResponseDTO> listSaleDeliveryPlanMainsPage(SaleDeliveryPlanMainRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleDeliveryPlanMainService
                    .listSaleDeliveryPlanMainsPage(query.clone(SaleDeliveryPlanMainQuery.class, CloneDirection.OPPOSITE)),
                        SaleDeliveryPlanMainResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除发货编排主表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleDeliveryPlanMainService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增发货编排主表")
    public SaleDeliveryPlanMainResponseDTO insert(@RequestBody SaleDeliveryPlanMainRequestDTO record) {
        return saleDeliveryPlanMainService.insert(record.clone(SaleDeliveryPlanMainDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleDeliveryPlanMainResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询发货编排主表")
    public SaleDeliveryPlanMainResponseDTO selectById(@PathVariable Long id) {
        SaleDeliveryPlanMainDTO result = saleDeliveryPlanMainService.selectById(id);
        return result != null ? result.clone(SaleDeliveryPlanMainResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新发货编排主表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleDeliveryPlanMainRequestDTO record) {
        return saleDeliveryPlanMainService.updateById(id, record.clone(SaleDeliveryPlanMainDTO.class, CloneDirection.OPPOSITE));
    }
}
