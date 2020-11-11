package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SaleOrderPayNotifyMapper
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
@Mapper
public interface SaleOrderPayNotifyMapper extends BaseMapper<SaleOrderPayNotifyDO> {

    /**
     * 查询
     *
     * @param query
     * @return
     */
    List<SaleOrderPayNotifyDO> findAll(SaleOrderPayNotifyQuery query);

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
    int updateBatch(List<SaleOrderPayNotifyDO> list);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOrderPayNotifyDO> list);
}