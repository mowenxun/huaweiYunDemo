package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationResponseDTO;
import com.deepexi.dd.middle.order.domain.query.ShopSupplerRelationRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
public interface ShopSupplerRelationDomainService {
    /**
     * 查询已分发订单和店铺订单关系表列表
     *
     * @return
     */
    List<ShopSupplerRelationResponseDTO> listShopSupplerRelations(ShopSupplerRelationRequestQuery query);

    /**
     * 分页查询已分发订单和店铺订单关系表列表
     *
     * @return
     */
    PageBean<ShopSupplerRelationResponseDTO> listShopSupplerRelationsPage(ShopSupplerRelationRequestQuery query);


    /**
     * 根据ID删除已分发订单和店铺订单关系表
     *
     * @return
     */
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增已分发订单和店铺订单关系表
     *
     * @param record
     * @return
     */
    ShopSupplerRelationResponseDTO insert(@RequestBody ShopSupplerRelationRequestDTO record);

    /**
     * 查询已分发订单和店铺订单关系表详情
     *
     * @param id
     * @return
     */
    ShopSupplerRelationResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改已分发订单和店铺订单关系表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(@PathVariable Long id, @RequestBody ShopSupplerRelationRequestDTO record);

    boolean saveBatch(@RequestBody List<ShopSupplerRelationRequestDTO> list);

    public List<ShopSupplerRelationResponseDTO> listBySupplierOrderCode(String supplierOrderCode) ;
    public List<ShopSupplerRelationResponseDTO> listBySupplierOrderId(Long supplierOrderId) ;
}

