package com.deepexi.dd.system.mall.controller.gxs;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.dd.system.mall.domain.gxs.QueryFinishedShopItemResponseVO;
import com.deepexi.dd.system.mall.domain.gxs.QuerySupplierItemsRequestVO;
import com.deepexi.dd.system.mall.service.gxs.ShopAfterSaleMallService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 供销社，门店订购端 售后申请api
 *
 * @author huanghuai
 * @date 2020/10/14
 */
@Slf4j
@RestController
@RequestMapping("/admin-api/v1/gxs/shop/afterSale")
@Api(value = "ShopAfterSaleController", tags = "供销社-门店订购端-售后申请")
public class ShopAfterSaleController {

    @Autowired
    ShopAfterSaleMallService shopAfterSaleMallService;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;


    /**
     * 查询 售后申请list
     */
    @GetMapping("/listAfterSales")
    @ApiOperation(value = "售后申请列表展示", notes = "售后申请列表展示")
    public Payload<PageBean<ListAfterSaleOrderResponseDTO>> listAfterSales(ListAfterSaleOrderRequestDTO vo) {
        String gxsName = vo.getSellerNameLike() == null ? vo.getAlterReceiveNameLike() : vo.getSellerNameLike();
        vo.setAlterReceiveNameLike(gxsName);
        vo.setSellerNameLike(null);
        PageBean<ListAfterSaleOrderResponseDTO> rs = shopAfterSaleMallService.listAfterSales(vo);
        rs.getContent().sort((e1,e2)->e2.getCreatedTime().compareTo(e1.getCreatedTime()));
        return new Payload<>(rs);
    }

    /**
     * 创建售后单
     */
    @PostMapping()
    @ApiOperation(value = "创建或更新售后单: 提交售后单", notes = "创建或更新售后单: 提交售后单")
    public Payload<Boolean> createOrUpdateAfterSale(@RequestBody @Valid CreateAfterSaleOrderVO vo) {
        return new Payload<>(shopAfterSaleMallService.createOrUpdateAfterSale(vo));
    }


    /**
     * 查询售后单商品item(创建售后单)
     * 通过商品sku编码和商品名称模糊查询
     */
    @GetMapping("/queryFinishedShopItems")
    @ApiOperation(value = "查询退货商品", notes = "查询退货商品")
    public PageBean<QueryFinishedShopItemResponseVO> queryFinishedShopItems(@Valid QuerySupplierItemsRequestVO query) {
        PageBean<ShopOrderItemDomainResponseDTO> rs =
                shopAfterSaleMallService.queryFinishedShopItems(query.clone(SupplerOrderItemDomainRequestQuery.class, 1));
        return ObjectCloneUtils.convertPageBean(rs, QueryFinishedShopItemResponseVO.class, 1);
    }

    /**
     * 售后单详情. <br>
     * 1.查售后单 after_sale_order
     * 2.查售后单 item after_sale_order_item
     */
    @GetMapping("/afterSaleDetails/{id}")
    @ApiOperation(value = "查询售后单详情", notes = "查询售后单详情")
    public Payload<AfterSaleOrderDetailsResponseDTO> afterSaleDetails(
            @ApiParam(value = "售后单的主键id", required = true) @PathVariable("id") Long id) {
        return new Payload<>(shopAfterSaleMallService.afterSaleDetails(id));
    }

    /**
     * 修改售后单状态
     */
    @PutMapping("/updateStatus/{id}")
    @ApiOperation(value = "修改售后单状态", notes = "修改售后单状态")
    public Payload<Boolean> updateStatus(@ApiParam(value = "售后单的主键id", required = true)
                                         @PathVariable("id") Long id,
                                         @ApiParam(value = "状态：1待审核 2审核通过 3驳回 4已完成", required = true)
                                         @RequestParam(value = "status") String status) {
        return new Payload<>(shopAfterSaleMallService.updateStatus(id, status));
    }


}
