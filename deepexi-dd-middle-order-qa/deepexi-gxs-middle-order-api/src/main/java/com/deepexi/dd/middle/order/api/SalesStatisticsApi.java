package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.SalesStatisticsResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsRequestQuery;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(value = "销售统计")
@RequestMapping("/middle/v1/salesStatistics")
public interface SalesStatisticsApi {
    @GetMapping("/getStatisticsRecord")
    Payload<List<SalesStatisticsResponseDTO>> getStatisticsRecord(@RequestBody SalesStatisticsRequestQuery query);
}
