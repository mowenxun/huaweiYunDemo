package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * SalePickGoodsOrderSkuDAO
 *
 * @author admin
 * @date Thu Aug 13 07:37:26 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsOrderSkuDAO extends IService<SalePickGoodsOrderSkuDO> {

    /**
     * 查询提货单据所关联的订单所关联的sku信息表列表}
     *
     * @return
     */
    List<SalePickGoodsOrderSkuDO> listSalePickGoodsOrderSkus(SalePickGoodsOrderSkuQuery query);

    /**
     * 根据ID删除提货单据所关联的订单所关联的sku信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增提货单据所关联的订单所关联的sku信息表
     *
     * @param record
     * @return
     */
    int insert(SalePickGoodsOrderSkuDO record);

    /**
     * 查询提货单据所关联的订单所关联的sku信息表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsOrderSkuDO selectById(Long id);

    /**
     * 根据ID修改提货单据所关联的订单所关联的sku信息表
     *
     * @param record
     * @return
     */
//    Boolean updateById(SalePickGoodsOrderSkuDO record);

    /**
     * 新增提货单据所关联的订单所关联的sku信息表
     *
     * @param skuDOS
     * @return
     */
    Integer insertBatch(ArrayList<SalePickGoodsOrderSkuDO> skuDOS);

    SalePickGoodsOrderSkuResponseDTO selectByRowCode(String rowCode);
}