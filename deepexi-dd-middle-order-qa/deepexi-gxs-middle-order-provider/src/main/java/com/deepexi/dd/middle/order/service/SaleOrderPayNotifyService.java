package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleOrderPayNotifyService
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
public interface SaleOrderPayNotifyService {


    /**
     * 查询列表
     *
     * @return
     */
    List<SaleOrderPayNotifyDTO> listSaleOrderPayNotifys(SaleOrderPayNotifyQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    PageBean<SaleOrderPayNotifyDTO> listSaleOrderPayNotifysPage(SaleOrderPayNotifyQuery query);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    SaleOrderPayNotifyDTO insert(SaleOrderPayNotifyDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SaleOrderPayNotifyDTO selectById(Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOrderPayNotifyDTO record);

    /**
     * 根据订单ID和支付单号查询通知信息
     * @param query
     * @return
     */
     List<SaleOrderPayNotifyResponseDTO> querySaleOrderNotify(SaleOrderPayNotifyQuery query);

}