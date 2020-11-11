package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderCouponInfoService
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderCouponInfoService {


    /**
     * 查询优惠信息表列表
     *
     * @return
     */
    List<OrderCouponInfoDTO> listOrderCouponInfos(OrderCouponInfoQuery query);

    /**
     * 分页查询优惠信息表列表
     *
     * @return
     */
    PageBean<OrderCouponInfoDTO> listOrderCouponInfosPage(OrderCouponInfoQuery query);

    /**
     * 根据ID删除优惠信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增优惠信息表
     *
     * @param record
     * @return
     */
    OrderCouponInfoDTO insert(OrderCouponInfoDTO record);

    /**
     * 查询优惠信息表详情
     *
     * @param id
     * @return
     */
    OrderCouponInfoDTO selectById(Long id);


    /**
     * 根据ID修改优惠信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderCouponInfoDTO record);

}