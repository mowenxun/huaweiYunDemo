package com.deepexi.dd.domain.transaction.util;

import com.deepexi.dd.domain.common.util.ObjectConvertUtil;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.add.SaleOrderItemAddRequestDTO;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoAddRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : SaleOrderInfoDTOHelper
 * @Description : 订单帮助类
 * @Author : yuanzaishun
 * @Date: 2020-07-01 19:52
 */
public class SaleOrderInfoDtoHelper {
    public static SaleOrderInfoDTO convertToSaleOrderInfoDTO(SaleOrderInfoRequestDTO requestDTO) {
        SaleOrderInfoDTO saleOrderInfoDTO = requestDTO.clone(SaleOrderInfoDTO.class);
        if (!TicketTypeEnum.PLAN.getValue().equals(saleOrderInfoDTO.getTicketType())) {//订货计划单没有地址操作，发票信息、费用信息

            if(Objects.nonNull(requestDTO.getOrderInvoiceInfo())){
                OrderInvoiceInfoDTO orderInvoiceInfoDTO = requestDTO.getOrderInvoiceInfo().clone(OrderInvoiceInfoDTO.class);
                orderInvoiceInfoDTO.setTenantId(saleOrderInfoDTO.getTenantId());
                orderInvoiceInfoDTO.setAppId(saleOrderInfoDTO.getAppId());
                saleOrderInfoDTO.setOrderInvoiceInfo(orderInvoiceInfoDTO);
            }

            saleOrderInfoDTO.setOrderExpenseInfoList(getOrderExpenseInfoList(requestDTO.getOrderExpenseInfo(),
                                                                             saleOrderInfoDTO));
            //设置送货地址
            if (!ObjectUtils.isEmpty(requestDTO.getOrderConsigneeInfo())) {
                OrderConsigneeInfoDTO orderConsigneeInfoDTO =
                        requestDTO.getOrderConsigneeInfo().clone(OrderConsigneeInfoDTO.class);
                orderConsigneeInfoDTO.setTenantId(saleOrderInfoDTO.getTenantId());
                orderConsigneeInfoDTO.setAppId(saleOrderInfoDTO.getAppId());
                saleOrderInfoDTO.setOrderConsigneeInfo(orderConsigneeInfoDTO);
            }
            //设置订单自提地址
            if (!ObjectUtils.isEmpty(requestDTO.getOrderSelfRaisingInfoAddInfo())) {
                OrderSelfRaisingInfoAddDTO orderSelfRaisingInfoAddDTO = requestDTO.getOrderSelfRaisingInfoAddInfo().clone(OrderSelfRaisingInfoAddDTO.class);
                saleOrderInfoDTO.setOrderSelfRaisingInfoAddInfo(orderSelfRaisingInfoAddDTO);
                if (!ObjectUtils.isEmpty(requestDTO.getOrderSelfRaisingInfoAddInfo().getFromStorehouse())) {
                    saleOrderInfoDTO.setFromStorehouse(requestDTO.getOrderSelfRaisingInfoAddInfo().getFromStorehouse());//设置发货仓库
                }
            }
        }
        saleOrderInfoDTO.setItems(getSaleOrderItemList(requestDTO.getItems()));
        return saleOrderInfoDTO;
    }

    public static SaleOrderInfoDTO convertToSaleOrderInfoDTO(SaleOrderInfoRequestDTO requestDTO,
                                                             com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO saleOrderInfoResponseDTO) {
        SaleOrderInfoDTO saleOrderInfoDTO = requestDTO.clone(SaleOrderInfoDTO.class);
        saleOrderInfoDTO.setId(saleOrderInfoResponseDTO.getId());
        saleOrderInfoDTO.setAppId(saleOrderInfoResponseDTO.getAppId());
        saleOrderInfoDTO.setTenantId(saleOrderInfoResponseDTO.getTenantId());
        saleOrderInfoDTO.setCode(saleOrderInfoResponseDTO.getCode());
        if (requestDTO.getOrderConsigneeInfo() != null) {
            OrderConsigneeInfoDTO orderConsigneeInfoDTO =
                    requestDTO.getOrderConsigneeInfo().clone(OrderConsigneeInfoDTO.class);
            orderConsigneeInfoDTO.setTenantId(saleOrderInfoDTO.getTenantId());
            orderConsigneeInfoDTO.setAppId(saleOrderInfoDTO.getAppId());
            saleOrderInfoDTO.setOrderConsigneeInfo(orderConsigneeInfoDTO);
        }
        saleOrderInfoDTO.setItems(getSaleOrderItemList(requestDTO.getItems()));
        if (requestDTO.getOrderInvoiceInfo() != null) {
            OrderInvoiceInfoDTO orderInvoiceInfoDTO = requestDTO.getOrderInvoiceInfo().clone(OrderInvoiceInfoDTO.class);
            orderInvoiceInfoDTO.setTenantId(saleOrderInfoDTO.getTenantId());
            orderInvoiceInfoDTO.setAppId(saleOrderInfoDTO.getAppId());
            saleOrderInfoDTO.setOrderInvoiceInfo(orderInvoiceInfoDTO);
        }

        saleOrderInfoDTO.setOrderExpenseInfoList(getOrderExpenseInfoList(requestDTO.getOrderExpenseInfo(),
                                                                         saleOrderInfoDTO));
        return saleOrderInfoDTO;
    }

    public static SaleOrderInfoDTO convertToSaleOrderInfoDTO(SaleOrderInfoAgentRequestDTO requestDTO) {
        SaleOrderInfoDTO saleOrderInfoDTO = requestDTO.clone(SaleOrderInfoDTO.class);
        OrderConsigneeInfoDTO orderConsigneeInfoDTO =
                requestDTO.getOrderConsigneeInfo().clone(OrderConsigneeInfoDTO.class);
        orderConsigneeInfoDTO.setTenantId(saleOrderInfoDTO.getTenantId());
        orderConsigneeInfoDTO.setAppId(saleOrderInfoDTO.getAppId());
        saleOrderInfoDTO.setOrderConsigneeInfo(orderConsigneeInfoDTO);
        saleOrderInfoDTO.setItems(getSaleOrderItemList(requestDTO.getItems()));

        if(Objects.nonNull(requestDTO.getOrderInvoiceInfo())){
            OrderInvoiceInfoDTO orderInvoiceInfoDTO = requestDTO.getOrderInvoiceInfo().clone(OrderInvoiceInfoDTO.class);
            orderInvoiceInfoDTO.setTenantId(saleOrderInfoDTO.getTenantId());
            orderInvoiceInfoDTO.setAppId(saleOrderInfoDTO.getAppId());
            saleOrderInfoDTO.setOrderInvoiceInfo(orderInvoiceInfoDTO);
        }

        saleOrderInfoDTO.setOrderExpenseInfoList(getOrderExpenseInfoList(requestDTO.getOrderExpenseInfo(),
                                                                         saleOrderInfoDTO));
        return saleOrderInfoDTO;
    }

    /**
     * 获取商品列表DO
     *
     * @param saleOrderItems
     * @return
     */
    private static List<SaleOrderItemDTO> getSaleOrderItemList(List<SaleOrderItemAddRequestDTO> saleOrderItems) {
        return ObjectConvertUtil.getList(saleOrderItems, SaleOrderItemDTO.class);
    }

    /**
     * 获取优惠券信息
     *
     * @param orderCouponInfoList
     * @return
     */
    private static List<OrderCouponInfoDTO> getOrderCouponInfoDTO(List<OrderCouponInfoRequestDTO> orderCouponInfoList) {
        List<OrderCouponInfoDTO> orderCouponInfoDOList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(orderCouponInfoList)) {
            orderCouponInfoList.forEach(item -> {
                OrderCouponInfoDTO infoDO = item.clone(OrderCouponInfoDTO.class);
                orderCouponInfoDOList.add(infoDO);
            });
        }
        return orderCouponInfoDOList;
    }

    /**
     * 获取费用列表DO
     *
     * @param orderExpenseInfoList
     * @return
     */
    private static List<OrderExpenseInfoDTO> getOrderExpenseInfoList(List<OrderExpenseInfoRequestDTO> orderExpenseInfoList, SaleOrderInfoDTO saleOrderInfoDTO) {
        List<OrderExpenseInfoDTO> orderExpenseInfos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(orderExpenseInfoList)) {
            orderExpenseInfoList.forEach(item -> {
                OrderExpenseInfoDTO infoDO = item.clone(OrderExpenseInfoDTO.class);
                infoDO.setTenantId(saleOrderInfoDTO.getTenantId());
                infoDO.setAppId(saleOrderInfoDTO.getAppId());
                orderExpenseInfos.add(infoDO);
            });
        }
        return orderExpenseInfos;
    }

    /**
     * 获取促销列表DO
     *
     * @param orderPromotionInfoList
     * @return
     */
    private static List<OrderPromotionInfoDTO> getOrderPromotionInfoList(List<OrderPromotionInfoRequestDTO> orderPromotionInfoList) {
        List<OrderPromotionInfoDTO> orderPromotionInfoDOs = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(orderPromotionInfoList)) {
            orderPromotionInfoList.forEach(item -> {
                OrderPromotionInfoDTO infoDO = item.clone(OrderPromotionInfoDTO.class);
                orderPromotionInfoDOs.add(infoDO);
            });
        }
        return orderPromotionInfoDOs;
    }

    public static SaleOrderInfoAddRequestDTO convertToMiddleSaleOrderInfoRequestDto(SaleOrderInfoDTO saleOrderInfoDto) {
        SaleOrderInfoAddRequestDTO saleOrderInfoAddRequestDTO =
                saleOrderInfoDto.clone(SaleOrderInfoAddRequestDTO.class);
        if (!TicketTypeEnum.PLAN.getValue().equals(saleOrderInfoAddRequestDTO.getTicketType())) {
            if (!ObjectUtils.isEmpty(saleOrderInfoDto.getOrderConsigneeInfo())) {
                //设置送货信息
                saleOrderInfoAddRequestDTO.setOrderConsigneeInfo(saleOrderInfoDto.getOrderConsigneeInfo().clone(com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoAddRequestDTO.class));
            }
            if (!ObjectUtils.isEmpty(saleOrderInfoDto.getOrderSelfRaisingInfoAddInfo())) {
                //设置自提信息
                saleOrderInfoAddRequestDTO.setOrderSelfRaisingInfo(saleOrderInfoDto.getOrderSelfRaisingInfoAddInfo().clone(OrderDeliverySelfRaisingInfoRequestDTO.class));
                //设置提货仓
                if (null != saleOrderInfoDto.getOrderSelfRaisingInfoAddInfo().getFromStorehouse())
                    saleOrderInfoAddRequestDTO.setFromStorehouse(saleOrderInfoDto.getOrderSelfRaisingInfoAddInfo().getFromStorehouse());
            }
            if(Objects.nonNull(saleOrderInfoDto.getOrderInvoiceInfo())){
                saleOrderInfoAddRequestDTO.setOrderInvoiceInfo(saleOrderInfoDto.getOrderInvoiceInfo().clone(com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoRequestDTO.class));
            }

            saleOrderInfoAddRequestDTO.setOrderCouponList(ObjectConvertUtil.getList(saleOrderInfoDto.getOrderCouponInfoList(), com.deepexi.dd.middle.order.domain.dto.OrderCouponInfoRequestDTO.class));
            saleOrderInfoAddRequestDTO.setOrderExpenseInfo(ObjectConvertUtil.getList(saleOrderInfoDto.getOrderExpenseInfoList(), com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoRequestDTO.class));
            saleOrderInfoAddRequestDTO.setOrderPromotionList(ObjectConvertUtil.getList(saleOrderInfoDto.getOrderPromotionInfoList(), com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoRequestDTO.class));
        }
        //防止price为null sql报错
        saleOrderInfoDto.getItems().forEach(item->{
            if(item.getPrice()==null){
                item.setPrice(new BigDecimal(0));
            }
        });
        saleOrderInfoAddRequestDTO.setItems(ObjectConvertUtil.getList(saleOrderInfoDto.getItems(),
                                                                      com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleRequestDTO.class));
        return saleOrderInfoAddRequestDTO;
    }

    /**
     * 提货单--->转普通订单 (线上)
     *
     * @param salePickGoodsInfoResponseDTO
     * @return
     */
    public static com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO convertToSaleOrderInfoResponseDTO(com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO) {
        com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO saleOrderInfoAddRequestDTO =
                salePickGoodsInfoResponseDTO.clone(com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO.class);
        saleOrderInfoAddRequestDTO.setCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());
        saleOrderInfoAddRequestDTO.setQuantity(salePickGoodsInfoResponseDTO.getTotalGoodsNumber());
        saleOrderInfoAddRequestDTO.setSellerId(salePickGoodsInfoResponseDTO.getSellerId());
        saleOrderInfoAddRequestDTO.setStatus(salePickGoodsInfoResponseDTO.getStatus());
        saleOrderInfoAddRequestDTO.setAccrueAmount(BigDecimal.valueOf(salePickGoodsInfoResponseDTO.getTotalGoodsMoney().doubleValue() - salePickGoodsInfoResponseDTO.getPayAmount().doubleValue()));
        return saleOrderInfoAddRequestDTO;
    }

    /**
     * 提货单--->转普通订单 (线下)
     *
     * @param salePickGoodsInfoResponseDTO
     * @return
     */
    public static SaleOrderResponseDTO convertToSaleOrderResponseDTO(com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO) {

        SaleOrderResponseDTO saleOrderResponseDTO =
                salePickGoodsInfoResponseDTO.clone(SaleOrderResponseDTO.class);
        saleOrderResponseDTO.setId(salePickGoodsInfoResponseDTO.getId());
        saleOrderResponseDTO.setCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());
        saleOrderResponseDTO.setTicketDate(salePickGoodsInfoResponseDTO.getCreatedTime());
        saleOrderResponseDTO.setVersion(salePickGoodsInfoResponseDTO.getVersion());
        saleOrderResponseDTO.setAccrueAmount(BigDecimal.valueOf(salePickGoodsInfoResponseDTO.getTotalGoodsMoney().doubleValue() - salePickGoodsInfoResponseDTO.getPayAmount().doubleValue()));
        saleOrderResponseDTO.setAscriptionOrgId(salePickGoodsInfoResponseDTO.getAscriptionOrgId());
        return saleOrderResponseDTO;
    }
}
