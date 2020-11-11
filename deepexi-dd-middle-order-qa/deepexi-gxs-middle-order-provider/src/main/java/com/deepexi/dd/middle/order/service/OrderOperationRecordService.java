package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/**
 * OrderOperationRecordService
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
public interface OrderOperationRecordService {


    /**
     * 查询操作记录表列表
     *
     * @return
     */
    List<OrderOperationRecordDTO> listOrderOperationRecords(OrderOperationRecordQuery query);

    /**
     * 分页查询操作记录表列表
     *
     * @return
     */
    PageBean<OrderOperationRecordDTO> listOrderOperationRecordsPage(OrderOperationRecordQuery query);

    /**
     * 根据ID删除操作记录表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增操作记录表
     *
     * @param record
     * @return
     */
    OrderOperationRecordDTO insert(OrderOperationRecordDTO record);

    /**
     * 查询操作记录表详情
     *
     * @param id
     * @return
     */
    OrderOperationRecordDTO selectById(Long id);


    /**
     * 根据ID修改操作记录表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, OrderOperationRecordDTO record);

    Boolean batchInsert(List<OrderOperationRecordDTO> record);
}