package com.deepexi.dd.domain.transaction.export;

import com.alibaba.excel.metadata.BaseRowModel;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.export.BaseExport;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.tool.enums.LinkTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderBtnResponseDTO;
import com.deepexi.dd.domain.transaction.domain.export.ReportSaleOrderDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.DateUtils;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class SaleOrderReportService extends BaseExport<ReportSaleOrderDTO, SaleOrderInfoQuery> {

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Override
    public Class<? extends BaseRowModel> getClazz() {
        return ReportSaleOrderDTO.class;
    }

    @Override
    public PageBean<ReportSaleOrderDTO> getPageData(SaleOrderInfoQuery query, int page, int pageSize) throws Exception {
        List<ReportSaleOrderDTO> exportSaleOrderDTOS = new ArrayList<>();
        List<SaleOrderBtnResponseDTO> saleOrders = saleOrderInfoService.exportSaleOrderPage(query);
        for (SaleOrderBtnResponseDTO dto : saleOrders) {
            ReportSaleOrderDTO exportDTO = new ReportSaleOrderDTO();
            BeanUtils.copyProperties(dto, exportDTO);
            if(null != dto.getTicketDate()){
                exportDTO.setTicketDate(DateUtils.format(dto.getTicketDate(),"yyyy/MM/dd"));
            }
            if(dto.getTicketType().equals(0)){
                exportDTO.setTicketType("网批订单");
            }else if(dto.getTicketType().equals(1)){
                exportDTO.setTicketType("标准订单");
            }else if(dto.getTicketType().equals(2)){
                exportDTO.setTicketType("非标准订单");
            }else if(dto.getTicketType().equals(3)){
                exportDTO.setTicketType("订货计划单");
            }
            if(dto.getBuyerType().equals(LinkTypeEnum.OnlineOrder.name())){
                exportDTO.setBuyerType(LinkTypeEnum.OnlineOrder.getMsg());
            }
            if(dto.getBuyerType().equals(LinkTypeEnum.ValetOrder.name())){
                exportDTO.setBuyerType(LinkTypeEnum.ValetOrder.getMsg());
            }
            for (StatusCodeEnum value : StatusCodeEnum.values()) {
                if(dto.getStatus().equals(value.getCode())){
                    exportDTO.setStatus(value.getMsg());
                }
            }
            exportSaleOrderDTOS.add(exportDTO);
        }
        return new PageBean<>(exportSaleOrderDTOS);
    }

    @Override
    public String getFieldName() {
        String fileName = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(),"销售订单报表", DateUtils.format(new Date(),"yyyyMMdd"),5);
        log.info("----------------------------------------订单导出文件:{}------------------------",fileName);
        return fileName;
    }

    @Override
    public String getSheetName() {
        return "销售订单报表";
    }

    @Override
    public int getPageSize() {
        return 3000;
    }
}