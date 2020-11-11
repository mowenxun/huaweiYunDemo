package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.OrderDeliveryInfoDAO;
import com.deepexi.dd.middle.order.domain.OrderDeliveryInfoDO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoQuery;
import com.deepexi.dd.middle.order.service.OrderDeliveryInfoService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.dd.middle.order.util.Utils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * OrderDeliveryInfoServiceImpl
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderDeliveryInfoServiceImpl implements OrderDeliveryInfoService {

    @Autowired
    private OrderDeliveryInfoDAO orderDeliveryInfoDao;

    @Override
    public List<OrderDeliveryInfoDTO> listOrderDeliveryInfos(OrderDeliveryInfoQuery query) {
        return ObjectCloneUtils.convertList(orderDeliveryInfoDao.listOrderDeliveryInfos(query), OrderDeliveryInfoDTO.class);
    }


    @Override
    public PageBean<OrderDeliveryInfoDTO> listOrderDeliveryInfosPage(OrderDeliveryInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderDeliveryInfoDao.listOrderDeliveryInfos(query)), OrderDeliveryInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售出库单物流信息,传入ID为空");
            return false;
        }
        return orderDeliveryInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderDeliveryInfoDTO insert(OrderDeliveryInfoDTO record) {
        if (record == null) {
            log.error("新增销售出库单物流信息,传入参数为空");
            return null;
        }
        OrderDeliveryInfoDO orderDeliveryInfoDo = record.clone(OrderDeliveryInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderDeliveryInfoDo);
        if (orderDeliveryInfoDao.insert(orderDeliveryInfoDo) > 0) {
            record.setId(orderDeliveryInfoDo.getId());
            return record;
        }
        log.error("新增销售出库单物流信息失败");
        return null;
    }

    @Override
    public OrderDeliveryInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售出库单物流信息,传入ID为空");
            return null;
        }
        OrderDeliveryInfoDO orderDeliveryInfoDo = orderDeliveryInfoDao.selectById(id);
        if (orderDeliveryInfoDo == null) {
            return null;
        }
        return orderDeliveryInfoDo.clone(OrderDeliveryInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderDeliveryInfoDTO record) {
        if (id == null) {
            log.error("修改销售出库单物流信息,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderDeliveryInfoDO orderDeliveryInfoDo = record.clone(OrderDeliveryInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderDeliveryInfoDo);
        return orderDeliveryInfoDao.updateById(orderDeliveryInfoDo);
    }
    /**
     * @Description:  根据出库单ID查询出库单物流信息，用于出库单列表查询.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public List<OrderDeliveryInfoResponseDTO> searchOrderDeliveryInfoById(List<Long> idList) {
        List<OrderDeliveryInfoResponseDTO> list = Lists.newArrayList();
        if (Utils.isEmpty(idList)){
            log.error("查询出库单物流信息，传入ID为空");
            return list;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("sale_out_task_id",idList);
        queryWrapper.eq("is_deleted",0);
        list = orderDeliveryInfoDao.list(queryWrapper);
        return ObjectCloneUtils.convertList(list, OrderDeliveryInfoResponseDTO.class);
    }

}