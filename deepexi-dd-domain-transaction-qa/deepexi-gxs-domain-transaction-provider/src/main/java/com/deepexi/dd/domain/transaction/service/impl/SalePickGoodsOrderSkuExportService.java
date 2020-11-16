package com.deepexi.dd.domain.transaction.service.impl;

import com.alibaba.excel.metadata.BaseRowModel;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.export.BaseExport;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderSkuRespDTO;
import com.deepexi.dd.domain.transaction.domain.export.ExportSalePickGoodsOrderSkuDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.domain.transaction.enums.SalePickGoodsEnum;
import com.deepexi.dd.domain.transaction.remote.order.MerchantClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsOrderClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsOrderSkuClient;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.util.DateUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import domain.dto.MerchantResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-17 7:54
 */
@Service
@Slf4j
public class SalePickGoodsOrderSkuExportService extends BaseExport<ExportSalePickGoodsOrderSkuDTO,
        SalePickGoodsOrderSkuQuery> {
    @Autowired
    private IdentifierGenerator identifierGenerator;
    @Autowired
    SalePickGoodsOrderSkuClient salePickGoodsOrderSkuClient;
    @Autowired
    SalePickGoodsInfoClient salePickGoodsInfoClient;
    @Autowired
    SalePickGoodsOrderClient salePickGoodsOrderClient;

    @Autowired
    MerchantClient merchantClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public Class<? extends BaseRowModel> getClazz() {
        return ExportSalePickGoodsOrderSkuDTO.class;
    }

    @Override
    public PageBean<ExportSalePickGoodsOrderSkuDTO> getPageData(SalePickGoodsOrderSkuQuery query, int page,
                                                                int pageSize) throws Exception {
        if (!appRuntimeEnv.getUserOrganization().getId().equals(appRuntimeEnv.getTopOrganization().getId())) {
            query.setAscriptionOrgId(appRuntimeEnv.getUserOrganization().getId());
        }
        query.setStatus(6);//查询待发货列表--商品维度
        List<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuResponseDTOS =
                salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkus(query.clone(SalePickGoodsOrderSkuRequestQuery.class));
        if (org.springframework.util.ObjectUtils.isEmpty(salePickGoodsOrderSkuResponseDTOS)) {
            return null;
        }
        List<ExportSalePickGoodsOrderSkuDTO> res = new ArrayList<>();
        List<SalePickGoodsOrderSkuRespDTO> salePickGoodsOrderSkuRespDTOS =
                ObjectCloneUtils.convertList(salePickGoodsOrderSkuResponseDTOS, SalePickGoodsOrderSkuRespDTO.class,
                        CloneDirection.OPPOSITE);
        for (SalePickGoodsOrderSkuRespDTO salePickGoodsOrderSkuRespDTO : salePickGoodsOrderSkuRespDTOS) {
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO =
                    salePickGoodsInfoClient.selectById(salePickGoodsOrderSkuRespDTO.getPickGoodsInfoId());
            SalePickGoodsOrderResponseDTO salePickGoodsOrderResponseDTO =
                    salePickGoodsOrderClient.selectById(salePickGoodsOrderSkuRespDTO.getPickGoodsOrderId());
            ExportSalePickGoodsOrderSkuDTO build = buildReturnData(salePickGoodsOrderSkuRespDTO,
                    salePickGoodsInfoResponseDTO, salePickGoodsOrderResponseDTO);
            res.add(build);
        }
        return new PageBean<>(res);
    }

    public ExportSalePickGoodsOrderSkuDTO buildReturnData(SalePickGoodsOrderSkuRespDTO salePickGoodsOrderSkuRespDTO,
                                                          SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO,
                                                          SalePickGoodsOrderResponseDTO salePickGoodsOrderResponseDTO) throws Exception {
        ExportSalePickGoodsOrderSkuDTO exportSalePickGoodsOrderSkuDTO = new ExportSalePickGoodsOrderSkuDTO();
        if (!ObjectUtils.isEmpty(salePickGoodsOrderSkuRespDTO)) {
            exportSalePickGoodsOrderSkuDTO.setSkuCode(salePickGoodsOrderSkuRespDTO.getSkuCode());
            exportSalePickGoodsOrderSkuDTO.setSkuName(salePickGoodsOrderSkuRespDTO.getSkuName());
            exportSalePickGoodsOrderSkuDTO.setSkuFormat(salePickGoodsOrderSkuRespDTO.getSkuFormat());
            exportSalePickGoodsOrderSkuDTO.setPickNum(salePickGoodsOrderSkuRespDTO.getPickNum());
            exportSalePickGoodsOrderSkuDTO.setWarehouse(salePickGoodsOrderSkuRespDTO.getWarehouse());
            String ifEager = "否";
            if ("YES".equals(salePickGoodsOrderSkuRespDTO.getIfEager())) {
                ifEager = "是";
            }
            exportSalePickGoodsOrderSkuDTO.setIfEager(ifEager);

            //exportSalePickGoodsOrderSkuDTO.setBatchNumber();//暂不维护批次号
        }
        if (!ObjectUtils.isEmpty(salePickGoodsInfoResponseDTO)) {
            //获得客户信息
            if (salePickGoodsInfoResponseDTO.getCustomerId() != null) {
                Payload<MerchantResponseDTO> customerPay =
                        merchantClient.detail(salePickGoodsInfoResponseDTO.getCustomerId());
                if(customerPay!=null&&customerPay.getPayload()!=null){
                    MerchantResponseDTO merchantResponseDTO= GeneralConvertUtils.conv(customerPay.getPayload(),MerchantResponseDTO.class);
                    exportSalePickGoodsOrderSkuDTO.setCustomerCode(merchantResponseDTO.getCode());
                    exportSalePickGoodsOrderSkuDTO.setCustomerName(merchantResponseDTO.getName());
                }
            }
            exportSalePickGoodsOrderSkuDTO.setRequiredTime(salePickGoodsInfoResponseDTO.getRequiredTime());
            exportSalePickGoodsOrderSkuDTO.setReceiveAddress(salePickGoodsInfoResponseDTO.getReceiveAddress());
            exportSalePickGoodsOrderSkuDTO.setConsignee(salePickGoodsInfoResponseDTO.getReceiveGoodsMan());
            exportSalePickGoodsOrderSkuDTO.setPickGoodsCode(salePickGoodsInfoResponseDTO.getPickGoodsCode());
            exportSalePickGoodsOrderSkuDTO.setReceiveHouseNameWc(salePickGoodsInfoResponseDTO.getReceiveHouseNameWc());
            String pickGoodsWayZt = "";
            if (SalePickGoodsEnum.OUTER_WAREHOUSE.getCode().equals(salePickGoodsInfoResponseDTO.getPickGoodsWayZt())) {
                pickGoodsWayZt = SalePickGoodsEnum.OUTER_WAREHOUSE.getMsg();
            } else if (SalePickGoodsEnum.PICK_MYSELF.getCode().equals(salePickGoodsInfoResponseDTO.getPickGoodsWayZt())) {
                pickGoodsWayZt = SalePickGoodsEnum.PICK_MYSELF.getMsg();

            } else if (SalePickGoodsEnum.BUILD_PLACE.getCode().equals(salePickGoodsInfoResponseDTO.getPickGoodsWayZt())) {
                pickGoodsWayZt = SalePickGoodsEnum.BUILD_PLACE.getMsg();

            } else if (SalePickGoodsEnum.SMALL_WAREHOUSE.getCode().equals(salePickGoodsInfoResponseDTO.getPickGoodsWayZt())) {
                pickGoodsWayZt = SalePickGoodsEnum.SMALL_WAREHOUSE.getMsg();

            }
            exportSalePickGoodsOrderSkuDTO.setPickGoodsWayZt(pickGoodsWayZt);
        }
        if (!ObjectUtils.isEmpty(salePickGoodsOrderResponseDTO)) {
            exportSalePickGoodsOrderSkuDTO.setSaleOrderCode(salePickGoodsOrderResponseDTO.getSaleOrderCode());
        }
        return exportSalePickGoodsOrderSkuDTO;
    }

    @Override
    public String getFieldName() {
        String fileName = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER_ITEM.getType(), "提货计划",
                DateUtils.format(new Date(), "yyyyMMdd"), 5);
        log.info("----------------------------------------计划编排导出文件:{}------------------------", fileName);
        return fileName;
    }

    @Override
    public String getSheetName() {
        return "提货计划";
    }

    @Override
    public int getPageSize() {
        return 3000;
    }
}

