package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.system.mall.domain.dto.LogisticsInfoUpdateRequest;
import com.deepexi.dd.system.mall.domain.dto.OrderItemUpdateRequest;

/**
 * @ClassName SaleOrderItemLogisticsService
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
public interface SaleOrderItemLogisticsService {
    /**
     * @Description:  oms物流信息.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    Boolean orderItemUpdate(OrderItemUpdateRequest dto) throws Exception;
    /**
     * @Description:  新加oms物流轨迹.
     * @Param: [record]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    Boolean logisticsInfoUpdate(LogisticsInfoUpdateRequest record) throws Exception;
}
