package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SalesStatisticsDAO;
import com.deepexi.dd.middle.order.domain.SalesStatisticsDO;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsQuery;
import com.deepexi.dd.middle.order.service.SalesStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SalesStatisticsServiceImpl implements SalesStatisticsService {

    @Autowired
    private SalesStatisticsDAO salesStatisticsDAO;

    @Override
    public List<SalesStatisticsDO> getStatisticsRecord(SalesStatisticsQuery query) {
        return salesStatisticsDAO.getStatisticsRecord(query);
    }
}
