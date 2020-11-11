package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderConsigneeAddressDAO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressDO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressDTO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressQuery;
import com.deepexi.dd.middle.order.service.OrderConsigneeAddressService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * OrderConsigneeAddressServiceImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderConsigneeAddressServiceImpl implements OrderConsigneeAddressService {

    @Autowired
    private OrderConsigneeAddressDAO orderConsigneeAddressDao;

    @Override
    public List<OrderConsigneeAddressDTO> listOrderConsigneeAddresss(OrderConsigneeAddressQuery query) {
        return ObjectCloneUtils.convertList(orderConsigneeAddressDao.listOrderConsigneeAddresss(query), OrderConsigneeAddressDTO.class);
    }


    @Override
    public PageBean<OrderConsigneeAddressDTO> listOrderConsigneeAddresssPage(OrderConsigneeAddressQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderConsigneeAddressDao.listOrderConsigneeAddresss(query)), OrderConsigneeAddressDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除订单收货地址信息表,传入ID为空");
            return false;
        }
        return orderConsigneeAddressDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderConsigneeAddressDTO insert(OrderConsigneeAddressDTO record) {
        if (record == null) {
            log.error("新增订单收货地址信息表,传入参数为空");
            return null;
        }
        OrderConsigneeAddressDO orderConsigneeAddressDo = record.clone(OrderConsigneeAddressDO.class);
        DomainUtils.initDomainCreatedInfo(orderConsigneeAddressDo);
        if (orderConsigneeAddressDao.insert(orderConsigneeAddressDo) > 0) {
            record.setId(orderConsigneeAddressDo.getId());
            return record;
        }
        log.error("新增订单收货地址信息表失败");
        return null;
    }

    @Override
    public OrderConsigneeAddressDTO selectById(Long id) {
        if (id == null) {
            log.error("查询订单收货地址信息表,传入ID为空");
            return null;
        }
        OrderConsigneeAddressDO orderConsigneeAddressDo = orderConsigneeAddressDao.selectById(id);
        if (orderConsigneeAddressDo == null) {
            return null;
        }
        return orderConsigneeAddressDo.clone(OrderConsigneeAddressDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderConsigneeAddressDTO record) {
        if (id == null) {
            log.error("修改订单收货地址信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderConsigneeAddressDO orderConsigneeAddressDo = record.clone(OrderConsigneeAddressDO.class);
        DomainUtils.initUpdateTimeInfo(orderConsigneeAddressDo);
        return orderConsigneeAddressDao.updateById(orderConsigneeAddressDo);
    }

}