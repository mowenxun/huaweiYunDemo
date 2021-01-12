package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreQuery;
import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.FrontCategoryTreeRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.FrontCategoryTreeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.dd.system.mall.service.app.CommodityCoreService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-08 15:49
 */
@Api(value = "商品分类管理", tags = "商品分类管理")
@RequestMapping("/admin-api/v1/commodityCore")
@RestController
public class CommodityCoreController {

    @Autowired
    private CommodityCoreService commodityCoreService;

    @ApiOperation("分类查询商品-分页")
    @PostMapping("/page")
    public Payload<PageBean<CommodityTypeResponseVO>> pageShelvesItemSku(@Valid @RequestBody CommodityCoreQuery query) {
        return getPayload(null, commodityCoreService.pageShelvesItemSku(query), null);
    }

    @ApiOperation("区域订货商品查询")
    @PostMapping("/findCommodityItem")
    public Payload<PageBean<CommodityTypeResponseVO>> findCommodityItemList(@Valid @RequestBody CommodityCoreQuery query) throws Exception {
        if(CollectionUtils.isEmpty(query.getAuthorizeSkuList())){
            return new Payload<>(new PageBean<>());
        }
        return getPayload(null, commodityCoreService.findCommodityItemList(query), null);
    }

    @ApiOperation("查询前台类目")
    @GetMapping("/getFrontCategory")
    public Payload<List<FrontCategoryTreeResponseVO>> listFrontCategoryTree(@Valid FrontCategoryTreeRequestQuery query) {
        return getPayload(commodityCoreService.listFrontCategoryTree(query), null, null);
    }

    @ApiOperation("非直供商品详情")
    @GetMapping("/getShelvesItemSkuDetail")
    public Payload<ShelvesItemSkuDetailResponseVO> getShelvesItemSkuDetail(@Valid CommodityCoreRequestQuery query) throws Exception{
        return getPayload(null, null, commodityCoreService.getShelvesItemSkuDetail(query));
    }

    /**
     * 获取payload
     *
     * @param resultList
     * @param resultPage
     * @param result
     * @return
     */
    private Payload getPayload(List<FrontCategoryTreeResponseVO> resultList,
                               PageBean<CommodityTypeResponseVO> resultPage,
                               ShelvesItemSkuDetailResponseVO result) {
        Payload payload = new Payload<>();
        // 分页列表
        if (Objects.nonNull(resultPage)) {
            if (resultPage.getContent().size() != 0) {
                payload.setMsg(resultPage.getContent().get(0).getMsg());
                payload.setCode(resultPage.getContent().get(0).getCode());
                payload.setPayload(resultPage);
            }
            return payload;
        }
        // 前台类目
        if (Objects.nonNull(resultList)) {
            if (resultList.size() != 0) {
                payload.setMsg(resultList.get(0).getMsg());
                payload.setCode(resultList.get(0).getCode());
                payload.setPayload(resultList);
            }
            return payload;
        }
        // 详情
        if (Objects.nonNull(result)) {
            payload.setMsg(result.getMsg());
            payload.setCode(result.getCode());
            payload.setPayload(result);
        }
        return payload;
    }
}
