package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.ShopOrderItemDAO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDTO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemQuery;
import com.deepexi.dd.middle.order.service.ShopOrderItemService;
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
/**
 * ShopOrderItemServiceImpl
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class ShopOrderItemServiceImpl implements ShopOrderItemService {

    @Autowired
    private ShopOrderItemDAO shopOrderItemDao;

    @Override
    public List<ShopOrderItemDTO> listShopOrderItems(ShopOrderItemQuery query) {
        return ObjectCloneUtils.convertList(shopOrderItemDao.listShopOrderItems(query), ShopOrderItemDTO.class);
    }


    @Override
    public PageBean<ShopOrderItemDTO> listShopOrderItemsPage(ShopOrderItemQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(shopOrderItemDao.listShopOrderItems(query)), ShopOrderItemDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除店铺订单明细表,传入ID为空");
            return false;
        }
        return shopOrderItemDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public ShopOrderItemDTO insert(ShopOrderItemDTO record) {
        if (record == null) {
            log.error("新增店铺订单明细表,传入参数为空");
            return null;
        }
        ShopOrderItemDO shopOrderItemDo = record.clone(ShopOrderItemDO.class);
        DomainUtils.initDomainCreatedInfo(shopOrderItemDo);
        if (shopOrderItemDao.insert(shopOrderItemDo) > 0) {
            record.setId(shopOrderItemDo.getId());
            return record;
        }
        log.error("新增店铺订单明细表失败");
        return null;
    }

    @Override
    public boolean saveBatch(List<ShopOrderItemDTO> list) {
      List<ShopOrderItemDO> shopOrderItemDOList= ObjectCloneUtils.convertList(list,ShopOrderItemDO.class);
        return shopOrderItemDao.saveBatch(shopOrderItemDOList);
    }

    @Override
    public ShopOrderItemDTO selectById(Long id) {
        if (id == null) {
            log.error("查询店铺订单明细表,传入ID为空");
            return null;
        }
        ShopOrderItemDO shopOrderItemDo = shopOrderItemDao.selectById(id);
        if (shopOrderItemDo == null) {
            return null;
        }
        return shopOrderItemDo.clone(ShopOrderItemDTO.class);
    }

    @Override
    public Boolean updateById(Long id, ShopOrderItemDTO record) {
        if (id == null) {
            log.error("修改店铺订单明细表,传入ID为空");
            return false;
        }
        record.setId(id);
        ShopOrderItemDO shopOrderItemDo = record.clone(ShopOrderItemDO.class);
        DomainUtils.initUpdateTimeInfo(shopOrderItemDo);
        return shopOrderItemDao.updateById(shopOrderItemDo);
    }

}