package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.OrderItemUpdateRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOmsLogisticsTrajectoryInfoRequestDTO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SaleOrderItemLogisticsApi
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Api(value = "oms物流信息rpc接口")
@RequestMapping("/open-api/v1/domain/saleOrderItemLogisticsApi")
public interface SaleOrderItemLogisticsApi {
    /**
     * @Description:  OMS同步发货状态、物流基础信息至B端.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    @PostMapping("/orderItemUpdate")
    Payload<Boolean> orderItemUpdate(@RequestBody OrderItemUpdateRequestDTO dto) throws Exception;
    /**
     * @Description:  新加oms物流轨迹.
     * @Param: [record]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    @PostMapping("logisticsInfoUpdate")
    Payload<Boolean> logisticsInfoUpdate(@RequestBody SaleOmsLogisticsTrajectoryInfoRequestDTO record) throws Exception;
}
