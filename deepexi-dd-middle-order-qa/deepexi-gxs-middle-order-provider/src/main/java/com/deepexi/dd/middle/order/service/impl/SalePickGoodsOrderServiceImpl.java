package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.SalePickGoodsOrderDAO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderQuery;
import com.deepexi.dd.middle.order.service.SalePickGoodsOrderService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * SalePickGoodsOrderServiceImpl
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SalePickGoodsOrderServiceImpl implements SalePickGoodsOrderService {

    @Autowired
    private SalePickGoodsOrderDAO salePickGoodsOrderDao;

    @Override
    public List<SalePickGoodsOrderDTO> listSalePickGoodsOrders(SalePickGoodsOrderQuery query) {
        return ObjectCloneUtils.convertList(salePickGoodsOrderDao.listSalePickGoodsOrders(query), SalePickGoodsOrderDTO.class);
    }


    @Override
    public PageBean<SalePickGoodsOrderDTO> listSalePickGoodsOrdersPage(SalePickGoodsOrderQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(salePickGoodsOrderDao.listSalePickGoodsOrders(query)), SalePickGoodsOrderDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除提货单据所关联的订单的信息表,传入ID为空");
            return false;
        }
        return salePickGoodsOrderDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public Boolean deleteByWrapper(QueryWrapper wrapper) {
        if(wrapper == null){
            return false;
        }
        return salePickGoodsOrderDao.remove(wrapper);
    }

    @Override
    public SalePickGoodsOrderDTO insert(SalePickGoodsOrderDTO record) {
        if (record == null) {
            log.error("新增提货单据所关联的订单的信息表,传入参数为空");
            return null;
        }
        SalePickGoodsOrderDO salePickGoodsOrderDo = record.clone(SalePickGoodsOrderDO.class);
        DomainUtils.initDomainCreatedInfo(salePickGoodsOrderDo);
        if (salePickGoodsOrderDao.insert(salePickGoodsOrderDo) > 0) {
            record.setId(salePickGoodsOrderDo.getId());
            return record;
        }
        log.error("新增提货单据所关联的订单的信息表失败");
        return null;
    }

    @Override
    public List<SalePickGoodsOrderDO> insertBatch(List<SalePickGoodsOrderDTO> list) {
        if(list == null || list.size() == 0){
            return null;
        }
        ArrayList<SalePickGoodsOrderDO> doList = new ArrayList<>();
        for(SalePickGoodsOrderDTO dtoItem : list){
            SalePickGoodsOrderDO doItem = dtoItem.clone(SalePickGoodsOrderDO.class);
            DomainUtils.initDomainCreatedInfo(doItem);
            doList.add(doItem);
        }
        Integer num = salePickGoodsOrderDao.insertBatch(doList);
        return doList;
    }

    @Override
    public SalePickGoodsOrderDTO selectById(Long id) {
        if (id == null) {
            log.error("查询提货单据所关联的订单的信息表,传入ID为空");
            return null;
        }
        SalePickGoodsOrderDO salePickGoodsOrderDo = salePickGoodsOrderDao.selectById(id);
        if (salePickGoodsOrderDo == null) {
            return null;
        }
        return salePickGoodsOrderDo.clone(SalePickGoodsOrderDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SalePickGoodsOrderDTO record) {
        if (id == null) {
            log.error("修改提货单据所关联的订单的信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        SalePickGoodsOrderDO salePickGoodsOrderDo = record.clone(SalePickGoodsOrderDO.class);
        DomainUtils.initUpdateTimeInfo(salePickGoodsOrderDo);
        return salePickGoodsOrderDao.updateById(salePickGoodsOrderDo);
    }

}