package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SupplerOrderDAO;
import com.deepexi.dd.middle.order.domain.SupplerOrderDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderDTO;
import com.deepexi.dd.middle.order.domain.SupplerOrderQuery;
import com.deepexi.dd.middle.order.service.SupplerOrderService;
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
 * SupplerOrderServiceImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SupplerOrderServiceImpl implements SupplerOrderService {

    @Autowired
    private SupplerOrderDAO supplerOrderDao;

    @Override
    public List<SupplerOrderDTO> listSupplerOrders(SupplerOrderQuery query) {
        return ObjectCloneUtils.convertList(supplerOrderDao.listSupplerOrders(query), SupplerOrderDTO.class);
    }


    @Override
    public PageBean<SupplerOrderDTO> listSupplerOrdersPage(SupplerOrderQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(supplerOrderDao.listSupplerOrders(query)), SupplerOrderDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除已分发订单,传入ID为空");
            return false;
        }
        return supplerOrderDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SupplerOrderDTO insert(SupplerOrderDTO record) {
        if (record == null) {
            log.error("新增已分发订单,传入参数为空");
            return null;
        }
        SupplerOrderDO supplerOrderDo = record.clone(SupplerOrderDO.class);
        DomainUtils.initDomainCreatedInfo(supplerOrderDo);
        if (supplerOrderDao.insert(supplerOrderDo) > 0) {
            record.setId(supplerOrderDo.getId());
            return record;
        }
        log.error("新增已分发订单失败");
        return null;
    }

    @Override
    public SupplerOrderDTO selectById(Long id) {
        if (id == null) {
            log.error("查询已分发订单,传入ID为空");
            return null;
        }
        SupplerOrderDO supplerOrderDo = supplerOrderDao.selectById(id);
        if (supplerOrderDo == null) {
            return null;
        }
        return supplerOrderDo.clone(SupplerOrderDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SupplerOrderDTO record) {
        if (id == null) {
            log.error("修改已分发订单,传入ID为空");
            return false;
        }
        record.setId(id);
        SupplerOrderDO supplerOrderDo = record.clone(SupplerOrderDO.class);
        DomainUtils.initUpdateTimeInfo(supplerOrderDo);
        return supplerOrderDao.updateById(supplerOrderDo);
    }

}