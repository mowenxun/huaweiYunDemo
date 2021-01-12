package com.deepexi.dd.system.mall.controller.commodity;

import com.deepexi.dd.system.mall.domain.dto.commodity.CategoryCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityRecommendResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.query.commodity.CategoryCommodityRecommendQuery;
import com.deepexi.dd.system.mall.domain.query.commodity.NewsCommodityAdminQuery;
import com.deepexi.dd.system.mall.domain.query.commodity.NewsCommodityRecommendQuery;
import com.deepexi.dd.system.mall.service.commodity.AuthorizeCommodityService;
import com.deepexi.sdk.commodity.api.ShelvesItemSkuOpenApi;
import com.deepexi.sdk.commodity.api.ShopItemOpenApi;
import com.deepexi.sdk.commodity.model.PageBeanPageSkuItemBySkuQueryResponseDTO;
import com.deepexi.sdk.commodity.model.PageSkuItemBySkuQueryRequestDTO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liaop on 2020/7/21.
 */
@Api(value = "获取授权商品", tags = "获取授权商品")
@RequestMapping("/admin-api/v1/domain/authorize/")
@RestController
public class AuthorizeCommodityController {

    @Autowired
    private AuthorizeCommodityService authorizeCommodityService;

    @GetMapping("/getCommodityList")
    public Payload<PageBeanPageSkuItemBySkuQueryResponseDTO> getCommodityList(PageSkuItemBySkuQueryRequestDTO request){
        return new Payload<>(null);
    }

    @PostMapping("/listNewsCommodity")
    @ApiOperation("查询新品推荐")
    public Payload<List<NewsCommodityResponseDTO>> getNewsCommodity(@RequestBody NewsCommodityAdminQuery query)throws Exception{
        return new Payload(authorizeCommodityService.getNewsCommodity(query));
    }

    @PostMapping("/listNewsCommodityResponse")
    @ApiOperation("商城首页-查询新品推荐")
    public Payload<List<NewsCommodityRecommendResponseDTO>> getNewsCommodityResponse(@RequestBody NewsCommodityRecommendQuery query)throws Exception{
        return new Payload(authorizeCommodityService.getNewsCommodityResponse(query));
    }

    @GetMapping("/listCategoryCommodityResponse")
    @ApiOperation("商城首页-热门分类")
    public Payload<List<CategoryCommodityResponseDTO>> listCategoryCommodityResponse(CategoryCommodityRecommendQuery query)throws Exception{
        return new Payload<>(authorizeCommodityService.getCategoryCommodityResponseDTO(query));
    }
}
