package com.deepexi.dd.system.mall.controller.saleOrderItem;

import com.deepexi.dd.domain.finance.domain.dto.FinanceRefundRecordsAdminResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinanceAuditAdminRequestQuery;
import com.deepexi.dd.domain.finance.domain.query.FinanceRefundRecordsAdminRequestQuery;
import com.deepexi.dd.system.mall.service.FinanceRefundRecordsService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: FinanceRefundRecordsApiImpl
 * @Description: TODO
 * @Author: hezhijian
 * @Datetime: 2020/9/17 17:17
 * @version: v1.0
 */
@Api(value = "退款记录表管理", tags = "退款记录表管理")
@RequestMapping("/admin-api/v1/domain/sale/financeRefundRecords")
@RestController
public class FinanceRefundRecordsController {

    @Autowired
    private FinanceRefundRecordsService financeRefundRecordsService;

    @ApiOperation("查询退款记录表")
    @GetMapping("/list")
    public Payload<List<FinanceRefundRecordsAdminResponseDTO>> listFinanceRefundRecords(FinanceRefundRecordsAdminRequestQuery query) throws Exception{
        return new Payload<>(ObjectCloneUtils.convertList(financeRefundRecordsService.listFinanceRefundRecords(query.clone(FinanceRefundRecordsAdminRequestQuery.class, CloneDirection.OPPOSITE)), FinanceRefundRecordsAdminResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @ApiOperation("分页查询退款记录表")
    @GetMapping("/page")
    public Payload<PageBean<FinanceRefundRecordsAdminResponseDTO>> listFinanceRefundRecordsPage(FinanceRefundRecordsAdminRequestQuery query) throws Exception{
        return new Payload<>(ObjectCloneUtils.convertPageBean(financeRefundRecordsService.listFinanceRefundRecordsPage(query.clone(FinanceRefundRecordsAdminRequestQuery.class, CloneDirection.OPPOSITE)), FinanceRefundRecordsAdminResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @ApiOperation("审核退款单")
    @GetMapping("/audit")
    public Payload<Boolean> financeAuditRefundRecords(FinanceAuditAdminRequestQuery query) throws Exception{
        return new Payload<>(financeRefundRecordsService.financeAuditRefundRecords(query));
    }

}
