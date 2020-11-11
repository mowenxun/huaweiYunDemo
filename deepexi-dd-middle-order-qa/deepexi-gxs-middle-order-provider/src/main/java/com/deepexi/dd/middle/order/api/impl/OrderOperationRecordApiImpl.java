package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderOperationRecordApi;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordQuery;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordRequestQuery;
import com.deepexi.dd.middle.order.service.OrderOperationRecordService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * OrderOperationRecordApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderOperationRecordApiImpl implements OrderOperationRecordApi {

    @Autowired
    private OrderOperationRecordService orderOperationRecordService;

    @Override
    @ApiOperation("查询操作记录表列表")
    public List<OrderOperationRecordResponseDTO> listOrderOperationRecords(OrderOperationRecordRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderOperationRecordService
                    .listOrderOperationRecords(query.clone(OrderOperationRecordQuery.class, CloneDirection.OPPOSITE)),
                                    OrderOperationRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询操作记录表列表")
    public PageBean<OrderOperationRecordResponseDTO> listOrderOperationRecordsPage(OrderOperationRecordRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderOperationRecordService
                    .listOrderOperationRecordsPage(query.clone(OrderOperationRecordQuery.class, CloneDirection.OPPOSITE)),
                        OrderOperationRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除操作记录表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderOperationRecordService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增操作记录表")
    public OrderOperationRecordResponseDTO insert(@RequestBody OrderOperationRecordRequestDTO record) {
        return orderOperationRecordService.insert(record.clone(OrderOperationRecordDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderOperationRecordResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询操作记录表")
    public OrderOperationRecordResponseDTO selectById(@PathVariable Long id) {
        OrderOperationRecordDTO result = orderOperationRecordService.selectById(id);
        return result != null ? result.clone(OrderOperationRecordResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新操作记录表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderOperationRecordRequestDTO record) {
        return orderOperationRecordService.updateById(id, record.clone(OrderOperationRecordDTO.class, CloneDirection.OPPOSITE));
    }
}
