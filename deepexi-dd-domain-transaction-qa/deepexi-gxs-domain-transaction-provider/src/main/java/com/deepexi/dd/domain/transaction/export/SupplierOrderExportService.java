package com.deepexi.dd.domain.transaction.export;

import com.alibaba.excel.metadata.BaseRowModel;
import com.deepexi.dd.domain.common.export.BaseExport;
import com.deepexi.dd.domain.transaction.domain.dto.SupplerOrderResponseDTO;
import com.deepexi.dd.domain.transaction.domain.export.SuppliersExportDTO;
import com.deepexi.dd.domain.transaction.domain.query.SupplerOrderQuery;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsResultEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsSupplierOrderStatusEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsSupplierOrderPaymentStatusEnum;
import com.deepexi.dd.domain.transaction.service.SupplierOrderService;
import com.deepexi.util.DateUtils;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-10-15 16:07
 */
@Service
@Slf4j
public class SupplierOrderExportService extends BaseExport<SuppliersExportDTO, SupplerOrderQuery> {

    @Autowired
    private SupplierOrderService supplierOrderService;

    @Override
    public Class<? extends BaseRowModel> getClazz() {
        return SuppliersExportDTO.class;
    }

    @Override
    public PageBean<SuppliersExportDTO> getPageData(SupplerOrderQuery query, int page, int pageSize) throws Exception {
        List<SuppliersExportDTO> suppliersExportDTOS = Lists.newArrayList();
        List<SupplerOrderResponseDTO> supplerOrderResponseDTOS = supplierOrderService.listSupplerOrderResponseDTO(query);
        log.info("导出数据:{}",supplerOrderResponseDTOS);
        if(CollectionUtils.isNotEmpty(supplerOrderResponseDTOS)) {
            for (SupplerOrderResponseDTO supplerOrderResponseDTO : supplerOrderResponseDTOS) {
                SuppliersExportDTO suppliersExportDTO = new SuppliersExportDTO();
                suppliersExportDTO.setOrderCode(supplerOrderResponseDTO.getOrderCode());
                if (null != supplerOrderResponseDTO.getCreatedTime()) {
                    suppliersExportDTO.setCreatedTime(DateUtils.format(supplerOrderResponseDTO.getCreatedTime(), "yyyy/MM/dd HH:mm:ss"));
                }
                suppliersExportDTO.setQuantity(supplerOrderResponseDTO.getQuantity());
                suppliersExportDTO.setReceiveDistributionName(supplerOrderResponseDTO.getReceiveDistributionName());
                suppliersExportDTO.setSellerName(supplerOrderResponseDTO.getSellerName());
                suppliersExportDTO.setTotalAmount(supplerOrderResponseDTO.getTotalAmount());
                List<String> shopOrderCodes = supplerOrderResponseDTO.getShopOrderCodes();
                if (CollectionUtils.isNotEmpty(shopOrderCodes)) {
                    StringBuilder shopOrderCode = new StringBuilder();
                    for (int i = 0; i < shopOrderCodes.size(); i++) {
                        shopOrderCode.append(shopOrderCodes.get(i));
                        if (i < shopOrderCodes.size() - 1) {
                            shopOrderCode.append("、");
                        }
                    }
                    suppliersExportDTO.setShopOrderCodes(shopOrderCode.toString());
                }
                //订单状态
                if (null != supplerOrderResponseDTO.getStatus()) {
                    suppliersExportDTO.setStatus(GxsSupplierOrderStatusEnum.fromCode(supplerOrderResponseDTO.getStatus()).getChineseName());
                }
                //支付状态
                if (null != supplerOrderResponseDTO.getPaymentStatus()) {
                    suppliersExportDTO.setPaymentStatus(GxsSupplierOrderPaymentStatusEnum.fromCode(supplerOrderResponseDTO.getPaymentStatus()+"").getChineseName());
                }

                suppliersExportDTOS.add(suppliersExportDTO);
            }
        }else{
//            throw new ApplicationException(GxsResultEnum.NOT_DATA);
            suppliersExportDTOS.add(new SuppliersExportDTO());
        }
        return new PageBean<>(suppliersExportDTOS);
    }

    @Override
    public String getFieldName() {
        return "收货订单";
    }

    @Override
    public String getSheetName() {
        return "收货订单";
    }

    @Override
    public int getPageSize() {
        return 1048576;
    }
}
