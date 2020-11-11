package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SalePickOrderYunLogDAO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogDTO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogQuery;
import com.deepexi.dd.middle.order.service.SalePickOrderYunLogService;
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
 * SalePickOrderYunLogServiceImpl
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SalePickOrderYunLogServiceImpl implements SalePickOrderYunLogService {

    @Autowired
    private SalePickOrderYunLogDAO salePickOrderYunLogDao;

    @Override
    public List<SalePickOrderYunLogDTO> listSalePickOrderYunLogs(SalePickOrderYunLogQuery query) {
        return ObjectCloneUtils.convertList(salePickOrderYunLogDao.listSalePickOrderYunLogs(query), SalePickOrderYunLogDTO.class);
    }


    @Override
    public PageBean<SalePickOrderYunLogDTO> listSalePickOrderYunLogsPage(SalePickOrderYunLogQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(salePickOrderYunLogDao.listSalePickOrderYunLogs(query)), SalePickOrderYunLogDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除,传入ID为空");
            return false;
        }
        return salePickOrderYunLogDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SalePickOrderYunLogDTO insert(SalePickOrderYunLogDTO record) {
        if (record == null) {
            log.error("新增,传入参数为空");
            return null;
        }
        SalePickOrderYunLogDO salePickOrderYunLogDo = record.clone(SalePickOrderYunLogDO.class);
        DomainUtils.initDomainCreatedInfo(salePickOrderYunLogDo);
        if (salePickOrderYunLogDao.insert(salePickOrderYunLogDo) > 0) {
            record.setId(salePickOrderYunLogDo.getId());
            return record;
        }
        log.error("新增失败");
        return null;
    }

    @Override
    public SalePickOrderYunLogDTO selectById(Long id) {
        if (id == null) {
            log.error("查询,传入ID为空");
            return null;
        }
        SalePickOrderYunLogDO salePickOrderYunLogDo = salePickOrderYunLogDao.selectById(id);
        if (salePickOrderYunLogDo == null) {
            return null;
        }
        return salePickOrderYunLogDo.clone(SalePickOrderYunLogDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SalePickOrderYunLogDTO record) {
        if (id == null) {
            log.error("修改,传入ID为空");
            return false;
        }
        record.setId(id);
        SalePickOrderYunLogDO salePickOrderYunLogDo = record.clone(SalePickOrderYunLogDO.class);
        DomainUtils.initUpdateTimeInfo(salePickOrderYunLogDo);
        return salePickOrderYunLogDao.updateById(salePickOrderYunLogDo) > 0;
    }

}