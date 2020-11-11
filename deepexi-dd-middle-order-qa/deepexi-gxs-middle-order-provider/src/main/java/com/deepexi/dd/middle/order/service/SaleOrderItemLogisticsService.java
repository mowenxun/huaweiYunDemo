package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemLogisticsDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemLogisticsQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleOrderItemLogisticsService
 *
 * @author admin
 * @date Sat Aug 22 16:34:04 CST 2020
 * @version 1.0
 */
public interface SaleOrderItemLogisticsService {


    /**
     * 查询OMS物流信息列表
     *
     * @return
     */
    List<SaleOrderItemLogisticsDTO> listSaleOrderItemLogisticss(SaleOrderItemLogisticsQuery query);

    /**
     * 分页查询OMS物流信息列表
     *
     * @return
     */
    PageBean<SaleOrderItemLogisticsDTO> listSaleOrderItemLogisticssPage(SaleOrderItemLogisticsQuery query);

    /**
     * 根据ID删除OMS物流信息
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增OMS物流信息
     *
     * @param record
     * @return
     */
    Long insert(SaleOrderItemLogisticsDTO record) throws Exception;

    /**
     * 查询OMS物流信息详情
     *
     * @param id
     * @return
     */
    SaleOrderItemLogisticsDTO selectById(Long id);


    /**
     * 根据ID修改OMS物流信息
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOrderItemLogisticsDTO record);

}