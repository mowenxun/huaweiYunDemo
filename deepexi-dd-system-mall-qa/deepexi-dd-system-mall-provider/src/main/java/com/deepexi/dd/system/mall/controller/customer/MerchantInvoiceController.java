package com.deepexi.dd.system.mall.controller.customer;

import com.deepexi.dd.system.mall.domain.dto.customer.MerchantInvoiceCreateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.customer.MerchantInvoiceUpdateAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.customer.MerchantInvoiceAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.MerchantInvoiceResponseVO;
import com.deepexi.dd.system.mall.service.customer.MerchantInvoiceService;
import com.deepexi.util.config.Payload;
import domain.dto.MerchantInvoiceCreateRequestDTO;
import domain.dto.MerchantInvoiceUpdateRequestDTO;
import domain.query.MerchantInvoiceRequestQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName MerchantInvoiceController
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Api(value = "客户开票管理", tags = "客户开票管理")
@RequestMapping("/admin-api/v1/domain/customer/invoice")
@RestController
public class MerchantInvoiceController {

    @Autowired
    private MerchantInvoiceService invoiceService;

    @GetMapping("/list")
    @ApiOperation(value = "查询列表", nickname = "findAllInvoice")
    public Payload<List<MerchantInvoiceResponseVO>> findAll(@Valid MerchantInvoiceAdminRequestQuery query) throws Exception {
        List<MerchantInvoiceResponseVO> result = invoiceService.findAll(query.clone(MerchantInvoiceRequestQuery.class));
        return new Payload<>(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询详情", nickname = "detailInvoice")
    public Payload<MerchantInvoiceResponseVO> detail(@PathVariable("id") Long pk) throws Exception {
        MerchantInvoiceResponseVO result = invoiceService.detail(pk);
        return new Payload<>(result);
    }

    @PutMapping
    @ApiOperation(value = "更新开票信息", nickname = "updateInvoice")
    public Payload<Boolean> update(@Valid @RequestBody MerchantInvoiceUpdateAdminRequestDTO dto) throws Exception {
        return invoiceService.update(dto.clone(MerchantInvoiceUpdateRequestDTO.class));
    }

    @PostMapping
    @ApiOperation(value = "创建开票信息", nickname = "createInvoice")
    public Payload<MerchantInvoiceResponseVO> create(@Valid @RequestBody MerchantInvoiceCreateAdminRequestDTO dto) throws Exception {
        MerchantInvoiceResponseVO result = invoiceService.create(dto.clone(MerchantInvoiceCreateRequestDTO.class));
        return new Payload<>(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除单个开票信息", nickname = "deleteInvoice")
    public Payload<Boolean> delete(@PathVariable(value = "id") Long id) throws Exception {
        return invoiceService.delete(id);
    }
}
