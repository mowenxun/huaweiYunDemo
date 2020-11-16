package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.service.SaleOrderOperationRecordService;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-28 15:27
 */
@RestController
@Api(tags = "操作记录接口")
@RequestMapping("/admin-api/v1/domain/transaction/operationRecordController")
public class OperationRecordController {

    @Autowired
    private SaleOrderOperationRecordService saleOrderOperationRecordService;

    @ApiOperation("新增操作记录")
    @PostMapping("/add")
    public Payload add(@RequestBody OrderOperationRecordRequestDTO orderOperationRecordRequestDTO){
      return new Payload<>(saleOrderOperationRecordService.add(orderOperationRecordRequestDTO));
    }

    /**
     * 查询操作记录集合
     *
     * @param orderOperationRecordRequestDTO
     * @return
     */
    @ApiOperation("查询操作记录集合")
    @GetMapping("/list")
    public Payload<List<OrderOperationRecordResponseDTO>> listOrderOperationRecords(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO){
        return new Payload<>(saleOrderOperationRecordService.listOrderOperationRecord(orderOperationRecordRequestDTO));
    }
}
