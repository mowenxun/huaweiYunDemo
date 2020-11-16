package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.dd.domain.transaction.service.SaleOrderItemService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SaleOrderItemController
 * @Description 销售订单明细接口
 * @Author SongTao
 * @Date 2020-07-10
 * @Version 1.0
 **/
@RestController
@Api(tags = "销售订单商品明细接口")
@RequestMapping("admin-api/v1/domain/transaction/saleOrderItem")
public class SaleOrderItemController {

    @Autowired
    private SaleOrderItemService saleOrderItemService;

    @ApiOperation("查询分页订单明细信息")
    @GetMapping("/page")
    public Payload<PageBean<SaleOrderItemResponseDTO>> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) {
        try{
            PageBean<SaleOrderItemResponseDTO> pageBean = saleOrderItemService.listSaleOrderItemsPage(query);
            return new Payload<>(pageBean);
        }
        catch (Exception e){
            return new Payload(null,"500",e.getMessage());
        }

    }
}
