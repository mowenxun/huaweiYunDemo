package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderReturnItemDAO;
import com.deepexi.dd.middle.order.domain.OrderReturnItemDO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnItemQuery;
import com.deepexi.dd.middle.order.service.OrderReturnItemService;
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
 * OrderReturnItemServiceImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderReturnItemServiceImpl implements OrderReturnItemService {

    @Autowired
    private OrderReturnItemDAO orderReturnItemDao;

    @Override
    public List<OrderReturnItemDTO> listOrderReturnItems(OrderReturnItemQuery query) {
        return ObjectCloneUtils.convertList(orderReturnItemDao.listOrderReturnItems(query), OrderReturnItemDTO.class);
    }


    @Override
    public PageBean<OrderReturnItemDTO> listOrderReturnItemsPage(OrderReturnItemQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderReturnItemDao.listOrderReturnItems(query)), OrderReturnItemDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单退货明细表,传入ID为空");
            return false;
        }
        return orderReturnItemDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderReturnItemDTO insert(OrderReturnItemDTO record) {
        if (record == null) {
            log.error("新增销售订单退货明细表,传入参数为空");
            return null;
        }
        OrderReturnItemDO orderReturnItemDo = record.clone(OrderReturnItemDO.class);
        DomainUtils.initDomainCreatedInfo(orderReturnItemDo);
        if (orderReturnItemDao.insert(orderReturnItemDo) > 0) {
            record.setId(orderReturnItemDo.getId());
            return record;
        }
        log.error("新增销售订单退货明细表失败");
        return null;
    }

    @Override
    public OrderReturnItemDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单退货明细表,传入ID为空");
            return null;
        }
        OrderReturnItemDO orderReturnItemDo = orderReturnItemDao.selectById(id);
        if (orderReturnItemDo == null) {
            return null;
        }
        return orderReturnItemDo.clone(OrderReturnItemDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderReturnItemDTO record) {
        if (id == null) {
            log.error("修改销售订单退货明细表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderReturnItemDO orderReturnItemDo = record.clone(OrderReturnItemDO.class);
        DomainUtils.initUpdateTimeInfo(orderReturnItemDo);
        return orderReturnItemDao.updateById(orderReturnItemDo);
    }

}