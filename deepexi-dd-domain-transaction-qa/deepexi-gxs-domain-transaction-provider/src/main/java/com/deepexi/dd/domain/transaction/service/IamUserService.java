package com.deepexi.dd.domain.transaction.service;

import com.deepexi.clientiam.model.GetUserIdGroupResultVO;
import com.deepexi.clientiam.model.GetUserResultVO;
import com.deepexi.clientiam.model.GroupResultVO;

/**
 * @ClassName : IAMUserService
 * @Description : IAM用户信息service
 * @Author : yuanzaishun
 * @Date: 2020-07-08 18:05
 */
public interface IamUserService
{
     /**
      * 获取当前登录用户信息
      * @return
      */
     GetUserResultVO getUserInfo();

     /**
      * 查询当前用户所属的组织
      * @return
      */
     GetUserIdGroupResultVO getUserGroupInfo();

     /**
      * 根据组织ID获取组织信息
      * @param groupId
      * @param tenanId
      * @param userId
      * @param userName
      * @return
      */
     GroupResultVO  getGroup(Long groupId, String tenanId, Long userId, String userName);


}
