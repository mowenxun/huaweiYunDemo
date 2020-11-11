package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOrderItemDO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * SaleOrderItemDAO
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
public interface SaleOrderItemDAO extends IService<SaleOrderItemDO> {

    /**
     * 查询销售订单明细表列表
     *
     * @return
     */
    List<SaleOrderItemDO> listSaleOrderItems(SaleOrderItemMiddleRequestQuery query);

    /**
     * 根据ID删除销售订单明细表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单明细表
     *
     * @param record
     * @return
     */
    int insert(SaleOrderItemDO record);

    /**
     * 查询销售订单明细表详情
     *
     * @param id
     * @return
     */
    SaleOrderItemDO selectById(Long id);

    /**
     * 根据ID修改销售订单明细表
     *
     * @param record
     * @return
     */
//    int updateById(SaleOrderItemDO record);

    /**
     * 批量新增销售订单明细表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SaleOrderItemDO> list);

}