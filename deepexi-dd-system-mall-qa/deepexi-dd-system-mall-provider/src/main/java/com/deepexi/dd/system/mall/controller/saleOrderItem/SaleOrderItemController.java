package com.deepexi.dd.system.mall.controller.saleOrderItem;

import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.SaleOrderItemInfoResponseDTO;
import com.deepexi.dd.system.mall.service.SaleOrderItemService;
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
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@RestController
@Api(tags = "销售订单商品明细接口")
@RequestMapping("admin-api/v1/domain/sale/saleOrderItem")
public class SaleOrderItemController {

    @Autowired
    private SaleOrderItemService saleOrderItemService;

    @ApiOperation("查询分页订单明细信息")
    @GetMapping("/page")
    public Payload<PageBean<SaleOrderItemInfoResponseDTO>> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) throws Exception {
        PageBean<SaleOrderItemInfoResponseDTO> pageBean = saleOrderItemService.listSaleOrderItemsPage(query);
        return new Payload<>(pageBean);
    }

}
