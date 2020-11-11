package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderStatusDAO;
import com.deepexi.dd.middle.order.domain.OrderStatusDO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusQuery;
import com.deepexi.dd.middle.order.service.OrderStatusService;
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
 * OrderStatusServiceImpl
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusDAO orderStatusDao;

    @Override
    public List<OrderStatusDTO> listOrderStatuss(OrderStatusQuery query) {
        return ObjectCloneUtils.convertList(orderStatusDao.listOrderStatuss(query), OrderStatusDTO.class);
    }


    @Override
    public PageBean<OrderStatusDTO> listOrderStatussPage(OrderStatusQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderStatusDao.listOrderStatuss(query)), OrderStatusDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除状态表,传入ID为空");
            return false;
        }
        return orderStatusDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderStatusDTO insert(OrderStatusDTO record) {
        if (record == null) {
            log.error("新增状态表,传入参数为空");
            return null;
        }
        OrderStatusDO orderStatusDo = record.clone(OrderStatusDO.class);
        DomainUtils.initDomainCreatedInfo(orderStatusDo);
        if (orderStatusDao.insert(orderStatusDo) > 0) {
            record.setId(orderStatusDo.getId());
            return record;
        }
        log.error("新增状态表失败");
        return null;
    }

    @Override
    public OrderStatusDTO selectById(Long id) {
        if (id == null) {
            log.error("查询状态表,传入ID为空");
            return null;
        }
        OrderStatusDO orderStatusDo = orderStatusDao.selectById(id);
        if (orderStatusDo == null) {
            return null;
        }
        return orderStatusDo.clone(OrderStatusDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderStatusDTO record) {
        if (id == null) {
            log.error("修改状态表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderStatusDO orderStatusDo = record.clone(OrderStatusDO.class);
        DomainUtils.initUpdateTimeInfo(orderStatusDo);
        return orderStatusDao.updateById(orderStatusDo) > 0;
    }

}