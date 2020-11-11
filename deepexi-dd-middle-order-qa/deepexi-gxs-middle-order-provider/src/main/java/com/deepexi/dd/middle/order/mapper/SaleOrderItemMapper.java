package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderSkuDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SaleOrderItemMapper
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleOrderItemMapper extends BaseMapper<SaleOrderItemDO> {

    /**
     * 查询销售订单明细表
     *
     * @param query
     * @return
     */
    List<SaleOrderItemDO> findAll(SaleOrderItemMiddleRequestQuery query);

    /**
     * 删除销售订单明细表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售订单明细表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOrderItemDO> list);

    /**
     * 更新销售订单的可提货数量
     * @return
     */
    void updateAvailablePickNum(SalePickGoodsOrderSkuDTO salePickGoodsOrderSkuDTO);

    /**
     * 批量新增销售订单明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOrderItemDO> list);
}