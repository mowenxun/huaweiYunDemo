package com.deepexi.dd.system.mall.service.app.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.domain.business.domain.dto.CommoditySearchHistoryRequestDTO;
import com.deepexi.dd.domain.common.domain.dto.CommonDirectSupplyResponseDTO;
import com.deepexi.dd.domain.common.domain.query.CommonDirectSupplyRequestQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.query.app.CommodityDirectSupplyQuery;
import com.deepexi.dd.system.mall.domain.query.app.FrontCategoryTreeRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.RecommendSkuCommodityQuery;
import com.deepexi.dd.system.mall.domain.query.app.ShelvesItemSkuDetailRequestQuery;
import com.deepexi.dd.system.mall.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.FrontCategoryTreeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.dd.system.mall.remote.commodity.CommonDirectSupplyRemote;
import com.deepexi.dd.system.mall.service.ShoppingCartService;
import com.deepexi.dd.system.mall.service.app.CommodityDirectSupplyService;
import com.deepexi.dd.system.mall.service.app.CommoditySearchHistoryService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.sdk.commodity.api.CategoryFrontOpenApi;
import com.deepexi.sdk.commodity.api.ShelvesItemSkuOpenApi;
import com.deepexi.sdk.commodity.api.SkuOpenApi;
import com.deepexi.sdk.commodity.model.*;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
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
public class CommodityDirectSupplyServiceImpl implements CommodityDirectSupplyService {

    @Autowired
    private SkuOpenApi skuOpenApi;

    @Autowired
    private ShelvesItemSkuOpenApi shelvesItemSkuOpenApi;

    @Autowired
    private CategoryFrontOpenApi categoryFrontOpenApi;

    @Autowired
    private CommonDirectSupplyRemote commonDirectSupplyRemote;

    @Autowired
    private CommoditySearchHistoryService commoditySearchHistoryService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private ShoppingCartService cartService;

    @Override
    public PageBean<CommodityTypeResponseVO> getShelvesItemSku(CommodityDirectSupplyQuery query) throws Exception {
        if (StringUtils.isEmpty(query.getName())) {
            query.setName(null);
        }
        // 添加搜索历史
        setSearchHistory(query);
        log.info("调用中台入参" + JSONObject.toJSONString(query));
        //query.setShopId(1L);
        //PageSkuItemNotOnlineRequestDTO requestDTO = request.clone(PageSkuItemNotOnlineRequestDTO.class);
        //requestDTO.setItemStatus(3);
        //return skuOpenApi.pageSkuItemNotOnline(requestDTO);
        PayloadPageBeanPageShelvesItemSkuResponseDTO result =
                shelvesItemSkuOpenApi.pageShelvesItemSku(GeneralConvertUtils.conv(query, PageShelvesItemSkuRequestDTO.class));
        log.info("中台返回结果" + JSONObject.toJSONString(result));
        if (!"0".equals(result.getCode())) {
            log.error("获取中台数据出错了");
            throw new ApplicationException(result.getMsg());
        }
        List<CommodityTypeResponseVO> resultList = GeneralConvertUtils.convert2List(
                result.getPayload().getContent(), CommodityTypeResponseVO.class);
        if (resultList.size() == 0) {
            log.error("没有获取到商品");
            return new PageBean<>();
        }
        // 获取所有直供商品和商品的关系
        /*CommonDirectSupplyRequestQuery cdsQuery = new CommonDirectSupplyRequestQuery();
        cdsQuery.setOnStatus(query.getOnStatus());
        List<CommonDirectSupplyResponseDTO> dsrList = GeneralConvertUtils.convert2List(
                commonDirectSupplyRemote.listDirectSupplyRelation(cdsQuery).getPayload(), CommonDirectSupplyResponseDTO.class);
        if (CollectionUtils.isEmpty(dsrList)) {
            log.error("没有查询到直供商品关系表的数据");
            throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
        }
        Map<String, CommonDirectSupplyResponseDTO> cdsMap = dsrList.stream()
                .collect(Collectors.toMap(CommonDirectSupplyResponseDTO::getSkuId, c -> c));
        // 把所有的购物车从Redis缓存中查询出来,然后逐一的去匹配,然后覆盖数量.
        ShoppingCartQuery cartQuery = new ShoppingCartQuery();
        cartQuery.setTenantId(appRuntimeEnv.getTenantId());
        Map<String, ShoppingCartCommodityResponseDTO> shoppingMap = Optional.ofNullable(
                cartService.searchShoppingToApp(cartQuery)).orElse(Lists.newArrayList())
                .stream().collect(Collectors.toMap(s -> getKey(s.getShopId(), s.getSkuId()), Function.identity()));
        resultList.forEach(r -> {
            r.setCode(result.getCode());
            r.setMsg(result.getMsg());
            // 获取直供价格和直供商品上下架状态, 没有数据的不显示
            r.setSkuList(getDirectSupplyPriceAndOnStatus(query, cdsMap, r));
            // 设置sku的数量.
            setShoppingNumber(shoppingMap, r);
            // 转换name到subName
            String subName = r.getItemWhole().getSubName();
            if (StringUtils.isEmpty(subName)) {
                r.getItemWhole().setSubName(r.getItemWhole().getName());
            }
        });*/
        // 分页
        PageBean resultPage = new PageBean<>();
        resultPage.setContent(resultList);
        resultPage.setSize(result.getPayload().getSize());
        resultPage.setNumber(result.getPayload().getNumber());
        resultPage.setNumberOfElements(result.getPayload().getNumberOfElements());
        resultPage.setTotalPages(result.getPayload().getTotalPages());
        resultPage.setTotalElements(result.getPayload().getTotalElements());
        return resultPage;
    }

    @Override
    public List<CommodityTypeResponseVO> getRecommendSkuCommodity(RecommendSkuCommodityQuery querys) throws Exception {
        CommodityDirectSupplyQuery query = querys.clone(CommodityDirectSupplyQuery.class);
        // 获取所有直供商品和商品的关系
        CommonDirectSupplyRequestQuery cdsQuery = new CommonDirectSupplyRequestQuery();
        cdsQuery.setIsRecommend(query.getIsRecommend());
        cdsQuery.setOnStatus(query.getOnStatus());
        List<CommonDirectSupplyResponseDTO> dsrList = GeneralConvertUtils.convert2List(
                commonDirectSupplyRemote.listDirectSupplyRelation(cdsQuery).getPayload(), CommonDirectSupplyResponseDTO.class);
        if (CollectionUtils.isEmpty(dsrList)) {
            log.error("没有查询到首页推荐的商品");
            return new ArrayList<>();
        }
        Map<String, CommonDirectSupplyResponseDTO> cdsMap = dsrList.stream()
                .collect(Collectors.toMap(CommonDirectSupplyResponseDTO::getSkuId, c -> c));
        // 根据首页推荐sku获取8条直供商品
        query.setPage(1);
        query.setSize(10);
        query.setSkuIdList(dsrList.stream().map(CommonDirectSupplyResponseDTO::getSkuId)
                .map(Long::valueOf).collect(Collectors.toList()));
        PayloadPageBeanPageShelvesItemSkuResponseDTO result =
                shelvesItemSkuOpenApi.pageShelvesItemSku(query.clone(PageShelvesItemSkuRequestDTO.class));
        if (!result.getCode().equals("0")) {
            log.error("获取中台数据出错了");
            throw new ApplicationException(result.getMsg());
        }
        List<CommodityTypeResponseVO> resultList = GeneralConvertUtils.convert2List(
                result.getPayload().getContent(), CommodityTypeResponseVO.class);
        if (CollectionUtils.isNotEmpty(dsrList) && CollectionUtils.isEmpty(resultList)) {
            log.error("没有获取到已上架的直供商品");
            throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
        }
        // 拼接直供数据
        resultList.forEach(r -> {
            r.setMsg(result.getMsg());
            r.setCode(result.getCode());
            List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> skuList = new ArrayList<>();
            r.getSkuList().forEach(sku -> {
                String skuId = String.valueOf(sku.getId());
                // 只查询已经上架的直供商品
                if (cdsMap.containsKey(skuId) && ObjectUtil.equal(cdsMap.get(skuId).getOnStatus(), query.getOnStatus())) {
                    sku.setDirectSupplyPrice(cdsMap.get(skuId).getDirectSupplyPrice());
                    sku.setOnStatus(cdsMap.get(skuId).getOnStatus());
                    sku.setIsolationId(cdsMap.get(skuId).getIsolationId());
                    sku.setDirectId(cdsMap.get(skuId).getId());
                    skuList.add(sku);
                }
            });
            r.setSkuList(skuList);
        });
        //转换name到subName
        String subName;
        for (CommodityTypeResponseVO commodityTypeResponseVO : resultList) {
            subName = commodityTypeResponseVO.getItemWhole().getSubName();
            if (StringUtils.isEmpty(subName)) {
                commodityTypeResponseVO.getItemWhole().setSubName(commodityTypeResponseVO.getItemWhole().getName());
            }
        }
        return resultList;
    }

    @Override
    public List<FrontCategoryTreeResponseVO> getFrontCategoryTree(@Valid FrontCategoryTreeRequestQuery query) {
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
            r.setCode(result.getCode());
            r.setMsg(result.getMsg());
        });
        return resultList;
    }

    @Override
    public ShelvesItemSkuDetailResponseVO getItemSkuDetail(ShelvesItemSkuDetailRequestQuery query) throws Exception {
        PayloadGetShelvesItemSkuDetailResponseDTO result =
                shelvesItemSkuOpenApi.getShelvesItemSkuDetail(query.clone(GetShelvesItemSkuDetailRequestDTO.class));
        if (Objects.isNull(result.getPayload())) {
            log.error("获取中台数据出错了");
            throw new ApplicationException(ResultEnum.COMMODITY_SOLD_OUT);
        }
        ShelvesItemSkuDetailResponseVO resultVO =
                GeneralConvertUtils.conv(result.getPayload(), ShelvesItemSkuDetailResponseVO.class);
        // 批量查询直供关系表的商品, 获取直供价格
        /*List<Long> skuIdList = resultVO.getSkuList().stream()
                .map(ShelvesItemSkuDetailResponseVO.GetShelvesItemSkuGroupSkuResponseDTO::getId).collect(Collectors.toList());
        CommonDirectSupplyRequestQuery cdsQuery = new CommonDirectSupplyRequestQuery();
        cdsQuery.setOnStatus(query.getOnStatus());
        cdsQuery.setSkuIdList(skuIdList.stream().map(x -> x + "").collect(Collectors.toList()));
        List<CommonDirectSupplyResponseDTO> dsrList = GeneralConvertUtils.convert2List(
                commonDirectSupplyRemote.listDirectSupplyRelation(cdsQuery).getPayload(), CommonDirectSupplyResponseDTO.class);
        if (CollectionUtils.isEmpty(dsrList)) {
            log.error("没有获取到直供商品关系表的数据");
            throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
        }
        Map<String, CommonDirectSupplyResponseDTO> cdsMap = dsrList.stream()
                .collect(Collectors.toMap(CommonDirectSupplyResponseDTO::getSkuId, c -> c));
        List<ShelvesItemSkuDetailResponseVO.GetShelvesItemSkuGroupSkuResponseDTO> skuList = new ArrayList<>();
        // 把所有的购物车从Redis缓存中查询出来,然后逐一的去匹配,然后覆盖数量.
        ShoppingCartQuery cartQuery = new ShoppingCartQuery();
        cartQuery.setTenantId(appRuntimeEnv.getTenantId());
        Map<String, ShoppingCartCommodityResponseDTO> shoppingMap = Optional.ofNullable(
                cartService.searchShoppingToApp(cartQuery)).orElse(Lists.newArrayList())
                .stream().collect(Collectors.toMap(s -> getKey(s.getShopId(), s.getSkuId()), Function.identity()));
        // 只获取有直供价格的sku
        resultVO.getSkuList().forEach(sku -> {
            String skuId = String.valueOf(sku.getId());
            if (cdsMap.containsKey(skuId) && ObjectUtil.equal(cdsMap.get(skuId).getOnStatus(), query.getOnStatus())) {
                sku.setDirectSupplyPrice(cdsMap.get(skuId).getDirectSupplyPrice());
                sku.setIsolationId(cdsMap.get(skuId).getIsolationId());
                sku.setDirectId(cdsMap.get(skuId).getId());
                skuList.add(sku);
            }
            // 设置sku的数量.
            ShoppingCartCommodityResponseDTO cartDTO = shoppingMap.get(getKey(resultVO.getShopId(), sku.getId()));
            if (Objects.nonNull(cartDTO)) {
                sku.setCount(cartDTO.getNum());
            } else {
                sku.setCount(0);
            }
        });
        if (CollectionUtils.isEmpty(skuList)) {
            log.info("获取直供价格, 过滤后数据为空");
            return null;
        }
        resultVO.setSkuList(skuList);*/
        resultVO.setCode(result.getCode());
        resultVO.setMsg(result.getMsg());
        //转换name到subName
        String subName = resultVO.getItemWhole().getSubName();
        if (StringUtils.isEmpty(subName)) {
            resultVO.getItemWhole().setSubName(resultVO.getItemWhole().getName());
        }
        return resultVO;
    }

    /**
     * 添加搜索历史
     *
     * @param query
     */
    private void setSearchHistory(CommodityDirectSupplyQuery query) {
        if (Objects.nonNull(query.getName())) {
            CommoditySearchHistoryRequestDTO record = new CommoditySearchHistoryRequestDTO();
            record.setKeyword(query.getName());
            record.setTenantId(query.getTenantId());
            record.setAppId(query.getAppId());
            record.setIsolationId(String.valueOf(appRuntimeEnv.getUserOrganization().getId()));
            record.setCreatedBy(appRuntimeEnv.getUsername());
            record.setUpdatedBy(appRuntimeEnv.getUsername());
            commoditySearchHistoryService.insert(record);
        }
    }

    private String getKey(Long shopId, Long skuId) {
        StringBuilder sb = new StringBuilder();
        sb.append(shopId);
        sb.append("_");
        sb.append(skuId);
        return sb.toString();
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

    /**
     * 获取直供价格和直供商品上下架状态, 没有数据的不显示
     *
     * @param query
     * @param cdsMap
     * @param r
     * @return
     */
    private List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> getDirectSupplyPriceAndOnStatus(
            CommodityDirectSupplyQuery query, Map<String, CommonDirectSupplyResponseDTO> cdsMap, CommodityTypeResponseVO r) {
        List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> skuList = new ArrayList<>();
        r.getSkuList().forEach(sku -> {
            String skuId = String.valueOf(sku.getId());
            // 只查询已经上架的直供商品
            if (cdsMap.containsKey(skuId) && ObjectUtil.equal(cdsMap.get(skuId).getOnStatus(), query.getOnStatus())) {
                sku.setDirectSupplyPrice(cdsMap.get(skuId).getDirectSupplyPrice());
                sku.setOnStatus(cdsMap.get(skuId).getOnStatus());
                sku.setIsolationId(cdsMap.get(skuId).getIsolationId());
                sku.setDirectId(cdsMap.get(skuId).getId());
                skuList.add(sku);
            }
        });
        return skuList;
    }
}
