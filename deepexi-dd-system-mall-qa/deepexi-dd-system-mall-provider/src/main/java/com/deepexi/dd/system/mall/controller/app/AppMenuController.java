package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.clientiam.model.GetMenuGroupResultVO;
import com.deepexi.clientiam.model.GetUserResultVO;
import com.deepexi.clientiam.model.MenuTreeVO;
import com.deepexi.clientiam.model.PayloadListMenuTreeVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.system.mall.domain.dto.User.*;
import com.deepexi.dd.system.mall.domain.dto.menu.AppMenuRequestDTO;
import com.deepexi.dd.system.mall.service.app.AppMenuService;
import com.deepexi.dd.system.mall.service.app.UserService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin-api/v1/app/menu")
@Api("app中台菜单管理")
public class AppMenuController {

    @Autowired
    private AppMenuService appMenuService;

    @PostMapping("/getMenuByAppId")
    @ApiOperation("获取APP中台的菜单权限")
    public Payload<List<MenuTreeVO>> getMenuByAppId(@Valid @RequestBody AppMenuRequestDTO dto){
        return new Payload<>(appMenuService.getMenuByAppId(dto).getPayload());
    }

}
