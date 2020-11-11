package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderInvoiceInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceInfoDO;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoQuery;
import com.deepexi.dd.middle.order.service.OrderInvoiceInfoService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * OrderInvoiceInfoServiceImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderInvoiceInfoServiceImpl implements OrderInvoiceInfoService {

    @Autowired
    private OrderInvoiceInfoDAO orderInvoiceInfoDao;

    @Override
    public List<OrderInvoiceInfoDTO> listOrderInvoiceInfos(OrderInvoiceInfoQuery query) {
        return ObjectCloneUtils.convertList(orderInvoiceInfoDao.listOrderInvoiceInfos(query), OrderInvoiceInfoDTO.class);
    }


    @Override
    public PageBean<OrderInvoiceInfoDTO> listOrderInvoiceInfosPage(OrderInvoiceInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderInvoiceInfoDao.listOrderInvoiceInfos(query)), OrderInvoiceInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除订单的开票信息表,传入ID为空");
            return false;
        }
        return orderInvoiceInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderInvoiceInfoDTO insert(OrderInvoiceInfoDTO record) {
        if (record == null) {
            log.error("新增订单的开票信息表,传入参数为空");
            return null;
        }
        OrderInvoiceInfoDO orderInvoiceInfoDo = record.clone(OrderInvoiceInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderInvoiceInfoDo);
        if (orderInvoiceInfoDao.insert(orderInvoiceInfoDo) > 0) {
            record.setId(orderInvoiceInfoDo.getId());
            return record;
        }
        log.error("新增订单的开票信息表失败");
        return null;
    }

    @Override
    public OrderInvoiceInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询订单的开票信息表,传入ID为空");
            return null;
        }
        OrderInvoiceInfoDO orderInvoiceInfoDo = orderInvoiceInfoDao.selectById(id);
        if (orderInvoiceInfoDo == null) {
            return null;
        }
        return orderInvoiceInfoDo.clone(OrderInvoiceInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderInvoiceInfoDTO record) {
        if (id == null) {
            log.error("修改订单的开票信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderInvoiceInfoDO orderInvoiceInfoDo = record.clone(OrderInvoiceInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderInvoiceInfoDo);
        return orderInvoiceInfoDao.updateById(orderInvoiceInfoDo);
    }

}