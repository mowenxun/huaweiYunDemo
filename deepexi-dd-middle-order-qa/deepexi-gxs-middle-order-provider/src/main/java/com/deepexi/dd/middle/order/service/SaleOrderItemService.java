package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleOrderItemService
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
public interface SaleOrderItemService {

    /**
     * @Description:  查询销售订单明细表列表.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOrderItemDTO>
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    List<SaleOrderItemDTO> listSaleOrderItems(SaleOrderItemQuery query);

    /**
     * @Description:  查询销售订单明细表分页列表.
     * @Param: [query]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOrderItemDTO>
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    PageBean<SaleOrderItemMiddleResponseDTO> listSaleOrderItemsPage(SaleOrderItemMiddleRequestQuery query)  throws Exception;

    /**
     * 根据ID删除销售订单明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单明细表
     *
     * @param record
     * @return
     */
    SaleOrderItemDTO insert(SaleOrderItemDTO record);

    /**
     * 查询销售订单明细表详情
     *
     * @param id
     * @return
     */
    SaleOrderItemDTO selectById(Long id);


    /**
     * 根据ID修改销售订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOrderItemDTO record);

    /**
     * 根据ID批量修改销售订单明细表
     * @return
     */
    Boolean updateBatchById(List<SaleOrderItemDTO> convertList);
}