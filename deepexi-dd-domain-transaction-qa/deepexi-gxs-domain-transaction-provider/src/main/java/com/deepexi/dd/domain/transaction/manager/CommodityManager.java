package com.deepexi.dd.domain.transaction.manager;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.business.domain.dto.BusinessSkuPriceAdminResponsDTO;
import com.deepexi.dd.domain.business.domain.dto.LevelPriceResponseDTO;
import com.deepexi.dd.domain.business.domain.query.BusinessSkuPriceAdminRequestQuery;
import com.deepexi.dd.domain.business.domain.query.LevelPriceQuery;
import com.deepexi.dd.domain.common.domain.dto.CommonDirectSupplyResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonDirectSupplyRequestQuery;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.common.util.ObjectConvertUtil;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuAuthorizeOrgResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.CommodityStockOperationDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderItemDTO;
import com.deepexi.dd.domain.transaction.domain.query.CommodityQuery;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.enums.ShopTypeEnum;
import com.deepexi.dd.domain.transaction.remote.business.BusinessSkuPriceOpenClient;
import com.deepexi.dd.domain.transaction.remote.common.OnlineActivitiesClient;
import com.deepexi.dd.domain.transaction.remote.order.CommonDirectSupplyClient;
import com.deepexi.dd.domain.transaction.remote.order.ToolCommoditytypeAuthorizeClient;
import com.deepexi.sdk.commodity.api.*;
import com.deepexi.sdk.commodity.model.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : CommodityManager
 * @Description : 商品管理
 * @Author : yuanzaishun
 * @Date: 2020-07-09 19:05
 */
@Component
@Slf4j
public class CommodityManager {
    Logger logger = LoggerFactory.getLogger(CommodityManager.class);
    private static final String SUCCESS = "0";
    @Resource
    private ShopItemOpenApi shopItemOpenApi;

    @Autowired
    private SaleStockOpenApi saleStockOpenApi;

    @Autowired
    private ActivityStockOpenApi activityStockOpenApi;

    @Autowired
    CommonDirectSupplyClient commonDirectSupplyClient;

    @Autowired
    private OnlineActivitiesClient activitiesClient;

    @Autowired
    private BusinessSkuPriceOpenClient skuPriceOpenClient;

    @Autowired
    protected ToolCommoditytypeAuthorizeClient authorizeClient;

    @Autowired
    private SkuOpenApi skuOpenApi;

    @Autowired
    private BrandApi brandApi;

    private static final String LOG_KEY = "公共业务处理";

    /**
    * @Description: 查询商品详情.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/31
    */
    public PayloadListListShopItemUpShelfResponseDTO getCommodity(CommodityQuery commodityQuery) {
        ListShopItemUpShelfRequestDTO requestDTO = new ListShopItemUpShelfRequestDTO();
        requestDTO.setTenantId(commodityQuery.getTenantId());
        requestDTO.setShopId(commodityQuery.getShopId());
        requestDTO.setSkuIds(commodityQuery.getSkuIds());
        requestDTO.setAppId(commodityQuery.getAppId());
        // 获取商品详情

        log.info("商品域获取商品详情入参：{}", JSON.toJSONString(requestDTO));
        return shopItemOpenApi.listShopItemUpShelf(requestDTO);
    }

    /**
     * 根据店铺ID和skuids查询商品信息
     *
     * @param commodityQuery
     * @return
     */
    public List<SaleOrderItemDTO> getCommoditys(CommodityQuery commodityQuery) throws Exception {
        if (CollectionUtil.isEmpty(commodityQuery.getSkuIds())) {
            return new ArrayList<>();
        }
        // 查询商品详情.
        PayloadListListShopItemUpShelfResponseDTO responseDTO = getCommodity(commodityQuery);
        log.info("商品域获取商品详情结果：{}", JSON.toJSONString(responseDTO));
        //TODO 目前暂不要库存， 原逻辑库存都实时查询商品中台的.
        Map<Long, Long> skuStockMap = null;//this.getSkuStock(commodityQuery);

        Map<Long, BrandDTO> skuIdBrandMap = this.getBrandInfoBySkuId(commodityQuery);

        //目前价格获取特定等级sku的价格，否则使用中台的sku的价格，原格力逻辑是如果是直供订单,需要获取直供价格
        Map<Long, BigDecimal> skuPriceMap = this.getLevelSkuPrice(commodityQuery);
        if (SUCCESS.equals(responseDTO.getCode())) {
            List<ListShopItemUpShelfResponseDTO> list = responseDTO.getPayload();
            return getSaleOrderItems(list, skuStockMap, skuIdBrandMap, skuPriceMap, commodityQuery.getActivitiesId());
        } else {
            throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
        }
    }

    /**
     * @Description: 获取客户价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/27
     */
    private Map<Long, BigDecimal> getAuthorizeCustomerSkuprice(CommodityQuery commodityQuery, List<Long> skuIds) throws Exception {
        if (CommonUtils.isEmpty(commodityQuery.getCustomerMap()) || CommonUtils.isEmpty(commodityQuery.getStoreMap())) {
            return Maps.newHashMap();
        }
        List<String> merchantIds = commodityQuery.getCustomerMap().get(commodityQuery.getStoreMap().get(commodityQuery.getShopId()));
        if (CommonUtils.isEmpty(merchantIds)) {
            log.info("getAuthorizeCustomerSkuprice custoer is null shopId:{}, customer:{}", commodityQuery.getShopId(), JSON.toJSONString(commodityQuery.getCustomerMap()));
            return Maps.newHashMap();
        }
        Map<String, List<BusinessSkuPriceAdminResponsDTO>> customerMap = Maps.newHashMap();
        // 反查sku的一级组织.
        Map<Long, Long> skuMap = findSkuFistOrg(commodityQuery, skuIds, Long.valueOf(commodityQuery.getStoreMap().get(commodityQuery.getShopId())));
        for (String customerInfo : merchantIds) {
            Long merchantId = Long.valueOf(customerInfo.split(";")[0]);
            BusinessSkuPriceAdminRequestQuery requestQuery = new BusinessSkuPriceAdminRequestQuery();
            requestQuery.setCustomerId(merchantId);
            requestQuery.setTenantId(commodityQuery.getTenantId());
            requestQuery.setAppId(commodityQuery.getAppId());
            requestQuery.setIsolationId(commodityQuery.getStoreMap().get(commodityQuery.getShopId()));
            requestQuery.setCompanyType(commodityQuery.getCompanyType());
            requestQuery.setSkuIds(commodityQuery.getSkuIds());
            List<BusinessSkuPriceAdminResponsDTO> skuPrices = GeneralConvertUtils.convert2List(skuPriceOpenClient.queryPriceBySkuIds(requestQuery).getPayload(), BusinessSkuPriceAdminResponsDTO.class);
            if (CommonUtils.isNotEmpty(skuPrices)) {
                Collections.sort(skuPrices, (v1, v2) -> v1.getTyp().compareTo(v2.getTyp()));
                customerMap.put(customerInfo.split(";")[1], skuPrices);
            }
        }
        if (CommonUtils.isEmpty(customerMap)) {
            return Maps.newHashMap();
        }
        Map<Long, BigDecimal> resultMap = Maps.newHashMap();
        skuIds.forEach(data -> {
            Long orgId = skuMap.get(data);
            List<BusinessSkuPriceAdminResponsDTO> skuPrices = customerMap.get(String.valueOf(orgId));
            if (CommonUtils.isNotEmpty(skuPrices)) {
                resultMap.put(data, getPrice(skuPrices, data));
            } else {
                resultMap.put(data, BigDecimal.valueOf(0));
            }
        });
        return resultMap;
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
    * @Description: 反查sku的一级组织.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/24
    */
    private Map<Long, Long> findSkuFistOrg(CommodityQuery commodityQuery, List<Long> skuIds, Long orgId) throws Exception {
        Map<Long, Long> skuMap = Maps.newHashMap();
        ToolSkuRequestDTO toolSkuRequestDTO = new ToolSkuRequestDTO();
        toolSkuRequestDTO.setAppId(commodityQuery.getAppId());
        toolSkuRequestDTO.setTenantId(commodityQuery.getTenantId());
        toolSkuRequestDTO.setOrgId(orgId);
        toolSkuRequestDTO.setSkuIds(org.apache.commons.lang3.StringUtils.join(skuIds, ","));
        Payload<List<ToolSkuAuthorizeOrgResponseDTO>> payload = authorizeClient.getAuthorizeOrgBySkuId(toolSkuRequestDTO);
        if (CommonUtils.isNotEmpty(payload.getPayload())) {
            List<ToolSkuAuthorizeOrgResponseDTO> authorizeList = GeneralConvertUtils.convert2List(payload.getPayload(), ToolSkuAuthorizeOrgResponseDTO.class);
            authorizeList.forEach(data -> {
                if (Objects.nonNull(data.getOrgId())) {
                    // 如果有一级组织就设置一级组织.
                    skuMap.put(data.getSkuId(), matchCustomer(commodityQuery.getAllCustomerMap(), data.getOrgId(), orgId));
                } else {
                    // 如果没有一级组织给顶级组织.
                    skuMap.put(data.getSkuId(), orgId);
                }
            });
        } else {
            skuIds.forEach(skuId -> skuMap.put(skuId, skuMap.get(orgId)));
        }
        return skuMap;
    }

    /**
     * @Description: 批量客户.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    private Long matchCustomer(Map<Long, Long> customerMap, Long orgId, Long topOrgId) {
        if (Objects.nonNull(customerMap.get(orgId))) {
            return orgId;
        }
        if (Objects.nonNull(customerMap.get(topOrgId))) {
            return topOrgId;
        }
        return 0L;
    }


    /**
     * @Description: 获取客户价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/27
     */
    private Map<Long, BigDecimal> getCustomerSkuprice(CommodityQuery commodityQuery, List<Long> skuIds) throws Exception {
        if (CommonUtils.isEmpty(commodityQuery.getMerchantMap()) || CommonUtils.isEmpty(commodityQuery.getStoreMap())) {
            return Maps.newHashMap();
        }
        List<BusinessSkuPriceAdminResponsDTO> result = Lists.newArrayList();
        List<Long> merchantIds = commodityQuery.getMerchantMap().get(commodityQuery.getStoreMap().get(commodityQuery.getShopId()));
        if (CommonUtils.isEmpty(merchantIds)) {
            log.info("getCustomerSkuprice merchant is null shopId:{}, merchantMap:{}", commodityQuery.getShopId(), JSON.toJSONString(commodityQuery.getMerchantMap()));
            return Maps.newHashMap();
        }
        for (Long merchantId : merchantIds) {
            BusinessSkuPriceAdminRequestQuery requestQuery = new BusinessSkuPriceAdminRequestQuery();
            requestQuery.setCustomerId(merchantId);
            requestQuery.setTenantId(commodityQuery.getTenantId());
            requestQuery.setAppId(commodityQuery.getAppId());
            requestQuery.setIsolationId(commodityQuery.getStoreMap().get(commodityQuery.getShopId()));
            requestQuery.setCompanyType(commodityQuery.getCompanyType());
            requestQuery.setSkuIds(commodityQuery.getSkuIds());
            List<BusinessSkuPriceAdminResponsDTO> skuPrices = GeneralConvertUtils.convert2List(skuPriceOpenClient.queryPriceBySkuIds(requestQuery).getPayload(), BusinessSkuPriceAdminResponsDTO.class);
            if (CommonUtils.isNotEmpty(skuPrices)) {
                pushSkuPrice(result, skuPrices);
            }
        }
        if (CommonUtils.isEmpty(result)) {
            return Maps.newHashMap();
        }
        return result.stream().collect(Collectors.toMap(BusinessSkuPriceAdminResponsDTO::getSkuId, v->getBigDecimal(v.getPrice()),(v1,v2)->v1));
    }

    private BigDecimal getBigDecimal(BigDecimal value){
        return value==null ? BigDecimal.valueOf(0):value;
    }

    /**
     * @Description: 如果有相同的sku, 获取优先级高的sku价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/27
     */
    private void pushSkuPrice(List<BusinessSkuPriceAdminResponsDTO> result, List<BusinessSkuPriceAdminResponsDTO> addList) {
        if (CommonUtils.isEmpty(result)) {
            result.addAll(addList);
        } else {
            addList.forEach(data -> {
                List<BusinessSkuPriceAdminResponsDTO> skuPrices = result.stream().filter(s -> data.getSkuId().equals(s.getSkuId())).collect(Collectors.toList());
                if (CommonUtils.isEmpty(skuPrices)) {
                    // 如果没有重复的直接加入.
                    result.add(data);
                } else {
                    // 如果有重复的需要修改优先级高的sku价格.
                    if (data.getTyp() < skuPrices.get(0).getTyp()) {
                        skuPrices.get(0).setPrice(data.getPrice());
                    }
                }
            });
        }
    }


    private Long getLong(Integer value) {
        return Long.valueOf(value);
    }

    /**
     * 查询活动库存
     *
     * @param tenantId
     * @param appId
     * @param activitiesId
     * @param skuIds
     * @return
     */
    private List<ListActivityStockResponseDTO> listActivityStocksDetail(String tenantId, Long appId, Long activitiesId, List<Long> skuIds) {
        ListActivityStockRequestDTO requestDTO = new ListActivityStockRequestDTO();
        requestDTO.setAppId(appId);
        requestDTO.setTenantId(tenantId);
        List<String> unionIdList = CollectionUtil.createArrayList();
        skuIds.forEach(t -> {
            String unionId = ShopTypeEnum.ACTIVITIES_STORE.getType().toString() + "_" + t + "_" + activitiesId;
            unionIdList.add(unionId);
        });
        requestDTO.setUnionIdList(unionIdList);
        PayloadListListActivityStockResponseDTO payload = activityStockOpenApi.listActivityStocksDetail(requestDTO);
        return payload.getPayload();
    }

    /**
     * @Description: 过滤掉不属于活动中的sku.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/24
     */
    private void filterCommodity(List<ListShopItemUpShelfResponseDTO> list, List<Long> skuIds) {
        list.removeIf(s -> Objects.nonNull(s.getSkuId()) && !skuIds.stream().anyMatch(skuId -> skuId.equals(s.getSkuId())));
    }

    /**
     * 减少锁定库存、增加可销售库存
     *
     * @param list
     */
    public void decLockIncSalableStock(List<CommodityStockOperationDTO> list) {
        List<UpdateDecLockIncSalableStockRequestDTO> requestDtoList = new ArrayList<>();
        for (CommodityStockOperationDTO commodityStockOperationDTO : list) {
            UpdateDecLockIncSalableStockRequestDTO requestDTO = new UpdateDecLockIncSalableStockRequestDTO();
            requestDTO.setAppId(commodityStockOperationDTO.getAppId());
            requestDTO.setTenantId(commodityStockOperationDTO.getTenantId());
            requestDTO.setChannel(commodityStockOperationDTO.getChannel());
            requestDTO.setExtendId(commodityStockOperationDTO.getExtendId());
            requestDTO.setExtendNo(commodityStockOperationDTO.getExtendNo());
            requestDTO.setExtendType(commodityStockOperationDTO.getExtendType());
            requestDTO.setShopId(commodityStockOperationDTO.getShopId());
            requestDTO.setSkuList(ObjectConvertUtil.getList(commodityStockOperationDTO.getSkuQty(), UpdateDecLockIncSalableStockSkuRequestDTO.class));
            requestDtoList.add(requestDTO);
        }
        try {
            PayloadUpdateDecLockIncSalableStockResponseDTO responseDTO = saleStockOpenApi.decLockIncSalableStock(requestDtoList);
            if (!SUCCESS.equals(responseDTO.getCode())) {
                logger.error("商品增加可售库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
                throw new ApplicationException(ResultEnum.COMMODITY_ADD_STOCK_ERROR);
            }
        } catch (Exception e) {
            logger.error("商品增加可售库存失败", e);
            logger.error("商品增加可售库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
            throw new ApplicationException(ResultEnum.COMMODITY_ADD_STOCK_ERROR);
        }

    }


    /**
     * 减少锁定库存、增加已销售库存
     *
     * @param list
     * @throws ApplicationException
     */
    public void decLockIncSaledStock(List<CommodityStockOperationDTO> list) throws ApplicationException {
        List<UpdateDecLockIncSaledStockRequestDTO> requestDtoList = new ArrayList<>();
        for (CommodityStockOperationDTO commodityStockOperationDTO : list) {
            UpdateDecLockIncSaledStockRequestDTO requestDTO = new UpdateDecLockIncSaledStockRequestDTO();
            requestDTO.setAppId(commodityStockOperationDTO.getAppId());
            requestDTO.setTenantId(commodityStockOperationDTO.getTenantId());
            requestDTO.setChannel(commodityStockOperationDTO.getChannel());
            requestDTO.setExtendId(commodityStockOperationDTO.getExtendId());
            requestDTO.setExtendNo(commodityStockOperationDTO.getExtendNo());
            requestDTO.setExtendType(commodityStockOperationDTO.getExtendType());
            requestDTO.setShopId(commodityStockOperationDTO.getShopId());
            requestDTO.setSkuList(ObjectConvertUtil.getList(commodityStockOperationDTO.getSkuQty(), UpdateDecLockIncSaledStockSkuRequestDTO.class));
            requestDtoList.add(requestDTO);
        }
        try {
            PayloadUpdateDecLockIncSaledStockResponseDTO responseDTO = saleStockOpenApi.decLockIncSaledStock(requestDtoList);
            if (!SUCCESS.equals(responseDTO.getCode())) {
                logger.error("商品增加已售库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
                throw new ApplicationException(ResultEnum.COMMODITY_UNLOCK_STOCK_ERROR);
            }
        } catch (Exception e) {
            logger.error("商品增加已售库存失败", e);
            logger.error("商品增加已售库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
            throw new ApplicationException(ResultEnum.COMMODITY_UNLOCK_STOCK_ERROR);
        }
    }

    /**
     * 增加锁定库存,减少可售库存
     *
     * @param list
     * @throws ApplicationException
     */
    public void incLockDecSalableStock(List<CommodityStockOperationDTO> list) throws ApplicationException {
        List<UpdateIncLockDecSalableStockRequestDTO> requestDtoList = new ArrayList<>();
        for (CommodityStockOperationDTO commodityStockOperationDTO : list) {
            UpdateIncLockDecSalableStockRequestDTO requestDTO = new UpdateIncLockDecSalableStockRequestDTO();
            requestDTO.setAppId(commodityStockOperationDTO.getAppId());
            requestDTO.setTenantId(commodityStockOperationDTO.getTenantId());
            requestDTO.setChannel(commodityStockOperationDTO.getChannel());
            requestDTO.setExtendId(commodityStockOperationDTO.getExtendId());
            requestDTO.setExtendNo(commodityStockOperationDTO.getExtendNo());
            requestDTO.setExtendType(commodityStockOperationDTO.getExtendType());
            requestDTO.setShopId(commodityStockOperationDTO.getShopId());
            requestDTO.setSkuList(ObjectConvertUtil.getList(commodityStockOperationDTO.getSkuQty(), UpdateIncLockDecSalableStockSkuRequestDTO.class));
            requestDtoList.add(requestDTO);
        }
        try {
            PayloadUpdateIncLockDecSalableStockResponseDTO responseDTO = saleStockOpenApi.incLockDecSalableStock(requestDtoList);
            if (!SUCCESS.equals(responseDTO.getCode())) {
                throw new ApplicationException(ResultEnum.COMMODITY_LOCK_STOCK_ERROR);
            }
        } catch (Exception e) {
            logger.error("商品锁定库存失败", e);
            logger.error("商品锁定库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
            throw new ApplicationException(ResultEnum.COMMODITY_LOCK_STOCK_ERROR);
        }
    }


    /**
     * 活动 减少锁定库存、增加可销售库存
     *
     * @param list
     */
    public void decLockIncSalableActivityStock(List<CommodityStockOperationDTO> list) {
        List<UpdateDecActivityLockIncActivityStockRequestDTO> requestDtoList = new ArrayList<>();
        for (CommodityStockOperationDTO commodityStockOperationDTO : list) {
            UpdateDecActivityLockIncActivityStockRequestDTO requestDTO = new UpdateDecActivityLockIncActivityStockRequestDTO();
            requestDTO.setAppId(commodityStockOperationDTO.getAppId());
            requestDTO.setTenantId(commodityStockOperationDTO.getTenantId());
            requestDTO.setChannel(commodityStockOperationDTO.getChannel());
            requestDTO.setExtendId(commodityStockOperationDTO.getExtendId());
            requestDTO.setExtendNo(commodityStockOperationDTO.getExtendNo());
            requestDTO.setExtendType(commodityStockOperationDTO.getExtendType());
            requestDTO.setShopId(commodityStockOperationDTO.getShopId());
            requestDTO.setActivityId(commodityStockOperationDTO.getActivityId());
            requestDTO.setSkuList(ObjectConvertUtil.getList(commodityStockOperationDTO.getSkuQty(), UpdateDecActivityLockIncActivityStockSkuRequestDTO.class));
            requestDtoList.add(requestDTO);
        }
        try {
            PayloadUpdateDecActivityLockIncActivityStockResponseDTO responseDTO = activityStockOpenApi.decLockIncActivityStockStockChange(requestDtoList);
            if (!SUCCESS.equals(responseDTO.getCode())) {
                logger.error("商品增加可售库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
                throw new ApplicationException(ResultEnum.COMMODITY_ADD_STOCK_ERROR);
            }
        } catch (Exception e) {
            logger.error("商品增加可售库存失败", e);
            logger.error("商品增加可售库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
            throw new ApplicationException(ResultEnum.COMMODITY_ADD_STOCK_ERROR);
        }

    }


    /**
     * 活动 增加锁定库存,减少可售库存
     *
     * @param list
     * @throws ApplicationException
     */
    public void incLockDecSalableActivityStock(List<CommodityStockOperationDTO> list) throws ApplicationException {
        List<UpdateIncActivityLockDecActivityStockRequestDTO> requestDtoList = new ArrayList<>();
        for (CommodityStockOperationDTO commodityStockOperationDTO : list) {
            UpdateIncActivityLockDecActivityStockRequestDTO requestDTO = new UpdateIncActivityLockDecActivityStockRequestDTO();
            requestDTO.setAppId(commodityStockOperationDTO.getAppId());
            requestDTO.setTenantId(commodityStockOperationDTO.getTenantId());
            requestDTO.setChannel(commodityStockOperationDTO.getChannel());
            requestDTO.setExtendId(commodityStockOperationDTO.getExtendId());
            requestDTO.setExtendNo(commodityStockOperationDTO.getExtendNo());
            requestDTO.setExtendType(commodityStockOperationDTO.getExtendType());
            requestDTO.setShopId(commodityStockOperationDTO.getShopId());
            requestDTO.setActivityId(commodityStockOperationDTO.getActivityId());
            requestDTO.setSkuList(ObjectConvertUtil.getList(commodityStockOperationDTO.getSkuQty(), UpdateIncActivityLockDecActivityStockSkuRequestDTO.class));
            requestDtoList.add(requestDTO);
        }
        try {
            PayloadUpdateIncActivityLockDecActivityStockResponseDTO responseDTO = activityStockOpenApi.incLockDecActivityStockStockChange(requestDtoList);
            if (!SUCCESS.equals(responseDTO.getCode())) {
                throw new ApplicationException(ResultEnum.COMMODITY_LOCK_STOCK_ERROR);
            }
        } catch (Exception e) {
            logger.error("商品锁定库存失败", e);
            logger.error("商品锁定库存失败:{}", JsonUtil.bean2JsonString(requestDtoList));
            throw new ApplicationException(ResultEnum.COMMODITY_LOCK_STOCK_ERROR);
        }
    }


    /**
     * 获取sku库存
     *
     * @param commodityQuery
     * @return
     * @throws ApplicationException
     */
    public Map<Long, Long> getSkuStock(CommodityQuery commodityQuery) throws ApplicationException {
        Map<Long, Long> stockMap = new HashedMap();
        ListSaleStockRequestDTO requestDTO = new ListSaleStockRequestDTO();
        requestDTO.setAppId(commodityQuery.getAppId());
        requestDTO.setTenantId(commodityQuery.getTenantId());
        requestDTO.setShopId(commodityQuery.getShopId());
        requestDTO.setSkuIdList(commodityQuery.getSkuIds());
        try {
            PayloadListListSaleStockResponseDTO responseDTO = saleStockOpenApi.listSaleStocks(requestDTO);
            if (SUCCESS.equals(responseDTO.getCode())) {
                List<ListSaleStockResponseDTO> list = responseDTO.getPayload();
                if (CollectionUtil.isNotEmpty(list)) {

                    for (ListSaleStockResponseDTO stock : list) {
                        stockMap.put(stock.getSkuId(), stock.getSalableStockQty() == null ? 0L : Long.valueOf(stock.getSalableStockQty()));
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
     * 获取sku关联品牌信息
     *
     * @param commodityQuery
     * @return
     * @throws ApplicationException
     */
    public Map<Long, BrandDTO> getBrandInfoBySkuId(CommodityQuery commodityQuery) throws ApplicationException {
        Map<Long, BrandDTO> skuIdBrandInfoMap = new HashedMap();
        PageSkuItemBySkuQueryRequestDTO requestDTO = new PageSkuItemBySkuQueryRequestDTO();
        requestDTO.setAppId(commodityQuery.getAppId());
        requestDTO.setTenantId(commodityQuery.getTenantId());
        requestDTO.setShopId(commodityQuery.getShopId());
        requestDTO.setIdList(commodityQuery.getSkuIds());
        try {
            log.info("根据门店组织id及skuIds查询品牌ids信息入参：{}", requestDTO);
            PayloadPageBeanPageSkuItemBySkuQueryResponseDTO responseDTO = skuOpenApi.pageSkuItemBySkuQuery(requestDTO);

            if (!SUCCESS.equals(responseDTO.getCode())) {
                log.error("根据门店组织id及skuIds查询品牌ids信息,返回码非success：{}", responseDTO);
                throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
            }

            PageBeanPageSkuItemBySkuQueryResponseDTO  page = responseDTO.getPayload();
            List<PageSkuItemBySkuQueryResponseDTO> list = page.getContent();

            List<Long> brandIds = new ArrayList<>();

            //Map <brandId, skuId>
            Map<Long,Long> tempMap = new HashMap<>();

            list.forEach(item ->{
                brandIds.add(item.getItemWhole().getBrandId());
                tempMap.put(item.getItemWhole().getBrandId(), item.getSku().getId());
            });
            log.info("根据门店组织id及skuIds查询品牌ids信息：{}", brandIds);

            log.info("根据品牌ids查询sku关联品牌信息入参：appId:{},tenantId:{},brandIds:{}", commodityQuery.getAppId(), commodityQuery.getTenantId(), brandIds);
            PayloadListBrandDTO response = brandApi.listBrands(commodityQuery.getAppId(),null,null,null,null,brandIds,null,1,5000,commodityQuery.getTenantId());

            if (!SUCCESS.equals(response.getCode())) {
                log.error("根据品牌ids查询sku关联品牌信息,返回码非success：{}", response);
                throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
            }

            log.info("根据品牌ids查询sku关联品牌信息返回：{}", response.getPayload());
            if (CollectionUtil.isNotEmpty(list)) {

                for (BrandDTO item : response.getPayload()) {
                    skuIdBrandInfoMap.put(tempMap.get(item.getId()), item);
                }
            }

        } catch (Exception e) {
            logger.error("商品库存查询异常:{}", e);
            throw new ApplicationException(ResultEnum.COMMODITY_HTTP_ERROR);
        }
        return skuIdBrandInfoMap;
    }

    /**
     * 获取sku价格信息
     *
     * @param commodityQuery
     * @return
     */
    public Map<Long, BigDecimal> getDirectSkuPrice(CommodityQuery commodityQuery) {
        Map<Long, BigDecimal> resultMap = new HashedMap();
        CommonDirectSupplyRequestQuery requestQuery = new CommonDirectSupplyRequestQuery();
        requestQuery.setAppId(commodityQuery.getAppId());
        requestQuery.setTenantId(commodityQuery.getTenantId());
        requestQuery.setSkuIdList(commodityQuery.getSkuIds().stream().map(a -> a.toString()).collect(Collectors.toList()));
        requestQuery.setOnStatus(1);
        Payload<List<CommonDirectSupplyResponseDTO>> payloadResult = commonDirectSupplyClient.listDirectSupplyRelation(requestQuery);
        if (SUCCESS.equals(payloadResult.getCode())) {
            try {
                List<CommonDirectSupplyResponseDTO> resultList = GeneralConvertUtils.convert2List(payloadResult.getPayload(), CommonDirectSupplyResponseDTO.class);
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

    /**
     * 根据组织id和skuId获取确定等级sku价格
     * BusinessSkuPriceOpenApi.queryLevelPriceBySkuIdsAndGroupId
     *
     * @param commodityQuery
     * @return 返回 map<skuId, 等级绑定的sku价格>
     */
    public Map<Long, BigDecimal> getLevelSkuPrice(CommodityQuery commodityQuery) throws Exception {
        Map<Long, BigDecimal> resultMap = new HashedMap();
        LevelPriceQuery requestQuery = new LevelPriceQuery();

        requestQuery.setGroupId(commodityQuery.getOrgId());
        requestQuery.setSkuIds(commodityQuery.getSkuIds());
        log.info("根据门店组织id及skuIds查询级别价格入参：{}", requestQuery);
        try {
            Payload<List<LevelPriceResponseDTO>> payloadResult = skuPriceOpenClient.queryLevelPriceBySkuIdsAndGroupId(requestQuery);
            log.info("根据门店组织id及skuIds查询级别价格返回：{}", payloadResult);

            if (!SUCCESS.equals(payloadResult.getCode())) {
                log.error("根据门店组织id及skuIds查询级别价格,返回码非success：{}",payloadResult);
                throw new ApplicationException(ResultEnum.BUSINESS_HTTP_ERROR);
            }
            List<LevelPriceResponseDTO> resultList = GeneralConvertUtils.convert2List(payloadResult.getPayload(), LevelPriceResponseDTO.class);
            if (CollectionUtil.isNotEmpty(resultList)) {
                for (LevelPriceResponseDTO r : resultList) {
                    resultMap.put(Long.valueOf(r.getSkuId()), r.getLevelPrice());
                }
            }
        } catch (Exception e) {
            logger.error("查询确定等级sku价格失败:", e);
            throw new ApplicationException(ResultEnum.BUSINESS_HTTP_ERROR);
        }
        return resultMap;

    }

    /**
     * 组装商品明细
     *
     * @param list
     * @param skuStockMap
     * @param skuPriceMap
     * @return
     */
    private List<SaleOrderItemDTO> getSaleOrderItems(List<ListShopItemUpShelfResponseDTO> list,
                                                     Map<Long, Long> skuStockMap,
                                                     Map<Long, BrandDTO> skuIdBrandMap,
                                                     Map<Long, BigDecimal> skuPriceMap,
                                                     Long activitiesId) {
        List<SaleOrderItemDTO> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list)) {
            for (ListShopItemUpShelfResponseDTO d : list) {
                SaleOrderItemDTO saleOrderItemDTO = new SaleOrderItemDTO();
                saleOrderItemDTO.setSkuId(d.getSkuId());
                saleOrderItemDTO.setSkuName(StringUtil.isEmpty(d.getSku().getSkuName()) ? d.getItemWhole().getName() : d.getSku().getSkuName());
                saleOrderItemDTO.setUnitId(d.getSku().getUnitId());
                saleOrderItemDTO.setUnitName(d.getSku().getUnitName());
                saleOrderItemDTO.setItemId(d.getItemId());
                saleOrderItemDTO.setItemName(d.getItemWhole().getName());
                saleOrderItemDTO.setSubName(d.getItemWhole().getSubName());
                saleOrderItemDTO.setStatus(d.getStatus());
                saleOrderItemDTO.setPurchasePrice(d.getSku().getPurchasePrice());
                //设置库存
                if (CollectionUtil.isNotEmpty(skuStockMap)) {
                    saleOrderItemDTO.setStock(skuStockMap.get(d.getSkuId()));
                }
                if (CollectionUtil.isNotEmpty(d.getSku().getSkuPropertyValueList())) {
                    saleOrderItemDTO.setPropertyValue(appendSkuProperty(d.getSku().getSkuPropertyValueList()));
                }
                saleOrderItemDTO.setSkuFormat(saleOrderItemDTO.getPropertyValue());
                saleOrderItemDTO.setSkuCode(d.getSku().getCode());
                saleOrderItemDTO.setUnitName(d.getSku().getUnitName());
                saleOrderItemDTO.setMajorPicture(getPicture(d));

                //如果有提供的价格并且价格>=0，从提供价格获取，否则获取sku的里面的价格
                if (CollectionUtil.isEmpty(skuPriceMap) || skuPriceMap.get(d.getSkuId()) == null || skuPriceMap.get(d.getSkuId()).compareTo(BigDecimal.ZERO) < 0  ) {
                    saleOrderItemDTO.setPrice(d.getSku().getPrice());
                } else {
                    saleOrderItemDTO.setPrice(skuPriceMap.get(d.getSkuId()));
                }

                //填充品牌信息
                if (CollectionUtil.isNotEmpty(skuIdBrandMap) && skuIdBrandMap.get(d.getSkuId()) != null) {
                    BrandDTO item = skuIdBrandMap.get(d.getSkuId());
                    saleOrderItemDTO.setBrandId(item.getId());
                    saleOrderItemDTO.setBrandCode(item.getCode());
                    saleOrderItemDTO.setBrandName(item.getChineseName());
                }

                saleOrderItemDTO.setCostPrice(d.getSku().getPurchasePrice() == null ? BigDecimal.valueOf(0) : d.getSku().getPurchasePrice());
                saleOrderItemDTO.setShopId(d.getShopId());
                saleOrderItemDTO.setActivitiesId(activitiesId);
                result.add(saleOrderItemDTO);
            }
        }
        return result;
    }


    /**
    * @Description: 获取图片如果sku没有图片使用spu的图片.
    * @Param: 
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/13
    */
    private String getPicture(ListShopItemUpShelfResponseDTO responseDTO) {
        if (CommonUtils.isNotEmpty(responseDTO.getSku().getMajorPicture())) {
            return responseDTO.getSku().getMajorPicture();
        }
        return responseDTO.getItemWhole().getMajorPicture();
    }

    /**
     * @Description: 添加sku的属性值.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/30
     */
    private String appendSkuProperty(List<ListShopItemUpShelfGroupSkuPropertyValueResponseDTO> skuPropertyList) {
        // 通过id进行倒序.
        Collections.sort(skuPropertyList, (s1, s2) -> s2.getId().compareTo(s1.getId()));
        StringBuilder sb = new StringBuilder();
        skuPropertyList.forEach(sku -> {
            sb.append(sku.getPropertyName());
            sb.append(":");
            sb.append(sku.getPropertyValue());
            sb.append(" ");
        });
        return sb.toString();
    }
}