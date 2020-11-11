package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.OrderOperationDAO;
import com.deepexi.dd.middle.order.dao.OrderStatusOperationDAO;
import com.deepexi.dd.middle.order.domain.OrderOperationDO;
import com.deepexi.dd.middle.order.domain.OrderStatusOperationDO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderStatusOperationDTO;
import com.deepexi.dd.middle.order.domain.query.OrderStatusOperationQuery;
import com.deepexi.dd.middle.order.service.OrderStatusOperationService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * OrderStatusOperationServiceImpl
 *
 * @author admin
 * @date Mon Jul 13 19:13:18 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderStatusOperationServiceImpl implements OrderStatusOperationService {

    @Autowired
    private OrderStatusOperationDAO orderStatusOperationDao;

    @Autowired
    private OrderOperationDAO orderOperationDAO;

    @Override
    public List<OrderStatusOperationDTO> listOrderStatusOperations(OrderStatusOperationQuery query) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("status_identity",query.getStatuses());
        queryWrapper.eq("portal",query.getPortal());
        List<OrderStatusOperationDTO> result = ObjectCloneUtils.convertList(orderStatusOperationDao.list(queryWrapper), OrderStatusOperationDTO.class);

        List<Long> operationIds = result.stream().map(OrderStatusOperationDTO::getOperationId).collect(Collectors.toList());

        QueryWrapper operationQuery = new QueryWrapper();
        operationQuery.in("id",operationIds);
        List<OrderOperationDO> orderOperationDOS = orderOperationDAO.list(operationQuery);

        //操作id 对应 操作对象
        Map<Long, OrderOperationResponseDTO> orderOperationResponseDTOMap = orderOperationDOS.stream().map(e->e.clone(OrderOperationResponseDTO.class)).collect(Collectors.toMap(OrderOperationResponseDTO::getId, e->e));

        result.forEach(i->{
            i.setOperationDTO(orderOperationResponseDTOMap.get(i.getOperationId()));
        });

        return result;
    }


    @Override
    public PageBean<OrderStatusOperationDTO> listOrderStatusOperationsPage(OrderStatusOperationQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderStatusOperationDao.listOrderStatusOperations(query)), OrderStatusOperationDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除订单 状态-操作表,传入ID为空");
            return false;
        }
        return orderStatusOperationDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderStatusOperationDTO insert(OrderStatusOperationDTO record) {
        if (record == null) {
            log.error("新增订单 状态-操作表,传入参数为空");
            return null;
        }
        OrderStatusOperationDO orderStatusOperationDo = record.clone(OrderStatusOperationDO.class);
        DomainUtils.initDomainCreatedInfo(orderStatusOperationDo);
        if (orderStatusOperationDao.insert(orderStatusOperationDo) > 0) {
            record.setId(orderStatusOperationDo.getId());
            return record;
        }
        log.error("新增订单 状态-操作表失败");
        return null;
    }

    @Override
    public OrderStatusOperationDTO selectById(Long id) {
        if (id == null) {
            log.error("查询订单 状态-操作表,传入ID为空");
            return null;
        }
        OrderStatusOperationDO orderStatusOperationDo = orderStatusOperationDao.selectById(id);
        if (orderStatusOperationDo == null) {
            return null;
        }
        return orderStatusOperationDo.clone(OrderStatusOperationDTO.class);
    }

}