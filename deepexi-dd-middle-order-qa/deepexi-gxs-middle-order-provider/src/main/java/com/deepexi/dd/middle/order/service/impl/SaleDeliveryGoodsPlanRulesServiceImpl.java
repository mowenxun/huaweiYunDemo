package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryGoodsPlanRulesDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesDO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesDTO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesQuery;
import com.deepexi.dd.middle.order.service.SaleDeliveryGoodsPlanRulesService;
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
 * SaleDeliveryGoodsPlanRulesServiceImpl
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SaleDeliveryGoodsPlanRulesServiceImpl implements SaleDeliveryGoodsPlanRulesService {

    @Autowired
    private SaleDeliveryGoodsPlanRulesDAO saleDeliveryGoodsPlanRulesDao;

    @Override
    public List<SaleDeliveryGoodsPlanRulesDTO> listSaleDeliveryGoodsPlanRules(SaleDeliveryGoodsPlanRulesQuery query) throws Exception{
        return ObjectCloneUtils.convertList(saleDeliveryGoodsPlanRulesDao.listSaleDeliveryGoodsPlanRules(query), SaleDeliveryGoodsPlanRulesDTO.class);
    }


    @Override
    public PageBean<SaleDeliveryGoodsPlanRulesDTO> listSaleDeliveryGoodsPlanRulesPage(SaleDeliveryGoodsPlanRulesQuery query) throws Exception{
        PageMethod.startPage(query.getPage(), query.getSize(),"updated_time desc");
        return ObjectCloneUtils.convertPageBean(new PageBean<>(saleDeliveryGoodsPlanRulesDao.listSaleDeliveryGoodsPlanRules(query)), SaleDeliveryGoodsPlanRulesDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) throws Exception{
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除发货计划编排规则,传入ID为空");
            return false;
        }
        return saleDeliveryGoodsPlanRulesDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SaleDeliveryGoodsPlanRulesDTO insert(SaleDeliveryGoodsPlanRulesDTO record) throws Exception{
        if (record == null) {
            log.error("新增发货计划编排规则,传入参数为空");
            return null;
        }
        SaleDeliveryGoodsPlanRulesDO saleDeliveryGoodsPlanRulesDo = record.clone(SaleDeliveryGoodsPlanRulesDO.class);
        DomainUtils.initDomainCreatedInfo(saleDeliveryGoodsPlanRulesDo);
        if (saleDeliveryGoodsPlanRulesDao.insert(saleDeliveryGoodsPlanRulesDo) > 0) {
            record.setId(saleDeliveryGoodsPlanRulesDo.getId());
            return record;
        }
        log.error("新增发货计划编排规则失败");
        return null;
    }

    @Override
    public SaleDeliveryGoodsPlanRulesDTO selectById(Long id) throws Exception{
        if (id == null) {
            log.error("查询发货计划编排规则,传入ID为空");
            return null;
        }
        SaleDeliveryGoodsPlanRulesDO saleDeliveryGoodsPlanRulesDo = saleDeliveryGoodsPlanRulesDao.selectById(id);
        if (saleDeliveryGoodsPlanRulesDo == null) {
            return null;
        }
        return saleDeliveryGoodsPlanRulesDo.clone(SaleDeliveryGoodsPlanRulesDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SaleDeliveryGoodsPlanRulesDTO record) throws Exception{
        if (id == null) {
            log.error("修改发货计划编排规则,传入ID为空");
            return false;
        }
        record.setId(id);
        SaleDeliveryGoodsPlanRulesDO saleDeliveryGoodsPlanRulesDo = record.clone(SaleDeliveryGoodsPlanRulesDO.class);
        DomainUtils.initUpdateTimeInfo(saleDeliveryGoodsPlanRulesDo);
        return saleDeliveryGoodsPlanRulesDao.updateById(saleDeliveryGoodsPlanRulesDo) > 0;
    }

}