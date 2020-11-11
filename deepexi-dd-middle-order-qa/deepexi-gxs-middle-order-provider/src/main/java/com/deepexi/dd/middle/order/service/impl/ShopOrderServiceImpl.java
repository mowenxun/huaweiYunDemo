package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.ShopOrderDAO;
import com.deepexi.dd.middle.order.domain.ShopOrderDO;
import com.deepexi.dd.middle.order.domain.ShopOrderDTO;
import com.deepexi.dd.middle.order.domain.ShopOrderQuery;
import com.deepexi.dd.middle.order.service.ShopOrderService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ShopOrderServiceImpl
 *
 * @author admin
 * @date Tue Oct 13 14:53:15 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderDAO shopOrderDao;

    @Override
    public List<ShopOrderDTO> listShopOrders(ShopOrderQuery query) {
        return ObjectCloneUtils.convertList(shopOrderDao.listShopOrders(query), ShopOrderDTO.class);
    }


    @Override
    public PageBean<ShopOrderDTO> listShopOrdersPage(ShopOrderQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(shopOrderDao.listShopOrders(query)), ShopOrderDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除店铺订单,传入ID为空");
            return false;
        }
        return shopOrderDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public ShopOrderDTO insert(ShopOrderDTO record) {
        if (record == null) {
            log.error("新增店铺订单,传入参数为空");
            return null;
        }
        ShopOrderDO shopOrderDo = record.clone(ShopOrderDO.class);
        DomainUtils.initDomainCreatedInfo(shopOrderDo);
        if (shopOrderDao.insert(shopOrderDo) > 0) {
            record.setId(shopOrderDo.getId());
            return record;
        }
        log.error("新增店铺订单失败");
        return null;
    }

    @Override
    public ShopOrderDTO selectById(Long id) {
        if (id == null) {
            log.error("查询店铺订单,传入ID为空");
            return null;
        }
        ShopOrderDO shopOrderDo = shopOrderDao.selectById(id);
        if (shopOrderDo == null) {
            return null;
        }
        return shopOrderDo.clone(ShopOrderDTO.class);
    }

    @Override
    public Boolean updateById(Long id, ShopOrderDTO record) {
        if (id == null) {
            log.error("修改店铺订单,传入ID为空");
            return false;
        }
        record.setId(id);
        ShopOrderDO shopOrderDo = record.clone(ShopOrderDO.class);
        DomainUtils.initUpdateTimeInfo(shopOrderDo);
        return shopOrderDao.updateById(shopOrderDo);
    }

    @Override
    public Boolean updateByCode(List<ShopOrderDTO> records) {
        List<String> collect = records.stream().map(ShopOrderDTO::getOrderCode).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(collect)){
            log.error("修改店铺订单,传入code为空");
            return false;
        }
        List<ShopOrderDO> shopOrderDOS = ObjectCloneUtils.convertList(records, ShopOrderDO.class);
        shopOrderDOS.forEach(DomainUtils::initUpdateTimeInfo);
        return shopOrderDao.updateByCode(shopOrderDOS);
    }

}