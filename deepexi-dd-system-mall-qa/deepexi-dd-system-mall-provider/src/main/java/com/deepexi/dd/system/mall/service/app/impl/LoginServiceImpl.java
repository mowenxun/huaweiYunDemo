package com.deepexi.dd.system.mall.service.app.impl;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.deepexi.clientiam.api.UserControllerApi;
import com.deepexi.clientiam.model.GetUserIdGroupResultVO;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.clientiam.model.PayloadGysUserVO;
import com.deepexi.clientiam.model.PayloadListGetUserIdGroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.BaseOrganizationService;
import com.deepexi.dd.system.mall.controller.app.UserController;
import com.deepexi.dd.system.mall.domain.dto.User.LoginAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.User.LoginRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.User.LoginResponseDTO;
import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.dd.system.mall.service.app.LoginService;
import com.deepexi.dd.system.mall.service.customer.BaseService;
import com.deepexi.dd.system.mall.util.PayloadUtils;
import com.deepexi.dd.system.mall.util.RSAUtil;
import com.deepexi.util.Base64;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${deepexi.iam.auth.url}")
    private String loginUrl;

    @Value("${deepexi.login.rsa.privateKey}")
    private String loginPrivateKey;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private BaseOrganizationService organizationService;

    @Autowired
    private UserControllerApi userControllerApi;

    public static final String GRANT_TYPE = "deepexi";

    public static final String SCOPE = "all";

    /**
     * 登录
     * @param dto
     * @return
     */
    @Override
    public LoginResponseDTO logining(LoginAdminRequestDTO dto) throws Exception {
        // 对密码解密.
        //dto.setPassword(Base64.decodeString(dto.getPassword()));

        try {
            byte[] decryptData = RSAUtil.decryptByPrivateKey(dto.getPassword(), loginPrivateKey);
            String newPassword = new String(decryptData);

            dto.setPassword(newPassword);
        }
        catch (Exception e){
            throw new Exception("不是有效的密码");
        }
        // 然后进行登录.
        return this.login(dto);
    }

    @Override
    public LoginResponseDTO login(LoginAdminRequestDTO dto) throws Exception{
        // 先获取当前登录用户所在组的管理员有没有被禁用，如：供应商被禁用，其下的员工都不能登录
        PayloadGysUserVO userPayload = userControllerApi.getAdminUser(dto.getEnterpriseCode(), dto.getUsername(), 0);
        if (userPayload == null) {
            throw new ApplicationException("系统异常，请联系管理员");
        }
        if (!"0".equals(userPayload.getCode())) {
            throw new ApplicationException(userPayload.getMsg());
        }
        if (userPayload.getPayload() == null) {
            throw new ApplicationException("管理员账号已被禁用!");
        }

        ResponseEntity<Payload> responseEntity = restTemplate.postForEntity(buildLoginUrl(dto), null, Payload.class);
        Payload body = responseEntity.getBody();
        PayloadUtils.assertSuccess(body);
        ObjectMapper objectMapper = new ObjectMapper();
        Object payload = body.getPayload();
        LoginResponseDTO loginResponseDTO = objectMapper.convertValue(payload, LoginResponseDTO.class);
        LoginResponseDTO.UserInfo userInfo = loginResponseDTO.getUserInfo();
        appRuntimeEnv.setTenantId(userInfo.getTenantId());
        appRuntimeEnv.setUserId(userInfo.getUserId());
        appRuntimeEnv.setUsername(userInfo.getUsername());
        appRuntimeEnv.setUserOrganization(organizationService.getOrganization(appRuntimeEnv));
        userInfo.setOrgId(getCurrenOrgId());
        return loginResponseDTO;

    }

    private String buildLoginUrl(LoginAdminRequestDTO dto) {
        String username = dto.getUsername() + "@" + dto.getEnterpriseCode();
        String url = loginUrl + "?grant_type="+GRANT_TYPE+"&username="+username+"&password="+dto.getPassword()+"&scope="+SCOPE;
        log.info("登录地址：{}",url);
        return url;
    }
}
