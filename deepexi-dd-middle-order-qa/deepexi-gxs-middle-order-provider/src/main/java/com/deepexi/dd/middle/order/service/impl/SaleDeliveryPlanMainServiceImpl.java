package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryPlanMainDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanMainDO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanMainDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanMainQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryPlanMainService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * SaleDeliveryPlanMainServiceImpl
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleDeliveryPlanMainServiceImpl implements SaleDeliveryPlanMainService {

    @Autowired
    private SaleDeliveryPlanMainDAO saleDeliveryPlanMainDao;

    @Override
    public List<SaleDeliveryPlanMainDTO> listSaleDeliveryPlanMains(SaleDeliveryPlanMainQuery query) {
        return ObjectCloneUtils.convertList(saleDeliveryPlanMainDao.listSaleDeliveryPlanMains(query), SaleDeliveryPlanMainDTO.class);
    }


    @Override
    public PageBean<SaleDeliveryPlanMainDTO> listSaleDeliveryPlanMainsPage(SaleDeliveryPlanMainQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleDeliveryPlanMainDao.listSaleDeliveryPlanMains(query)), SaleDeliveryPlanMainDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除发货编排主表,传入ID为空");
            return false;
        }
        return saleDeliveryPlanMainDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleDeliveryPlanMainDTO insert(SaleDeliveryPlanMainDTO record) {
        if (record == null) {
            log.error("新增发货编排主表,传入参数为空");
            return null;
        }
        SaleDeliveryPlanMainDO saleDeliveryPlanMainDo = record.clone(SaleDeliveryPlanMainDO.class);
        DomainUtils.initDomainCreatedInfo(saleDeliveryPlanMainDo);
        if (saleDeliveryPlanMainDao.insert(saleDeliveryPlanMainDo) > 0) {
            record.setId(saleDeliveryPlanMainDo.getId());
            return record;
        }
        log.error("新增发货编排主表失败");
        return null;
    }

    @Override
    public SaleDeliveryPlanMainDTO selectById(Long id) {
        if (id == null) {
            log.error("查询发货编排主表,传入ID为空");
            return null;
        }
        SaleDeliveryPlanMainDO saleDeliveryPlanMainDo = saleDeliveryPlanMainDao.selectById(id);
        if (saleDeliveryPlanMainDo == null) {
            return null;
        }
        return saleDeliveryPlanMainDo.clone(SaleDeliveryPlanMainDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleDeliveryPlanMainDTO record) {
        if (id == null) {
            log.error("修改发货编排主表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleDeliveryPlanMainDO saleDeliveryPlanMainDo = record.clone(SaleDeliveryPlanMainDO.class);
        DomainUtils.initUpdateTimeInfo(saleDeliveryPlanMainDo);
        return saleDeliveryPlanMainDao.updateById(saleDeliveryPlanMainDo) > 0;
    }

}