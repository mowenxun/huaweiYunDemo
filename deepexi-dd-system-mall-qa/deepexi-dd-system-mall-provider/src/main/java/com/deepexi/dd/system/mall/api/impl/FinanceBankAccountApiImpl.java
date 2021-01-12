//package com.deepexi.dd.system.mall.api.impl;
//
//import com.deepexi.dd.system.mall.api.FinanceBankAccountApi;
//import com.deepexi.dd.system.mall.service.FinanceBankAccountService;
//import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountRequestDTO;
//import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
//import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
//import com.deepexi.util.pageHelper.PageBean;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
///**
// * @version 1.0
// * @date 2020-06-22 15:32
// */
//@RestController
//public class FinanceBankAccountApiImpl implements FinanceBankAccountApi {
//
//    @Autowired
//    private FinanceBankAccountService financeBankAccountService;
//
//    @Override
//    @ApiOperation("查询银行账户表列表")
//    public List<FinanceBankAccountResponseDTO> listFinanceBankAccounts(FinanceBankAccountRequestQuery query) throws Exception{
//        return financeBankAccountService.listFinanceBankAccounts(query);
//    }
//
//    @Override
//    @ApiOperation("分页查询银行账户表列表")
//    public PageBean<FinanceBankAccountResponseDTO> listFinanceBankAccountsPage(FinanceBankAccountRequestQuery query) throws Exception{
//        return financeBankAccountService.listFinanceBankAccountsPage(query);
//    }
//
//    @Override
//    @ApiOperation("批量删除银行账户表")
//    public Boolean deleteByIdIn(@RequestBody List<Long> id) throws Exception{
//        return financeBankAccountService.deleteByIdIn(id);
//    }
//
//    @Override
//    @ApiOperation("新增银行账户表")
//    public FinanceBankAccountResponseDTO insert(@RequestBody FinanceBankAccountRequestDTO record) throws Exception{
//        return financeBankAccountService.insert(record);
//    }
//
//    @Override
//    @ApiOperation("根据ID查询银行账户表")
//    public FinanceBankAccountResponseDTO selectById(@PathVariable Long id) throws Exception{
//        return financeBankAccountService.selectById(id);
//    }
//
//    @Override
//    @ApiOperation("根据ID更新银行账户表")
//    public Boolean updateById(@PathVariable Long id,@RequestBody FinanceBankAccountRequestDTO record) throws Exception{
//        return financeBankAccountService.updateById(id, record);
//    }
//}
