package com.deepexi.dd.domain.transaction.service.common;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.CompanyInfoDomainClient;
import com.deepexi.dd.domain.transaction.remote.gxs.GxsManagementRemoteService;
import com.deepexi.dd.domain.transaction.util.ServiceUtil;
import com.deepexi.middle.merchant.domain.dto.GxsManagementResponseDTO;
import com.deepexi.sdk.commodity.api.ShopItemOpenApi;
import com.deepexi.sdk.commodity.model.ListShopItemUpShelfRequestDTO;
import com.deepexi.sdk.commodity.model.ListShopItemUpShelfResponseDTO;
import com.deepexi.sdk.commodity.model.PayloadListListShopItemUpShelfResponseDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.CompanyInfoResponseApiDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author yangwu
 * @date 2020/7/8
 */
@Component
@Slf4j
public class CommonService {
    @Resource
    private ShopItemOpenApi shopItemOpenApi;
    @Autowired
    GxsManagementRemoteService gxsManagementRemoteService;
    @Autowired
    CompanyInfoDomainClient companyInfoDomainClient;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;
    private static final String LOG_KEY = "公共业务处理";

    /**
     * 查询商品sku详情
     *
     * @param request 请求参数
     * @return 商品sku详情
     */
    public List<ListShopItemUpShelfResponseDTO> searchCommoditySkuInfo(ListShopItemUpShelfRequestDTO request) {
        try {
            log.info("{}-->商品查询，请求request参数：{}", LOG_KEY, JSON.toJSONString(request));
            PayloadListListShopItemUpShelfResponseDTO payloadListListShopItemUpShelfResponseDTO = shopItemOpenApi.listShopItemUpShelf(request);
            log.info("{}-->商品查询，返回response结果：{}", LOG_KEY, JSON.toJSONString(payloadListListShopItemUpShelfResponseDTO));
            if (payloadListListShopItemUpShelfResponseDTO.getCode().equals("0")) {
                return payloadListListShopItemUpShelfResponseDTO.getPayload();
            } else {
                throw new Exception(payloadListListShopItemUpShelfResponseDTO.getMsg());
            }
        } catch (Exception e) {
            log.error("{}-->商品查询异常，参数：{},异常信息：{}", LOG_KEY, JSON.toJSONString(request), e);
            throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
        }
    }


    public GxsManagementResponseDTO queryOrg(Long orgId) {
        return doQueryOrg(orgId, false);
    }

    public GxsManagementResponseDTO getShopById(Long id) {
        Object payLoad = ServiceUtil.getPayLoad(gxsManagementRemoteService.selectById(id));
        return payLoad == null ? null : GeneralConvertUtils.conv(payLoad, GxsManagementResponseDTO.class);
    }

    public GxsManagementResponseDTO queryOrgThrowIfNotFound(Long orgId) {
        return doQueryOrg(orgId, true);
    }

    private GxsManagementResponseDTO doQueryOrg(Long orgId, boolean needThrow) {
        List<Long> ids = Collections.singletonList(orgId);
        Payload<List<GxsManagementResponseDTO>> rs = gxsManagementRemoteService.listGxsManagementByGroupIds(ids);
        List<GxsManagementResponseDTO> payLoad = ServiceUtil.getPayLoadList(rs);
        if (CollectionUtil.isEmpty(payLoad)) {
            if (needThrow) {
                throw new IllegalArgumentException("根据组织id:" + orgId + " 没有查到门店信息");
            }
            return null;
        }
        return GeneralConvertUtils.conv(payLoad.get(0), GxsManagementResponseDTO.class);
    }

    public CompanyInfoResponseApiDTO queryCurrentLoginSeller(Long orgId) {
        try {
            Payload<List<CompanyInfoResponseApiDTO>> listPayload = companyInfoDomainClient.listCompanyInfosByCompanyIds(Collections.singletonList(orgId));
            List<CompanyInfoResponseApiDTO> payLoadList = ServiceUtil.getPayLoadList(listPayload);
            if (CollUtil.isEmpty(payLoadList)) {
                throw new IllegalStateException("错误：根据组织id：" + orgId + " 没有查询到供应商信息");
            }
            return GeneralConvertUtils.conv(payLoadList.get(0), CompanyInfoResponseApiDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
