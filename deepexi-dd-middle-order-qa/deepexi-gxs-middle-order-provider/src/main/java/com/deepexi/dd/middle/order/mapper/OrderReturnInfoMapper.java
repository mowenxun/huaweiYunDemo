package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderReturnInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderReturnInfoMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderReturnInfoMapper extends BaseMapper<OrderReturnInfoDO> {

    /**
     * 查询销售订单退货单信息表
     *
     * @param query
     * @return
     */
    List<OrderReturnInfoDO> findAll(OrderReturnInfoQuery query);

    /**
     * 删除销售订单退货单信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售订单退货单信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderReturnInfoDO> list);

    /**
     * 批量新增销售订单退货单信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderReturnInfoDO> list);
}