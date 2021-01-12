package com.deepexi.dd.system.mall.service.app.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepexi.clientiam.api.UserControllerApi;
import com.deepexi.clientiam.model.GysUserVO;
import com.deepexi.clientiam.model.PayloadGysUserVO;
import com.deepexi.dd.domain.business.api.BusinessSkuPriceOpenApi;
import com.deepexi.dd.domain.business.domain.dto.BusinessSkuPriceAdminResponsDTO;
import com.deepexi.dd.domain.business.domain.dto.LevelPriceResponseDTO;
import com.deepexi.dd.domain.business.domain.query.BusinessSkuPriceAdminRequestQuery;
import com.deepexi.dd.domain.business.domain.query.LevelPriceQuery;
import com.deepexi.dd.domain.common.domain.dto.SuppliersResponseDTO;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuAuthorizeOrgResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.query.app.AuthorizeSkuInfo;
import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreQuery;
import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.FrontCategoryTreeRequestQuery;
import com.deepexi.dd.system.mall.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.FrontCategoryTreeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.dd.system.mall.remote.business.BusinessSkuPriceOpenClient;
import com.deepexi.dd.system.mall.remote.customer.CompanyInfoClient;
import com.deepexi.dd.system.mall.service.BaseCommodityService;
import com.deepexi.dd.system.mall.service.ShoppingCartService;
import com.deepexi.dd.system.mall.service.app.CommodityCoreService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.sdk.commodity.api.CategoryFrontOpenApi;
import com.deepexi.sdk.commodity.api.ShelvesItemSkuOpenApi;
import com.deepexi.sdk.commodity.model.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import domain.dto.BusinessPartnerResponseDTO;
import domain.dto.CompanyInfoResponseApiDTO;
import domain.dto.MerchantResponseDTO;
import domain.dto.StoreResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-10 14:16
 */
@Service
@Slf4j
public class CommodityCoreServiceImpl extends BaseCommodityService implements CommodityCoreService {

    @Autowired
    private BusinessSkuPriceOpenApi businessSkuPriceOpenApi;

    @Autowired
    private ShelvesItemSkuOpenApi shelvesItemSkuOpenApi;

    @Autowired
    private CategoryFrontOpenApi categoryFrontOpenApi;

    @Autowired
    private BusinessSkuPriceOpenClient skuPriceClient;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private CompanyInfoClient companyInfoClient;

    @Autowired
    private UserControllerApi userControllerApi;

    @Override
    public PageBean<CommodityTypeResponseVO> pageShelvesItemSku(CommodityCoreQuery query) {
        if (StringUtils.isEmpty(query.getItemNameFuzzy())) {
            query.setItemNameFuzzy(null);
        } else {
            query.setName(query.getItemNameFuzzy());
            query.setItemNameFuzzy(null);
        }
        log.info("调用中台入参" + JSONObject.toJSONString(query));
        PayloadPageBeanPageShelvesItemSkuResponseDTO result =
                shelvesItemSkuOpenApi.pageShelvesItemSku(GeneralConvertUtils.conv(query, PageShelvesItemSkuRequestDTO.class));
        log.info("中台返回结果" + JSONObject.toJSONString(result));
        if (!"0".equals(result.getCode())) {
            log.error("获取中台数据出错了");
            throw new ApplicationException(result.getMsg());
        }
        List<CommodityTypeResponseVO> resultList = GeneralConvertUtils.convert2List(
                result.getPayload().getContent(), CommodityTypeResponseVO.class);
        resultList.forEach(r -> {
            r.setCode(result.getCode());
            r.setMsg(result.getMsg());
            initPrice(r);
        });
        //转换name到subName
        String subName;
        for (CommodityTypeResponseVO commodityTypeResponseVO : resultList) {
            subName = commodityTypeResponseVO.getItemWhole().getSubName();
            if (StringUtils.isEmpty(subName)) {
                commodityTypeResponseVO.getItemWhole().setSubName(commodityTypeResponseVO.getItemWhole().getName());
            }
            List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> skuList = commodityTypeResponseVO.getSkuList();
            List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> skuListNew = CollectionUtil.createArrayList();
            for (CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO pageShelvesItemSkuGroupSkuResponseDTO : skuList) {
                try {
                    Long skuId = pageShelvesItemSkuGroupSkuResponseDTO.getId();
                    log.info("skuId:{}", JSON.toJSONString(skuId));
                    //如果平台端未对SKU设置“批发价”，则展示“建议零售价”
                    LevelPriceQuery levelPriceQuery = new LevelPriceQuery();
                    levelPriceQuery.setGroupId(appRuntimeEnv.getTopOrganization().getId());
                    List<Long> skuIds = new ArrayList<>();
                    skuIds.add(skuId);
                    levelPriceQuery.setSkuIds(skuIds);
                    // 查询对应级别的批发价
                    String jsonString = JSON.toJSONString(businessSkuPriceOpenApi.queryLevelPriceBySkuIdsAndGroupId(levelPriceQuery).getPayload());
                    List<LevelPriceResponseDTO> levelPriceResponseDTOList = JSON.parseArray(jsonString, LevelPriceResponseDTO.class);
                    if (CollectionUtil.isNotEmpty(levelPriceResponseDTOList)) {
                        if (null != levelPriceResponseDTOList.get(0)) {
                            if (null != levelPriceResponseDTOList.get(0).getLevelPrice()) {
                                pageShelvesItemSkuGroupSkuResponseDTO.setTradePrice(levelPriceResponseDTOList.get(0).getLevelPrice());
                            } else {
                                pageShelvesItemSkuGroupSkuResponseDTO.setTradePrice(pageShelvesItemSkuGroupSkuResponseDTO.getPrice());
                            }
                        }
                    }
                    skuListNew.add(pageShelvesItemSkuGroupSkuResponseDTO);
                    log.info("skuListNew:{}", JSON.toJSONString(skuListNew));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (CollectionUtil.isNotEmpty(skuListNew)) {
                commodityTypeResponseVO.setSkuList(skuListNew);
            }
        }
        return getResultBody(resultList, result);
    }

    /**
     * @Description: 如果价格为null, 默认初始化为0.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/3
     */
    private void initPrice(CommodityTypeResponseVO responseVO) {
        if (CommonUtils.isNotEmpty(responseVO.getSkuList())) {
            responseVO.getSkuList().forEach(s -> {
                if (Objects.isNull(s.getTradePrice())) {
                    s.setTradePrice(BigDecimal.valueOf(0));
                }
            });
        }
    }

    @Override
    public List<FrontCategoryTreeResponseVO> listFrontCategoryTree(@Valid FrontCategoryTreeRequestQuery query) {
        GetFrontCategoryTreeRequestDTO requestQuery = query.clone(GetFrontCategoryTreeRequestDTO.class);
        requestQuery.setEnabled(true);
        requestQuery.setPage(-1);
        PayloadListGetFrontCategoryTreeResponseDTO result =
                categoryFrontOpenApi.getFrontCategoryTree(requestQuery);
        if (!result.getCode().equals("0")) {
            log.error("获取中台数据出错了");
            throw new ApplicationException(result.getMsg());
        }
        List<FrontCategoryTreeResponseVO> resultList = GeneralConvertUtils.convert2List(
                result.getPayload(), FrontCategoryTreeResponseVO.class);
        resultList.forEach(r -> {
            r.setMsg(result.getMsg());
            r.setCode(result.getCode());
        });
        return resultList;
    }

    @Override
    public ShelvesItemSkuDetailResponseVO getShelvesItemSkuDetail(CommodityCoreRequestQuery query) throws Exception {
        PayloadGetShelvesItemSkuDetailResponseDTO result =
                shelvesItemSkuOpenApi.getShelvesItemSkuDetail(query.clone(GetShelvesItemSkuDetailRequestDTO.class));
        if (!result.getCode().equals("0")) {
            log.error("获取中台数据出错了");
            throw new ApplicationException(ResultEnum.COMMODITY_SOLD_OUT);
        } else if (null == result.getPayload()) {
            throw new ApplicationException(ResultEnum.COMMODITY_SOLD_OUT);
        }
        log.info("result:{}", JSON.toJSONString(result));
        List<CommodityTypeResponseVO> resultList =
                GeneralConvertUtils.convert2List(Arrays.asList(result.getPayload()), CommodityTypeResponseVO.class);
        log.info("resultList:{}", JSON.toJSONString(resultList));
        //转换name到subName
        String subName;
        for (CommodityTypeResponseVO commodityTypeResponseVO : resultList) {
            if (null != commodityTypeResponseVO.getItemWhole()) {
                subName = commodityTypeResponseVO.getItemWhole().getSubName();
                if (StringUtils.isEmpty(subName)) {
                    commodityTypeResponseVO.getItemWhole().setSubName(commodityTypeResponseVO.getItemWhole().getName());
                }
            }
        }
        CommodityTypeResponseVO commodityTypeResponseVO = resultList.get(0);
        if (CollectionUtil.isNotEmpty(commodityTypeResponseVO.getSkuList())) {
            List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> list = commodityTypeResponseVO.getSkuList();
            List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> listNew = new ArrayList<>();
            for (CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO dto : list) {
                Long skuId = dto.getId();
                LevelPriceQuery levelPriceQuery = new LevelPriceQuery();
                levelPriceQuery.setGroupId(appRuntimeEnv.getTopOrganization().getId());
                List<Long> skuIds = new ArrayList<>();
                skuIds.add(skuId);
                levelPriceQuery.setSkuIds(skuIds);
                String jsonString = JSON.toJSONString(businessSkuPriceOpenApi.queryLevelPriceBySkuIdsAndGroupId(levelPriceQuery).getPayload());
                List<LevelPriceResponseDTO> levelPriceResponseDTOList = JSON.parseArray(jsonString, LevelPriceResponseDTO.class);
                log.info("levelPriceResponseDTOList：{}", JSON.toJSONString(levelPriceResponseDTOList));
                if (CollectionUtil.isNotEmpty(levelPriceResponseDTOList)) {
                    if (null != levelPriceResponseDTOList.get(0)) {
                        if (null != levelPriceResponseDTOList.get(0).getLevelPrice()) {
                            dto.setTradePrice(levelPriceResponseDTOList.get(0).getLevelPrice());
                        } else {
                            dto.setTradePrice(dto.getPrice());
                        }
                    }
                }
                listNew.add(dto);
            }
            log.info("listNew:{}", JSON.toJSONString(listNew));
            commodityTypeResponseVO.setSkuList(listNew);
        }

        log.info("resultList:{}", JSON.toJSONString(resultList));
        ShelvesItemSkuDetailResponseVO vo = GeneralConvertUtils.conv(commodityTypeResponseVO, ShelvesItemSkuDetailResponseVO.class);
        //调用接口获取供应商信息
        List<Long> ids = new ArrayList<>();
        ids.add(vo.getShopId());
        String jsonString = JSON.toJSONString(companyInfoClient.listCompanyInfosByCompanyIds(ids).getPayload());
        List<CompanyInfoResponseApiDTO> companyInfos = JSON.parseArray(jsonString, CompanyInfoResponseApiDTO.class);
        if (CollectionUtil.isNotEmpty(companyInfos)) {
            vo.setSupplierName(companyInfos.get(0).getCompanyName());
            vo.setSupplierId(companyInfos.get(0).getId());
            log.info("根据组织ID获取供应商信息：{}", appRuntimeEnv.getTenantId() + companyInfos.get(0).getCompanyId());
            PayloadGysUserVO payloadGysUserVO = userControllerApi.getGysUserByGroupId(appRuntimeEnv.getTenantId(),companyInfos.get(0).getCompanyId());
            log.info("根据组织ID获取供应商信息,返回结果：{}", payloadGysUserVO);
            if (null != payloadGysUserVO) {
                GysUserVO gysUserVO = payloadGysUserVO.getPayload();
                if (null != gysUserVO) {
                    vo.setSupplierCode(gysUserVO.getExtend3());
                }
            }
        }
        vo.setCode(result.getCode());
        vo.setMsg(result.getMsg());
        return vo;
    }

    /**
     * @Description: 查询商品spu列表.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/24
     */
    @Override
    public PageBean<CommodityTypeResponseVO> findCommodityItemList(CommodityCoreQuery coreQuery) throws Exception {
        if (StringUtils.isEmpty(coreQuery.getItemNameFuzzy())) {
            coreQuery.setItemNameFuzzy(null);
        }
        if (CommonUtils.isEmpty(coreQuery.getAuthorizeSkuList())) {
            coreQuery.setSkuIdList(null);
        }
        // 查询上游门店信息.
        List<StoreResponseDTO> shopList = setSuppliersStore(coreQuery);
        if (CommonUtils.isEmpty(shopList)) {
            log.info("查询不到上游门店非直供门店.");
            return getResultBody(Lists.newArrayList(), getDefualtSkuResponse());
        }
        List<String> itemList = getItemBySku(coreQuery.getAuthorizeSkuList(), coreQuery.getAppId(), shopList);
        if (CommonUtils.isEmpty(itemList)) {
            log.info("查询不到对应的item信息.");
            return getResultBody(Lists.newArrayList(), getDefualtSkuResponse());
        }
        // 查询指定店铺的商品数据.
        PageShelvesItemSkuRequestDTO skuRequestDTO = GeneralConvertUtils.conv(coreQuery, PageShelvesItemSkuRequestDTO.class);
        skuRequestDTO.setSkuIdList(null);
        skuRequestDTO.setShopItemList(itemList);
        PayloadPageBeanPageShelvesItemSkuResponseDTO result = shelvesItemSkuOpenApi.pageShelvesItemSku(skuRequestDTO);
        if (!result.getCode().equals("0")) {
            throw new ApplicationException(result.getMsg());
        }
        // 转换对象.
        List<CommodityTypeResponseVO> resultList = GeneralConvertUtils.convert2List(result.getPayload().getContent(), CommodityTypeResponseVO.class);
        if (CommonUtils.isNotEmpty(resultList)) {
            // 获取授权价格
            getAuthorizeThePrice(coreQuery, result, resultList);
        }
        // 把所有的购物车从Redis缓存中查询出来,然后逐一的去匹配,然后覆盖数量.
        ShoppingCartQuery cartQuery = new ShoppingCartQuery();
        cartQuery.setTenantId(appRuntimeEnv.getTenantId());
        cartQuery.setAppId(coreQuery.getAppId());
        Map<String, ShoppingCartCommodityResponseDTO> shoppingMap = Optional.ofNullable(
                cartService.searchShoppingToApp(cartQuery)).orElse(Lists.newArrayList())
                .stream().collect(Collectors.toMap(s -> getKey(s.getShopId(), s.getSkuId()), Function.identity()));
        resultList.forEach(r -> {
            r.setCode(result.getCode());
            r.setMsg(result.getMsg());
            // 设置sku的数量.
            setShoppingNumber(shoppingMap, r);
            // 转换name到subName
            String subName = r.getItemWhole().getSubName();
            if (StringUtils.isEmpty(subName)) {
                r.getItemWhole().setSubName(r.getItemWhole().getName());
            }
        });
        // 分页
        return getResultBody(resultList, result);
    }

    /**
     * @Description: 设置商品的数量.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    private void setShoppingNumber(Map<String, ShoppingCartCommodityResponseDTO> shoppingMap, CommodityTypeResponseVO responseVO) {
        if (CommonUtils.isEmpty(shoppingMap) || CommonUtils.isEmpty(responseVO.getSkuList())) {
            return;
        }
        responseVO.getSkuList().stream().forEach(data -> {
            ShoppingCartCommodityResponseDTO responseDTO = shoppingMap.get(getKey(responseVO.getShopId(), data.getId()));
            if (Objects.nonNull(responseDTO)) {
                data.setCount(responseDTO.getNum());
            } else {
                data.setCount(0);
            }
        });
    }

    private String getKey(Long shopId, Long skuId) {
        StringBuilder sb = new StringBuilder();
        sb.append(shopId);
        sb.append("_");
        sb.append(skuId);
        return sb.toString();
    }

    private PayloadPageBeanPageShelvesItemSkuResponseDTO getDefualtSkuResponse() {
        PayloadPageBeanPageShelvesItemSkuResponseDTO skuResponseDTO = new PayloadPageBeanPageShelvesItemSkuResponseDTO();
        skuResponseDTO.setCode("200");
        PageBeanPageShelvesItemSkuResponseDTO itemSkuResponseDTO = new PageBeanPageShelvesItemSkuResponseDTO();
        itemSkuResponseDTO.setNumber(0);
        itemSkuResponseDTO.setSize(0);
        itemSkuResponseDTO.setNumberOfElements(0);
        itemSkuResponseDTO.setTotalPages(0);
        itemSkuResponseDTO.setTotalElements(0L);
        skuResponseDTO.setPayload(itemSkuResponseDTO);
        return skuResponseDTO;
    }

    /**
     * @Description: 获取上游供应商的店铺.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/28
     */
    private List<StoreResponseDTO> setSuppliersStore(CommodityCoreQuery coreQuery) throws Exception {
        // 如果界面给了授权的商品信息,直接使用授权的门店信息.
        if (CommonUtils.isNotEmpty(coreQuery.getAuthorizeSkuList())) {
            coreQuery.setSkuIdList(coreQuery.getAuthorizeSkuList()
                    .stream().map(AuthorizeSkuInfo::getSkuIds).flatMap(Collection::stream).collect(Collectors.toList()));
            return getStoreInfos(coreQuery.getAuthorizeSkuList()
                    .stream().map(AuthorizeSkuInfo::getAuthorizeBy).collect(Collectors.toList()));
        }
        String code = appRuntimeEnv.getUserOrganization().getCode();
        List<SuppliersResponseDTO> suppliers = Lists.newArrayList();
        if (ORG_CODE.equals(code)) {
            SuppliersResponseDTO suppliersResponseDTO = new SuppliersResponseDTO();
            suppliersResponseDTO.setSuppliersId(appRuntimeEnv.getUserOrganization().getId());
            suppliers.add(suppliersResponseDTO);
        } else {
            // 首先查询当前用户的上游信息.
            suppliers = getSuppliersOrgIds(coreQuery);
        }
        if (CommonUtils.isEmpty(suppliers)) {
            throw new ApplicationException("查询不到当前用户上游信息");
        }
        // 然后通过上游的组织信息,查询对应的门店信息.
        return getStoreInfos(suppliers.stream().map(SuppliersResponseDTO::getSuppliersId).distinct().collect(Collectors.toList()));
    }

    /**
     * @Description: 获取上游组织信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    private List<SuppliersResponseDTO> getSuppliersOrgIds(CommodityCoreQuery coreQuery) throws Exception {
        // 查询当前用户业务伙伴.
        BusinessPartnerResponseDTO partnerResponseDTO = getPartner(String.valueOf(appRuntimeEnv.getTopOrganization().getId()), coreQuery.getAppId());
        // 获取业务伙伴下面的客户.
        List<MerchantResponseDTO> merchantList = getAuthorizeCustomer(getCustomer(partnerResponseDTO.getId(), coreQuery.getAppId()), coreQuery.getAppId());
        if (CommonUtils.isEmpty(merchantList)) {
            throw new ApplicationException("当前用户没有被授权的客户信息.");
        }
        return merchantList.stream().map(s -> {
            SuppliersResponseDTO suppliersResponseDTO = new SuppliersResponseDTO();
            suppliersResponseDTO.setSuppliersId(Long.valueOf(s.getIsolationId()));
            return suppliersResponseDTO;
        }).collect(Collectors.toList());
    }

    /**
     * @Description: 获取返回体.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/28
     */
    private PageBean<CommodityTypeResponseVO> getResultBody(List<CommodityTypeResponseVO> resultList,
                                                            PayloadPageBeanPageShelvesItemSkuResponseDTO result) {
        PageBean resultPage = new PageBean<>();
        resultPage.setContent(resultList);
        resultPage.setNumber(result.getPayload().getNumber());
        resultPage.setSize(result.getPayload().getSize());
        resultPage.setNumberOfElements(result.getPayload().getNumberOfElements());
        resultPage.setTotalPages(result.getPayload().getTotalPages());
        resultPage.setTotalElements(result.getPayload().getTotalElements());
        return resultPage;
    }

    /**
     * 获取授权价格
     *
     * @param coreQuery
     * @param result
     * @param resultList
     * @throws Exception
     */
    private void getAuthorizeThePrice(CommodityCoreQuery coreQuery, PayloadPageBeanPageShelvesItemSkuResponseDTO result,
                                      List<CommodityTypeResponseVO> resultList) throws Exception {
        // 删除没有授权的商品.
        removeNotAuthorizationSku(resultList, coreQuery.getSkuIdList());
        // 查询店铺对应的组织id.
        List<StoreResponseDTO> storeList = findStore(resultList);
        // 然后获取当前用户的业务伙伴.
        BusinessPartnerResponseDTO partnerDTO = getPartner(String.valueOf(appRuntimeEnv.getTopOrganization().getId()), coreQuery.getAppId());
        // 查询客户.
        List<MerchantResponseDTO> merchantList = getCustomer(partnerDTO.getId(), partnerDTO.getId());

        Map<String, List<MerchantResponseDTO>> merchantMap = merchantList.stream().collect(Collectors.groupingBy(MerchantResponseDTO::getIsolationId));
        Map<Long, StoreResponseDTO> storeMap = storeList.stream().collect(Collectors.toMap(StoreResponseDTO::getId, Function.identity()));

        resultList.forEach(r -> {
            r.setCode(result.getCode());
            r.setMsg(result.getMsg());
            // 默认把商品的价格设置为0.
            if (CommonUtils.isNotEmpty(r.getSkuList())) {
                r.getSkuList().stream().forEach(s -> {
                    s.setTradePrice(BigDecimal.valueOf(0.00d));
                    if (Objects.nonNull(storeMap.get(r.getShopId()))) {
                        s.setIsolationId(String.valueOf(storeMap.get(r.getShopId()).getOrgId()));
                    }
                });
            }
        });

        // 对门店进行分组,逐个门店查询商品的价格.
        Map<Long, List<CommodityTypeResponseVO>> commodityMap = resultList.stream()
                .filter(s -> Objects.nonNull(s.getShopId())).collect(Collectors.groupingBy(CommodityTypeResponseVO::getShopId));
        // 反查商品的一级组织.
        Map<Long, Map<Long, Long>> skuMap = findSkuFistOrg(commodityMap, storeMap, merchantList);
        if (CommonUtils.isNotEmpty(commodityMap)) {
            Iterator<Map.Entry<Long, List<CommodityTypeResponseVO>>> iterator = commodityMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, List<CommodityTypeResponseVO>> entry = iterator.next();
                findSkuPrice(entry, merchantMap, storeMap, skuMap.get(entry.getKey()));
            }
        }
    }

    /**
     * @Description: 查询sku是被哪些一级组织授权的.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/24
     */
    private Map<Long, Map<Long, Long>> findSkuFistOrg(Map<Long, List<CommodityTypeResponseVO>> commodityMap,
                                                      Map<Long, StoreResponseDTO> storeMap,
                                                      List<MerchantResponseDTO> merchantList) throws Exception {
        Map<Long, Map<Long, Long>> map = Maps.newHashMap();
        Iterator<Map.Entry<Long, List<CommodityTypeResponseVO>>> iterator = commodityMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<CommodityTypeResponseVO>> next = iterator.next();
            ToolSkuRequestDTO toolSkuRequestDTO = new ToolSkuRequestDTO();
            toolSkuRequestDTO.setAppId(appRuntimeEnv.getAppId());
            toolSkuRequestDTO.setTenantId(appRuntimeEnv.getTenantId());
            toolSkuRequestDTO.setOrgId(storeMap.get(next.getKey()).getOrgId());
            List<Long> skuIds = next.getValue().stream().
                    map(CommodityTypeResponseVO::getSkuList).
                    flatMap(Collection::stream).
                    map(CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO::getId).
                    collect(Collectors.toList());
            if (CommonUtils.isEmpty(skuIds)) {
                log.info("CommodityCore sku is null key:{}", next.getKey());
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
                        skuMap.put(data.getSkuId(), matchCustomer(merchantList, data.getOrgId(), storeMap.get(next.getKey()).getOrgId()));
                    } else {
                        // 如果没有一级组织给顶级组织.
                        skuMap.put(data.getSkuId(), storeMap.get(next.getKey()).getOrgId());
                    }
                });
                map.put(next.getKey(), skuMap);
            } else {
                Map<Long, Long> skuMap = Maps.newHashMap();
                skuIds.forEach(skuId -> skuMap.put(skuId, storeMap.get(next.getKey()).getOrgId()));
                map.put(next.getKey(), skuMap);
            }
        }
        return map;
    }

    /**
     * @Description: 重置商品的价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/25
     */
    private void findSkuPrice(Map.Entry<Long, List<CommodityTypeResponseVO>> entry,
                              Map<String, List<MerchantResponseDTO>> merchantMap,
                              Map<Long, StoreResponseDTO> storeMap,
                              Map<Long, Long> skuMap) throws Exception {
        if (Objects.isNull(storeMap.get(entry.getKey()))) {
            return;
        }
        if (CommonUtils.isEmpty(merchantMap.get(String.valueOf(storeMap.get(entry.getKey()).getOrgId())))) {
            return;
        }
        // 获取商品的sku信息.
        List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> skuList = entry.getValue()
                .stream().map(CommodityTypeResponseVO::getSkuList).flatMap(Collection::stream).collect(Collectors.toList());
        List<MerchantResponseDTO> merchantList = merchantMap.get(String.valueOf(storeMap.get(entry.getKey()).getOrgId()));
        Map<String, List<BusinessSkuPriceAdminResponsDTO>> customerMap = Maps.newHashMap();
        for (MerchantResponseDTO merchantDTO : merchantList) {
            BusinessSkuPriceAdminRequestQuery requestQuery = new BusinessSkuPriceAdminRequestQuery();
            requestQuery.setCustomerId(merchantDTO.getId());
            requestQuery.setTenantId(appRuntimeEnv.getTenantId());
            requestQuery.setAppId(merchantDTO.getAppId());
            requestQuery.setIsolationId(merchantDTO.getIsolationId());
            requestQuery.setSkuIds(skuList.stream()
                    .map(CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO::getId).collect(Collectors.toList()));
            List<BusinessSkuPriceAdminResponsDTO> skuPrices = GeneralConvertUtils.convert2List(
                    skuPriceClient.queryPriceBySkuIds(requestQuery).getPayload(), BusinessSkuPriceAdminResponsDTO.class);
            if (CommonUtils.isNotEmpty(skuPrices)) {
                // 过滤重复的,并且取优先级高的价格.
                Collections.sort(skuPrices, (v1, v2) -> v1.getTyp().compareTo(v2.getTyp()));
                customerMap.put(merchantDTO.getOrgId(), skuPrices);
            }
        }
        resetSkuPrice(customerMap, skuList, skuMap);
    }

    /**
     * @Description: 重置价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/25
     */
    private void resetSkuPrice(Map<String, List<BusinessSkuPriceAdminResponsDTO>> customerMap,
                               List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> skuList,
                               Map<Long, Long> skuMap) {
        if (CommonUtils.isEmpty(skuMap) || CommonUtils.isEmpty(customerMap)) {
            return;
        }
        skuList.forEach(data -> {
            Long orgId = skuMap.get(data.getId());
            List<BusinessSkuPriceAdminResponsDTO> skuPrices = customerMap.get(String.valueOf(orgId));
            if (CommonUtils.isNotEmpty(skuPrices)) {
                data.setTradePrice(getPrice(skuPrices, data.getId()));
            }
        });
    }

    /**
     * @Description: 通过们的查询对应的商品价格.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/25
     */
    private Map<Long, List<BusinessSkuPriceAdminResponsDTO>> getSkuPriceByStore(Map<String,
            List<BusinessSkuPriceAdminResponsDTO>> map, Long shopId, List<StoreResponseDTO> storeList) {
        List<StoreResponseDTO> stores = storeList.stream().filter(s -> s.getId().equals(shopId)).collect(Collectors.toList());
        if (CommonUtils.isEmpty(stores)) {
            return Maps.newHashMap();
        }
        return Optional.ofNullable(map.get(stores.get(0).getIsolationId())).orElse(Lists.newArrayList())
                .stream().collect(Collectors.groupingBy(BusinessSkuPriceAdminResponsDTO::getSkuId));
    }

    /**
     * @Description: 删除没有授权的sku.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/25
     */
    private void removeNotAuthorizationSku(List<CommodityTypeResponseVO> resultList, List<Long> skuIds) {
        if (CommonUtils.isNotEmpty(skuIds)) {
            resultList.forEach(spu -> {
                spu.getSkuList().removeIf(s -> !skuIds.stream().anyMatch(skuId -> skuId.equals(s.getId())));
            });
        }
    }
}
