package com.deepexi.dd.domain.transaction.config;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.sdk.core.JSON;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author cpb
 * @version 1.0
 * @date 2019/9/29 13:53
 */
@Component
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {
  @Autowired
  AppRuntimeEnv appRuntimeEnv;
   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        appRuntimeEnv.setToken(request.getHeader("Authorization"));
        String method = request.getMethod();
        if (null == method) {
            throw new ApplicationException("没有请求方法类型");
        }
        //get请求校验tenantId
        if ("GET".equals(method)) {
            String tenantId = getParam(request, "tenantId");
            String appId = getParam(request, "appId");
            String requestURI = request.getRequestURI();
            log.info(requestURI + ":tenantId:{},appId{}", tenantId,appId);
            if(StringUtil.isNotEmpty(appId)){
                appRuntimeEnv.setAppId(Long.valueOf(appId));
            }
        }else if("POST".equals(method)||"PUT".equals(method)){
//         Map<String,String> param=  JsonUtil.json2Map(getPostByTextPlain(request)) ;
//            if(param.get("appId")!=null){
//                appRuntimeEnv.setAppId(Long.valueOf(param.get("appId")));
//            }
        }
        //post请求校验tenantId

        //put请求校验tenantId

        //delete请求校验tenantId

        return true;
    }

    /**
     * 获取业务参数
     *
     * @param request
     * @param param
     * @throws Exception
     */
    private String getParam(HttpServletRequest request, String param) {
        String[] reqParam = request.getParameterValues(param);
        return (reqParam == null || reqParam.length < 1 ? null : reqParam[0]);
    }

    public static String getPostByTextPlain(HttpServletRequest request) throws IOException {

        BufferedReader reader = request.getReader();
        char[] buf = new char[512];
        int len = 0;
        StringBuffer contentBuffer = new StringBuffer();
        while ((len = reader.read(buf)) != -1) {
            contentBuffer.append(buf, 0, len);
        }
        String content= contentBuffer.toString();
        return content;
    }
}
