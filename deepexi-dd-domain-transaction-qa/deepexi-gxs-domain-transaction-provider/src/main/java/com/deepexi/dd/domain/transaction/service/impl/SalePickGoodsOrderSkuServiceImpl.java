package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderSkuRespDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsOrderClient;
import com.deepexi.dd.domain.transaction.remote.order.SalePickGoodsOrderSkuClient;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsOrderSkuService;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-15 14:27
 */
@Service
@Slf4j
public class SalePickGoodsOrderSkuServiceImpl implements SalePickGoodsOrderSkuService {
    @Autowired
    SalePickGoodsOrderSkuClient salePickGoodsOrderSkuClient;
    @Autowired
    SalePickGoodsInfoClient salePickGoodsInfoClient;
    @Autowired
    SalePickGoodsOrderClient salePickGoodsOrderClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean<SalePickGoodsOrderSkuRespDTO> getPageList(SalePickGoodsOrderSkuQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        if (!appRuntimeEnv.getUserOrganization().getId().equals(appRuntimeEnv.getTopOrganization().getId())) {
            query.setAscriptionOrgId(appRuntimeEnv.getUserOrganization().getId());
        }
        query.setStatus(6);//查询待发货列表--商品维度
        PageBean<SalePickGoodsOrderSkuResponseDTO> salePickGoodsOrderSkuResponseDTOPageBean = salePickGoodsOrderSkuClient.listSalePickGoodsOrderSkusPage(query.clone(SalePickGoodsOrderSkuRequestQuery.class));
        if (Objects.isNull(salePickGoodsOrderSkuResponseDTOPageBean) || Objects.isNull(salePickGoodsOrderSkuResponseDTOPageBean.getContent()) || salePickGoodsOrderSkuResponseDTOPageBean.getContent().size() <= 0) {
            return null;
        }
        PageBean<SalePickGoodsOrderSkuRespDTO> salePickGoodsOrderSkuRespDTOPageBean = ObjectCloneUtils.convertPageBean(salePickGoodsOrderSkuResponseDTOPageBean,
                                                                                                                       SalePickGoodsOrderSkuRespDTO.class, CloneDirection.OPPOSITE);
        for (SalePickGoodsOrderSkuRespDTO salePickGoodsOrderSkuRespDTO : salePickGoodsOrderSkuRespDTOPageBean.getContent()) {
            SalePickGoodsInfoResponseDTO salePickGoodsInfoResponseDTO = salePickGoodsInfoClient.selectById(salePickGoodsOrderSkuRespDTO.getPickGoodsInfoId());
            if (!Objects.isNull(salePickGoodsInfoResponseDTO)) {
                salePickGoodsInfoResponseDTO.clone(salePickGoodsOrderSkuRespDTO);
            }
            SalePickGoodsOrderResponseDTO salePickGoodsOrderResponseDTO = salePickGoodsOrderClient.selectById(salePickGoodsOrderSkuRespDTO.getPickGoodsOrderId());
            if (!Objects.isNull(salePickGoodsOrderResponseDTO)) {
                salePickGoodsOrderResponseDTO.clone(salePickGoodsOrderSkuRespDTO);
            }
        }
        return salePickGoodsOrderSkuRespDTOPageBean;
    }
}
