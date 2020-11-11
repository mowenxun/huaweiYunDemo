package com.deepexi.dd.middle.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.util.pageHelper.PageBean;

import java.util.ArrayList;
import java.util.List;
/**
 * SalePickGoodsOrderSkuService
 *
 * @author admin
 * @date Thu Aug 13 07:37:26 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsOrderSkuService {


    /**
     * 查询提货单据所关联的订单所关联的sku信息表列表
     *
     * @return
     */
    List<SalePickGoodsOrderSkuDTO> listSalePickGoodsOrderSkus(SalePickGoodsOrderSkuQuery query);

    /**
     * 分页查询提货单据所关联的订单所关联的sku信息表列表
     *
     * @return
     */
    PageBean<SalePickGoodsOrderSkuDTO> listSalePickGoodsOrderSkusPage(SalePickGoodsOrderSkuQuery query);

    /**
     * 根据ID删除提货单据所关联的订单所关联的sku信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增提货单据所关联的订单所关联的sku信息表
     *
     * @param record
     * @return
     */
    SalePickGoodsOrderSkuDTO insert(SalePickGoodsOrderSkuDTO record);

    /**
     * 批量新增提货单据所关联的订单所关联的sku信息表
     * @param skulist
     * @return
     */
    Integer insertBatch(ArrayList<SalePickGoodsOrderSkuDTO> skulist);

    /**
     * 查询提货单据所关联的订单所关联的sku信息表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsOrderSkuDTO selectById(Long id);


    /**
     * 根据ID修改提货单据所关联的订单所关联的sku信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SalePickGoodsOrderSkuDTO record);

    Boolean deleteByWrapper(QueryWrapper<SalePickGoodsOrderDO> wrapper);

    SalePickGoodsOrderSkuResponseDTO selectByRowCode(String rowCode);

    List<SaleOutTaskDetailInfoRequestDTO> getOutItemBySaleItemId(List<Long> saleItemIds);
}