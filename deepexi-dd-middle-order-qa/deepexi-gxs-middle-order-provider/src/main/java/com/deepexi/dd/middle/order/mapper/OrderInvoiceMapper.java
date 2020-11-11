package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderInvoiceDO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderInvoiceMapper
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderInvoiceMapper extends BaseMapper<OrderInvoiceDO> {

    /**
     * 查询订单的开票信息表
     *
     * @param query
     * @return
     */
    List<OrderInvoiceDO> findAll(OrderInvoiceQuery query);

    /**
     * 删除订单的开票信息表
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新订单的开票信息表
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderInvoiceDO> list);

    /**
     * 批量新增订单的开票信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderInvoiceDO> list);
}