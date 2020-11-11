package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SalesStatisticsDO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsQuery;

import java.util.List;

public interface SalesStatisticsService {
    List<SalesStatisticsDO> getStatisticsRecord(SalesStatisticsQuery query);
}
