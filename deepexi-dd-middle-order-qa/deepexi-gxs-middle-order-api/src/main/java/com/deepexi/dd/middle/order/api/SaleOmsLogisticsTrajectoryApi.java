package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOmsLogisticsTrajectoryRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SaleOmsLogisticsTrajectoryApi
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
@Api(value = "OMS物流轨迹管理")
@RequestMapping("/saleOmsLogisticsTrajectorys")
public interface SaleOmsLogisticsTrajectoryApi {
    /**
     * 查询OMS物流轨迹列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleOmsLogisticsTrajectoryResponseDTO> listSaleOmsLogisticsTrajectorys(SaleOmsLogisticsTrajectoryRequestQuery query);

    /**
     * 分页查询OMS物流轨迹列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleOmsLogisticsTrajectoryResponseDTO> listSaleOmsLogisticsTrajectorysPage(SaleOmsLogisticsTrajectoryRequestQuery query);


    /**
     * 根据ID删除OMS物流轨迹
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增OMS物流轨迹
     *
     * @param record
     * @return
     */
    @PostMapping("")
    Boolean insert(@RequestBody SaleOmsLogisticsTrajectoryRequestDTO record) throws Exception;

    /**
     * 查询OMS物流轨迹详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleOmsLogisticsTrajectoryResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改OMS物流轨迹
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleOmsLogisticsTrajectoryRequestDTO record);

}