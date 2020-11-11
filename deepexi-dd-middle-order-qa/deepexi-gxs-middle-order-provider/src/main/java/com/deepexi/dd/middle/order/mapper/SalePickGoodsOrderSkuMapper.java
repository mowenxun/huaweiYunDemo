package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuQuery;

import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SalePickGoodsOrderSkuMapper
 *
 * @author admin
 * @date Thu Aug 13 07:37:26 CST 2020
 * @version 1.0
 */
@Mapper
public interface SalePickGoodsOrderSkuMapper extends BaseMapper<SalePickGoodsOrderSkuDO> {

    /**
     * 查询提货单据所关联的订单所关联的sku信息表
     *
     * @param query
     * @return
     */
    List<SalePickGoodsOrderSkuDO> findAll(SalePickGoodsOrderSkuQuery query);

    /**
     * 删除提货单据所关联的订单所关联的sku信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新提货单据所关联的订单所关联的sku信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SalePickGoodsOrderSkuDO> list);

    /**
     * 批量新增提货单据所关联的订单所关联的sku信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SalePickGoodsOrderSkuDO> list);

    SalePickGoodsOrderSkuDO selectByRowCode(@Param("rowCode") String rowCode);
}