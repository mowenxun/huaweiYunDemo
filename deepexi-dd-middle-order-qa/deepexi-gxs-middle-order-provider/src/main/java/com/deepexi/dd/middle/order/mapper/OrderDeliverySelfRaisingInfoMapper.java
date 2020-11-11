package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoDO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderDeliverySelfRaisingInfoMapper
 *
 * @author admin
 * @date Wed Aug 26 16:41:35 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderDeliverySelfRaisingInfoMapper extends BaseMapper<OrderDeliverySelfRaisingInfoDO> {

    /**
     * 查询出库单自提地址信息表
     *
     * @param query
     * @return
     */
    List<OrderDeliverySelfRaisingInfoDO> findAll(OrderDeliverySelfRaisingInfoQuery query);

    /**
     * 删除出库单自提地址信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新出库单自提地址信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderDeliverySelfRaisingInfoDO> list);

    /**
     * 批量新增出库单自提地址信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderDeliverySelfRaisingInfoDO> list);
}