package com.deepexi.dd.system.mall.service.gxs;

import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 供销社，门店订购端 售后申请api
 *
 * @author huanghuai
 * @date 2020/10/14
 */
public interface ShopAfterSaleMallService {


    /**
     * 查询 售后申请list
     */
    PageBean<ListAfterSaleOrderResponseDTO> listAfterSales(ListAfterSaleOrderRequestDTO vo);

    /**
     * 查询 下属门店售后申请list
     */
    PageBean<ListAfterSaleOrderResponseDTO> listSubordinateShopAfterSales(ListAfterSaleOrderRequestDTO vo);

    /**
     * 创建售后单
     */
    Boolean createOrUpdateAfterSale(@RequestBody @Valid CreateAfterSaleOrderVO vo);

    /**
     * 售后单详情. <br>
     * 1.查售后单 after_sale_order
     * 2.查售后单 item after_sale_order_item
     */
    AfterSaleOrderDetailsResponseDTO afterSaleDetails(@ApiParam(value = "售后单的主键id", required = true) @PathVariable("id") Long id);

    /**
     * 修改售后单状态
     */
    Boolean updateStatus(@ApiParam(value = "售后单的主键id", required = true)
                                  @PathVariable("id") Long id,
                                  @ApiParam(value = "状态：1待审核 2审核通过 3驳回 4已完成", required = true)
                                  @RequestParam(value = "status") String status);

    /**
     * 查询售后单商品item(创建售后单)
     * 通过商品sku编码和商品名称模糊查询
     */
     PageBean<ShopOrderItemDomainResponseDTO> queryFinishedShopItems(SupplerOrderItemDomainRequestQuery query) ;

}
