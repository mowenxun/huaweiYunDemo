package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.SaleOrderCollectionNotifyDAO;
import com.deepexi.dd.middle.order.domain.*;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.service.SaleOrderCollectionNotifyService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * SaleOrderCollectionNotifyServiceImpl
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleOrderCollectionNotifyServiceImpl implements SaleOrderCollectionNotifyService {

    @Autowired
    private SaleOrderCollectionNotifyDAO saleOrderCollectionNotifyDao;

    @Override
    public List<SaleOrderCollectionNotifyDTO> listSaleOrderCollectionNotifys(SaleOrderCollectionNotifyQuery query) {
        return ObjectCloneUtils.convertList(saleOrderCollectionNotifyDao.listSaleOrderCollectionNotifys(query), SaleOrderCollectionNotifyDTO.class);
    }


    @Override
    public PageBean<SaleOrderCollectionNotifyDTO> listSaleOrderCollectionNotifysPage(SaleOrderCollectionNotifyQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleOrderCollectionNotifyDao.listSaleOrderCollectionNotifys(query)), SaleOrderCollectionNotifyDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除按单收款消息表,传入ID为空");
            return false;
        }
        return saleOrderCollectionNotifyDao.deleteByIdIn(id) == id.size();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public SaleOrderCollectionNotifyDTO insert(SaleOrderCollectionNotifyDTO record) {
        if (record == null) {
            log.error("新增按单收款消息表,传入参数为空");
            return null;
        }
        SaleOrderCollectionNotifyDO saleOrderCollectionNotifyDo = record.clone(SaleOrderCollectionNotifyDO.class);
        DomainUtils.initDomainCreatedInfo(saleOrderCollectionNotifyDo);
        if (saleOrderCollectionNotifyDao.insert(saleOrderCollectionNotifyDo) > 0) {
            record.setId(saleOrderCollectionNotifyDo.getId());
            return record;
        }
        log.error("新增按单收款消息表失败");
        return null;
    }

    @Override
    public SaleOrderCollectionNotifyDTO selectById(Long id) {
        if (id == null) {
            log.error("查询按单收款消息表,传入ID为空");
            return null;
        }
        SaleOrderCollectionNotifyDO saleOrderCollectionNotifyDo = saleOrderCollectionNotifyDao.selectById(id);
        if (saleOrderCollectionNotifyDo == null) {
            return null;
        }
        return saleOrderCollectionNotifyDo.clone(SaleOrderCollectionNotifyDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleOrderCollectionNotifyDTO record) {
        if (id == null) {
            log.error("修改按单收款消息表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleOrderCollectionNotifyDO saleOrderCollectionNotifyDo = record.clone(SaleOrderCollectionNotifyDO.class);
        DomainUtils.initUpdateTimeInfo(saleOrderCollectionNotifyDo);
        return saleOrderCollectionNotifyDao.updateById(saleOrderCollectionNotifyDo) ;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
   public List<SaleOrderCollectionNotifyDTO> querySaleOrderCollectionNotify(SaleOrderCollectionNotifyQuery query) {
        List<SaleOrderCollectionNotifyDO> list=  saleOrderCollectionNotifyDao.list(new QueryWrapper<SaleOrderCollectionNotifyDO>().lambda().eq(SaleOrderCollectionNotifyDO::getOrderId,query.getOrderId()).eq(SaleOrderCollectionNotifyDO::getPayNo,query.getPayNo()).eq(SaleOrderCollectionNotifyDO::getDeleted,0));
        return ObjectCloneUtils.convertList(list,SaleOrderCollectionNotifyDTO.class);
    }
}