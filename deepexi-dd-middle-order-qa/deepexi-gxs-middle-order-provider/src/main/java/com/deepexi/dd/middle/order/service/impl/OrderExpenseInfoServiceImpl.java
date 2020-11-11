package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderExpenseInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderExpenseInfoDO;
import com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoQuery;
import com.deepexi.dd.middle.order.service.OrderExpenseInfoService;
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
 * OrderExpenseInfoServiceImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderExpenseInfoServiceImpl implements OrderExpenseInfoService {

    @Autowired
    private OrderExpenseInfoDAO orderExpenseInfoDao;

    @Override
    public List<OrderExpenseInfoDTO> listOrderExpenseInfos(OrderExpenseInfoQuery query) {
        return ObjectCloneUtils.convertList(orderExpenseInfoDao.listOrderExpenseInfos(query), OrderExpenseInfoDTO.class);
    }


    @Override
    public PageBean<OrderExpenseInfoDTO> listOrderExpenseInfosPage(OrderExpenseInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderExpenseInfoDao.listOrderExpenseInfos(query)), OrderExpenseInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单的费用信息表,传入ID为空");
            return false;
        }
        return orderExpenseInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderExpenseInfoDTO insert(OrderExpenseInfoDTO record) {
        if (record == null) {
            log.error("新增销售订单的费用信息表,传入参数为空");
            return null;
        }
        OrderExpenseInfoDO orderExpenseInfoDo = record.clone(OrderExpenseInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderExpenseInfoDo);
        if (orderExpenseInfoDao.insert(orderExpenseInfoDo) > 0) {
            record.setId(orderExpenseInfoDo.getId());
            return record;
        }
        log.error("新增销售订单的费用信息表失败");
        return null;
    }

    @Override
    public OrderExpenseInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单的费用信息表,传入ID为空");
            return null;
        }
        OrderExpenseInfoDO orderExpenseInfoDo = orderExpenseInfoDao.selectById(id);
        if (orderExpenseInfoDo == null) {
            return null;
        }
        return orderExpenseInfoDo.clone(OrderExpenseInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderExpenseInfoDTO record) {
        if (id == null) {
            log.error("修改销售订单的费用信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderExpenseInfoDO orderExpenseInfoDo = record.clone(OrderExpenseInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderExpenseInfoDo);
        return orderExpenseInfoDao.updateById(orderExpenseInfoDo);
    }

}