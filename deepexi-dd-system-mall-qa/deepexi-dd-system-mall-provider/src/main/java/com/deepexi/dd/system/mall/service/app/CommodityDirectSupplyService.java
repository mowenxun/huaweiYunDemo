package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.system.mall.domain.query.app.CommodityDirectSupplyQuery;
import com.deepexi.dd.system.mall.domain.query.app.FrontCategoryTreeRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.RecommendSkuCommodityQuery;
import com.deepexi.dd.system.mall.domain.query.app.ShelvesItemSkuDetailRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityDirectSupplyVO;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.FrontCategoryTreeResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.ShelvesItemSkuDetailResponseVO;
import com.deepexi.util.pageHelper.PageBean;

import javax.validation.Valid;
import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-10 14:11
 */
public interface CommodityDirectSupplyService {

    /**
     * 分类查询商品-分页
     *
     * @param query
     * @return
     */
    PageBean<CommodityTypeResponseVO> getShelvesItemSku(@Valid CommodityDirectSupplyQuery query) throws Exception;

    /**
     * 查询前台类目
     *
     * @param query
     * @return
     */
    List<FrontCategoryTreeResponseVO> getFrontCategoryTree(@Valid FrontCategoryTreeRequestQuery query);

    /**
     * sku详情
     *
     * @param query
     * @return
     */
    ShelvesItemSkuDetailResponseVO getItemSkuDetail(ShelvesItemSkuDetailRequestQuery query) throws Exception;

    /**
     * 获取首页推荐的直供商品-分页
     *
     * @param querys
     * @return
     */
    List<CommodityTypeResponseVO> getRecommendSkuCommodity(@Valid RecommendSkuCommodityQuery querys) throws Exception;

}
