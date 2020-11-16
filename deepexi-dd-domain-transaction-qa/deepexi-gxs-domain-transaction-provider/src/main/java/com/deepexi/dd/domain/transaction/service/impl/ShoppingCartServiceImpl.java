package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.clientiam.api.GroupControllerApi;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.clientiam.model.PayloadPageBeanGroupResultVO;
import com.deepexi.dd.domain.common.domain.dto.OnlineActivitiesGoodResponseDTO;
import com.deepexi.dd.domain.common.domain.dto.OnlineActivitiesResponseDTO;
import com.deepexi.dd.domain.common.domain.query.OnlineActivitiesGoodQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.constant.ShoppingCartRedisKeyConstant;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderItemDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.*;
import com.deepexi.dd.domain.transaction.domain.query.CommodityQuery;
import com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.manager.CommodityManager;
import com.deepexi.dd.domain.transaction.remote.common.OnlineActivitiesClient;
import com.deepexi.dd.domain.transaction.remote.order.BusinessPartnerClient;
import com.deepexi.dd.domain.transaction.remote.order.MerchantClient;
import com.deepexi.dd.domain.transaction.remote.order.StoreClient;
import com.deepexi.dd.domain.transaction.service.ShoppingCartService;
import com.deepexi.dd.domain.transaction.service.common.CommonService;
import com.deepexi.sdk.commodity.api.ActivityStockOpenApi;
import com.deepexi.sdk.commodity.model.ListShopItemUpShelfRequestDTO;
import com.deepexi.sdk.commodity.model.ListShopItemUpShelfResponseDTO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import domain.dto.MerchantResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 购物车接口实现
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private Redisson redisson;

    @Resource
    private CommonService commonService;

    @Autowired
    private CommodityManager commodityManager;

    @Autowired
    private GroupControllerApi groupControllerApi;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private OnlineActivitiesClient activitiesClient;

    @Autowired
    private BusinessPartnerClient partnerClient;

    @Autowired
    private StoreClient storeClient;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private ActivityStockOpenApi activityStockOpenApi;

    private static final String LOG_KEY = "购物车业务处理";

    private Map<Long, List<ShoppingCartCommodityDTO>> groupShopping(List<ShoppingCartCommodityDTO> newShoopings) {
        return newShoopings.stream().collect(Collectors.groupingBy(ShoppingCartCommodityDTO::getShopId));
    }

    /**
     * @Description: 获取商品.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/29
     */
    private Map<String, SaleOrderItemDTO> getOrderList(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO,
                                                       List<ShoppingCartCommodityDTO> shoppings) {
        // 首先批量查询商品信息.
        Map<Long, List<ShoppingCartCommodityDTO>> map = groupShopping(shoppings);
        List<SaleOrderItemDTO> orderList = batchQueryShop(map, shoppingCartUserInfoDTO);
        Map<String, SaleOrderItemDTO> orderMap = orderList.stream().collect(Collectors.toMap(s -> getStockKey(s.getShopId(), s.getSkuId()), Function.identity()));
        return orderMap;
    }

    private String groupShopKey(ShoppingCartCommodityDTO cartCommodityDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append(cartCommodityDTO.getAffiliatedOrganizationId());
        sb.append("_");
        sb.append(cartCommodityDTO.getFirstOrgId());
        sb.append("_");
        sb.append(cartCommodityDTO.getOrderTypeId());
        return sb.toString();
    }
    private Map<Long, Long> getCustomerMap(List<MerchantResponseDTO> merchantList) {
        Map<Long, Long> map = Maps.newHashMap();
        merchantList.forEach(data -> {
            map.put(Long.valueOf(data.getOrgId()), data.getId());
        });
        return map;
    }

    private Integer setCompanyType(Long companyTypeId) {
        if (Objects.isNull(companyTypeId) || companyTypeId.equals(1L)) {
            return 1;
        }
        return 0;
    }

    /**
     * @Description: 获取客户的map.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/25
     */
    private Map<String, List<String>> getMerchantMap(List<MerchantResponseDTO> merchantList) {
        if (CommonUtils.isEmpty(merchantList)) {
            return Maps.newHashMap();
        }
        return merchantList.stream().collect(Collectors.groupingBy(MerchantResponseDTO::getIsolationId, Collectors.mapping(this::appendCustomer, Collectors.toList())));
    }

    private String appendCustomer(MerchantResponseDTO responseDTO) {
        StringBuffer sb = new StringBuffer();
        sb.append(responseDTO.getId());
        sb.append(";");
        sb.append(responseDTO.getOrgId());
        return sb.toString();
    }

    /**
     * @Description: 查询组织信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/17
     */
    private Map<Long, String> findOranization(List<ShoppingCartCommodityDTO> shoppings, ShoppingCartUserInfoDTO infoQuery) {
        List<String> orgIds = Lists.newArrayList();
        shoppings.forEach(data -> {
            pushOrgId(data.getAffiliatedOrganizationId(), orgIds);
            pushOrgId(data.getFirstOrgId(), orgIds);
        });
        List<Long>orgidsLong=Lists.newArrayList();
        orgIds.forEach(item->{
            orgidsLong.add(Long.valueOf(item));
        });
        PayloadPageBeanGroupResultVO groupResultVO = groupControllerApi.getGroupList(infoQuery.getTenantId(),
                appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername(), null, null, null, null, null,null, null, null
                ,null, orgidsLong, null, null, 1, null, orgIds.size());
        if (Objects.nonNull(groupResultVO.getPayload()) && CommonUtils.isNotEmpty(groupResultVO.getPayload().getContent())) {
            return groupResultVO.getPayload().getContent().stream().collect(Collectors.toMap(GroupResultVO::getId, GroupResultVO::getName));
        }
        return Maps.newHashMap();
    }

    private void pushOrgId(Long orgId, List<String> orgIds) {
        if (Objects.nonNull(orgId) && !orgIds.contains(orgId)) {
            orgIds.add(String.valueOf(orgId));
        }
    }

    /**
     * @Description: 添加组织 顶级组织 + 一级组织
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/17
     */
    private String appendOrganization(ShoppingCartCommodityDTO cartCommodityDTO, Map<Long, String> orgMap) {
        StringBuffer sb = new StringBuffer();
        sb.append(orgMap.get(cartCommodityDTO.getAffiliatedOrganizationId()));
        if (Objects.nonNull(cartCommodityDTO.getFirstOrgId())) {
            sb.append(" ");
            sb.append("(");
            sb.append(orgMap.get(cartCommodityDTO.getFirstOrgId()));
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * 商品信息查询
     *
     * @param shoppingCartUserInfoDTO
     * @param shoppings
     */
    private List<ShoppingCartResponseDTO> searchSkuInfo(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO,
                                                        List<ShoppingCartCommodityDTO> shoppings) throws Exception {
        // 对商品进行排序.
        orderByDate(shoppings);
        List<ShoppingCartResponseDTO> result = Lists.newArrayList();
        // 查询商品.
        Map<String, SaleOrderItemDTO> orderMap = getOrderList(shoppingCartUserInfoDTO, shoppings);
        // 根据店铺进行分组.
        LinkedHashMap<String, List<ShoppingCartCommodityDTO>> map = shoppings.stream().collect(Collectors.groupingBy(this::groupShopKey, LinkedHashMap::new, Collectors.toList()));
        // 批量查询购物车中的组织信息.
        //Map<Long, String> orgMap = findOranization(shoppings, shoppingCartUserInfoDTO);
        map.forEach((k, v) -> {
            ShoppingCartCommodityDTO oneCart = v.get(0);
            ShoppingCartResponseDTO responseDTO = new ShoppingCartResponseDTO();
            //responseDTO.setAffiliatedOrganizationName(appendOrganization(oneCart, orgMap));
            responseDTO.setAffiliatedOrganization(oneCart.getAffiliatedOrganizationId());
            responseDTO.setFirstOrgId(oneCart.getFirstOrgId());
            responseDTO.setOrderTypeId(oneCart.getOrderTypeId());
            List<ShoppingCartCommodityResponseDTO> shoppingCarts = v.stream().filter(s -> Objects.nonNull(orderMap.get(getStockKey(s.getShopId(), s.getSkuId())))).map(s -> {
                SaleOrderItemDTO itemDTO = orderMap.get(getStockKey(s.getShopId(), s.getSkuId()));
                ShoppingCartCommodityResponseDTO cartResponseDTO = new ShoppingCartCommodityResponseDTO();
                if (Objects.isNull(itemDTO)) {
                    return getDefualtShopping(cartResponseDTO, s);
                }
                cartResponseDTO.setSkuName(itemDTO.getSkuName());
                cartResponseDTO.setSkuCode(itemDTO.getSkuCode());
                cartResponseDTO.setSkuId(itemDTO.getSkuId());
                cartResponseDTO.setCommodityId(itemDTO.getItemId());
                cartResponseDTO.setCommodityName(itemDTO.getItemName());
                cartResponseDTO.setSubName(StringUtils.isBlank(itemDTO.getSubName())?itemDTO.getItemName():itemDTO.getSubName());
                cartResponseDTO.setNum(s.getNum());
                cartResponseDTO.setStatus(itemDTO.getStatus());
                //上架即启用
                cartResponseDTO.setIsEnable(itemDTO.getStatus().equals(1));
                cartResponseDTO.setShopId(itemDTO.getShopId());
                cartResponseDTO.setPrice(itemDTO.getPrice());
                cartResponseDTO.setUnitName(itemDTO.getUnitName());
                // 设置图片.
                cartResponseDTO.setPictureUrl(itemDTO.getMajorPicture());
                // 库存.
                cartResponseDTO.setStockNum(converterLong(itemDTO.getStock()));
                // 属性规格.
                cartResponseDTO.setPropertyValue(itemDTO.getPropertyValue());
                // 组织id.
                cartResponseDTO.setOrgId(s.getAffiliatedOrganizationId());
                // 活动id.
                cartResponseDTO.setActivitiesId(s.getActivitiesId());
                // 直供id.
                cartResponseDTO.setDirectId(s.getDirectId());
                // 设置购物车商品状态.
                setShoppingStatus(cartResponseDTO);
                // 设置是否限购.
                cartResponseDTO.setIsLimited(itemDTO.getIsLimited());
                // 设置限购数量.
                cartResponseDTO.setLimitNum(getLimitNum(shoppingCartUserInfoDTO, itemDTO));
                //设置品牌信息
                cartResponseDTO.setBrandId(itemDTO.getBrandId());
                cartResponseDTO.setBrandCode(itemDTO.getBrandCode());
                cartResponseDTO.setBrandName(itemDTO.getBrandName());
                return cartResponseDTO;
            }).collect(Collectors.toList());
            if (CommonUtils.isNotEmpty(shoppingCarts)) {
                responseDTO.setShoppingCartCommodityResponseDTOList(shoppingCarts);
                result.add(responseDTO);
            }
        });
        if (CommonUtils.isEmpty(result)) {
            log.info("购物车整合结果为空，返回空集合");
            return Lists.newArrayList();
        }
        return result;
    }

    /**
     * @Description: 获取限购数量.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/31
     */
    private Integer getLimitNum(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, SaleOrderItemDTO itemDTO) {
        if (Objects.nonNull(itemDTO.getActivitiesId()) && Objects.nonNull(itemDTO.getIsLimited()) && itemDTO.getIsLimited().booleanValue()) {
            String key = getHistoryShopKey(shoppingCartUserInfoDTO, itemDTO.getActivitiesId());
            RMap<Long, Integer> rMap = redisson.getMap(key);
            Integer num = rMap.get(itemDTO.getSkuId());
            if (Objects.nonNull(num)) {
                if (num >= itemDTO.getLimitNum()) {
                    return 0;
                }
                return (itemDTO.getLimitNum() - num);
            }
        }
        return itemDTO.getLimitNum();
    }

    /**
     * @Description: 设置购物车中商品的状态:商品状态 0.待上架 1.已上架 2.下架 3.已售罄
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/29
     */
    private void setShoppingStatus(ShoppingCartCommodityResponseDTO cartResponseDTO) {
        if ("0".equals(cartResponseDTO.getStockNum())) {
            // 如果没有库存默认就是已售罄.
            cartResponseDTO.setStatus(3);
        }
    }

    /**
     * @Description: 如果在商品中台查询不到商品, 返回默认的商品信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/29
     */
    private ShoppingCartCommodityResponseDTO getDefualtShopping(ShoppingCartCommodityResponseDTO cartResponseDTO, ShoppingCartCommodityDTO shoppingDTO) {
        cartResponseDTO.setStatus(2);
        cartResponseDTO.setSkuId(shoppingDTO.getSkuId());
        cartResponseDTO.setShopId(shoppingDTO.getShopId());
        cartResponseDTO.setActivitiesId(shoppingDTO.getActivitiesId());
        cartResponseDTO.setOrgId(shoppingDTO.getAffiliatedOrganizationId());
        cartResponseDTO.setNum(shoppingDTO.getNum());
        cartResponseDTO.setDirectId(shoppingDTO.getDirectId());
        return cartResponseDTO;
    }

    private String converterLong(Long value) {
        if (Objects.nonNull(value)) {
            return String.valueOf(value);
        }
        return "0";
    }

    private String getStockKey(Long shopId, Long skuId) {
        StringBuilder sb = new StringBuilder();
        sb.append(shopId);
        sb.append("_");
        sb.append(skuId);
        return sb.toString();
    }

    private List<SaleOrderItemDTO> batchQueryShop(Map<Long, List<ShoppingCartCommodityDTO>> map, ShoppingCartUserInfoDTO query) {
        List<SaleOrderItemDTO> orderList = Lists.newArrayList();
        map.forEach((k, v) -> {
            List<SaleOrderItemDTO> itemList = null;
            try {
                itemList = getOrderList(query, v.get(0).getShopId(), v.stream().map(ShoppingCartCommodityDTO::getSkuId).collect(Collectors.toList()), v.get(0).getActivitiesId());
            } catch (Exception e) {
                log.error("getOrderList error", e);
            }
            if (CollectionUtils.isNotEmpty(itemList)) {
                orderList.addAll(itemList);
            }
        });
        return orderList;
    }

    /**
     * 库存校验
     *
     * @param query          当前用户信息
     * @param cartCommoditys 需要校验库存的SKU.
     */
    private void verificationStock(ShoppingCartUserInfoDTO query, List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception {
        if (cartCommoditys.stream().anyMatch(s -> s.getOrderTypeId().equals(1))) {
            // 如果是标准订单不需要判断库存.
            return;
        }
        // 获取SKU的库存.
        List<SaleOrderItemDTO> orderList = batchQueryShop(groupShopping(cartCommoditys), query);
        // 对库存进行分组.
        Map<String, SaleOrderItemDTO> orderMap = orderList.stream().collect(Collectors.toMap(s -> getStockKey(s.getShopId(), s.getSkuId()), Function.identity()));
        // 进行库存是否足.
        boolean result = cartCommoditys.stream()
                .filter(s -> Objects.nonNull(orderMap.get(getStockKey(s.getShopId(), s.getSkuId())))).anyMatch(s -> {
                    SaleOrderItemDTO itemDTO = orderMap.get(getStockKey(s.getShopId(), s.getSkuId()));
                    if (itemDTO.getStock() < s.getNum()) {
                        return true;
                    }
                    return false;
                });
        if (result) {
            // 商品库存不足.
            throw new ApplicationException(ResultEnum.STOCK_ERROR);
        }
    }

    /**
     * @Description: 是否超过限购数量.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/31
     */
    private boolean checkMoreLimited(OnlineActivitiesGoodResponseDTO goodResponseDTO, Integer number, RMap<Long, Integer> rMap) {
        if (Objects.isNull(goodResponseDTO) || Objects.isNull(number)) {
            return false;
        }
        return goodResponseDTO.getLimitNum() < getShoppingNumber(number, rMap.get(goodResponseDTO.getSkuId()));
    }

    private Integer getShoppingNumber(Integer number, Integer historyNumber) {
        if (Objects.isNull(historyNumber)) {
            return number;
        }
        return (number + historyNumber);
    }

    /**
     * @Description: 是否有超过活动库存.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/23
     */
    private boolean hasOutNumber(ShoppingCartCommodityDTO shopCommodity, OnlineActivitiesGoodResponseDTO good) {
        // 如果是限购商品,需要判断购物车中的数量是否超过限购数量.
        if (good.getIsLimited().booleanValue()) {
            // 购物车中的数量不能大于活动的库存跟限购数量.
            if (shopCommodity.getNum() > good.getLimitNum() || shopCommodity.getNum() > good.getAvailableQuantity()) {
                return true;
            }
        } else {
            return shopCommodity.getNum() > good.getAvailableQuantity();
        }
        return false;
    }

    private List<SaleOrderItemDTO> getOrderList(ShoppingCartUserInfoDTO query, Long shopId, List<Long> skuIds, Long activitiesId) throws Exception {
        CommodityQuery commodityQuery = new CommodityQuery();
        commodityQuery.setAppId(query.getAppId());
        commodityQuery.setTenantId(query.getTenantId());
        commodityQuery.setShopId(shopId);
        commodityQuery.setSkuIds(skuIds);
        commodityQuery.setActivitiesId(activitiesId);
        commodityQuery.setStoreMap(query.getStoreMap());
        commodityQuery.setType(1);
        commodityQuery.setAllCustomerMap(query.getCustomers());
        commodityQuery.setCompanyType(query.getCompanyType());
        commodityQuery.setOrgId(ObjectUtils.isEmpty(appRuntimeEnv.getTopOrganization().getId()) ? appRuntimeEnv.getUserOrganization().getId() : appRuntimeEnv.getTopOrganization().getId());
        if (shopId.equals(1L)) {
            commodityQuery.setTicketType(0);
        }
        return commodityManager.getCommoditys(commodityQuery);
    }

    /**
     * sdk数据校验
     *
     * @param shoppingCartUserInfoDTO 当前用户信息
     * @param cartCommoditys          sku数据
     */
    private boolean verificationSdk(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO,
                                 List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception {
        //上下架校验,是否禁用校验,校验规则：上架的商品就是启用了的，是可以下单的，所以只需要校验是否上架.
        Map<Long, List<ShoppingCartCommodityDTO>> map = cartCommoditys.stream().collect(Collectors.groupingBy(ShoppingCartCommodityDTO::getShopId));
        map.forEach((k, v) -> {
            ListShopItemUpShelfRequestDTO listShopItemUpShelfRequestDTO = new ListShopItemUpShelfRequestDTO();
            listShopItemUpShelfRequestDTO.setAppId(shoppingCartUserInfoDTO.getAppId());
            listShopItemUpShelfRequestDTO.setTenantId(shoppingCartUserInfoDTO.getTenantId());
            listShopItemUpShelfRequestDTO.setShopId(k);
            listShopItemUpShelfRequestDTO.setSkuIds(v.stream().map(ShoppingCartCommodityDTO::getSkuId).collect(Collectors.toList()));
            List<ListShopItemUpShelfResponseDTO> shopList = commonService.searchCommoditySkuInfo(listShopItemUpShelfRequestDTO);
            boolean result = shopList.stream().anyMatch(s -> !s.getStatus().equals(1));
            //如果未查询到相关商品也意味着，商品已经不可用
            if (result || CollectionUtil.isEmpty(shopList)) {
                throw new ApplicationException(ResultEnum.COMMODITY_STATUS_ERROR);
            }
        });
        return true;
    }

    /**
     * @Description: 查询活动中的商品.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/23
     */
    private OnlineActivitiesResponseDTO getActivities(List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception {
        OnlineActivitiesGoodQuery goodQuery = new OnlineActivitiesGoodQuery();
        goodQuery.setActivitiesId(cartCommoditys.get(0).getActivitiesId());
        goodQuery.setSkuIds(cartCommoditys.stream().map(ShoppingCartCommodityDTO::getSkuId).collect(Collectors.toList()));
        goodQuery.setTenantId(appRuntimeEnv.getTenantId());
        Payload<OnlineActivitiesResponseDTO> payload = activitiesClient.getGoodByActivities(goodQuery);
        return GeneralConvertUtils.conv(payload.getPayload(), OnlineActivitiesResponseDTO.class);
    }

    /**
     * 设置redis key键
     *
     * @param shoppingCartUserInfoDTO 用户信息
     * @return redis key键
     */
    private String setRedisKey(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO) {
        return String.format(ShoppingCartRedisKeyConstant.USER_ID, shoppingCartUserInfoDTO.getTenantId(),
                shoppingCartUserInfoDTO.getAppId(), shoppingCartUserInfoDTO.getUserId());
    }

    @Override
    public Boolean addCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception {
        // 首先校验购物车是否上架,仅校验上架.
        checkCommodity(shoppingCartUserInfoDTO, cartCommoditys);
        // 然后加入缓存中.
        String redisKey = setRedisKey(shoppingCartUserInfoDTO);log.info("购物车缓存redisKey:{}",redisKey);
        RList<ShoppingCartCommodityDTO> rList = redisson.getList(redisKey);
        List<ShoppingCartCommodityDTO> shoppingList = rList.readAll();
        if (CollectionUtils.isNotEmpty(shoppingList)) {
            addShopping(cartCommoditys, shoppingList, rList);
        } else {
            // 然后把新增加的购物车添加到缓存中.
            rList.addAll(cartCommoditys);
        }
        return true;
    }

    /**
     * 校验上架
     * @param shoppingCartUserInfoDTO
     * @param cartCommoditys
     * @throws Exception
     */
    @Override
    public boolean checkCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception {
        //sku数据校验
        return verificationSdk(shoppingCartUserInfoDTO, cartCommoditys);
        //库存校验
        //verificationStock(shoppingCartUserInfoDTO, cartCommoditys);
    }

    /**
     * @Description: 添加购物车.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/23
     */
    private void addShopping(List<ShoppingCartCommodityDTO> newCartList, List<ShoppingCartCommodityDTO> shoppingList, RList<ShoppingCartCommodityDTO> rList) {
        newCartList.stream().forEach(data -> {
            boolean result = shoppingList.stream().anyMatch(s -> getStockKey(s.getShopId(), s.getSkuId()).equals(getStockKey(data.getShopId(), data.getSkuId())));
            if (result) {
                IntStream.range(0, shoppingList.size()).forEach(index -> {
                    ShoppingCartCommodityDTO cart = shoppingList.get(index);
                    if (getStockKey(cart.getShopId(), cart.getSkuId()).equals(getStockKey(data.getShopId(), data.getSkuId()))) {
                        cart.setNum(data.getNum());
                        rList.set(index, cart);
                        return;
                    }
                });
            } else {
                rList.add(data);
            }
        });
    }

    @Override
    public Boolean delCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, ShoppingCartDTO shoppingCartDTO, boolean hasComplete) {
        String redisKey = setRedisKey(shoppingCartUserInfoDTO);
        RList<ShoppingCartCommodityDTO> rList = redisson.getList(redisKey);
        List<ShoppingCartCommodityDTO> cartList = rList.readAll();
        if (CollectionUtils.isNotEmpty(cartList)) {
            // 删除指定位置的商品.
            IntStream.range(0, cartList.size()).forEach(index -> {
                ShoppingCartCommodityDTO cartDTO = cartList.get(index);
                if (hasDelete(shoppingCartDTO, cartDTO)) {
                    rList.remove(cartDTO);
                }
            });
        }
        return true;
    }

    /**
     * @Description: 获取历史购物车的key.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/31
     */
    private String getHistoryShopKey(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, Long activitiesId) {
        return String.format(ShoppingCartRedisKeyConstant.ACTIVITIES_ID, shoppingCartUserInfoDTO.getTenantId(),
                shoppingCartUserInfoDTO.getAppId(), shoppingCartUserInfoDTO.getUserId(), activitiesId);
    }

    /**
     * @Description: 是否需要删除.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/13
     */
    private boolean hasDelete(ShoppingCartDTO shoppingCartDTO, ShoppingCartCommodityDTO cartDTO) {
        return shoppingCartDTO.getShoppingCartCommodityDTOList().stream().anyMatch(s -> getStockKey(s.getShopId(), s.getSkuId()).equals(getStockKey(cartDTO.getShopId(), cartDTO.getSkuId())));
    }

    @Override
    public Boolean updateCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, List<ShoppingCartCommodityDTO> updateList) throws Exception {
        return this.addCommodity(shoppingCartUserInfoDTO, updateList);
    }

    @Override
    public List<ShoppingCartResponseDTO> searchCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, ShoppingCartQuery shoppingCartQuery) throws Exception {
        String redisKey = setRedisKey(shoppingCartUserInfoDTO);
        log.info("获取购物车列表redisKey：{}", redisKey);
        RList<ShoppingCartCommodityDTO> rList = redisson.getList(redisKey);
        List<ShoppingCartCommodityDTO> shoppingList = rList.readAll();
        if (CollectionUtils.isNotEmpty(shoppingList)) {
            return searchSkuInfo(shoppingCartUserInfoDTO, shoppingList);
        }
        //商品信息、价格信息
        return Lists.newArrayList();
    }

    /**
     * @Description: 查询商品.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/29
     */
    @Override
    public List<ShoppingCartCommodityDTO> searchShopping(ShoppingCartUserInfoDTO userInfoDTO, ShoppingCartQuery shoppingCartQuery) {
        String redisKey = setRedisKey(userInfoDTO);
        RList<ShoppingCartCommodityDTO> rList = redisson.getList(redisKey);
        List<ShoppingCartCommodityDTO> shoppingList = rList.readAll();
        if (CollectionUtils.isNotEmpty(shoppingList)) {
            Map<String, SaleOrderItemDTO> map = getOrderList(userInfoDTO, shoppingList);
            return shoppingList.stream().filter(s -> Objects.nonNull(map.get(getStockKey(s.getShopId(), s.getSkuId())))).collect(Collectors.toList());
        }
        //商品信息、价格信息
        return Lists.newArrayList();
    }

    /**
     * @Description: 查询购物车商品总数.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/28
     */
    @Override
    public Long getShoppingCount(ShoppingCartUserInfoDTO setUserInfo, ShoppingCartQuery shoppingCartQuery) {
        String redisKey = setRedisKey(setUserInfo);
        RList<ShoppingCartCommodityDTO> rList = redisson.getList(redisKey);
        List<ShoppingCartCommodityDTO> shoppingList = rList.readAll();
        if (CollectionUtils.isNotEmpty(shoppingList)) {
            // 有些商品被删除了,所以需要查询商品中台.
            Map<String, SaleOrderItemDTO> map = getOrderList(setUserInfo, shoppingList);
            return shoppingList.stream().filter(s -> Objects.nonNull(map.get(getStockKey(s.getShopId(), s.getSkuId())))).count();
        }
        // 没有返回为0.
        return 0L;
    }

    /**
     * @Description: 查询购物车提供给APP使用.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    @Override
    public List<ShoppingCartCommodityDTO> searchShoppingToApp(ShoppingCartUserInfoDTO setUserInfo, ShoppingCartQuery shoppingCartQuery) {
        return this.searchShopping(setUserInfo, shoppingCartQuery);
    }

    /**
     * @Description: 查询最新的几条记录.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/17
     */
    @Override
    public List<ShoppingCartCommodityResponseDTO> searchCommodityDesDate(ShoppingCartUserInfoDTO setUserInfo) throws Exception {
        String redisKey = setRedisKey(setUserInfo);
        RList<ShoppingCartCommodityDTO> rList = redisson.getList(redisKey);
        List<ShoppingCartCommodityDTO> shoppingList = rList.readAll();
        if (CommonUtils.isEmpty(shoppingList)) {
            return Lists.newArrayList();
        }
        // 按照时间倒叙.
        orderByDate(shoppingList);
        // 查询商品详情.
        List<ShoppingCartResponseDTO> result = searchSkuInfo(setUserInfo, shoppingList.stream().limit(getLimit(shoppingList.size())).collect(Collectors.toList()));
        // 返回商品的详情.
        return result.stream().
                map(ShoppingCartResponseDTO::getShoppingCartCommodityResponseDTOList).
                flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * @Description: 时间倒叙.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/18
     */
    private void orderByDate(List<ShoppingCartCommodityDTO> shoppingList) {
        Collections.sort(shoppingList, (s1, s2) -> getLong(s2.getOperationTime()).compareTo(getLong(s1.getOperationTime())));
    }

    /**
     * @Description: 获取分页.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/17
     */
    private int getLimit(int size) {
        return Math.min(size, 3);
    }

    private Long getLong(Long time) {
        if (Objects.nonNull(time)) {
            return time;
        }
        return 0L;
    }
}

