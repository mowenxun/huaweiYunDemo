package com.deepexi.dd.system.mall.service.common.impl;

import api.MerchantApi;
import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.common.domain.dto.CommonMainImageSettingsResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonMainImageSettingsRequestQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.system.mall.remote.common.MainImageSettingsRemote;
import com.deepexi.dd.system.mall.remote.customer.BusinessPartnerClient;
import com.deepexi.dd.system.mall.remote.customer.MerchantClient;
import com.deepexi.dd.system.mall.service.common.MainImageSettingsService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.BaseResponseDTO;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.MerchantResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import domain.query.MerchantRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MainImageSettingsServiceImpl implements MainImageSettingsService {
    private static final String SUCCESS = "0";
    @Autowired
    MainImageSettingsRemote mainImageSettingsRemote;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private BusinessPartnerClient businessPartnerClient;

    /**
     * 首页
     */
    public static final Integer FIRST_PAGE = 0;

    /**
     * 区域
     */
    public static final Integer AREA = 1;

    @Override
    public List<CommonMainImageSettingsResponseDTO> listCommonMainImageSettingssPage(CommonMainImageSettingsRequestQuery query) throws Exception {
        query.setIsSort(Boolean.TRUE);
        if(AREA.equals(query.getType())){
            log.info("listCommonMainImageSettingssP-2");
            // 查询供应商
            BusinessPartnerRequestQuery requestQuery = new BusinessPartnerRequestQuery();
            requestQuery.setAppId(query.getAppId());
            requestQuery.setTenantId(query.getTenantId());
            requestQuery.setOrgId(Long.valueOf(query.getIsolationId()));
            Payload<BusinessPartnerResponseDTO> payload = businessPartnerClient.getPartner(requestQuery);
            log.info("businessPartnerClient-result:"+payload);
            BusinessPartnerResponseDTO partner = GeneralConvertUtils.conv(payload.getPayload(), BusinessPartnerResponseDTO.class);
            if(partner!=null){
                log.info("in-partner:");
                MerchantRequestQuery merchantRequestQuery = new MerchantRequestQuery();
                merchantRequestQuery.setAppId(query.getAppId());
                merchantRequestQuery.setTenantId(query.getTenantId());
                merchantRequestQuery.setPartnerId(partner.getId());
                Payload<List<MerchantResponseDTO>> supplierPayload = merchantClient.findList(merchantRequestQuery);
                List<MerchantResponseDTO> suppliers = GeneralConvertUtils.convert2List(supplierPayload.getPayload(), MerchantResponseDTO.class);
                List<Long> supplierOrgIds = suppliers.stream().map(BaseResponseDTO::getIsolationId).filter(f->f!=null).map(Long::parseLong).collect(Collectors.toList());
                query.setSupplierIds(supplierOrgIds);
            }

        }
        query.setIsolationId(null);
        log.info("mainImageSettingsRemote.listCommonMainImageSettingss-begin");
        Payload<List<CommonMainImageSettingsResponseDTO>> result = mainImageSettingsRemote.listCommonMainImageSettingss(query);
        log.info("mainImageSettingsRemote.listCommonMainImageSettingss-reslt"+ JSON.toJSONString(result));
        return GeneralConvertUtils.convert2List(result.getPayload(), CommonMainImageSettingsResponseDTO.class);
    }

    @Override
    public CommonMainImageSettingsResponseDTO selectById(Long id) throws Exception {
        Payload<CommonMainImageSettingsResponseDTO> payload = mainImageSettingsRemote.selectById(id);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                CommonMainImageSettingsResponseDTO result = GeneralConvertUtils.conv(payload.getPayload(), CommonMainImageSettingsResponseDTO.class);
                return result;
            } catch (Exception e) {
                log.error("获取广告详情失败", e);
                throw new ApplicationException("获取广告详情失败");
            }
        } else {
            throw new ApplicationException(payload.getMsg());
        }
    }
}
