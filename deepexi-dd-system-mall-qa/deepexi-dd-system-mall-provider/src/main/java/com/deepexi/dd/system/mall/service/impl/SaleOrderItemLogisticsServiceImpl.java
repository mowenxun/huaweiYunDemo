package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.transaction.domain.dto.OrderItemUpdateRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOmsLogisticsTrajectoryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOmsLogisticsTrajectoryMessageInfoRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.LogisticsInfoUpdateRequest;
import com.deepexi.dd.system.mall.domain.dto.OrderItemUpdateRequest;
import com.deepexi.dd.system.mall.domain.dto.TrackMessageOut;
import com.deepexi.dd.system.mall.remote.order.SaleOrderItemLogisticsRemote;
import com.deepexi.dd.system.mall.service.SaleOrderItemLogisticsService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import jdk.nashorn.internal.codegen.types.BooleanType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SaleOrderItemLogisticsServiceImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Slf4j
@Service
public class SaleOrderItemLogisticsServiceImpl implements SaleOrderItemLogisticsService {

    @Autowired
    private SaleOrderItemLogisticsRemote saleOrderItemLogisticsRemote;

    /**
     * @param dto
     * @Description: oms物流信息.
     * @Param: [dto]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    @Override
    public Boolean orderItemUpdate(OrderItemUpdateRequest dto) throws Exception {
        Payload<Boolean> payload = saleOrderItemLogisticsRemote.orderItemUpdate(dto.clone(OrderItemUpdateRequestDTO.class));
        return payload.getPayload();
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
    public Boolean logisticsInfoUpdate(LogisticsInfoUpdateRequest record) throws Exception {
        SaleOmsLogisticsTrajectoryInfoRequestDTO requestDTO = record.clone(SaleOmsLogisticsTrajectoryInfoRequestDTO.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SaleOmsLogisticsTrajectoryMessageInfoRequestDTO> list = record.getTrackMessage().stream().map(map -> {
            SaleOmsLogisticsTrajectoryMessageInfoRequestDTO dto = new SaleOmsLogisticsTrajectoryMessageInfoRequestDTO();
            try {
                Date date = sdf.parse(map.getTime());
                dto.setTime(date);
                dto.setMessage(map.getMessage());
            } catch (ParseException e) {
                throw new ApplicationException("时间格式错误.");
            }
            return dto;
        }).collect(Collectors.toList());
        requestDTO.setTrackMessage(list);
        Payload<Boolean> payload = saleOrderItemLogisticsRemote.logisticsInfoUpdate(requestDTO);
        return payload.getPayload();
    }
}
