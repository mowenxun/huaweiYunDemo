package com.deepexi.dd.system.mall.controller;

import com.deepexi.dd.domain.finance.domain.dto.FinanceBankAccountSupplierDomainRequestDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceBankAccountSupplierDomainResponseDTO;
import com.deepexi.dd.system.mall.domain.query.FinanceBankAccountSupplierRequestQuery;
import com.deepexi.dd.system.mall.service.FinanceBankAccountSupplierService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author easy
 * @date 2020-10-19 19:22:17
 **/
@Api(value = "供应商-银行账户管理", tags = "供应商-银行账户管理")
@RequestMapping("/admin-api/v1/domain/finance/financeBankAccountSupplier")
@RestController
public class FinanceBankAccountSupplierController {

    @Autowired
    private FinanceBankAccountSupplierService financeBankAccountSupplierService;

    /**
     * 查询当前登录供应商的银行账户详情
     *
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation("查询当前登录供应商的银行账户详情")
    @PostMapping("/account")
    public Payload<FinanceBankAccountSupplierDomainResponseDTO> selectMyFinanceSupplierAccount(@RequestBody FinanceBankAccountSupplierRequestQuery query) throws Exception {
        return new Payload<>(financeBankAccountSupplierService.selectMyFinanceAccount(query));
    }

    /**
     * 新增供应商银行账户
     *
     * @param record
     * @return
     * @throws Exception
     */
    @ApiOperation("新增供应商银行账户")
    @PostMapping("/insert")
    public Payload<FinanceBankAccountSupplierDomainResponseDTO> insert(@RequestBody FinanceBankAccountSupplierDomainRequestDTO record) throws Exception {
        return new Payload<>(financeBankAccountSupplierService.insert(record));
    }

    /**
     * 根据ID修改供应商银行账户
     *
     * @param record
     * @return
     */
    @ApiOperation("根据ID修改供应商银行账户")
    @PostMapping("/updateById")
    public Payload<Boolean> updateById(@RequestBody FinanceBankAccountSupplierDomainRequestDTO record) throws Exception {
        return new Payload<>(financeBankAccountSupplierService.updateById(record.getId(), record));
    }
}
