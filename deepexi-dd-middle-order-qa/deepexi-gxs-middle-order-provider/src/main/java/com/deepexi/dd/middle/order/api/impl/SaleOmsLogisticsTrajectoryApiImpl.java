package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOmsLogisticsTrajectoryApi;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDTO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOmsLogisticsTrajectoryRequestQuery;
import com.deepexi.dd.middle.order.service.SaleOmsLogisticsTrajectoryService;
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
 * SaleOmsLogisticsTrajectoryApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOmsLogisticsTrajectoryApiImpl implements SaleOmsLogisticsTrajectoryApi {

    @Autowired
    private SaleOmsLogisticsTrajectoryService saleOmsLogisticsTrajectoryService;

    @Override
    @ApiOperation("查询OMS物流轨迹列表")
    public List<SaleOmsLogisticsTrajectoryResponseDTO> listSaleOmsLogisticsTrajectorys(SaleOmsLogisticsTrajectoryRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleOmsLogisticsTrajectoryService
                    .listSaleOmsLogisticsTrajectorys(query.clone(SaleOmsLogisticsTrajectoryQuery.class, CloneDirection.OPPOSITE)),
                                    SaleOmsLogisticsTrajectoryResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询OMS物流轨迹列表")
    public PageBean<SaleOmsLogisticsTrajectoryResponseDTO> listSaleOmsLogisticsTrajectorysPage(SaleOmsLogisticsTrajectoryRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleOmsLogisticsTrajectoryService
                    .listSaleOmsLogisticsTrajectorysPage(query.clone(SaleOmsLogisticsTrajectoryQuery.class, CloneDirection.OPPOSITE)),
                        SaleOmsLogisticsTrajectoryResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除OMS物流轨迹")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleOmsLogisticsTrajectoryService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增OMS物流轨迹")
    public Boolean insert(@RequestBody SaleOmsLogisticsTrajectoryRequestDTO record) throws Exception {
        return saleOmsLogisticsTrajectoryService.insert(record);
    }

    @Override
    @ApiOperation("根据ID查询OMS物流轨迹")
    public SaleOmsLogisticsTrajectoryResponseDTO selectById(@PathVariable Long id) {
        SaleOmsLogisticsTrajectoryDTO result = saleOmsLogisticsTrajectoryService.selectById(id);
        return result != null ? result.clone(SaleOmsLogisticsTrajectoryResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新OMS物流轨迹")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleOmsLogisticsTrajectoryRequestDTO record) {
        return saleOmsLogisticsTrajectoryService.updateById(id, record.clone(SaleOmsLogisticsTrajectoryDTO.class, CloneDirection.OPPOSITE));
    }
}
