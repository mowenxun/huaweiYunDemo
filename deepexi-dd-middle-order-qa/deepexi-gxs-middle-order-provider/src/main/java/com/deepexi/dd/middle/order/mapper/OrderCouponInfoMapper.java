package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderCouponInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderCouponInfoMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderCouponInfoMapper extends BaseMapper<OrderCouponInfoDO> {

    /**
     * 查询优惠信息表
     *
     * @param query
     * @return
     */
    List<OrderCouponInfoDO> findAll(OrderCouponInfoQuery query);

    /**
     * 删除优惠信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新优惠信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderCouponInfoDO> list);

    /**
     * 批量新增优惠信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderCouponInfoDO> list);
}