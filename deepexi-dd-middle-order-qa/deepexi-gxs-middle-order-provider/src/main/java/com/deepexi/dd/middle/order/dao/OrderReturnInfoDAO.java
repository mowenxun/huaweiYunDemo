package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderReturnInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoQuery;

import java.util.List;


/**
 * OrderReturnInfoDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderReturnInfoDAO extends IService<OrderReturnInfoDO> {

    /**
     * 查询销售订单退货单信息表列表}
     *
     * @return
     */
    List<OrderReturnInfoDO> listOrderReturnInfos(OrderReturnInfoQuery query);

    /**
     * 根据ID删除销售订单退货单信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单退货单信息表
     *
     * @param record
     * @return
     */
    int insert(OrderReturnInfoDO record);

    /**
     * 查询销售订单退货单信息表详情
     *
     * @param id
     * @return
     */
    OrderReturnInfoDO selectById(Long id);

    /**
     * 根据ID修改销售订单退货单信息表
     *
     * @param record
     * @return
     */
//    int updateById(OrderReturnInfoDO record);

}