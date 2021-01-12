package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.shoppingcart.ShoppingCartRequestDTO;
import com.deepexi.dd.system.mall.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.system.mall.domain.vo.shoppingcart.ShoppingCartResponseVO;

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
     * @param shoppingCartRequestDTO 购物车信息
     * @return 操作成功或失败
     */
    Boolean addCommodity(ShoppingCartRequestDTO shoppingCartRequestDTO) throws Exception;

    /**
     * 校验购物车商品
     *
     * @param shoppingCartRequestDTO 购物车信息
     * @return 操作成功或失败
     */
    Boolean checkCommodity(ShoppingCartRequestDTO shoppingCartRequestDTO) throws Exception;

    /**
     * 删除
     *
     * @param shoppingCartRequestDTO 购物车信息
     * @return 操作成功或失败
     */
    Boolean delCommodity(ShoppingCartRequestDTO shoppingCartRequestDTO);

    /**
     * 修改
     *
     * @param shoppingCartRequestDTO 购物车信息
     * @return 操作成功或失败
     */
    Boolean updateCommodity(ShoppingCartRequestDTO shoppingCartRequestDTO) throws Exception;

    /**
     * 查询
     *
     * @param shoppingCartQuery 购物车信息
     * @return 查询结果
     */
    List<ShoppingCartResponseVO> searchCommodity(ShoppingCartQuery shoppingCartQuery) throws Exception;

    /**
     * @Description: 获取购物车商品总数.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/28
     */
    Long getShoppingCount(ShoppingCartQuery shoppingCartQuery);

    /**
     * @Description: 查询购物车.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/29
     */
    List<ShoppingCartCommodityResponseDTO> searchShopping(ShoppingCartQuery shoppingCartQuery);

    /**
     * @Description: 查询购物车提供给APP使用.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/29
     */
    List<ShoppingCartCommodityResponseDTO> searchShoppingToApp(ShoppingCartQuery shoppingCartQuery);

    /**
    * @Description: 查询最新的记录.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/17
    */
    List<ShoppingCartCommodityResponseDTO> searchCommodityDesDate(Long appId, String tenantId) throws Exception;
}
