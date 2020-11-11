package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SalePickReceiveOrderYunLogDAO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogDTO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogQuery;
import com.deepexi.dd.middle.order.service.SalePickReceiveOrderYunLogService;
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
 * SalePickReceiveOrderYunLogServiceImpl
 *
 * @author admin
 * @date Wed Sep 23 13:47:55 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SalePickReceiveOrderYunLogServiceImpl implements SalePickReceiveOrderYunLogService {

    @Autowired
    private SalePickReceiveOrderYunLogDAO salePickReceiveOrderYunLogDao;

    @Override
    public List<SalePickReceiveOrderYunLogDTO> listSalePickReceiveOrderYunLogs(SalePickReceiveOrderYunLogQuery query) {
        return ObjectCloneUtils.convertList(salePickReceiveOrderYunLogDao.listSalePickReceiveOrderYunLogs(query), SalePickReceiveOrderYunLogDTO.class);
    }


    @Override
    public PageBean<SalePickReceiveOrderYunLogDTO> listSalePickReceiveOrderYunLogsPage(SalePickReceiveOrderYunLogQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(salePickReceiveOrderYunLogDao.listSalePickReceiveOrderYunLogs(query)), SalePickReceiveOrderYunLogDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除,传入ID为空");
            return false;
        }
        return salePickReceiveOrderYunLogDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SalePickReceiveOrderYunLogDTO insert(SalePickReceiveOrderYunLogDTO record) {
        if (record == null) {
            log.error("新增,传入参数为空");
            return null;
        }
        SalePickReceiveOrderYunLogDO salePickReceiveOrderYunLogDo = record.clone(SalePickReceiveOrderYunLogDO.class);
        DomainUtils.initDomainCreatedInfo(salePickReceiveOrderYunLogDo);
        if (salePickReceiveOrderYunLogDao.insert(salePickReceiveOrderYunLogDo) > 0) {
            record.setId(salePickReceiveOrderYunLogDo.getId());
            return record;
        }
        log.error("新增失败");
        return null;
    }

    @Override
    public SalePickReceiveOrderYunLogDTO selectById(Long id) {
        if (id == null) {
            log.error("查询,传入ID为空");
            return null;
        }
        SalePickReceiveOrderYunLogDO salePickReceiveOrderYunLogDo = salePickReceiveOrderYunLogDao.selectById(id);
        if (salePickReceiveOrderYunLogDo == null) {
            return null;
        }
        return salePickReceiveOrderYunLogDo.clone(SalePickReceiveOrderYunLogDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SalePickReceiveOrderYunLogDTO record) {
        if (id == null) {
            log.error("修改,传入ID为空");
            return false;
        }
        record.setId(id);
        SalePickReceiveOrderYunLogDO salePickReceiveOrderYunLogDo = record.clone(SalePickReceiveOrderYunLogDO.class);
        DomainUtils.initUpdateTimeInfo(salePickReceiveOrderYunLogDo);
        return salePickReceiveOrderYunLogDao.updateById(salePickReceiveOrderYunLogDo) > 0;
    }

}