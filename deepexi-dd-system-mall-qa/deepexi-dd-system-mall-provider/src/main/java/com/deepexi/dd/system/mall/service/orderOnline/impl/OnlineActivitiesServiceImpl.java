package com.deepexi.dd.system.mall.service.orderOnline.impl;

import com.deepexi.dd.domain.common.domain.dto.OnlineActivitiesResponseDTO;
import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesGoodQuery;
import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreQuery;
import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreRequestQuery;
import com.deepexi.dd.system.mall.domain.query.orderOnline.OnlineActivitiesGoodRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.dd.system.mall.domain.vo.orderOnline.OnlineActivitiesGoodResponseVO;
import com.deepexi.dd.system.mall.domain.vo.orderOnline.OnlineActivitiesImageUrlResponseVO;
import com.deepexi.dd.system.mall.domain.vo.orderOnline.OnlineActivitiesResponseVO;
import com.deepexi.dd.system.mall.remote.common.OnlineActivitiesClient;
import com.deepexi.dd.system.mall.service.app.CommodityCoreService;
import com.deepexi.dd.system.mall.service.orderOnline.OnlineActivitiesService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.sdk.commodity.api.ActivityStockOpenApi;
import com.deepexi.sdk.commodity.api.ShelvesItemSkuOpenApi;
import com.deepexi.sdk.commodity.model.*;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName OnlineActivitiesServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-19
 * @Version 1.0
 **/
@Service
@Slf4j
public class OnlineActivitiesServiceImpl implements OnlineActivitiesService {

    @Autowired
    private OnlineActivitiesClient activitiesClient;

    @Autowired
    private CommodityCoreService commodityCoreService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private ActivityStockOpenApi activityStockOpenApi;

    @Autowired
    private ShelvesItemSkuOpenApi shelvesItemSkuOpenApi;

    @Autowired
    private Redisson redisson;

    /**
     * @Description: 查询活动列表.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/19
     */
    @Override
    public List<OnlineActivitiesResponseVO> findActivities(OnlineActivitiesQuery activitiesQuery) throws Exception {
        Payload<List<OnlineActivitiesResponseDTO>> payload = activitiesClient.findList(activitiesQuery);
        if (CommonUtils.isEmpty(payload.getPayload())) {
            return Lists.newArrayList();
        }
        List<OnlineActivitiesResponseVO> result = GeneralConvertUtils.convert2List(payload.getPayload(), OnlineActivitiesResponseVO.class);
        // 通过活动商品中的sku查询具体的商品信息.
        List<Long> skuIds = getSkuIds(result);
        if (CommonUtils.isEmpty(skuIds)) {
            // 如果sku没有,直接返回不需要查询.
            return result;
        }
        // 把所有的sku查询出来,然后根据活动商品规则重新组装一下.
        List<CommodityTypeResponseVO> commoditys = getCommodityTypeList(skuIds, result.get(0), activitiesQuery);
        // 最后把商品设置到活动中.
        result.get(0).setCommodityList(commoditys);
        return result;
    }

    @Override
    public List<OnlineActivitiesImageUrlResponseVO> getActivitiesImageUrl(OnlineActivitiesQuery activitiesQuery) throws Exception {
        Payload<List<OnlineActivitiesResponseDTO>> payload = activitiesClient.findList(activitiesQuery);
        if (CommonUtils.isEmpty(payload.getPayload())) {
            return Lists.newArrayList();
        }
        List<OnlineActivitiesImageUrlResponseVO> result = GeneralConvertUtils.convert2List(payload.getPayload(), OnlineActivitiesImageUrlResponseVO.class);

        String firstImageUrl = "";
        for (OnlineActivitiesImageUrlResponseVO vo:result){
            if(StringUtils.isNotBlank(vo.getImageUrl())){
                firstImageUrl = vo.getImageUrl().split(",")[0];
                vo.setImageUrl(firstImageUrl);
            }
        }

        return result;
    }

    /**
     * @Description: 通过skuId查询商品.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/19
     */
    private List<CommodityTypeResponseVO> getCommodityTypeList(List<Long> skuIds, OnlineActivitiesResponseVO activitiesResponseVO, OnlineActivitiesQuery activitiesQuery) {
        List<CommodityTypeResponseVO> commoditys = findCommodity(skuIds, activitiesQuery.getAppId());
        return assembleCommodity(activitiesResponseVO, commoditys, activitiesQuery);
    }

    /**
     * @Description: 通过sku查询商品.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/20
     */
    private List<CommodityTypeResponseVO> findCommodity(List<Long> skuIds, Long appId) {
        CommodityCoreQuery commodityCoreQuery = new CommodityCoreQuery();
        commodityCoreQuery.setSkuIdList(skuIds);
        commodityCoreQuery.setShopId(3L);
        commodityCoreQuery.setPage(1);
        commodityCoreQuery.setSize(skuIds.size());
        commodityCoreQuery.setTenantId(appRuntimeEnv.getTenantId());
        commodityCoreQuery.setAppId(appId);
        PageBean<CommodityTypeResponseVO> pageBean = commodityCoreService.pageShelvesItemSku(commodityCoreQuery);
        if (CommonUtils.isEmpty(pageBean.getContent())) {
            return Lists.newArrayList();
        }
        return pageBean.getContent();
    }

    /**
     * @Description: 通过活动查询商品.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/19
     */
    @Override
    public OnlineActivitiesResponseVO getCommodityByActivities(OnlineActivitiesGoodQuery goodQuery) throws Exception {
        Payload<OnlineActivitiesResponseDTO> payload = activitiesClient.getGoodByActivities(goodQuery);
        if (Objects.isNull(payload.getPayload())) {
            return null;
        }
        OnlineActivitiesResponseVO goods = GeneralConvertUtils.conv(payload.getPayload(), OnlineActivitiesResponseVO.class);
        if (CommonUtils.isEmpty(goods.getGoodList())) {
            return goods;
        }
        List<Long> skuIds = goods.getGoodList().stream().map(OnlineActivitiesGoodResponseVO::getSkuId).collect(Collectors.toList());
        goods.setCommodityList(getCommodityTypeList(skuIds, goods, goodQuery.clone(OnlineActivitiesQuery.class)));

        //转换name到subName
        String subName = "";
        for(CommodityTypeResponseVO commodityTypeResponseVO:goods.getCommodityList()){
            subName = commodityTypeResponseVO.getItemWhole().getSubName();
            if(org.springframework.util.StringUtils.isEmpty(subName)){
                commodityTypeResponseVO.getItemWhole().setSubName(commodityTypeResponseVO.getItemWhole().getName());
            }
        }

        return goods;
    }

    @Override
    public ShelvesItemSkuDetailResponseVO getActivitiesDetail(OnlineActivitiesGoodRequestQuery requestQuery) throws Exception {
        // 查询活动.
        Payload<OnlineActivitiesResponseDTO> payload = activitiesClient.getGoodByActivities(requestQuery.clone(OnlineActivitiesGoodQuery.class));
        if (Objects.isNull(payload.getPayload())) {
            return null;
        }
        OnlineActivitiesResponseVO goods = GeneralConvertUtils.conv(payload.getPayload(), OnlineActivitiesResponseVO.class);
        // 查询活动下面的商品.
        ShelvesItemSkuDetailResponseVO skuDetailVO = getSkuDetail(requestQuery.clone(CommodityCoreRequestQuery.class));
        if (Objects.isNull(skuDetailVO)) {
            return null;
        }
        if (CommonUtils.isNotEmpty(skuDetailVO.getSkuList())) {
            // 设置活动信息.
            skuDetailVO.setActivitiesId(goods.getId());
            skuDetailVO.setActivitiesName(goods.getName());

            // 获取当前活动下面的限购数量.
            RMap<Long, Integer> rMap = redisson.getMap(getRedisKey(requestQuery, requestQuery.getActivitiesId()));

            Map<Long, OnlineActivitiesGoodResponseVO> map = goods.getGoodList().stream().collect(Collectors.toMap(OnlineActivitiesGoodResponseVO::getSkuId, Function.identity()));
            // 把不存在活动里面的sku删除掉.
            skuDetailVO.getSkuList().removeIf(s -> !requestQuery.getSkuIds().stream().anyMatch(sku -> sku.equals(s.getId())));
            // 查询活动库存.
            Map<Long, Integer> stockMap = getActivitiesStockQty(skuDetailVO.getSkuList(), requestQuery, requestQuery.getActivitiesId());
            // 然后设置下活动的库存..
            skuDetailVO.getSkuList().forEach(sku -> {
                OnlineActivitiesGoodResponseVO goodVO = map.get(sku.getId());
                if (goodVO.getIsLimited().booleanValue()) {
                    sku.setLimitNum(getLimitedNumber(rMap.get(sku.getId()), goodVO.getLimitNum()));
                } else {
                    sku.setLimitNum(goodVO.getLimitNum());
                }
                sku.setIsLimited(goodVO.getIsLimited());
                sku.setActivitiesId(goodVO.getOnlineActivitiesId());
                sku.setDirectSupplyPrice(goodVO.getActivitiesPrice());
                sku.setIsolationId(goodVO.getIsolationId());
                sku.setSalableStockQty(stockMap.get(sku.getId()));
            });
        }

        String subName = skuDetailVO.getItemWhole().getSubName();
        if(org.springframework.util.StringUtils.isEmpty(subName)){
            skuDetailVO.getItemWhole().setSubName(skuDetailVO.getItemWhole().getName());
        }

        return skuDetailVO;
    }

    /**
    * @Description: 获取限购数量.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/31
    */
    private Integer getLimitedNumber(Integer number, Integer limitedNumber) {
        if (Objects.isNull(number)) {
            return limitedNumber;
        }
        if (number >= limitedNumber) {
            return 0;
        }
        return limitedNumber - number;
    }

    private String getRedisKey(OnlineActivitiesGoodRequestQuery requestQuery, Long activitiesId) {
        String key = "shoppingCart:tenant_id_%s:app_id_%s:user_id_%s:activities_id%s:";
        return String.format(key, requestQuery.getTenantId(),
                requestQuery.getAppId(), appRuntimeEnv.getUserId(), activitiesId);
    }

    public ShelvesItemSkuDetailResponseVO getSkuDetail(CommodityCoreRequestQuery query) {
        PayloadGetShelvesItemSkuDetailResponseDTO result =
                shelvesItemSkuOpenApi.getShelvesItemSkuDetail(query.clone(GetShelvesItemSkuDetailRequestDTO.class));
        if (!result.getCode().equals("0")) {
            log.error("获取中台数据出错了");
            throw new ApplicationException(result.getMsg());
        }
        List<CommodityTypeResponseVO> resultList =
                GeneralConvertUtils.convert2List(Arrays.asList(result.getPayload()), CommodityTypeResponseVO.class);
        return GeneralConvertUtils.conv(resultList.get(0), ShelvesItemSkuDetailResponseVO.class);
    }

    /**
     * @Description: 根据活动重新组装商品信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/19
     */
    private List<CommodityTypeResponseVO> assembleCommodity(OnlineActivitiesResponseVO activitiesResponseVO, List<CommodityTypeResponseVO> commodityList, OnlineActivitiesQuery activitiesQuery) {
        if (CommonUtils.isEmpty(activitiesResponseVO.getGoodList()) || CommonUtils.isEmpty(commodityList)) {
            return Lists.newArrayList();
        }
        // 查询活动库存.
        List<CommodityTypeResponseVO.PageShelvesItemSkuGroupSkuResponseDTO> skuList = commodityList.stream().map(CommodityTypeResponseVO::getSkuList).flatMap(Collection::stream).collect(Collectors.toList());
        Map<Long, Integer> stockMap = getActivitiesStockQty(GeneralConvertUtils.convert2List(skuList, ShelvesItemSkuDetailResponseVO.GetShelvesItemSkuGroupSkuResponseDTO.class), activitiesQuery.clone(OnlineActivitiesGoodRequestQuery.class), activitiesResponseVO.getId());

        Map<Long, OnlineActivitiesGoodResponseVO> map = activitiesResponseVO.getGoodList().stream().collect(Collectors.toMap(OnlineActivitiesGoodResponseVO::getSkuId, Function.identity()));
        commodityList.stream().forEach(data -> {
            // 先把不属于活动的sku都删除掉.
            data.getSkuList().removeIf(s -> Objects.isNull(map.get(s.getId())));
            // 然后设置下活动中的属性值.
            data.getSkuList().forEach(sku -> {
                OnlineActivitiesGoodResponseVO goodVO = map.get(sku.getId());
                sku.setLimitNum(goodVO.getLimitNum());
                sku.setIsLimited(goodVO.getIsLimited());
                sku.setActivitiesId(goodVO.getOnlineActivitiesId());
                sku.setDirectSupplyPrice(goodVO.getActivitiesPrice());
                sku.setSalableStockQty(stockMap.get(sku.getId()));
                sku.setIsolationId(goodVO.getIsolationId());
            });
            // 设置活动id.
            data.setActivitiesId(activitiesResponseVO.getId());
            // 设置店铺id.
            data.setShopId(3L);
            // 设置活动名称
            data.setActivitiesName(activitiesResponseVO.getName());
        });
        return commodityList;
    }

    /**
     * @Description: 获取活动库存.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/27
     */
    private Map<Long, Integer> getActivitiesStockQty(List<ShelvesItemSkuDetailResponseVO.GetShelvesItemSkuGroupSkuResponseDTO> skuList, OnlineActivitiesGoodRequestQuery requestQuery, Long activitiesId) {
        if (CommonUtils.isEmpty(skuList)) {
            return Maps.newHashMap();
        }
        ListActivityStockRequestDTO requestDTO = new ListActivityStockRequestDTO();
        requestDTO.setAppId(requestQuery.getAppId());
        requestDTO.setTenantId(requestQuery.getTenantId());
        requestDTO.setUnionIdList(skuList.stream().map(s -> 3 + "_" + s.getId() + "_" + activitiesId).collect(Collectors.toList()));
        PayloadListListActivityStockResponseDTO payload = activityStockOpenApi.listActivityStocksDetail(requestDTO);
        if (CommonUtils.isEmpty(payload.getPayload())) {
            return Maps.newHashMap();
        }
        return payload.getPayload().stream().collect(Collectors.toMap(ListActivityStockResponseDTO::getSkuId, ListActivityStockResponseDTO::getActivityStockQty));
    }

    /**
     * @Description: 获取活动商品中的所有skuId.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/19
     */
    private List<Long> getSkuIds(List<OnlineActivitiesResponseVO> activities) {
        if (CommonUtils.isEmpty(activities.get(0).getGoodList())) {
            return null;
        }
        return activities.get(0).getGoodList().stream().map(OnlineActivitiesGoodResponseVO::getSkuId).collect(Collectors.toList());
    }
}
