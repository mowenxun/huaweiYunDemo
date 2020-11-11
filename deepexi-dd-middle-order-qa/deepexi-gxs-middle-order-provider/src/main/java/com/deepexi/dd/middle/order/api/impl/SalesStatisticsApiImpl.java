package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SalesStatisticsApi;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalesStatisticsResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsQuery;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsRequestQuery;
import com.deepexi.dd.middle.order.service.SalesStatisticsService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalesStatisticsApiImpl implements SalesStatisticsApi {

    @Autowired
    private SalesStatisticsService salesStatisticsService;

    @Override
    public Payload<List<SalesStatisticsResponseDTO>> getStatisticsRecord(SalesStatisticsRequestQuery query) {
        List<SalesStatisticsResponseDTO> list = ObjectCloneUtils
                .convertList(salesStatisticsService
                                .getStatisticsRecord(query.clone(SalesStatisticsQuery.class, CloneDirection.OPPOSITE)),
                        SalesStatisticsResponseDTO.class, CloneDirection.OPPOSITE);
        return new Payload<>(list);
    }
}
