package com.deepexi.dd.system.mall.service.customer;

import com.deepexi.dd.system.mall.domain.query.customer.BusinessPartnerAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.BusinessPartnerResponseVO;

import java.util.List;

/**
 * @ClassName BusinessPartnerService
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-14
 * @Version 1.0
 **/
public interface BusinessPartnerService {

    BusinessPartnerResponseVO getTopLevelPartner(BusinessPartnerAdminRequestQuery partnerQuery) throws Exception;

    /**
    * @Description: 获取业务伙伴信息.
    * @Param:
    * @return:
    * @Author: JiangHaoWei
    * @Date: 2020/7/17
    */
    BusinessPartnerResponseVO getPartner(BusinessPartnerAdminRequestQuery partnerQuery) throws Exception;

    /**
    * @Description: 查询业务伙伴列表.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/8/18
    */
    List<BusinessPartnerResponseVO> findList(BusinessPartnerAdminRequestQuery partnerQuery) throws Exception;

    /**
    * @Description: 查询所有的业务伙伴.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/14
    */
    List<BusinessPartnerResponseVO> findALL(BusinessPartnerAdminRequestQuery partnerQuery) throws Exception;
}
