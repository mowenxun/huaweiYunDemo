package com.deepexi.dd.domain.transaction.service.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/16:34
 * @Description:
 */

import com.deepexi.dd.domain.business.domain.dto.BusinessSkuPriceAdminResponsDTO;
import com.deepexi.dd.domain.business.domain.query.BusinessSkuPriceAdminRequestQuery;
import com.deepexi.dd.domain.common.domain.dto.CommonDirectSupplyResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonDirectSupplyRequestQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuAuthorizeOrgResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.AuthorizedPriceRquestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.DirectSkuPriceRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice.SkuStockRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderItemDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.AuthorizedPrice.AuthorizedPriceResponseDTO;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.remote.business.BusinessSkuPriceOpenClient;
import com.deepexi.dd.domain.transaction.remote.order.BusinessPartnerClient;
import com.deepexi.dd.domain.transaction.remote.order.CommonDirectSupplyClient;
import com.deepexi.dd.domain.transaction.remote.order.MerchantClient;
import com.deepexi.dd.domain.transaction.remote.order.ToolCommoditytypeAuthorizeClient;
import com.deepexi.dd.domain.transaction.service.AuthorizedPriceService;
import com.deepexi.sdk.commodity.api.SaleStockOpenApi;
import com.deepexi.sdk.commodity.model.ListSaleStockRequestDTO;
import com.deepexi.sdk.commodity.model.ListSaleStockResponseDTO;
import com.deepexi.sdk.commodity.model.PayloadListListSaleStockResponseDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.MerchantResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import domain.query.MerchantRequestQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName AuthorizedPriceServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/28
 * @Version V1.0
 **/
@Service
public class AuthorizedPriceServiceImpl implements AuthorizedPriceService {

    Logger logger = LoggerFactory.getLogger(SaleOrderInfoServiceImpl.class);

    @Autowired
    MerchantClient merchantClient;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    BusinessPartnerClient businessPartnerClient;

    @Autowired
    ToolCommoditytypeAuthorizeClient toolCommoditytypeAuthorizeClient;

    @Autowired
    private BusinessSkuPriceOpenClient skuPriceOpenClient;

    @Autowired
    CommonDirectSupplyClient commonDirectSupplyClient;

    @Autowired
    private SaleStockOpenApi saleStockOpenApi;

    private static final Long CHANNEL = 1L;

    private static final String SUCCESS = "0";

    @Override
    public List<AuthorizedPriceResponseDTO> getAuthorizedPrice(List<AuthorizedPriceRquestDTO> list) throws Exception {
        Long appId = list.get(0).getAppId();
        String tenantId = list.get(0).getTenantId();
        Long sellerId = list.get(0).getSellerId();
        List<AuthorizedPriceResponseDTO> result = new ArrayList<>();
        BusinessPartnerResponseDTO businessPartnerResponseDTO = getPartner(appId, tenantId);
        Integer companyType = businessPartnerResponseDTO.getCompanyTypeId().intValue();
        //根据当前用户的业务伙伴ID，供应商直属组织ID
        List<MerchantResponseDTO> merchatList = queryMerchatByPartners(tenantId, appId,
                businessPartnerResponseDTO.getId());
        Map<String, MerchantResponseDTO> merchatMap =
                merchatList.stream().collect(Collectors.toMap(MerchantResponseDTO::getOrgId, Function.identity(), (k1
                        , k2) -> k1));
        List<Long> skuIds = list.stream().map(AuthorizedPriceRquestDTO::getSkuId).collect(Collectors.toList());
        //查询商品对应的一级组织
        Map<Long, List<Long>> skuOrgMap = getSkuOrgMap(appId, sellerId, skuIds);
        Set<Long> skuSets = new HashSet<>();
        Long customerId = null;
        if (skuOrgMap.size() > 0) {
            for (Map.Entry<Long, List<Long>> entry : skuOrgMap.entrySet()) {
                //如果组织id不为空，则获取客户信息
                if (!entry.getKey().equals(-9999L)) {
                    MerchantResponseDTO merchant = merchatMap.get(entry.getKey() + "");
                    if (merchant != null) {
                        customerId = merchant.getId();
                    } else {
                        merchant = merchatMap.get(sellerId + "");
                        if (merchant == null) {
                            throw new ApplicationException(ResultEnum.CUSTOMER_NOT_AUTHRISE);
                        } else {
                            customerId = merchant.getId();
                        }
                    }
                } else {
                    MerchantResponseDTO merchant = merchatMap.get(sellerId + "");
                    if (merchant != null) {
                        customerId = merchant.getId();
                    } else {
                        throw new ApplicationException(ResultEnum.CUSTOMER_NOT_AUTHRISE);
                    }
                }
                //getPrice(appId, customerId, sellerId, skuIds, result, companyType);
                getPrice(appId, customerId, sellerId, entry.getValue(), result, companyType);
            }
        } else {
            MerchantResponseDTO merchant = merchatMap.get(sellerId + "");
            if (merchant != null) {
                customerId = merchant.getId();
            }
            getPrice(appId, customerId, sellerId, skuIds, result, companyType);
        }
        logger.info("获得授权价格=={}", result);
        return result;
    }

    @Override
    public Map<Long, BigDecimal> getDirectSkuPrice(DirectSkuPriceRequestDTO directSkuPriceRequestDTO) throws Exception {
        Map<Long, BigDecimal> resultMap = new HashedMap();
        CommonDirectSupplyRequestQuery requestQuery = new CommonDirectSupplyRequestQuery();
       // requestQuery.setAppId(directSkuPriceRequestDTO.getAppId());
        //requestQuery.setTenantId(directSkuPriceRequestDTO.getTenantId());
        requestQuery.setSkuIdList(directSkuPriceRequestDTO.getSkuIdList());
        requestQuery.setOnStatus(1);
        Payload<List<CommonDirectSupplyResponseDTO>> payloadResult =
                commonDirectSupplyClient.listDirectSupplyRelation(requestQuery);
        if (SUCCESS.equals(payloadResult.getCode())) {
            try {
                List<CommonDirectSupplyResponseDTO> resultList =
                        GeneralConvertUtils.convert2List(payloadResult.getPayload(),
                                CommonDirectSupplyResponseDTO.class);
                if (CollectionUtil.isNotEmpty(resultList)) {
                    for (CommonDirectSupplyResponseDTO r : resultList) {
                        resultMap.put(Long.valueOf(r.getSkuId()), r.getDirectSupplyPrice());
                    }
                }
            } catch (Exception e) {
                logger.error("查询直供商品失败:", e);
                throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
            }
        } else {
            return null;
        }
        return resultMap;
    }

    @Override
    public Map<Long, Long> getSkuStock(SkuStockRequestDTO skuStockRequestDTO) throws ApplicationException {
        Map<Long, Long> stockMap = new HashedMap();
        ListSaleStockRequestDTO requestDTO = new ListSaleStockRequestDTO();
        requestDTO.setAppId(skuStockRequestDTO.getAppId());
        requestDTO.setTenantId(skuStockRequestDTO.getTenantId());
        requestDTO.setShopId(skuStockRequestDTO.getShopId());
        requestDTO.setSkuIdList(skuStockRequestDTO.getSkuIdList());
        try {
            PayloadListListSaleStockResponseDTO responseDTO = saleStockOpenApi.listSaleStocks(requestDTO);
            if (SUCCESS.equals(responseDTO.getCode())) {
                List<ListSaleStockResponseDTO> list = responseDTO.getPayload();
                if (CollectionUtil.isNotEmpty(list)) {
                    for (ListSaleStockResponseDTO stock : list) {
                        stockMap.put(stock.getSkuId(), stock.getSalableStockQty() == null ? 0L :
                                Long.valueOf(stock.getSalableStockQty()));
                    }
                }
            } else {
                throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
            }
        } catch (Exception e) {
            logger.error("商品库存查询异常:{}", e);
            throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
        }
        return stockMap;
    }

    /**
     * 根据业务伙伴ID查询所有客户信息
     *
     * @param partnerId
     * @return
     */
    private List<MerchantResponseDTO> queryMerchatByPartners(String tenantId, Long appId, Long partnerId) throws ApplicationException {
        MerchantRequestQuery query = new MerchantRequestQuery();
        query.setTenantId(tenantId);
        query.setAppId(appId);
        query.setPartnerId(partnerId);
        try {
            Payload<List<MerchantResponseDTO>> payload = merchantClient.findList(query);
            return GeneralConvertUtils.convert2List(payload.getPayload(), MerchantResponseDTO.class);
        } catch (Exception e) {
            logger.error("查询客户信息失败:", e);
            throw new ApplicationException("查询客户信息失败:{}", JsonUtil.bean2JsonString(query));
        }
    }

    public BusinessPartnerResponseDTO getPartner(Long appId, String tenantId) throws InvocationTargetException,
            NoSuchMethodException,
            NoSuchFieldException, IllegalAccessException, IOException {
        Payload<BusinessPartnerResponseDTO> businessPartnerResponseDTOPayload = null;
        try {
            BusinessPartnerRequestQuery businessPartnerRequestQuery = new BusinessPartnerRequestQuery();
            businessPartnerRequestQuery.setTenantId(tenantId);
            businessPartnerRequestQuery.setAppId(appId);
            businessPartnerRequestQuery.setOrgId(Long.valueOf(appRuntimeEnv.getTopOrganization().getId()));

            businessPartnerResponseDTOPayload = businessPartnerClient.getPartner(businessPartnerRequestQuery);
            logger.info("获取伙伴id:{}", JsonUtil.bean2JsonString(businessPartnerResponseDTOPayload));
            if (!businessPartnerResponseDTOPayload.getCode().equals("0")) {
                throw new ApplicationException("获取伙伴ID失败");
            }
        } catch (Exception e) {
            logger.error("获取伙伴ID失败:", e);
            throw new ApplicationException("获取伙伴ID失败");
        }
        BusinessPartnerResponseDTO businessPartner =
                GeneralConvertUtils.conv(businessPartnerResponseDTOPayload.getPayload(),
                        BusinessPartnerResponseDTO.class);
        return businessPartner;
    }

    /**
     * 获取商品对应的一级组织
     *
     * @param appId
     * @param skuIds
     * @return
     * @throws Exception
     */
    private Map<Long, List<Long>> getSkuOrgMap(Long appId, Long orgId, List<Long> skuIds) throws Exception {
        ToolSkuRequestDTO requestDTO = new ToolSkuRequestDTO();
        requestDTO.setAppId(appId);
        requestDTO.setTenantId(appRuntimeEnv.getTenantId());
        requestDTO.setOrgId(orgId);
        String ids = StringUtils.join(skuIds, ",");
        requestDTO.setSkuIds(ids);
        Payload<List<ToolSkuAuthorizeOrgResponseDTO>> payloadSkuOrgs =
                toolCommoditytypeAuthorizeClient.getAuthorizeOrgBySkuId(requestDTO);
        if (SUCCESS.equals(payloadSkuOrgs.getCode())) {
            List<ToolSkuAuthorizeOrgResponseDTO> resultList =
                    GeneralConvertUtils.convert2List(payloadSkuOrgs.getPayload(), ToolSkuAuthorizeOrgResponseDTO.class);
            if (CollectionUtil.isNotEmpty(resultList)) {
                return resultList.stream().collect(Collectors.groupingBy(a -> getDefaultKey(a.getOrgId()),
                        Collectors.mapping(ToolSkuAuthorizeOrgResponseDTO::getSkuId, Collectors.toList())));
            }
        }
        return new HashMap<>();
    }

    private Long getDefaultKey(Long key) {
        return key == null ? -9999L : key;
    }

    //处理授权价格
    public void getPrice(Long appId, Long custmoterId, Long sellerId, List<Long> skuIds,
                         List<AuthorizedPriceResponseDTO> result, Integer companyType) throws Exception {
        BusinessSkuPriceAdminRequestQuery requestQuery = new BusinessSkuPriceAdminRequestQuery();
        requestQuery.setCustomerId(custmoterId);
        requestQuery.setTenantId(appRuntimeEnv.getTenantId());
        requestQuery.setAppId(appId);
        requestQuery.setIsolationId(sellerId.toString());
        requestQuery.setCompanyType(companyType);
        requestQuery.setSkuIds(skuIds);
        List<BusinessSkuPriceAdminResponsDTO> skuPrices =
                GeneralConvertUtils.convert2List(skuPriceOpenClient.queryPriceBySkuIds(requestQuery).getPayload(),
                        BusinessSkuPriceAdminResponsDTO.class);
        if (CollectionUtils.isNotEmpty(skuPrices)) {
            skuIds.forEach(sku -> {
                skuPrices.forEach(price -> {
                    if (sku.equals(price.getSkuId())) {
                        AuthorizedPriceResponseDTO authorizedPriceResponseDTO = new AuthorizedPriceResponseDTO();
                        authorizedPriceResponseDTO.setSellerId(sellerId);
                        authorizedPriceResponseDTO.setSkuId(sku);
                        authorizedPriceResponseDTO.setAuthorizedPrice(price.getPrice());
                        result.add(authorizedPriceResponseDTO);
                    }
                });

            });
        }
    }
}
