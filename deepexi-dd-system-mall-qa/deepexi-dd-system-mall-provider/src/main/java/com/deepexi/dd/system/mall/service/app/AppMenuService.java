package com.deepexi.dd.system.mall.service.app;

import com.deepexi.clientiam.model.GetUserResultVO;
import com.deepexi.clientiam.model.PayloadListGetMenuGroupResultVO;
import com.deepexi.clientiam.model.PayloadListMenuTreeVO;
import com.deepexi.dd.system.mall.domain.dto.User.CurrentUserInfoIncludeOrgResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserInfoUpdateDTO;
import com.deepexi.dd.system.mall.domain.dto.User.UserPasswordUpdateDTO;
import com.deepexi.dd.system.mall.domain.dto.menu.AppMenuRequestDTO;
import com.deepexi.sdk.core.Payload;

public interface AppMenuService {

    /**
     * 获取菜单树
     * @return
     */
    PayloadListMenuTreeVO getMenuByAppId(AppMenuRequestDTO appMenuRequestDTO);

}
