package com.deepexi.dd.system.mall.service.gxs;

import com.deepexi.dd.domain.transaction.api.gxs.domain.CreatePayOrderItemRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderResponseDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.PayOrderDetailsResponseDTO;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 供销社，供应商端 财务管理api
 *
 * @author huanghuai
 * @date 2020/10/14
 */
public interface SupplierFinancialMallService {


    /**
     * 查询应收单 ，发货之后才有应收单， 付了钱才会发货
     */
    @GetMapping("/listPayOrders")
    @ApiOperation(value = "应收单列表展示", notes = "应收单列表展示")
     PageBean<ListPayOrderResponseDTO> listPayOrders(ListPayOrderRequestDTO vo);

    /**
     * 供应商财务收款<br>
     * 1.创建收款单
     * 2.修改应收单的已收和待收金额
     */
    @PostMapping("/supplierCollection")
    @ApiOperation(value = "供应商财务收款", notes = "供应商财务收款")
     Boolean supplierCollection(@RequestBody @Valid CreatePayOrderItemRequestDTO vo) ;


    /**
     * 查询应收单详情. <br>
     * 1.查应收单 pay_order
     * 2.查应收单item pay_order_item
     */
    @GetMapping("/payOrdersDetails/{id}")
    @ApiOperation(value = "查询应收单详情", notes = "查询应收单详情")
     PayOrderDetailsResponseDTO payOrdersDetails(@ApiParam(value = "应收单的主键id", required = true) @PathVariable("id") Long id) ;

}
