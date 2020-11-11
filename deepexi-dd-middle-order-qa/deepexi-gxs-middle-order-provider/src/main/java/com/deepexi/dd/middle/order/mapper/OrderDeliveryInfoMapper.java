package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderDeliveryInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderDeliveryInfoMapper
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderDeliveryInfoMapper extends BaseMapper<OrderDeliveryInfoDO> {

    /**
     * 查询销售出库单物流信息
     *
     * @param query
     * @return
     */
    List<OrderDeliveryInfoDO> findAll(OrderDeliveryInfoQuery query);

    /**
     * 删除销售出库单物流信息
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售出库单物流信息
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderDeliveryInfoDO> list);

    /**
     * 批量新增销售出库单物流信息
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderDeliveryInfoDO> list);
}