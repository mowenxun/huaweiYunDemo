package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.ShopOrderDO;
import com.deepexi.dd.middle.order.domain.ShopOrderQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * ShopOrderMapper
 *
 * @author admin
 * @date Tue Oct 13 14:53:15 CST 2020
 * @version 1.0
 */
@Mapper
public interface ShopOrderMapper extends BaseMapper<ShopOrderDO> {

    /**
     * 查询店铺订单
     *
     * @param query
     * @return
     */
    List<ShopOrderDO> findAll(ShopOrderQuery query);

    /**
     * 删除店铺订单
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新店铺订单
     *
     * @param list
     * @return
     */
    int updateBatch(List<ShopOrderDO> list);

    /**
     * 批量新增店铺订单
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<ShopOrderDO> list);

    /**
     * 根据批量更新门店状态bycode
     * @param shopOrderDos
     * @return
     */
    int updateBatchByCode(@Param("list") List<ShopOrderDO> shopOrderDos);
}