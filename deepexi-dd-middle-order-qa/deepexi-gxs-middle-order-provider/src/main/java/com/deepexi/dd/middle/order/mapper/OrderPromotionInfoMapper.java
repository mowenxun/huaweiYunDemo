package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderPromotionInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderPromotionInfoMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderPromotionInfoMapper extends BaseMapper<OrderPromotionInfoDO> {

    /**
     * 查询销售订单的促销信息表
     *
     * @param query
     * @return
     */
    List<OrderPromotionInfoDO> findAll(OrderPromotionInfoQuery query);

    /**
     * 删除销售订单的促销信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售订单的促销信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderPromotionInfoDO> list);

    /**
     * 批量新增销售订单的促销信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderPromotionInfoDO> list);
}