package com.deepexi.dd.system.mall.controller.customer;

import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAccountBankCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantAccountBankUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantAccountBankAdminRequestPageQuery;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantAccountBankAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantAccountBankResponseVO;
import com.deepexi.dd.system.mall.service.customer.MerchantAccountBankService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import domain.query.MerchantAccountBankRequestQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName MerchantAccountBankController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Api(value = "客户银行账户管理", tags = "客户银行账户管理")
@RequestMapping("/admin-api/v1/domain/customer/accountBank")
@RestController
public class MerchantAccountBankController {

    @Autowired
    private MerchantAccountBankService bankService;

    /**
     * 获取列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取银行账号列表", nickname = "findAllAccountBank")
    public Payload<List<MerchantAccountBankResponseVO>> findAll(@Valid MerchantAccountBankAdminRequestQuery query) throws Exception {
        List<MerchantAccountBankResponseVO> result = bankService.findAll(query.clone(MerchantAccountBankRequestQuery.class));
        return new Payload<>(result);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询银行账号", nickname = "findPage")
    public Payload<PageBean<MerchantAccountBankResponseVO>> findPage(@Valid MerchantAccountBankAdminRequestPageQuery query) throws Exception {
        PageBean<MerchantAccountBankResponseVO> result = bankService.findPage(query.clone(FinanceBankAccountRequestQuery.class));
        return new Payload<>(result);
    }

    /**
     * 获取详情
     *
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation(value = "获取银行账号详情", nickname = "detailAccountBank")
    public Payload<MerchantAccountBankResponseVO> detail(@RequestParam(value = "id", required = true) Long id) throws Exception {
        MerchantAccountBankResponseVO result = bankService.detail(id);
        return new Payload<>(result);
    }

    /**
     * 更新eo
     *
     * @param dto
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新银行账号信息", nickname = "updateAccountBank")
    public Payload<Boolean> update(@Valid @RequestBody MerchantAccountBankUpdateAdminRequestDTO dto) throws Exception {
        Boolean result = bankService.update(dto);
        return new Payload<>(result);
    }

    /**
     * 创建商户银行信息
     *
     * @param dto
     * @return
     */
    @PostMapping
    @ApiOperation(value = "创建银行账号信息", nickname = "createAccountBank")
    public Payload<MerchantAccountBankResponseVO> create(@Valid @RequestBody MerchantAccountBankCreateAdminRequestDTO dto) throws Exception {
        MerchantAccountBankResponseVO result = bankService.create(dto);
        return new Payload<>(result);
    }

    /**
     * 单个删除
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除单个银行账号信息", nickname = "deleteAccountBank")
    public Payload<Boolean> delete(@RequestParam(value = "id", required = true) Long id) throws Exception {
        Boolean result = bankService.delete(id);
        return new Payload<>(result);
    }
}
