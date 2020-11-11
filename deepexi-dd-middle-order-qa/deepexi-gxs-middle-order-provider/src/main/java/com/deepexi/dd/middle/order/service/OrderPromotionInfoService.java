package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoDTO;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderPromotionInfoService
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
public interface OrderPromotionInfoService {


    /**
     * 查询销售订单的促销信息表列表
     *
     * @return
     */
    List<OrderPromotionInfoDTO> listOrderPromotionInfos(OrderPromotionInfoQuery query);

    /**
     * 分页查询销售订单的促销信息表列表
     *
     * @return
     */
    PageBean<OrderPromotionInfoDTO> listOrderPromotionInfosPage(OrderPromotionInfoQuery query);

    /**
     * 根据ID删除销售订单的促销信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单的促销信息表
     *
     * @param record
     * @return
     */
    OrderPromotionInfoDTO insert(OrderPromotionInfoDTO record);

    /**
     * 查询销售订单的促销信息表详情
     *
     * @param id
     * @return
     */
    OrderPromotionInfoDTO selectById(Long id);


    /**
     * 根据ID修改销售订单的促销信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderPromotionInfoDTO record);

}