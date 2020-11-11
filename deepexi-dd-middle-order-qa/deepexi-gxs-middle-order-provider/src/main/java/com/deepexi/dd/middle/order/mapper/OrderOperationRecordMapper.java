package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderOperationRecordDO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrderOperationRecordMapper
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderOperationRecordMapper extends BaseMapper<OrderOperationRecordDO> {

    /**
     * 查询操作记录表
     *
     * @param query
     * @return
     */
    List<OrderOperationRecordDO> findAll(OrderOperationRecordQuery query);

    /**
     * 删除操作记录表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新操作记录表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderOperationRecordDO> list);

    /**
     * 批量新增操作记录表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderOperationRecordDO> list);
}