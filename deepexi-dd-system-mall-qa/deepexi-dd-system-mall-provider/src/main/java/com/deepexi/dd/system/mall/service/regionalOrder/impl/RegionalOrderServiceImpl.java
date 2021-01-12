package com.deepexi.dd.system.mall.service.regionalOrder.impl;

import com.deepexi.dd.system.mall.domain.query.regionalOrder.FrontCategoryPropertyQuery;
import com.deepexi.dd.system.mall.domain.vo.regionalOrder.FrontCategoryPropertyResponseVO;
import com.deepexi.dd.system.mall.service.regionalOrder.RegionalOrderService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.sdk.commodity.api.CategoryFrontOpenApi;
import com.deepexi.sdk.commodity.model.ListFrontCategoryPropertyRelationShipRequestDTO;
import com.deepexi.sdk.commodity.model.PayloadListListFrontCategoryPropertyRelationShipResponseDTO;
import com.deepexi.util.extension.ApplicationException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RegionalOrderServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Service
public class RegionalOrderServiceImpl implements RegionalOrderService {

    @Autowired
    private CategoryFrontOpenApi categoryFrontOpenApi;

    /**
     * @Description: 通过类目查询类目下面的属性.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/18
     */
    @Override
    public List<FrontCategoryPropertyResponseVO> findCategoryProperty(FrontCategoryPropertyQuery propertyQuery) throws Exception {
        PayloadListListFrontCategoryPropertyRelationShipResponseDTO payload = categoryFrontOpenApi.listFrontCategoryProperty(getCategoryProperyBody(propertyQuery));
        if (!"0".equals(payload.getCode())) {
            return Lists.newArrayList();
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), FrontCategoryPropertyResponseVO.class);
    }

    /**
    * @Description: 获取查询类目属性的条件.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/18
    */
    private ListFrontCategoryPropertyRelationShipRequestDTO getCategoryProperyBody(FrontCategoryPropertyQuery propertyQuery) {
        ListFrontCategoryPropertyRelationShipRequestDTO relationShipRequestDTO = propertyQuery.clone(ListFrontCategoryPropertyRelationShipRequestDTO.class);
        relationShipRequestDTO.setIdList(propertyQuery.getCategoryIds());
        return relationShipRequestDTO;
    }
}
