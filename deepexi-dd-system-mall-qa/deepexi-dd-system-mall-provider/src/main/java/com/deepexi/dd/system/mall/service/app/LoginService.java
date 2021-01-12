package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.system.mall.domain.dto.User.LoginAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.User.LoginResponseDTO;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface LoginService {

    /**
     * 登录
     * @param dto
     * @return
     */
    LoginResponseDTO login(LoginAdminRequestDTO dto) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, IOException, Exception;

    /**
     * 登录
     * @param dto
     * @return
     */
    LoginResponseDTO logining(LoginAdminRequestDTO dto) throws Exception;
}
