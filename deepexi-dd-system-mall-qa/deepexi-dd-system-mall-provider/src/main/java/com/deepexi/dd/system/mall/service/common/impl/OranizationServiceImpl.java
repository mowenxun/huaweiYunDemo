package com.deepexi.dd.system.mall.service.common.impl;

import com.deepexi.clientiam.api.GroupControllerApi;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.clientiam.model.PayloadPageBeanGroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuAuthorizeOrgResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolSkuRequestDTO;
import com.deepexi.dd.system.mall.domain.query.common.OrganizationRequestQuery;
import com.deepexi.dd.system.mall.domain.vo.tool.ToolSkuAuthorizeOrgResponseVO;
import com.deepexi.dd.system.mall.remote.commodity.ToolCommoditytypeAuthorizeApiRemote;
import com.deepexi.dd.system.mall.service.common.OranizationService;
import com.deepexi.util.config.Payload;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName SuppliersServiceImpl
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-09-17
 * @Version 1.0
 **/
@Service
public class OranizationServiceImpl implements OranizationService {

    @Autowired
    private ToolCommoditytypeAuthorizeApiRemote apiRemote;

    @Autowired
    private GroupControllerApi groupControllerApi;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;


    /**
     * @Description: 通过sku查询上游的组织信息.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/9/17
     */
    @Override
    public List<ToolSkuAuthorizeOrgResponseVO> findUpstreamSuppliersBySku(OrganizationRequestQuery requestQuery) throws Exception {
        Payload<List<ToolSkuAuthorizeOrgResponseDTO>> payload = apiRemote.getAuthorizeOrgBySkuId(converterToolSkuBody(requestQuery));
        if (CommonUtils.isEmpty(payload.getPayload())) {
            return Lists.newArrayList();
        }
        List<ToolSkuAuthorizeOrgResponseDTO> authorizeList = GeneralConvertUtils.convert2List(payload.getPayload(), ToolSkuAuthorizeOrgResponseDTO.class);
        List<Long> orgIds = authorizeList.stream().filter(s -> Objects.nonNull(s.getOrgId())).map(s -> s.getOrgId()).distinct().collect(Collectors.toList());
        orgIds.add(requestQuery.getOrgId());
        // 批量查询组织名称.
        PayloadPageBeanGroupResultVO groupResultVO = groupControllerApi.getGroupList(requestQuery.getTenantId(), appRuntimeEnv.getUserId(), appRuntimeEnv.getUsername(), null, null, null, null, null,null, null, null, null,orgIds,  null, null, null,null, orgIds.size());
        if (Objects.nonNull(groupResultVO.getPayload()) && CommonUtils.isNotEmpty(groupResultVO.getPayload().getContent())) {
            Map<Long, String> map = groupResultVO.getPayload().getContent().stream().collect(Collectors.toMap(GroupResultVO::getId, GroupResultVO::getName));
            return authorizeList.stream().map(s -> {
                ToolSkuAuthorizeOrgResponseVO responseVO = new ToolSkuAuthorizeOrgResponseVO();
                responseVO.setOrgId(requestQuery.getOrgId());
                responseVO.setOrgName(map.get(requestQuery.getOrgId()));
                responseVO.setFirstOrgId(s.getOrgId());
                responseVO.setFirstOrgName(map.get(s.getOrgId()));
                responseVO.setSkuId(s.getSkuId());
                return responseVO;
            }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    private ToolSkuRequestDTO converterToolSkuBody(OrganizationRequestQuery requestQuery) {
        ToolSkuRequestDTO toolSkuRequestDTO = new ToolSkuRequestDTO();
        toolSkuRequestDTO.setAppId(requestQuery.getAppId());
        toolSkuRequestDTO.setTenantId(requestQuery.getTenantId());
        toolSkuRequestDTO.setOrgId(requestQuery.getOrgId());
        toolSkuRequestDTO.setSkuIds(StringUtils.join(requestQuery.getSkuIds(), ","));
        return toolSkuRequestDTO;
    }
}
