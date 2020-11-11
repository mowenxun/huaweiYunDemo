package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryDO;
import com.deepexi.dd.middle.order.domain.SaleOmsLogisticsTrajectoryQuery;

import java.util.List;


/**
 * SaleOmsLogisticsTrajectoryDAO
 *
 * @author admin
 * @date Tue Aug 25 16:23:34 CST 2020
 * @version 1.0
 */
public interface SaleOmsLogisticsTrajectoryDAO  extends IService<SaleOmsLogisticsTrajectoryDO> {

    /**
     * 查询OMS物流轨迹列表}
     *
     * @return
     */
    List<SaleOmsLogisticsTrajectoryDO> listSaleOmsLogisticsTrajectorys(SaleOmsLogisticsTrajectoryQuery query);

    /**
     * 根据ID删除OMS物流轨迹
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增OMS物流轨迹
     *
     * @param record
     * @return
     */
    int insert(SaleOmsLogisticsTrajectoryDO record);

    /**
     * 查询OMS物流轨迹详情
     *
     * @param id
     * @return
     */
    SaleOmsLogisticsTrajectoryDO selectById(Long id);

    /**
     * 根据ID修改OMS物流轨迹
     *
     * @param list
     * @return
     */
    Boolean updateBatch(List<SaleOmsLogisticsTrajectoryDO> list);

}