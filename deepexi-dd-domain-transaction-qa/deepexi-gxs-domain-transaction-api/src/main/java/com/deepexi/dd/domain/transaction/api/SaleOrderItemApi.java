package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SaleOrderItemApi
 * @Description 商品
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@Api(value = "销售订单商品明细模块rpc接口")
@RequestMapping("/open-api/v1/domain/saleOrderItemApi")
public interface SaleOrderItemApi {
    /**
     * @Description:  查询分页订单明细信息..
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    @GetMapping("/page")
    PageBean<SaleOrderItemResponseDTO> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) throws Exception;
}
