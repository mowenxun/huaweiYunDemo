package com.deepexi.dd.system.mall.service.app;

import com.deepexi.clientiam.model.GetUserResultVO;
import com.deepexi.dd.system.mall.domain.dto.User.CurrentUserInfoIncludeOrgResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.User.GysUserDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserInfoUpdateDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserPasswordUpdateDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserRegisterDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserRegisterResultDTO;
import com.deepexi.dd.system.mall.domain.query.user.UserQuery;
import com.deepexi.util.pageHelper.PageBean;

public interface UserService {

    /**
     * 查询当前用户个人信息
     * @param
     * @return
     */
    GetUserResultVO currentUserInfo(Long userId);

    /**
     * 修改个人信息
     * @param dto 个人信息
     * @return
     */
    Boolean update(UserInfoUpdateDTO dto);

    /**
     * 获取当前用户信息包含企业机构信息
     * @return
     */
    CurrentUserInfoIncludeOrgResponseDTO currentUserInfoIncludeOrg(Long userId) throws Exception;

    /**
     * 修改密码
     * @param dto
     * @return
     */
    Boolean updateUserPassword(UserPasswordUpdateDTO dto);

    /**
    * @Description: 修改密码-加密.
    * @Param:
    * @return: 
    * @Author: JiangHaoWei
    * @Date: 2020/9/23
    */
    Boolean updatePwd(UserPasswordUpdateDTO updateDTO) throws Exception;

    /**
     * 注册供应商
     * @param dto
     * @return
     */
    UserRegisterResultDTO register(UserRegisterDTO dto) throws Exception;

    /**
     * 分页获取供应商
     * @param query
     * @return
     */
    PageBean<GysUserDTO> getGysUserPage(UserQuery query) throws Exception;
}
