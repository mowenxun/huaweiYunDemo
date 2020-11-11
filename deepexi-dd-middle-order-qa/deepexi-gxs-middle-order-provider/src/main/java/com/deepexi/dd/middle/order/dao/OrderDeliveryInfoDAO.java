package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderDeliveryInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoQuery;

import java.util.List;


/**
 * OrderDeliveryInfoDAO
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
public interface OrderDeliveryInfoDAO extends IService<OrderDeliveryInfoDO> {

    /**
     * 查询销售出库单物流信息列表}
     *
     * @return
     */
    List<OrderDeliveryInfoDO> listOrderDeliveryInfos(OrderDeliveryInfoQuery query);

    /**
     * 根据ID删除销售出库单物流信息
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售出库单物流信息
     *
     * @param record
     * @return
     */
    int insert(OrderDeliveryInfoDO record);

    /**
     * 查询销售出库单物流信息详情
     *
     * @param id
     * @return
     */
    OrderDeliveryInfoDO selectById(Long id);

    /**
     * 根据ID修改销售出库单物流信息
     *
     * @param record
     * @return
     */
//    int updateById(OrderDeliveryInfoDO record);

}