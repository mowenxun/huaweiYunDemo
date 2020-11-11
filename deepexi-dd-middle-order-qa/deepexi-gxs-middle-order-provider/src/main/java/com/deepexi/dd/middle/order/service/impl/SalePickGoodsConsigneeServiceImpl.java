package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.deepexi.dd.middle.order.dao.SalePickGoodsConsigneeDAO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsConsigneeQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsConsigneeResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsConsigneeRequestQuery;
import com.deepexi.dd.middle.order.service.SalePickGoodsConsigneeService;
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
 * SalePickGoodsConsigneeServiceImpl
 *
 * @author admin
 * @date Mon Aug 24 16:35:15 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SalePickGoodsConsigneeServiceImpl implements SalePickGoodsConsigneeService {

    @Autowired
    private SalePickGoodsConsigneeDAO salePickGoodsConsigneeDao;

    @Override
    public List<SalePickGoodsConsigneeDTO> listSalePickGoodsConsignees(SalePickGoodsConsigneeQuery query) {
        return ObjectCloneUtils.convertList(salePickGoodsConsigneeDao.listSalePickGoodsConsignees(query), SalePickGoodsConsigneeDTO.class);
    }


    @Override
    public PageBean<SalePickGoodsConsigneeDTO> listSalePickGoodsConsigneesPage(SalePickGoodsConsigneeQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(salePickGoodsConsigneeDao.listSalePickGoodsConsignees(query)), SalePickGoodsConsigneeDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除提货计划收货地址信息表,传入ID为空");
            return false;
        }
        return salePickGoodsConsigneeDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SalePickGoodsConsigneeDTO insert(SalePickGoodsConsigneeDTO record) {
        if (record == null) {
            log.error("新增提货计划收货地址信息表,传入参数为空");
            return null;
        }
        SalePickGoodsConsigneeDO salePickGoodsConsigneeDo = record.clone(SalePickGoodsConsigneeDO.class);
        DomainUtils.initDomainCreatedInfo(salePickGoodsConsigneeDo);
        if (salePickGoodsConsigneeDao.insert(salePickGoodsConsigneeDo) > 0) {
            record.setId(salePickGoodsConsigneeDo.getId());
            return record;
        }
        log.error("新增提货计划收货地址信息表失败");
        return null;
    }

    @Override
    public SalePickGoodsConsigneeDTO selectById(Long id) {
        if (id == null) {
            log.error("查询提货计划收货地址信息表,传入ID为空");
            return null;
        }
        SalePickGoodsConsigneeDO salePickGoodsConsigneeDo = salePickGoodsConsigneeDao.selectById(id);
        if (salePickGoodsConsigneeDo == null) {
            return null;
        }
        return salePickGoodsConsigneeDo.clone(SalePickGoodsConsigneeDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SalePickGoodsConsigneeDTO record) {
        if (id == null) {
            log.error("修改提货计划收货地址信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        SalePickGoodsConsigneeDO salePickGoodsConsigneeDo = record.clone(SalePickGoodsConsigneeDO.class);
        DomainUtils.initUpdateTimeInfo(salePickGoodsConsigneeDo);
        return salePickGoodsConsigneeDao.updateById(salePickGoodsConsigneeDo) > 0;
    }

    @Override
    public Integer updateByWrapper(SalePickGoodsConsigneeDTO record, Wrapper<SalePickGoodsConsigneeDO> updateWrapper) {
        if (record == null) {
            log.error("修改提货计划收货地址信息表,传入record为空");
            return 0;
        }
        SalePickGoodsConsigneeDO salePickGoodsConsigneeDo = record.clone(SalePickGoodsConsigneeDO.class);
        DomainUtils.initUpdateTimeInfo(salePickGoodsConsigneeDo);
        return salePickGoodsConsigneeDao.updateByWrapper(salePickGoodsConsigneeDo,updateWrapper);
    }

    @Override
    public SalePickGoodsConsigneeResponseDTO selectByPickCode(SalePickGoodsConsigneeRequestQuery salePickGoodsConsigneeRequestQuery) {
        return salePickGoodsConsigneeDao.selectByPickCode(salePickGoodsConsigneeRequestQuery);
    }

}