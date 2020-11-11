package com.deepexi.dd.middle.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.*;
import com.deepexi.dd.middle.order.domain.*;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoResQuery;
import com.deepexi.dd.middle.order.enums.PaymentStatusEnum;
import com.deepexi.dd.middle.order.service.OrderOperationRecordService;
import com.deepexi.dd.middle.order.service.SaleOrderInfoService;
import com.deepexi.dd.middle.order.service.SaleOutTaskService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.dd.middle.order.util.Utils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

/**
 * SaleOrderInfoServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
@Service
@Slf4j
public class SaleOrderInfoServiceImpl implements SaleOrderInfoService {
    @Autowired
    private OrderCouponInfoDAO orderCouponInfoDAO;

    @Autowired
    private OrderConsigneeInfoDAO orderConsigneeInfoDAO;
    @Autowired
    private OrderDeliverySelfRaisingInfoDAO orderDeliverySelfRaisingInfoDAO;
    @Autowired
    private OrderExpenseInfoDAO orderExpenseInfoDAO;

    @Autowired
    private OrderInvoiceInfoDAO orderInvoiceInfoDAO;

    @Autowired
    private OrderDeliveryInfoDAO orderDeliveryInfoDAO;

    @Autowired
    private OrderPromotionInfoDAO orderPromotionInfoDAO;

    @Autowired
    private SaleOrderItemDAO saleOrderItemDAO;

    @Autowired
    private SaleOrderInfoDAO saleOrderInfoDAO;

    @Autowired
    private SaleOutTaskDAO saleOutTaskDAO;

    @Autowired
    private SaleOutTaskDetailInfoDAO saleOutTaskDetailInfoDAO;

    @Autowired
    private SaleOutTaskService saleOutTaskService;

    @Autowired
    private OrderOperationRecordService orderOperationRecordService;

    @Override
    public List<SaleOrderInfoResponseDTO> findList(SaleOrderInfoResQuery query) {
        return saleOrderInfoDAO.findList(query);
    }

    @Override
    public List<SaleOrderInfoDTO> listSaleOrderInfos(SaleOrderInfoQuery query) {
        List<SaleOrderInfoDTO> result = ObjectCloneUtils.convertList(saleOrderInfoDAO.listSaleOrderInfos(query), SaleOrderInfoDTO.class);
        List<Long> ids =
                result.stream().map(SaleOrderInfoDTO::getId).collect(Collectors.toList());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.in("sale_order_id", ids);
        List<SaleOrderItemDO> saleOrderItemDOS = saleOrderItemDAO.list(queryWrapper);
        Map<Long, List<SaleOrderItemMiddleResponseDTO>> saleOrderItemDTOSMap = null;
        if (CollectionUtil.isNotEmpty(saleOrderItemDOS)) {
            saleOrderItemDTOSMap =
                    saleOrderItemDOS.stream().map(e -> e.clone(SaleOrderItemMiddleResponseDTO.class)).collect(Collectors.groupingBy(SaleOrderItemMiddleResponseDTO::getSaleOrderId));
        }
        for (SaleOrderInfoDTO saleOrderInfoDTO : result) {
            Long saleOrderId = saleOrderInfoDTO.getId();
            if (CollectionUtil.isNotEmpty(saleOrderItemDTOSMap) && saleOrderItemDTOSMap.containsKey(saleOrderId)) {
                saleOrderInfoDTO.setItems(saleOrderItemDTOSMap.get(saleOrderId));
            }
        }
        return result;
    }

    @Override
    public List<SaleOrderInfoDTO> listSaleOrderInfosByIds(List<Long> ids) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("id", ids);
        wrapper.eq("is_deleted", 0);
        return ObjectCloneUtils.convertList(saleOrderInfoDAO.list(wrapper), SaleOrderInfoDTO.class);
    }

    /**
     * 根据销售订单ID查找明细
     *
     * @param saleOrderId
     * @return
     */
    private List<SaleOrderItemMiddleResponseDTO> packageSalesItemDTO(Long saleOrderId) {
        QueryWrapper<SaleOrderItemDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SaleOrderItemDO::getSaleOrderId, saleOrderId);
        List<SaleOrderItemDO> salesOrderItemDOS = saleOrderItemDAO.list(queryWrapper);
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemResponseDTOS =
                ObjectCloneUtils.convertList(salesOrderItemDOS, SaleOrderItemMiddleResponseDTO.class);
        return saleOrderItemResponseDTOS;
    }

    private OrderConsigneeInfoResponseDTO packageOrderConsigneeInfoResponseDTO(Long saleOrderId) {
        QueryWrapper<OrderConsigneeInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(OrderConsigneeInfoDO::getSaleOrderId, saleOrderId);
        List<OrderConsigneeInfoDO> orderConsigneeInfoDOS = orderConsigneeInfoDAO.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(orderConsigneeInfoDOS)) {
            return orderConsigneeInfoDOS.get(0).clone(OrderConsigneeInfoResponseDTO.class);
        }
        return null;
    }

    private OrderConsigneeInfoResponseDTO packageOrderDeliverySelfRaisingInfo(Long saleOrderId) {
        QueryWrapper<OrderDeliverySelfRaisingInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(OrderDeliverySelfRaisingInfoDO::getSaleOrderId, saleOrderId);
        List<OrderDeliverySelfRaisingInfoDO> orderConsigneeInfoDOS = orderDeliverySelfRaisingInfoDAO.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(orderConsigneeInfoDOS)) {
            return orderConsigneeInfoDOS.get(0).clone(OrderConsigneeInfoResponseDTO.class);
        }
        return null;
    }

    private List<OrderCouponInfoResponseDTO> packageOrderCouponInfoResponseDTO(Long saleOrderId) {
        QueryWrapper<OrderCouponInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(OrderCouponInfoDO::getSaleOrderId, saleOrderId);
        List<OrderCouponInfoDO> orderCouponInfoDOS = orderCouponInfoDAO.list(queryWrapper);
        List<OrderCouponInfoResponseDTO> orderCouponInfoResponseDTOS =
                ObjectCloneUtils.convertList(orderCouponInfoDOS, OrderCouponInfoResponseDTO.class);
        return orderCouponInfoResponseDTOS;
    }

    private List<OrderPromotionInfoResponseDTO> packageOrderPromotionInfoResponseDTO(Long saleOrderId) {
        QueryWrapper<OrderPromotionInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(OrderPromotionInfoDO::getSaleOrderId, saleOrderId);
        List<OrderPromotionInfoDO> orderPromotionInfoDOS = orderPromotionInfoDAO.list(queryWrapper);
        List<OrderPromotionInfoResponseDTO> orderPromotionInfoResponseDTOS =
                ObjectCloneUtils.convertList(orderPromotionInfoDOS, OrderPromotionInfoResponseDTO.class);
        return orderPromotionInfoResponseDTOS;
    }

    private OrderInvoiceInfoResponseDTO packageOrderInvoiceInfoResponseDTO(Long saleOrderId) {
        QueryWrapper<OrderInvoiceInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(OrderInvoiceInfoDO::getSaleOrderId, saleOrderId);
        List<OrderInvoiceInfoDO> orderInvoiceInfoDOS = orderInvoiceInfoDAO.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(orderInvoiceInfoDOS)) {
            return orderInvoiceInfoDOS.get(0).clone(OrderInvoiceInfoResponseDTO.class);
        }
        return null;
    }

    private List<OrderExpenseInfoResponseDTO> packageOrderExpenseInfoResponseDTO(Long saleOrderId) {
        QueryWrapper<OrderExpenseInfoDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(OrderExpenseInfoDO::getSaleOrderId, saleOrderId);
        List<OrderExpenseInfoDO> orderExpenseInfoDOS = orderExpenseInfoDAO.list(queryWrapper);
        List<OrderExpenseInfoResponseDTO> orderExpenseInfoResponseDTOS =
                ObjectCloneUtils.convertList(orderExpenseInfoDOS, OrderExpenseInfoResponseDTO.class);
        return orderExpenseInfoResponseDTOS;
    }

    private void packageSaleOutTaskAndOrderDeliveryInSaleOrder(SaleOrderInfoResponseDTO salesOrderInfoResponse) {
        Long saleOrderId = salesOrderInfoResponse.getId();
        QueryWrapper<SaleOutTaskDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SaleOutTaskDO::getSaleOrderId, saleOrderId);
        List<SaleOutTaskDO> saleOutTaskDOS = saleOutTaskDAO.list(queryWrapper);
        if (CollectionUtil.isEmpty(saleOutTaskDOS)) {
            return;
        }
        List<SaleOutTaskMiddleResponseDTO> saleOutTaskMiddleResponseDTOS =
                ObjectCloneUtils.convertList(saleOutTaskDOS, SaleOutTaskMiddleResponseDTO.class);
        salesOrderInfoResponse.setSaleOutTaskList(saleOutTaskMiddleResponseDTOS);
        List<Long> ids = saleOutTaskDOS.stream().map(SaleOutTaskDO::getId).collect(Collectors.toList());
        QueryWrapper<OrderDeliveryInfoDO> queryWrappera = new QueryWrapper();
        queryWrappera.lambda().in(OrderDeliveryInfoDO::getSaleOutTaskId, ids);
        List<OrderDeliveryInfoDO> orderDeliveryInfoDOS = orderDeliveryInfoDAO.list(queryWrappera);
        if (CollectionUtil.isNotEmpty(orderDeliveryInfoDOS)) {
            List<OrderDeliveryInfoResponseDTO> orderDeliveryInfoResponseDTOS =
                    ObjectCloneUtils.convertList(orderDeliveryInfoDOS, OrderDeliveryInfoResponseDTO.class);
            salesOrderInfoResponse.setOrderDeliveryInfoList(orderDeliveryInfoResponseDTOS);
        }
        QueryWrapper<SaleOutTaskDetailInfoDO> queryWrapperb = new QueryWrapper();
        queryWrapperb.lambda().in(SaleOutTaskDetailInfoDO::getSaleOutTaskId, ids);
        List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOS = saleOutTaskDetailInfoDAO.list(queryWrapperb);
        if (CollectionUtil.isEmpty(saleOutTaskDetailInfoDOS)) {
            return;
        }
        Map<Long, List<SaleOutTaskDetailInfoDO>> saleOutTaskDetailMap =
                saleOutTaskDetailInfoDOS.stream().collect(Collectors.groupingBy(SaleOutTaskDetailInfoDO::getSaleOutTaskId));
        List<SaleOrderItemMiddleResponseDTO> item = salesOrderInfoResponse.getItems();
        for (SaleOutTaskMiddleResponseDTO saleOutTaskMiddleResponseDTO : saleOutTaskMiddleResponseDTOS) {
            List<SaleOrderItemMiddleResponseDTO> details = ObjectCloneUtils.convertList(item,
                                                                                        SaleOrderItemMiddleResponseDTO.class);
            saleOutTaskMiddleResponseDTO.setSaleOrderItemList(details);
            Long saleOutTaskId = saleOutTaskMiddleResponseDTO.getId();
            if (saleOutTaskDetailMap.containsKey(saleOutTaskId)) {
                List<SaleOutTaskDetailInfoDO> saleOutTaskDetailInfoDOS1 = saleOutTaskDetailMap.get(saleOutTaskId);
                Map<Long, SaleOutTaskDetailInfoDO> detailInfoDOMap =
                        saleOutTaskDetailInfoDOS1.stream().collect(Collectors.toMap(SaleOutTaskDetailInfoDO::getSaleOrderItemId, e -> e));
                for (SaleOrderItemMiddleResponseDTO saleOrderItemResponseDTO : details) {
                    if (detailInfoDOMap.containsKey(saleOrderItemResponseDTO.getId())) {
                        SaleOutTaskDetailInfoDO saleOutTaskDetailInfoDO =
                                detailInfoDOMap.get(saleOrderItemResponseDTO.getId());
                        saleOrderItemResponseDTO.setSkuShipmentQuantity(saleOutTaskDetailInfoDO.getSkuShipmentQuantity());
                    }
                }
            }
        }
    }

    private List<OrderDeliveryInfoResponseDTO> packageOrderDeliveryInfoResponseDTO(Long saleOrderId) {
        QueryWrapper<SaleOutTaskDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SaleOutTaskDO::getSaleOrderId, saleOrderId).eq(SaleOutTaskDO::getDeleted, 0);
        List<SaleOutTaskDO> saleOutTaskDOS = saleOutTaskDAO.list(queryWrapper);
        if (CollectionUtil.isEmpty(saleOutTaskDOS)) {
            return new ArrayList<>(0);
        }
        List<Long> ids = saleOutTaskDOS.stream().map(SaleOutTaskDO::getId).collect(Collectors.toList());
        QueryWrapper<OrderDeliveryInfoDO> queryWrappera = new QueryWrapper();
        queryWrappera.lambda().in(OrderDeliveryInfoDO::getSaleOutTaskId, ids).eq(OrderDeliveryInfoDO::getDeleted, 0);
        List<OrderDeliveryInfoDO> orderDeliveryInfoDOS = orderDeliveryInfoDAO.list(queryWrappera);
        List<OrderDeliveryInfoResponseDTO> orderDeliveryInfoResponseDTOS =
                ObjectCloneUtils.convertList(orderDeliveryInfoDOS, OrderDeliveryInfoResponseDTO.class);
        return orderDeliveryInfoResponseDTOS;
    }

    private Callable<Map<Long, List<SaleOrderItemMiddleResponseDTO>>> findOrderItem(List<Long> ids) {
        return () -> {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_deleted", 0);
            queryWrapper.in("sale_order_id", ids);
            List<SaleOrderItemDO> saleOrderItemDOS = saleOrderItemDAO.list(queryWrapper);
            if (CollectionUtil.isNotEmpty(saleOrderItemDOS)) {
                return saleOrderItemDOS.stream().map(e -> e.clone(SaleOrderItemMiddleResponseDTO.class)).collect(Collectors.groupingBy(SaleOrderItemMiddleResponseDTO::getSaleOrderId));
            }
            return Maps.newHashMap();
        };
    }

    private Callable<Map<Long, OrderConsigneeInfoResponseDTO>> findOrderConsigneeInfo(List<Long> ids) {
        return () -> {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_deleted", 0);
            queryWrapper.in("sale_order_id", ids);
            List<OrderConsigneeInfoDO> orderConsigneeInfoDOS = orderConsigneeInfoDAO.list(queryWrapper);
            if (CollectionUtil.isNotEmpty(orderConsigneeInfoDOS)) {
                return orderConsigneeInfoDOS.stream().map(e -> e.clone(OrderConsigneeInfoResponseDTO.class)).collect(Collectors.toMap(OrderConsigneeInfoResponseDTO::getSaleOrderId, e -> e, (a, b) -> {
                    if (a.getCreatedTime().compareTo(b.getCreatedTime()) > 0) {
                        return a;
                    }
                    return b;
                }));
            }
            return Maps.newHashMap();
        };
    }

    private Callable<Map<Long, List<OrderCouponInfoResponseDTO>>> findOrderCouponInfo(List<Long> ids) {
        return () -> {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_deleted", 0);
            queryWrapper.in("sale_order_id", ids);
            List<OrderCouponInfoDO> orderCouponInfoDOS = orderCouponInfoDAO.list(queryWrapper);
            Map<Long, List<OrderCouponInfoResponseDTO>> orderCouponInfoResponseDTOSMaps = null;
            if (CollectionUtil.isNotEmpty(orderCouponInfoDOS)) {
                return orderCouponInfoDOS.stream().map(e -> e.clone(OrderCouponInfoResponseDTO.class)).collect(Collectors.groupingBy(OrderCouponInfoResponseDTO::getSaleOrderId));
            }
            return Maps.newHashMap();
        };
    }

    private Callable<Map<Long, List<OrderPromotionInfoResponseDTO>>> findOrderPromotionInfo(List<Long> ids) {
        return () -> {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_deleted", 0);
            queryWrapper.in("sale_order_id", ids);
            List<OrderPromotionInfoDO> orderPromotionInfoDOS = orderPromotionInfoDAO.list(queryWrapper);
            Map<Long, List<OrderPromotionInfoResponseDTO>> orderPromotionInfoResponseDTOSMaps = null;
            if (CollectionUtil.isNotEmpty(orderPromotionInfoDOS)) {
                return orderPromotionInfoDOS.stream().map(e -> e.clone(OrderPromotionInfoResponseDTO.class)).collect(Collectors.groupingBy(OrderPromotionInfoResponseDTO::getSaleOrderId));
            }
            return Maps.newHashMap();
        };
    }

    private Callable<Map<Long, OrderInvoiceInfoResponseDTO>> findOrderInvoiceInfo(List<Long> ids) {
        return () -> {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_deleted", 0);
            queryWrapper.in("sale_order_id", ids);
            List<OrderInvoiceInfoDO> orderInvoiceInfoDOS = orderInvoiceInfoDAO.list(queryWrapper);
            if (CollectionUtil.isNotEmpty(orderInvoiceInfoDOS)) {
                return orderInvoiceInfoDOS.stream().map(e -> e.clone(OrderInvoiceInfoResponseDTO.class)).collect(Collectors.toMap(OrderInvoiceInfoResponseDTO::getSaleOrderId, e -> e, (a, b) -> {
                    if (a.getCreatedTime().compareTo(b.getCreatedTime()) > 0) {
                        return a;
                    }
                    return b;
                }));
            }
            return Maps.newHashMap();
        };
    }

    private Callable<Map<Long, List<OrderExpenseInfoResponseDTO>>> findOrderExpenseInfo(List<Long> ids) {
        return () -> {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_deleted", 0);
            queryWrapper.in("sale_order_id", ids);
            List<OrderExpenseInfoDO> orderExpenseInfoDOS = orderExpenseInfoDAO.list(queryWrapper);
            if (CollectionUtil.isNotEmpty(orderExpenseInfoDOS)) {
                return orderExpenseInfoDOS.stream().map(e -> e.clone(OrderExpenseInfoResponseDTO.class)).collect(Collectors.groupingBy(OrderExpenseInfoResponseDTO::getSaleOrderId));
            }
            return Maps.newHashMap();
        };
    }

    private void packageSaleOrderInfoPageObj(List<SaleOrderInfoResponseDTO> saleOrderInfoResponseVOS) {

        List<Long> ids = saleOrderInfoResponseVOS.stream().map(SaleOrderInfoResponseDTO::getId).collect(Collectors.toList());

        ForkJoinPool joinPool = ForkJoinPool.commonPool();
        ForkJoinTask<Map<Long, List<SaleOrderItemMiddleResponseDTO>>> itemTask = joinPool.submit(findOrderItem(ids));
        ForkJoinTask<Map<Long, OrderConsigneeInfoResponseDTO>> consigneeTask = joinPool.submit(findOrderConsigneeInfo(ids));
        ForkJoinTask<Map<Long, List<OrderCouponInfoResponseDTO>>> couponTask = joinPool.submit(findOrderCouponInfo(ids));
        ForkJoinTask<Map<Long, List<OrderPromotionInfoResponseDTO>>> promotionTask = joinPool.submit(findOrderPromotionInfo(ids));
        ForkJoinTask<Map<Long, OrderInvoiceInfoResponseDTO>> invoiceTask = joinPool.submit(findOrderInvoiceInfo(ids));
        ForkJoinTask<Map<Long, List<OrderExpenseInfoResponseDTO>>> expenseTask = joinPool.submit(findOrderExpenseInfo(ids));

        Map<Long, List<SaleOrderItemMiddleResponseDTO>> saleOrderItemDTOSMap = itemTask.join();
        Map<Long, OrderConsigneeInfoResponseDTO> orderConsigneeInfoResponseVOSMaps = consigneeTask.join();
        Map<Long, List<OrderCouponInfoResponseDTO>> orderCouponInfoResponseDTOSMaps = couponTask.join();
        Map<Long, List<OrderPromotionInfoResponseDTO>> orderPromotionInfoResponseDTOSMaps = promotionTask.join();
        Map<Long, OrderInvoiceInfoResponseDTO> orderInvoiceInfoResponseDTOSMaps = invoiceTask.join();
        Map<Long, List<OrderExpenseInfoResponseDTO>> orderExpenseInfoResponseDTOSMpas = expenseTask.join();

//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("is_deleted", 0);
//        queryWrapper.in("sale_order_id", ids);
//        List<SaleOutTaskDO> saleOutTaskDOS = saleOutTaskDAO.list(queryWrapper);
//        Map<Long, OrderDeliveryInfoResponseDTO> orderDeliveryMaps = null;
//        Map<Long, OrderDeliveryInfoResponseDTO> orderDeliveryInfoResponseVOSMaps = new HashMap<Long,
//                OrderDeliveryInfoResponseDTO>();
//
//        if (CollectionUtil.isNotEmpty(saleOutTaskDOS)) {
//            //查deliveryInfo
//            List<Long> orderDeliveryIds =
//                    saleOutTaskDOS.stream().map(SaleOutTaskDO::getOrderDeliveryId).collect(Collectors.toList());
//            QueryWrapper<OrderDeliveryInfoDO> queryWrapperDelivery = new QueryWrapper();
//            queryWrapperDelivery.lambda().eq(OrderDeliveryInfoDO::getId, orderDeliveryIds).eq(OrderDeliveryInfoDO::getDeleted, 0);
//            List<OrderDeliveryInfoDO> orderDeliveryInfoDOS = orderDeliveryInfoDAO.list(queryWrapperDelivery);
//            if (CollectionUtil.isNotEmpty(orderDeliveryInfoDOS)) {
//                orderDeliveryMaps =
//                        orderDeliveryInfoDOS.stream().map(e -> e.clone(OrderDeliveryInfoResponseDTO.class)).collect(Collectors.toMap(OrderDeliveryInfoResponseDTO::getSaleOutTaskId, e -> e));
//                for (SaleOutTaskDO saleOutTaskDO : saleOutTaskDOS) {
//                    orderDeliveryInfoResponseVOSMaps.put(saleOutTaskDO.getSaleOrderId(),
//                                                         orderDeliveryMaps.get(saleOutTaskDO.getId()));
//                }
//            }
//        }

        for (SaleOrderInfoResponseDTO saleOrderInfoResponseVO : saleOrderInfoResponseVOS) {
            Long saleOrderId = saleOrderInfoResponseVO.getId();
            if (CollectionUtil.isNotEmpty(saleOrderItemDTOSMap) && saleOrderItemDTOSMap.containsKey(saleOrderId)) {
                saleOrderInfoResponseVO.setItems(saleOrderItemDTOSMap.get(saleOrderId));
            }
            if (CollectionUtil.isNotEmpty(orderConsigneeInfoResponseVOSMaps) && orderConsigneeInfoResponseVOSMaps.containsKey(saleOrderId)) {
                saleOrderInfoResponseVO.setOrderConsigneeInfo(orderConsigneeInfoResponseVOSMaps.get(saleOrderId));
            }
            if (CollectionUtil.isNotEmpty(orderCouponInfoResponseDTOSMaps) && orderCouponInfoResponseDTOSMaps.containsKey(saleOrderId)) {
                saleOrderInfoResponseVO.setOrderCouponList(orderCouponInfoResponseDTOSMaps.get(saleOrderId));
            }
            if (CollectionUtil.isNotEmpty(orderInvoiceInfoResponseDTOSMaps) && orderInvoiceInfoResponseDTOSMaps.containsKey(saleOrderId)) {
                saleOrderInfoResponseVO.setOrderInvoiceInfo(orderInvoiceInfoResponseDTOSMaps.get(saleOrderId));
            }
            if (CollectionUtil.isNotEmpty(orderPromotionInfoResponseDTOSMaps) && orderPromotionInfoResponseDTOSMaps.containsKey(saleOrderId)) {
                saleOrderInfoResponseVO.setOrderPromotionList(orderPromotionInfoResponseDTOSMaps.get(saleOrderId));
            }
            if (CollectionUtil.isNotEmpty(orderExpenseInfoResponseDTOSMpas) && orderExpenseInfoResponseDTOSMpas.containsKey(saleOrderId)) {
                saleOrderInfoResponseVO.setOrderExpenseInfo(orderExpenseInfoResponseDTOSMpas.get(saleOrderId));
            }
        }
    }

    @Override
    public PageBean<SaleOrderInfoResponseDTO> listSaleOrderInfosPage(SaleOrderInfoQuery query) {
        if (query.getPaymentStatusList() != null) {   //按单收款
            PageHelper.startPage(query.getPage(), query.getSize(), "payment_status asc, ticket_date desc");
        } else {   //销售订单
            PageHelper.startPage(query.getPage(), query.getSize(), "ticket_date desc");
        }
        if (null != query.getAddressArea()) {
            String[] split = query.getAddressArea().split("/");
            query.setProvinceCode(split[0]);
            query.setCityCode(split[1]);
            query.setAreaCode(split[2]);
        }
        List<SaleOrderInfoDO> saleOrderInfoDOS = saleOrderInfoDAO.listSaleOrderInfos(query);
        List<SaleOrderInfoResponseDTO> saleOrderInfoResponseVOS = ObjectCloneUtils.convertList(saleOrderInfoDOS,
                                                                                               SaleOrderInfoResponseDTO.class);
        if (CollectionUtil.isNotEmpty(saleOrderInfoResponseVOS)) {
            packageSaleOrderInfoPageObj(saleOrderInfoResponseVOS);
        }
        return new PageBean<>(saleOrderInfoResponseVOS);
    }

    @Override
    public PageBean<SaleOrderInfoOLResponseDTO> listSaleOrdersPage(SaleOrderInfoQuery query) {
        PageHelper.startPage(query.getPage(), query.getSize(), "ticket_date desc");
        //查询近三个月的销售订单明细
        if (null != query.getYear() && query.getYear().equals("3")) {
            query.setYear(null);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -2);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            query.setMonthBand(simpleDateFormat.format(calendar.getTime()));
        }
        //获取6年前的年份
        if (null != query.getYear() && query.getYear().equals("8")) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR, -7);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            query.setYearBand(simpleDateFormat.format(calendar.getTime()));
        }
        List<SaleOrderInfoDO> saleOrderInfoDOS = saleOrderInfoDAO.listSaleOrderInfos(query);
        List<SaleOrderInfoOLResponseDTO> saleOrderInfoOLResponseDTO = ObjectCloneUtils.convertList(saleOrderInfoDOS,
                                                                                                   SaleOrderInfoOLResponseDTO.class);
        if (CollectionUtil.isNotEmpty(saleOrderInfoOLResponseDTO)) {
            //获取销售订单明细
            List<Long> ids = saleOrderInfoOLResponseDTO.stream().map(SaleOrderInfoOLResponseDTO::getId).collect(Collectors.toList());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_deleted", 0);
            queryWrapper.in("sale_order_id", ids);
            if(query.getAvailablePickNumNotZero() != null && query.getAvailablePickNumNotZero() == true){
                queryWrapper.notIn("available_pick_num",0);
            }
            List<SaleOrderItemDO> saleOrderItemDOS = saleOrderItemDAO.list(queryWrapper);

            //获取出库单
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("is_deleted", 0);
            queryWrapper2.in("sale_order_id", ids);
            queryWrapper2.eq("sign_status", 17);
            List<SaleOutTaskDO> saleOutTaskDOS = saleOutTaskDAO.list(queryWrapper2);
            Map<Long, List<SaleOrderItemMiddleResponseDTO>> saleOrderItemDTOSMap = null;
            if (CollectionUtil.isNotEmpty(saleOrderItemDOS)) {
                saleOrderItemDTOSMap =
                        saleOrderItemDOS.stream().map(e -> e.clone(SaleOrderItemMiddleResponseDTO.class)).collect(Collectors.groupingBy(SaleOrderItemMiddleResponseDTO::getSaleOrderId));
            }

            Map<Long, List<SaleOutTaskMiddleResponseDTO>> saleOutTaskDTOSMap = null;
            if (CollectionUtil.isNotEmpty(saleOutTaskDOS)) {
                saleOutTaskDTOSMap =
                        saleOutTaskDOS.stream().map(e -> e.clone(SaleOutTaskMiddleResponseDTO.class)).collect(Collectors.groupingBy(SaleOutTaskMiddleResponseDTO::getSaleOrderId));
            }
            for (SaleOrderInfoOLResponseDTO saleOrderInfoOLResponseVO : saleOrderInfoOLResponseDTO) {
                Long saleOrderId = saleOrderInfoOLResponseVO.getId();
                if (CollectionUtil.isNotEmpty(saleOrderItemDTOSMap) && saleOrderItemDTOSMap.containsKey(saleOrderId)) {
                    if (saleOrderItemDTOSMap != null) {
                        saleOrderInfoOLResponseVO.setItems(saleOrderItemDTOSMap.get(saleOrderId));
                    }
                }
                if (CollectionUtil.isNotEmpty(saleOutTaskDTOSMap) && saleOutTaskDTOSMap.containsKey(saleOrderId)) {
                    if (saleOutTaskDTOSMap != null) {
                        saleOrderInfoOLResponseVO.setSaleOutTaskList(saleOutTaskDTOSMap.get(saleOrderId));
                    }
                }
            }
        }
        return new PageBean<>(saleOrderInfoOLResponseDTO);
    }




    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除销售订单表,传入ID为空");
            return false;
        }
        return saleOrderInfoDAO.deleteByIdIn(id) == id.size();
    }

    /**
     * 创建销售订单
     *
     * @param saleOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public SaleOrderInfoDTO createOrder(SaleOrderInfoAddRequestDTO saleOrder) {
        SaleOrderInfoDO saleOrderInfoDO = getSaleOrderInfoDO(saleOrder);
        //保存订单信息
        saleOrderInfoDAO.insert(saleOrderInfoDO);
        //保存优惠券信息
        if (CollectionUtil.isNotEmpty(saleOrder.getOrderCouponList())) {
            List<OrderCouponInfoDO> orderCouponInfoDOList = getOrderCouponInfoDO(saleOrder.getOrderCouponList(), true
                    , saleOrderInfoDO.getId(), saleOrderInfoDO.getCode());
            orderCouponInfoDAO.batchInsert(orderCouponInfoDOList);
        }
        //保存送货地址信息
        if (saleOrder.getOrderConsigneeInfo() != null) {
            OrderConsigneeInfoDO orderConsigneeInfoDO =
                    saleOrder.getOrderConsigneeInfo().clone(OrderConsigneeInfoDO.class);
            DomainUtils.initDomainCreatedInfo(orderConsigneeInfoDO);
            orderConsigneeInfoDO.setSaleOrderId(saleOrderInfoDO.getId());
            orderConsigneeInfoDO.setSaleOrderCode(saleOrderInfoDO.getCode());
            orderConsigneeInfoDO.setAppId(saleOrderInfoDO.getAppId());
            orderConsigneeInfoDO.setTenantId(saleOrderInfoDO.getTenantId());
            log.info("创建订单自提地址信息:{ }", orderConsigneeInfoDO);
            orderConsigneeInfoDAO.insert(orderConsigneeInfoDO);
        }
        //保存自提地址信息
        if (saleOrder.getOrderSelfRaisingInfo() != null) {
            OrderDeliverySelfRaisingInfoDO selfRaisingInfoDO = saleOrder.getOrderSelfRaisingInfo().clone(OrderDeliverySelfRaisingInfoDO.class);
            DomainUtils.initDomainCreatedInfo(selfRaisingInfoDO);
            selfRaisingInfoDO.setSaleOrderId(saleOrderInfoDO.getId());
            selfRaisingInfoDO.setSaleOrderCode(saleOrderInfoDO.getCode());
            log.info("创建订单自提地址信息:{ }", selfRaisingInfoDO);
            orderDeliverySelfRaisingInfoDAO.insert(selfRaisingInfoDO);
        }
        //保存订单明细
        List<SaleOrderItemDO> saleOrderItemDOs = getSaleOrderItemList(saleOrder.getItems(), true,
                                                                      saleOrderInfoDO.getId(), saleOrderInfoDO.getCode());
        saleOrderItemDAO.batchInsert(saleOrderItemDOs);
        //保存费用信息
        if (CollectionUtil.isNotEmpty(saleOrder.getOrderExpenseInfo())) {
            List<OrderExpenseInfoDO> orderExpenseInfoList = getOrderExpenseInfoList(saleOrder.getOrderExpenseInfo(),
                                                                                    true, saleOrderInfoDO.getId());
            orderExpenseInfoDAO.batchInsert(orderExpenseInfoList);
        }
        //保存促销信息
        if (CollectionUtil.isNotEmpty(saleOrder.getOrderPromotionList())) {
            List<OrderPromotionInfoDO> orderPromotionInfoList =
                    getOrderPromotionInfoList(saleOrder.getOrderPromotionList(), true, saleOrderInfoDO.getId(),
                                              saleOrderInfoDO.getCode());
            orderPromotionInfoDAO.batchInsert(orderPromotionInfoList);
        }
        //保存开票信息
        if (saleOrder.getOrderInvoiceInfo() != null && !StringUtil.isEmpty(saleOrder.getOrderInvoiceInfo().getTaxNo())) {
            OrderInvoiceInfoDO orderInvoiceInfoDO = saleOrder.getOrderInvoiceInfo().clone(OrderInvoiceInfoDO.class);
            DomainUtils.initDomainCreatedInfo(orderInvoiceInfoDO);
            orderInvoiceInfoDO.setSaleOrderCode(saleOrderInfoDO.getCode());
            orderInvoiceInfoDO.setSaleOrderId(saleOrderInfoDO.getId());
            orderInvoiceInfoDAO.insert(orderInvoiceInfoDO);
        }
        return saleOrderInfoDO.clone(SaleOrderInfoDTO.class);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<SaleOrderInfoDTO> createOrders(List<SaleOrderInfoAddRequestDTO> saleOrder) {
        if (CollectionUtil.isNotEmpty(saleOrder)) {
            for (SaleOrderInfoAddRequestDTO dto : saleOrder) {
                ((SaleOrderInfoService) (AopContext.currentProxy())).createOrder(dto);
            }
        }
        return null;
    }

    /**
     * 获取优惠券信息
     *
     * @param orderCouponInfoList
     * @return
     */
    private List<OrderCouponInfoDO> getOrderCouponInfoDO(List<OrderCouponInfoRequestDTO> orderCouponInfoList,
                                                         boolean isAdd, Long saleOrderId, String saleOrderNo) {
        List<OrderCouponInfoDO> orderCouponInfoDOList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(orderCouponInfoList)) {
            orderCouponInfoList.forEach(item -> {
                OrderCouponInfoDO infoDO = item.clone(OrderCouponInfoDO.class);
                infoDO.setSaleOrderCode(saleOrderNo);
                infoDO.setSaleOrderId(saleOrderId);
                if (isAdd) {
                    DomainUtils.initDomainCreatedInfo(infoDO);
                }
                orderCouponInfoDOList.add(infoDO);
            });
        }
        return orderCouponInfoDOList;
    }

    /**
     * 获取商品列表DO
     *
     * @param saleOrderItems
     * @return
     */
    private List<SaleOrderItemDO> getSaleOrderItemList(List<SaleOrderItemMiddleRequestDTO> saleOrderItems,
                                                       boolean isAdd, Long saleOrderId, String saleOrderCode) {
        List<SaleOrderItemDO> saleOrderItemDOs = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(saleOrderItems)) {
            saleOrderItems.forEach(item -> {
                SaleOrderItemDO itemDO = item.clone(SaleOrderItemDO.class);
                itemDO.setSaleOrderCode(saleOrderCode);
                itemDO.setSaleOrderId(saleOrderId);
                itemDO.setAvailablePickNum(itemDO.getSkuQuantity());
                if (isAdd) {
                    DomainUtils.initDomainCreatedInfo(itemDO);
                }
                saleOrderItemDOs.add(itemDO);
            });
        }
        return saleOrderItemDOs;
    }

    /**
     * 获取费用列表DO
     *
     * @param orderExpenseInfoList
     * @return
     */
    private List<OrderExpenseInfoDO> getOrderExpenseInfoList(List<OrderExpenseInfoRequestDTO> orderExpenseInfoList,
                                                             boolean isAdd, Long saleOrderId) {
        List<OrderExpenseInfoDO> orderExpenseInfos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(orderExpenseInfoList)) {
            orderExpenseInfoList.forEach(item -> {
                OrderExpenseInfoDO infoDO = item.clone(OrderExpenseInfoDO.class);
                infoDO.setSaleOrderId(saleOrderId);
                if (isAdd) {
                    DomainUtils.initDomainCreatedInfo(infoDO);
                }
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
    private List<OrderPromotionInfoDO> getOrderPromotionInfoList(List<OrderPromotionInfoRequestDTO> orderPromotionInfoList, boolean isAdd, Long saleOrderId, String saleOrderCode) {
        List<OrderPromotionInfoDO> orderPromotionInfoDOs = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(orderPromotionInfoList)) {
            orderPromotionInfoList.forEach(item -> {
                OrderPromotionInfoDO infoDO = item.clone(OrderPromotionInfoDO.class);
                infoDO.setSaleOrderCode(saleOrderCode);
                infoDO.setSaleOrderId(saleOrderId);
                if (isAdd) {
                    DomainUtils.initDomainCreatedInfo(infoDO);
                }
                orderPromotionInfoDOs.add(infoDO);
            });
        }
        return orderPromotionInfoDOs;
    }

    private SaleOrderInfoDO getSaleOrderInfoDO(SaleOrderInfoAddRequestDTO saleOrder) {
        SaleOrderInfoDO saleOrderInfoDo = new SaleOrderInfoDO();
        saleOrderInfoDo.setAccrueAmount(saleOrder.getAccrueAmount());
        saleOrderInfoDo.setArriveDate(saleOrder.getArriveDate());
        saleOrderInfoDo.setBuyerId(saleOrder.getBuyerId());
        saleOrderInfoDo.setBuyerName(saleOrder.getBuyerName());
        saleOrderInfoDo.setBuyerType(saleOrder.getBuyerType());
        saleOrderInfoDo.setCreatedBy(saleOrder.getCreatedBy());
        saleOrderInfoDo.setUpdatedBy(saleOrder.getUpdatedBy());
        saleOrderInfoDo.setDiscountAmount(saleOrder.getDiscountAmount());
        saleOrderInfoDo.setFromStorehouse(saleOrder.getFromStorehouse());
        saleOrderInfoDo.setHandler(saleOrder.getHandler());
        saleOrderInfoDo.setHandlerName(saleOrder.getHandlerName());
        saleOrderInfoDo.setPaymentStatus(PaymentStatusEnum.UNPAY.getValue());
        saleOrderInfoDo.setPaymentType(saleOrder.getPaymentType());
        saleOrderInfoDo.setSellerId(saleOrder.getSellerId());
        saleOrderInfoDo.setSellerName(saleOrder.getSellerName());
        saleOrderInfoDo.setSettlementStatus(saleOrder.getSettlementStatus());
        saleOrderInfoDo.setStatus(saleOrder.getStatus());
        saleOrderInfoDo.setTicketDate(saleOrder.getTicketDate());
        saleOrderInfoDo.setTicketType(saleOrder.getTicketType());
        saleOrderInfoDo.setShipmentStatus(saleOrder.getShipmentStatus());
        saleOrderInfoDo.setShippingType(saleOrder.getShippingType());
        saleOrderInfoDo.setTotalExpense(saleOrder.getTotalAmount());
        saleOrderInfoDo.setTotalAmount(saleOrder.getTotalAmount());
        saleOrderInfoDo.setCode(saleOrder.getCode());
        saleOrderInfoDo.setTenantId(saleOrder.getTenantId());
        saleOrderInfoDo.setAppId(saleOrder.getAppId());
        saleOrderInfoDo.setQuantity(saleOrder.getQuantity());
        saleOrderInfoDo.setIsolationId(saleOrder.getIsolationId());
        saleOrderInfoDo.setPartnerId(saleOrder.getPartnerId());
        saleOrderInfoDo.setPartnerName(saleOrder.getPartnerName());
        DomainUtils.initDomainCreatedInfo(saleOrderInfoDo);
        saleOrderInfoDo.setRemark(saleOrder.getRemark());
        saleOrderInfoDo.setCustomerId(saleOrder.getCustomerId());
        saleOrderInfoDo.setCustomerName(saleOrder.getCustomerName());
        saleOrderInfoDo.setAscriptionOrgId(saleOrder.getAscriptionOrgId());
        saleOrderInfoDo.setParentSaleOrderId(saleOrder.getParentSaleOrderId());
        saleOrderInfoDo.setParentSaleOrderCode(saleOrder.getParentSaleOrderCode());
        saleOrderInfoDo.setProductId(saleOrder.getProductId());
        saleOrderInfoDo.setProductName(saleOrder.getProductName());
        saleOrderInfoDo.setPlanMonth(saleOrder.getPlanMonth());
        saleOrderInfoDo.setContractId(saleOrder.getContractId());
        saleOrderInfoDo.setContractName(saleOrder.getContractName());
        saleOrderInfoDo.setProjectId(saleOrder.getProjectId());
        saleOrderInfoDo.setProjectName(saleOrder.getProjectName());
        saleOrderInfoDo.setOrderType(saleOrder.getOrderType());
        saleOrderInfoDo.setDeliveryType(saleOrder.getDeliveryType());
        saleOrderInfoDo.setPayOrderCode(saleOrder.getPayOrderCode());
        return saleOrderInfoDo;
    }

    private void setSaleOrderInfoDO(SaleOrderInfoDO saleOrderInfoDo, SaleOrderInfoAddRequestDTO saleOrder) {
        saleOrderInfoDo.setAccrueAmount(saleOrder.getAccrueAmount());
        saleOrderInfoDo.setArriveDate(saleOrder.getArriveDate());
        saleOrderInfoDo.setBuyerType(saleOrder.getBuyerType());
        saleOrderInfoDo.setUpdatedBy(saleOrder.getUpdatedBy());
        saleOrderInfoDo.setDiscountAmount(saleOrder.getDiscountAmount());
        saleOrderInfoDo.setFromStorehouse(saleOrder.getFromStorehouse());
        saleOrderInfoDo.setHandler(saleOrder.getHandler());
        saleOrderInfoDo.setPaymentType(saleOrder.getPaymentType());
        saleOrderInfoDo.setSettlementStatus(saleOrder.getSettlementStatus());
        saleOrderInfoDo.setTicketDate(saleOrder.getTicketDate());
        saleOrderInfoDo.setTicketType(saleOrder.getTicketType());
        saleOrderInfoDo.setShipmentStatus(saleOrder.getShipmentStatus());
        saleOrderInfoDo.setShippingType(saleOrder.getShippingType());
        saleOrderInfoDo.setTotalExpense(saleOrder.getTotalAmount());
        saleOrderInfoDo.setTotalAmount(saleOrder.getTotalAmount());
        saleOrderInfoDo.setQuantity(saleOrder.getQuantity());
        saleOrderInfoDo.setRemark(saleOrder.getRemark());
        //设置合同项目信息
        saleOrderInfoDo.setContractId(saleOrder.getContractId());
        saleOrderInfoDo.setContractName(saleOrder.getContractName());
        saleOrderInfoDo.setProjectId(saleOrder.getProjectId());
        saleOrderInfoDo.setProjectName(saleOrder.getProjectName());
        saleOrderInfoDo.setDeliveryType(saleOrder.getDeliveryType());
    }

    /**
     * 计算订单总金额
     *
     * @param saleOrder
     */
    private void computeOrderAmount(SaleOrderInfoAddRequestDTO saleOrder) {
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        BigDecimal discountAmount = BigDecimal.valueOf(0);
        BigDecimal accrueAmount = BigDecimal.valueOf(0);
        BigDecimal totalExpense = getOrderExpenseTotalAmount(saleOrder.getOrderExpenseInfo());
        //设置商品明细
        setItemPrice(saleOrder.getItems(), saleOrder.getOrderCouponList(), saleOrder.getOrderPromotionList());
        if (CollectionUtil.isNotEmpty(saleOrder.getItems())) {
            saleOrder.getItems().forEach(item -> {
                totalAmount.add(item.getTotalAmount());
                discountAmount.add(item.getDiscountAmount());
                accrueAmount.add(item.getAccrueAmount());
            });
        }
        //应用金额=商品应收+其他费用
        accrueAmount.add(totalExpense);
        saleOrder.setAccrueAmount(accrueAmount);
        saleOrder.setDiscountAmount(discountAmount);
        saleOrder.setTotalAmount(totalAmount);
        saleOrder.setTotalExpense(totalExpense);
    }

    /**
     * 获取其他费用合计
     *
     * @param orderExpenseInfoList
     * @return
     */
    private BigDecimal getOrderExpenseTotalAmount(List<OrderExpenseInfoRequestDTO> orderExpenseInfoList) {
        BigDecimal orderExpenseTotalAmount = BigDecimal.valueOf(0);
        if (CollectionUtil.isNotEmpty(orderExpenseInfoList)) {
            orderExpenseInfoList.forEach(ex -> {
                orderExpenseTotalAmount.add(ex.getAmount());
            });
        }
        return orderExpenseTotalAmount;
    }

    /**
     * 设置商品明细总金额，优惠后金额,优惠分摊金额
     *
     * @param items
     * @param orderCouponList    优惠券
     * @param orderPromotionList 活动
     */
    private void setItemPrice(List<SaleOrderItemMiddleRequestDTO> items,
                              List<OrderCouponInfoRequestDTO> orderCouponList,
                              List<OrderPromotionInfoRequestDTO> orderPromotionList) {
        BigDecimal totalAmount = new BigDecimal(0);
        if (CollectionUtil.isNotEmpty(items)) {
            items.forEach(item -> {
                //合计金额
                item.setTotalAmount(item.getPrice().multiply(BigDecimal.valueOf(item.getSkuQuantity())));
                //TODO 优惠分摊金额
                //优惠后金额
                item.setAccrueAmount(item.getTotalAmount().subtract(item.getDiscountAmount() == null ?
                                                                            BigDecimal.valueOf(0) : item.getDiscountAmount()));
            });
        }
    }

    @Override
    public SaleOrderInfoDTO insert(SaleOrderInfoDTO record) {
        if (record == null) {
            log.error("新增销售订单表,传入参数为空");
            return null;
        }
        SaleOrderInfoDO saleOrderInfoDo = record.clone(SaleOrderInfoDO.class);
        DomainUtils.initDomainCreatedInfo(saleOrderInfoDo);
        if (saleOrderInfoDAO.insert(saleOrderInfoDo) > 0) {
            record.setId(saleOrderInfoDo.getId());
            return record;
        }
        log.error("新增销售订单表失败");
        return null;
    }

    @Override
    public SaleOrderResponseDTO selectSaleOrder(Long id) {
        SaleOrderInfoDO saleOrderInfoDO = saleOrderInfoDAO.selectById(id);
        if (saleOrderInfoDO == null) {
            return null;
        }
        return saleOrderInfoDO.clone(SaleOrderResponseDTO.class);
    }

    @Override
    public SaleOrderResponseDTO getSaleOrderByCode(String code) {
        QueryWrapper<SaleOrderInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SaleOrderInfoDO::getCode, code).eq(SaleOrderInfoDO::getDeleted, 0);
        SaleOrderInfoDO saleOrderInfoDO = saleOrderInfoDAO.getOne(queryWrapper);
        if (saleOrderInfoDO == null) {
            return null;
        }
        return saleOrderInfoDO.clone(SaleOrderResponseDTO.class);
    }

    @Override
    public List<SaleOrderResponseDTO> getSaleOrderByParentCode(String code) {
        QueryWrapper<SaleOrderInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SaleOrderInfoDO::getParentSaleOrderCode, code).eq(SaleOrderInfoDO::getDeleted, 0);
        List<SaleOrderInfoDO> saleOrderInfoDO = saleOrderInfoDAO.list(queryWrapper);
        if (CollectionUtil.isEmpty(saleOrderInfoDO)) {
            return null;
        }
        return ObjectCloneUtils.convertList(saleOrderInfoDO, SaleOrderResponseDTO.class);
    }

    @Override
    public SaleOrderInfoResponseDTO selectById(Long id) {
        if (id == null) {
            log.error("查询销售订单表,传入ID为空");
            return null;
        }
        SaleOrderInfoDO saleOrderInfoDO = saleOrderInfoDAO.selectById(id);
        if (saleOrderInfoDO == null) {
            return null;
        }
        SaleOrderInfoResponseDTO salesOrderInfoResponse = saleOrderInfoDO.clone(SaleOrderInfoResponseDTO.class);
        List<SaleOrderItemMiddleResponseDTO> saleOrderItemss = packageSalesItemDTO(id);
        salesOrderInfoResponse.setItems(saleOrderItemss);
        OrderConsigneeInfoResponseDTO orderConsigneeInfo = packageOrderConsigneeInfoResponseDTO(id);
        salesOrderInfoResponse.setOrderConsigneeInfo(orderConsigneeInfo);
        List<OrderCouponInfoResponseDTO> orderCouponList = packageOrderCouponInfoResponseDTO(id);
        salesOrderInfoResponse.setOrderCouponList(orderCouponList);
        List<OrderPromotionInfoResponseDTO> orderPromotionList = packageOrderPromotionInfoResponseDTO(id);
        salesOrderInfoResponse.setOrderPromotionList(orderPromotionList);
        OrderInvoiceInfoResponseDTO orderInvoiceInfo = packageOrderInvoiceInfoResponseDTO(id);
        salesOrderInfoResponse.setOrderInvoiceInfo(orderInvoiceInfo);
        List<OrderExpenseInfoResponseDTO> orderExpenseInfo = packageOrderExpenseInfoResponseDTO(id);
        salesOrderInfoResponse.setOrderExpenseInfo(orderExpenseInfo);
        packageSaleOutTaskAndOrderDeliveryInSaleOrder(salesOrderInfoResponse);
        List<OrderDeliveryInfoResponseDTO> orderDeliveryInfoResponseDTOS = packageOrderDeliveryInfoResponseDTO(id);
        salesOrderInfoResponse.setOrderDeliveryInfoList(orderDeliveryInfoResponseDTOS);
        //判断是否为自提
        if(null != saleOrderInfoDO.getDeliveryType() && saleOrderInfoDO.getDeliveryType() == 1){
            salesOrderInfoResponse.setOrderDeliverySelfRaisingInfoResponseDTO(packageOrderDeliverySelfRaisingInfoResponseDTO(id));
        }
        return salesOrderInfoResponse;
    }

    /**
     * @Description:  根据id和code查询订单信息.
     * @Param: [saleOrderInfoQuery]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/8/29
     */
    @Override
    public SaleOrderInfoResponseDTO searchSaleOrderInfoByQuery(SaleOrderInfoQuery saleOrderInfoQuery) throws Exception{
        log.info("查询传入的参数:{}",saleOrderInfoQuery);
        Optional.ofNullable(saleOrderInfoQuery).orElseThrow( ()->new ApplicationException("查询传入参数为空."));
        SaleOrderInfoQuery query = new SaleOrderInfoQuery();
        query.setId(saleOrderInfoQuery.getId());
        query.setCode(saleOrderInfoQuery.getCode());
        List<SaleOrderInfoDTO> saleOrderInfoDTOS = listSaleOrderInfos(query);
        //如果子订单存在则去查子订单的订单信息
        if (Utils.isNotEmpty(saleOrderInfoDTOS)){
            log.info("查询订单返回的信息:{}",JSON.toJSONString(saleOrderInfoDTOS));
            SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = Utils.selectOne(saleOrderInfoDTOS).clone(SaleOrderInfoResponseDTO.class);
            Long id = saleOrderInfoResponseDTO.getId();
            //商品信息
            List<SaleOrderItemMiddleResponseDTO> saleOrderItems = packageSalesItemDTO(id);
            saleOrderInfoResponseDTO.setItems(saleOrderItems);
            //收货地址
            if (saleOrderInfoResponseDTO.getDeliveryType().equals(0)){//送货
                OrderConsigneeInfoResponseDTO orderConsigneeInfo = packageOrderConsigneeInfoResponseDTO(id);
                saleOrderInfoResponseDTO.setOrderConsigneeInfo(orderConsigneeInfo);
            }else if (saleOrderInfoResponseDTO.getDeliveryType().equals(1)){//自提
                OrderConsigneeInfoResponseDTO dto = packageOrderDeliverySelfRaisingInfo(id);
                saleOrderInfoResponseDTO.setOrderConsigneeInfo(dto);
            }
            //物流信息
            List<OrderDeliveryInfoResponseDTO> orderDeliveryInfoResponseDTOS = packageOrderDeliveryInfoResponseDTO(id);
            saleOrderInfoResponseDTO.setOrderDeliveryInfoList(orderDeliveryInfoResponseDTOS);
            log.info("子订单返回的信息:{}",saleOrderInfoResponseDTO);
            return saleOrderInfoResponseDTO;
        }else {//如果为空 则去查对应的主订单的信息
            SaleOrderInfoQuery orderInfoQuery = new SaleOrderInfoQuery();
            orderInfoQuery.setParentSaleOrderId(saleOrderInfoQuery.getId());
            orderInfoQuery.setParentSaleOrderCode(saleOrderInfoQuery.getCode());
            List<SaleOrderInfoDTO> infoDTOList = listSaleOrderInfos(orderInfoQuery);
            if (Utils.isEmpty(infoDTOList)){
                throw new ApplicationException("订单错误");
            }
            log.info("查询的主订单信息:{}",JSON.toJSONString(infoDTOList));
            List<Long> saleOrderIdList = infoDTOList.stream().map(SaleOrderInfoDTO::getId).collect(Collectors.toList());
            SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = Utils.selectOne(infoDTOList).clone(SaleOrderInfoResponseDTO.class);
            Long id = saleOrderInfoResponseDTO.getId();
            //商品信息
            QueryWrapper<SaleOrderItemDO> queryWrapper = new QueryWrapper();
            queryWrapper.lambda().in(SaleOrderItemDO::getSaleOrderId, saleOrderIdList);
            List<SaleOrderItemDO> salesOrderItemDOS = saleOrderItemDAO.list(queryWrapper);
            List<SaleOrderItemMiddleResponseDTO> saleOrderItemResponseDTOS =
                    ObjectCloneUtils.convertList(salesOrderItemDOS, SaleOrderItemMiddleResponseDTO.class);
            log.info("主订单查询的商品信息:{}",saleOrderItemResponseDTOS);
            saleOrderInfoResponseDTO.setItems(saleOrderItemResponseDTOS);
            //收货地址
            if (saleOrderInfoResponseDTO.getDeliveryType().equals(0)){//送货
                OrderConsigneeInfoResponseDTO orderConsigneeInfo = packageOrderConsigneeInfoResponseDTO(id);
                saleOrderInfoResponseDTO.setOrderConsigneeInfo(orderConsigneeInfo);
            }else if (saleOrderInfoResponseDTO.getDeliveryType().equals(1)){//自提
                OrderConsigneeInfoResponseDTO dto = packageOrderDeliverySelfRaisingInfo(id);
                saleOrderInfoResponseDTO.setOrderConsigneeInfo(dto);
            }
            //物流信息
            List<OrderDeliveryInfoResponseDTO> orderDeliveryInfoResponseDTOS = packageOrderDeliveryInfoResponseDTO(id);
            saleOrderInfoResponseDTO.setOrderDeliveryInfoList(orderDeliveryInfoResponseDTOS);
            //金额汇总
            BigDecimal bigDecimal = infoDTOList.stream().filter(f -> Objects.nonNull(f.getAccrueAmount())).map(SaleOrderInfoDTO::getAccrueAmount).reduce(BigDecimal::add).get();
            saleOrderInfoResponseDTO.setAccrueAmount(bigDecimal);
            log.info("返回的信息:{}",saleOrderInfoResponseDTO);
            return saleOrderInfoResponseDTO;
        }
    }

    @Override
    public Boolean updatePayAmountById(SaleOrderInfoAddRequestDTO record) {
        if (record.getId() == null) {
            log.error("修改销售订单表,传入ID为空");
            return false;
        }
        SaleOrderInfoDO saleOrderInfoDO = saleOrderInfoDAO.selectById(record.getId());
        saleOrderInfoDO.setPayAmount(record.getPayAmount());
        return saleOrderInfoDAO.updateById(saleOrderInfoDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateById(Long id, SaleOrderInfoAddRequestDTO saleOrder) {
        if (id == null) {
            log.error("修改销售订单表,传入ID为空");
            return false;
        }

        SaleOrderInfoDO saleOrderInfoDO = saleOrderInfoDAO.selectById(id);
        setSaleOrderInfoDO(saleOrderInfoDO, saleOrder);
        DomainUtils.initUpdateTimeInfo(saleOrderInfoDO);
        //保存订单信息
        saleOrderInfoDAO.updateById(saleOrderInfoDO);
        //保存优惠券信息
        saveOrderCouponList(id, saleOrder, saleOrderInfoDO);

        //保存收货信息
        OrderConsigneeInfoDO orderConsigneeInfoDO =
                orderConsigneeInfoDAO.getOne(new QueryWrapper<OrderConsigneeInfoDO>().lambda().eq(OrderConsigneeInfoDO::getSaleOrderId, id).eq(OrderConsigneeInfoDO::getDeleted, 0));
        if (orderConsigneeInfoDO != null && saleOrder.getOrderConsigneeInfo() != null) {
            setOrderConsigneeInfoDO(orderConsigneeInfoDO, saleOrder.getOrderConsigneeInfo());
            DomainUtils.initUpdateTimeInfo(orderConsigneeInfoDO);
            orderConsigneeInfoDAO.updateById(orderConsigneeInfoDO);
        }

        //保存订单明细
        saveOrderItem(saleOrder, saleOrderInfoDO);
        //保存费用信息
        saveOrderExpenseInfo(saleOrder, saleOrderInfoDO);
        //保存促销信息
        saveOrderPromotionInfo(saleOrder, saleOrderInfoDO);
        //保存开票信息
        OrderInvoiceInfoDO orderInvoiceInfoDO =
                orderInvoiceInfoDAO.getOne(new QueryWrapper<OrderInvoiceInfoDO>().lambda().eq(OrderInvoiceInfoDO::getSaleOrderId, id).eq(OrderInvoiceInfoDO::getDeleted, 0));
        if (orderInvoiceInfoDO != null && saleOrder.getOrderInvoiceInfo() != null) {
            setOrderInvoiceInfoDO(orderInvoiceInfoDO, saleOrder.getOrderInvoiceInfo());
            DomainUtils.initUpdateTimeInfo(orderInvoiceInfoDO);
            orderInvoiceInfoDAO.updateById(orderInvoiceInfoDO);
        }
        if(Objects.isNull(orderInvoiceInfoDO) && Objects.nonNull(saleOrder.getOrderInvoiceInfo())){
            orderInvoiceInfoDO = new OrderInvoiceInfoDO();
            setOrderInvoiceInfoDO(orderInvoiceInfoDO, saleOrder.getOrderInvoiceInfo());
            DomainUtils.initDomainCreatedInfo(orderInvoiceInfoDO);
            orderInvoiceInfoDO.setSaleOrderId(saleOrderInfoDO.getId());
            orderInvoiceInfoDO.setSaleOrderCode(saleOrderInfoDO.getCode());
            orderInvoiceInfoDAO.insert(orderInvoiceInfoDO);
        }
        //保存自提信息
        if(null != saleOrder.getOrderSelfRaisingInfo()){
            OrderDeliverySelfRaisingInfoDO one =
                    orderDeliverySelfRaisingInfoDAO.getOne(new QueryWrapper<OrderDeliverySelfRaisingInfoDO>().eq("sale_order_id", id).eq("is_deleted", 0));
            if(null != one){
                OrderDeliverySelfRaisingInfoDO clone = saleOrder.getOrderSelfRaisingInfo().clone(OrderDeliverySelfRaisingInfoDO.class);
                clone.setId(one.getId());
                orderDeliverySelfRaisingInfoDAO.updateById(clone);
            }else{
                orderDeliverySelfRaisingInfoDAO.insert(saleOrder.getOrderSelfRaisingInfo().clone(OrderDeliverySelfRaisingInfoDO.class));
            }
        }
        return true;
    }

    private void setOrderInvoiceInfoDO(OrderInvoiceInfoDO orderInvoiceInfoDO,
                                       OrderInvoiceInfoRequestDTO orderInvoiceInfo) {
        orderInvoiceInfoDO.setAccountName(orderInvoiceInfo.getAccountName());
        orderInvoiceInfoDO.setAccountNo(orderInvoiceInfo.getAccountNo());
        orderInvoiceInfoDO.setBankName(orderInvoiceInfo.getBankName());
        orderInvoiceInfoDO.setInvocieContent(orderInvoiceInfo.getInvocieContent());
        orderInvoiceInfoDO.setInvoiceTitle(orderInvoiceInfo.getInvoiceTitle());
        orderInvoiceInfoDO.setInvoiceType(orderInvoiceInfo.getInvoiceType());
        orderInvoiceInfoDO.setTaxNo(orderInvoiceInfo.getTaxNo());
        orderInvoiceInfoDO.setRemark(orderInvoiceInfo.getRemark());
    }

    /**
     * 设置送货地址信息
     *
     * @param oldOrderConsigneeInfoDO
     * @param requestDTO
     */
    private void setOrderConsigneeInfoDO(OrderConsigneeInfoDO oldOrderConsigneeInfoDO,
                                         OrderConsigneeInfoAddRequestDTO requestDTO) {
        oldOrderConsigneeInfoDO.setAreaCode(requestDTO.getAreaCode());
        oldOrderConsigneeInfoDO.setAreaName(requestDTO.getAreaName());
        oldOrderConsigneeInfoDO.setCityCode(requestDTO.getCityCode());
        oldOrderConsigneeInfoDO.setCityName(requestDTO.getCityName());
        oldOrderConsigneeInfoDO.setConsignee(requestDTO.getConsignee());
        oldOrderConsigneeInfoDO.setMobile(requestDTO.getMobile());
        oldOrderConsigneeInfoDO.setProvinceCode(requestDTO.getProvinceCode());
        oldOrderConsigneeInfoDO.setProvinceName(requestDTO.getProvinceName());
        oldOrderConsigneeInfoDO.setStreetCode(requestDTO.getStreetCode());
        oldOrderConsigneeInfoDO.setStreetName(requestDTO.getStreetName());
        oldOrderConsigneeInfoDO.setDetailedAddress(requestDTO.getDetailedAddress());
        oldOrderConsigneeInfoDO.setRemark(requestDTO.getRemark());
    }

    /**
     * 保存促销信息
     *
     * @param saleOrder
     * @param saleOrderInfoDO
     */
    public void saveOrderPromotionInfo(SaleOrderInfoAddRequestDTO saleOrder, SaleOrderInfoDO saleOrderInfoDO) {
        if (CollectionUtil.isNotEmpty(saleOrder.getOrderPromotionList())) {
            //查询原有促销信息
            List<OrderPromotionInfoDO> list =
                    orderPromotionInfoDAO.list(new QueryWrapper<OrderPromotionInfoDO>().lambda().eq(OrderPromotionInfoDO::getSaleOrderId, saleOrderInfoDO.getId()).eq(OrderPromotionInfoDO::getDeleted, 0));
            if (CollectionUtil.isNotEmpty(list)) {
                orderPromotionInfoDAO.deleteByIdIn(list.stream().map(ex -> ex.getId()).collect(Collectors.toList()));
            }
            List<OrderPromotionInfoDO> orderPromotionInfoList =
                    getOrderPromotionInfoList(saleOrder.getOrderPromotionList(), true, saleOrderInfoDO.getId(),
                                              saleOrderInfoDO.getCode());
            orderPromotionInfoDAO.batchInsert(orderPromotionInfoList);
        }
    }

    /**
     * 保存费用明细
     *
     * @param saleOrder
     * @param saleOrderInfoDO
     */
    public void saveOrderExpenseInfo(SaleOrderInfoAddRequestDTO saleOrder, SaleOrderInfoDO saleOrderInfoDO) {
        if (CollectionUtil.isNotEmpty(saleOrder.getOrderExpenseInfo())) {
            List<OrderExpenseInfoDO> list =
                    orderExpenseInfoDAO.list(new QueryWrapper<OrderExpenseInfoDO>().lambda().eq(OrderExpenseInfoDO::getSaleOrderId, saleOrderInfoDO.getId()).eq(OrderExpenseInfoDO::getDeleted, 0));
            if (CollectionUtil.isNotEmpty(list)) {
                orderExpenseInfoDAO.deleteByIdIn(list.stream().map(ex -> ex.getId()).collect(Collectors.toList()));
            }
            List<OrderExpenseInfoDO> orderExpenseInfoList = getOrderExpenseInfoList(saleOrder.getOrderExpenseInfo(),
                                                                                    true, saleOrderInfoDO.getId());
            orderExpenseInfoDAO.batchInsert(orderExpenseInfoList);
        }
    }

    /**
     * 保存明细信息,先删除原有明细，批量插入
     *
     * @param saleOrder
     * @param saleOrderInfoDO
     */
    public void saveOrderItem(SaleOrderInfoAddRequestDTO saleOrder, SaleOrderInfoDO saleOrderInfoDO) {
        //查询出当前订单所有明细
        List<SaleOrderItemDO> oldSaleOrderItemList =
                saleOrderItemDAO.list(new QueryWrapper<SaleOrderItemDO>().lambda().eq(SaleOrderItemDO::getSaleOrderId
                        , saleOrderInfoDO.getId()).eq(SaleOrderItemDO::getDeleted, 0));
        //删除已存在的订单明细
        if (CollectionUtil.isNotEmpty(oldSaleOrderItemList)) {
            saleOrderItemDAO.deleteByIdIn(oldSaleOrderItemList.stream().map(old -> old.getId()).collect(Collectors.toList()));
        }
        List<SaleOrderItemDO> saleOrderItemDOs = getSaleOrderItemList(saleOrder.getItems(), true,
                                                                      saleOrderInfoDO.getId(), saleOrderInfoDO.getCode());
        saleOrderItemDAO.batchInsert(saleOrderItemDOs);
    }

    /**
     * 保存优惠券信息
     *
     * @param id
     * @param saleOrder
     * @param saleOrderInfoDO
     */
    public void saveOrderCouponList(Long id, SaleOrderInfoAddRequestDTO saleOrder, SaleOrderInfoDO saleOrderInfoDO) {
        if (CollectionUtil.isNotEmpty(saleOrder.getOrderCouponList())) {
            //删除当前已经存在的优惠券
            List<OrderCouponInfoDO> oldOrderCouponInfoDOList =
                    orderCouponInfoDAO.list(new QueryWrapper<OrderCouponInfoDO>().lambda().in(OrderCouponInfoDO::getCouponId).eq(OrderCouponInfoDO::getSaleOrderId, id).eq(OrderCouponInfoDO::getDeleted, 0));
            if (CollectionUtil.isNotEmpty(oldOrderCouponInfoDOList)) {
                orderCouponInfoDAO.deleteByIdIn(oldOrderCouponInfoDOList.stream().map(infoDo -> infoDo.getId()).collect(Collectors.toList()));
            }
            //保存新的优惠券信息
            List<OrderCouponInfoDO> orderCouponInfoDOList = getOrderCouponInfoDO(saleOrder.getOrderCouponList(), true
                    , saleOrderInfoDO.getId(), saleOrderInfoDO.getCode());
            orderCouponInfoDAO.batchInsert(orderCouponInfoDOList);
        }
    }

    @Override
    public Boolean updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO) {
        if (saleOrderInfoDAO.updateOrderStatus(saleOrderInfoStatusEditRequestDTO) > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 根据订单ID集合查询销售订单表，用于出库单列表查询.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public List<SaleOrderInfoResponseDTO> searchSaleOrderInfoById(List<Long> idList) {
        List<SaleOrderInfoResponseDTO> list = Lists.newArrayList();
        if (Utils.isEmpty(idList)) {
            log.error("查询销售订单表，传入ID为空");
            return list;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id", idList);
        queryWrapper.eq("is_deleted", 0);
        list = saleOrderInfoDAO.list(queryWrapper);
        return ObjectCloneUtils.convertList(list, SaleOrderInfoResponseDTO.class);
    }

    /**
     * @param buyerName
     * @Description: 根据用户拿到批量订单ID，用于出库单查询.
     * @Param: [buyerName]
     * @return: java.util.List<java.lang.Long>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    @Override
    public List<Long> getSaleOrderIdListByBuyerName(String buyerName) {
        List<Long> list = Lists.newArrayList();
        if (Utils.isEmpty(buyerName)) {
            return list;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("buyer_name", buyerName.trim());
        queryWrapper.eq("is_deleted", 0);
        List<SaleOrderInfoDO> saleOrderInfoDOList = saleOrderInfoDAO.list(queryWrapper);
        list = saleOrderInfoDOList.stream().map(SaleOrderInfoDO::getId).collect(Collectors.toList());
        return list;
    }

    /**
     * @Description: 订单发货.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsMiddleRequestDTO requestDTO) throws Exception {
        log.info("订单发货传入的参数: {}", JSON.toJSONString(requestDTO));
        if (Objects.isNull(requestDTO)) {
            throw new ApplicationException("订单发货，传入参数为空.");
        }
        List<SaleOrderItemMiddleRequestDTO> itemList = requestDTO.getItems();//商品信息
        List<SaleOrderItemMiddleRequestDTO> middleRequestDTOList =
                itemList.stream().filter(f -> Objects.nonNull(f.getSkuShipmentQuantity())).collect(Collectors.toList());
        if (Utils.isEmpty(middleRequestDTOList)) {
            throw new ApplicationException("订单发货，没有发货的商品");
        }
        List<Long> idList = middleRequestDTOList.stream().filter(f -> Objects.nonNull(f.getId()) && (!Objects.equals(f.getId(), 0))).
                map(AbstractTenantDTO::getId).collect(Collectors.toList());//商品明细ID集合
        List<SaleOrderItemDO> saleOrderItemList = searchSaleOrderItemInfoByItemIdList(idList);
        log.info("商品信息: {}", JSON.toJSONString(saleOrderItemList));
        if (idList.size() != saleOrderItemList.size()) {//传入参数校验
            throw new ApplicationException("订单发货，商品参数异常.");
        }
        //商品明细id 对应每次的发货数
        Map<Long, Long> itemMap = middleRequestDTOList.stream().collect(Collectors.toMap(AbstractTenantDTO::getId,
                                                                                         SaleOrderItemMiddleRequestDTO::getSkuShipmentQuantity));
        //更新商品库存
        saleOrderItemList.stream().forEach(f -> {
            Long skuShipmentQuantity = itemMap.get(f.getId());//商品每次发货数量
            if (skuShipmentQuantity > 0) {
                Long skuTotalQuantity = f.getSkuTotalQuantity();//商品总发货数
                if (Objects.isNull(skuTotalQuantity)) {
                    skuTotalQuantity = 0L;
                }
                skuTotalQuantity += skuShipmentQuantity;
                if (skuTotalQuantity <= f.getSkuQuantity()) {
                    f.setSkuTotalQuantity(skuTotalQuantity);//更新库存
                } else {
                    throw new ApplicationException("商品库存不足.");
                }
                DomainUtils.initUpdateTimeInfo(f);
            }
        });
        //更新订单总发货数量
        saleOrderItemDAO.updateBatchById(saleOrderItemList);//更新库存
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = selectById(requestDTO.getSaleOrderId());
        requestDTO.setSkuQuantity(saleOrderInfoResponseDTO.getQuantity());//订单关联的商品总数
        Long SaleOrderTotalQuantity = searchSaleOrderItemInfoById(requestDTO.getSaleOrderId()).stream().
                filter(f -> Objects.nonNull(f.getSkuTotalQuantity())).mapToLong(SaleOrderItemDO::getSkuTotalQuantity).sum();//总发货数量
        Long totalQuantity = Objects.isNull(saleOrderInfoResponseDTO.getTotalQuantity()) ? 0L :
                Long.valueOf(saleOrderInfoResponseDTO.getTotalQuantity());//发货总数
        // 对比 如果发货总数 = 总库存 那么订单已发货 否则 部分发货
        Integer shipmentStatus = SaleOrderTotalQuantity == totalQuantity ? 1 : 2;
        saleOrderInfoResponseDTO.setTotalQuantity(SaleOrderTotalQuantity);//发货总数
        saleOrderInfoResponseDTO.setShipmentStatus(shipmentStatus);//出库状态
        SaleOrderInfoDO saleOrderInfoDO = saleOrderInfoResponseDTO.clone(SaleOrderInfoDO.class);
        log.info("订单信息: {}", JSON.toJSONString(saleOrderInfoDO));
        DomainUtils.initUpdateTimeInfo(saleOrderInfoDO);
        saleOrderInfoDAO.updateById(saleOrderInfoDO);
        saleOutTaskService.insert(saleOrderInfoConverterDTO(requestDTO));//生成出库单
        return true;
    }

    /**
     * @Description: 发货根据商品id集合拿到所有的商品信息.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.SaleOrderItemDO>
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    private List<SaleOrderItemDO> searchSaleOrderItemInfoByItemIdList(List<Long> idList) {
        QueryWrapper<SaleOrderItemDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().in(SaleOrderItemDO::getId, idList).eq(SaleOrderItemDO::getDeleted, 0);
        List<SaleOrderItemDO> saleOrderItemDOList = saleOrderItemDAO.list(queryWrapper);
        return saleOrderItemDOList;
    }

    /**
     * @Description: 发货根据订单id集合拿到所有的商品信息.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.SaleOrderItemDO>
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    private List<SaleOrderItemDO> searchSaleOrderItemInfoById(Long id) {
        QueryWrapper<SaleOrderItemDO> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SaleOrderItemDO::getSaleOrderId, id).eq(SaleOrderItemDO::getDeleted, 0);
        List<SaleOrderItemDO> saleOrderItemDOList = saleOrderItemDAO.list(queryWrapper);
        return saleOrderItemDOList;
    }

    /**
     * @Description: 出库单DTO转化.
     * @Param: [dto]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOutTaskMiddleRequestDTO
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    private SaleOutTaskMiddleRequestDTO saleOrderInfoConverterDTO(SaleOrderInfoDeliverGoodsMiddleRequestDTO dto) {
        SaleOutTaskMiddleRequestDTO requestDTO = new SaleOutTaskMiddleRequestDTO();
        if (Objects.isNull(dto)) {
            return requestDTO;
        }
        requestDTO.setAppId(dto.getAppId());//应用id
        requestDTO.setTenantId(dto.getTenantId());//租户id
        requestDTO.setVersion(dto.getVersion());//版本号
        requestDTO.setSaleOrderId(dto.getSaleOrderId());//订单号
        requestDTO.setSaleOrderCode(dto.getSaleOrderCode());//订单编号
        requestDTO.setTicketDate(dto.getTicketDate());//单据日期
        requestDTO.setDeliveryWareHouseId(dto.getDeliveryWareHouseId());//仓库ID
        requestDTO.setDeliveryWareHouseName(dto.getDeliveryWareHouseName());//仓库名称
        requestDTO.setDeliveryType(dto.getDeliveryType());//发货方式
        requestDTO.setOrderDeliveryInfo(dto.getOrderDeliveryInfo());//物流信息
        requestDTO.setDeliveryTime(dto.getDeliveryTime());//发货时间
        requestDTO.setOrderDeliveryConsigneeInfo(dto.getOrderDeliveryConsigneeInfo());//出库单发货信息
        requestDTO.setCode(dto.getSaleOutTaskCode());//出库单编号
        requestDTO.setSkuQuantity(dto.getSkuQuantity());//计划出库总数量
        requestDTO.setCreatedBy(dto.getCreatedBy());//创建人
        requestDTO.setUpdateBy(dto.getUpdateBy());//修改人
        requestDTO.setIsolationId(dto.getIsolationId());//数据隔离ID
        requestDTO.setAscriptionOrgId(dto.getAscriptionOrgId());//拆单所属组织
        List<SaleOutTaskDetailInfoMiddleRequestDTO> list = dto.getItems().stream().map(map -> {
            SaleOutTaskDetailInfoMiddleRequestDTO detailInfo = new SaleOutTaskDetailInfoMiddleRequestDTO();
            detailInfo.setSaleOrderId(map.getSaleOrderId());//订单ID
            detailInfo.setSaleOrderItemId(map.getId());//商品明细ID
            detailInfo.setSkuShipmentQuantity(map.getSkuShipmentQuantity());//商品每次出库数量
            return detailInfo;
        }).collect(Collectors.toList());
        requestDTO.setSaleOrderDetailInfo(list);
        return requestDTO;
    }

    /**
     * 发货根据订单id获取自提信息.
     * @param id
     * @return
     */
    private OrderDeliverySelfRaisingInfoResponseDTO packageOrderDeliverySelfRaisingInfoResponseDTO(Long id) {
        OrderDeliverySelfRaisingInfoDO orderDeliverySelfRaisingInfoDO = orderDeliverySelfRaisingInfoDAO.selectBySaleOrderId(id);
        return orderDeliverySelfRaisingInfoDO.clone(OrderDeliverySelfRaisingInfoResponseDTO.class);
    }

    /**
     * 更新订单金额和状态
     *
     * @param saleOrderInfoAmountEditDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean updateOrderAmount(SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO) {
        return saleOrderInfoDAO.updateOrderAmount(saleOrderInfoAmountEditDTO) > 0;
    }

    /**
     * 更新支付记录订单编号
     *
     * @param saleOrderInfoPayOrderCodeEditDTO
     * @return
     */
    @Override
    public Boolean updatePayOrderCode(SaleOrderInfoPayOrderCodeEditDTO saleOrderInfoPayOrderCodeEditDTO) {
        log.info("更新支付记录订单编号", JsonUtil.bean2JsonString(saleOrderInfoPayOrderCodeEditDTO));
        if (ObjectUtils.isEmpty(saleOrderInfoPayOrderCodeEditDTO)) {
            return false;
        }
        return saleOrderInfoDAO.updatePayOrderCode(saleOrderInfoPayOrderCodeEditDTO);
    }

    /**
     * @param saleOrderCodeList
     * @Description: 根据批量的订单编号查订单信息.
     * @Param: [saleOrderIdList]
     * @return: com.deepexi.util.config.Payload<java.util.List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    @Override
    public List<SaleOrderInfoResponseDTO> batchSearchSaleOrderInfo(List<String> saleOrderCodeList) {
        if (Utils.isEmpty(saleOrderCodeList)) {
            return null;
        }
        QueryWrapper<SaleOrderInfoDO> queryWrapper = new QueryWrapper<SaleOrderInfoDO>();
        queryWrapper.lambda().in(SaleOrderInfoDO::getCode, saleOrderCodeList).eq(BaseDO::getDeleted, 0);
        List<SaleOrderInfoDO> list = saleOrderInfoDAO.list(queryWrapper);
        List<SaleOrderInfoResponseDTO> saleOrderInfoResponseDTOList = ObjectCloneUtils.convertList(list, SaleOrderInfoResponseDTO.class);
        return saleOrderInfoResponseDTOList;
    }

    /**
     * @param list
     * @Description: 根据批量订单id修改.
     * @Param: [list]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    @Override
    public Boolean updateBatchById(List<SaleOrderInfoRequestDTO> list) {
        List<SaleOrderInfoDO> saleOrderInfoDOList = ObjectCloneUtils.convertList(list, SaleOrderInfoDO.class);
        return saleOrderInfoDAO.updateBatchById(saleOrderInfoDOList);
    }

    @Override
    public Boolean updateById(Long id, SaleOrderInfoRequestDTO record) {
        SaleOrderInfoDO infoDO = record.clone(SaleOrderInfoDO.class);
        return saleOrderInfoDAO.updateById(infoDO);
    }

    @Override
    public SaleOrderInfoDTO selectSaleOrder(Long id, String code) {
        QueryWrapper<SaleOrderInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SaleOrderInfoDO::getId, id).eq(SaleOrderInfoDO::getCode, code).eq(SaleOrderInfoDO::getDeleted, 0);
        SaleOrderInfoDO infoDO = saleOrderInfoDAO.getOne(queryWrapper);
        if (infoDO != null) {
            return infoDO.clone(SaleOrderInfoDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean closePreMonthPlanOrder() {
        List<SaleOrderInfoDO> list= saleOrderInfoDAO.getPreMonthPlanOrder();
        if(CollectionUtil.isEmpty(list)){
            log.info("没有要关闭的计划订单");
            return true;
        }

        List<SaleOrderInfoDO> updateOrder=new ArrayList<>();
        List<OrderOperationRecordDTO> recordDTOS=new ArrayList<>();
        for(SaleOrderInfoDO item:list){
            SaleOrderInfoDO saleOrderInfoDO=new SaleOrderInfoDO();
            saleOrderInfoDO.setId(item.getId());
            saleOrderInfoDO.setStatus(11);
            updateOrder.add(saleOrderInfoDO);

            OrderOperationRecordDTO operationRecordDTO = new OrderOperationRecordDTO();
            operationRecordDTO.setAppId(item.getAppId());
            operationRecordDTO.setTenantId(item.getTenantId());
            operationRecordDTO.setCreatedTime(new Date());
            operationRecordDTO.setOrderId(item.getId());
            operationRecordDTO.setOperation("关闭订单");
            operationRecordDTO.setOperationType(1);
            operationRecordDTO.setVersion(0);
            operationRecordDTO.setDeleted(false);
            operationRecordDTO.setCreatedBy("");
            operationRecordDTO.setRemark("自动关闭");
            recordDTOS.add(operationRecordDTO);
        }

        saleOrderInfoDAO.updateBatchById(updateOrder);
        orderOperationRecordService.batchInsert(recordDTOS);

        return true;
    }
}