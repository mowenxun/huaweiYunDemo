package com.deepexi.dd.system.mall.service.regionalOrder;

import com.deepexi.dd.system.mall.domain.query.regionalOrder.FrontCategoryPropertyQuery;
import com.deepexi.dd.system.mall.domain.vo.regionalOrder.FrontCategoryPropertyResponseVO;

import java.util.List;

/**
 * @ClassName RegionalOrderService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
public interface RegionalOrderService {

    /**
    * @Description: 通过类目查询类目下面的属性.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/18
    */
    List<FrontCategoryPropertyResponseVO> findCategoryProperty(FrontCategoryPropertyQuery propertyQuery) throws Exception;
}
