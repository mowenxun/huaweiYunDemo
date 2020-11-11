package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SalePickGoodsInfoDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderDO;
import com.deepexi.dd.middle.order.domain.SalePickGoodsOrderQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * SalePickGoodsOrderDAO
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
public interface SalePickGoodsOrderDAO extends IService<SalePickGoodsOrderDO> {

    /**
     * 查询提货单据所关联的订单的信息表列表}
     *
     * @return
     */
    List<SalePickGoodsOrderDO> listSalePickGoodsOrders(SalePickGoodsOrderQuery query);

    /**
     * 根据ID删除提货单据所关联的订单的信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增提货单据所关联的订单的信息表
     *
     * @param record
     * @return
     */
    int insert(SalePickGoodsOrderDO record);

    /**
     * 查询提货单据所关联的订单的信息表详情
     *
     * @param id
     * @return
     */
    SalePickGoodsOrderDO selectById(Long id);

//    /**
//     * 根据ID修改提货单据所关联的订单的信息表
//     *
//     * @param record
//     * @return
//     */
//    int updateById(SalePickGoodsOrderDO record);

    /**
     * 批量新增提货单据所关联的订单的信息表
     *
     * @param orderDOs
     * @return
     */
    Integer insertBatch(ArrayList<SalePickGoodsOrderDO> orderDOs);
}