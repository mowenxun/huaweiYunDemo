package com.deepexi.dd.middle.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SalesStatisticsDO;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesStatisticsMapper extends BaseMapper<SalesStatisticsDO> {
    /**
     * 查询销售统计记录
     *
     * @param query
     * @return
     */
    List<SalesStatisticsDO> getStatisticsRecord(SalesStatisticsQuery query);
}
