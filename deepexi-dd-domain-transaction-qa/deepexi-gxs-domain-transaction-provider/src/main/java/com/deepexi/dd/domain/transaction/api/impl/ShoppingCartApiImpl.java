package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.ShoppingCartApi;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.*;
import com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.domain.transaction.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yangwu
 * @date 2020/7/6
 */
@RestController
@Slf4j
public class ShoppingCartApiImpl implements ShoppingCartApi {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    private ShoppingCartUserInfoDTO setUserInfo(Long appId){
        ShoppingCartUserInfoDTO shoppingCartUserInfoDTO = new ShoppingCartUserInfoDTO();
        shoppingCartUserInfoDTO.setUserId(appRuntimeEnv.getUserId());
        shoppingCartUserInfoDTO.setTenantId(appRuntimeEnv.getTenantId());
        if(null == appId || appId < 0){
            shoppingCartUserInfoDTO.setAppId(appRuntimeEnv.getAppId());
        }else{
            shoppingCartUserInfoDTO.setAppId(appId);
        }
        log.info("购物车当前用户：{},appId:{},tenantId:{}", shoppingCartUserInfoDTO,shoppingCartUserInfoDTO.getAppId(),shoppingCartUserInfoDTO.getTenantId());
        return shoppingCartUserInfoDTO;
    }

    @Override
    public Boolean addCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
        return shoppingCartService.addCommodity(setUserInfo(shoppingCartDTO.getAppId()), shoppingCartDTO.getShoppingCartCommodityDTOList());
    }

    @Override
    public Boolean checkCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
        return shoppingCartService.checkCommodity(setUserInfo(shoppingCartDTO.getAppId()), shoppingCartDTO.getShoppingCartCommodityDTOList());
    }

    @Override
    public Boolean delCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.delCommodity(setUserInfo(shoppingCartDTO.getAppId()), shoppingCartDTO, false);
    }

    @Override
    public Boolean updateCommodity(@RequestBody ShoppingCartDTO cartList) throws Exception {
        return shoppingCartService.updateCommodity(setUserInfo(cartList.getAppId()), cartList.getShoppingCartCommodityDTOList());
    }

    @Override
    public List<ShoppingCartResponseDTO> searchCommodity(@RequestBody ShoppingCartQuery shoppingCartQuery) throws Exception {
        return shoppingCartService.searchCommodity(setUserInfo(shoppingCartQuery.getAppId()), shoppingCartQuery);
    }

    /**
     * 获取最新加入购物车的商品.
     *
     * @return 查询结果
     */
    @Override
    public List<ShoppingCartCommodityResponseDTO> searchCommodityDesDate(@RequestParam(value = "appId") Long appId,
                                                                         @RequestParam(value = "tenantId") String tenantId) throws Exception {
        return shoppingCartService.searchCommodityDesDate(setUserInfo(appId));
    }

    /**
     * 查询
     *
     * @param shoppingCartQuery 查询商品.
     * @return 查询结果
     */
    public List<ShoppingCartCommodityResponseDTO> searchShopping(@RequestBody ShoppingCartQuery shoppingCartQuery) {
        List<ShoppingCartCommodityDTO> result = shoppingCartService.searchShopping(setUserInfo(shoppingCartQuery.getAppId()), shoppingCartQuery);
        return GeneralConvertUtils.convert2List(result, ShoppingCartCommodityResponseDTO.class);
    }

    /**
     * 查询
     *
     * @param shoppingCartQuery 查询商品.
     * @return 查询结果
     */
    @Override
    public List<ShoppingCartCommodityResponseDTO> searchShoppingToApp(@RequestBody ShoppingCartQuery shoppingCartQuery) {
        List<ShoppingCartCommodityDTO> result = shoppingCartService.searchShoppingToApp(setUserInfo(shoppingCartQuery.getAppId()), shoppingCartQuery);
        return GeneralConvertUtils.convert2List(result, ShoppingCartCommodityResponseDTO.class);
    }

    /**
     * 查询购物车总数.
     *
     * @param shoppingCartQuery 购物车信息
     * @return 查询结果
     */
    @Override
    public Long getShoppingCount(@Valid @RequestBody ShoppingCartQuery shoppingCartQuery) {
        return shoppingCartService.getShoppingCount(setUserInfo(shoppingCartQuery.getAppId()), shoppingCartQuery);
    }
}
