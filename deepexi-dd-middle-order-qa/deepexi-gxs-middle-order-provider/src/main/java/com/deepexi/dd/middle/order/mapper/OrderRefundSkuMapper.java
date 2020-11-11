package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderRefundSkuDO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderRefundSkuMapper
 *
 * @author admin
 * @date Wed Aug 19 16:31:13 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderRefundSkuMapper extends BaseMapper<OrderRefundSkuDO> {

    /**
     * 查询
     *
     * @param query
     * @return
     */
    List<OrderRefundSkuDO> findAll(OrderRefundSkuQuery query);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderRefundSkuDO> list);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderRefundSkuDO> list);
}