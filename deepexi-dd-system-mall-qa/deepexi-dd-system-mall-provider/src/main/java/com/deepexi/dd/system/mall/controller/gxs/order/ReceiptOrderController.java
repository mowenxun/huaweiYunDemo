package com.deepexi.dd.system.mall.controller.gxs.order;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/20:49
 * @Description:
 */

import com.deepexi.dd.domain.transaction.domain.query.gxs.SupplerOrderDomainQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.SupplerOrderDomainResponseDTO;
import com.deepexi.dd.system.mall.service.gxs.ReceiptOrderSysService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ReceiptOrderController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/19
 * @Version V1.0
 **/
@RestController
@Api(tags = "门店端-收货单接口")
@RequestMapping("/admin-api/v1/shop/receipt/order")
public class ReceiptOrderController {

    @Autowired
    private ReceiptOrderSysService receiptOrderSysService;

    @GetMapping("/page")
    @ApiOperation("供销社看到自己的收货单列表接口")
    public Payload<PageBean<SupplerOrderDomainResponseDTO>> getPage(SupplerOrderDomainQuery supplerOrderDomainQuery) throws Exception {
        return new Payload<>(receiptOrderSysService.listPageSupplerOrderResponseDTO(supplerOrderDomainQuery));
    }

    @GetMapping("/{id}")
    @ApiOperation("收货单详情")
    public Payload<SupplerOrderDomainResponseDTO> getDetail(@PathVariable Long id) throws Exception {
        return new Payload<>(receiptOrderSysService.getSupplierDetails(id));
    }

    @GetMapping("/sign/{id}")
    @ApiOperation("签收接口")
    public Payload<Boolean> signOrderById(@PathVariable Long id)throws Exception{
        return new Payload<>(receiptOrderSysService.signOrderById(id));
    }


}
