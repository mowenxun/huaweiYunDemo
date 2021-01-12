package com.deepexi.dd.system.mall.controller.finance;

import com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionRequestDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO;
import com.deepexi.dd.domain.finance.domain.query.FinanceCollectionRequestQuery;
import com.deepexi.dd.system.mall.service.finance.FinanceCollectionService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName FinanceCollectionController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-08
 * @Version 1.0
 **/
@Api(value = "收款单表管理", tags = "收款单表管理")
@RequestMapping("/admin-api/v1/domain/customer/financeCollection")
@RestController
public class FinanceCollectionController {

    @Autowired
    private FinanceCollectionService collectionService;

    @ApiOperation("查询收款单表列表")
    @GetMapping("/list")
    public Payload<List<com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO>> listFinanceCollections(@RequestBody FinanceCollectionRequestQuery requestQuery) throws Exception {
        return collectionService.listFinanceCollections(requestQuery);
    }

    @ApiOperation("分页查询收款单表列表")
    @GetMapping("/page")
    public Payload<PageBean<com.deepexi.dd.domain.finance.domain.dto.FinanceCollectionResponseDTO>> listFinanceCollectionsPage(@RequestBody FinanceCollectionRequestQuery query) throws Exception {
        return collectionService.listFinanceCollectionsPage(query);
    }

    @ApiOperation("批量删除收款单表")
    @DeleteMapping("")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) throws Exception {
        return collectionService.deleteByIdIn(id);
    }

    @ApiOperation("收款")
    @PostMapping("")
    public Payload<Long> insert(@RequestBody FinanceCollectionRequestDTO record) throws Exception {
        if (record.getAmount().compareTo(new BigDecimal(0)) < 1) {
            throw new ApplicationException("收款金额不能小于0");
        }
        return collectionService.insert(record);
    }

    @ApiOperation("确认收款")
    @PostMapping("/confirm/{recordId}")
    public Payload<Boolean> confirm(@PathVariable Long recordId) throws Exception {
        return collectionService.confirm(recordId);
    }


    @ApiOperation("取消确认收款")
    @GetMapping("/cancel/{recordId}")
    public Payload<Boolean> cancel(@PathVariable Long recordId) throws Exception {
        return collectionService.cancel(recordId);
    }

    @ApiOperation("根据ID查询收款单表")
    @GetMapping("/{id}")
    public Payload<FinanceCollectionResponseDTO> selectById(@PathVariable Long id) throws Exception {
        return collectionService.selectById(id);
    }

    @ApiOperation("根据ID更新收款单表")
    @PutMapping("/{id}")
    public Payload<Boolean> updateById(@PathVariable Long id, @RequestBody FinanceCollectionRequestDTO record) throws Exception {
        return collectionService.updateById(id, record);
    }
}
