package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.domain.query.SuppliersRequestQuery;
import com.deepexi.dd.domain.transaction.domain.dto.DistributionCheckResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.PayVoucherRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SupplerOrderResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SupplerOrderQuery;
import com.deepexi.dd.domain.transaction.export.SupplierOrderExportService;
import com.deepexi.dd.domain.transaction.service.SupplierOrderService;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayVoucherRequestPageQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-10-15 14:39
 */
@RestController
@Api(tags = "查询已分发订单")
@RequestMapping("/admin-api/v1/domain/transaction/supplier/order")
public class SupplerOrderInfoController {

    @Autowired
    private SupplierOrderService supplierOrderService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private SupplierOrderExportService supplierOrderExportService;

    /**
     * 分页查询已分发订单列表
     * @param supplerOrderQuery
     * @return
     * @throws Exception
     */
    @ApiOperation("分页查询已分发订单列表")
    @GetMapping("/page")
    public Payload<PageBean<SupplerOrderResponseDTO>> listPageSupplerOrderResponseDTO(SupplerOrderQuery supplerOrderQuery) throws Exception {
        return new Payload<>(supplierOrderService.listPageSupplerOrderResponseDTO(supplerOrderQuery));
    }

    /**
     * 导出已分发订单列表
     * @param supplerOrderQuery
     */
    @GetMapping("/export")
    @ApiOperation("导出已分发订单列表")
    public void exportSupplierOrder(SupplerOrderQuery supplerOrderQuery) throws Exception {
        supplierOrderExportService.export(response,supplerOrderQuery);
    }

    /**
     * 查分发单详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/getSupplierDetails/{id}")
    @ApiOperation("查分发单详情")
    public Payload<SupplerOrderResponseDTO> getSupplierDetails(@PathVariable("id") Long id) throws Exception {
        return new Payload<>(supplierOrderService.getSupplierDetails(id));
    }


    /**
     * 配货查看
     * @return
     */
    @ApiOperation("配货查看")
    @PostMapping("/distribution/check")
    public Payload<List<DistributionCheckResponseDTO>> getDistributionCheck(@RequestBody SupplerOrderQuery supplerOrderQuery) throws Exception {
        return new Payload<>(supplierOrderService.getDistributionCheck(supplerOrderQuery));
    }

    /**
     * 上传支付凭证
     * @param requestDTO
     * @return
     */
    @PostMapping("/upload/pay/voucher")
    @ApiOperation("上传支付凭证")
    public Payload<Boolean> uploadPayVoucher(@RequestBody PayVoucherRequestDTO requestDTO){
        return new Payload<>(supplierOrderService.uploadPayVoucher(requestDTO));
    }

    /**
     * 上传支付凭证
     * @param requestDTO
     * @return
     */
    @GetMapping("/search/pay/voucher")
    @ApiOperation("查询支付凭证")
    public Payload<List<PayVoucherResponseDTO>> getPayVoucher(PayVoucherRequestDTO requestDTO){
        return new Payload<>(supplierOrderService.getPayVoucher(requestDTO));
    }

    /**
     *  撤销分发
     * @param
     * @return
     */
    @GetMapping("/revocation/distribution/{id}")
    @ApiOperation("撤销分发")
    public Payload<Boolean> revocationDistribution(@PathVariable("id") Long id){
        return new Payload<>(supplierOrderService.revocationDistribution(id));
    }

}
