package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.OrderReturnInfoDAO;
import com.deepexi.dd.middle.order.dao.OrderReturnItemDAO;
import com.deepexi.dd.middle.order.domain.OrderReturnInfoDO;
import com.deepexi.dd.middle.order.domain.OrderReturnItemDO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoAddRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnItemRequestDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoQuery;
import com.deepexi.dd.middle.order.service.OrderReturnInfoService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * OrderReturnInfoServiceImpl
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderReturnInfoServiceImpl implements OrderReturnInfoService {

    @Autowired
    private OrderReturnInfoDAO orderReturnInfoDao;

    @Autowired
    private OrderReturnItemDAO orderReturnItemDAO;

    @Override
    public List<OrderReturnInfoDTO> listOrderReturnInfos(OrderReturnInfoQuery query) {
        return ObjectCloneUtils.convertList(orderReturnInfoDao.listOrderReturnInfos(query), OrderReturnInfoDTO.class);
    }


    @Override
    public PageBean<OrderReturnInfoDTO> listOrderReturnInfosPage(OrderReturnInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        PageBean<OrderReturnInfoDTO> result = ObjectCloneUtils.convertPageBean(new PageBean<>(orderReturnInfoDao.listOrderReturnInfos(query)), OrderReturnInfoDTO.class);
        List<Long> orderReturnIds = result.getContent().stream().map(OrderReturnInfoDTO::getId).collect(Collectors.toList());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("is_deleted",0);
        wrapper.in("order_return_id",orderReturnIds);
        List<OrderReturnItemDO> orderReturnItemDOS = orderReturnItemDAO.list(wrapper);

        for(OrderReturnInfoDTO orderReturnInfoDTO : result.getContent()){
            Long returnAmount = 0L;
            for(OrderReturnItemDO orderReturnItemDO : orderReturnItemDOS){
                if(orderReturnInfoDTO.getId().equals(orderReturnItemDO.getOrderReturnId())){
                    returnAmount+=orderReturnItemDO.getReturnQuantity();
                }
            }
            orderReturnInfoDTO.setReturnQuantity(returnAmount);
        }
        return result;
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单退货单信息表,传入ID为空");
            return false;
        }
        return orderReturnInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public OrderReturnInfoDTO insert(OrderReturnInfoAddRequestDTO record) {
        if (record == null) {
            log.error("新增销售订单退货单信息表,传入参数为空");
            return null;
        }
        OrderReturnInfoDO orderReturnInfoDo = record.clone(OrderReturnInfoDO.class);
        DomainUtils.initDomainCreatedInfo(orderReturnInfoDo);
        Date now = new Date();
        if (orderReturnInfoDao.insert(orderReturnInfoDo) > 0) {
            //保存退货明细
            List<OrderReturnItemDO> orderReturnItemDOS=  getOrderReturnItemList(record.getItems(),true,orderReturnInfoDo.getId(),orderReturnInfoDo.getCode());

            if(CollectionUtil.isNotEmpty(orderReturnItemDOS)){
                orderReturnItemDOS.forEach(i->{
                    i.setTenantId(orderReturnInfoDo.getTenantId());
                    i.setAppId(orderReturnInfoDo.getAppId());
                    i.setOrderReturnId(orderReturnInfoDo.getId());
                    i.setOrderReturnCode(orderReturnInfoDo.getCode());
                    i.setSaleOrderId(orderReturnInfoDo.getSaleOrderId());
                    i.setSaleOrderCode(orderReturnInfoDo.getSaleOrderCode());
                    i.setCreatedTime(now);
                    i.setUpdatedTime(now);
                });
                if(orderReturnItemDAO.saveBatch(orderReturnItemDOS)){
                    return orderReturnInfoDo.clone(OrderReturnInfoDTO.class);
                }
            }

        }
        log.error("新增销售订单退货单信息表失败");
        return null;
    }

    /**
     * 获取商品列表DO
     * @param orderReturnItems
     * @return
     */
    private List<OrderReturnItemDO> getOrderReturnItemList(List<OrderReturnItemRequestDTO> orderReturnItems, boolean isAdd, Long orderReturnId, String orderReturnCode){
        List<OrderReturnItemDO> orderReturnItemDOS=new ArrayList<>();
        if (CollectionUtil.isNotEmpty(orderReturnItems)) {
            orderReturnItems.forEach(item->{
                OrderReturnItemDO itemDO=  item.clone(OrderReturnItemDO.class);
                itemDO.setSaleOrderCode(orderReturnCode);
                itemDO.setSaleOrderId(orderReturnId);
                if(isAdd){
                    DomainUtils.initDomainCreatedInfo(itemDO);
                }
                orderReturnItemDOS.add(itemDO);
            });
        }
        return orderReturnItemDOS;
    }

    @Override
    public OrderReturnInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单退货单信息表,传入ID为空");
            return null;
        }
        OrderReturnInfoDO orderReturnInfoDo = orderReturnInfoDao.selectById(id);
        if (orderReturnInfoDo == null) {
            return null;
        }
        return orderReturnInfoDo.clone(OrderReturnInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderReturnInfoDTO record) {
        if (id == null) {
            log.error("修改销售订单退货单信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderReturnInfoDO orderReturnInfoDo = record.clone(OrderReturnInfoDO.class);
        DomainUtils.initUpdateTimeInfo(orderReturnInfoDo);
        return orderReturnInfoDao.updateById(orderReturnInfoDo);
    }

}