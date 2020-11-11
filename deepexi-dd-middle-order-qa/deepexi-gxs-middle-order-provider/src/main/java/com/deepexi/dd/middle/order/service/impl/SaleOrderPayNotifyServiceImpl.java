package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.SaleOrderPayNotifyDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.service.SaleOrderPayNotifyService;
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
 * SaleOrderPayNotifyServiceImpl
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleOrderPayNotifyServiceImpl implements SaleOrderPayNotifyService {

    @Autowired
    private SaleOrderPayNotifyDAO saleOrderPayNotifyDao;

    @Override
    public List<SaleOrderPayNotifyDTO> listSaleOrderPayNotifys(SaleOrderPayNotifyQuery query) {
        return ObjectCloneUtils.convertList(saleOrderPayNotifyDao.listSaleOrderPayNotifys(query), SaleOrderPayNotifyDTO.class);
    }


    @Override
    public PageBean<SaleOrderPayNotifyDTO> listSaleOrderPayNotifysPage(SaleOrderPayNotifyQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleOrderPayNotifyDao.listSaleOrderPayNotifys(query)), SaleOrderPayNotifyDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除,传入ID为空");
            return false;
        }
        return saleOrderPayNotifyDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleOrderPayNotifyDTO insert(SaleOrderPayNotifyDTO record) {
        if (record == null) {
            log.error("新增,传入参数为空");
            return null;
        }
        SaleOrderPayNotifyDO saleOrderPayNotifyDo = record.clone(SaleOrderPayNotifyDO.class);
        DomainUtils.initDomainCreatedInfo(saleOrderPayNotifyDo);
        if (saleOrderPayNotifyDao.insert(saleOrderPayNotifyDo) > 0) {
            record.setId(saleOrderPayNotifyDo.getId());
            return record;
        }
        log.error("新增失败");
        return null;
    }

    @Override
    public SaleOrderPayNotifyDTO selectById(Long id) {
        if (id == null) {
            log.error("查询,传入ID为空");
            return null;
        }
        SaleOrderPayNotifyDO saleOrderPayNotifyDo = saleOrderPayNotifyDao.selectById(id);
        if (saleOrderPayNotifyDo == null) {
            return null;
        }
        return saleOrderPayNotifyDo.clone(SaleOrderPayNotifyDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleOrderPayNotifyDTO record) {
        if (id == null) {
            log.error("修改,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleOrderPayNotifyDO saleOrderPayNotifyDo = record.clone(SaleOrderPayNotifyDO.class);
        DomainUtils.initUpdateTimeInfo(saleOrderPayNotifyDo);
        return saleOrderPayNotifyDao.updateById(saleOrderPayNotifyDo) ;
    }

    @Override
    public List<SaleOrderPayNotifyResponseDTO> querySaleOrderNotify(SaleOrderPayNotifyQuery query) {
        List<SaleOrderPayNotifyDO> list=  saleOrderPayNotifyDao.list(new QueryWrapper<SaleOrderPayNotifyDO>().lambda().eq(SaleOrderPayNotifyDO::getOrderId,query.getOrderId()).eq(SaleOrderPayNotifyDO::getPayNo,query.getPayNo()).eq(SaleOrderPayNotifyDO::getDeleted,0));
        return ObjectCloneUtils.convertList(list,SaleOrderPayNotifyResponseDTO.class);
    }
}