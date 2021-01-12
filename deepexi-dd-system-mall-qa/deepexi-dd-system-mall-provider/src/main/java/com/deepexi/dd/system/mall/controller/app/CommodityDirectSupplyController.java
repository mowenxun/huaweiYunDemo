package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.system.mall.domain.query.app.CommodityDirectSupplyQuery;
import com.deepexi.dd.system.mall.domain.query.app.FrontCategoryTreeRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.RecommendSkuCommodityQuery;
import com.deepexi.dd.system.mall.domain.query.app.ShelvesItemSkuDetailRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityDirectSupplyVO;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.FrontCategoryTreeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.dd.system.mall.service.app.CommodityDirectSupplyService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-08 15:49
 */
@Api(value = "直供商品管理", tags = "直供商品管理")
@RequestMapping("/admin-api/v1/commodityDirectSupply")
@RestController
public class CommodityDirectSupplyController {

    @Autowired
    private CommodityDirectSupplyService commodityDirectSupplyService;

    @ApiOperation("分类查询直供商品-分页")
    @GetMapping("/page")
    public Payload<PageBean<CommodityTypeResponseVO>> getShelvesItemSku(@Valid CommodityDirectSupplyQuery query) throws Exception {
        return getPayload(null, commodityDirectSupplyService.getShelvesItemSku(query), null, null);
    }

    @ApiOperation("获取首页推荐的直供商品-分页")
    @GetMapping("/getRecommendSkuCommodity")
    public Payload<List<CommodityTypeResponseVO>> getRecommendSkuCommodity(@Valid RecommendSkuCommodityQuery query) throws Exception {
        return getPayload(null, null, commodityDirectSupplyService.getRecommendSkuCommodity(query), null);
    }

    @ApiOperation("查询前台类目")
    @GetMapping("/getFrontCategory")
    public Payload<List<FrontCategoryTreeResponseVO>> getFrontCategoryTree(@Valid FrontCategoryTreeRequestQuery query) {
        return getPayload(commodityDirectSupplyService.getFrontCategoryTree(query), null, null, null);
    }

    @ApiOperation("直供商品sku详情")
    @GetMapping("/getItemSkuDetail")
    public Payload<ShelvesItemSkuDetailResponseVO> getItemSkuDetail(@Valid ShelvesItemSkuDetailRequestQuery query) throws Exception {
        return getPayload(null, null, null, commodityDirectSupplyService.getItemSkuDetail(query));
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
                               List<CommodityTypeResponseVO> list,
                               ShelvesItemSkuDetailResponseVO result) {
        Payload payload = new Payload<>();
        // 前台类目
        if (Objects.nonNull(resultList)) {
            if (resultList.size() != 0) {
                payload.setCode(resultList.get(0).getCode());
                payload.setMsg(resultList.get(0).getMsg());
                payload.setPayload(resultList);
            }
            return payload;
        }
        // 分页列表
        if (Objects.nonNull(resultPage) && Objects.nonNull(resultPage.getContent())) {
            if (resultPage.getContent().size() != 0) {
                payload.setCode(resultPage.getContent().get(0).getCode());
                payload.setMsg(resultPage.getContent().get(0).getMsg());
                payload.setPayload(resultPage);
            }
            return payload;
        }
        // 列表
        if (Objects.nonNull(list)) {
            if (list.size() != 0) {
                payload.setCode(list.get(0).getCode());
                payload.setMsg(list.get(0).getMsg());
                payload.setPayload(list);
            }
            return payload;
        }
        // 详情
        if (Objects.nonNull(result)) {
            payload.setCode(result.getCode());
            payload.setMsg(result.getMsg());
            payload.setPayload(result);
        }
        return payload;
    }
}
