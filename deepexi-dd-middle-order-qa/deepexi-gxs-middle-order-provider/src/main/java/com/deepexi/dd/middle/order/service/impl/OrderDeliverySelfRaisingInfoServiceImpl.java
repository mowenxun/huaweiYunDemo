package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderDeliverySelfRaisingInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoDO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoDTO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoQuery;
import com.deepexi.dd.middle.order.service.OrderDeliverySelfRaisingInfoService;
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
 * OrderDeliverySelfRaisingInfoServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 26 16:41:35 CST 2020
 */
@Service
@Slf4j
public class OrderDeliverySelfRaisingInfoServiceImpl implements OrderDeliverySelfRaisingInfoService {

    @Autowired
    private OrderDeliverySelfRaisingInfoDAO orderDeliverySelfRaisingInfoDao;

    @Override
    public List<OrderDeliverySelfRaisingInfoDTO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoQuery query) {
        return ObjectCloneUtils.convertList(orderDeliverySelfRaisingInfoDao.listOrderDeliverySelfRaisingInfos(query), OrderDeliverySelfRaisingInfoDTO.class);
    }

    @Override
    public PageBean<OrderDeliverySelfRaisingInfoDTO> listOrderDeliverySelfRaisingInfosPage(OrderDeliverySelfRaisingInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderDeliverySelfRaisingInfoDao.listOrderDeliverySelfRaisingInfos(query)), OrderDeliverySelfRaisingInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除出库单自提地址信息表,传入ID为空");
            return false;
        }
        return orderDeliverySelfRaisingInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderDeliverySelfRaisingInfoDTO insert(OrderDeliverySelfRaisingInfoDTO record) {
        if (record == null) {
            log.error("新增出库单自提地址信息表,传入参数为空");
            return null;
        }
        OrderDeliverySelfRaisingInfoDO orderDeliverySelfRaisingInfoDo = record.clone(OrderDeliverySelfRaisingInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderDeliverySelfRaisingInfoDo);
        if (orderDeliverySelfRaisingInfoDao.insert(orderDeliverySelfRaisingInfoDo) > 0) {
            record.setId(orderDeliverySelfRaisingInfoDo.getId());
            return record;
        }
        log.error("新增出库单自提地址信息表失败");
        return null;
    }

    @Override
    public OrderDeliverySelfRaisingInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询出库单自提地址信息表,传入ID为空");
            return null;
        }
        OrderDeliverySelfRaisingInfoDO orderDeliverySelfRaisingInfoDo = orderDeliverySelfRaisingInfoDao.selectById(id);
        if (orderDeliverySelfRaisingInfoDo == null) {
            return null;
        }
        return orderDeliverySelfRaisingInfoDo.clone(OrderDeliverySelfRaisingInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderDeliverySelfRaisingInfoDTO record) {
        if (id == null) {
            log.error("修改出库单自提地址信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderDeliverySelfRaisingInfoDO orderDeliverySelfRaisingInfoDo = record.clone(OrderDeliverySelfRaisingInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderDeliverySelfRaisingInfoDo);
        return orderDeliverySelfRaisingInfoDao.updateById(orderDeliverySelfRaisingInfoDo) ;
    }

}