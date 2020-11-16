package com.deepexi.dd.domain.transaction.export;

import com.alibaba.excel.metadata.BaseRowModel;
import com.deepexi.clientiam.api.GroupControllerApi;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.clientiam.model.PayloadGroupResultVO;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.export.BaseExport;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderBtnResponseDTO;
import com.deepexi.dd.domain.transaction.domain.export.ExportPlanSaleOrderDTO;
import com.deepexi.dd.domain.transaction.domain.export.ExportSaleOrderDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.domain.transaction.remote.order.MerchantClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.DateUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.dto.MerchantResponseDTO;
import domain.query.MerchantRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlanSaleOrderExportService extends BaseExport<ExportPlanSaleOrderDTO, SaleOrderInfoRequestQuery> {
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Autowired
    MerchantClient merchantClient;
    @Autowired
    private GroupControllerApi groupControllerApi;

    private static final String SUCCESS = "0";


    @Override
    public Class<? extends BaseRowModel> getClazz() {
        return ExportPlanSaleOrderDTO.class;
    }

    @Override
    public PageBean<ExportPlanSaleOrderDTO> getPageData(SaleOrderInfoRequestQuery query, int page, int pageSize) throws Exception {
        List<ExportPlanSaleOrderDTO> exportPlanSaleOrderDTOS = new ArrayList<>();
        SaleOrderInfoQuery model = query.clone(SaleOrderInfoQuery.class);
        model.setListType("SalesOrder");
        model.setSize(19999);
        model.setTicketType(TicketTypeEnum.PLAN.getValue());
        PageBean<SaleOrderBtnResponseDTO> pageBean =
                GeneralConvertUtils.convert2PageBean(saleOrderInfoService.listSaleOrderInfosPage(model),
                        SaleOrderBtnResponseDTO.class);
        if (pageBean != null && pageBean.getContent() != null && pageBean.getContent().size() != 0) {
            List<SaleOrderBtnResponseDTO> saleOrderBtnResponseDTOList =
                    GeneralConvertUtils.convert2List(pageBean.getContent(), SaleOrderBtnResponseDTO.class);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获得客户
            List<Long> merchantIds =
                    saleOrderBtnResponseDTOList.stream().filter(f -> f.getCustomerId() != null).map(m -> m.getCustomerId()).collect(Collectors.toList());
            MerchantRequestQuery merchantRequestQuery = new MerchantRequestQuery();
            merchantRequestQuery.setIdList(merchantIds);
            merchantRequestQuery.setTenantId(saleOrderBtnResponseDTOList.get(0).getTenantId());
            merchantRequestQuery.setAppId(saleOrderBtnResponseDTOList.get(0).getAppId());
            Payload<List<MerchantResponseDTO>> listPayload = merchantClient.findList(merchantRequestQuery);
            List<MerchantResponseDTO> merchantResponseDTOList =
                    GeneralConvertUtils.convert2List(listPayload.getPayload(), MerchantResponseDTO.class);
            Map<Long, List<MerchantResponseDTO>> merchantList =
                    merchantResponseDTOList.stream().collect(Collectors.groupingBy(MerchantResponseDTO::getId));
            saleOrderBtnResponseDTOList.forEach(planOrder -> {
                if (CollectionUtils.isNotEmpty(planOrder.getItems())) {
                    planOrder.getItems().forEach(sku -> {
                        ExportPlanSaleOrderDTO exportPlanSaleOrderDTO = new ExportPlanSaleOrderDTO();
                        exportPlanSaleOrderDTO.setCode(planOrder.getCode());
                        //下单人
                        //exportPlanSaleOrderDTO.setCreatedBy(planOrder.getBuyerName());
                        exportPlanSaleOrderDTO.setCreatedBy(planOrder.getHandlerName());
                        exportPlanSaleOrderDTO.setCreatedTime(simpleDateFormat.format(planOrder.getCreatedTime()));
                        if (planOrder.getCustomerId() != null && CollectionUtils.isNotEmpty(merchantList.get(planOrder.getCustomerId()))) {
                            exportPlanSaleOrderDTO.setCustomerCode(merchantList.get(planOrder.getCustomerId()).get(0).getCode());
                            exportPlanSaleOrderDTO.setCustomerName(merchantList.get(planOrder.getCustomerId()).get(0).getName());
                        } else {
                            exportPlanSaleOrderDTO.setCustomerCode(null);
                            exportPlanSaleOrderDTO.setCustomerName(exportPlanSaleOrderDTO.getCustomerName());
                        }
                        exportPlanSaleOrderDTO.setPlanMonth(planOrder.getPlanMonth());
                        exportPlanSaleOrderDTO.setPrice(sku.getPrice());
                        exportPlanSaleOrderDTO.setQuantity(sku.getSkuQuantity());
                        PayloadGroupResultVO resultVO = groupControllerApi.getGroup(planOrder.getSellerId(),
                                planOrder.getTenantId(),
                                Long.valueOf(planOrder.getCreatedBy()), null);
                        log.info("通过订单记录的一级组织id获得组织编码；接口：resultVO={};", resultVO);
                        GroupResultVO grouVO = null;
                        if (SUCCESS.equals(resultVO.getCode()) && resultVO.getPayload() != null) {
                            grouVO = resultVO.getPayload();
                            exportPlanSaleOrderDTO.setSellerCode(grouVO.getCode());
                            exportPlanSaleOrderDTO.setSellerName(grouVO.getName());
                        } else {
                            log.info("查询不到卖家的顶级组织");
                            exportPlanSaleOrderDTO.setSellerCode(null);
                            exportPlanSaleOrderDTO.setSellerName(planOrder.getSellerName());
                        }
                        exportPlanSaleOrderDTO.setSkuCode(sku.getSkuCode());
                        exportPlanSaleOrderDTO.setSkuFormat(sku.getSkuFormat());
                        exportPlanSaleOrderDTO.setSkuName(sku.getSkuName());
                        exportPlanSaleOrderDTO.setTotalAmount(sku.getTotalAmount());
                        exportPlanSaleOrderDTOS.add(exportPlanSaleOrderDTO);
                    });

                }
            });
        }


        return new PageBean<>(exportPlanSaleOrderDTOS);
    }

    @Override
    public String getFieldName() {
        String fileName = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(), "订货计划订单",
                DateUtils.format(new Date(), "yyyyMMdd"), 5);
        log.info("----------------------------------------订单导出文件:{}------------------------", fileName);
        return fileName;
    }

    @Override
    public String getSheetName() {
        return "订货计划订单";
    }

    @Override
    public int getPageSize() {
        return 8000;
    }
}
