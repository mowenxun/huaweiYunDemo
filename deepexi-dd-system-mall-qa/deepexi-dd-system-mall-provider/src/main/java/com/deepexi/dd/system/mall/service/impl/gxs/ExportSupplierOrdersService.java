package com.deepexi.dd.system.mall.service.impl.gxs;

import com.alibaba.excel.metadata.BaseRowModel;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.export.BaseExport;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListSupplierOrderRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListSupplierOrderResponseDTO;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsSupplierOrderPaymentStatusEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsSupplierOrderStatusEnum;
import com.deepexi.dd.system.mall.domain.gxs.ExportListSupplierOrderResponseDTO;
import com.deepexi.dd.system.mall.service.gxs.SupplierOrderMallService;
import com.deepexi.util.DateUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class ExportSupplierOrdersService extends BaseExport<ExportListSupplierOrderResponseDTO,
        ListSupplierOrderRequestDTO> {


    @Autowired
    IdentifierGenerator identifierGenerator;
    @Autowired
    SupplierOrderMallService supplierOrderService;
    @Value("${plat.customer.name:威宁县供销社}")
    String customerName;

    @Override
    public Class<? extends BaseRowModel> getClazz() {
        return ExportListSupplierOrderResponseDTO.class;
    }

    @Override
    public PageBean<ExportListSupplierOrderResponseDTO> getPageData(ListSupplierOrderRequestDTO query, int page,
                                                                    int pageSize) throws Exception {
        query.setSize(pageSize);
        query.setPage(page);
        PageBean<ListSupplierOrderResponseDTO> pageBean =
                supplierOrderService.listSupplierOrdersPage(query.clone(ListSupplierOrderRequestDTO.class, 1));
        PageBean<ExportListSupplierOrderResponseDTO> r = ObjectCloneUtils.convertPageBean(pageBean,
                ExportListSupplierOrderResponseDTO.class, 1);
        if (pageBean == null || CollectionUtils.isEmpty(pageBean.getContent())) {
            List<ExportListSupplierOrderResponseDTO> listSupplierOrderResponseDTOS = new ArrayList<>();
            listSupplierOrderResponseDTOS.add(new ExportListSupplierOrderResponseDTO());
            r.setContent(listSupplierOrderResponseDTOS);
            return r;
        }
        for (int i = 0; i < r.getContent().size(); i++) {
            ExportListSupplierOrderResponseDTO dto = r.getContent().get(i);
            ListSupplierOrderResponseDTO source = pageBean.getContent().get(i);
            dto.setCustomerName(customerName);
            dto.setStatus(GxsSupplierOrderStatusEnum.fromCode(source.getStatus()).getChineseName());
            dto.setPaymentStatus(GxsSupplierOrderPaymentStatusEnum.fromCode(String.valueOf(source.getPaymentStatus())).getChineseName());
        }
        return r;
    }

    @Override
    public String getFieldName() {
        String fileName = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(), "订单导出",
                DateUtils.format(new Date(), "yyyyMMdd"), 5);
        log.info("----------------------------------------供销社供应商订单导出文件:{}------------------------", fileName);
        return fileName;
    }

    @Override
    public String getSheetName() {
        return "订单导出";
    }

    @Override
    public int getPageSize() {
        return 10000;
    }
}