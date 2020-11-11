package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderPayBackDAO;
import com.deepexi.dd.middle.order.domain.OrderPayBackDO;
import com.deepexi.dd.middle.order.domain.OrderPayBackDTO;
import com.deepexi.dd.middle.order.domain.OrderPayBackQuery;
import com.deepexi.dd.middle.order.service.OrderPayBackService;
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
 * OrderPayBackServiceImpl
 *
 * @author admin
 * @date Wed Jul 22 16:27:51 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderPayBackServiceImpl implements OrderPayBackService {

    @Autowired
    private OrderPayBackDAO orderPayBackDao;

    @Override
    public List<OrderPayBackDTO> listOrderPayBacks(OrderPayBackQuery query) {
        return ObjectCloneUtils.convertList(orderPayBackDao.listOrderPayBacks(query), OrderPayBackDTO.class);
    }


    @Override
    public PageBean<OrderPayBackDTO> listOrderPayBacksPage(OrderPayBackQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderPayBackDao.listOrderPayBacks(query)), OrderPayBackDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除支付回调参数表,传入ID为空");
            return false;
        }
        return orderPayBackDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderPayBackDTO insert(OrderPayBackDTO record) {
        if (record == null) {
            log.error("新增支付回调参数表,传入参数为空");
            return null;
        }
        OrderPayBackDO orderPayBackDo = record.clone(OrderPayBackDO.class);
        DomainUtils.initDomainCreatedInfo(orderPayBackDo);
        if (orderPayBackDao.insert(orderPayBackDo) > 0) {
            record.setId(orderPayBackDo.getId());
            return record;
        }
        log.error("新增支付回调参数表失败");
        return null;
    }

    @Override
    public OrderPayBackDTO selectById(Long id) {
        if (id == null) {
            log.error("查询支付回调参数表,传入ID为空");
            return null;
        }
        OrderPayBackDO orderPayBackDo = orderPayBackDao.selectById(id);
        if (orderPayBackDo == null) {
            return null;
        }
        return orderPayBackDo.clone(OrderPayBackDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderPayBackDTO record) {
        if (id == null) {
            log.error("修改支付回调参数表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderPayBackDO orderPayBackDo = record.clone(OrderPayBackDO.class);
        DomainUtils.initUpdateTimeInfo(orderPayBackDo);
        return orderPayBackDao.updateById(orderPayBackDo) > 0;
    }

}