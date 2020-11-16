package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.SaleDeliveryPlanInfoDetail;
import com.deepexi.dd.domain.transaction.domain.dto.SaleDeliveryPlanInfoResDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleDeliveryPlanItemRespDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleDeliveryPlanInfoRequest;
import com.deepexi.dd.domain.transaction.remote.order.SaleDeliveryPlanInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleDeliveryPlanItemClient;
import com.deepexi.dd.domain.transaction.service.SaleDeliveryPlanInfoService;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleDeliveryPlanItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleDeliveryPlanItemRequestQuery;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.AbstractObject;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-13 21:21
 */
@Service
@Slf4j
public class SaleDeliveryPlanInfoServiceImpl implements SaleDeliveryPlanInfoService {

    @Autowired
    SaleDeliveryPlanInfoClient saleDeliveryPlanInfoClient;

    @Autowired
    SaleDeliveryPlanItemClient saleDeliveryPlanItemClient;

    @Override
    public PageBean<SaleDeliveryPlanInfoDetail> getSaleDeliveryPlanInfoDetails(SaleDeliveryPlanInfoRequest query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        PageBean<SaleDeliveryPlanInfoResponseDTO> saleDeliveryPlanInfoResponseDTOPageBean = saleDeliveryPlanInfoClient.listSaleDeliveryPlanInfosPage(query.clone(SaleDeliveryPlanInfoRequestQuery.class));
        Optional.ofNullable(saleDeliveryPlanInfoResponseDTOPageBean).orElseThrow(() -> new ApplicationException("发货计划为空"));
        List<SaleDeliveryPlanInfoDetail> resDetail = new ArrayList<>();
        for (SaleDeliveryPlanInfoResponseDTO saleDeliveryPlanInfoResponseDTO : saleDeliveryPlanInfoResponseDTOPageBean.getContent()) {
            SaleDeliveryPlanInfoDetail detail = new SaleDeliveryPlanInfoDetail();
            detail.setSaleDeliveryPlanInfoResponseDTO(saleDeliveryPlanInfoResponseDTO.clone(SaleDeliveryPlanInfoResDTO.class));
            SaleDeliveryPlanItemRequestQuery itemRequestQuery = new SaleDeliveryPlanItemRequestQuery();
            itemRequestQuery.setSaleDeliveryPlanId(saleDeliveryPlanInfoResponseDTO.getId());
            itemRequestQuery.setSaleDeliveryPlanCode(saleDeliveryPlanInfoResponseDTO.getCode());
            List<SaleDeliveryPlanItemResponseDTO> saleDeliveryPlanItemResponseDTOS = saleDeliveryPlanItemClient.listSaleDeliveryPlanItems(itemRequestQuery);
            if (!org.springframework.util.ObjectUtils.isEmpty(saleDeliveryPlanItemResponseDTOS)) {
                detail.setSaleDeliveryPlanItemRespDTOS(GeneralConvertUtils.convert2List(saleDeliveryPlanItemResponseDTOS, SaleDeliveryPlanItemRespDTO.class));
            }
            resDetail.add(detail);
        }

        PageBean<SaleDeliveryPlanInfoDetail> result = ObjectCloneUtils.convertPageBean(saleDeliveryPlanInfoResponseDTOPageBean,
                                                                                       SaleDeliveryPlanInfoDetail.class, CloneDirection.OPPOSITE);
        result.setContent(resDetail);
        return result;
    }

}
