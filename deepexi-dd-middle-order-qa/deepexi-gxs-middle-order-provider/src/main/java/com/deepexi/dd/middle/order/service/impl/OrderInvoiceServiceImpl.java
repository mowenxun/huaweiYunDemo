package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderInvoiceDAO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceDO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceDTO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceQuery;
import com.deepexi.dd.middle.order.service.OrderInvoiceService;
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
 * OrderInvoiceServiceImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderInvoiceServiceImpl implements OrderInvoiceService {

    @Autowired
    private OrderInvoiceDAO orderInvoiceDao;

    @Override
    public List<OrderInvoiceDTO> listOrderInvoices(OrderInvoiceQuery query) {
        return ObjectCloneUtils.convertList(orderInvoiceDao.listOrderInvoices(query), OrderInvoiceDTO.class);
    }


    @Override
    public PageBean<OrderInvoiceDTO> listOrderInvoicesPage(OrderInvoiceQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderInvoiceDao.listOrderInvoices(query)), OrderInvoiceDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除订单的开票信息表,传入ID为空");
            return false;
        }
        return orderInvoiceDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderInvoiceDTO insert(OrderInvoiceDTO record) {
        if (record == null) {
            log.error("新增订单的开票信息表,传入参数为空");
            return null;
        }
        OrderInvoiceDO orderInvoiceDo = record.clone(OrderInvoiceDO.class);
        DomainUtils.initDomainCreatedInfo(orderInvoiceDo);
        if (orderInvoiceDao.insert(orderInvoiceDo) > 0) {
            record.setId(orderInvoiceDo.getId());
            return record;
        }
        log.error("新增订单的开票信息表失败");
        return null;
    }

    @Override
    public OrderInvoiceDTO selectById(Long id) {
        if (id == null) {
            log.error("查询订单的开票信息表,传入ID为空");
            return null;
        }
        OrderInvoiceDO orderInvoiceDo = orderInvoiceDao.selectById(id);
        if (orderInvoiceDo == null) {
            return null;
        }
        return orderInvoiceDo.clone(OrderInvoiceDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderInvoiceDTO record) {
        if (id == null) {
            log.error("修改订单的开票信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderInvoiceDO orderInvoiceDo = record.clone(OrderInvoiceDO.class);
        DomainUtils.initUpdateTimeInfo(orderInvoiceDo);
        return orderInvoiceDao.updateById(orderInvoiceDo);
    }

}