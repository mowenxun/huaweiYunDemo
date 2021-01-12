package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.dd.system.mall.service.FinanceBankAccountService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: mumu
 * @Datetime: 2020/8/29   19:28
 * @version: v1.0
 */
@RestController
@Api(value = "银行账户表管理")
@RequestMapping("/admin-api/v1/domain/financeBankAccounts")
public class FinanceBankAccountController {

    @Autowired
    private FinanceBankAccountService financeBankAccountService;

    @ApiOperation("查询银行账户表列表")
    @GetMapping("/list")
    public Payload<List<FinanceBankAccountResponseDTO>> listFinanceBankAccounts(FinanceBankAccountRequestQuery query) throws Exception {
        return new Payload<>(financeBankAccountService.listFinanceBankAccounts(query.clone(FinanceBankAccountRequestQuery.class, CloneDirection.OPPOSITE)));
    }

}
