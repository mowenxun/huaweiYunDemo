//package com.deepexi.dd.system.mall.api;
//
//import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountRequestDTO;
//import com.deepexi.dd.middle.finance.domain.dto.FinanceBankAccountResponseDTO;
//import com.deepexi.dd.middle.finance.domain.query.FinanceBankAccountRequestQuery;
//import com.deepexi.util.pageHelper.PageBean;
//import io.swagger.annotations.Api;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
///**
// * FinanceBankAccountApi
// *
// * @author admin
// * @date Fri Jun 19 17:38:17 CST 2020
// * @version 1.0
// */
//@Api(value = "银行账户表管理", tags = "银行账户表管理openApi")
//@RequestMapping("/openApi/v1/domain/financeBankAccounts")
//public interface FinanceBankAccountApi {
//    /**
//     * 查询银行账户表列表
//     *
//     * @return
//     */
//    @GetMapping("/list")
//    List<FinanceBankAccountResponseDTO> listFinanceBankAccounts(FinanceBankAccountRequestQuery query) throws Exception;
//
//    /**
//     * 分页查询银行账户表列表
//     *
//     * @return
//     */
//    @GetMapping("/page")
//    PageBean<FinanceBankAccountResponseDTO> listFinanceBankAccountsPage(FinanceBankAccountRequestQuery query)throws Exception;
//
//
//    /**
//     * 根据ID删除银行账户表
//     *
//     * @param id
//     * @return
//     */
//    @DeleteMapping("")
//    Boolean deleteByIdIn(@RequestBody List<Long> id) throws Exception;
//
//    /**
//     * 新增银行账户表
//     *
//     * @param record
//     * @return
//     */
//    @PostMapping("")
//    FinanceBankAccountResponseDTO insert(@RequestBody FinanceBankAccountRequestDTO record) throws Exception;
//
//    /**
//     * 查询银行账户表详情
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    FinanceBankAccountResponseDTO selectById(@PathVariable Long id) throws Exception;
//
//
//    /**
//     * 根据ID修改银行账户表
//     *
//     * @param id
//     * @param record
//     * @return
//     */
//    @PutMapping("/{id}")
//    Boolean updateById(@PathVariable Long id, @RequestBody FinanceBankAccountRequestDTO record) throws Exception;
//
//}