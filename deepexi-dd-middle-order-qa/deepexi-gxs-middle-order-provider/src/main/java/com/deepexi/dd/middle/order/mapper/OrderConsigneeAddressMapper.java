package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressDO;
import com.deepexi.dd.middle.order.domain.OrderConsigneeAddressQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderConsigneeAddressMapper
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderConsigneeAddressMapper extends BaseMapper<OrderConsigneeAddressDO> {

    /**
     * 查询订单收货地址信息表
     *
     * @param query
     * @return
     */
    List<OrderConsigneeAddressDO> findAll(OrderConsigneeAddressQuery query);

    /**
     * 删除订单收货地址信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新订单收货地址信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderConsigneeAddressDO> list);

    /**
     * 批量新增订单收货地址信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderConsigneeAddressDO> list);
}