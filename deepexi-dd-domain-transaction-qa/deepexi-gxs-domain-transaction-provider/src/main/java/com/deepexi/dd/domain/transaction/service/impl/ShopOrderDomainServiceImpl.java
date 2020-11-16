package com.deepexi.dd.domain.transaction.service.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/17:16
 * @Description:
 */

import cn.hutool.core.util.StrUtil;
import com.deepexi.dd.domain.business.domain.dto.BusinessCommodityRelationshipResponseDTO;
import com.deepexi.dd.domain.business.domain.dto.LevelPriceResponseDTO;
import com.deepexi.dd.domain.business.domain.query.LevelPriceQuery;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderAddDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.DistributionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionDomainRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionViewDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.enums.OrderTypeEnum;
import com.deepexi.dd.domain.transaction.enums.ShopOrderPlatStatusEnum;
import com.deepexi.dd.domain.transaction.enums.ShopOrderStatusEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.*;
import com.deepexi.dd.domain.transaction.remote.business.BusinessCommodityRelationshipDomainRemote;
import com.deepexi.dd.domain.transaction.remote.business.BusinessSkuPriceOpenClient;
import com.deepexi.dd.domain.transaction.remote.gxs.*;
import com.deepexi.dd.domain.transaction.service.ShopOrderDomainService;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.*;
import com.deepexi.middle.merchant.domain.dto.GxsManagementResponseDTO;
import com.deepexi.util.DateUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName ShopOrderDomainServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/13
 * @Version V1.0
 **/
@Slf4j
@Service
public class ShopOrderDomainServiceImpl implements ShopOrderDomainService {

    @Autowired
    private ShopOrderDomainRemote shopOrderDomainRemote;


    @Autowired
    private ShopOrderItemDomainRemote shopOrderItemDomainRemote;

    @Autowired
    IdentifierGenerator identifierGenerator;

    @Autowired
    private OrderInvoiceDomainRemote orderInvoiceDomainRemote;

    @Autowired
    private OrderConsigneeAddressDomainRemote orderConsigneeAddressDomainRemote;
    @Autowired
    private ShopSupplerRelationRemote shopSupplerRelationDomainRemote;

    @Autowired
    private SupplierOrderDomainRemote supplierOrderDomainRemote;

    @Autowired
    private SupplerOrderItemRemote supplerOrderItemRemote;

    @Autowired
    private SendGoodsInfoRemoteService sendGoodsInfoRemoteService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private commonServiceImpl commonService;

    @Autowired
    private BusinessSkuPriceOpenClient businessSkuPriceOpenClient;

    @Autowired
    private BusinessCommodityRelationshipDomainRemote businessCommodityRelationshipDomainRemote;


    private static final String YYYYMMDD = "yyyyMMdd";

    @Value("${appId}")
    private Long appId;

    @Override
    public PageBean<ShopOrderDomainResponseDTO> listShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception {

        ShopOrderRequestQuery shopOrderRequestQuery = query.clone(ShopOrderRequestQuery.class);
        if (StringUtils.isNotBlank(query.getSkuCode()) || StringUtils.isNotBlank(query.getSkuName())) {
            //skuName,skuCode查詢
            ShopOrderItemRequestQuery shopItemQuery = new ShopOrderItemRequestQuery();
            shopItemQuery.setSkuCode(query.getSkuCode());
            shopItemQuery.setSkuName(query.getSkuName());
            List<ShopOrderItemResponseDTO> itemResponseDTOList1 =
                    shopOrderItemDomainRemote.listShopOrderItems(shopItemQuery);
            List<Long> Ids =
                    itemResponseDTOList1.stream().map(ShopOrderItemResponseDTO::getShopOrderId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(Ids)) {
                return new PageBean<>();
            }
            //分頁查詢
            shopOrderRequestQuery.setIds(Ids);
        }
        PageBean<ShopOrderResponseDTO> pageBean = shopOrderDomainRemote.listShopOrdersPage(shopOrderRequestQuery);
        PageBean<ShopOrderDomainResponseDTO> domainResponseDTOPageBean = GeneralConvertUtils.convert2PageBean(pageBean,
                ShopOrderDomainResponseDTO.class);
        //封装详情
        List<ShopOrderDomainResponseDTO> list = domainResponseDTOPageBean.getContent();
        if (CollectionUtils.isNotEmpty(list)) {
            List<Long> shopOrderIds = list.stream().map(ShopOrderDomainResponseDTO::getId).collect(Collectors.toList());
            ShopOrderItemRequestQuery shopOrderItemRequestQuery = new ShopOrderItemRequestQuery();
            shopOrderItemRequestQuery.setShopOrderIds(shopOrderIds);
            List<ShopOrderItemResponseDTO> itemResponseDTOList =
                    shopOrderItemDomainRemote.listShopOrderItems(shopOrderItemRequestQuery);
            Map<Long, List<ShopOrderItemResponseDTO>> listMap =
                    itemResponseDTOList.stream().collect(Collectors.groupingBy(ShopOrderItemResponseDTO::getShopOrderId));
            list.forEach(item -> {
                item.setShopOrderItem(listMap.get(item.getId()));
            });
        }
        return domainResponseDTOPageBean;
    }

    @Override
    public List<ShopOrderAddDTO> addShopOrder(List<ShopOrderAddDTO> shopOrderAddDTOS) throws Exception {
        GxsManagementResponseDTO shop = commonService.getShop();
        //金额校验以及计算
        validShopAdd(shopOrderAddDTOS, shop);
        if (CollectionUtils.isNotEmpty(shopOrderAddDTOS)) {
            for (ShopOrderAddDTO shopOrderAddDTO : shopOrderAddDTOS) {
                shopOrderAddDTO.setAppId(appId);
                shopOrderAddDTO.setTenantId(appRuntimeEnv.getTenantId());
                shopOrderAddDTO.setShopId(shop.getId());
                shopOrderAddDTO.setShopName(shop.getShopName());
                shopOrderAddDTO.setShopCode(shop.getShopNo());
                //如果顶级供销社的直营门店的patentId=0;订单里面：顶级供销社父id=他自己的id
                shopOrderAddDTO.setParentShopId(shop.getParentId() == 0 ? shop.getId() : shop.getParentId());
                shopOrderAddDTO.getItem().forEach(item -> {
                    item.setSellerId(shopOrderAddDTO.getSellerId());
                    item.setSellerCode(item.getSellerCode());
                    item.setSellerName(item.getSellerName());
                    BigDecimal totalMoney = new BigDecimal(item.getSkuQuantity()).multiply(item.getPrice());
                    item.setTotalAmount(totalMoney);
                    item.setShopId(shop.getId());
                    item.setShopName(shop.getShopName());
                    item.setShopCode(shop.getShopNo());
                });
                //订单插入数据库
                insertShopOrder(shopOrderAddDTO);
                //发票信息插入数据库
                insertOrderInvoice(shopOrderAddDTO);

                insertOrderConsigneeAddress(shopOrderAddDTO);
            }
            return shopOrderAddDTOS;
        } else {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }
    }

    @Override
    public Boolean cancelShopOrder(Long id) throws Exception {
        ShopOrderRequestDTO record = new ShopOrderRequestDTO();
        record.setId(id);
        record.setStatus(ShopOrderStatusEnum.CANCEL.getValue());
        record.setPlatOrderStatus(ShopOrderPlatStatusEnum.CANCEL.getValue());
        return shopOrderDomainRemote.updateById(id, record);
    }

    @Override
    public ShopOrderDetailVO getShopOrderDetailById(Long id) throws Exception {
        ShopOrderResponseDTO shopOrderResponseDTO = shopOrderDomainRemote.selectById(id);
        if (shopOrderResponseDTO == null) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }
        ShopOrderDetailVO returnVo = shopOrderResponseDTO.clone(ShopOrderDetailVO.class);
        //明细
        ShopOrderItemRequestQuery shopOrderItemRequestQuery = new ShopOrderItemRequestQuery();
        shopOrderItemRequestQuery.setShopOrderId(shopOrderResponseDTO.getId());
        List<ShopOrderItemResponseDTO> itemResponseDTOList =
                shopOrderItemDomainRemote.listShopOrderItems(shopOrderItemRequestQuery);
        returnVo.setItem(itemResponseDTOList);

        //查询发票
        OrderInvoiceRequestQuery orderInvoiceRequestQuery = new OrderInvoiceRequestQuery();
        orderInvoiceRequestQuery.setOrderCode(shopOrderResponseDTO.getOrderCode());
        orderInvoiceRequestQuery.setOrderType(OrderTypeEnum.SHOP_ORDER.getValue());
        List<OrderInvoiceResponseDTO> orderInvoiceResponseDTOS =
                orderInvoiceDomainRemote.listOrderInvoices(orderInvoiceRequestQuery);
        if (CollectionUtils.isNotEmpty(orderInvoiceResponseDTOS)) {
            returnVo.setOrderInvoiceResponseDTO(orderInvoiceResponseDTOS.get(0));
        }
        //收货地址
        OrderConsigneeAddressRequestQuery query = new OrderConsigneeAddressRequestQuery();
        query.setOrderCode(shopOrderResponseDTO.getOrderCode());
        List<OrderConsigneeAddressResponseDTO> addressResponseDTOS =
                orderConsigneeAddressDomainRemote.listOrderConsigneeAddresss(query);
        if (CollectionUtils.isNotEmpty(addressResponseDTOS)) {
            returnVo.setOrderConsigneeAddressResponseDTO(addressResponseDTOS.get(0));
        }
        //店铺订单和已分发订单关系->发货信息
        ShopSupplerRelationRequestQuery shopSupplerRelationRequestQuery = new ShopSupplerRelationRequestQuery();
        shopSupplerRelationRequestQuery.setShopOrderCode(shopOrderResponseDTO.getOrderCode());
        List<ShopSupplerRelationResponseDTO> list =
                shopSupplerRelationDomainRemote.listShopSupplerRelations(shopSupplerRelationRequestQuery);
        if (CollectionUtils.isNotEmpty(list)) {
            SendGoodsInfoRequestQuery sendGoodsInfoRequestQuery = new SendGoodsInfoRequestQuery();
            sendGoodsInfoRequestQuery.setOrderCode(list.get(0).getSupplerOrderCode());
            List<SendGoodsInfoResponseDTO> sendGoodsInfoResponseDTOS =
                    sendGoodsInfoRemoteService.listSendGoodsInfos(sendGoodsInfoRequestQuery);
            if (CollectionUtils.isNotEmpty(sendGoodsInfoResponseDTOS)) {
                returnVo.setSendGoodsInfoResponseDTO(sendGoodsInfoResponseDTOS.get(0));
            }
        }
        //所属供销社
        GxsManagementResponseDTO gxsManagementResponseDTO =
                commonService.getShopById(shopOrderResponseDTO.getParentShopId());
        returnVo.setSupplyMarketingName(gxsManagementResponseDTO.getName());
        //返回订单对应的门店等级
        List<Long> shopIds = new ArrayList<>();
        shopIds.add(shopOrderResponseDTO.getShopId());
        List<GxsManagementResponseDTO> listShops = commonService.getShops(shopIds);
        //1：县级，2：乡镇级，3：村级，4：团购
        returnVo.setShopLevel(listShops.get(0).getShopLevel());
        return returnVo;
    }

    @Override
    public PageBean<PlatformShopOrderDomainResponseDTO> listPlatformShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception {
        PageBean<ShopOrderDomainResponseDTO> pageBean = listShopOrdersPage(query);
        PageBean<PlatformShopOrderDomainResponseDTO> returnVo = ObjectCloneUtils.convertPageBean(pageBean,
                PlatformShopOrderDomainResponseDTO.class);
        //查找关系，店铺订单-已分发订单
        if (CollectionUtils.isNotEmpty(pageBean.getContent())) {
            List<Long> shopOrderIds =
                    pageBean.getContent().stream().map(ShopOrderDomainResponseDTO::getId).collect(Collectors.toList());
            ShopSupplerRelationRequestQuery shopSupplerRelationRequestQuery = new ShopSupplerRelationRequestQuery();
            shopSupplerRelationRequestQuery.setShopOrderIds(shopOrderIds);
            List<ShopSupplerRelationResponseDTO> list =
                    shopSupplerRelationDomainRemote.listShopSupplerRelations(shopSupplerRelationRequestQuery);
            if (CollectionUtils.isNotEmpty(list)) {
                Map<Long, ShopSupplerRelationResponseDTO> map =
                        list.stream().collect(Collectors.toMap(ShopSupplerRelationResponseDTO::getShopOrderId,
                                Function.identity(), (k1, k2) -> k1));
                //组装
                returnVo.getContent().forEach(item -> {
                    item.setShopSupplerRelationResponseDTO(map.get(item.getId()));
                });
            }
        }
        return returnVo;
    }

    @Override
    public List<PlatformDistributionViewDomainResponseDTO> distributeView(List<Long> ids) throws Exception {
        List<PlatformDistributionViewDomainResponseDTO> returnVos =
                new ArrayList<PlatformDistributionViewDomainResponseDTO>();
        if (CollectionUtils.isEmpty(ids)) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }
        //获得订单集合
        ShopOrderRequestQuery shopOrderRequestQuery = new ShopOrderRequestQuery();
        shopOrderRequestQuery.setIds(ids);
        List<ShopOrderResponseDTO> shopOrderResponseDTOList =
                shopOrderDomainRemote.listShopOrders(shopOrderRequestQuery);
        if (CollectionUtils.isEmpty(shopOrderResponseDTOList)) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }
        //获得明细集合
        ShopOrderItemRequestQuery shopOrderItemRequestQuery = new ShopOrderItemRequestQuery();
        shopOrderItemRequestQuery.setShopOrderIds(ids);
        List<ShopOrderItemResponseDTO> shopOrderItemResponseDTOS =
                shopOrderItemDomainRemote.listShopOrderItems(shopOrderItemRequestQuery);
        //根据订单的供应商id分组
        Map<Long, List<ShopOrderResponseDTO>> orderMap =
                shopOrderResponseDTOList.stream().collect(Collectors.groupingBy(ShopOrderResponseDTO::getSellerId));
        //根据店铺订单id分组
        Map<Long, List<ShopOrderItemResponseDTO>> orderItemMap = shopOrderItemResponseDTOS.stream().collect
                (Collectors.groupingBy(ShopOrderItemResponseDTO::getShopOrderId));
        for (Map.Entry<Long, List<ShopOrderResponseDTO>> entry : orderMap.entrySet()) {
            PlatformDistributionViewDomainResponseDTO vo = new PlatformDistributionViewDomainResponseDTO();
            vo.setSellerId(entry.getKey());
            vo.setSellerCode(entry.getValue().get(0).getSellerCode());
            vo.setSellerName(entry.getValue().get(0).getSellerName());
            vo.setShopOrderResponseDTOS(entry.getValue());
            vo.setAppId(appId);
            vo.setTenantId(appRuntimeEnv.getTenantId());
            Long quantity = entry.getValue().stream().mapToLong(ShopOrderResponseDTO::getQuantity).sum();
            BigDecimal total = new BigDecimal(0);
            List<Long> shopIds = new ArrayList<>();
            for (ShopOrderResponseDTO shopOrderResponseDTO : entry.getValue()) {
                total = total.add(shopOrderResponseDTO.getTotalAmount());
                shopIds.add(shopOrderResponseDTO.getShopId());
            }
            vo.setQuantity(quantity);
            vo.setTotalAmount(total);
            List<ShopOrderItemResponseDTO> shopOrderItem = new ArrayList<>();
            entry.getValue().forEach(item -> {
                shopOrderItem.addAll(orderItemMap.get(item.getId()));

            });
            //如果skuid相同，合并
            List<ShopOrderItemResponseDTO> shopOrderItem1 = new ArrayList<>();
            Map<Long, List<ShopOrderItemResponseDTO>> listMap =
                    shopOrderItem.stream().collect(Collectors.groupingBy(ShopOrderItemResponseDTO::getSkuId));
            for (Map.Entry<Long, List<ShopOrderItemResponseDTO>> entry1 : listMap.entrySet()) {
                ShopOrderItemResponseDTO shopOrderItemResponseDTO = entry1.getValue().get(0);
                Long num = entry1.getValue().stream().mapToLong(sku -> sku.getSkuQuantity()).sum();
                BigDecimal totalAmount =
                        new BigDecimal(num).multiply(entry1.getValue().get(0).getPrice());
                shopOrderItemResponseDTO.setSkuQuantity(num);
                shopOrderItemResponseDTO.setTotalAmount(totalAmount);
                shopOrderItem1.add(shopOrderItemResponseDTO);
            }

            vo.setShopOrderItem(shopOrderItem1);
            //所属供销社；先获得店铺集合再通过parentId去获得供销社
            List<GxsManagementResponseDTO> gxsManagementResponseDTOS = commonService.getShops(shopIds);
            List<Long> parentIds =
                    gxsManagementResponseDTOS.stream().map(GxsManagementResponseDTO::getParentId).collect(Collectors.toList());
            List<GxsManagementResponseDTO> gxsParentManagementResponseDTOS = commonService.getShops(parentIds);
            //如果是顶级供销社展示他自己
            gxsManagementResponseDTOS.forEach(item -> {
                if (item.getParentId().equals(0)) {
                    gxsParentManagementResponseDTOS.add(item);
                }
            });
            vo.setDistributionResponseDTOS(ObjectCloneUtils.convertList(gxsParentManagementResponseDTOS,
                    DistributionResponseDTO.class));
            returnVos.add(vo);
        }

        return returnVos;
    }

    @Override
    public Boolean distributeOrder(List<PlatformDistributionDomainRequestDTO> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }

        for (PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO : list) {
            SupplerOrderRequestDTO record = platformDistributionDomainRequestDTO.clone(SupplerOrderRequestDTO.class);
            String code = identifierGenerator.getIdentifier(GxsIdentifierTypeEnum.SUPPLIER_ORDER.getType(),
                    GxsIdentifierTypeEnum.SUPPLIER_ORDER.getPrefix() + DateUtils.format(new Date(), YYYYMMDD),
                    IdentifierTypeEnum.ORDER.getLen());
            record.setAppId(appId);
            record.setTenantId(appRuntimeEnv.getTenantId());
            record.setOrderCode(code);
            record.setStatus(GxsSupplierOrderStatusEnum.ORDER_TO_BE_RECEIVED.getCode());
            record.setPaymentStatus(Integer.valueOf(GxsSupplierOrderPaymentStatusEnum.TO_BE_PAID.getCode()));
            record.setCreatedBy(appRuntimeEnv.getUsername());
            //插入分发订单表
            SupplerOrderResponseDTO supplerOrderResponseDTO = supplierOrderDomainRemote.insert(record);
            //插入明细表
            List<SupplerOrderItemRequestDTO> list1 =
                    ObjectCloneUtils.convertList(platformDistributionDomainRequestDTO.getItem(),
                            SupplerOrderItemRequestDTO.class);
            list1.forEach(item -> {
                item.setAppId(appId);
                item.setTenantId(appRuntimeEnv.getTenantId());
                item.setOrderCode(code);
                item.setSupplerOrderId(supplerOrderResponseDTO.getId());
            });
            supplerOrderItemRemote.saveBatch(list1);

            //发票
            OrderInvoiceRequestDTO orderInvoiceRequestDTO =
                    platformDistributionDomainRequestDTO.getOrderInvoiceRequestDTO();
            orderInvoiceRequestDTO.setOrderId(supplerOrderResponseDTO.getId());
            orderInvoiceRequestDTO.setOrderCode(code);
            orderInvoiceRequestDTO.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
            orderInvoiceRequestDTO.setAppId(appId);
            orderInvoiceRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderInvoiceDomainRemote.insert(orderInvoiceRequestDTO);

            //收货地址
            OrderConsigneeAddressRequestDTO orderConsigneeAddressRequestDTO =
                    platformDistributionDomainRequestDTO.getOrderConsigneeAddressRequestDTO();
            if (orderConsigneeAddressRequestDTO != null) {
                orderConsigneeAddressRequestDTO.setAppId(appId);
                orderConsigneeAddressRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
                orderConsigneeAddressRequestDTO.setOrderCode(code);
                orderConsigneeAddressRequestDTO.setOrderId(supplerOrderResponseDTO.getId());
                orderConsigneeAddressRequestDTO.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
                orderConsigneeAddressDomainRemote.insert(orderConsigneeAddressRequestDTO);
            }
            //关系记录下来
            List<ShopOrderResponseDTO> shopOrderResponseDTOList =
                    platformDistributionDomainRequestDTO.getShopOrderResponseDTOS();
            List<ShopSupplerRelationRequestDTO> shopSupplerRelationRequestDTOS = new ArrayList<>();
            shopOrderResponseDTOList.forEach(item -> {
                ShopSupplerRelationRequestDTO shopSupplerRelationRequestDTO =
                        item.clone(ShopSupplerRelationRequestDTO.class);
                shopSupplerRelationRequestDTO.setAppId(appId);
                shopSupplerRelationRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
                //shopSupplerRelationRequestDTO.setAppId(item.getAppId());
                //shopSupplerRelationRequestDTO.setCreatedBy(appRuntimeEnv.getUsername());
                shopSupplerRelationRequestDTO.setShopOrderCode(item.getOrderCode());
                shopSupplerRelationRequestDTO.setShopOrderId(item.getId());
                shopSupplerRelationRequestDTO.setSupplerOrderCode(code);
                shopSupplerRelationRequestDTO.setSupplerOrderId(supplerOrderResponseDTO.getId());
                shopSupplerRelationRequestDTOS.add(shopSupplerRelationRequestDTO);
                //店铺点单平台端状态为已分发
                ShopOrderRequestDTO updateDTO = new ShopOrderRequestDTO();
                updateDTO.setPlatOrderStatus(ShopOrderPlatStatusEnum.OVER_DISTRIBUTE.getValue());
                updateDTO.setId(item.getId());
                shopOrderDomainRemote.updateById(item.getId(), updateDTO);
            });
            shopSupplerRelationDomainRemote.saveBatch(shopSupplerRelationRequestDTOS);

        }

        return true;
    }

    @Override
    public Boolean updateSign(Long supperId) throws Exception {
        ShopSupplerRelationRequestQuery shopSupplerRelationRequestQuery = new ShopSupplerRelationRequestQuery();
        shopSupplerRelationRequestQuery.setSupplerOrderId(supperId);
        List<ShopSupplerRelationResponseDTO> list =
                shopSupplerRelationDomainRemote.listShopSupplerRelations(shopSupplerRelationRequestQuery);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(item -> {
                ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
                shopOrderRequestDTO.setStatus(ShopOrderStatusEnum.REPLACE_SIGN.getValue());
                shopOrderRequestDTO.setPlatOrderStatus(ShopOrderPlatStatusEnum.REPLACE_SIGN.getValue());
                shopOrderDomainRemote.updateById(item.getShopOrderId(), shopOrderRequestDTO);
            });
            return true;
        } else {
            throw new ApplicationException(GxsResultEnum.SUP_SHOPORDER_NOT_FOUND);
        }

    }

    @Override
    public Boolean paySuccessUpdateStatus(List<Long> ids) throws Exception {
        if (CollectionUtils.isEmpty(ids)) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }
        ShopOrderRequestQuery shopOrderRequestQuery = new ShopOrderRequestQuery();
        shopOrderRequestQuery.setIds(ids);
        List<ShopOrderResponseDTO> list = shopOrderDomainRemote.listShopOrders(shopOrderRequestQuery);
        if (CollectionUtils.isEmpty(list)) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }
        list.forEach(item -> {
            ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
            shopOrderRequestDTO.setStatus(ShopOrderStatusEnum.DELIVER.getValue());
            shopOrderRequestDTO.setPlatOrderStatus(ShopOrderPlatStatusEnum.DISTRIBUTE.getValue());
            shopOrderRequestDTO.setPaymentType(GxsPayTypeEnum.CREDIT.getCode());
            shopOrderRequestDTO.setPaymentStatus(Integer.valueOf(GxsSupplierOrderPaymentStatusEnum.PAID.getCode()));
            shopOrderRequestDTO.setPayAmount(item.getTotalAmount());
            shopOrderDomainRemote.updateById(item.getId(), shopOrderRequestDTO);
        });
        return true;
    }

    @Override
    public Boolean signById(Long id) throws Exception {
        ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
        shopOrderRequestDTO.setStatus(ShopOrderStatusEnum.OVER.getValue());
        shopOrderRequestDTO.setPlatOrderStatus(ShopOrderPlatStatusEnum.OVER.getValue());
        return shopOrderDomainRemote.updateById(id, shopOrderRequestDTO);
    }

    @Override
    public List<PlatformDistributionViewDomainResponseDTO> againDistributeView(Long id) throws Exception {
        List<PlatformDistributionViewDomainResponseDTO> returnVos =
                new ArrayList<PlatformDistributionViewDomainResponseDTO>();
        SupplerOrderResponseDTO supplerOrderResponseDTO = supplierOrderDomainRemote.selectById(id);
        if (supplerOrderResponseDTO == null) {
            throw new ApplicationException(GxsResultEnum.SUPPLIER_ORDER_NOT_EXITS);
        }
        PlatformDistributionViewDomainResponseDTO vo = new PlatformDistributionViewDomainResponseDTO();
        vo.setReceiveAddress(supplerOrderResponseDTO.getReceiveAddress());
        vo.setReceiveDistributionCode(supplerOrderResponseDTO.getReceiveDistributionCode());
        vo.setReceiveDistributionId(supplerOrderResponseDTO.getReceiveDistributionId());
        vo.setReceiveDistributionName(supplerOrderResponseDTO.getReceiveDistributionName());
        vo.setSellerId(supplerOrderResponseDTO.getSellerId());
        vo.setSellerCode(supplerOrderResponseDTO.getSellerCode());
        vo.setSellerName(supplerOrderResponseDTO.getSellerName());
        vo.setRemark(supplerOrderResponseDTO.getRemark());
        vo.setArriveDate(supplerOrderResponseDTO.getArriveDate());
        //根据关系查询出对应的店铺订单
        ShopSupplerRelationRequestQuery shopSupplerRelationRequestQuery = new ShopSupplerRelationRequestQuery();
        shopSupplerRelationRequestQuery.setSupplerOrderId(id);
        List<ShopSupplerRelationResponseDTO> shopSupplerRelationResponseDTOList =
                shopSupplerRelationDomainRemote.listShopSupplerRelations(shopSupplerRelationRequestQuery);
        if (CollectionUtils.isEmpty(shopSupplerRelationResponseDTOList)) {
            throw new ApplicationException(GxsResultEnum.SUPPLIER_SHOP_ORDER_NOT_EXITS);
        }
        List<Long> shopOrderIds =
                shopSupplerRelationResponseDTOList.stream().map(ShopSupplerRelationResponseDTO::getShopOrderId).collect(Collectors.toList());
        //获得订单集合
        ShopOrderRequestQuery shopOrderRequestQuery = new ShopOrderRequestQuery();
        shopOrderRequestQuery.setIds(shopOrderIds);
        List<ShopOrderResponseDTO> shopOrderResponseDTOList =
                shopOrderDomainRemote.listShopOrders(shopOrderRequestQuery);
        if (CollectionUtils.isEmpty(shopOrderResponseDTOList)) {
            throw new ApplicationException(GxsResultEnum.REQUEST_PARA_ERRO);
        }
        //门店id
        List<Long> shopIds =
                shopOrderResponseDTOList.stream().map(ShopOrderResponseDTO::getShopId).collect(Collectors.toList());
        vo.setShopOrderResponseDTOS(shopOrderResponseDTOList);
        vo.setAppId(appId);
        vo.setTenantId(appRuntimeEnv.getTenantId());
        Long quantity = supplerOrderResponseDTO.getQuantity();
        BigDecimal total = supplerOrderResponseDTO.getTotalAmount();
        vo.setQuantity(quantity);
        vo.setTotalAmount(total);
        SupplerOrderItemRequestQuery supplerOrderItemRequestQuery = new SupplerOrderItemRequestQuery();
        supplerOrderItemRequestQuery.setSupplerOrderId(id);
        List<SupplerOrderItemResponseDTO> supplerOrderItemResponseDTOList =
                supplerOrderItemRemote.listSupplerOrderItems(supplerOrderItemRequestQuery);
        vo.setShopOrderItem(GeneralConvertUtils.convert2List(supplerOrderItemResponseDTOList,
                ShopOrderItemResponseDTO.class));
        //所属供销社；获得所有门店信息再通过parentId去获得对应的供销社
        List<GxsManagementResponseDTO> gxsManagementResponseDTOS = commonService.getShops(shopIds);
        List<Long> parentIds =
                gxsManagementResponseDTOS.stream().map(GxsManagementResponseDTO::getParentId).collect(Collectors.toList());
        List<GxsManagementResponseDTO> gxsParentManagementResponseDTOS = commonService.getShops(parentIds);
        //如果是顶级供销社展示他自己
        gxsManagementResponseDTOS.forEach(item -> {
            if (item.getParentId().equals(0)) {
                gxsParentManagementResponseDTOS.add(item);
            }
        });
        vo.setDistributionResponseDTOS(ObjectCloneUtils.convertList(gxsParentManagementResponseDTOS,
                DistributionResponseDTO.class));
        //获得发票信息
        OrderInvoiceRequestQuery orderInvoiceRequestQuery = new OrderInvoiceRequestQuery();
        orderInvoiceRequestQuery.setOrderId(id);
        orderInvoiceRequestQuery.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
        List<OrderInvoiceResponseDTO> orderInvoiceResponseDTOList =
                orderInvoiceDomainRemote.listOrderInvoices(orderInvoiceRequestQuery);
        if (CollectionUtils.isNotEmpty(orderInvoiceResponseDTOList)) {
            vo.setOrderInvoiceResponseDTO(orderInvoiceResponseDTOList.get(0));
        }
        //收货地址
        OrderConsigneeAddressRequestQuery addressRequestQuery = new OrderConsigneeAddressRequestQuery();
        addressRequestQuery.setOrderId(id);
        addressRequestQuery.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
        List<OrderConsigneeAddressResponseDTO> addressResponseDTOList =
                orderConsigneeAddressDomainRemote.listOrderConsigneeAddresss(addressRequestQuery);
        if (CollectionUtils.isNotEmpty(addressResponseDTOList)) {
            vo.setOrderConsigneeAddressResponseDTO(addressResponseDTOList.get(0));
        }
        returnVos.add(vo);

        return returnVos;
    }

    @Override
    public Boolean againDistributeOrder(PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO) throws Exception {
        //修改已分发订单
        validgainDistributeOrder(platformDistributionDomainRequestDTO);
        //修改已分发订单
        SupplerOrderResponseDTO supplerOrderResponseDTO =
                supplierOrderDomainRemote.selectById(platformDistributionDomainRequestDTO.getId());
        if (supplerOrderResponseDTO == null) {
            throw new ApplicationException(GxsResultEnum.SUPPLIER_ORDER_NOT_EXITS);
        }
        SupplerOrderRequestDTO supplerOrderRequestDTO = new SupplerOrderRequestDTO();
        supplerOrderRequestDTO.setTotalAmount(platformDistributionDomainRequestDTO.getTotalAmount());
        supplerOrderRequestDTO.setQuantity(platformDistributionDomainRequestDTO.getQuantity());
        supplerOrderRequestDTO.setReceiveDistributionId(platformDistributionDomainRequestDTO.getReceiveDistributionId());
        supplerOrderRequestDTO.setReceiveAddress(platformDistributionDomainRequestDTO.getReceiveAddress());
        supplerOrderRequestDTO.setReceiveDistributionCode(platformDistributionDomainRequestDTO.getReceiveDistributionCode());
        supplerOrderRequestDTO.setReceiveDistributionName(platformDistributionDomainRequestDTO.getReceiveDistributionName());
        supplerOrderRequestDTO.setRemark(platformDistributionDomainRequestDTO.getRemark());
        supplerOrderRequestDTO.setArriveDate(platformDistributionDomainRequestDTO.getArriveDate());
        supplerOrderRequestDTO.setStatus(GxsSupplierOrderStatusEnum.ORDER_TO_BE_RECEIVED.getCode());
        supplierOrderDomainRemote.updateById(platformDistributionDomainRequestDTO.getId(), supplerOrderRequestDTO);
        //修改明细
        List<SupplerOrderItemRequestDTO> listItem = new ArrayList<>();
        platformDistributionDomainRequestDTO.getItem().forEach(item -> {
            SupplerOrderItemRequestDTO supplerOrderItemRequestDTO = new SupplerOrderItemRequestDTO();
            supplerOrderItemRequestDTO.setCollectPurchasePrice(item.getCollectPurchasePrice());
            supplerOrderItemRequestDTO.setTotalAmount(item.getCollectPurchasePrice().multiply
                    (new BigDecimal(item.getSkuQuantity())));
            supplerOrderItemRequestDTO.setId(item.getId());
            listItem.add(supplerOrderItemRequestDTO);
        });
        supplerOrderItemRemote.updateBatchById(listItem);
        //替换发票信息
        OrderInvoiceRequestQuery orderInvoiceRequestQuery = new OrderInvoiceRequestQuery();
        orderInvoiceRequestQuery.setOrderId(platformDistributionDomainRequestDTO.getId());
        orderInvoiceRequestQuery.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
        List<OrderInvoiceResponseDTO> orderInvoiceResponseDTOList =
                orderInvoiceDomainRemote.listOrderInvoices(orderInvoiceRequestQuery);
        if (CollectionUtils.isNotEmpty(orderInvoiceResponseDTOList)) {
            List<Long> invoiceIds =
                    orderInvoiceResponseDTOList.stream().map(OrderInvoiceResponseDTO::getId).collect(Collectors.toList());
            orderInvoiceDomainRemote.deleteByIdIn(invoiceIds);
        }
        OrderInvoiceRequestDTO orderInvoiceRequestDTO =
                platformDistributionDomainRequestDTO.getOrderInvoiceRequestDTO();
        orderInvoiceRequestDTO.setOrderId(supplerOrderResponseDTO.getId());
        orderInvoiceRequestDTO.setOrderCode(supplerOrderResponseDTO.getOrderCode());
        orderInvoiceRequestDTO.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
        orderInvoiceRequestDTO.setAppId(appId);
        orderInvoiceRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        orderInvoiceDomainRemote.insert(orderInvoiceRequestDTO);

        //替换收货地址
        OrderConsigneeAddressRequestQuery addressRequestQuery = new OrderConsigneeAddressRequestQuery();
        addressRequestQuery.setOrderId(platformDistributionDomainRequestDTO.getId());
        addressRequestQuery.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
        List<OrderConsigneeAddressResponseDTO> addressResponseDTOS =
                orderConsigneeAddressDomainRemote.listOrderConsigneeAddresss(addressRequestQuery);
        if (CollectionUtils.isNotEmpty(addressResponseDTOS)) {
            List<Long> addressIds =
                    addressResponseDTOS.stream().map(OrderConsigneeAddressResponseDTO::getId).collect(Collectors.toList());
            orderConsigneeAddressDomainRemote.deleteByIdIn(addressIds);
        }
        OrderConsigneeAddressRequestDTO orderConsigneeAddressRequestDTO =
                platformDistributionDomainRequestDTO.getOrderConsigneeAddressRequestDTO();
        if (orderConsigneeAddressRequestDTO != null) {
            orderConsigneeAddressRequestDTO.setAppId(appId);
            orderConsigneeAddressRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderConsigneeAddressRequestDTO.setOrderCode(supplerOrderResponseDTO.getOrderCode());
            orderConsigneeAddressRequestDTO.setOrderId(supplerOrderResponseDTO.getId());
            orderConsigneeAddressRequestDTO.setOrderType(OrderTypeEnum.SUPPLIER_ORDER.getValue());
            orderConsigneeAddressDomainRemote.insert(orderConsigneeAddressRequestDTO);
        }
        //把对应的门店订单状态改为已分发
        ShopSupplerRelationRequestQuery shopSupplerRelationRequestQuery = new ShopSupplerRelationRequestQuery();
        shopSupplerRelationRequestQuery.setSupplerOrderId(platformDistributionDomainRequestDTO.getId());
        List<ShopSupplerRelationResponseDTO> shopSupplerRelationResponseDTOList =
                shopSupplerRelationDomainRemote.listShopSupplerRelations(shopSupplerRelationRequestQuery);
        if (CollectionUtils.isNotEmpty(shopSupplerRelationResponseDTOList)) {
            shopSupplerRelationResponseDTOList.forEach(item -> {
                ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
                shopOrderRequestDTO.setPlatOrderStatus(ShopOrderPlatStatusEnum.OVER_DISTRIBUTE.getValue());
                shopOrderDomainRemote.updateById(item.getShopOrderId(), shopOrderRequestDTO);
            });
        }


        return true;
    }

    @Override
    public boolean rejectOrder(Long id) {
        ShopOrderResponseDTO shopOrder = null;
        if (id == null || (shopOrder = shopOrderDomainRemote.selectById(id)) == null) {
            return false;
        }
        ShopOrderPlatStatusEnum overDistribute = ShopOrderPlatStatusEnum.OVER_DISTRIBUTE;
        ShopOrderPlatStatusEnum refuse = ShopOrderPlatStatusEnum.REFUSE;
        if (!overDistribute.getValue().equals(shopOrder.getPlatOrderStatus())) {
            throw new IllegalArgumentException(StrUtil.format("门店端【拒单】操作只能由【{}】发起，实际状态【{}】",
                    overDistribute, ShopOrderPlatStatusEnum.getOrderStatusEnum(shopOrder.getPlatOrderStatus())));
        } else {
            ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
            shopOrderRequestDTO.setPlatOrderStatus(refuse.getValue());
            shopOrderRequestDTO.setId(id);
            shopOrderDomainRemote.updateById(id, shopOrderRequestDTO);
            log.info("[Reject Order]updated shop_order plat_order_status from {} to {} success", overDistribute,
                    refuse);
        }
        return true;
    }

    @Override
    public boolean receiveOrder(Long id) {
        ShopOrderResponseDTO shopOrder = null;
        if (id == null || (shopOrder = shopOrderDomainRemote.selectById(id)) == null) {
            return false;
        }
        ShopOrderPlatStatusEnum overDistribute = ShopOrderPlatStatusEnum.OVER_DISTRIBUTE;
        ShopOrderPlatStatusEnum deliver = ShopOrderPlatStatusEnum.DELIVER;
        if (!overDistribute.getValue().equals(shopOrder.getPlatOrderStatus())) {
            throw new IllegalArgumentException(StrUtil.format("门店端【接单】操作只能由【{}】发起，实际状态【{}】",
                    overDistribute, ShopOrderPlatStatusEnum.getOrderStatusEnum(shopOrder.getPlatOrderStatus())));
        } else {
            ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
            shopOrderRequestDTO.setPlatOrderStatus(deliver.getValue());
            shopOrderRequestDTO.setId(id);
            shopOrderDomainRemote.updateById(id, shopOrderRequestDTO);
            log.info("[Receive Order]update shop_order plat_order_status from {} to {} success", overDistribute,
                    deliver);
        }
        return true;
    }

    @Override
    public boolean sendGoods(Long id) throws Exception {
        ShopOrderResponseDTO shopOrder = null;
        if (id == null || (shopOrder = shopOrderDomainRemote.selectById(id)) == null) {
            return false;
        }
        ShopOrderStatusEnum deliver = ShopOrderStatusEnum.DELIVER;
        ShopOrderStatusEnum alreadyDeliver = ShopOrderStatusEnum.ALREADY_DELIVER;
        ShopOrderPlatStatusEnum platDeliver = ShopOrderPlatStatusEnum.DELIVER;
        ShopOrderPlatStatusEnum platAlreadyDeliver = ShopOrderPlatStatusEnum.ALREADY_DELIVER;
        // 待发货才能变为已发货
        if (!deliver.getValue().equals(shopOrder.getStatus())
                && platDeliver.getValue().equals(shopOrder.getPlatOrderStatus())) {
            throw new IllegalArgumentException(StrUtil.format("门店端【发货】操作只能由【status：{} plat_status：{}】发起，实际状态【{} , {}】",
                    deliver, platDeliver, ShopOrderStatusEnum.getOrderStatusEnum(shopOrder.getStatus()),
                    ShopOrderPlatStatusEnum.getOrderStatusEnum(shopOrder.getPlatOrderStatus())));
        } else {
            ShopOrderRequestDTO shopOrderRequestDTO = new ShopOrderRequestDTO();
            shopOrderRequestDTO.setStatus(alreadyDeliver.getValue());
            shopOrderRequestDTO.setPlatOrderStatus(platAlreadyDeliver.getValue());
            shopOrderRequestDTO.setId(id);
            shopOrderDomainRemote.updateById(id, shopOrderRequestDTO);
            log.info("[Deliver Goods]updated shop_order status from {} to {} and plat_order_status from {} to {} " +
                            "success",
                    deliver, alreadyDeliver, platDeliver, platAlreadyDeliver);
        }
        return true;
    }

    /**
     * 收货地址插入地址表
     *
     * @param shopOrderAddDTO
     */
    public void insertOrderConsigneeAddress(ShopOrderAddDTO shopOrderAddDTO) {
        OrderConsigneeAddressRequestDTO orderConsigneeAddressRequestDTO =
                shopOrderAddDTO.getOrderConsigneeAddressRequestDTO();
        orderConsigneeAddressRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
        orderConsigneeAddressRequestDTO.setAppId(appId);
        orderConsigneeAddressRequestDTO.setOrderId(shopOrderAddDTO.getId());
        orderConsigneeAddressRequestDTO.setOrderCode(shopOrderAddDTO.getOrderCode());
        orderConsigneeAddressRequestDTO.setOrderType(OrderTypeEnum.SHOP_ORDER.getValue());
        orderConsigneeAddressDomainRemote.insert(orderConsigneeAddressRequestDTO);
    }

    /**
     * 发票插入快照表
     *
     * @param shopOrderAddDTO
     */
    public void insertOrderInvoice(ShopOrderAddDTO shopOrderAddDTO) {
        OrderInvoiceRequestDTO orderInvoiceRequestDTO = shopOrderAddDTO.getOrderInvoiceRequestDTO();
        if (orderInvoiceRequestDTO != null) {
            orderInvoiceRequestDTO.setAppId(appId);
            orderInvoiceRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            orderInvoiceRequestDTO.setOrderCode(shopOrderAddDTO.getOrderCode());
            orderInvoiceRequestDTO.setOrderId(shopOrderAddDTO.getId());
            orderInvoiceRequestDTO.setOrderType(OrderTypeEnum.SHOP_ORDER.getValue());
            orderInvoiceDomainRemote.insert(orderInvoiceRequestDTO);
        }
    }

    /**
     * 订单插入数据库
     *
     * @param shopOrderAddDTO
     */
    public void insertShopOrder(ShopOrderAddDTO shopOrderAddDTO) {
        String code = identifierGenerator.getIdentifier(GxsIdentifierTypeEnum.ORDER.getType(),
                GxsIdentifierTypeEnum.ORDER.getPrefix() + DateUtils.format(new Date(), YYYYMMDD),
                IdentifierTypeEnum.ORDER.getLen());
        shopOrderAddDTO.setOrderCode(code);
        //店铺订单-店铺端状态
        shopOrderAddDTO.setStatus(ShopOrderStatusEnum.PAYMENT.getValue());
        //店铺订单-平台端状态
        shopOrderAddDTO.setPlatOrderStatus(ShopOrderPlatStatusEnum.PAYMENT.getValue());
        shopOrderAddDTO.setPaymentStatus(Integer.valueOf(GxsSupplierOrderPaymentStatusEnum.TO_BE_PAID.getCode()));
        ShopOrderRequestDTO record = shopOrderAddDTO.clone(ShopOrderRequestDTO.class);
        ShopOrderResponseDTO shopOrderResponseDTO = shopOrderDomainRemote.insert(record);
        shopOrderAddDTO.setId(shopOrderResponseDTO.getId());
        //生成明細
        List<ShopOrderItemRequestDTO> list = shopOrderAddDTO.getItem();
        list.forEach(item -> {
            item.setAppId(appId);
            item.setTenantId(appRuntimeEnv.getTenantId());
            item.setOrderCode(shopOrderAddDTO.getOrderCode());
            item.setShopOrderId(shopOrderAddDTO.getId());
        });
        shopOrderItemDomainRemote.saveBatch(list);
    }

    /**
     * 订单提价校验
     */
    public void validShopAdd(List<ShopOrderAddDTO> shopOrderAddDTOS, GxsManagementResponseDTO shop) throws Exception {
        //校验售卖价格是否一致
        shopOrderAddDTOS.forEach(order -> {
            LevelPriceQuery levelPriceQuery = new LevelPriceQuery();
            List<Long> skuIds =
                    order.getItem().stream().map(ShopOrderItemRequestDTO::getSkuId).collect(Collectors.toList());
            //是否上下架
            List<BusinessCommodityRelationshipResponseDTO> businessCommodityRelationshipResponseDTOS =
                    businessCommodityRelationshipDomainRemote.findBusinessCommodityByGoodId(skuIds);
            if (CollectionUtils.isEmpty(businessCommodityRelationshipResponseDTOS)) {
                throw new ApplicationException(GxsResultEnum.COMMODITY_NOT_SHOP);
            }
            List<Long> goodsId =
                    businessCommodityRelationshipResponseDTOS.stream().
                            map(BusinessCommodityRelationshipResponseDTO::getSkuId).collect(Collectors.toList());
           /* if (skuIds.size() != businessCommodityRelationshipResponseDTOS.size()) {
                throw new ApplicationException(GxsResultEnum.COMMODITY_NOT_SHOP);
            }*/
            skuIds.forEach(skuId -> {
                if (!goodsId.contains(skuId)) {
                    throw new ApplicationException(GxsResultEnum.COMMODITY_NOT_SHOP);
                }
            });

            levelPriceQuery.setSkuIds(skuIds);
            levelPriceQuery.setGroupId(appRuntimeEnv.getUserOrganization().getId());
            try {
                //获得售卖价格
                Payload<List<LevelPriceResponseDTO>> payload =
                        businessSkuPriceOpenClient.queryLevelPriceBySkuIdsAndGroupId(levelPriceQuery);
                if (payload == null || CollectionUtils.isEmpty(payload.getPayload())) {
                    throw new ApplicationException(GxsResultEnum.SKU_PRICE_NOT_FOUND);
                }
                List<LevelPriceResponseDTO> listPrice = GeneralConvertUtils.convert2List(payload.getPayload(),
                        LevelPriceResponseDTO.class);
                if (skuIds.size() != listPrice.size()) {
                    throw new ApplicationException(GxsResultEnum.SKU_PRICE_NOT_FOUND);
                }
                Map<Long, LevelPriceResponseDTO> priceMap =
                        listPrice.stream().collect(Collectors.toMap(e -> e.getSkuId(), e -> e));
                //比较售卖价格是否一致
                order.getItem().forEach(sku -> {
                    if (priceMap.get(sku.getSkuId()).getLevelPrice() != null) {
                        if (sku.getPrice().compareTo(priceMap.get(sku.getSkuId()).getLevelPrice()) != 0) {
                            throw new ApplicationException(GxsResultEnum.SKU_PRICE_NOT_SAME);
                        }
                    }
                });

            } catch (Exception e) {
                throw new ApplicationException(e.getMessage());
                //e.printStackTrace();
            }
            //校验订单总金额是否一致
            AtomicReference<BigDecimal> orderTotalAmount = new AtomicReference<>(new BigDecimal(0));
            order.getItem().forEach(item -> {
                //明细总计
                item.setTotalAmount(new BigDecimal(item.getSkuQuantity()).multiply(item.getPrice()));
                orderTotalAmount.updateAndGet(v -> v.add((new BigDecimal(item.getSkuQuantity()).multiply(item.getPrice()))));
            });
            if (order.getTotalAmount().compareTo(orderTotalAmount.get()) != 0) {
                throw new ApplicationException(GxsResultEnum.ORDER_TOTAL_AMOUNT_NOT_SAME);
            }

        });


    }

    /**
     * 校验重新分发入参
     *
     * @param requestDTO
     */
    public void validgainDistributeOrder(PlatformDistributionDomainRequestDTO requestDTO) {
        //校验id
        if (requestDTO.getId() == null || requestDTO.getId() == 0) {
            throw new ApplicationException(GxsResultEnum.SUPPLIER_ID_NOT_EXITS);
        }
        requestDTO.getItem().forEach(item -> {
            if (item.getId() == null || item.getId() == 0) {
                throw new ApplicationException(GxsResultEnum.ITEM_ID_NOT_EXITS);
            }

        });

    }
}
