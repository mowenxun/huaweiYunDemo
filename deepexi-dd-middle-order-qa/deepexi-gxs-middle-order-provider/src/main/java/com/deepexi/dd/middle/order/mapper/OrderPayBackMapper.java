package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderPayBackDO;
import com.deepexi.dd.middle.order.domain.OrderPayBackQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderPayBackMapper
 *
 * @author admin
 * @date Wed Jul 22 16:27:51 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderPayBackMapper extends BaseMapper<OrderPayBackDO> {

    /**
     * 查询支付回调参数表
     *
     * @param query
     * @return
     */
    List<OrderPayBackDO> findAll(OrderPayBackQuery query);

    /**
     * 删除支付回调参数表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新支付回调参数表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderPayBackDO> list);

    /**
     * 批量新增支付回调参数表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderPayBackDO> list);
}