package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.ShopOrderItemDO;
import com.deepexi.dd.middle.order.domain.ShopOrderItemQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * ShopOrderItemMapper
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Mapper
public interface ShopOrderItemMapper extends BaseMapper<ShopOrderItemDO> {

    /**
     * 查询店铺订单明细表
     *
     * @param query
     * @return
     */
    List<ShopOrderItemDO> findAll(ShopOrderItemQuery query);

    /**
     * 删除店铺订单明细表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新店铺订单明细表
     *
     * @param list
     * @return
     */
    int updateBatch(List<ShopOrderItemDO> list);

    /**
     * 批量新增店铺订单明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<ShopOrderItemDO> list);
}