package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartUserInfoDTO;
import com.deepexi.dd.domain.transaction.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.domain.transaction.service.ShoppingCartService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车控制器
 * @author yangwu
 * @date 2020/7/6
 */
@Api(tags = "购物车控制器")
@RequestMapping("/domain/transaction/shoppingCart")
@RestController
public class ShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    private ShoppingCartUserInfoDTO setUserInfo(){
        ShoppingCartUserInfoDTO shoppingCartUserInfoDTO = new ShoppingCartUserInfoDTO();
        shoppingCartUserInfoDTO.setUserId(1000L);
        return shoppingCartUserInfoDTO;
    }
    @ApiOperation("添加商品到购物车")
    @PostMapping
    public Boolean addCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
        return shoppingCartService.addCommodity(setUserInfo(), shoppingCartDTO.getShoppingCartCommodityDTOList());
    }
    @ApiOperation("删除商品到购物车")
    @DeleteMapping
    public Boolean delCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.delCommodity(setUserInfo(), shoppingCartDTO, false);
    }
    @ApiOperation("更新商品到购物车")
    @PutMapping
    public Boolean updateCommodity(@RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {
        return shoppingCartService.updateCommodity(setUserInfo(), shoppingCartDTO.getShoppingCartCommodityDTOList());
    }
    @ApiOperation("搜索购物车上的商品")
    @GetMapping
    public List<ShoppingCartResponseDTO> searchCommodity(ShoppingCartQuery shoppingCartQuery) throws Exception {
        return shoppingCartService.searchCommodity(setUserInfo(), shoppingCartQuery);
    }
}
