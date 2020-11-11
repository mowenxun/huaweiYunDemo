package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryPlanItemDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanItemDO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryPlanItemService;
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
 * SaleDeliveryPlanItemServiceImpl
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleDeliveryPlanItemServiceImpl implements SaleDeliveryPlanItemService {

    @Autowired
    private SaleDeliveryPlanItemDAO saleDeliveryPlanItemDao;

    @Override
    public List<SaleDeliveryPlanItemDTO> listSaleDeliveryPlanItems(SaleDeliveryPlanItemQuery query) {
        return ObjectCloneUtils.convertList(saleDeliveryPlanItemDao.listSaleDeliveryPlanItems(query), SaleDeliveryPlanItemDTO.class);
    }


    @Override
    public PageBean<SaleDeliveryPlanItemDTO> listSaleDeliveryPlanItemsPage(SaleDeliveryPlanItemQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleDeliveryPlanItemDao.listSaleDeliveryPlanItems(query)), SaleDeliveryPlanItemDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除发货计划明细表,传入ID为空");
            return false;
        }
        return saleDeliveryPlanItemDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleDeliveryPlanItemDTO insert(SaleDeliveryPlanItemDTO record) {
        if (record == null) {
            log.error("新增发货计划明细表,传入参数为空");
            return null;
        }
        SaleDeliveryPlanItemDO saleDeliveryPlanItemDo = record.clone(SaleDeliveryPlanItemDO.class);
        DomainUtils.initDomainCreatedInfo(saleDeliveryPlanItemDo);
        if (saleDeliveryPlanItemDao.insert(saleDeliveryPlanItemDo) > 0) {
            record.setId(saleDeliveryPlanItemDo.getId());
            return record;
        }
        log.error("新增发货计划明细表失败");
        return null;
    }

    @Override
    public SaleDeliveryPlanItemDTO selectById(Long id) {
        if (id == null) {
            log.error("查询发货计划明细表,传入ID为空");
            return null;
        }
        SaleDeliveryPlanItemDO saleDeliveryPlanItemDo = saleDeliveryPlanItemDao.selectById(id);
        if (saleDeliveryPlanItemDo == null) {
            return null;
        }
        return saleDeliveryPlanItemDo.clone(SaleDeliveryPlanItemDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleDeliveryPlanItemDTO record) {
        if (id == null) {
            log.error("修改发货计划明细表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleDeliveryPlanItemDO saleDeliveryPlanItemDo = record.clone(SaleDeliveryPlanItemDO.class);
        DomainUtils.initUpdateTimeInfo(saleDeliveryPlanItemDo);
        return saleDeliveryPlanItemDao.updateById(saleDeliveryPlanItemDo) > 0;
    }

}