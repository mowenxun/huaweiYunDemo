package com.deepexi.dd.system.mall.controller.customer;

import com.deepexi.dd.system.mall.domain.dto.customer.MerchantDocumentCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantDocumentUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantDocumentAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantDocumentResponseVO;
import com.deepexi.dd.system.mall.service.customer.MerchantDocumentService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName MerchantDocumentController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Api(value = "客户资质材料管理", tags = "客户资质材料管理")
@RequestMapping("/admin-api/v1/domain/customer/document")
@RestController
public class MerchantDocumentController {

    @Autowired
    private MerchantDocumentService documentService;

    @PostMapping
    @ApiOperation(value = "创建", nickname = "createDocument")
    public Payload<MerchantDocumentResponseVO> create(@Valid @RequestBody MerchantDocumentCreateAdminRequestDTO vo) throws Exception {
        MerchantDocumentResponseVO result = documentService.create(vo);
        return new Payload<>(result);
    }

    @ApiOperation(value = "查询列表", nickname = "findAllDocument")
    @GetMapping("/list")
    public Payload<List<MerchantDocumentResponseVO>> findAll(@Valid MerchantDocumentAdminRequestQuery query) throws Exception {
        List<MerchantDocumentResponseVO> result = documentService.findAll(query);
        return new Payload<>(result);
    }

    @ApiOperation(value = "查询详情", nickname = "detail")
    @GetMapping("/{id}")
    public Payload<MerchantDocumentResponseVO> detail(@PathVariable("id") Long id) throws Exception {
        MerchantDocumentResponseVO result = documentService.detail(id);
        return new Payload<>(result);
    }

    @PutMapping
    @ApiOperation(value = "更新", nickname = "updateById")
    public Payload<Boolean> update(@Valid @RequestBody MerchantDocumentUpdateAdminRequestDTO vo) throws Exception {
        return documentService.update(vo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除", nickname = "deleteDocument")
    public Payload<Boolean> delete(@PathVariable(value = "id") Long id) throws Exception {
        return documentService.delete(id);
    }
}
