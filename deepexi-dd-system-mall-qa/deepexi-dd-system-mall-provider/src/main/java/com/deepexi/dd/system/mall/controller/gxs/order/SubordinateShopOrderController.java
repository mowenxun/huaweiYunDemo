package com.deepexi.dd.system.mall.controller.gxs.order;

import com.deepexi.dd.domain.transaction.api.gxs.domain.AfterSaleOrderDetailsResponseDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListAfterSaleOrderRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListAfterSaleOrderResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SubordinateShopAfterSaleOrderRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SubordinateShopOrderRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.dd.system.mall.enums.GxsSysResultEnum;
import com.deepexi.dd.system.mall.remote.customer.CompanyInfoClient;
import com.deepexi.dd.system.mall.service.gxs.ShopAfterSaleMallService;
import com.deepexi.dd.system.mall.service.ShopOrderSysService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.dto.CompanyInfoResponseApiDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsAfterSaleOrderStatusEnum;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubordinateShopOrderController(excel导出在交易域)
 * @Description:  下属门店的订单管理类
 * @Author Leon Wong
 * @Date 2020/10/16
 * @Version V1.0
 **/
@RestController
@Api(tags = "门店订购-下属门店订单管理")
@RequestMapping("/admin-api/v1/sys/subordinate/shop/order")
public class SubordinateShopOrderController {

    @Autowired
    private ShopOrderSysService shopOrderSysService;

    @Autowired
    ShopAfterSaleMallService shopAfterSaleMallService;

    @Autowired
    private CompanyInfoClient companyInfoClient;

    @ApiOperation("下属门店订单-普通订单-订单详情查看")
    @GetMapping("/{id}")
    public Payload<ShopOrderDetailVO> getOrderDetailById(@PathVariable Long id) throws Exception {
        return new Payload<>(shopOrderSysService.getShopOrderDetailById(id));
    }

    @ApiOperation("下属门店订单-普通订单-列表接口")
    @GetMapping("/page")
    public Payload<PageBean<PlatformShopOrderDomainResponseDTO>> listSubordinateShopOrdersPage(@Valid SubordinateShopOrderRequestQuery query) throws Exception{
        //前端把组织id当做供应商id传进来，转一遍
        if (query.getSellerId() != null && query.getSellerId() != 0) {
            List<Long> ids = new ArrayList<>();
            ids.add(query.getSellerId());
            Payload<List<CompanyInfoResponseApiDTO>> payload = companyInfoClient.listCompanyInfosByCompanyIds(ids);
            if (payload == null || CollectionUtils.isEmpty(payload.getPayload())) {
                throw new ApplicationException(GxsSysResultEnum.GROUP_ID_NOT_SUPPLIER);
            }
            List<CompanyInfoResponseApiDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                    CompanyInfoResponseApiDTO.class);
            query.setSellerId(list.get(0).getId());
        }
        return new Payload<>(shopOrderSysService.listPlatformShopOrdersPage(query.clone(ShopOrderDomainRequestQuery.class)));
    }

    @ApiOperation("下属门店订单-售后订单-列表接口")
    @GetMapping("/afterSaleOrder/page")
    public Payload<PageBean<ListAfterSaleOrderResponseDTO>> listSubordinateShopAfterSaleOrdersPage(@Valid SubordinateShopAfterSaleOrderRequestQuery query) throws Exception{
        ListAfterSaleOrderRequestDTO requestDTO = query.clone(ListAfterSaleOrderRequestDTO.class);
        //query
        String gxsName = query.getSellerNameLike() == null ? requestDTO.getAlterReceiveNameLike() : requestDTO.getSellerNameLike();
        requestDTO.setAlterReceiveNameLike(gxsName);
        requestDTO.setSellerNameLike(null);

        requestDTO.setOrgId(query.getParentShopId());
        PageBean<ListAfterSaleOrderResponseDTO> rs = shopAfterSaleMallService.listSubordinateShopAfterSales(requestDTO);
        rs.getContent().sort((e1,e2)->e2.getCreatedTime().compareTo(e1.getCreatedTime()));
        return new Payload<>(rs);

    }

    @ApiOperation("下属门店订单-售后订单-订单详情查看")
    @GetMapping("/afterSaleOrder/{id}")
    public Payload<AfterSaleOrderDetailsResponseDTO> getAfterSaleOrderDetailById(@ApiParam(value = "售后单的主键id", required = true) @PathVariable Long id) throws Exception{
        return new Payload<>(shopAfterSaleMallService.afterSaleDetails(id));
    }

    @ApiOperation("下属门店订单-售后订单-订单线下处理完成")
    @PutMapping("/afterSaleOrder/{id}")
    public Payload<Boolean> updateStatusById(@ApiParam(value = "售后单的主键id", required = true)
                                                 @PathVariable("id") Long id) throws Exception {
        return new Payload<>(shopAfterSaleMallService.updateStatus(id, GxsAfterSaleOrderStatusEnum.COMPLETED.getCode()));
    }
}
