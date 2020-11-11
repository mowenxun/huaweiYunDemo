package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleDeliveryPlanInfoApi;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoRequestQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryPlanInfoService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SaleDeliveryPlanInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleDeliveryPlanInfoApiImpl implements SaleDeliveryPlanInfoApi {

    @Autowired
    private SaleDeliveryPlanInfoService saleDeliveryPlanInfoService;

    @Override
    @ApiOperation("查询发货计划表列表")
    public List<SaleDeliveryPlanInfoResponseDTO> listSaleDeliveryPlanInfos(SaleDeliveryPlanInfoRequestQuery query) {
        List<SaleDeliveryPlanInfoDTO> saleDeliveryPlanInfoDTOS = saleDeliveryPlanInfoService
                .listSaleDeliveryPlanInfos(query.clone(SaleDeliveryPlanInfoQuery.class, CloneDirection.OPPOSITE));
        if (org.springframework.util.ObjectUtils.isEmpty(saleDeliveryPlanInfoDTOS) || saleDeliveryPlanInfoDTOS.size() < 0) {
            return null;
        }
        return ObjectCloneUtils
                .convertList(saleDeliveryPlanInfoDTOS,
                             SaleDeliveryPlanInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询发货计划表列表")
    public PageBean<SaleDeliveryPlanInfoResponseDTO> listSaleDeliveryPlanInfosPage(SaleDeliveryPlanInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleDeliveryPlanInfoService
                                         .listSaleDeliveryPlanInfosPage(query.clone(SaleDeliveryPlanInfoQuery.class, CloneDirection.OPPOSITE)),
                                 SaleDeliveryPlanInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除发货计划表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleDeliveryPlanInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增发货计划表")
    public SaleDeliveryPlanInfoResponseDTO insert(@RequestBody SaleDeliveryPlanInfoRequestDTO record) {
        return saleDeliveryPlanInfoService.insert(record.clone(SaleDeliveryPlanInfoDTO.class, CloneDirection.OPPOSITE))
                                          .clone(SaleDeliveryPlanInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询发货计划表")
    public SaleDeliveryPlanInfoResponseDTO selectById(@PathVariable Long id) {
        SaleDeliveryPlanInfoDTO result = saleDeliveryPlanInfoService.selectById(id);
        return result != null ? result.clone(SaleDeliveryPlanInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新发货计划表")
    public Boolean updateById(@PathVariable Long id, @RequestBody SaleDeliveryPlanInfoRequestDTO record) {
        return saleDeliveryPlanInfoService.updateById(id, record.clone(SaleDeliveryPlanInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
