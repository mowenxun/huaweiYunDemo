package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordDO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleOrderSplitRecordMapper
 *
 * @author admin
 * @date Wed Aug 12 20:24:31 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleOrderSplitRecordMapper extends BaseMapper<SaleOrderSplitRecordDO> {

    /**
     * 查询销售订单拆单记录表
     *
     * @param query
     * @return
     */
    List<SaleOrderSplitRecordDO> findAll(SaleOrderSplitRecordQuery query);

    /**
     * 删除销售订单拆单记录表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新销售订单拆单记录表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOrderSplitRecordDO> list);

    /**
     * 批量新增销售订单拆单记录表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOrderSplitRecordDO> list);
}