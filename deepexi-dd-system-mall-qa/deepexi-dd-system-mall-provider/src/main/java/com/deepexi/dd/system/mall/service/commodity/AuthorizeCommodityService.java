package com.deepexi.dd.system.mall.service.commodity;

import com.deepexi.dd.system.mall.domain.dto.commodity.CategoryCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityRecommendResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityResponseDTO;
import com.deepexi.dd.system.mall.domain.query.commodity.CategoryCommodityRecommendQuery;
import com.deepexi.dd.system.mall.domain.query.commodity.NewsCommodityAdminQuery;
import com.deepexi.dd.system.mall.domain.query.commodity.NewsCommodityRecommendQuery;

import java.util.List;

public interface AuthorizeCommodityService {

    /**
     * 新品推荐列表
     * @param query
     * @return
     */
    List<NewsCommodityResponseDTO> getNewsCommodity(NewsCommodityAdminQuery query) throws Exception;

    /**
     * 商城首页-查询新品推荐
     * @param query
     * @return
     */
    List<NewsCommodityRecommendResponseDTO> getNewsCommodityResponse(NewsCommodityRecommendQuery query) throws Exception;

    /**
     * 查询热门分类
     * @param query
     * @return
     */
    List<CategoryCommodityResponseDTO> getCategoryCommodityResponseDTO(CategoryCommodityRecommendQuery query) throws Exception;
}
