package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyDO;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleOrderCollectionNotifyMapper
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleOrderCollectionNotifyMapper extends BaseMapper<SaleOrderCollectionNotifyDO> {

    /**
     * 查询按单收款消息表
     *
     * @param query
     * @return
     */
    List<SaleOrderCollectionNotifyDO> findAll(SaleOrderCollectionNotifyQuery query);

    /**
     * 删除按单收款消息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新按单收款消息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<SaleOrderCollectionNotifyDO> list);

    /**
     * 批量新增按单收款消息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOrderCollectionNotifyDO> list);
}