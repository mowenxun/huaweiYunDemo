package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SaleOrderInfoDAO;
import com.deepexi.dd.middle.order.domain.SaleOrderInfoDO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoAmountEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoPayOrderCodeEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoResQuery;
import com.deepexi.dd.middle.order.mapper.SaleOrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SaleOrderInfoDAOImpl
 *
 * @author admin
 * @date Tue Jun 30 11:43:59 CST 2020
 * @version 1.0
 */
@Repository
public class SaleOrderInfoDAOImpl extends ServiceImpl<SaleOrderInfoMapper, SaleOrderInfoDO> implements SaleOrderInfoDAO {
    /*@Autowired
    private SaleOrderInfoMapper saleOrderInfoMapper;*/

    @Override
    public List<SaleOrderInfoResponseDTO> findList(SaleOrderInfoResQuery query) {
        return baseMapper.findList(query);
    }

    @Override
    public List<SaleOrderInfoDO> listSaleOrderInfos(SaleOrderInfoQuery query) {
        List<SaleOrderInfoDO> all = baseMapper.findAll(query);
        return all;
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return baseMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SaleOrderInfoDO record) {
        return baseMapper.insert(record);
    }

    @Override
    public SaleOrderInfoDO selectById(Long id) {
        return baseMapper.selectById(id);
    }

   /* @Override
    public int updateById(SaleOrderInfoDO record) {
        return baseMapper.updateById(record);
    }*/

    @Override
    public int updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO) {
        return baseMapper.updateOrderStatus(saleOrderInfoStatusEditRequestDTO);
    }

    @Override
   public int updateOrderAmount(SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO){
        return baseMapper.updateOrderAmount(saleOrderInfoAmountEditDTO);
    }

    @Override
    public Boolean updatePayOrderCode(SaleOrderInfoPayOrderCodeEditDTO saleOrderInfoPayOrderCodeEditDTO) {
        return baseMapper.updatePayOrderCode(saleOrderInfoPayOrderCodeEditDTO);
    }

    @Override
    public List<SaleOrderInfoDO> getPreMonthPlanOrder() {
        return baseMapper.getPreMonthPlanOrder();
    }
}