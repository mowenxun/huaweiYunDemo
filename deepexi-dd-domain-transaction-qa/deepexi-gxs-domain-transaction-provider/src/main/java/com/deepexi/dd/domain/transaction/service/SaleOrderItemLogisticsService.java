package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.OrderItemUpdateRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOmsLogisticsTrajectoryInfoRequestDTO;

/**
 * @ClassName SaleOrderItemLogisticsService
 * @Description OMS物流信息
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
public interface SaleOrderItemLogisticsService {
    /**
     * @Description:  OMS同步发货状态、物流基础信息至B端.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    Boolean orderItemUpdate(OrderItemUpdateRequestDTO dto) throws Exception;
    /**
     * @Description:  新加oms物流轨迹.
     * @Param: [record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    Boolean logisticsInfoUpdate(SaleOmsLogisticsTrajectoryInfoRequestDTO dto) throws Exception;
}
