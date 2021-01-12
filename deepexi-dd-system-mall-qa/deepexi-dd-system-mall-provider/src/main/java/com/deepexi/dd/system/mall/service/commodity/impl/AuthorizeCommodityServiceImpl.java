package com.deepexi.dd.system.mall.service.commodity.impl;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.business.api.BusinessSkuPriceOpenApi;
import com.deepexi.dd.domain.business.domain.dto.BusinessActivityResponseDTO;
import com.deepexi.dd.domain.business.domain.dto.BusinessSkuPriceAdminResponsDTO;
import com.deepexi.dd.domain.business.domain.dto.LevelPriceResponseDTO;
import com.deepexi.dd.domain.business.domain.query.BusinessActivityRequestQuery;
import com.deepexi.dd.domain.business.domain.query.BusinessSkuPriceAdminRequestQuery;
import com.deepexi.dd.domain.business.domain.query.LevelPriceQuery;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuAuthorizeOrgResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuRequestDTO;
import com.deepexi.dd.system.mall.converter.NewsCommodityResponseDTOConverter;
import com.deepexi.dd.system.mall.domain.dto.business.BusinessActivityDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.CategoryCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.CommodityDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityRecommendResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.query.app.AuthorizeSkuInfo;
import com.deepexi.dd.system.mall.domain.query.app.FrontCategoryTreeRequestQuery;
import com.deepexi.dd.system.mall.domain.query.commodity.CategoryCommodityRecommendQuery;
import com.deepexi.dd.system.mall.domain.query.commodity.NewsCommodityAdminQuery;
import com.deepexi.dd.system.mall.domain.query.commodity.NewsCommodityRecommendQuery;
import com.deepexi.dd.system.mall.domain.vo.app.FrontCategoryTreeResponseVO;
import com.deepexi.dd.system.mall.remote.business.BusinessActivityRemote;
import com.deepexi.dd.system.mall.remote.business.BusinessSkuPriceOpenClient;
import com.deepexi.dd.system.mall.service.BaseCommodityService;
import com.deepexi.dd.system.mall.service.app.CommodityCoreService;
import com.deepexi.dd.system.mall.service.commodity.AuthorizeCommodityService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.dd.system.mall.util.PayloadUtils;
import com.deepexi.sdk.commodity.api.ShelvesItemSkuOpenApi;
import com.deepexi.sdk.commodity.model.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.MerchantResponseDTO;
import domain.dto.StoreResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthorizeCommodityServiceImpl extends BaseCommodityService implements AuthorizeCommodityService {

    @Autowired
    private ShelvesItemSkuOpenApi shelvesItemSkuOpenApi;

    @Autowired
    private BusinessSkuPriceOpenApi businessSkuPriceOpenApi;

    @Autowired
    private BusinessSkuPriceOpenClient businessSkuPriceOpenClient;

    @Autowired
    private CommodityCoreService commodityCoreService;

    @Autowired
    private BusinessActivityRemote businessActivityRemote;

    public static final Integer SORT_TYPE = 5;

    public static final Integer NEWS_LINE = 10;

    public static final Integer CATEGORY_LINE = 4;

    @Value("${brand.bussiness.companytype.id}")
    private Long brandBussinessCompanyTypeId;

    @Override
    public List<NewsCommodityResponseDTO> getNewsCommodity(NewsCommodityAdminQuery query) throws Exception {
        // 如果是gree账号直接返回.
        if (ORG_CODE.equals(appRuntimeEnv.getUserOrganization().getCode())) {
            return Lists.newArrayList();
        }
        appRuntimeEnv.setAppId(query.getAppId());
        // 获取当前用户的业务伙伴.
        BusinessPartnerResponseDTO partnerDTO = getPartner(String.valueOf(appRuntimeEnv.getTopOrganization().getId()), query.getAppId());
        // 获取业务伙伴对应的客户.
        List<MerchantResponseDTO> customerList = getCustomer(partnerDTO.getId(), query.getAppId());
        // 获取业务伙伴下面被授权的客户.
        List<MerchantResponseDTO> authorizeList = getAuthorizeCustomer(customerList, query.getAppId());
        if (CommonUtils.isEmpty(authorizeList)) {
            throw new ApplicationException("当前用户没有订货授权的客户.");
        }
        // 通过授权的客户查询对应的门店.
        List<StoreResponseDTO> storeList = getStoreInfos(authorizeList.stream().map(s -> Long.valueOf(s.getIsolationId())).distinct().collect(Collectors.toList()));
        if (CommonUtils.isEmpty(storeList)) {
            throw new ApplicationException("当前用户没有可查询区域订货的门店.");
        }
        // 转换MAP便于后续操作.
        Map<Long, Long> storeMap = storeList.stream().collect(Collectors.toMap(StoreResponseDTO::getId, StoreResponseDTO::getOrgId));
        // 然后查询最新的10条记录.
        List<NewsCommodityResponseDTO> commodityList = getCommodityDesc(query, storeList, storeMap);
        if (CommonUtils.isEmpty(commodityList)) {
            return Lists.newArrayList();
        }
        // 查询商品最终定价
        Map<Long, List<NewsCommodityResponseDTO>> returnMap = commodityList.stream().collect(Collectors.groupingBy(NewsCommodityResponseDTO::getShopId));
        // 反查sku是被哪些一级组织授权的.
        Map<Long, Map<Long, Long>> skuMap = findSkuFistOrg(returnMap, storeMap, customerList);
        returnMap.forEach((k, v) -> {
            try {
                List<MerchantResponseDTO> customers = customerList.stream().filter(s -> s.getIsolationId().equals(String.valueOf(storeMap.get(k)))).collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(customers)) {
                    List<Long> skuIds = v.stream().map(NewsCommodityResponseDTO::getSkuIds).flatMap(Collection::stream).collect(Collectors.toList());
                    Map<String, List<BusinessSkuPriceAdminResponsDTO>> customerMap = Maps.newHashMap();
                    for (MerchantResponseDTO merchant : customers) {
                        BusinessSkuPriceAdminRequestQuery skuPriceQuery = new BusinessSkuPriceAdminRequestQuery();
                        skuPriceQuery.setCustomerId(merchant.getId());
                        skuPriceQuery.setIsolationId(merchant.getIsolationId());
                        skuPriceQuery.setSkuIds(skuIds);
                        skuPriceQuery.setAppId(merchant.getAppId());
                        skuPriceQuery.setTenantId(appRuntimeEnv.getTenantId());
                        Payload<List<BusinessSkuPriceAdminResponsDTO>> skuPricePayload = businessSkuPriceOpenClient.queryPriceBySkuIds(skuPriceQuery);
                        List<BusinessSkuPriceAdminResponsDTO> skuPriceList = GeneralConvertUtils.convert2List(skuPricePayload.getPayload(), BusinessSkuPriceAdminResponsDTO.class);
                        if (CommonUtils.isNotEmpty(skuPriceList)) {
                            Collections.sort(skuPriceList, (v1, v2) -> v1.getTyp().compareTo(v2.getTyp()));
                            customerMap.put(merchant.getOrgId(), skuPriceList);
                        }
                    }
                    setPrice(v, customerMap, skuMap.get(k));
                }
            } catch (Exception e) {
                log.error("获取客户信息错误：{}", e);
            }
        });

        //转换name到subName
        String subName = "";
        for (NewsCommodityResponseDTO newsCommodityResponseDTO : commodityList) {
            subName = newsCommodityResponseDTO.getSubName();
            if (StringUtils.isEmpty(subName)) {
                newsCommodityResponseDTO.setSubName(newsCommodityResponseDTO.getName());
            }
        }

        return commodityList;
    }

    /**
     * 商城首页-查询新品推荐
     *
     * @param query
     * @return
     */
    @Override
    public List<NewsCommodityRecommendResponseDTO> getNewsCommodityResponse(NewsCommodityRecommendQuery query) throws Exception {
        // 查询最新的10条记录
        List<NewsCommodityRecommendResponseDTO> commodityList = getNewsCommodityDesc(query);
        if (CommonUtils.isEmpty(commodityList)) {
            return Lists.newArrayList();
        }
        return commodityList;
    }

    /**
     * 商城首页-热门分类
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<CategoryCommodityResponseDTO> getCategoryCommodityResponseDTO(CategoryCommodityRecommendQuery query) throws Exception {
        //获取所有1级分类
        FrontCategoryTreeRequestQuery frontCategoryTreeRequestQuery = new FrontCategoryTreeRequestQuery();
        frontCategoryTreeRequestQuery.setAppId(query.getAppId());
        frontCategoryTreeRequestQuery.setTenantId(query.getTenantId());
        List<FrontCategoryTreeResponseVO> frontCategoryTreeResponseVOS = commodityCoreService.listFrontCategoryTree(frontCategoryTreeRequestQuery);
        log.info("商城首页-热门分类获取的一级类目信息为:{}", JsonUtil.bean2JsonString(frontCategoryTreeResponseVOS));
        List<Long> categoryIds = new ArrayList<>();
        Map<Long, FrontCategoryTreeResponseVO> mapFrontCategoryTreeResponseVO = new HashMap<>();
        if(CollectionUtil.isNotEmpty(frontCategoryTreeResponseVOS)){
            categoryIds = frontCategoryTreeResponseVOS.stream().map(FrontCategoryTreeResponseVO::getId).collect(Collectors.toList());
            mapFrontCategoryTreeResponseVO = frontCategoryTreeResponseVOS.stream().collect(Collectors.toMap(FrontCategoryTreeResponseVO::getId, v -> v));
        }
        List<CategoryCommodityResponseDTO> result = new ArrayList<>();
        //查询类目下商品
        if (CollectionUtil.isNotEmpty(categoryIds)){
            for (Long categoryId : categoryIds) {
                FrontCategoryTreeResponseVO orDefault = mapFrontCategoryTreeResponseVO.getOrDefault(categoryId, new FrontCategoryTreeResponseVO());
                CategoryCommodityResponseDTO categoryCommodityResponseDTO = orDefault.clone(CategoryCommodityResponseDTO.class);
                PageShelvesItemSkuRequestDTO itemQuery = new PageShelvesItemSkuRequestDTO();
                itemQuery.setAppId(query.getAppId());
                itemQuery.setTenantId(query.getTenantId());
                itemQuery.setPage(1);
                itemQuery.setSize(CATEGORY_LINE);
                itemQuery.setSortType(SORT_TYPE);
                itemQuery.setFrontCategoryId(categoryId);
                log.info("查询类目商品入参:{}",JsonUtil.bean2JsonString(itemQuery));
                //查询当前类目下4个商品
                PayloadPageBeanPageShelvesItemSkuResponseDTO responseDTO = shelvesItemSkuOpenApi.pageShelvesItemSku(itemQuery);
                PayloadUtils.assertSuccess(responseDTO);
                List<PageShelvesItemSkuResponseDTO> content = responseDTO.getPayload().getContent();
                log.info("getCommodityDesc.skuList:{}", JSON.toJSONString(content));
                if(CollectionUtil.isEmpty(content)){
                    categoryCommodityResponseDTO.setCommodityDTOS(CollectionUtil.createArrayList());
                }else{
                //组装结果
                    List<CommodityDTO> commodityDTOS = new ArrayList<>();
                    content.forEach(item -> {
                        try{
                            NewsCommodityRecommendResponseDTO convert = convert(appRuntimeEnv.getUserOrganization().getId(), item);
                            commodityDTOS.add(convert.clone(CommodityDTO.class));
                        }catch (Exception e){
                            e.printStackTrace();
                            throw new ApplicationException("热门分类商品数据转换异常");
                        }
                    });
                    categoryCommodityResponseDTO.setCommodityDTOS(commodityDTOS);
                }
                result.add(categoryCommodityResponseDTO);
            }
        }
        return result;
    }

    /**
     * 查询最新的10条记录(新品推荐)
     *
     * @param query
     * @return
     */
    private List<NewsCommodityRecommendResponseDTO> getNewsCommodityDesc(NewsCommodityRecommendQuery query) {
        PageShelvesItemSkuRequestDTO requestDTO = new PageShelvesItemSkuRequestDTO();
        requestDTO.setAppId(appRuntimeEnv.getAppId());
        requestDTO.setTenantId(appRuntimeEnv.getTenantId());
        requestDTO.setPage(1);
        requestDTO.setSize(NEWS_LINE);
        requestDTO.setSortType(SORT_TYPE);
        log.info("getCommodityDesc.sku query:{}", JSON.toJSONString(requestDTO));
        //查询最新十条商品
        PayloadPageBeanPageShelvesItemSkuResponseDTO payload = shelvesItemSkuOpenApi.pageShelvesItemSku(requestDTO);
        PayloadUtils.assertSuccess(payload);
        PageBeanPageShelvesItemSkuResponseDTO pageBean = payload.getPayload();
        List<PageShelvesItemSkuResponseDTO> content = pageBean.getContent();
        List<NewsCommodityRecommendResponseDTO> resultList = CollectionUtil.createArrayList();
        if (CollectionUtil.isEmpty(content)) {
            return CollectionUtil.createArrayList();
        }
        log.info("getCommodityDesc.skuList:{}", JSON.toJSONString(content));
        // 组装结果
        content.forEach(t -> {
            try {
                NewsCommodityRecommendResponseDTO convert = convert(appRuntimeEnv.getTopOrganization().getId(),t);
                resultList.add(convert);
            } catch (Exception e) {
                log.info("Exception:", JSON.toJSONString(e));
                e.printStackTrace();
            }
            log.info("resultList：{}", JSON.toJSONString(resultList));
        });
        Map<Long, NewsCommodityRecommendResponseDTO> commodityMap = resultList.stream().collect(Collectors.toMap(NewsCommodityRecommendResponseDTO::getId, Function.identity()));
        // 筛选出所有商品ID集合
        List<Long> itemIds = resultList.stream().map(NewsCommodityRecommendResponseDTO::getId).collect(Collectors.toList());
        // 根据商品ID集合查询这些商品正在进行的活动
        BusinessActivityRequestQuery activityQuery = new BusinessActivityRequestQuery();
        activityQuery.setItemIds(itemIds);
        List<BusinessActivityResponseDTO> activityList = businessActivityRemote.findRunningActivityByItem(activityQuery);
        if (CollectionUtil.isNotEmpty(activityList)) {
            activityList.forEach(activity -> {
                if (commodityMap.containsKey(activity.getItemId())) {
                    commodityMap.get(activity.getItemId()).setActivity(activity.clone(BusinessActivityDTO.class));
                }
            });
        }

        return resultList;
    }

    /**
     * @Description: 查询最新的10条记录.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    private List<NewsCommodityResponseDTO> getCommodityDesc(NewsCommodityAdminQuery query, List<StoreResponseDTO> storeList, Map<Long, Long> storeMap) {
        List<String> itemList = getItemBySku(GeneralConvertUtils.convert2List(query.getSkuInfos(), AuthorizeSkuInfo.class), query.getAppId(), storeList);
        if (CommonUtils.isEmpty(itemList)) {
            return Lists.newArrayList();
        }
        log.info("getCommodityDesc.itemList:{}", JSON.toJSONString(itemList));
        PageShelvesItemSkuRequestDTO requestDTO = new PageShelvesItemSkuRequestDTO();
        requestDTO.setAppId(appRuntimeEnv.getAppId());
        requestDTO.setTenantId(appRuntimeEnv.getTenantId());
        requestDTO.setPage(1);
        requestDTO.setSize(NEWS_LINE);
        requestDTO.setSortType(SORT_TYPE);
        requestDTO.setShopItemList(itemList);
        log.info("getCommodityDesc.sku query:{}", JSON.toJSONString(requestDTO));
        PayloadPageBeanPageShelvesItemSkuResponseDTO payload = shelvesItemSkuOpenApi.pageShelvesItemSku(requestDTO);
        PayloadUtils.assertSuccess(payload);
        PageBeanPageShelvesItemSkuResponseDTO pageBean = payload.getPayload();
        List<PageShelvesItemSkuResponseDTO> content = pageBean.getContent();
        List<NewsCommodityResponseDTO> resultList = CollectionUtil.createArrayList();
        if (CollectionUtil.isEmpty(content)) {
            return CollectionUtil.createArrayList();
        }
        log.info("getCommodityDesc.skuList:{}", JSON.toJSONString(content));
        // 过滤未授权的商品sku
        if (CollectionUtil.isNotEmpty(query.getSkuInfos())) {
            List<Long> skuIds = query.getSkuInfos().stream().map(NewsCommodityAdminQuery.NewsSkuInfo::getSkuIds).flatMap(Collection::stream).collect(Collectors.toList());
            content = filterAuthorizeCommodity(content, skuIds);
        }
        // 组装结果
        content.forEach(t -> {
            NewsCommodityResponseDTO convert = NewsCommodityResponseDTOConverter.convert(t);
            convert.setAuthorizeBy(storeMap.get(convert.getShopId()));
            resultList.add(convert);
        });
        return resultList;
    }
    private NewsCommodityRecommendResponseDTO convert(Long orgId,PageShelvesItemSkuResponseDTO dto) throws Exception{
        NewsCommodityRecommendResponseDTO result = new NewsCommodityRecommendResponseDTO();
        result.setChannelId(dto.getChannelId());
        result.setId(dto.getItemWhole().getId());
        result.setItemReleaseShop(dto.getItemReleaseShop());
        result.setMajorPicture(dto.getItemWhole().getMajorPicture());
        result.setName(dto.getItemWhole().getName());
        result.setSubName(dto.getItemWhole().getSubName());
        result.setRemark(dto.getItemWhole().getRemark());
        result.setShopId(dto.getShopId());

        // 排序级别批发价：
        List<PageShelvesItemSkuGroupSkuResponseDTO> pageShelvesItemSkuGroupSkuResponseDTOList = dto.getSkuList();
        List<PageShelvesItemSkuGroupSkuResponseDTO> skuGroupSkuResponseDTOList = new ArrayList<>();
        if (CollectionUtil.isEmpty(pageShelvesItemSkuGroupSkuResponseDTOList)) {
            result.setSalePrice(BigDecimal.valueOf(0));
            result.setSuggestPrice(BigDecimal.valueOf(0));
        } else {
            for (PageShelvesItemSkuGroupSkuResponseDTO groupSkuResponseDTO:pageShelvesItemSkuGroupSkuResponseDTOList) {
                PageShelvesItemSkuGroupSkuResponseDTO itemSkuGroupSkuResponseDTO = GeneralConvertUtils.conv(groupSkuResponseDTO,PageShelvesItemSkuGroupSkuResponseDTO.class);
                LevelPriceQuery levelPriceQuery = new LevelPriceQuery();
                levelPriceQuery.setGroupId(orgId);
                List<Long> skuIds = new ArrayList<>();
                skuIds.add(groupSkuResponseDTO.getId());
                levelPriceQuery.setSkuIds(skuIds);
                String jsonString = JSON.toJSONString(businessSkuPriceOpenApi.queryLevelPriceBySkuIdsAndGroupId(levelPriceQuery).getPayload());
                List<LevelPriceResponseDTO> levelPriceResponseDTOList = JSON.parseArray(jsonString, LevelPriceResponseDTO.class);
                if (CollectionUtil.isNotEmpty(levelPriceResponseDTOList)) {
                    if (null != levelPriceResponseDTOList.get(0).getLevelPrice()) {
                        itemSkuGroupSkuResponseDTO.setTradePrice(levelPriceResponseDTOList.get(0).getLevelPrice());
                    } else {
                        itemSkuGroupSkuResponseDTO.setTradePrice(groupSkuResponseDTO.getPrice());
                    }
                } else {
                    itemSkuGroupSkuResponseDTO.setTradePrice(groupSkuResponseDTO.getPrice());
                }
                skuGroupSkuResponseDTOList.add(itemSkuGroupSkuResponseDTO);
            }

            // 根据批发价正序（从小到大）排列
            List<PageShelvesItemSkuGroupSkuResponseDTO> newList = skuGroupSkuResponseDTOList.stream().sorted(Comparator.comparing(PageShelvesItemSkuGroupSkuResponseDTO::getTradePrice))
                    .collect(Collectors.toList());
            if (newList.get(0).getPrice() == null) {
                result.setSuggestPrice(newList.get(0).getTradePrice());
            } else {
                result.setSuggestPrice(newList.get(0).getPrice());
            }
            result.setSalePrice(newList.get(0).getTradePrice());
            result.setSkuCode(newList.get(0).getCode());
        }
        //result.setSkuIds(dto.getSkuList().stream().map(PageShelvesItemSkuGroupSkuResponseDTO::getId).collect(Collectors.toList()));
        if (Objects.nonNull(dto.getItemWhole())) {
            result.setItemTagList(dto.getItemWhole().getItemTagList());
        }
        return result;
    }

    /**
     * @Description: 查询sku是被哪些一级组织授权的.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    private Map<Long, Map<Long, Long>> findSkuFistOrg(Map<Long, List<NewsCommodityResponseDTO>> returnMap,
                                                      Map<Long, Long> storeMap,
                                                      List<MerchantResponseDTO> customerList) throws Exception {
        Map<Long, Map<Long, Long>> map = Maps.newHashMap();
        Iterator<Map.Entry<Long, List<NewsCommodityResponseDTO>>> iterator = returnMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<NewsCommodityResponseDTO>> next = iterator.next();
            ToolSkuRequestDTO toolSkuRequestDTO = new ToolSkuRequestDTO();
            toolSkuRequestDTO.setAppId(appRuntimeEnv.getAppId());
            toolSkuRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            toolSkuRequestDTO.setOrgId(storeMap.get(next.getKey()));
            List<Long> skuIds = next.getValue().stream().map(NewsCommodityResponseDTO::getSkuIds).flatMap(Collection::stream).collect(Collectors.toList());
            if (CommonUtils.isEmpty(skuIds)) {
                log.info("AuthorizeCommodity skuId is null key:{}", next.getKey());
                continue;
            }
            toolSkuRequestDTO.setSkuIds(org.apache.commons.lang3.StringUtils.join(skuIds, ","));
            Payload<List<ToolSkuAuthorizeOrgResponseDTO>> payload = apiRemote.getAuthorizeOrgBySkuId(toolSkuRequestDTO);
            if (CommonUtils.isNotEmpty(payload.getPayload())) {
                List<ToolSkuAuthorizeOrgResponseDTO> authorizeList = GeneralConvertUtils.convert2List(payload.getPayload(), ToolSkuAuthorizeOrgResponseDTO.class);
                Map<Long, Long> skuMap = Maps.newHashMap();
                authorizeList.forEach(data -> {
                    if (Objects.nonNull(data.getOrgId())) {
                        // 如果有一级组织就设置一级组织.
                        skuMap.put(data.getSkuId(), matchCustomer(customerList, data.getOrgId(), storeMap.get(next.getKey())));
                    } else {
                        // 如果没有一级组织给顶级组织.
                        skuMap.put(data.getSkuId(), storeMap.get(next.getKey()));
                    }
                });
                map.put(next.getKey(), skuMap);
            } else {
                Map<Long, Long> skuMap = Maps.newHashMap();
                skuIds.forEach(skuId -> skuMap.put(skuId, storeMap.get(next.getKey())));
                map.put(next.getKey(), skuMap);
            }
        }
        return map;
    }

    /**
     * @Description: 设置最终的商品价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    private void setPrice(List<NewsCommodityResponseDTO> commodityList, Map<String, List<BusinessSkuPriceAdminResponsDTO>> customerMap, Map<Long, Long> skuMap) {
        if (CommonUtils.isEmpty(skuMap)) {
            return;
        }
        commodityList.forEach(data -> {
            List<BigDecimal> tmpPrices = CollectionUtil.createArrayList();
            data.getSkuIds().forEach(skuId -> {
                Long orgId = skuMap.get(skuId);
                List<BusinessSkuPriceAdminResponsDTO> skuPrices = customerMap.get(String.valueOf(orgId));
                if (CommonUtils.isEmpty(skuPrices)) {
                    tmpPrices.add(BigDecimal.valueOf(0));
                } else {
                    tmpPrices.add(getPrice(skuPrices, skuId));
                }
            });
            if (CommonUtils.isEmpty(tmpPrices)) {
                data.setPrice(BigDecimal.valueOf(0));
            } else {
                data.setPrice(Collections.min(tmpPrices));
            }
        });
    }

    private List<PageShelvesItemSkuResponseDTO> filterAuthorizeCommodity(List<PageShelvesItemSkuResponseDTO> content, List<Long> skuIds) {
        content.forEach(t -> {
            List<PageShelvesItemSkuGroupSkuResponseDTO> skuList = t.getSkuList();
            List<PageShelvesItemSkuGroupSkuResponseDTO> authSkuList = skuList.stream().
                    filter(sku -> skuIds.contains(sku.getId())).
                    collect(Collectors.toList());
            t.setSkuList(authSkuList);
        });
        return content;
    }
}
