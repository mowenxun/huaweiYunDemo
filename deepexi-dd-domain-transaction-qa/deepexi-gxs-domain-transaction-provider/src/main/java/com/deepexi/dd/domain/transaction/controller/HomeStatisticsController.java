package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.vo.SalesStatisticsResponseVO;
import com.deepexi.dd.domain.transaction.domain.vo.SalesTodoResponseVO;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SalesStatisticsClient;
import com.deepexi.dd.domain.transaction.util.DateUtil;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalesStatisticsResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalesStatisticsRequestQuery;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@Api(tags = "主控台统计")
@RequestMapping("/admin-api/v1/domain/transaction/homeStatistics")
public class HomeStatisticsController {

    @Autowired
    private SalesStatisticsClient salesStatisticsClient;

    @Autowired
    private SalePickGoodsInfoClient salePickGoodsInfoClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;


    @ApiOperation("获取销售待处理事项")
    @GetMapping("/getSalesTodos")
    public Payload<SalesTodoResponseVO> getSalesTodos(String tenantId,Long appId,Long orgId) throws Exception {

        Long topOrgId=appRuntimeEnv.getTopOrganization().getId();
        Long currentOrgId=appRuntimeEnv.getUserOrganization().getId();

        if(topOrgId.longValue()!=currentOrgId.longValue()){
            orgId=currentOrgId;
        }

        List<SalesStatisticsResponseDTO> salesOrderResult= getTodoOrders(tenantId,appId,orgId);

        List<SalesStatisticsResponseDTO> salesPayments= getTodoSalesPayments(tenantId,appId,orgId);

        List<SalePickGoodsInfoResponseDTO> pickGoodsResult=getTodoPickGoods(tenantId,appId,orgId);

        //待接单网批订单
        long s4DirectSupply = salesOrderResult.stream().filter(f->f.getStatus()==4).count();

        //待发货网批订单
        long s67DirectSupply = salesOrderResult.stream().filter(f->(f.getStatus()==6 || f.getStatus()==7)).count();

        //待签收网批订单
        long s8DirectSupply = salesOrderResult.stream().filter(f->f.getStatus()==8).count();

        //待确认提货计划
        long s24PickPlan=pickGoodsResult.size();

        //待付款网批订单
        long s26DirectSupply=salesPayments.size();


        SalesTodoResponseVO salesTodoResponseVO = new SalesTodoResponseVO();
        salesTodoResponseVO.setS4DirectSupply(s4DirectSupply);
        salesTodoResponseVO.setS67DirectSupply(s67DirectSupply);
        salesTodoResponseVO.setS8DirectSupply(s8DirectSupply);
        salesTodoResponseVO.setS24PickPlan(s24PickPlan);
        salesTodoResponseVO.setS20OrderPlan(0L);
        salesTodoResponseVO.setS26DirectSupply(s26DirectSupply);


        return new Payload<>(salesTodoResponseVO);
    }

    @ApiOperation("获取销售统计")
    @GetMapping("/getSalesStatistics")
    public Payload<SalesStatisticsResponseVO> getSalesStatistics(String tenantId,Long appId,Long orgId,String statisticsType) throws Exception {

        Long topOrgId=appRuntimeEnv.getTopOrganization().getId();
        Long currentOrgId=appRuntimeEnv.getUserOrganization().getId();

        if(topOrgId.longValue()!=currentOrgId.longValue()){
            orgId=currentOrgId;
        }

        List<SalesStatisticsResponseDTO> salesOrderResult = getStatisticsOrders(tenantId,appId,orgId,statisticsType);

        //销售笔数
        long salesCount = salesOrderResult.size();

        //销售金额
        BigDecimal salesAmount = salesOrderResult.stream().map(m->m.getAccrueAmount()).reduce(BigDecimal.ZERO,BigDecimal::add);

        //收款金额
        BigDecimal payAmount = salesOrderResult.stream().map(m->m.getPayAmount()).reduce(BigDecimal.ZERO,BigDecimal::add);

        SalesStatisticsResponseVO salesStatisticsResponseVO = new SalesStatisticsResponseVO();
        salesStatisticsResponseVO.setSalesCount(salesCount);
        salesStatisticsResponseVO.setSalesAmount(salesAmount);
        salesStatisticsResponseVO.setPayAmount(payAmount);

        return new Payload<>(salesStatisticsResponseVO);
    }

    private List<SalesStatisticsResponseDTO> getTodoOrders(String tenantId, Long appId, Long orgId) throws Exception {
        SalesStatisticsRequestQuery salesStatisticsRequestQuery=new SalesStatisticsRequestQuery();
        salesStatisticsRequestQuery.setTenantId(tenantId);
        salesStatisticsRequestQuery.setAppId(appId);
        //saleOrderInfoRequestQuery.setIsolationId(appRuntimeEnv.getTopOrganization().getId().toString());
        if(null!=orgId) {
            salesStatisticsRequestQuery.setAscriptionOrgId(orgId);
        }

        salesStatisticsRequestQuery.setSellerId(appRuntimeEnv.getTopOrganization().getId());

        salesStatisticsRequestQuery.setTicketType(0);//网批订单
        List<Integer> statuses=new ArrayList();
        statuses.add(4);//待接单
        statuses.add(6);//待发货
        statuses.add(7);//部分发货
        statuses.add(8);//全部发货
        salesStatisticsRequestQuery.setStatusList(statuses);

        Payload<List<SalesStatisticsResponseDTO>> statisticsRecord = salesStatisticsClient.getStatisticsRecord(salesStatisticsRequestQuery);

        return GeneralConvertUtils.convert2List(statisticsRecord.getPayload(), SalesStatisticsResponseDTO.class);
    }

    private List<SalesStatisticsResponseDTO> getTodoSalesPayments(String tenantId,Long appId,Long orgId) throws Exception {
        SalesStatisticsRequestQuery salesStatisticsRequestQuery=new SalesStatisticsRequestQuery();
        salesStatisticsRequestQuery.setTenantId(tenantId);
        salesStatisticsRequestQuery.setAppId(appId);

        Long topOrganizationId=appRuntimeEnv.getTopOrganization().getId();

        if(null!=orgId) {
            salesStatisticsRequestQuery.setAscriptionOrgId(orgId);
        }

        salesStatisticsRequestQuery.setSellerId(topOrganizationId);
        //saleOrderInfoRequestQuery.setIsolationId(topOrganizationId.toString());

        salesStatisticsRequestQuery.setTicketType(0);//网批订单
        List<Integer> exceptStatuses = new ArrayList<>();
        exceptStatuses.add(1);    //过滤草稿状态
        salesStatisticsRequestQuery.setExceptStatusList(exceptStatuses);
        salesStatisticsRequestQuery.setPaymentStatus(26);//待付款

        Payload<List<SalesStatisticsResponseDTO>> statisticsRecord = salesStatisticsClient.getStatisticsRecord(salesStatisticsRequestQuery);

        return GeneralConvertUtils.convert2List(statisticsRecord.getPayload(), SalesStatisticsResponseDTO.class);
    }

    private List<SalePickGoodsInfoResponseDTO> getTodoPickGoods(String tenantId,Long appId,Long orgId){
        SalePickGoodsInfoRequestQuery query =new SalePickGoodsInfoRequestQuery();
        query.setTenantId(tenantId);
        query.setAppId(appId);
        //query.setIsolationId(appRuntimeEnv.getTopOrganization().getId().toString());
        query.setSellerId(appRuntimeEnv.getTopOrganization().getId());
        if(null!=orgId) {
            query.setAscriptionOrgId(orgId);
        }

        query.setStatus(24);//待确认

        return salePickGoodsInfoClient.listSalePickGoodsInfos(query);
    }


    private List<SalesStatisticsResponseDTO> getStatisticsOrders(String tenantId,Long appId,Long orgId,String statisticsType) throws Exception {

        SalesStatisticsRequestQuery salesStatisticsRequestQuery=new SalesStatisticsRequestQuery();

        salesStatisticsRequestQuery.setTenantId(tenantId);
        salesStatisticsRequestQuery.setAppId(appId);
        //saleOrderInfoRequestQuery.setIsolationId(appRuntimeEnv.getTopOrganization().getId().toString());
        salesStatisticsRequestQuery.setSellerId(appRuntimeEnv.getTopOrganization().getId());
        if(null!=orgId) {
            salesStatisticsRequestQuery.setAscriptionOrgId(orgId);
        }

        List<Integer> exceptStatuses = new ArrayList<>();
        exceptStatuses.add(11);//交易取消
        exceptStatuses.add(12);//交易关闭
        salesStatisticsRequestQuery.setExceptStatusList(exceptStatuses);

        switch (statisticsType){
            case "week":
                salesStatisticsRequestQuery.setCreateTimeFrom(DateUtil.date2String(DateUtil.getBeginDayOfWeek()));
                salesStatisticsRequestQuery.setCreateTimeTo(DateUtil.date2String(DateUtil.getEndDayOfWeek()));
                break;
            case "month":
                salesStatisticsRequestQuery.setCreateTimeFrom(DateUtil.date2String(DateUtil.getBeginDayOfMonth()));
                salesStatisticsRequestQuery.setCreateTimeTo(DateUtil.date2String(DateUtil.getEndDayOfMonth()));
                break;
            default:
                salesStatisticsRequestQuery.setCreateTimeFrom(DateUtil.date2String(new Date()));
                salesStatisticsRequestQuery.setCreateTimeTo(DateUtil.date2String(new Date()));
                break;
        }


        Payload<List<SalesStatisticsResponseDTO>> statisticsRecord = salesStatisticsClient.getStatisticsRecord(salesStatisticsRequestQuery);

        return GeneralConvertUtils.convert2List(statisticsRecord.getPayload(), SalesStatisticsResponseDTO.class);
    }







}
