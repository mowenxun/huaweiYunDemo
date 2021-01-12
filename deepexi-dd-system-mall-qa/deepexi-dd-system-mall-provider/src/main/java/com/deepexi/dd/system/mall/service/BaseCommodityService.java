package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.business.domain.dto.BusinessSkuPriceAdminResponsDTO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.middle.tool.domain.dto.ToolCustomerDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolAuthorizeCommodityInfoQuery;
import com.deepexi.dd.system.mall.domain.query.app.AuthorizeSkuInfo;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.remote.commodity.ToolCommoditytypeAuthorizeApiRemote;
import com.deepexi.dd.system.mall.remote.customer.BusinessPartnerClient;
import com.deepexi.dd.system.mall.remote.customer.MerchantClient;
import com.deepexi.dd.system.mall.remote.customer.StoreClient;
import com.deepexi.dd.system.mall.remote.tool.ToolAuthorizeClient;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.sdk.commodity.api.SkuOpenApi;
import com.deepexi.sdk.commodity.model.ListAllSkuRequestDTO;
import com.deepexi.sdk.commodity.model.ListAllSkuResponseDTO;
import com.deepexi.sdk.commodity.model.PayloadListListAllSkuResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.MerchantResponseDTO;
import domain.dto.StoreResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import domain.query.MerchantRequestQuery;
import domain.query.StoreRequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName BaseCommodityService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-09-24
 * @Version 1.0
 **/
@Component
public class BaseCommodityService {

    @Autowired
    protected AppRuntimeEnv appRuntimeEnv;

    @Autowired
    protected BusinessPartnerClient partnerClient;

    @Autowired
    protected StoreClient storeClient;

    @Autowired
    protected MerchantClient merchantClient;

    @Autowired
    protected ToolAuthorizeClient toolAuthorizeClient;

    @Autowired
    protected ToolCommoditytypeAuthorizeApiRemote apiRemote;

    @Autowired
    protected SkuOpenApi skuOpenApi;

    // 格力组织编码.
    protected static final String ORG_CODE = "gree-deparent";

    /**
     * @Description: 获取当前用户的业务伙伴信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    protected BusinessPartnerResponseDTO getPartner(String orgId, Long appId) throws Exception {
        BusinessPartnerRequestQuery query = new BusinessPartnerRequestQuery();
        query.setTenantId(appRuntimeEnv.getTenantId());
        query.setOrgId(Long.valueOf(orgId));
        query.setAppId(appId);
        Payload<BusinessPartnerResponseDTO> payload = partnerClient.getPartner(query);
        return GeneralConvertUtils.conv(payload.getPayload(), BusinessPartnerResponseDTO.class);
    }

    /**
     * @Description: 查询业务伙伴.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/25
     */
    protected List<MerchantResponseDTO> getCustomer(Long partnerId, Long appId) throws Exception {
        MerchantRequestQuery merchantRequestQuery = new MerchantRequestQuery();
        merchantRequestQuery.setPartnerId(partnerId);
        merchantRequestQuery.setTenantId(appRuntimeEnv.getTenantId());
        merchantRequestQuery.setAppId(appRuntimeEnv.getAppId());
        List<MerchantResponseDTO> merchantList = GeneralConvertUtils.convert2List(merchantClient.findList(merchantRequestQuery).getPayload(), MerchantResponseDTO.class);
        if (CommonUtils.isEmpty(merchantList)) {
            throw new ApplicationException("当前用户没有所属客户信息.");
        }
        return merchantList;
    }

    /**
     * @Description: 被授权的客户.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    protected List<MerchantResponseDTO> getAuthorizeCustomer(List<MerchantResponseDTO> merchantList, Long appId) throws Exception {
        ToolAuthorizeCommodityInfoQuery query = new ToolAuthorizeCommodityInfoQuery();
        query.setAppId(appId);
        query.setTenantId(appRuntimeEnv.getTenantId());
        query.setCustomerId(merchantList.stream().map(MerchantResponseDTO::getId).collect(Collectors.toList()));
        query.setCustomerTypeId(merchantList.stream().map(MerchantResponseDTO::getTypeId).collect(Collectors.toList()));
        query.setCustomerOrgId(merchantList.stream().map(s -> Long.valueOf(s.getOrgId())).collect(Collectors.toList()));
        Payload<List<ToolCustomerDTO>> payload = toolAuthorizeClient.getAuthorizedCustomerId(query);
        if (CommonUtils.isEmpty(payload.getPayload())) {
            return Lists.newArrayList();
        }
        List<ToolCustomerDTO> tools = GeneralConvertUtils.convert2List(payload.getPayload(), ToolCustomerDTO.class);
        return merchantList.stream().filter(s -> tools.stream().anyMatch(t -> t.getCustomerId().equals(s.getId()))).collect(Collectors.toList());
    }

    /**
     * @Description: 获取指定sku的价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    protected BigDecimal getPrice(List<BusinessSkuPriceAdminResponsDTO> skuPrices, Long skuId) {
        List<BusinessSkuPriceAdminResponsDTO> prices = skuPrices.stream().filter(s -> s.getSkuId().equals(skuId)).collect(Collectors.toList());
        if (CommonUtils.isNotEmpty(prices)) {
            return prices.get(0).getPrice();
        }
        return BigDecimal.valueOf(0);
    }

    /**
     * @Description: 查询门店信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/9
     */
    protected List<StoreResponseDTO> getStoreInfos(List<Long> orgIds) throws Exception {
        StoreRequestQuery storeQuery = new StoreRequestQuery();
        storeQuery.setOrgIds(orgIds);
        storeQuery.setTenantId(appRuntimeEnv.getTenantId());
        List<StoreResponseDTO> stores = GeneralConvertUtils.convert2List(
                storeClient.findList(storeQuery).getPayload(), StoreResponseDTO.class);
        if (CommonUtils.isEmpty(stores)) {
            throw new ApplicationException("当前用户对应的上游没有初始化店铺信息.");
        }
        // 过滤掉直供门店.
        return stores.stream().filter(s -> s.getType().equals(2)).collect(Collectors.toList());
    }

    /**
     * @Description: 查询门店信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/24
     */
    protected List<StoreResponseDTO> findStore(List<CommodityTypeResponseVO> resultList) throws Exception {
        StoreRequestQuery storeQuery = new StoreRequestQuery();
        List<Long> shopIds = resultList.stream().filter(s -> Objects.nonNull(s.getShopId()))
                .map(CommodityTypeResponseVO::getShopId).distinct().collect(Collectors.toList());
        storeQuery.setIds(shopIds);
        storeQuery.setTenantId(appRuntimeEnv.getTenantId());
        Payload<List<StoreResponseDTO>> payload = storeClient.findList(storeQuery);
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        if (CommonUtils.isEmpty(payload.getPayload())) {
            throw new ApplicationException("找不到对应店铺信息.");
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), StoreResponseDTO.class);
    }

    /**
     * @Description: 批量客户.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    protected Long matchCustomer(List<MerchantResponseDTO> customerList, Long orgId, Long topOrgId) {
        boolean result = customerList.stream().anyMatch(s -> s.getOrgId().equals(String.valueOf(orgId)));
        if (result) {
            return orgId;
        }
        // 如果一级没有客户,找顶级的客户.
        List<MerchantResponseDTO> customets = customerList.stream().filter(s -> s.getOrgId().equals(String.valueOf(topOrgId))).collect(Collectors.toList());
        if (CommonUtils.isNotEmpty(customets)) {
            return Long.valueOf(customets.get(0).getOrgId());
        }
        return 0L;
    }

    /**
    * @Description: 通过sku查询具体的iteam.
    * @Param: 
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/27
    */
    protected List<String> getItemBySku(List<AuthorizeSkuInfo> authorizeSkuList, Long appId, List<StoreResponseDTO> storeList) {
        if (CommonUtils.isEmpty(authorizeSkuList)) {
            return Lists.newArrayList();
        }
        List<String> resultList = Lists.newArrayList();
        for (AuthorizeSkuInfo skuInfo : authorizeSkuList) {
            List<StoreResponseDTO> stores = storeList.stream().filter(s -> skuInfo.getAuthorizeBy().equals(s.getOrgId())).collect(Collectors.toList());
            if (CommonUtils.isEmpty(stores)) {
                continue;
            }
            ListAllSkuRequestDTO requestDTO = new ListAllSkuRequestDTO();
            requestDTO.setAppId(appId);
            requestDTO.setTenantId(appRuntimeEnv.getTenantId());
            requestDTO.setIdList(skuInfo.getSkuIds().stream().collect(Collectors.toList()));
            PayloadListListAllSkuResponseDTO payload = skuOpenApi.listAllSku(requestDTO);
            if (Objects.nonNull(payload) && CommonUtils.isNotEmpty(payload.getPayload())) {
                StoreResponseDTO storeResponseDTO = stores.get(0);
                payload.getPayload().stream().forEach(data -> {
                    resultList.add((storeResponseDTO.getId() + "_" + data.getItemId()));
                });
            }
        }
        return resultList;
    }
}
