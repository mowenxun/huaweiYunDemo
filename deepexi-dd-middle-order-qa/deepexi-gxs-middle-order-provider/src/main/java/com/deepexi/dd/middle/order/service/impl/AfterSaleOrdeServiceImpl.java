package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.AfterSaleOrdeDAO;
import com.deepexi.dd.middle.order.domain.AfterSaleOrdeDO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeQuery;
import com.deepexi.dd.middle.order.service.AfterSaleOrdeService;
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
 * AfterSaleOrdeServiceImpl
 *
 * @author admin
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class AfterSaleOrdeServiceImpl implements AfterSaleOrdeService {

    @Autowired
    private AfterSaleOrdeDAO afterSaleOrdeDao;

    @Override
    public List<AfterSaleOrdeDTO> listAfterSaleOrdes(AfterSaleOrdeQuery query) {
        return ObjectCloneUtils.convertList(afterSaleOrdeDao.listAfterSaleOrdes(query), AfterSaleOrdeDTO.class);
    }


    @Override
    public PageBean<AfterSaleOrdeDTO> listAfterSaleOrdesPage(AfterSaleOrdeQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(afterSaleOrdeDao.listAfterSaleOrdes(query)), AfterSaleOrdeDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除售后申请单,传入ID为空");
            return false;
        }
        return afterSaleOrdeDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public AfterSaleOrdeDTO insert(AfterSaleOrdeDTO record) {
        if (record == null) {
            log.error("新增售后申请单,传入参数为空");
            return null;
        }
        AfterSaleOrdeDO afterSaleOrdeDo = record.clone(AfterSaleOrdeDO.class);
        DomainUtils.initDomainCreatedInfo(afterSaleOrdeDo);
        if (afterSaleOrdeDao.insert(afterSaleOrdeDo) > 0) {
            record.setId(afterSaleOrdeDo.getId());
            return record;
        }
        log.error("新增售后申请单失败");
        return null;
    }

    @Override
    public AfterSaleOrdeDTO selectById(Long id) {
        if (id == null) {
            log.error("查询售后申请单,传入ID为空");
            return null;
        }
        AfterSaleOrdeDO afterSaleOrdeDo = afterSaleOrdeDao.selectById(id);
        if (afterSaleOrdeDo == null) {
            return null;
        }
        return afterSaleOrdeDo.clone(AfterSaleOrdeDTO.class);
    }

    @Override
    public Boolean updateById(Long id, AfterSaleOrdeDTO record) {
        if (id == null) {
            log.error("修改售后申请单,传入ID为空");
            return false;
        }
        record.setId(id);
        AfterSaleOrdeDO afterSaleOrdeDo = record.clone(AfterSaleOrdeDO.class);
        DomainUtils.initUpdateTimeInfo(afterSaleOrdeDo);
        return afterSaleOrdeDao.updateById(afterSaleOrdeDo) > 0;
    }

}