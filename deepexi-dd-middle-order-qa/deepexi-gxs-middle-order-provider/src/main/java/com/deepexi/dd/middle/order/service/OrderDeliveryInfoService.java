package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderDeliveryInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * OrderDeliveryInfoService
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
public interface OrderDeliveryInfoService {


    /**
     * 查询销售出库单物流信息列表
     *
     * @return
     */
    List<OrderDeliveryInfoDTO> listOrderDeliveryInfos(OrderDeliveryInfoQuery query);

    /**
     * 分页查询销售出库单物流信息列表
     *
     * @return
     */
    PageBean<OrderDeliveryInfoDTO> listOrderDeliveryInfosPage(OrderDeliveryInfoQuery query);

    /**
     * 根据ID删除销售出库单物流信息
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售出库单物流信息
     *
     * @param record
     * @return
     */
    OrderDeliveryInfoDTO insert(OrderDeliveryInfoDTO record);

    /**
     * 查询销售出库单物流信息详情
     *
     * @param id
     * @return
     */
    OrderDeliveryInfoDTO selectById(Long id);


    /**
     * 根据ID修改销售出库单物流信息
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderDeliveryInfoDTO record);
    /**
     * @Description:  根据出库单ID查询出库单物流信息，用于出库单列表查询.
     * @Param: [idList]
     * @return: com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    List<OrderDeliveryInfoResponseDTO> searchOrderDeliveryInfoById(List<Long> idList);

}