package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SupplerOrderItemDAO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemDO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemDTO;
import com.deepexi.dd.middle.order.domain.SupplerOrderItemQuery;
import com.deepexi.dd.middle.order.service.SupplerOrderItemService;
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
 * SupplerOrderItemServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 15:15:23 CST 2020
 */
@Service
@Slf4j
public class SupplerOrderItemServiceImpl implements SupplerOrderItemService {

    @Autowired
    private SupplerOrderItemDAO supplerOrderItemDao;

    @Override
    public List<SupplerOrderItemDTO> listSupplerOrderItems(SupplerOrderItemQuery query) {
        return ObjectCloneUtils.convertList(supplerOrderItemDao.listSupplerOrderItems(query),
                SupplerOrderItemDTO.class);
    }


    @Override
    public PageBean<SupplerOrderItemDTO> listSupplerOrderItemsPage(SupplerOrderItemQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(supplerOrderItemDao.listSupplerOrderItems(query)),
                SupplerOrderItemDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除已分发订单明细表,传入ID为空");
            return false;
        }
        return supplerOrderItemDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SupplerOrderItemDTO insert(SupplerOrderItemDTO record) {
        if (record == null) {
            log.error("新增已分发订单明细表,传入参数为空");
            return null;
        }
        SupplerOrderItemDO supplerOrderItemDo = record.clone(SupplerOrderItemDO.class);
        DomainUtils.initDomainCreatedInfo(supplerOrderItemDo);
        if (supplerOrderItemDao.insert(supplerOrderItemDo) > 0) {
            record.setId(supplerOrderItemDo.getId());
            return record;
        }
        log.error("新增已分发订单明细表失败");
        return null;
    }

    @Override
    public SupplerOrderItemDTO selectById(Long id) {
        if (id == null) {
            log.error("查询已分发订单明细表,传入ID为空");
            return null;
        }
        SupplerOrderItemDO supplerOrderItemDo = supplerOrderItemDao.selectById(id);
        if (supplerOrderItemDo == null) {
            return null;
        }
        return supplerOrderItemDo.clone(SupplerOrderItemDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SupplerOrderItemDTO record) {
        if (id == null) {
            log.error("修改已分发订单明细表,传入ID为空");
            return false;
        }
        record.setId(id);
        SupplerOrderItemDO supplerOrderItemDo = record.clone(SupplerOrderItemDO.class);
        DomainUtils.initUpdateTimeInfo(supplerOrderItemDo);
        return supplerOrderItemDao.updateById(supplerOrderItemDo);
    }

    @Override
    public boolean saveBatch(List<SupplerOrderItemDTO> list) {
        List<SupplerOrderItemDO> shopOrderItemDOList = ObjectCloneUtils.convertList(list, SupplerOrderItemDO.class);
        return supplerOrderItemDao.saveBatch(shopOrderItemDOList);
    }

    @Override
    public boolean updateBatchById(List<SupplerOrderItemDTO> list) {
        List<SupplerOrderItemDO> shopOrderItemDOList = ObjectCloneUtils.convertList(list, SupplerOrderItemDO.class);
        return supplerOrderItemDao.updateBatchById(shopOrderItemDOList);
    }

}