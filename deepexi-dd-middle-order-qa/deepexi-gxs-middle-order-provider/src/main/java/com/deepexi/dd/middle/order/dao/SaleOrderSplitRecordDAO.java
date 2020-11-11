package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOrderInfoDO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordDO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordQuery;

import java.util.List;


/**
 * SaleOrderSplitRecordDAO
 *
 * @author admin
 * @date Wed Aug 12 20:24:31 CST 2020
 * @version 1.0
 */
public interface SaleOrderSplitRecordDAO extends IService<SaleOrderSplitRecordDO> {

    /**
     * 查询销售订单拆单记录表列表}
     *
     * @return
     */
    List<SaleOrderSplitRecordDO> listSaleOrderSplitRecords(SaleOrderSplitRecordQuery query);

    /**
     * 根据ID删除销售订单拆单记录表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单拆单记录表
     *
     * @param record
     * @return
     */
    int insert(SaleOrderSplitRecordDO record);

    /**
     * 查询销售订单拆单记录表详情
     *
     * @param id
     * @return
     */
    SaleOrderSplitRecordDO selectById(Long id);



}