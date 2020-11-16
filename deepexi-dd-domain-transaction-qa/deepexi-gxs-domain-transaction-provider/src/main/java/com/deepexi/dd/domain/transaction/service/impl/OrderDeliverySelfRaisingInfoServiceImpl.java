package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.transaction.remote.order.OrderDeliverySelfRaisingInfoClient;
import com.deepexi.dd.domain.transaction.service.OrderDeliverySelfRaisingInfoService;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliverySelfRaisingInfoRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OrderDeliverySelfRaisingInfoServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 26 16:41:35 CST 2020
 */
@Service
@Slf4j
public class OrderDeliverySelfRaisingInfoServiceImpl implements OrderDeliverySelfRaisingInfoService {

    @Autowired
    private OrderDeliverySelfRaisingInfoClient orderDeliverySelfRaisingInfoClient;

    @Override
    public List<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoRequestQuery query) {
        return orderDeliverySelfRaisingInfoClient.listOrderDeliverySelfRaisingInfos(query);
    }

    @Override
    public PageBean<OrderDeliverySelfRaisingInfoResponseDTO> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoRequestQuery query) {
        return orderDeliverySelfRaisingInfoClient.listOrderDeliverySelfRaisingInfosPage(query);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) throws Exception {
        return orderDeliverySelfRaisingInfoClient.deleteByIdIn(id);
    }

    @Override
    public OrderDeliverySelfRaisingInfoResponseDTO insert(OrderDeliverySelfRaisingInfoRequestDTO record) {
        return orderDeliverySelfRaisingInfoClient.insert(record);
    }

    @Override
    public OrderDeliverySelfRaisingInfoResponseDTO selectById(Long id) {
        return orderDeliverySelfRaisingInfoClient.selectById(id);
    }

    @Override
    public Boolean updateById(Long id, OrderDeliverySelfRaisingInfoRequestDTO record) {
        return orderDeliverySelfRaisingInfoClient.updateById(record.getId(), record);
    }
}