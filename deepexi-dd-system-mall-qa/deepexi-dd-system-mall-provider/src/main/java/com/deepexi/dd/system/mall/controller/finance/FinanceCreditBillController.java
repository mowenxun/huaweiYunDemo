package com.deepexi.dd.system.mall.controller.finance;

import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillDetailResponseDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillRelationResponseDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditRepaymentBillResponseDTO;
import com.deepexi.dd.middle.finance.domain.dto.FinanceCreditRepaymentBillRequestDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRepaymentBillRelationRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRepaymentBillRequestQuery;
import com.deepexi.dd.middle.finance.domain.query.FinanceCreditRequestQuery;
import com.deepexi.dd.system.mall.service.finance.FinanceCreditBillService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author easy
 * @date 2020/9/10 10:14
 **/
@Api(value = "信用账单", tags = "信用账单")
@RequestMapping("/admin-api/v1/domain/finance/financeCreditBill")
@RestController
public class FinanceCreditBillController {

    @Autowired
    private FinanceCreditBillService financeCreditBillService;

    /**
     * 分页查询信用账单列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("分页查询信用账单列表")
    @GetMapping("/page")
    public Payload<PageBean<FinanceCreditRepaymentBillResponseDTO>> listFinanceCreditRepaymentBillsPage(FinanceCreditRepaymentBillRequestQuery query) throws  Exception {
        return financeCreditBillService.listFinanceCreditRepaymentBillsPage(query);
    }

    /**
     * 新增信用账单、生成信用账单与支付记录关联关系、更新支付记录还款状态为待确认
     *
     * @param record
     * @return
     * @throws Exception
     */
    @ApiOperation("新增信用账单")
    @PostMapping("")
    public Payload<FinanceCreditRepaymentBillResponseDTO> insert(@RequestBody FinanceCreditRepaymentBillRequestDTO record) throws Exception {
        return financeCreditBillService.insert(record);
    }

    /**
     * 查看信用账单详情
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("查看信用账单详情")
    @GetMapping("/look")
    public Payload<FinanceCreditRepaymentBillDetailResponseDTO> lookCreditBill(FinanceCreditRepaymentBillRequestQuery query) throws Exception {
        return financeCreditBillService.lookCreditBill(query);
    }

    /**
     * 分页查询信用账单与支付记录关联关系列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("分页查询信用账单与支付记录关联关系列表")
    @GetMapping("/relationPage")
    public Payload<PageBean<FinanceCreditRepaymentBillRelationResponseDTO>> listFinanceCreditRepaymentBillRelationsPage(FinanceCreditRepaymentBillRelationRequestQuery query) throws Exception {
        return financeCreditBillService.listFinanceCreditRepaymentBillRelationsPage(query);
    }

    /**
     * 还款按钮查询信用账单还款状态
     *
     * @param query
     * @return 1-没有需要还款的额度，2-有需要生成账单的支付记录，3-无需要生成账单的支付记录，但有需要还款的账单，4-无需生成账单的支付记录，无需还款的账单，有退款单在审核中
     * @throws Exception
     */
    @ApiOperation("还款按钮查询信用账单还款状态")
    @GetMapping("/repaymentButton")
    Payload<String> repaymentButton(FinanceCreditRequestQuery query) throws Exception {
        return financeCreditBillService.repaymentButton(query);
    }

}
