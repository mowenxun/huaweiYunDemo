package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.SaleOutTaskDetailInfoDAO;
import com.deepexi.dd.middle.order.dao.SalePickGoodsOrderSkuDAO;
import com.deepexi.dd.middle.order.domain.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.service.SalePickGoodsOrderSkuService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * SalePickGoodsOrderSkuServiceImpl
 *
 * @author admin
 * @date Thu Aug 13 07:37:26 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class SalePickGoodsOrderSkuServiceImpl implements SalePickGoodsOrderSkuService {

    @Autowired
    private SalePickGoodsOrderSkuDAO salePickGoodsOrderSkuDao;

    @Autowired
    private SaleOutTaskDetailInfoDAO saleOutTaskDetailInfoDAO;

    @Override
    public List<SalePickGoodsOrderSkuDTO> listSalePickGoodsOrderSkus(SalePickGoodsOrderSkuQuery query) {
        return ObjectCloneUtils.convertList(salePickGoodsOrderSkuDao.listSalePickGoodsOrderSkus(query), SalePickGoodsOrderSkuDTO.class);
    }


    @Override
    public PageBean<SalePickGoodsOrderSkuDTO> listSalePickGoodsOrderSkusPage(SalePickGoodsOrderSkuQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(salePickGoodsOrderSkuDao.listSalePickGoodsOrderSkus(query)), SalePickGoodsOrderSkuDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除提货单据所关联的订单所关联的sku信息表,传入ID为空");
            return false;
        }
        return salePickGoodsOrderSkuDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public SalePickGoodsOrderSkuDTO insert(SalePickGoodsOrderSkuDTO record) {
        if (record == null) {
            log.error("新增提货单据所关联的订单所关联的sku信息表,传入参数为空");
            return null;
        }
        SalePickGoodsOrderSkuDO salePickGoodsOrderSkuDo = record.clone(SalePickGoodsOrderSkuDO.class);
        DomainUtils.initDomainCreatedInfo(salePickGoodsOrderSkuDo);
        if (salePickGoodsOrderSkuDao.insert(salePickGoodsOrderSkuDo) > 0) {
            record.setId(salePickGoodsOrderSkuDo.getId());
            return record;
        }
        log.error("新增提货单据所关联的订单所关联的sku信息表失败");
        return null;
    }

    @Override
    public Integer insertBatch(ArrayList<SalePickGoodsOrderSkuDTO> skulist) {
        if(skulist == null || skulist.size() == 0){
            return 0;
        }
        ArrayList<SalePickGoodsOrderSkuDO> skuDOS = new ArrayList<>();
        for(SalePickGoodsOrderSkuDTO sku : skulist){
            SalePickGoodsOrderSkuDO skuDO  = sku.clone(SalePickGoodsOrderSkuDO.class);
            DomainUtils.initDomainCreatedInfo(skuDO);
            skuDOS.add(skuDO);
        }
        Integer num = salePickGoodsOrderSkuDao.insertBatch(skuDOS);
        return num;
    }

    @Override
    public SalePickGoodsOrderSkuDTO selectById(Long id) {
        if (id == null) {
            log.error("查询提货单据所关联的订单所关联的sku信息表,传入ID为空");
            return null;
        }
        SalePickGoodsOrderSkuDO salePickGoodsOrderSkuDo = salePickGoodsOrderSkuDao.selectById(id);
        if (salePickGoodsOrderSkuDo == null) {
            return null;
        }
        return salePickGoodsOrderSkuDo.clone(SalePickGoodsOrderSkuDTO.class);
    }

    @Override
    public Boolean updateById(Long id, SalePickGoodsOrderSkuDTO record) {
        if (id == null) {
            log.error("修改提货单据所关联的订单所关联的sku信息表,传入ID为空");
            return false;
        }
        record.setId(id);
        SalePickGoodsOrderSkuDO salePickGoodsOrderSkuDo = record.clone(SalePickGoodsOrderSkuDO.class);
        DomainUtils.initUpdateTimeInfo(salePickGoodsOrderSkuDo);
        return salePickGoodsOrderSkuDao.updateById(salePickGoodsOrderSkuDo);
    }

    @Override
    public SalePickGoodsOrderSkuResponseDTO selectByRowCode(String rowCode) {
        return salePickGoodsOrderSkuDao.selectByRowCode(rowCode);
    }


    @Override
    public Boolean deleteByWrapper(QueryWrapper wrapper) {
        if(wrapper == null){
            return false;
        }
        return salePickGoodsOrderSkuDao.remove(wrapper);
    }


    @Override
    public List<SaleOutTaskDetailInfoRequestDTO> getOutItemBySaleItemId(List<Long> saleItemIds) {
        QueryWrapper<SaleOutTaskDetailInfoDO> queryWrapperb = new QueryWrapper();
        queryWrapperb.lambda().in(SaleOutTaskDetailInfoDO::getSaleOrderItemId, saleItemIds);
        List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOS = saleOutTaskDetailInfoDAO.list(queryWrapperb);
        List<SaleOutTaskDetailInfoRequestDTO> dtos = ObjectCloneUtils.convertList(saleOutTaskDetailInfoDOS, SaleOutTaskDetailInfoRequestDTO.class);
        return dtos;
    }

}