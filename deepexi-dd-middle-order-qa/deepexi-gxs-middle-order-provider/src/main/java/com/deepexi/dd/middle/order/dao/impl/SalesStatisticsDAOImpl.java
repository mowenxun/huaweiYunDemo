package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SalesStatisticsDAO;
import com.deepexi.dd.middle.order.domain.SalesStatisticsDO;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsQuery;
import com.deepexi.dd.middle.order.mapper.SalesStatisticsMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesStatisticsDAOImpl extends ServiceImpl<SalesStatisticsMapper, SalesStatisticsDO> implements SalesStatisticsDAO{
        @Override
    public List<SalesStatisticsDO> getStatisticsRecord(SalesStatisticsQuery query) {
        return baseMapper.getStatisticsRecord(query);
    }
}
