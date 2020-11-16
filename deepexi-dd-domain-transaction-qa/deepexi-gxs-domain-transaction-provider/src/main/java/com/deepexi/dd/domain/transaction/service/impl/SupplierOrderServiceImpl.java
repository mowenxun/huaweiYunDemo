package com.deepexi.dd.domain.transaction.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListSupplierOrderResponseDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.SupplierOrderDetailDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.SupplierOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.SupplerOrderQuery;
import com.deepexi.dd.domain.transaction.enums.OrderTypeEnum;
import com.deepexi.dd.domain.transaction.enums.ShopOrderPlatStatusEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsSupplierOrderPaymentStatusEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsSupplierOrderStatusEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.*;
import com.deepexi.dd.domain.transaction.service.ShopOrderDomainService;
import com.deepexi.dd.domain.transaction.service.ShopSupplerRelationDomainService;
import com.deepexi.dd.domain.transaction.service.SupplierOrderService;
import com.deepexi.dd.domain.transaction.util.ServiceUtil;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.query.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangjian
 * 已分发订单接口实现
 * @version 1.0
 * @date 2020-10-15 14:46
 */
@Service
@Slf4j
public class SupplierOrderServiceImpl implements SupplierOrderService {
    @Autowired
    AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private SupplierOrderDomainRemote supplierOrderDomainRemote;

    @Autowired
    private ShopSupplerRelationDomainService shopSupplerRelationDomainService;


    @Autowired
    private OrderInvoiceDomainRemote orderInvoiceDomainRemote;

    @Autowired
    private OrderConsigneeAddressDomainRemote orderConsigneeAddressDomainRemote;

    @Autowired
    private SupplerOrderItemRemote supplerOrderItemRemote;
    @Autowired
    ShopSupplerRelationRemote shopSupplerRelationRemote;

    @Autowired
    private PayVoucherRemote payVoucherRemote;

    @Autowired
    private ShopOrderDomainRemote shopOrderDomainRemote;
    @Autowired
    ShopOrderDomainService shopOrderDomainService;

    @Autowired
    private ShopOrderItemDomainRemote shopOrderItemDomainRemote;

    @Autowired
    private GxsManagementRemoteService gxsManagementClient;

    @Value("${plat.customer.name}")
    private String CUSTOMER_NAME;

    /**
     * 分页查询已分发订单列表
     *
     * @param supplerOrderQuery
     * @return
     */
    @Override
    public PageBean<SupplerOrderResponseDTO> listPageSupplerOrderResponseDTO(SupplerOrderQuery supplerOrderQuery) throws Exception {
        //查询已分发订单信息
        PageBean<SupplerOrderResponseDTO> supplerOrderResponseDTOPageBean =
                GeneralConvertUtils.convert2PageBean(supplierOrderDomainRemote.listSupplerOrdersPage(GeneralConvertUtils.conv(supplerOrderQuery, SupplerOrderRequestQuery.class)), SupplerOrderResponseDTO.class);
        supplerOrderResponseDTOPageBean.getContent().forEach(item -> {
            ShopSupplerRelationRequestQuery shopSupplerRelationResponseDTOS = new ShopSupplerRelationRequestQuery();
            shopSupplerRelationResponseDTOS.setSupplerOrderId(item.getId());
            List<ShopSupplerRelationResponseDTO> shopSupplerRelationResponseDTOS1 = shopSupplerRelationRemote.listShopSupplerRelations(shopSupplerRelationResponseDTOS);
            List<String> shopOrderCodes = shopSupplerRelationResponseDTOS1.stream().map(ShopSupplerRelationResponseDTO::getShopOrderCode).collect(Collectors.toList());
            item.setShopOrderCodes(shopOrderCodes);
            List<Long> shopOrderIds = shopSupplerRelationResponseDTOS1.stream().map(ShopSupplerRelationResponseDTO::getShopOrderId).collect(Collectors.toList());
            item.setShopOrderIds(shopOrderIds);

        });
        return supplerOrderResponseDTOPageBean;
    }

    @Override
    public List<SupplerOrderResponseDTO> listSupplerOrderResponseDTO(SupplerOrderQuery supplerOrderQuery) throws Exception {
        List<SupplerOrderResponseDTO> supplerOrderResponseDTOS =
                GeneralConvertUtils.convert2List(supplierOrderDomainRemote.listSupplerOrders(GeneralConvertUtils.conv(supplerOrderQuery, SupplerOrderRequestQuery.class)), SupplerOrderResponseDTO.class);
        supplerOrderResponseDTOS.stream().forEach(item -> {
            ShopSupplerRelationRequestQuery shopSupplerRelationRequestQuery = new ShopSupplerRelationRequestQuery();
            shopSupplerRelationRequestQuery.setSupplerOrderId(item.getId());
            List<ShopSupplerRelationResponseDTO> shopSupplerRelationResponseDTOS = shopSupplerRelationRemote.listShopSupplerRelations(shopSupplerRelationRequestQuery);
            List<String> collect = shopSupplerRelationResponseDTOS.stream().map(ShopSupplerRelationResponseDTO::getShopOrderCode).collect(Collectors.toList());
            item.setShopOrderCodes(collect);
        });
        return supplerOrderResponseDTOS;
    }

    @Override
    public SupplerOrderResponseDTO getSupplierDetails(Long id) throws Exception {
        //查询已分发订单主体
        SupplerOrderResponseDTO supplerOrderResponseDTO =
                GeneralConvertUtils.conv(supplierOrderDomainRemote.selectById(id), SupplerOrderResponseDTO.class);
        supplerOrderResponseDTO.setCustomerName(CUSTOMER_NAME);
        //查询发票信息
        OrderInvoiceRequestQuery orderInvoiceRequestQuery = new OrderInvoiceRequestQuery();
        orderInvoiceRequestQuery.setOrderId(id);
        orderInvoiceRequestQuery.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());//查已分发订单
        OrderInvoiceResponseDTO orderInvoiceResponseDTOS = GeneralConvertUtils.conv(orderInvoiceDomainRemote.listOrderInvoices(orderInvoiceRequestQuery).get(0), OrderInvoiceResponseDTO.class);
        supplerOrderResponseDTO.setOrderInvoiceResponseDTO(GeneralConvertUtils.conv(orderInvoiceResponseDTOS, OrderInvoiceResponseDTO.class));
        //查询收获地址
        OrderConsigneeAddressRequestQuery orderConsigneeAddressRequestQuery = new OrderConsigneeAddressRequestQuery();
        orderConsigneeAddressRequestQuery.setOrderId(id);
        orderConsigneeAddressRequestQuery.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());//查已分发订单
        OrderConsigneeAddressResponseDTO orderConsigneeAddressResponseDTO =
                GeneralConvertUtils.conv(orderConsigneeAddressDomainRemote.listOrderConsigneeAddresss(orderConsigneeAddressRequestQuery).get(0), OrderConsigneeAddressResponseDTO.class);
        supplerOrderResponseDTO.setOrderConsigneeAddressResponseDTO(orderConsigneeAddressResponseDTO);
        //获取订单明细
        SupplerOrderItemRequestQuery query = new SupplerOrderItemRequestQuery();
        query.setSupplerOrderId(id);
        List<SupplerOrderItemResponseDTO> supplerOrderItemResponseDTOS = GeneralConvertUtils.convert2List(supplerOrderItemRemote.listSupplerOrderItems(query), SupplerOrderItemResponseDTO.class);
        supplerOrderResponseDTO.setSupplerOrderItemResponseDTOS(supplerOrderItemResponseDTOS);
        return supplerOrderResponseDTO;
    }

    @Override
    public Boolean uploadPayVoucher(PayVoucherRequestDTO requestDTO) {
        if (null == requestDTO.getOrderId()) {
            throw new ApplicationException("请传入订单Id");
        }
        if (null == requestDTO.getPayMoney()) {
            throw new ApplicationException("本次支付金额");
        }
        com.deepexi.dd.middle.order.domain.dto.PayVoucherRequestDTO payVoucherRequestDTO = new com.deepexi.dd.middle.order.domain.dto.PayVoucherRequestDTO();
        payVoucherRequestDTO.setOrderId(requestDTO.getOrderId());
        List<PayVoucherResponseDTO> list =
                payVoucherRemote.list(payVoucherRequestDTO);
        List<BigDecimal> collect = list.stream().map(PayVoucherResponseDTO::getPayMoney).collect(Collectors.toList());
        collect.add(requestDTO.getPayMoney());
        BigDecimal pay = new BigDecimal(0);
        for (BigDecimal bigDecimal : collect) {
            if (null != bigDecimal) {
                pay = pay.add(bigDecimal);
            }
        }

        if(requestDTO.getTotalAmount().compareTo(pay) == -1){
            throw new ApplicationException("金额大于应支付总金额");
        }
        payVoucherRemote.insert(GeneralConvertUtils.conv(requestDTO, com.deepexi.dd.middle.order.domain.dto.PayVoucherRequestDTO.class));
        SupplerOrderRequestDTO supplerOrderRequestDTO = new SupplerOrderRequestDTO();
        if (requestDTO.getTotalAmount().compareTo(pay) == 1) {
            supplerOrderRequestDTO.setPaymentStatus(Integer.parseInt(GxsSupplierOrderPaymentStatusEnum.PARTIAL_PAYMENT.getCode()));
            supplierOrderDomainRemote.updateById(requestDTO.getOrderId(), supplerOrderRequestDTO);
        } else {
            supplerOrderRequestDTO.setPaymentStatus(Integer.parseInt(GxsSupplierOrderPaymentStatusEnum.PAID.getCode()));
            supplierOrderDomainRemote.updateById(requestDTO.getOrderId(), supplerOrderRequestDTO);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean revocationDistribution(Long id) {
        if (null == id) {
            throw new ApplicationException("请传入订单Id");
        }
        //供应商端的分发的订单变为“已撤销”
        SupplerOrderRequestDTO supplerOrderRequestDTO = new SupplerOrderRequestDTO();
        supplerOrderRequestDTO.setStatus(GxsSupplierOrderStatusEnum.RESCINDED.getCode());
        supplierOrderDomainRemote.updateById(id, supplerOrderRequestDTO);
        //门店的订单状态退回至“待分发”状态。
        ShopSupplerRelationRequestQuery shopSupplerRelationRequestQuery = new ShopSupplerRelationRequestQuery();
        shopSupplerRelationRequestQuery.setSupplerOrderId(id);
        List<ShopSupplerRelationResponseDTO> shopSupplerRelationResponseDTOS = shopSupplerRelationRemote.listShopSupplerRelations(shopSupplerRelationRequestQuery);
        List<String> shopOrderCodes =
                shopSupplerRelationResponseDTOS.stream().map(ShopSupplerRelationResponseDTO::getShopOrderCode).collect(Collectors.toList());
        log.info("获取到的门店单号:{}", shopOrderCodes);
        List<ShopOrderRequestDTO> list = new ArrayList<>();
        shopOrderCodes.forEach(item -> {
            ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
            shopOrderRequestDTO.setOrderCode(item);
            shopOrderRequestDTO.setPlatOrderStatus(ShopOrderPlatStatusEnum.DISTRIBUTE.getValue());
            list.add(shopOrderRequestDTO);
        });
        shopOrderDomainRemote.updateByCode(list);
        //去除门店订单和已分发订单得关联关系
        List<Long> ids = shopSupplerRelationResponseDTOS.stream().map(ShopSupplerRelationResponseDTO::getId).collect(Collectors.toList());
        return shopSupplerRelationRemote.deleteByIdIn(ids);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> ids) {
        log.info("[Delete supplier_order]Input Params:{}", ids);
        return supplierOrderDomainRemote.deleteByIdIn(ids);
    }

    @Override
    public SupplerOrderResponseDTO insert(SupplerOrderRequestDTO record) {
        log.info("[Insert supplier_order]Input Params:{}", record);
        return supplierOrderDomainRemote.insert(record).clone(SupplerOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public SupplerOrderResponseDTO selectById(Long id) {
        com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO result = supplierOrderDomainRemote.selectById(id);
        return result != null ? result.clone(SupplerOrderResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    public Boolean updateById(SupplerOrderRequestDTO record) {
        log.info("[{}][Update supplier_order]Input Params:{}", appRuntimeEnv.getRequestId(), record);
        return supplierOrderDomainRemote.updateById(record.getId(), record);
    }

    @Override
    public SupplierOrderDetailDTO orderDetailsByCode(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        SupplerOrderRequestQuery q = new SupplerOrderRequestQuery();
        q.setOrderCode(code);
        List<com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO> dtos = supplierOrderDomainRemote.listSupplerOrders(q);
        if (CollUtil.isEmpty(dtos)) {
            return null;
        }
        return doGetOrderDetail(dtos.get(0));
    }

    @Override
    public boolean sendGoods(Long id) {
        if (id == null) {
            return false;
        }
        GxsSupplierOrderStatusEnum delivered = GxsSupplierOrderStatusEnum.DELIVERED;
        GxsSupplierOrderStatusEnum toBeDelivered = GxsSupplierOrderStatusEnum.TO_BE_DELIVERED;
        com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO supplierOrder = supplierOrderDomainRemote.selectById(id);
        if (supplierOrder == null) {
            return false;
        }
        if (!toBeDelivered.getCode().equals(supplierOrder.getStatus())) {
            throw new IllegalArgumentException(StrUtil.format("供应商端【发货】操作只能由【{}】发起，实际状态【{}】",
                    delivered, GxsSupplierOrderStatusEnum.fromCode(supplierOrder.getStatus())));
        }

        // 更新供应商订单状态 已发货
        SupplerOrderRequestDTO supplerOrderRequestDTO = new SupplerOrderRequestDTO();
        supplerOrderRequestDTO.setId(id);
        supplerOrderRequestDTO.setStatus(delivered.getCode());
        supplierOrderDomainRemote.updateById(id, supplerOrderRequestDTO);
        log.info("[Deliver Goods]updated supplier_order status from {} to {} success", toBeDelivered, delivered);
        return true;
    }

    /**
     * 包含 1.供应商订单信息 <br>
     * 2.商品item supplier_order_item 表<br>
     * 3.发票信息 <br>
     * 4.收货地址信息 <br>
     */
    @Override
    public SupplierOrderDetailDTO orderDetailsById(Long id) {
        com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO supplierOrder = supplierOrderDomainRemote.selectById(id);
        if (supplierOrder == null) {
            return null;
        }
        return doGetOrderDetail(supplierOrder);
    }

    private SupplierOrderDetailDTO doGetOrderDetail(com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO supplierOrder) {
        String requestId = appRuntimeEnv.getRequestId();
        // 订单信息
        log.debug("[{}][Query Supplier Order Details]1.OrderInfo:{}", requestId, supplierOrder);
        Long id = supplierOrder.getId();

        // 商品信息
        SupplerOrderItemRequestQuery supplierOrderQuery = new SupplerOrderItemRequestQuery();
        supplierOrderQuery.setSupplerOrderId(id);
        List<com.deepexi.dd.middle.order.domain.dto.SupplerOrderItemResponseDTO> items = supplerOrderItemRemote.listSupplerOrderItems(supplierOrderQuery);
        log.debug("[{}][Query Supplier Order Details]2.ShopItemInfos:{}", requestId, items);

        // 发票信息
        OrderInvoiceRequestQuery orderQuery = new OrderInvoiceRequestQuery();
        orderQuery.setOrderId(id);
        List<com.deepexi.dd.middle.order.domain.dto.OrderInvoiceResponseDTO> orderInvoices = orderInvoiceDomainRemote.listOrderInvoices(orderQuery);
        com.deepexi.dd.middle.order.domain.dto.OrderInvoiceResponseDTO invoice = orderInvoices.isEmpty() ? null : orderInvoices.get(0);
        log.debug("[{}][Query Supplier Order Details]3.OrderInvoices:{}", requestId, orderInvoices);

        // 收货地址信息
        OrderConsigneeAddressRequestQuery addressRequestQuery = new OrderConsigneeAddressRequestQuery();
        addressRequestQuery.setOrderId(id);
        List<com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressResponseDTO> consigneeAddresses = orderConsigneeAddressDomainRemote.listOrderConsigneeAddresss(addressRequestQuery);
        com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressResponseDTO consigneeAddress = consigneeAddresses.isEmpty() ? null : consigneeAddresses.get(0);
        log.debug("[{}][Query Supplier Order Details]4.SendGoodsInfos:{}", requestId, consigneeAddresses);

        SupplierOrderDetailDTO rs = supplierOrder.clone(SupplierOrderDetailDTO.class, 1);
        rs.setItemList(ObjectCloneUtils.convertList(items, SupplierOrderItemResponseDTO.class, 1));
        rs.setInvoice(ServiceUtil.deepClone(invoice, SupplierOrderDetailDTO.Invoice.class));
        SupplierOrderDetailDTO.ConsigneeAddress address = ServiceUtil.deepClone(consigneeAddress, SupplierOrderDetailDTO.ConsigneeAddress.class);
        rs.setConsigneeAddress(address);
        if (address != null) {
            address.setReceiveDistributionName(supplierOrder.getReceiveDistributionName());
        }
        return rs;
    }

    /**
     * 供应商接单, 将订单状态由 ORDER_TO_BE_RECEIVED -->> TO_BE_DELIVERED  2.门店订单 改状态 待发货
     *
     * @param id
     * @return
     */
    @Override
    public boolean receiveOrder(Long id) {
        String requestId = appRuntimeEnv.getRequestId();
        boolean success = receiveOrderOfSupplierOrder(id, requestId);
        // 门店订单表平台状态 从 ShopOrderPlatStatusEnum#OVER_DISTRIBUTE--> DELIVER 已分发-->待发货
        shopSupplerRelationDomainService.listBySupplierOrderId(id).forEach(relation -> {
            try {
                shopOrderDomainService.receiveOrder(relation.getShopOrderId());
            } catch (Exception e) {
                log.error(StrUtil.format("门店{}接单失败:{}", relation.getShopOrderId(), e.getMessage()), e);
            }
        });
        return success;
    }

    private boolean receiveOrderOfSupplierOrder(Long id, String requestId) {
        log.info("[{}][Receive Order]Input Params: id={}", requestId, id);
        SupplerOrderRequestDTO dto = new SupplerOrderRequestDTO();
        dto.setId(id);
        GxsSupplierOrderStatusEnum orderToBeReceived = GxsSupplierOrderStatusEnum.ORDER_TO_BE_RECEIVED;
        GxsSupplierOrderStatusEnum toBeDelivered = GxsSupplierOrderStatusEnum.TO_BE_DELIVERED;
        // 数据库必须是 ORDER_TO_BE_RECEIVED（待接单） 才能接单
        com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO old = supplierOrderDomainRemote.selectById(id);
        if (old == null) {
            return false;
        }
        if (!orderToBeReceived.getCode().equals(old.getStatus())) {
            throw new IllegalArgumentException(StrUtil.format("供应商【接单】操作只能由{}发起，实际状态{}",
                    orderToBeReceived, GxsSupplierOrderStatusEnum.fromCode(old.getStatus())));
        }
        dto.setStatus(toBeDelivered.getCode());
        supplierOrderDomainRemote.updateById(id, dto);
        log.info("[{}][Receive Order]Updated supplier_order status from {} to {} success", requestId, toBeDelivered, orderToBeReceived);
        return true;
    }

    /**
     * 供应商拒单, 1.将订单状态改为 REJECTED只能从待接单改为拒单  2.门店订单 改状态 待发货
     *
     * @param id
     * @param reason
     * @return
     */
    @Override
    public boolean rejectOrder(Long id, String reason) {
        String requestId = appRuntimeEnv.getRequestId();
        log.info("[{}][Reject Order]Input Params: id={} reason={}", requestId, id, reason);
        Boolean success = rejectOrderOfSupplierOrder(id, reason, requestId);

        // 门店订单表平台状态 从 ShopOrderPlatStatusEnum OVER_DISTRIBUTE("3","已分发")-- >  REFUSE("9","已拒单")
        shopSupplerRelationDomainService.listBySupplierOrderId(id).forEach(relation -> {
            try {
                shopOrderDomainService.rejectOrder(relation.getShopOrderId());
            } catch (Exception e) {
                log.error(StrUtil.format("门店{}拒单失败:{}", relation.getShopOrderId(), e.getMessage()), e);
            }
        });
        return success;
    }

    private Boolean rejectOrderOfSupplierOrder(Long id, String reason, String requestId) {
        GxsSupplierOrderStatusEnum toBeReceived = GxsSupplierOrderStatusEnum.ORDER_TO_BE_RECEIVED;
        com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO old = supplierOrderDomainRemote.selectById(id);
        if (!toBeReceived.getCode().equals(old.getStatus())) {
            throw new IllegalArgumentException(StrUtil.format("供应商【拒单】操作只能由{}发起，实际状态{}",
                    toBeReceived, GxsSupplierOrderStatusEnum.fromCode(old.getStatus())));
        }

        SupplerOrderRequestDTO dto = new SupplerOrderRequestDTO();
        dto.setId(id);
        dto.setStatus(GxsSupplierOrderStatusEnum.REJECTED.getCode());
        dto.setRemark(reason);
        Boolean success = supplierOrderDomainRemote.updateById(id, dto);
        log.info("[{}][Reject Order {}] Update supplier_order status from {} to {} success", requestId, id, toBeReceived, GxsSupplierOrderStatusEnum.REJECTED);
        return success;
    }

    @Override
    public PageBean<ListSupplierOrderResponseDTO> listSupplerOrdersPage(SupplerOrderRequestQuery query) {
        log.debug("[Query supplier_order list]Input Params:{}", query);
        PageBean<com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO> pageBean = supplierOrderDomainRemote.listSupplerOrdersPage(query);
        PageBean<ListSupplierOrderResponseDTO> rs = ObjectCloneUtils.convertPageBean(pageBean, ListSupplierOrderResponseDTO.class, 1);
        if (!rs.getContent().isEmpty()) {
            // 设置来源订单
            rs.getContent().forEach(e -> {
                ShopSupplerRelationRequestQuery q = new ShopSupplerRelationRequestQuery();
                q.setSupplerOrderId(e.getId());
                String shopOrderCodes = shopSupplerRelationRemote.listShopSupplerRelations(q).stream()
                        .map(e1 -> e1.getShopOrderCode()).collect(Collectors.joining(","));
                e.setShopOrderCodes(shopOrderCodes);
            });
        }
        return rs;
    }

    @Override
    public List<DistributionCheckResponseDTO> getDistributionCheck(SupplerOrderQuery res) throws Exception {
        if (res.getSupplierIds() == null) {
            throw new ApplicationException("请传入订单Id");
        }
        //查询所有已签收得已分发订单
        SupplerOrderQuery supplerOrderQuery = new SupplerOrderQuery();
        supplerOrderQuery.setStatus(GxsSupplierOrderStatusEnum.SIGNED.getCode());
        List<SupplerOrderResponseDTO> supplerOrderResponseDTOS = listSupplerOrderResponseDTO(supplerOrderQuery);
        Map<String, List<SupplerOrderResponseDTO>> collect = supplerOrderResponseDTOS.stream()
                .filter(item -> res.getSupplierIds().contains(item.getId()))//过滤列表
                .collect(Collectors.groupingBy(SupplerOrderResponseDTO::getReceiveDistributionName));
        List<DistributionCheckResponseDTO> distributionCheckResponseDTOS = new ArrayList<>();
        //根据收货供销商分组
        collect.forEach((k, v) -> {
            DistributionCheckResponseDTO distributionCheckResponseDTO = new DistributionCheckResponseDTO();
            List<String> orderCodes = v.stream().map(SupplerOrderResponseDTO::getOrderCode).collect(Collectors.toList());
            distributionCheckResponseDTO.setSupplierOrderCodes(orderCodes);//供销社订单号
            distributionCheckResponseDTO.setReceiveDistributionName(k);//收货供销社
            //订单对应的门店订单号
            List<String> shopCodes = Lists.newArrayList();
            v.forEach(item -> shopCodes.addAll(item.getShopOrderCodes()));
            //根据门店订单Code查询门店订单
            List<ShopOrderResponseDTO> shopOrderResponseDTOS = Lists.newArrayList();
            shopCodes.forEach(item -> {
                ShopOrderRequestQuery shopOrderRequestQuery = new ShopOrderRequestQuery();
                shopOrderRequestQuery.setOrderCode(item);
                shopOrderResponseDTOS.addAll(GeneralConvertUtils.convert2List(shopOrderDomainRemote.listShopOrders(shopOrderRequestQuery), ShopOrderResponseDTO.class));
            });
            //按提货供销社进行分组
            Map<String, List<ShopOrderResponseDTO>> groupShopOrderResponseDTO =
                    shopOrderResponseDTOS.stream().filter(item -> item.getDeliveryDistributionCode() != null)
                            .collect(Collectors.groupingBy(ShopOrderResponseDTO::getDeliveryDistributionCode));
            //查询订单明细
            List<ShopOrderResponseDTO> result = Lists.newArrayList();
            groupShopOrderResponseDTO.forEach((n, m) -> {
                ShopOrderResponseDTO shopOrderResponseDTO = new ShopOrderResponseDTO();
                shopOrderResponseDTO.setDeliveryDistributionName(m.get(0).getDeliveryDistributionName());
                List<ShopOrderItemResponseDTO> shopOrderItemResponseDTOSList = Lists.newArrayList();
                List<ShopOrderItemResponseDTO> shopOrderItemDetailsResponseDTOSList = Lists.newArrayList();
                m.forEach(item -> {
                    ShopOrderItemRequestQuery shopOrderItemRequestQuery = new ShopOrderItemRequestQuery();
                    shopOrderItemRequestQuery.setShopOrderId(item.getId());
                    List<ShopOrderItemResponseDTO> shopOrderItemResponseDTOS = GeneralConvertUtils.convert2List(shopOrderItemDomainRemote.listShopOrderItems(shopOrderItemRequestQuery), ShopOrderItemResponseDTO.class);
                    List<ShopOrderItemResponseDTO> shopOrderItemDetailsResponseDTOS = GeneralConvertUtils.convert2List(shopOrderItemResponseDTOS, ShopOrderItemResponseDTO.class);
                    shopOrderItemResponseDTOSList.addAll(shopOrderItemResponseDTOS);
                    shopOrderItemDetailsResponseDTOSList.addAll(shopOrderItemDetailsResponseDTOS);
                });
                //对重复得商品进行数量累加
                Map<Long, List<ShopOrderItemResponseDTO>> shopItemToMapBySkuId = shopOrderItemResponseDTOSList.stream().filter(item -> item.getSkuId() != null).collect(Collectors.groupingBy(ShopOrderItemResponseDTO::getSkuId));
                List<ShopOrderItemResponseDTO> shopOrderItemResponseDTOS = addShopItem(shopItemToMapBySkuId);
                shopOrderResponseDTO.setShopOrderItemResponseDTOS(shopOrderItemResponseDTOS);
                //门店商品明细  根据门店分组
                List<ShopOrderItemDetailsResponseDTO> shopOrderItemDetailsResponseDTOList = Lists.newArrayList();
                if (CollectionUtil.isNotEmpty(shopOrderItemDetailsResponseDTOSList)) {
                    Map<String, List<ShopOrderItemResponseDTO>> shopItemDetails = shopOrderItemDetailsResponseDTOSList.stream().filter(item -> item.getShopCode() != null).collect(Collectors.groupingBy(ShopOrderItemResponseDTO::getShopCode));
                    shopItemDetails.forEach((x, y) -> {
                        ShopOrderItemDetailsResponseDTO shopOrderItemResponseDTO = new ShopOrderItemDetailsResponseDTO();
                        List<String> collect1 = y.stream().map(ShopOrderItemResponseDTO::getOrderCode).distinct().collect(Collectors.toList());
                        shopOrderItemResponseDTO.setShopOrderCodes(collect1);
                        shopOrderItemResponseDTO.setShopOrderItemResponseDTOS(y);
                        List<OrderConsigneeAddressResponseDTO> orderConsigneeAddressResponseDTOS = new ArrayList<>();
                        collect1.forEach(c -> {
                            OrderConsigneeAddressRequestQuery orderConsigneeAddressRequestQuery = new OrderConsigneeAddressRequestQuery();
                            orderConsigneeAddressRequestQuery.setOrderType(OrderTypeEnum.SHOP_ORDER.getValue());
                            orderConsigneeAddressRequestQuery.setOrderCode(c);
                            log.info("查询门店收货地址---门店单号:{}", orderConsigneeAddressRequestQuery);
                            List<OrderConsigneeAddressResponseDTO> list =
                                    GeneralConvertUtils.convert2List(orderConsigneeAddressDomainRemote.listOrderConsigneeAddresss(orderConsigneeAddressRequestQuery), OrderConsigneeAddressResponseDTO.class);
                            log.info("门店收货地址:{}", list);
                            orderConsigneeAddressResponseDTOS.addAll(list);
                        });
                        shopOrderItemResponseDTO.setOrderConsigneeAddressResponseDTOS(orderConsigneeAddressResponseDTOS);
                        shopOrderItemDetailsResponseDTOList.add(shopOrderItemResponseDTO);
                    });
                }
                //对重复商品进行累加
                List<ShopOrderItemDetailsResponseDTO> shopOrderItemDetailsResponseDTOS = Lists.newArrayList();
                for (ShopOrderItemDetailsResponseDTO shopOrderItemDetailsResponseDTO : shopOrderItemDetailsResponseDTOList) {
                    ShopOrderItemDetailsResponseDTO shopOrderItemDetailsResponseDTO1 = new ShopOrderItemDetailsResponseDTO();
                    List<ShopOrderItemResponseDTO> orderShopOrderItemResponseDTOS = shopOrderItemDetailsResponseDTO.getShopOrderItemResponseDTOS();
                    Map<Long, List<ShopOrderItemResponseDTO>> shopItems = orderShopOrderItemResponseDTOS.stream().filter(item -> item.getSkuId() != null).collect(Collectors.groupingBy(ShopOrderItemResponseDTO::getSkuId));
                    List<ShopOrderItemResponseDTO> newShopOrderItemResponseDTOS = addShopItem(shopItems);
                    shopOrderItemDetailsResponseDTO1.setShopOrderCodes(shopOrderItemDetailsResponseDTO.getShopOrderCodes());
                    shopOrderItemDetailsResponseDTO1.setShopOrderItemResponseDTOS(newShopOrderItemResponseDTOS);
                    shopOrderItemDetailsResponseDTO1.setOrderConsigneeAddressResponseDTOS(shopOrderItemDetailsResponseDTO.getOrderConsigneeAddressResponseDTOS());
                    shopOrderItemDetailsResponseDTOS.add(shopOrderItemDetailsResponseDTO1);
                }
                shopOrderResponseDTO.setShopOrderItemDetailsResponseDTOS(shopOrderItemDetailsResponseDTOS);
                //查询提货供销社收货地址
                GxsManagementResponseDTO address = null;
                try {
                    log.info("查询提货供销社---供销社id:{}", m.get(0).getDeliveryDistributionId());
                    address = GeneralConvertUtils.conv(gxsManagementClient.selectById(m.get(0).getDeliveryDistributionId()).getPayload(), GxsManagementResponseDTO.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("查询提货供销社收货地址失败");
                }
                shopOrderResponseDTO.setGxsManagementResponseDTO(address);
                result.add(shopOrderResponseDTO);
            });
            distributionCheckResponseDTO.setShopOrderResponseDTOS(result);
            distributionCheckResponseDTOS.add(distributionCheckResponseDTO);
        });
        return distributionCheckResponseDTOS;
    }

    private List<ShopOrderItemResponseDTO> addShopItem(Map<Long, List<ShopOrderItemResponseDTO>> shopItemToMapBySkuId) {
        if (CollectionUtil.isNotEmpty(shopItemToMapBySkuId)) {
            List<ShopOrderItemResponseDTO> result = new ArrayList<>();
            shopItemToMapBySkuId.forEach((q, w) -> {
                List<Long> skuQuantity = w.stream().map(ShopOrderItemResponseDTO::getSkuQuantity).collect(Collectors.toList());
                List<Long> signQuantity = w.stream().map(ShopOrderItemResponseDTO::getSignQuantity).collect(Collectors.toList());
                Long nSkuQuantity = 0L;
                for (Long aLong : skuQuantity) {
                    if (null == aLong) {
                        aLong = 0L;
                    }
                    nSkuQuantity += aLong;
                }
                Long nSignQuantity = 0L;
                for (Long aLong : signQuantity) {
                    if (null == aLong) {
                        aLong = 0L;
                    }
                    nSignQuantity += aLong;
                }
                w.get(0).setSkuQuantity(nSkuQuantity);
                w.get(0).setSignQuantity(nSignQuantity);
                result.add(w.get(0));
            });
            return result;
        }
        return null;
    }

    @Override
    public List<PayVoucherResponseDTO> getPayVoucher(PayVoucherRequestDTO requestDTO) {
        if (null == requestDTO.getOrderId()) {
            throw new ApplicationException("请传入订单ID");
        }
        List<PayVoucherResponseDTO> payVoucherResponseDTOS =
                GeneralConvertUtils.convert2List(payVoucherRemote.list(requestDTO.clone(com.deepexi.dd.middle.order.domain.dto.PayVoucherRequestDTO.class)), PayVoucherResponseDTO.class);
        log.info("查到的付款详情:{}", payVoucherResponseDTOS);
        return payVoucherResponseDTOS;
    }


}
