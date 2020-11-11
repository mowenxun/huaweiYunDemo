package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderInvoiceInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoQuery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * OrderInvoiceInfoMapper
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Mapper
public interface OrderInvoiceInfoMapper extends BaseMapper<OrderInvoiceInfoDO> {

    /**
     * 查询订单的开票信息表
     *
     * @param query
     * @return
     */
    List<OrderInvoiceInfoDO> findAll(OrderInvoiceInfoQuery query);

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
    int updateBatch(List<OrderInvoiceInfoDO> list);

    /**
     * 批量新增订单的开票信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderInvoiceInfoDO> list);
}