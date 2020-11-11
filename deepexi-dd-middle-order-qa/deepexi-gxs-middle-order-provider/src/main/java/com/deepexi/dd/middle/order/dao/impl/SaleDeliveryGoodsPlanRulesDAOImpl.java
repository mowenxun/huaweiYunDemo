package com.deepexi.dd.middle.order.dao.impl;

import com.deepexi.dd.middle.order.dao.SaleDeliveryGoodsPlanRulesDAO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesDO;
import com.deepexi.dd.middle.order.domain.SaleDeliveryGoodsPlanRulesQuery;
import com.deepexi.dd.middle.order.mapper.SaleDeliveryGoodsPlanRulesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleDeliveryGoodsPlanRulesDAOImpl
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
@Repository
public class SaleDeliveryGoodsPlanRulesDAOImpl implements SaleDeliveryGoodsPlanRulesDAO {
    @Autowired
    private SaleDeliveryGoodsPlanRulesMapper saleDeliveryGoodsPlanRulesMapper;

    @Override
    public List<SaleDeliveryGoodsPlanRulesDO> listSaleDeliveryGoodsPlanRules(SaleDeliveryGoodsPlanRulesQuery query) {
        return saleDeliveryGoodsPlanRulesMapper.findAll(query);
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return saleDeliveryGoodsPlanRulesMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleDeliveryGoodsPlanRulesDO record) {
        return saleDeliveryGoodsPlanRulesMapper.insert(record);
    }

    @Override
    public SaleDeliveryGoodsPlanRulesDO selectById(Long id) {
        return saleDeliveryGoodsPlanRulesMapper.selectById(id);
    }

    @Override
    public int updateById(SaleDeliveryGoodsPlanRulesDO record) {
        return saleDeliveryGoodsPlanRulesMapper.updateById(record);
    }

}