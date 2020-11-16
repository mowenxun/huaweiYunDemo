package com.deepexi.dd.domain.transaction.service.impl;

import cn.hutool.core.util.StrUtil;
import com.deepexi.dd.domain.transaction.remote.gxs.ShopSupplerRelationRemote;
import com.deepexi.dd.domain.transaction.service.ShopSupplerRelationDomainService;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationResponseDTO;
import com.deepexi.dd.middle.order.domain.query.ShopSupplerRelationRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class ShopSupplerRelationDomainServiceImpl implements ShopSupplerRelationDomainService {

    @Autowired
    ShopSupplerRelationRemote shopSupplerRelationRemote;


    @Override
    public List<ShopSupplerRelationResponseDTO> listShopSupplerRelations(ShopSupplerRelationRequestQuery query) {
        return shopSupplerRelationRemote.listShopSupplerRelations(query);
    }

    @Override
    public PageBean<ShopSupplerRelationResponseDTO> listShopSupplerRelationsPage(ShopSupplerRelationRequestQuery query) {
        return shopSupplerRelationRemote.listShopSupplerRelationsPage(query);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        return shopSupplerRelationRemote.deleteByIdIn(id);
    }

    @Override
    public ShopSupplerRelationResponseDTO insert(ShopSupplerRelationRequestDTO record) {
        return shopSupplerRelationRemote.insert(record);
    }

    @Override
    public ShopSupplerRelationResponseDTO selectById(Long id) {
        return shopSupplerRelationRemote.selectById(id);
    }
    @Override
    public List<ShopSupplerRelationResponseDTO> listBySupplierOrderCode(String supplierOrderCode) {
        if (StrUtil.isEmpty(supplierOrderCode)) {
            return new ArrayList<>(0);
        }
        ShopSupplerRelationRequestQuery q = new ShopSupplerRelationRequestQuery();
        q.setSupplerOrderCode(supplierOrderCode);
        return shopSupplerRelationRemote.listShopSupplerRelations(q);
    }
    @Override
    public List<ShopSupplerRelationResponseDTO> listBySupplierOrderId(Long supplierOrderId) {
        if (supplierOrderId==null) {
            return new ArrayList<>(0);
        }
        ShopSupplerRelationRequestQuery q = new ShopSupplerRelationRequestQuery();
        q.setSupplerOrderId(supplierOrderId);
        return shopSupplerRelationRemote.listShopSupplerRelations(q);
    }

    @Override
    public Boolean updateById(Long id, ShopSupplerRelationRequestDTO record) {
        return shopSupplerRelationRemote.updateById(id,record);
    }

    @Override
    public boolean saveBatch(List<ShopSupplerRelationRequestDTO> list) {
        return shopSupplerRelationRemote.saveBatch(list);
    }
}
