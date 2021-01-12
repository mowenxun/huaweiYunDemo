package com.deepexi.dd.system.mall.service.app.impl;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.system.mall.service.BaseCommodityService;
import com.deepexi.dd.system.mall.service.app.CategoryBackService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.sdk.commodity.api.CategoryBackOpenApi;
import com.deepexi.sdk.commodity.model.ListBackCategoryRequestDTO;
import com.deepexi.sdk.commodity.model.ListBackCategoryResponseDTO;
import com.deepexi.sdk.commodity.model.PayloadListListBackCategoryResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.CustomerProjectApiResponse;
import domain.dto.ListCustomerProjectsResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author yp
 * @version 1.0
 * @date 2020-09-27 15:32
 */
@Service
@Slf4j
public class CategoryBackServiceImpl implements CategoryBackService {
    @Autowired
    private CategoryBackOpenApi categoryBackOpenApi;

    @Override
    public Payload<CustomerProjectApiResponse> listBackCategory(CustomerProjectApiResponse payload) {
        Optional.ofNullable(payload).orElseThrow(() -> new ApplicationException("CustomerProjectApiResponse:{}null"));
        if (!ObjectUtils.isEmpty(payload.getTypeQuantity())) {
            List<Long> ids = new ArrayList<>();
            for (ListCustomerProjectsResponseDTO.ListCustomerProjectsTypeQuantityResponseDTO typeQuantityDTO : payload.getTypeQuantity()) {
                if (!ObjectUtils.isEmpty(typeQuantityDTO.getOneCategoryId()))
                    ids.add(typeQuantityDTO.getOneCategoryId());
                if (!ObjectUtils.isEmpty(typeQuantityDTO.getTwoCategoryId()))
                    ids.add(typeQuantityDTO.getTwoCategoryId());
                if (!ObjectUtils.isEmpty(typeQuantityDTO.getThreeCategoryId()))
                    ids.add(typeQuantityDTO.getThreeCategoryId());
            }
            log.info("中台获取类目入参：query{}" + JSONObject.toJSONString(ids));
            ListBackCategoryRequestDTO query = new ListBackCategoryRequestDTO();
            query.setIds(ids);
            query.setAppId(payload.getAppId());
            query.setTenantId(payload.getTenantId());
            PayloadListListBackCategoryResponseDTO result = categoryBackOpenApi.listBackCategory(query);
            if (!"0".equals(result.getCode())) {
                log.error("result：{}中台获取类目异常");
                throw new ApplicationException(result.getMsg());
            }
            List<ListBackCategoryResponseDTO> dtoList = GeneralConvertUtils.convert2List(result.getPayload(), ListBackCategoryResponseDTO.class);
            Map<Long, String> categoryResultSet = new HashMap<Long, String>();
            for (ListBackCategoryResponseDTO listBackCategoryResponseDTO : dtoList) {
                categoryResultSet.put(listBackCategoryResponseDTO.getId(), listBackCategoryResponseDTO.getName());
            }
            for (ListCustomerProjectsResponseDTO.ListCustomerProjectsTypeQuantityResponseDTO typeQuantityDTO : payload.getTypeQuantity()) {
                for (Map.Entry<Long, String> entry : categoryResultSet.entrySet()) {
                    if (!ObjectUtils.isEmpty(typeQuantityDTO.getOneCategoryId()))
                        if (typeQuantityDTO.getOneCategoryId().equals(entry.getKey()))
                            typeQuantityDTO.setOneCategoryName(entry.getValue());
                    if (!ObjectUtils.isEmpty(typeQuantityDTO.getTwoCategoryId()))
                        if (typeQuantityDTO.getTwoCategoryId().equals(entry.getKey()))
                            typeQuantityDTO.setTwoCategoryName(entry.getValue());
                    if (!ObjectUtils.isEmpty(typeQuantityDTO.getThreeCategoryId()))
                        if (typeQuantityDTO.getThreeCategoryId().equals(entry.getKey()))
                            typeQuantityDTO.setThreeCategoryName(entry.getValue());

                }
            }
        }
        return new Payload<>(payload);
    }
}
