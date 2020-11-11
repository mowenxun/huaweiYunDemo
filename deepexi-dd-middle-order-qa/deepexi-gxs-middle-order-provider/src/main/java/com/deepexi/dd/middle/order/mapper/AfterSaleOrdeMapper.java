package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.deepexi.dd.middle.order.domain.AfterSaleOrdeDO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * AfterSaleOrdeMapper
 *
 * @author admin
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
@Mapper
public interface AfterSaleOrdeMapper extends BaseMapper<AfterSaleOrdeDO> {

    /**
     * 查询售后申请单
     *
     * @param query
     * @return
     */
    List<AfterSaleOrdeDO> findAll(AfterSaleOrdeQuery query);

    /**
     * 删除售后申请单
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新售后申请单
     *
     * @param list
     * @return
     */
    int updateBatch(List<AfterSaleOrdeDO> list);

    /**
     * 批量新增售后申请单
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<AfterSaleOrdeDO> list);
}