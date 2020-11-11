package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderExpenseInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderExpenseInfoMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderExpenseInfoMapper extends BaseMapper<OrderExpenseInfoDO> {

    /**
     * 查询销售订单的费用信息表
     *
     * @param query
     * @return
     */
    List<OrderExpenseInfoDO> findAll(OrderExpenseInfoQuery query);

    /**
     * 删除销售订单的费用信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售订单的费用信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderExpenseInfoDO> list);

    /**
     * 批量新增销售订单的费用信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderExpenseInfoDO> list);
}