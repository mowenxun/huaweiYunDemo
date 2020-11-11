package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryPlanInfoDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryPlanInfoDO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryPlanInfoService;
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
 * SaleDeliveryPlanInfoServiceImpl
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleDeliveryPlanInfoServiceImpl implements SaleDeliveryPlanInfoService {

    @Autowired
    private SaleDeliveryPlanInfoDAO saleDeliveryPlanInfoDao;

    @Override
    public List<SaleDeliveryPlanInfoDTO> listSaleDeliveryPlanInfos(SaleDeliveryPlanInfoQuery query) {
        return ObjectCloneUtils.convertList(saleDeliveryPlanInfoDao.listSaleDeliveryPlanInfos(query), SaleDeliveryPlanInfoDTO.class);
    }


    @Override
    public PageBean<SaleDeliveryPlanInfoDTO> listSaleDeliveryPlanInfosPage(SaleDeliveryPlanInfoQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleDeliveryPlanInfoDao.listSaleDeliveryPlanInfos(query)), SaleDeliveryPlanInfoDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除发货计划表,传入ID为空");
            return false;
        }
        return saleDeliveryPlanInfoDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleDeliveryPlanInfoDTO insert(SaleDeliveryPlanInfoDTO record) {
        if (record == null) {
            log.error("新增发货计划表,传入参数为空");
            return null;
        }
        SaleDeliveryPlanInfoDO saleDeliveryPlanInfoDo = record.clone(SaleDeliveryPlanInfoDO.class);
        DomainUtils.initDomainCreatedInfo(saleDeliveryPlanInfoDo);
        if (saleDeliveryPlanInfoDao.insert(saleDeliveryPlanInfoDo) > 0) {
            record.setId(saleDeliveryPlanInfoDo.getId());
            return record;
        }
        log.error("新增发货计划表失败");
        return null;
    }

    @Override
    public SaleDeliveryPlanInfoDTO selectById(Long id) {
        if (id == null) {
            log.error("查询发货计划表,传入ID为空");
            return null;
        }
        SaleDeliveryPlanInfoDO saleDeliveryPlanInfoDo = saleDeliveryPlanInfoDao.selectById(id);
        if (saleDeliveryPlanInfoDo == null) {
            return null;
        }
        return saleDeliveryPlanInfoDo.clone(SaleDeliveryPlanInfoDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleDeliveryPlanInfoDTO record) {
        if (id == null) {
            log.error("修改发货计划表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleDeliveryPlanInfoDO saleDeliveryPlanInfoDo = record.clone(SaleDeliveryPlanInfoDO.class);
        DomainUtils.initUpdateTimeInfo(saleDeliveryPlanInfoDo);
        return saleDeliveryPlanInfoDao.updateById(saleDeliveryPlanInfoDo) > 0;
    }

}