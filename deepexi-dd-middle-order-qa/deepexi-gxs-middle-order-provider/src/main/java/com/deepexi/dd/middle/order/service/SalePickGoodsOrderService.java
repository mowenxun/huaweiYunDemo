package com.deepexi.dd.middle.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDTO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SalePickGoodsOrderService
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsOrderService {


    /**
     * 查询提货单据所关联的订单的信息表列表
     *
     * @return
     */
    List<SalePickGoodsOrderDTO> listSalePickGoodsOrders(SalePickGoodsOrderQuery query);

    /**
     * 分页查询提货单据所关联的订单的信息表列表
     *
     * @return
     */
    PageBean<SalePickGoodsOrderDTO> listSalePickGoodsOrdersPage(SalePickGoodsOrderQuery query);

    /**
     * 根据ID删除提货单据所关联的订单的信息表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    Boolean deleteByWrapper(QueryWrapper wrapper);

    /**
     * 新增提货单据所关联的订单的信息表
     *
     * @param record
     * @return
     */
    SalePickGoodsOrderDTO insert(SalePickGoodsOrderDTO record);

    /**
     * 批量新增提货单据所关联的订单的信息表
     *
     * @param list
     * @return
     */
    List<SalePickGoodsOrderDO> insertBatch(List<SalePickGoodsOrderDTO> list);

    /**
     * 查询提货单据所关联的订单的信息表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsOrderDTO selectById(Long id);


    /**
     * 根据ID修改提货单据所关联的订单的信息表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SalePickGoodsOrderDTO record);

}