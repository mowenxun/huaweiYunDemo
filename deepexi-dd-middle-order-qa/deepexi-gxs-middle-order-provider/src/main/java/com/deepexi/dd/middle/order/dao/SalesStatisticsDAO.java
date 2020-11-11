package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.SalesStatisticsDO;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsQuery;

import java.util.List;

public interface SalesStatisticsDAO {
    /**
     * 查询销售统计记录
     *
     * @param query
     * @return
     */
    List<SalesStatisticsDO> getStatisticsRecord(SalesStatisticsQuery query);
}
