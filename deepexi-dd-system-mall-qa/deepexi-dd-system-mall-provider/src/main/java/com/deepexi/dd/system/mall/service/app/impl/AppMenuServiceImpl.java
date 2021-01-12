package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.clientiam.api.ApplicationControllerApi;
import com.deepexi.clientiam.api.MenuGroupControllerApi;
import com.deepexi.clientiam.model.GetMenuGroupResultVO;
import com.deepexi.clientiam.model.PayloadListGetMenuGroupResultVO;
import com.deepexi.clientiam.model.PayloadListMenuTreeVO;
import com.deepexi.dd.system.mall.domain.dto.menu.AppMenuRequestDTO;
import com.deepexi.dd.system.mall.service.app.AppMenuService;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class AppMenuServiceImpl extends BaseService implements AppMenuService {

    @Autowired
    private MenuGroupControllerApi menuGroupControllerApi;

    @Autowired
    private ApplicationControllerApi applicationControllerApi;

    @Override
    public PayloadListMenuTreeVO getMenuByAppId(AppMenuRequestDTO appMenuRequestDTO) {

        PayloadListGetMenuGroupResultVO payloadListGetMenuGroupResultVO = menuGroupControllerApi.getMenuGroupByAppId(appMenuRequestDTO.getAppId(), appMenuRequestDTO.getTenantId(), appMenuRequestDTO.getUserId(), appMenuRequestDTO.getUserName(), appMenuRequestDTO.getAppCode());
        if (
            Objects.isNull(payloadListGetMenuGroupResultVO) ||
            Objects.isNull(payloadListGetMenuGroupResultVO.getPayload()) ||
            payloadListGetMenuGroupResultVO.getPayload().size()<=0
        ){
            return new PayloadListMenuTreeVO();
        }

        String groupCode = null;
        for(GetMenuGroupResultVO vo:payloadListGetMenuGroupResultVO.getPayload()){
            if("app_console".equals(vo.getCode())){
                groupCode = vo.getCode();
                break;
            }
        }
        if(StringUtils.isBlank(groupCode)){
            return new PayloadListMenuTreeVO();
        }

        return applicationControllerApi.getMenuByAppId(appMenuRequestDTO.getAppId(), appMenuRequestDTO.getTenantId(), appMenuRequestDTO.getUserId(), appMenuRequestDTO.getUserName(), groupCode);
    }
}
