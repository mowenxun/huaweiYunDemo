package com.deepexi.dd.system.mall.controller;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.system.mall.domain.dto.shoppingcart.ShoppingCartCommodityGroupBySupplierResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.shoppingcart.ShoppingCartCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.shoppingcart.ShoppingCartRequestDTO;
import com.deepexi.dd.system.mall.domain.query.ListCommodityShoppingCartRequestQuery;
import com.deepexi.dd.system.mall.domain.query.shoppingcart.ShoppingCartCommodityRequestQuery;
import com.deepexi.dd.system.mall.domain.query.shoppingcart.ShoppingCartQuery;
import com.deepexi.dd.system.mall.domain.vo.shoppingcart.ShoppingCartCommodityResponseVO;
import com.deepexi.dd.system.mall.domain.vo.shoppingcart.ShoppingCartResponseVO;
import com.deepexi.dd.system.mall.remote.customer.CompanyInfoClient;
import com.deepexi.dd.system.mall.service.ShoppingCartService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import domain.dto.CompanyInfoResponseApiDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 购物车控制器
 *
 * @author yangwu
 * @date 2020/7/6
 */
@Api(tags = "购物车接口")
@RequestMapping("/admin-api/v1/domain/transaction/shoppingCart")
@Slf4j
@RestController
public class ShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CompanyInfoClient companyInfoClient;

    @ApiOperation("新增")
    @PostMapping
    public Payload<Boolean> addCommodity(@RequestBody @Valid ShoppingCartRequestDTO shoppingCartRequestDTO) throws Exception {
        return new Payload<>(shoppingCartService.addCommodity(shoppingCartRequestDTO));
    }

    @ApiOperation("删除")
    @DeleteMapping
    public Payload<Boolean> delCommodity(@RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
        return new Payload<>(shoppingCartService.delCommodity(shoppingCartRequestDTO));
    }

    @ApiOperation("修改")
    @PutMapping
    public Payload<Boolean> updateCommodity(@RequestBody @Valid ShoppingCartRequestDTO shoppingCartRequestDTO) throws Exception {
        return new Payload<>(shoppingCartService.updateCommodity(shoppingCartRequestDTO));
    }

    @ApiOperation("查询")
    @GetMapping
    public Payload<List<ShoppingCartResponseVO>> searchCommodity(@Valid ShoppingCartQuery shoppingCartQuery) throws Exception {
        return new Payload<>(shoppingCartService.searchCommodity(shoppingCartQuery));
    }

    @ApiOperation("校验购物车商品")
    @PostMapping("/check")
    public Payload<Boolean> checkCommodity(@RequestBody @Valid ShoppingCartRequestDTO shoppingCartRequestDTO) throws Exception {
        return new Payload<>(shoppingCartService.checkCommodity(shoppingCartRequestDTO));
    }

    @ApiOperation("查询购物车")
    @PostMapping("/searchShopping")
    public Payload<List<ShoppingCartCommodityResponseDTO>> searchShopping(@Valid @RequestBody ShoppingCartQuery shoppingCartQuery) {
        return new Payload<>(shoppingCartService.searchShopping(shoppingCartQuery));
    }

    @ApiOperation("查询购物车-app使用")
    @PostMapping("/app/searchShopping")
    public Payload<List<ShoppingCartCommodityResponseDTO>> searchShoppingToApp(@Valid @RequestBody ShoppingCartQuery shoppingCartQuery) {
        return new Payload<>(shoppingCartService.searchShoppingToApp(shoppingCartQuery));
    }

    @ApiOperation("查询购物车中的商品总数.")
    @GetMapping("/getShoppingCount")
    public Payload<Long> getShoppingCount(@Valid ShoppingCartQuery shoppingCartQuery) {
        return new Payload<>(shoppingCartService.getShoppingCount(shoppingCartQuery));
    }

    /**
     * 获取最新加入购物车的商品.
     *
     * @return 查询结果
     */
    @GetMapping("/searchCommodityDesDate")
    @ApiOperation("获取最新加入购物车的商品.")
    public Payload<List<ShoppingCartCommodityResponseDTO>> searchCommodityDesDate(@RequestParam(value = "appId") Long appId,
                                                                                  @RequestParam(value = "tenantId") String tenantId) throws Exception {
        return new Payload<>(shoppingCartService.searchCommodityDesDate(appId, tenantId));
    }

    /**
     * 获取根据供应商分组的购物车商品列表.
     * tip：商品列表中的shopId在商品上架时，绑定为供应商组织id，根据供应商组织id，查询供应商信息
     * 商品shopId=企业CompanyId=供应商组织id
     * @return 查询结果
     */
    @GetMapping("/listCommodityGroupBySuppliers")
    @ApiOperation("获取根据供应商组织id分组的购物车商品列表.")
    public Payload<List<ShoppingCartCommodityGroupBySupplierResponseDTO>> listCommodityGroupBySuppliers(@RequestParam(value = "appId") Long appId,
                                                                                                                 @RequestParam(value = "tenantId") String tenantId) throws Exception {
        ShoppingCartQuery shoppingCartQuery = new ShoppingCartQuery();
        shoppingCartQuery.setTenantId(tenantId);
        shoppingCartQuery.setAppId(appId);
        //1.获取原购物车列表
        List<ShoppingCartResponseVO> response = shoppingCartService.searchCommodity(shoppingCartQuery);
        if (CollectionUtil.isEmpty(response)) {
            return new Payload<>(Collections.emptyList());
        }
        //2.抽取出商品列表
        List<ShoppingCartCommodityResponseVO> allCartCommodityList = new ArrayList<>();

        response.forEach(item -> allCartCommodityList.addAll(item.getShoppingCartCommodityResponseDTOList()));

        return new Payload<>(getAssemblyData(allCartCommodityList));
    }


    /**
     * 根据skuIds获取供应商组织id分组的购物车商品列表.
     * tip：商品列表中的shopId在商品上架时，绑定为供应商组织id，根据供应商组织id，查询供应商信息
     * 商品shopId=企业CompanyId=供应商组织id
     * @return 查询结果
     */
    @PostMapping("/listCommodityBySkuIds")
    @ApiOperation("根据skuIds获取供应商组织id分组的购物车商品列表.")
    public Payload<List<ShoppingCartCommodityGroupBySupplierResponseDTO>> listCommodityBySkuIds(@RequestParam(value = "appId") Long appId,
                                                                                                @RequestParam(value = "tenantId") String tenantId,
                                                                                                @RequestBody ListCommodityShoppingCartRequestQuery query) throws Exception {
        ShoppingCartQuery shoppingCartQuery = new ShoppingCartQuery();
        shoppingCartQuery.setTenantId(tenantId);
        shoppingCartQuery.setAppId(appId);
        //1.获取原购物车列表
        List<ShoppingCartResponseVO> response = shoppingCartService.searchCommodity(shoppingCartQuery);
        if (CollectionUtil.isEmpty(response)) {
            return new Payload<>(Collections.emptyList());
        }
        //2.抽取出商品列表
        List<ShoppingCartCommodityResponseVO> allCartCommodityList = new ArrayList<>();

        response.forEach(item -> allCartCommodityList.addAll(item.getShoppingCartCommodityResponseDTOList()));

        //3.过滤skuIds
        List<ShoppingCartCommodityResponseVO> temp = new ArrayList<>();
        for (ShoppingCartCommodityRequestQuery requestQuery : query.getRequestQueryList()) {
            //进行一个个匹配
            List<ShoppingCartCommodityResponseVO> responseVOS = allCartCommodityList.stream().filter(responseVO ->
                    responseVO.getShopId().equals(requestQuery.getShopId()) &&
                    responseVO.getSkuId().equals(requestQuery.getSkuId()) &&
                    responseVO.getNum().equals(requestQuery.getNum())).collect(Collectors.toList());
            temp.addAll(responseVOS);
        }
        return new Payload<>(getAssemblyData(temp));
    }

    private List<ShoppingCartCommodityGroupBySupplierResponseDTO> getAssemblyData(List<ShoppingCartCommodityResponseVO> allCartCommodityList) throws Exception {
        //1.分组为Map<shopId,商品列表>
        Map<Long, List<ShoppingCartCommodityResponseVO>> map = allCartCommodityList.stream()
                .collect(Collectors.groupingBy(ShoppingCartCommodityResponseVO::getShopId));

        //2.获取供应商组织ids，即：shopIds
        List<Long> list = new ArrayList<>(map.keySet());

        //3.调用接口获取供应商信息
        String jsonString = JSON.toJSONString(companyInfoClient.listCompanyInfosByCompanyIds(list).getPayload());
        List<CompanyInfoResponseApiDTO> companyInfos = JSON.parseArray(jsonString, CompanyInfoResponseApiDTO.class);
        if (CollectionUtil.isEmpty(companyInfos)) {
            log.error("组织id获取供应商列表为空");
            return Collections.emptyList();
        }
        //4.整合返回信息
        List<ShoppingCartCommodityGroupBySupplierResponseDTO> result = new ArrayList<>();

        for (CompanyInfoResponseApiDTO companyInfo : companyInfos) {
            ShoppingCartCommodityGroupBySupplierResponseDTO temp = new ShoppingCartCommodityGroupBySupplierResponseDTO();
            temp.setShoppingCartCommodityList(map.get(companyInfo.getCompanyId()));
            temp.setSupplier(companyInfo);
            result.add(temp);
        }
        return result;
    }
}
