package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.AfterSaleOrderItemDAO;
import com.deepexi.dd.middle.order.domain.AfterSaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemQuery;
import com.deepexi.dd.middle.order.service.AfterSaleOrderItemService;
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
 * AfterSaleOrderItemServiceImpl
 *
 * @author admin
 * @date Fri Oct 16 14:17:13 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class AfterSaleOrderItemServiceImpl implements AfterSaleOrderItemService {

    @Autowired
    private AfterSaleOrderItemDAO afterSaleOrderItemDao;

    @Override
    public List<AfterSaleOrderItemDTO> listAfterSaleOrderItems(AfterSaleOrderItemQuery query) {
        return ObjectCloneUtils.convertList(afterSaleOrderItemDao.listAfterSaleOrderItems(query), AfterSaleOrderItemDTO.class);
    }


    @Override
    public PageBean<AfterSaleOrderItemDTO> listAfterSaleOrderItemsPage(AfterSaleOrderItemQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(afterSaleOrderItemDao.listAfterSaleOrderItems(query)), AfterSaleOrderItemDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除售后订单明细表,传入ID为空");
            return false;
        }
        return afterSaleOrderItemDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public AfterSaleOrderItemDTO insert(AfterSaleOrderItemDTO record) {
        if (record == null) {
            log.error("新增售后订单明细表,传入参数为空");
            return null;
        }
        AfterSaleOrderItemDO afterSaleOrderItemDo = record.clone(AfterSaleOrderItemDO.class);
        DomainUtils.initDomainCreatedInfo(afterSaleOrderItemDo);
        if (afterSaleOrderItemDao.insert(afterSaleOrderItemDo) > 0) {
            record.setId(afterSaleOrderItemDo.getId());
            return record;
        }
        log.error("新增售后订单明细表失败");
        return null;
    }

    @Override
    public AfterSaleOrderItemDTO selectById(Long id) {
        if (id == null) {
            log.error("查询售后订单明细表,传入ID为空");
            return null;
        }
        AfterSaleOrderItemDO afterSaleOrderItemDo = afterSaleOrderItemDao.selectById(id);
        if (afterSaleOrderItemDo == null) {
            return null;
        }
        return afterSaleOrderItemDo.clone(AfterSaleOrderItemDTO.class);
    }

    @Override
    public Boolean updateById(Long id, AfterSaleOrderItemDTO record) {
        if (id == null) {
            log.error("修改售后订单明细表,传入ID为空");
            return false;
        }
        record.setId(id);
        AfterSaleOrderItemDO afterSaleOrderItemDo = record.clone(AfterSaleOrderItemDO.class);
        DomainUtils.initUpdateTimeInfo(afterSaleOrderItemDo);
        return afterSaleOrderItemDao.updateById(afterSaleOrderItemDo) > 0;
    }

}