package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * OrderConsigneeInfoService
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderConsigneeInfoService {


    /**
     * 查询收货地址信息表列表
     *
     * @return
     */
    List<OrderConsigneeInfoDTO> listOrderConsigneeInfos(OrderConsigneeInfoQuery query);

    /**
     * 分页查询收货地址信息表列表
     *
     * @return
     */
    PageBean<OrderConsigneeInfoDTO> listOrderConsigneeInfosPage(OrderConsigneeInfoQuery query);

    /**
     * 根据ID删除收货地址信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增收货地址信息表
     *
     * @param record
     * @return
     */
    OrderConsigneeInfoDTO insert(OrderConsigneeInfoDTO record);

    /**
     * 查询收货地址信息表详情
     *
     * @param id
     * @return
     */
    OrderConsigneeInfoDTO selectById(Long id);


    /**
     * 根据ID修改收货地址信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderConsigneeInfoDTO record);
    /**
     * @Description:  根据订单ID查询收货信息，用于出库单列表查询.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.OrderDeliveryInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    List<OrderConsigneeInfoResponseDTO> searchOrderConsigneeInfoById(List<Long> idList);
    /**
     * @Description:  公共接口 根据字段拿到拼接后的发货信息.
     * @Param: [key, value]
     * @return: java.lang.String
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    String splicingShippingAddress(Object key,Object value);

    /**
     * @Description:  根据字段和值 查对应的收货信息.
     * @Param: [field, value]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/8
     */
    List<OrderConsigneeInfoResponseDTO> searchConsigneeInfo(Object field,Object value);

}