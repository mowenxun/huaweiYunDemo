package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.ShopSupplerRelationDAO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDTO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationQuery;
import com.deepexi.dd.middle.order.service.ShopSupplerRelationService;
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
 * ShopSupplerRelationServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 15:15:24 CST 2020
 */
@Service
@Slf4j
public class ShopSupplerRelationServiceImpl implements ShopSupplerRelationService {

    @Autowired
    private ShopSupplerRelationDAO shopSupplerRelationDao;

    @Override
    public List<ShopSupplerRelationDTO> listShopSupplerRelations(ShopSupplerRelationQuery query) {
        return ObjectCloneUtils.convertList(shopSupplerRelationDao.listShopSupplerRelations(query),
                ShopSupplerRelationDTO.class);
    }


    @Override
    public PageBean<ShopSupplerRelationDTO> listShopSupplerRelationsPage(ShopSupplerRelationQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(shopSupplerRelationDao.listShopSupplerRelations(query)), ShopSupplerRelationDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除已分发订单和店铺订单关系表,传入ID为空");
            return false;
        }
        return shopSupplerRelationDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public ShopSupplerRelationDTO insert(ShopSupplerRelationDTO record) {
        if (record == null) {
            log.error("新增已分发订单和店铺订单关系表,传入参数为空");
            return null;
        }
        ShopSupplerRelationDO shopSupplerRelationDo = record.clone(ShopSupplerRelationDO.class);
        DomainUtils.initDomainCreatedInfo(shopSupplerRelationDo);
        if (shopSupplerRelationDao.insert(shopSupplerRelationDo) > 0) {
            record.setId(shopSupplerRelationDo.getId());
            return record;
        }
        log.error("新增已分发订单和店铺订单关系表失败");
        return null;
    }

    @Override
    public ShopSupplerRelationDTO selectById(Long id) {
        if (id == null) {
            log.error("查询已分发订单和店铺订单关系表,传入ID为空");
            return null;
        }
        ShopSupplerRelationDO shopSupplerRelationDo = shopSupplerRelationDao.selectById(id);
        if (shopSupplerRelationDo == null) {
            return null;
        }
        return shopSupplerRelationDo.clone(ShopSupplerRelationDTO.class);
    }

    @Override
    public Boolean updateById(Long id, ShopSupplerRelationDTO record) {
        if (id == null) {
            log.error("修改已分发订单和店铺订单关系表,传入ID为空");
            return false;
        }
        record.setId(id);
        ShopSupplerRelationDO shopSupplerRelationDo = record.clone(ShopSupplerRelationDO.class);
        DomainUtils.initUpdateTimeInfo(shopSupplerRelationDo);
        return shopSupplerRelationDao.updateById(shopSupplerRelationDo);
    }

    @Override
    public boolean saveBatch(List<ShopSupplerRelationDTO> list) {
        List<ShopSupplerRelationDO> shopSupplerRelationDOList = ObjectCloneUtils.convertList(list,
                ShopSupplerRelationDO.class);
        return shopSupplerRelationDao.saveBatch(shopSupplerRelationDOList);
    }

}