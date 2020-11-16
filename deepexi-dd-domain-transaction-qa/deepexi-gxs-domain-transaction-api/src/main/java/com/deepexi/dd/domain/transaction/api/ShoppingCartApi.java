package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 购物车接口
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Api(value = "购物车接口")
@RequestMapping("/open-api/v1/domain/transaction/shoppingCart")
public interface ShoppingCartApi {

    /**
     * 添加
     *
     * @param shoppingCartDTO 购物车信息
     * @return 操作成功或失败
     */
    @PostMapping("/addShopping")
    Boolean addCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception;

    /**
     * 删除
     *
     * @param shoppingCartDTO 购物车信息
     * @return 操作成功或失败
     */
    @DeleteMapping("/delShopping")
    Boolean delCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO);

    /**
     * 修改
     *
     * @param shoppingCartDTO 购物车信息
     * @return 操作成功或失败
     */
    @PutMapping("/updateShopping")
    Boolean updateCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception;

    /**
     * 查询
     *
     * @param shoppingCartQuery 购物车信息
     * @return 查询结果
     */
    @PostMapping("/searchCommodity")
    List<ShoppingCartResponseDTO> searchCommodity(@Valid @RequestBody ShoppingCartQuery shoppingCartQuery) throws Exception;

    /**
     * 获取最新加入购物车的商品.
     *
     * @return 查询结果
     */
    @GetMapping("/searchCommodityDesDate")
    List<ShoppingCartCommodityResponseDTO> searchCommodityDesDate(@RequestParam(value = "appId") Long appId,
                                                         @RequestParam(value = "tenantId") String tenantId) throws Exception;

    /**
     * 查询
     *
     * @param shoppingCartQuery 查询商品.
     * @return 查询结果
     */
    @PostMapping("/searchShopping")
    List<ShoppingCartCommodityResponseDTO> searchShopping(@Valid @RequestBody ShoppingCartQuery shoppingCartQuery);

    /**
     * 查询
     *
     * @param shoppingCartQuery 查询商品.
     * @return 查询结果
     */
    @PostMapping("/app/searchShopping")
    List<ShoppingCartCommodityResponseDTO> searchShoppingToApp(@Valid @RequestBody ShoppingCartQuery shoppingCartQuery);

    /**
     * 查询购物车总数.
     *
     * @param shoppingCartQuery 购物车信息
     * @return 查询结果
     */
    @PostMapping("/getShoppingCount")
    Long getShoppingCount(@Valid @RequestBody ShoppingCartQuery shoppingCartQuery);

    /**
     * 校验购物车商品
     *
     * @param shoppingCartDTO 购物车信息
     * @return 操作成功或失败
     */
    @PostMapping("/checkShopping")
    Boolean checkCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception;
}
