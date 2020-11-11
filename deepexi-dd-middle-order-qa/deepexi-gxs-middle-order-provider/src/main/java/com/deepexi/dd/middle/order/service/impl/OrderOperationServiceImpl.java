package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderOperationDAO;
import com.deepexi.dd.middle.order.domain.OrderOperationDO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationQuery;
import com.deepexi.dd.middle.order.service.OrderOperationService;
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
 * OrderOperationServiceImpl
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderOperationServiceImpl implements OrderOperationService {

    @Autowired
    private OrderOperationDAO orderOperationDao;

    @Override
    public List<OrderOperationDTO> listOrderOperations(OrderOperationQuery query) {
        return ObjectCloneUtils.convertList(orderOperationDao.listOrderOperations(query), OrderOperationDTO.class);
    }


    @Override
    public PageBean<OrderOperationDTO> listOrderOperationsPage(OrderOperationQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderOperationDao.listOrderOperations(query)), OrderOperationDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除按钮表,传入ID为空");
            return false;
        }
        return orderOperationDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderOperationDTO insert(OrderOperationDTO record) {
        if (record == null) {
            log.error("新增按钮表,传入参数为空");
            return null;
        }
        OrderOperationDO orderOperationDo = record.clone(OrderOperationDO.class);
        DomainUtils.initDomainCreatedInfo(orderOperationDo);
        if (orderOperationDao.insert(orderOperationDo) > 0) {
            record.setId(orderOperationDo.getId());
            return record;
        }
        log.error("新增按钮表失败");
        return null;
    }

    @Override
    public OrderOperationDTO selectById(Long id) {
        if (id == null) {
            log.error("查询按钮表,传入ID为空");
            return null;
        }
        OrderOperationDO orderOperationDo = orderOperationDao.selectById(id);
        if (orderOperationDo == null) {
            return null;
        }
        return orderOperationDo.clone(OrderOperationDTO.class);
    }
}