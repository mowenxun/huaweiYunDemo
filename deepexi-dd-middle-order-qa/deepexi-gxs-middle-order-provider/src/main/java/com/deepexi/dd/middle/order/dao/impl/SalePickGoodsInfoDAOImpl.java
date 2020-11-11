package com.deepexi.dd.middle.order.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.dd.middle.order.dao.SalePickGoodsInfoDAO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoAmountEditDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.mapper.SalePickGoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * SalePickGoodsInfoDAOImpl
 *
 * @author admin
 * @date Wed Aug 12 19:18:27 CST 2020
 * @version 1.0
 */
@Repository
public class SalePickGoodsInfoDAOImpl extends ServiceImpl<SalePickGoodsInfoMapper, SalePickGoodsInfoDO> implements SalePickGoodsInfoDAO {
    @Autowired
    private SalePickGoodsInfoMapper salePickGoodsInfoMapper;

    @Override
    public List<SalePickGoodsInfoDO> listSalePickGoodsInfos(SalePickGoodsInfoQuery query) {
        List<SalePickGoodsInfoDO> all = salePickGoodsInfoMapper.findAll(query);
        return all;
    }

    @Override
    public int deleteByIdIn(List<Long> id) {
        return salePickGoodsInfoMapper.deleteByIdIn(id);
    }

    @Override
    public int insert(SalePickGoodsInfoDO record) {
        ArrayList<SalePickGoodsInfoDO> salePickGoodsInfoDOS = new ArrayList<>();
        salePickGoodsInfoDOS.add(record);
        salePickGoodsInfoMapper.batchInsert(salePickGoodsInfoDOS);
        return 1;
    }

    @Override
    public SalePickGoodsInfoDO selectById(Long id) {
        return salePickGoodsInfoMapper.selectById(id);
    }

    @Override
    public boolean updateById(SalePickGoodsInfoDO record) {
        return salePickGoodsInfoMapper.updateById(record)>0;
    }

    @Override
    public int updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO) {
        return salePickGoodsInfoMapper.updateOrderStatus(saleOrderInfoStatusEditRequestDTO);
    }

    @Override
    public List<SalePickGoodsOrderSkuResponseDTO> searchPickGoodsList(Long saleOrderId) {
        return salePickGoodsInfoMapper.searchPickGoodsList(saleOrderId);
    }

    @Override
    public SalePickGoodsInfoResponseDTO selectByPickCode(SalePickGoodsInfoRequestQuery query) {
        return salePickGoodsInfoMapper.selectByPickCode(query);
    }

    @Override
    public int updateOrderAmount(SalePickGoodsInfoAmountEditDTO salePickGoodsInfoAmountEditDTO) {
        return salePickGoodsInfoMapper.updateOrderAmount(salePickGoodsInfoAmountEditDTO);
    }
}