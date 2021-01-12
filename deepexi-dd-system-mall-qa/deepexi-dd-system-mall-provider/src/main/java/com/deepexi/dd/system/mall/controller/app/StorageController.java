package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.clientiam.model.GetUserResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.system.mall.domain.dto.User.*;
import com.deepexi.dd.system.mall.service.app.UserService;
import com.deepexi.sdk.storage.api.DepotSdkApi;
import com.deepexi.sdk.storage.model.DepotFindPagePostByTypeRequestDTO;
import com.deepexi.sdk.storage.model.PayloadDepotFindPagePostResponseDTO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api/v1/app/stock/depot")
@Api("用户管理")
public class StorageController {

    @Autowired
    private DepotSdkApi depotSdkApi;

    @GetMapping("/findPageByType")
    @ApiOperation("获取仓库列表")
    public Payload<PayloadDepotFindPagePostResponseDTO> currentUserInfo(DepotFindPagePostByTypeRequestDTO dto){
        return new Payload<>(depotSdkApi.findDepotPageByType(dto));
    }
}
