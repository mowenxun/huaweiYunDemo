//package com.deepexi.dd.system.mall.controller;
//
//import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
//import com.deepexi.dd.system.mall.domain.dto.FinanceBankAccountRequestDTO;
//import com.deepexi.dd.system.mall.domain.query.FinanceBankAccountRequestQuery;
//import com.deepexi.dd.system.mall.domain.vo.FinanceBankAccountResponseVO;
//import com.deepexi.dd.system.mall.service.FinanceBankAccountService;
//import com.deepexi.util.config.Payload;
//import com.deepexi.util.pageHelper.PageBean;
//import com.deepexi.util.pojo.CloneDirection;
//import com.deepexi.util.pojo.ObjectCloneUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
///**
// * @version 1.0
// * @date 2020-06-24 15:32
// */
//@Api(value = "银行账户表管理", tags = "银行账户表管理")
//@RequestMapping("/api/v1/system/financeBankAccounts")
//@RestController
//public class FinanceBankAccountController {
//
//    @Autowired
//    private FinanceBankAccountService financeBankAccountService;
//
//    @ApiOperation("查询银行账户表列表")
//    @GetMapping("/list")
//    public Payload<List<FinanceBankAccountResponseVO>> listFinanceBankAccounts(FinanceBankAccountRequestQuery query) throws Exception {
//        List<FinanceBankAccountResponseDTO> list = financeBankAccountService.listFinanceBankAccounts(query.clone(com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery.class, CloneDirection.OPPOSITE));
//        return new Payload<>(ObjectCloneUtils.convertList(list, FinanceBankAccountResponseVO.class, CloneDirection.OPPOSITE));
//    }
//
//    @ApiOperation("分页查询银行账户表列表")
//    @GetMapping("/page")
//    public Payload<PageBean<FinanceBankAccountResponseVO>> listFinanceBankAccountsPage(FinanceBankAccountRequestQuery query)  throws Exception {
//        PageBean<FinanceBankAccountResponseDTO> page = financeBankAccountService.listFinanceBankAccountsPage(query.clone(com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery.class, CloneDirection.OPPOSITE));
//        return new Payload<>(ObjectCloneUtils.convertPageBean(page, FinanceBankAccountResponseVO.class, CloneDirection.OPPOSITE));
//    }
//
//    @ApiOperation("批量删除银行账户表")
//    @DeleteMapping("")
//    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id)  throws Exception {
//        return new Payload<>(financeBankAccountService.deleteByIdIn(id));
//    }
//
//    @ApiOperation("新增银行账户表")
//    @PostMapping("")
//    public Payload<FinanceBankAccountResponseVO> insert(@RequestBody FinanceBankAccountRequestDTO record)  throws Exception {
//        FinanceBankAccountResponseDTO dto = financeBankAccountService.insert(record.clone(com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountRequestDTO.class, CloneDirection.OPPOSITE));
//        return new Payload<>(dto.clone(FinanceBankAccountResponseVO.class, CloneDirection.OPPOSITE));
//    }
//
//    @ApiOperation("根据ID查询银行账户表")
//    @GetMapping("/{id}")
//    public Payload<FinanceBankAccountResponseVO> selectById(@PathVariable Long id)  throws Exception {
//        FinanceBankAccountResponseDTO dto = financeBankAccountService.selectById(id);
//        return new Payload<>(dto.clone(FinanceBankAccountResponseVO.class, CloneDirection.OPPOSITE));
//    }
//
//    @ApiOperation("根据ID更新银行账户表")
//    @PutMapping("/{id}")
//    public Payload<Boolean> updateById(@PathVariable Long id,@RequestBody FinanceBankAccountRequestDTO record)  throws Exception {
//        return new Payload<>(financeBankAccountService.updateById(id, record.clone(com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountRequestDTO.class, CloneDirection.OPPOSITE)));
//    }
//}
