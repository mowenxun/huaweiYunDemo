package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreQuery;
import com.deepexi.dd.system.mall.domain.query.app.CommodityCoreRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.FrontCategoryTreeRequestQuery;
import com.deepexi.dd.system.mall.domain.query.app.ShelvesItemSkuDetailRequestQuery;
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
public interface CommodityCoreService {

    /**
     * 分类查询商品-分页
     *
     * @param query
     * @return
     */
    PageBean<CommodityTypeResponseVO> pageShelvesItemSku(@Valid CommodityCoreQuery query);

    /**
     * 查询前台类目
     *
     * @param query
     * @return
     */
    List<FrontCategoryTreeResponseVO> listFrontCategoryTree(@Valid FrontCategoryTreeRequestQuery query);

    /**
     * sku详情
     *
     * @param query
     * @return
     */
    ShelvesItemSkuDetailResponseVO getShelvesItemSkuDetail(@Valid CommodityCoreRequestQuery query)throws Exception;

    /**
    * @Description: 查询商品spu列表.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/24
    */
    PageBean<CommodityTypeResponseVO> findCommodityItemList(CommodityCoreQuery query) throws Exception;
}
