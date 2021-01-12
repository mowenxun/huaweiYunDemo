package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.clientiam.model.GetUserResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.system.mall.domain.dto.User.*;
import com.deepexi.dd.system.mall.domain.query.user.UserQuery;
import com.deepexi.dd.system.mall.service.app.UserService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api/v1/app/users")
@Api("用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @GetMapping("/current_user_info/{userId}")
    @ApiOperation("获取当前用户个人信息")
    public Payload<CurrentUserInfoResponseDTO> currentUserInfo(@PathVariable("userId") Long userId){
        GetUserResultVO userResultVO = userService.currentUserInfo(userId);
        if(userResultVO == null){
            return new Payload<>();
        }
        return new Payload<>(userResultVO.clone(CurrentUserInfoResponseDTO.class));
    }

    @PutMapping
    @ApiOperation("更新当前用户个人信息")
    public Payload<Boolean> updateCurrentUserInfo(@RequestBody CurrentUserInfoUpdateAdminRequestDTO dto){
        UserInfoUpdateDTO userInfo = dto.clone(UserInfoUpdateDTO.class);
        userInfo.setId(appRuntimeEnv.getUserId());
        return new Payload<>(userService.update(userInfo));
    }

    @GetMapping("/user_info_include_org/{userId}")
    @ApiOperation("获取首页我的个人信息，包含企业,资金")
    public Payload<CurrentUserInfoIncludeOrgResponseDTO> currentUserInfoIncludeOrg(@PathVariable("userId") Long userId) throws Exception {
        return new Payload<>(userService.currentUserInfoIncludeOrg(userId));
    }

    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    public Payload<Boolean> updateUserPassword(@RequestBody UserPasswordUpdateDTO dto){
        return new Payload<>(userService.updateUserPassword(dto));
    }

    @PostMapping("/updatePwd")
    @ApiOperation("修改密码-加密")
    public Payload<Boolean> updatePwd(@RequestBody UserPasswordUpdateDTO dto) throws Exception {
        return new Payload<>(userService.updatePwd(dto));
    }

    @PostMapping("/register")
    @ApiOperation("注册供应商")
    public Payload<UserRegisterResultDTO> register(@RequestBody UserRegisterDTO dto) throws Exception {
        return new Payload<>(userService.register(dto));
    }

    @GetMapping("/getGysUserPage")
    @ApiOperation("获取供应商列表")
    public Payload<PageBean<GysUserDTO>> getGysUserPage(UserQuery query) throws Exception {
        return new Payload<>(userService.getGysUserPage(query));
    }

}
