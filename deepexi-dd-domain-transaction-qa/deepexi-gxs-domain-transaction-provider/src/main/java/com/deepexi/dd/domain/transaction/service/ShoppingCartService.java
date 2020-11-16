package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.*;
import com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 购物车业务接口
 *
 * @author yangwu
 * @date 2020/7/6
 */
public interface ShoppingCartService {
    /**
     * 添加
     *
     * @param shoppingCartUserInfoDTO 用户信息
     * @param cartCommoditys         购物车信息
     * @return 操作成功或失败
     */
    Boolean addCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception;

    /**
     * 校验购物车商品
     *
     * @param shoppingCartUserInfoDTO 用户信息
     * @param cartCommoditys         购物车信息
     * @return 操作成功或失败
     */
    boolean checkCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception;

    /**
     * 删除
     *
     * @param shoppingCartUserInfoDTO 用户信息
     * @param shoppingCartDTO         购物车信息
     * @return 操作成功或失败
     */
    Boolean delCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, ShoppingCartDTO shoppingCartDTO, boolean hasComplete);

    /**
     * 修改
     *
     * @param shoppingCartUserInfoDTO 用户信息
     * @param cartCommoditys         购物车信息
     * @return 操作成功或失败
     */
    Boolean updateCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, List<ShoppingCartCommodityDTO> cartCommoditys) throws Exception;

    /**
     * 查询
     *
     * @param shoppingCartUserInfoDTO 用户信息
     * @param shoppingCartQuery       购物车信息
     * @return 查询结果
     */
    List<ShoppingCartResponseDTO> searchCommodity(ShoppingCartUserInfoDTO shoppingCartUserInfoDTO, ShoppingCartQuery shoppingCartQuery) throws Exception;

    /**
     * @Description: 查询购物车商品总数.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/28
     */
    Long getShoppingCount(ShoppingCartUserInfoDTO setUserInfo, ShoppingCartQuery shoppingCartQuery);

    /**
     * @Description: 查询商品.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/29
     */
    List<ShoppingCartCommodityDTO> searchShopping(ShoppingCartUserInfoDTO userInfoDTO, ShoppingCartQuery shoppingCartQuery);

    /**
     * @Description: 查询购物车提供给APP使用.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/5
     */
    List<ShoppingCartCommodityDTO> searchShoppingToApp(ShoppingCartUserInfoDTO setUserInfo, ShoppingCartQuery shoppingCartQuery);

    /**
    * @Description: 查询最新的几条记录.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/17
    */
    List<ShoppingCartCommodityResponseDTO> searchCommodityDesDate(ShoppingCartUserInfoDTO setUserInfo) throws Exception;
}
