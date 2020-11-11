package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SalePickGoodsOrderMapper
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
@Mapper
public interface SalePickGoodsOrderMapper extends BaseMapper<SalePickGoodsOrderDO> {

    /**
     * 查询提货单据所关联的订单的信息表
     *
     * @param query
     * @return
     */
    List<SalePickGoodsOrderDO> findAll(SalePickGoodsOrderQuery query);

    /**
     * 删除提货单据所关联的订单的信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新提货单据所关联的订单的信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SalePickGoodsOrderDO> list);

    /**
     * 批量新增提货单据所关联的订单的信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SalePickGoodsOrderDO> list);
}