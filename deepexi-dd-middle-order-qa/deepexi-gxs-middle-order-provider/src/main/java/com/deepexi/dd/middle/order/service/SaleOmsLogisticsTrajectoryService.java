package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDTO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryRequestDTO;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleOmsLogisticsTrajectoryService
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
public interface SaleOmsLogisticsTrajectoryService {


    /**
     * 查询OMS物流轨迹列表
     *
     * @return
     */
    List<SaleOmsLogisticsTrajectoryDTO> listSaleOmsLogisticsTrajectorys(SaleOmsLogisticsTrajectoryQuery query);

    /**
     * 分页查询OMS物流轨迹列表
     *
     * @return
     */
    PageBean<SaleOmsLogisticsTrajectoryDTO> listSaleOmsLogisticsTrajectorysPage(SaleOmsLogisticsTrajectoryQuery query);

    /**
     * 根据ID删除OMS物流轨迹
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增OMS物流轨迹
     *
     * @param record
     * @return
     */
    Boolean insert(SaleOmsLogisticsTrajectoryRequestDTO record) throws Exception;

    /**
     * 查询OMS物流轨迹详情
     *
     * @param id
     * @return
     */
    SaleOmsLogisticsTrajectoryDTO selectById(Long id);


    /**
     * 根据ID修改OMS物流轨迹
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOmsLogisticsTrajectoryDTO record);

}