package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SaleCloudInterfaceRecordDAO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordDO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordDTO;
import com.deepexi.dd.middle.order.domain.SaleCloudInterfaceRecordQuery;
import com.deepexi.dd.middle.order.service.SaleCloudInterfaceRecordService;
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
 * SaleCloudInterfaceRecordServiceImpl
 *
 * @author admin
 * @date Wed Aug 26 19:14:46 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleCloudInterfaceRecordServiceImpl implements SaleCloudInterfaceRecordService {

    @Autowired
    private SaleCloudInterfaceRecordDAO saleCloudInterfaceRecordDao;

    @Override
    public List<SaleCloudInterfaceRecordDTO> listSaleCloudInterfaceRecords(SaleCloudInterfaceRecordQuery query) {
        return ObjectCloneUtils.convertList(saleCloudInterfaceRecordDao.listSaleCloudInterfaceRecords(query), SaleCloudInterfaceRecordDTO.class);
    }


    @Override
    public PageBean<SaleCloudInterfaceRecordDTO> listSaleCloudInterfaceRecordsPage(SaleCloudInterfaceRecordQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleCloudInterfaceRecordDao.listSaleCloudInterfaceRecords(query)), SaleCloudInterfaceRecordDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除调用云仓接口记录表,传入ID为空");
            return false;
        }
        return saleCloudInterfaceRecordDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleCloudInterfaceRecordDTO insert(SaleCloudInterfaceRecordDTO record) {
        if (record == null) {
            log.error("新增调用云仓接口记录表,传入参数为空");
            return null;
        }
        SaleCloudInterfaceRecordDO saleCloudInterfaceRecordDo = record.clone(SaleCloudInterfaceRecordDO.class);
        DomainUtils.initDomainCreatedInfo(saleCloudInterfaceRecordDo);
        if (saleCloudInterfaceRecordDao.insert(saleCloudInterfaceRecordDo) > 0) {
            record.setId(saleCloudInterfaceRecordDo.getId());
            return record;
        }
        log.error("新增调用云仓接口记录表失败");
        return null;
    }

    @Override
    public SaleCloudInterfaceRecordDTO selectById(Long id) {
        if (id == null) {
            log.error("查询调用云仓接口记录表,传入ID为空");
            return null;
        }
        SaleCloudInterfaceRecordDO saleCloudInterfaceRecordDo = saleCloudInterfaceRecordDao.selectById(id);
        if (saleCloudInterfaceRecordDo == null) {
            return null;
        }
        return saleCloudInterfaceRecordDo.clone(SaleCloudInterfaceRecordDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleCloudInterfaceRecordDTO record) {
        if (id == null) {
            log.error("修改调用云仓接口记录表,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleCloudInterfaceRecordDO saleCloudInterfaceRecordDo = record.clone(SaleCloudInterfaceRecordDO.class);
        DomainUtils.initUpdateTimeInfo(saleCloudInterfaceRecordDo);
        return saleCloudInterfaceRecordDao.updateById(saleCloudInterfaceRecordDo) > 0;
    }

}