package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderCouponInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderCouponInfoDO;
import com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoQuery;
import com.deepexi.dd.middle.order.service.OrderCouponInfoService;
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
 * OrderCouponInfoServiceImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderCouponInfoServiceImpl implements OrderCouponInfoService {

    @Autowired
    private OrderCouponInfoDAO orderCouponInfoDao;

    @Override
    public List<OrderCouponInfoDTO> listOrderCouponInfos(OrderCouponInfoQuery query) {
        return ObjectCloneUtils.convertList(orderCouponInfoDao.listOrderCouponInfos(query), OrderCouponInfoDTO.class);
    }


    @Override
    public PageBean<OrderCouponInfoDTO> listOrderCouponInfosPage(OrderCouponInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderCouponInfoDao.listOrderCouponInfos(query)), OrderCouponInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除优惠信息表,传入ID为空");
            return false;
        }
        return orderCouponInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderCouponInfoDTO insert(OrderCouponInfoDTO record) {
        if (record == null) {
            log.error("新增优惠信息表,传入参数为空");
            return null;
        }
        OrderCouponInfoDO orderCouponInfoDo = record.clone(OrderCouponInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderCouponInfoDo);
        if (orderCouponInfoDao.insert(orderCouponInfoDo) > 0) {
            record.setId(orderCouponInfoDo.getId());
            return record;
        }
        log.error("新增优惠信息表失败");
        return null;
    }

    @Override
    public OrderCouponInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询优惠信息表,传入ID为空");
            return null;
        }
        OrderCouponInfoDO orderCouponInfoDo = orderCouponInfoDao.selectById(id);
        if (orderCouponInfoDo == null) {
            return null;
        }
        return orderCouponInfoDo.clone(OrderCouponInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderCouponInfoDTO record) {
        if (id == null) {
            log.error("修改优惠信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderCouponInfoDO orderCouponInfoDo = record.clone(OrderCouponInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderCouponInfoDo);
        return orderCouponInfoDao.updateById(orderCouponInfoDo);
    }

}