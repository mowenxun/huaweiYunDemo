package com.deepexi.dd.system.mall.service.common;

import com.deepexi.dd.system.mall.domain.query.common.OrganizationRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.tool.ToolSkuAuthorizeOrgResponseVO;

import java.util.List;

/**
 * @ClassName SuppliersService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-09-17
 * @Version 1.0
 **/
public interface OranizationService {
    
    /**
    * @Description: 通过sku查询上游的组织信息.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/17
    */
    List<ToolSkuAuthorizeOrgResponseVO> findUpstreamSuppliersBySku(OrganizationRequestQuery requestQuery) throws Exception;
}
