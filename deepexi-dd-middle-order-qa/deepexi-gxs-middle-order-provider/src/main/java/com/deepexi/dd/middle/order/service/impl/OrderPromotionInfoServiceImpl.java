package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderPromotionInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderPromotionInfoDO;
import com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoQuery;
import com.deepexi.dd.middle.order.service.OrderPromotionInfoService;
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
 * OrderPromotionInfoServiceImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderPromotionInfoServiceImpl implements OrderPromotionInfoService {

    @Autowired
    private OrderPromotionInfoDAO orderPromotionInfoDao;

    @Override
    public List<OrderPromotionInfoDTO> listOrderPromotionInfos(OrderPromotionInfoQuery query) {
        return ObjectCloneUtils.convertList(orderPromotionInfoDao.listOrderPromotionInfos(query), OrderPromotionInfoDTO.class);
    }


    @Override
    public PageBean<OrderPromotionInfoDTO> listOrderPromotionInfosPage(OrderPromotionInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderPromotionInfoDao.listOrderPromotionInfos(query)), OrderPromotionInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单的促销信息表,传入ID为空");
            return false;
        }
        return orderPromotionInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderPromotionInfoDTO insert(OrderPromotionInfoDTO record) {
        if (record == null) {
            log.error("新增销售订单的促销信息表,传入参数为空");
            return null;
        }
        OrderPromotionInfoDO orderPromotionInfoDo = record.clone(OrderPromotionInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderPromotionInfoDo);
        if (orderPromotionInfoDao.insert(orderPromotionInfoDo) > 0) {
            record.setId(orderPromotionInfoDo.getId());
            return record;
        }
        log.error("新增销售订单的促销信息表失败");
        return null;
    }

    @Override
    public OrderPromotionInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单的促销信息表,传入ID为空");
            return null;
        }
        OrderPromotionInfoDO orderPromotionInfoDo = orderPromotionInfoDao.selectById(id);
        if (orderPromotionInfoDo == null) {
            return null;
        }
        return orderPromotionInfoDo.clone(OrderPromotionInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderPromotionInfoDTO record) {
        if (id == null) {
            log.error("修改销售订单的促销信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderPromotionInfoDO orderPromotionInfoDo = record.clone(OrderPromotionInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderPromotionInfoDo);
        return orderPromotionInfoDao.updateById(orderPromotionInfoDo);
    }

}