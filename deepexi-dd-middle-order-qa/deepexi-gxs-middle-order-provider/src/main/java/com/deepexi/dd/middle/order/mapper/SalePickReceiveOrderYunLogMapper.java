package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickReceiveOrderYunLogQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SalePickReceiveOrderYunLogMapper
 *
 * @author admin
 * @date Wed Sep 23 13:47:55 CST 2020
 * @version 1.0
 */
@Mapper
public interface SalePickReceiveOrderYunLogMapper extends BaseMapper<SalePickReceiveOrderYunLogDO> {

    /**
     * 查询
     *
     * @param query
     * @return
     */
    List<SalePickReceiveOrderYunLogDO> findAll(SalePickReceiveOrderYunLogQuery query);

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
    int updateBatch(List<SalePickReceiveOrderYunLogDO> list);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SalePickReceiveOrderYunLogDO> list);
}