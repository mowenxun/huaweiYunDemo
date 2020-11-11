package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderConsigneeInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderConsigneeInfoMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderConsigneeInfoMapper extends BaseMapper<OrderConsigneeInfoDO> {

    /**
     * 查询收货地址信息表
     *
     * @param query
     * @return
     */
    List<OrderConsigneeInfoDO> findAll(OrderConsigneeInfoQuery query);

    /**
     * 删除收货地址信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新收货地址信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderConsigneeInfoDO> list);

    /**
     * 批量新增收货地址信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderConsigneeInfoDO> list);
}