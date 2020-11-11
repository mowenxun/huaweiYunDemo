package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderOperationRecordDO;
import com.deepexi.dd.middle.order.domain.OrderRefundSkuDO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordQuery;

import java.util.List;


/**
 * OrderOperationRecordDAO
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
public interface OrderOperationRecordDAO extends IService<OrderOperationRecordDO> {

    /**
     * 查询操作记录表列表}
     *
     * @return
     */
    List<OrderOperationRecordDO> listOrderOperationRecords(OrderOperationRecordQuery query);

    /**
     * 根据ID删除操作记录表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增操作记录表
     *
     * @param record
     * @return
     */
    int insert(OrderOperationRecordDO record);

    /**
     * 查询操作记录表详情
     *
     * @param id
     * @return
     */
    OrderOperationRecordDO selectById(Long id);

    /**
     * 根据ID修改操作记录表
     *
     * @param record
     * @return
     */
//    int updateById(OrderOperationRecordDO record);

}