package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogDO;
import com.deepexi.dd.middle.order.domain.SalePickOrderYunLogQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * SalePickOrderYunLogMapper
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
@Mapper
public interface SalePickOrderYunLogMapper extends BaseMapper<SalePickOrderYunLogDO> {

    /**
     * 查询
     *
     * @param query
     * @return
     */
    List<SalePickOrderYunLogDO> findAll(SalePickOrderYunLogQuery query);

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
    int updateBatch(List<SalePickOrderYunLogDO> list);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SalePickOrderYunLogDO> list);
}