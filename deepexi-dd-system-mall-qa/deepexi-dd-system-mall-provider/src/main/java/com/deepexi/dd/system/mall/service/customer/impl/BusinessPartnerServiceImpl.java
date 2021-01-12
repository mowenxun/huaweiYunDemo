package com.deepexi.dd.system.mall.service.customer.impl;

import com.deepexi.dd.domain.tool.domain.dto.ToolEnterpriseTypeNameRequestDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolEnterpriseTypeNameResponseDTO;
import com.deepexi.dd.system.mall.domain.query.customer.BusinessPartnerAdminRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.customer.BusinessPartnerResponseVO;
import com.deepexi.dd.system.mall.remote.customer.BusinessPartnerClient;
import com.deepexi.dd.system.mall.remote.tool.ToolEnterpriseTypeClient;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import com.deepexi.dd.system.mall.service.customer.BusinessPartnerService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.BusinessPartnerResponseDTO;
import domain.query.BusinessPartnerRequestQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName BusinessPartnerServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-14
 * @Version 1.0
 **/
@Service
@Slf4j
public class BusinessPartnerServiceImpl extends BaseService implements BusinessPartnerService {

    @Autowired
    private BusinessPartnerClient partnerClient;

    @Autowired
    private ToolEnterpriseTypeClient toolEnterpriseTypeClient;

    @Override
    public BusinessPartnerResponseVO getTopLevelPartner(BusinessPartnerAdminRequestQuery query) throws Exception {
        Payload<BusinessPartnerResponseDTO> payload = partnerClient.getTopLevelPartner(query.clone(BusinessPartnerRequestQuery.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.conv(payload.getPayload(), BusinessPartnerResponseVO.class);
    }

    /**
     * @Description: 获取业务伙伴信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/17
     */
    @Override
    public BusinessPartnerResponseVO getPartner(BusinessPartnerAdminRequestQuery partnerQuery) throws Exception {
        partnerQuery.setOrgId(appRuntimeEnv.getTopOrganization().getId());
        Payload<BusinessPartnerResponseDTO> payload = partnerClient.getPartner(partnerQuery.clone(BusinessPartnerRequestQuery.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        BusinessPartnerResponseVO result = GeneralConvertUtils.conv(payload.getPayload(), BusinessPartnerResponseVO.class);
        // 获取企业类型
        result.setCompanyTypeName(getCompanyName(partnerQuery.getAppId(), partnerQuery.getTenantId(), result.getCompanyTypeId()));
        return result;
    }

    private String getCompanyName(Long appId, String tenantId, Long companyTypeId) throws Exception {
        ToolEnterpriseTypeNameRequestDTO requestDTO = new ToolEnterpriseTypeNameRequestDTO();
        requestDTO.setIds(Arrays.asList(companyTypeId));
        requestDTO.setTenantId(tenantId);
        requestDTO.setAppId(appId);
        Payload<List<ToolEnterpriseTypeNameResponseDTO>> enterpriseTypePayload = toolEnterpriseTypeClient.getToolEnterpriseTypeByIds(requestDTO);
        List<ToolEnterpriseTypeNameResponseDTO> enterpriseTypeNameList = GeneralConvertUtils.convert2List(enterpriseTypePayload.getPayload(), ToolEnterpriseTypeNameResponseDTO.class);
        if(CollectionUtil.isNotEmpty(enterpriseTypeNameList)){
            return enterpriseTypeNameList.get(0).getEnterpriseTypeName();
        }
        return null;
    }

    /**
     * @Description: 查询业务伙伴列表.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/8/18
     */
    @Override
    public List<BusinessPartnerResponseVO> findList(BusinessPartnerAdminRequestQuery partnerQuery) throws Exception {
        Payload<List<BusinessPartnerResponseDTO>> payload = partnerClient.findList(partnerQuery.clone(BusinessPartnerRequestQuery.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), BusinessPartnerResponseVO.class);
    }

    /**
     * @Description: 查询所有的业务伙伴.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/14
     */
    @Override
    public List<BusinessPartnerResponseVO> findALL(BusinessPartnerAdminRequestQuery partnerQuery) throws Exception {
        Payload<List<BusinessPartnerResponseDTO>> payload = partnerClient.findAll(partnerQuery.clone(BusinessPartnerRequestQuery.class));
        if (!"0".equals(payload.getCode())) {
            throw new ApplicationException(payload.getMsg());
        }
        return GeneralConvertUtils.convert2List(payload.getPayload(), BusinessPartnerResponseVO.class);
    }
}
