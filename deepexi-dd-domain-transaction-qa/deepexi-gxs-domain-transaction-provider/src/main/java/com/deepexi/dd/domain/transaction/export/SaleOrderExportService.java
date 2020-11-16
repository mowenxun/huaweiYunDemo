package com.deepexi.dd.domain.transaction.export;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.export.BaseExport;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.tool.enums.LinkTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.domain.dto.OrderConsigneeInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderBtnResponseDTO;
import com.deepexi.dd.domain.transaction.domain.export.ExportSaleOrderDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.export.SaleOrderQueryPage;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.DateUtils;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SaleOrderExportService extends BaseExport<ExportSaleOrderDTO, SaleOrderInfoQuery> {
    @Autowired
    private SaleOrderInfoService saleOrderInfoService;
    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Override
    public Class<? extends BaseRowModel> getClazz() {
        return ExportSaleOrderDTO.class;
    }

    @Override
    public PageBean<ExportSaleOrderDTO> getPageData(SaleOrderInfoQuery query, int page, int pageSize) throws Exception {
        List<ExportSaleOrderDTO> exportSaleOrderDTOS = new ArrayList<>();
        List<SaleOrderBtnResponseDTO> saleOrders = saleOrderInfoService.exportSaleOrderPage(query);
        for (SaleOrderBtnResponseDTO dto : saleOrders) {
            ExportSaleOrderDTO exportDTO = new ExportSaleOrderDTO();
            BeanUtils.copyProperties(dto, exportDTO);
            if(null != dto.getTicketDate()){
                exportDTO.setTicketDate(DateUtils.format(dto.getTicketDate(),"yyyy/MM/dd"));
            }
            if(null != dto.getArriveDate()){
                exportDTO.setArriveDate(DateUtils.format(dto.getArriveDate(),"yyyy/MM/dd"));
            }
            if(dto.getTicketType().equals(0)){
                exportDTO.setTicketType("直供订单");
            }else if(dto.getTicketType().equals(1)){
                exportDTO.setTicketType("标准订单");
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
                if(dto.getPaymentStatus().equals(value.getCode())){
                    exportDTO.setPaymentStatus(value.getMsg());
                }
            }
            OrderConsigneeInfoResponseDTO adressDTO = dto.getOrderConsigneeInfo();
            if (null != adressDTO) {
                exportDTO.setConsignee(adressDTO.getConsignee());
                exportDTO.setMobile(adressDTO.getMobile());
                String adress = adressDTO.getProvinceName() + adressDTO.getCityName() + adressDTO.getStreetName();
                if(adressDTO.getSignTime() != null){
                    exportDTO.setSignTime(DateUtils.format(adressDTO.getSignTime(),"yyyy/MM/dd"));
                }
                exportDTO.setAdress(adress);
            }
            exportSaleOrderDTOS.add(exportDTO);
        }
        return new PageBean<>(exportSaleOrderDTOS);
    }

    @Override
    public String getFieldName() {
        String fileName = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(),"销售订单", DateUtils.format(new Date(),"yyyyMMdd"),5);
        log.info("----------------------------------------订单导出文件:{}------------------------",fileName);
        return fileName;
    }

    @Override
    public String getSheetName() {
        return "销售订单";
    }

    @Override
    public int getPageSize() {
        return 3000;
    }
}
