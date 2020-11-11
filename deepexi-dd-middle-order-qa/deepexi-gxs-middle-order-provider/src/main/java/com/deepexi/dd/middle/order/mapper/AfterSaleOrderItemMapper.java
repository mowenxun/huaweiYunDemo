package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.deepexi.dd.middle.order.domain.AfterSaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * AfterSaleOrderItemMapper
 *
 * @author admin
 * @date Fri Oct 16 14:17:13 CST 2020
 * @version 1.0
 */
@Mapper
public interface AfterSaleOrderItemMapper extends BaseMapper<AfterSaleOrderItemDO> {

    /**
     * 查询售后订单明细表
     *
     * @param query
     * @return
     */
    List<AfterSaleOrderItemDO> findAll(AfterSaleOrderItemQuery query);

    /**
     * 删除售后订单明细表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新售后订单明细表
     *
     * @param list
     * @return
     */
    int updateBatch(List<AfterSaleOrderItemDO> list);

    /**
     * 批量新增售后订单明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<AfterSaleOrderItemDO> list);
}