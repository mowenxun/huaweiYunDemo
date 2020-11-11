package com.deepexi.dd.middle.order.dao;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoDO;
import com.deepexi.dd.middle.order.domain.OrderDeliverySelfRaisingInfoQuery;

import java.util.List;


/**
 * OrderDeliverySelfRaisingInfoDAO
 *
 * @author admin
 * @date Wed Aug 26 16:41:35 CST 2020
 * @version 1.0
 */
public interface OrderDeliverySelfRaisingInfoDAO extends IService<OrderDeliverySelfRaisingInfoDO> {

    /**
     * 查询出库单自提地址信息表列表}
     *
     * @return
     */
    List<OrderDeliverySelfRaisingInfoDO> listOrderDeliverySelfRaisingInfos(OrderDeliverySelfRaisingInfoQuery query);

    /**
     * 根据ID删除出库单自提地址信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增出库单自提地址信息表
     *
     * @param record
     * @return
     */
    int insert(OrderDeliverySelfRaisingInfoDO record);

    /**
     * 查询出库单自提地址信息表详情
     *
     * @param id
     * @return
     */
    OrderDeliverySelfRaisingInfoDO selectById(Long id);

    /**
     * 根据ID修改出库单自提地址信息表
     *
     * @param record
     * @return
     */
//    int updateById(OrderDeliverySelfRaisingInfoDO record);

    /**
     * 根据订单Id 获取自提信息
     */
    OrderDeliverySelfRaisingInfoDO selectBySaleOrderId(Long id);
}