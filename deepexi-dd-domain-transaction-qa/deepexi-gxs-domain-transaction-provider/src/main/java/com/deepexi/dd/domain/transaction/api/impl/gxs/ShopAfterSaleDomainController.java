package com.deepexi.dd.domain.transaction.api.impl.gxs;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.ShopAfterSaleDomainApi;
import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.dd.domain.transaction.enums.ShopOrderStatusEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsAfterSaleOrderReasonTypeEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsAfterSaleOrderStatusEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.ShopOrderDomainRemote;
import com.deepexi.dd.domain.transaction.remote.gxs.ShopOrderItemDomainRemote;
import com.deepexi.dd.domain.transaction.remote.gxs.SupplerOrderItemRemote;
import com.deepexi.dd.domain.transaction.service.AfterSaleOrderItemDomainService;
import com.deepexi.dd.domain.transaction.service.AfterSaleOrderService;
import com.deepexi.dd.domain.transaction.service.common.CommonService;
import com.deepexi.dd.domain.transaction.util.ServiceUtil;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeRequestQuery;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemRequestQuery;
import com.deepexi.dd.middle.order.domain.query.ShopOrderItemRequestQuery;
import com.deepexi.dd.middle.order.domain.query.ShopOrderRequestQuery;
import com.deepexi.middle.merchant.domain.dto.GxsManagementResponseDTO;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 供销社，门店订购端 售后申请api
 *
 * @author huanghuai
 * @date 2020/10/14
 */
@Slf4j
@RestController
@Api(value = "ShopAfterSaleDomainController", tags = "供销社-门店订购端-售后申请")
public class ShopAfterSaleDomainController implements ShopAfterSaleDomainApi {


    @Autowired
    CommonService commonService;
    @Autowired
    AfterSaleOrderService afterSaleOrderService;
    @Autowired
    AfterSaleOrderItemDomainService afterSaleOrderItemDomainService;
    @Autowired
    SupplerOrderItemRemote supplerOrderItemRemote;
    @Autowired
    ShopOrderItemDomainRemote shopOrderItemDomainRemote;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;
    @Autowired
    ShopOrderDomainRemote shopOrderDomainRemote;

    /**
     * 查询 售后申请list
     */
    @ApiOperation(value = "售后申请列表展示", notes = "售后申请列表展示")
    public PageBean<ListAfterSaleOrderResponseDTO> listAfterSales(ListAfterSaleOrderRequestDTO vo) {
        AfterSaleOrdeRequestQuery query = vo.clone(AfterSaleOrdeRequestQuery.class, 1);
        // 根据组织id查询 门店id，只展示当前门店退货
        query.setShopId(commonService.queryOrgThrowIfNotFound(vo.getOrgId()).getId());
        PageBean<AfterSaleOrdeResponseDTO> page = afterSaleOrderService.listAfterSaleOrdesPage(query);
        return ObjectCloneUtils.convertPageBean(page, ListAfterSaleOrderResponseDTO.class, 1);
    }

    /**
     * 查询 下属门店售后申请list
     */
    @Override
    @ApiOperation(value = "下属门店售后申请列表展示", notes = "下属门店售后申请列表展示")
    public PageBean<ListAfterSaleOrderResponseDTO> listSubordinateShopAfterSales(ListAfterSaleOrderRequestDTO vo) {
        AfterSaleOrdeRequestQuery query = vo.clone(AfterSaleOrdeRequestQuery.class, 1);
        // current shop
        GxsManagementResponseDTO shopInfo = commonService.queryOrgThrowIfNotFound(vo.getOrgId());
        boolean topOrg = Long.valueOf(0).equals(shopInfo.getParentId());
        List<Long> ids = topOrg ? Arrays.asList(shopInfo.getId(), 0L) : Collections.singletonList(shopInfo.getId());
        query.setParentShopIds(ids);

        log.info("下属门店售后申请列表展示-请求request：{}", JSON.toJSONString(query));
        PageBean<AfterSaleOrdeResponseDTO> page = afterSaleOrderService.listAfterSaleOrdesPage(query);
        log.info("下属门店售后申请列表展示-返回response：{}", JSON.toJSONString(page));

        return ObjectCloneUtils.convertPageBean(page, ListAfterSaleOrderResponseDTO.class, 1);
    }

    /**
     * 创建售后单
     */
    @ApiOperation(value = "创建售后单", notes = "创建售后单")
    public Boolean createOrUpdateAfterSale(@RequestBody @Valid CreateAfterSaleOrderVO vo) {
        log.info("[{}][Create Or Update After Sale order]Input Params:{}", appRuntimeEnv.getRequestId(), vo);
        if (vo.getId() != null) {
            // return doUpdate(vo);
            updateDoDelete(vo);
        }
        // createAfterSaleParamPreCheck(vo);
        AfterSaleOrdeRequestDTO afterSale = vo.clone(AfterSaleOrdeRequestDTO.class, 1);
        afterSale.setQuantity(vo.getAfterSaleItems().stream().mapToLong(e -> e.getBackNum()).sum());
        BigDecimal computeTotalAmount = vo.getAfterSaleItems().stream().map(e -> e.getTotalAmount()).reduce((e1, e2) -> e1.add(e2)).get();
        afterSale.setTotalAmount(computeTotalAmount);

        // 根据组织id查到门店，以及parent_shop_id
        GxsManagementResponseDTO shopInfo = commonService.queryOrgThrowIfNotFound(vo.getOrgId());
        log.info("[{}][Create After Sale order]ShopInfo:{}", appRuntimeEnv.getRequestId(), shopInfo);
        GxsManagementResponseDTO gxsInfo = null;

        if (shopInfo.getParentId() == null || shopInfo.getParentId().equals(0L)) {
            gxsInfo = shopInfo;
        } else {
            gxsInfo = commonService.getShopById(shopInfo.getParentId());
            if (gxsInfo == null) {
                log.error("[{}][Create After Sale order]Filed, There are no parent shop info found 当前门店有父级，但是没有查询到",
                        appRuntimeEnv.getRequestId());
                throw new IllegalArgumentException("当前门店有父级，但是没有查询到,parentId:" + shopInfo.getParentId());
            }
            log.info("[{}][Create After Sale order]ParentInfo:{}", appRuntimeEnv.getRequestId(), gxsInfo);
        }
        CreateAfterSaleOrderVO.AfterSaleItem saleItem = vo.getAfterSaleItems().get(0);

        String gxsName = gxsInfo.getName();
        String gxsCode = gxsInfo.getShopNo();
        Long gxsId = gxsInfo.getId();

        Long parentId = shopInfo.getParentId();
        Long shopId = shopInfo.getId();
        String shopCode = shopInfo.getShopNo();
        String shopName = shopInfo.getShopName();

        afterSale.setShopId(shopId);
        afterSale.setShopCode(shopCode);
        afterSale.setShopName(shopName);
        afterSale.setParentShopId(parentId);
        afterSale.setSellerId(saleItem.getSellerId());
        afterSale.setSellerCode(saleItem.getSellerCode());
        afterSale.setSellerName(saleItem.getSellerName());
        afterSale.setStatus(GxsAfterSaleOrderStatusEnum.TO_BE_REVIEWED.getCode());
        afterSale.setAlterReceiveName(gxsName);
        afterSale.setAlterReceiveCode(gxsCode);
        afterSale.setAlterReceiveId(gxsId);
        AfterSaleOrdeResponseDTO insert = afterSaleOrderService.insert(afterSale);

        // 插入items
        vo.getAfterSaleItems().forEach(e -> {
            AfterSaleOrderItemRequestDTO item = e.clone(AfterSaleOrderItemRequestDTO.class, 1);
            item.setShopId(shopId);
            item.setShopName(shopName);
            item.setShopCode(shopCode);
            item.setAfterSaleOrderCode(insert.getOrderCode());
            item.setAfterSaleOrderId(insert.getId());
            afterSaleOrderItemDomainService.insert(item);
        });
        return Boolean.TRUE;
    }

    private void updateDoDelete(CreateAfterSaleOrderVO vo) {
        log.info("[{}][Update After Sale order]Delete origin:{}", appRuntimeEnv.getRequestId(), vo);
        afterSaleOrderService.deleteByIdIn(Arrays.asList(vo.getId()));
        afterSaleOrderItemDomainService.deleteByIdIn(ServiceUtil.mapCollect(vo.getAfterSaleItems(), e -> e.getId()));
    }

    private Boolean doUpdate(CreateAfterSaleOrderVO vo) {
        log.info("[{}][Update After Sale order]Input Params:{}", appRuntimeEnv.getRequestId(), vo);
        Boolean success = afterSaleOrderService.updateById(vo.getId(), vo.clone(AfterSaleOrdeRequestDTO.class, 1));
        if (success) {
            vo.getAfterSaleItems().forEach(e -> {
                afterSaleOrderItemDomainService.updateById(e.getId(), e.clone(AfterSaleOrderItemRequestDTO.class, 1));
            });
        }
        return success;
    }

    private void createAfterSaleParamPreCheck(CreateAfterSaleOrderVO vo) {
        GxsAfterSaleOrderReasonTypeEnum.throwIfCodeNotExist(vo.getReasonsType(), "创建售后单失败,[售后原因]枚举值错误");
        vo.getAfterSaleItems().forEach(e -> {
            if (e.getTotalAmount() == null || e.getTotalAmount().doubleValue() <= 0D) {
                throw new IllegalArgumentException("退货合计金额必须大于0");
            }
            if (e.getPrice() == null || e.getPrice().doubleValue() <= 0D) {
                throw new IllegalArgumentException("批发价格必须大于0");
            }
        });
    }

    /**
     * 查询当前门店，从上下文获取当前门店id，已经完成的商品(创建售后单)
     * 通过商品sku编码和商品名称模糊查询
     */
    @ApiOperation(value = "查询退货商品", notes = "查询退货商品")
    public PageBean<ShopOrderItemDomainResponseDTO> queryFinishedShopItems(@Valid SupplerOrderItemDomainRequestQuery query) {
        // 先查到已完成状态的订单
        ShopOrderRequestQuery shopOrderRequestQuery = new ShopOrderRequestQuery();
        GxsManagementResponseDTO shopInfo = commonService.queryOrgThrowIfNotFound(query.getOrgId());

        shopOrderRequestQuery.setShopId(shopInfo.getId());
        shopOrderRequestQuery.setAppId(query.getAppId());
        shopOrderRequestQuery.setTenantId(query.getTenantId());
        shopOrderRequestQuery.setStatus(ShopOrderStatusEnum.OVER.getValue());
        List<ShopOrderResponseDTO> shopOrders = shopOrderDomainRemote.listShopOrders(shopOrderRequestQuery);

        if (shopOrders.isEmpty()) {
            return new PageBean<>();
        }
        ShopOrderItemRequestQuery q = query.clone(ShopOrderItemRequestQuery.class, 1);
        q.setShopOrderIds(ServiceUtil.mapCollect(shopOrders, e -> e.getId()));
        PageBean<ShopOrderItemResponseDTO> page = shopOrderItemDomainRemote.listShopOrderItemsPage(q);
        // page.getContent().forEach(e -> {
        //     BigDecimal currentOrgPrice = getCurrentOrgPrice(e, shopInfo);
        //     if (currentOrgPrice != null && currentOrgPrice.doubleValue() > 0D) {
        //         e.setPrice(currentOrgPrice);
        //     }
        // });
        return ObjectCloneUtils.convertPageBean(page, ShopOrderItemDomainResponseDTO.class, 1);
    }

    /**
     * 设置当前门店应有的 商品价格
     *
     * @return
     */
    private BigDecimal getCurrentOrgPrice(ShopOrderItemResponseDTO shop, GxsManagementResponseDTO org) {
        //1：县级，2：乡镇级，3：村级，4：团购
        ServiceUtil.assertNull(org.getShopLevel(), "门店等级不能为空（listGxsManagementByGroupIds）：{}", org);
        switch (org.getShopLevel()) {
            case 1:
                return shop.getCountyPrice();
            case 2:
                return shop.getTownPrice();
            case 3:
                return shop.getVillagePrice();
            case 4:
                return shop.getGroupPurchasePrice();
            default:
                log.error("不存在这样的门店等级：{} 默认返回【单价：{}】, 组织架构信息：{}", org.getShopLevel(), shop.getPrice(), org);
                return shop.getPrice();
        }
    }


    /**
     * 售后单详情. <br>
     * 1.查售后单 after_sale_order
     * 2.查售后单 item after_sale_order_item
     */
    @ApiOperation(value = "查询售后单详情", notes = "查询售后单详情")
    public AfterSaleOrderDetailsResponseDTO afterSaleDetails(@ApiParam(value = "售后单的主键id", required = true) @PathVariable("id") Long id) {
        AfterSaleOrdeResponseDTO afterSaleOrder = afterSaleOrderService.selectById(id);
        if (afterSaleOrder == null) {
            return null;
        }
        // 售后单item
        AfterSaleOrderItemRequestQuery query = new AfterSaleOrderItemRequestQuery();
        query.setAfterSaleOrderId(id);
        List<AfterSaleOrderItemResponseDTO> items = afterSaleOrderItemDomainService.listAfterSaleOrderItems(query);

        AfterSaleOrderDetailsResponseDTO rs = afterSaleOrder.clone(AfterSaleOrderDetailsResponseDTO.class, 1);
        rs.setAfterSaleItems(ObjectCloneUtils.convertList(items, AfterSaleOrderDetailsResponseDTO.AfterSaleItem.class, 1));
        return rs;
    }

    /**
     * 修改售后单状态
     */
    @ApiOperation(value = "修改售后单状态", notes = "修改售后单状态")
    public Boolean updateStatus(@ApiParam(value = "售后单的主键id", required = true)
                                @PathVariable("id") Long id,
                                @ApiParam(value = "状态：1待审核 2审核通过 3驳回 4已完成", required = true)
                                @RequestParam(value = "status") String status) {
        GxsAfterSaleOrderStatusEnum.throwIfCodeNotExist(status);
        AfterSaleOrdeRequestDTO dto = new AfterSaleOrdeRequestDTO();
        dto.setId(id);
        dto.setStatus(status);
        return afterSaleOrderService.updateById(id, dto);
    }


}
