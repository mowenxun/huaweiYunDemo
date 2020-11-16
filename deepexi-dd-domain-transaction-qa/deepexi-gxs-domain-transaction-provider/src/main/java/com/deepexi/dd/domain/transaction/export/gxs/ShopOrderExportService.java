package com.deepexi.dd.domain.transaction.export.gxs;

import com.alibaba.excel.metadata.BaseRowModel;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.export.BaseExport;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.transaction.domain.export.gxs.ExportShopOrderDTO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.enums.ShopOrderPlatStatusEnum;
import com.deepexi.dd.domain.transaction.enums.ShopOrderStatusEnum;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsResultEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.OrderConsigneeAddressDomainRemote;
import com.deepexi.dd.domain.transaction.service.ShopOrderDomainService;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeAddressResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeAddressRequestQuery;
import com.deepexi.util.DateUtils;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ShopOrderExportService extends BaseExport<ExportShopOrderDTO, ShopOrderDomainRequestQuery> {

    @Autowired
    private IdentifierGenerator identifierGenerator;


    private static final String SUCCESS = "0";

    @Autowired
    private ShopOrderDomainService shopOrderDomainService;

    @Autowired
    private OrderConsigneeAddressDomainRemote orderConsigneeAddressDomainRemote;


    @Override
    public Class<? extends BaseRowModel> getClazz() {
        return ExportShopOrderDTO.class;
    }

    @Override
    public PageBean<ExportShopOrderDTO> getPageData(ShopOrderDomainRequestQuery query, int page, int pageSize) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<ExportShopOrderDTO> exportShopOrderDTOS = new ArrayList<>();
        query.setSize(10000);
        PageBean<PlatformShopOrderDomainResponseDTO> pageBean =
                shopOrderDomainService.listPlatformShopOrdersPage(query);
        if (pageBean != null && pageBean.getContent() != null && pageBean.getContent().size() != 0) {
            pageBean.getContent().forEach(item -> {
                ExportShopOrderDTO exportShopOrderDTO = new ExportShopOrderDTO();
                exportShopOrderDTO = item.clone(ExportShopOrderDTO.class);
                OrderConsigneeAddressRequestQuery orderConsigneeAddressRequestQuery =
                        new OrderConsigneeAddressRequestQuery();
                orderConsigneeAddressRequestQuery.setOrderId(item.getId());
                List<OrderConsigneeAddressResponseDTO> list =
                        orderConsigneeAddressDomainRemote.listOrderConsigneeAddresss(orderConsigneeAddressRequestQuery);
                if (CollectionUtils.isNotEmpty(list)) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(list.get(0).getProvinceName());
                    sb.append(list.get(0).getCityName());
                    sb.append(list.get(0).getAreaName());
                    sb.append(list.get(0).getStreetName());
                    sb.append(list.get(0).getDetailedAddress());
                    exportShopOrderDTO.setAddress(sb.toString());
                    exportShopOrderDTO.setConsignee(list.get(0).getConsignee());
                    exportShopOrderDTO.setMobile(list.get(0).getMobile());
                }
                exportShopOrderDTO.setCreatedTime(sdf.format(item.getCreatedTime()));

                //下属门店订单状态处理
                if (query.getParentShopId()!=null&&query.getParentShopId()>0L) {
                    exportShopOrderDTO.setStatusName(ShopOrderStatusEnum.getOrderStatusEnum(item.getStatus()) == null ?
                            "" :
                            ShopOrderStatusEnum.getOrderStatusEnum(item.getStatus()).getDesc());
                } else {
                    //平台端的门店订单状态处理
                    exportShopOrderDTO.setStatusName(ShopOrderPlatStatusEnum.getOrderStatusEnum(item.getPlatOrderStatus()) == null ?
                            "" :
                            ShopOrderPlatStatusEnum.getOrderStatusEnum(item.getPlatOrderStatus()).getDesc());
                }
                if (item.getShopSupplerRelationResponseDTO() != null) {
                    exportShopOrderDTO.setSupplierOrderCode(item.getShopSupplerRelationResponseDTO().getSupplerOrderCode());
                }
                exportShopOrderDTOS.add(exportShopOrderDTO);
            });
        }else{
            exportShopOrderDTOS.add(new ExportShopOrderDTO());
           // throw new ApplicationException(GxsResultEnum.NOT_DATA);
        }
        return new PageBean<>(exportShopOrderDTOS);
    }

    @Override
    public String getFieldName() {
        String fileName = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER.getType(), "shopOrder",
                DateUtils.format(new Date(), "yyyyMMdd"), 5);
        log.info("----------------------------------------店铺订单导出文件:{}------------------------", fileName);
        return fileName;
    }

    @Override
    public String getSheetName() {
        return "店铺订单";
    }

    @Override
    public int getPageSize() {
        return 8000;
    }
}
