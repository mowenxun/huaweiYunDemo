package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationDO;
import com.deepexi.dd.middle.order.domain.ShopSupplerRelationQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * ShopSupplerRelationMapper
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Mapper
public interface ShopSupplerRelationMapper extends BaseMapper<ShopSupplerRelationDO> {

    /**
     * 查询已分发订单和店铺订单关系表
     *
     * @param query
     * @return
     */
    List<ShopSupplerRelationDO> findAll(ShopSupplerRelationQuery query);

    /**
     * 删除已分发订单和店铺订单关系表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新已分发订单和店铺订单关系表
     *
     * @param list
     * @return
     */
    int updateBatch(List<ShopSupplerRelationDO> list);

    /**
     * 批量新增已分发订单和店铺订单关系表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<ShopSupplerRelationDO> list);
}