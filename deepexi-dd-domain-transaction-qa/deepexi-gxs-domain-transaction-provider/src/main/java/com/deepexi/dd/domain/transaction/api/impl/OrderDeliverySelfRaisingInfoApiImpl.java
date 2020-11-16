package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.OrderDeliverySelfRaisingInfoApi;
import com.deepexi.dd.domain.transaction.domain.dto.OrderDeliverySelfRaisingInfoReqDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderDeliverySelfRaisingInfoRespDTO;
import com.deepexi.dd.domain.transaction.domain.query.OrderDeliverySelfRaisingInfoReqQuery;
import com.deepexi.dd.domain.transaction.service.OrderDeliverySelfRaisingInfoService;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliverySelfRaisingInfoRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SaleOrderInfoService
 *
 * @author admin
 * @date Tue Jun 30 11:43:59 CST 2020
 * @version 1.0
 */
@RestController
public class OrderDeliverySelfRaisingInfoApiImpl implements OrderDeliverySelfRaisingInfoApi {

    @Autowired
    private OrderDeliverySelfRaisingInfoService orderDeliverySelfRaisingInfoService;

    @Override
    public Payload<List<OrderDeliverySelfRaisingInfoRespDTO>> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoReqQuery query) throws Exception {
        List<OrderDeliverySelfRaisingInfoResponseDTO> responseDTOS = orderDeliverySelfRaisingInfoService.listOrderDeliverySelfRaisingInfos(query.clone(OrderDeliverySelfRaisingInfoRequestQuery.class));
        return new Payload<>(GeneralConvertUtils.convert2List(responseDTOS, OrderDeliverySelfRaisingInfoRespDTO.class));
    }

    @Override
    public Payload<PageBean<OrderDeliverySelfRaisingInfoRespDTO>> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoReqQuery query) throws Exception {
        PageBean<OrderDeliverySelfRaisingInfoResponseDTO> responseDTOPageBean = orderDeliverySelfRaisingInfoService.listOrderDeliverySelfRaisingInfosPage(query.clone(OrderDeliverySelfRaisingInfoRequestQuery.class));
        return new Payload<>(GeneralConvertUtils.convert2PageBean(responseDTOPageBean, OrderDeliverySelfRaisingInfoRespDTO.class));
    }

    @Override
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) throws Exception {
        return new Payload<>(orderDeliverySelfRaisingInfoService.deleteByIdIn(id));
    }

    @Override
    public Payload<OrderDeliverySelfRaisingInfoRespDTO> insert(@RequestBody OrderDeliverySelfRaisingInfoReqDTO record) throws Exception {
        OrderDeliverySelfRaisingInfoResponseDTO result = orderDeliverySelfRaisingInfoService.insert(record.clone(OrderDeliverySelfRaisingInfoRequestDTO.class));
        return new Payload<>(GeneralConvertUtils.conv(result, OrderDeliverySelfRaisingInfoRespDTO.class));
    }

    @Override
    public Payload<OrderDeliverySelfRaisingInfoRespDTO> selectById(@PathVariable Long id) throws Exception {
        OrderDeliverySelfRaisingInfoResponseDTO result = orderDeliverySelfRaisingInfoService.selectById(id);
        return new Payload<>(GeneralConvertUtils.conv(result, OrderDeliverySelfRaisingInfoRespDTO.class));
    }

    @Override
    public Payload<Boolean> updateById(@PathVariable Long id, @RequestBody OrderDeliverySelfRaisingInfoReqDTO record) throws Exception {
        return new Payload<>(orderDeliverySelfRaisingInfoService.updateById(id, record.clone(OrderDeliverySelfRaisingInfoRequestDTO.class)));
    }
}
