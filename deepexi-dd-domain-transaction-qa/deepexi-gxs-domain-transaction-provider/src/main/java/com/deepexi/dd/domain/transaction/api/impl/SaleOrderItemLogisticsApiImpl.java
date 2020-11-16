package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.transaction.api.SaleOrderItemLogisticsApi;
import com.deepexi.dd.domain.transaction.domain.dto.OrderItemUpdateRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOmsLogisticsTrajectoryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.service.SaleOrderItemLogisticsService;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryRequestDTO;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SaleOrderItemLogisticsApiImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@RestController
public class SaleOrderItemLogisticsApiImpl implements SaleOrderItemLogisticsApi {

    @Autowired
    private SaleOrderItemLogisticsService saleOrderItemLogisticsService;

    /**
     * @param dto
     * @Description: OMS同步发货状态、物流基础信息至B端.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    @Override
    public Payload<Boolean> orderItemUpdate(@RequestBody OrderItemUpdateRequestDTO dto) throws Exception {
        return new Payload<>(saleOrderItemLogisticsService.orderItemUpdate(dto));
    }

    /**
     * @param record
     * @Description: 新加oms物流轨迹.
     * @Param: [record]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    @Override
    public Payload<Boolean> logisticsInfoUpdate(@RequestBody SaleOmsLogisticsTrajectoryInfoRequestDTO record) throws Exception {
        return new Payload<>(saleOrderItemLogisticsService.logisticsInfoUpdate(record));
    }
}
