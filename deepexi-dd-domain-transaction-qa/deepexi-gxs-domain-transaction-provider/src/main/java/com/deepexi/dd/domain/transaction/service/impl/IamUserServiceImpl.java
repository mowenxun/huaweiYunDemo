package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.clientiam.api.GroupControllerApi;
import com.deepexi.clientiam.api.UserControllerApi;
import com.deepexi.clientiam.model.*;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.service.IamUserService;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName : IAMUserService
 * @Description : IAM用户信息service
 * @Author : yuanzaishun
 * @Date: 2020-07-08 18:05
 */
@Service
public class IamUserServiceImpl implements IamUserService {

    private static final String SUCCESS="0";
    @Autowired
    private UserControllerApi userControllerApi;

    @Autowired
    private GroupControllerApi groupControllerApi;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;
    @Override
    public GetUserResultVO getUserInfo() {
        PayloadGetUserResultVO payloadGetUserResultVO=userControllerApi.getUserById(appRuntimeEnv.getUserId(),appRuntimeEnv.getTenantId(),appRuntimeEnv.getUserId(),appRuntimeEnv.getUsername());
       return payloadGetUserResultVO.getPayload();
    }

    @Override
    public GetUserIdGroupResultVO getUserGroupInfo() {
        PayloadListGetUserIdGroupResultVO payloadListGetUserIdGroupResultVO = userControllerApi.getGroupsByUserId(appRuntimeEnv.getUserId()+"",appRuntimeEnv.getTenantId(),appRuntimeEnv.getUserId(),appRuntimeEnv.getUsername(),null,null,null,null,null);
        if(CollectionUtil.isNotEmpty(payloadListGetUserIdGroupResultVO.getPayload())){
            return payloadListGetUserIdGroupResultVO.getPayload().get(0);
        }
        return null;
    }

    @Override
    public GroupResultVO getGroup(Long groupId, String tenanId, Long userId, String userName) {
        PayloadGroupResultVO payloadGroupResultVO=  groupControllerApi.getGroup(groupId,tenanId,userId,userName);
        if(SUCCESS.equals(payloadGroupResultVO.getCode())){
            return payloadGroupResultVO.getPayload();
        }
        return null;
    }
}
