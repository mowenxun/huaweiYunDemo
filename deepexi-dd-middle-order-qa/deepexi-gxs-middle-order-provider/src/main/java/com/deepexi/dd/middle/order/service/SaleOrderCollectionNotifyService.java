package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleOrderCollectionNotifyService
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
public interface SaleOrderCollectionNotifyService {


    /**
     * 查询按单收款消息表列表
     *
     * @return
     */
    List<SaleOrderCollectionNotifyDTO> listSaleOrderCollectionNotifys(SaleOrderCollectionNotifyQuery query);

    /**
     * 分页查询按单收款消息表列表
     *
     * @return
     */
    PageBean<SaleOrderCollectionNotifyDTO> listSaleOrderCollectionNotifysPage(SaleOrderCollectionNotifyQuery query);

    /**
     * 根据ID删除按单收款消息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增按单收款消息表
     *
     * @param record
     * @return
     */
    SaleOrderCollectionNotifyDTO insert(SaleOrderCollectionNotifyDTO record);

    /**
     * 查询按单收款消息表详情
     *
     * @param id
     * @return
     */
    SaleOrderCollectionNotifyDTO selectById(Long id);


    /**
     * 根据ID修改按单收款消息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOrderCollectionNotifyDTO record);

    /**
     * 根据订单ID和支付单号查询通知信息
     * @param query
     * @return
     */
    List<SaleOrderCollectionNotifyDTO> querySaleOrderCollectionNotify(SaleOrderCollectionNotifyQuery query);
}