package com.deepexi.dd.domain.transaction.util;

import cn.hutool.core.util.StrUtil;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.AbstractObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huanghuai
 * @date 2020/10/14
 */
public final class ServiceUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    private ServiceUtil() {
    }

    public static <T> T deepClone(AbstractObject source, Class<T> targetType) {
        if (source == null) {
            return null;
        }
        return source.clone(targetType, 1);
    }

    /**
     * e.g:
     * <p>
     * List<User> users;
     * mapCollect(users,user->user.getId()) return List<Long> ids
     * </p>
     *
     * @param filterNull 是否需要过滤null 如果是true，list元素为空，以及经过apply后返回空都会过滤
     */
    private static <T, R> List<R> mapCollect(Collection<T> data, Function<? super T, ? extends R> mapper, boolean filterNull) {
        if (CollectionUtil.isEmpty(data) || mapper == null) {
            return new ArrayList<>(0);
        }
        Stream<? extends R> stream = data.stream().map(mapper);
        if (filterNull) {
            stream = stream.filter(Objects::nonNull);
        }
        return stream.collect(Collectors.toList());
    }

    /**
     * 替代 list.stream().map(e->e.getXXX()).collect(Collectors.toList()); <br>
     * <p>
     */
    public static <T, R> List<R> mapCollect(Collection<T> data, Function<? super T, ? extends R> mapFun) {
        return mapCollect(data, mapFun, true);
    }

    public static void setRequestInfos(AppRuntimeEnv appRuntimeEnv, HttpServletRequest request) {
        String username = request.getHeader("username");
        if (StringUtils.isEmpty(username)) {
            username = request.getParameter("username");
        }
        // String token = request.getHeader("token");
        // if (StringUtils.isEmpty(token)) {
        //     token = request.getParameter("token");
        // }
        String tenantId = request.getHeader("tenantId");
        if (StringUtils.isEmpty(tenantId)) {
            tenantId = request.getParameter("tenantId");
        }
        String requestId = request.getHeader("requestId");
        if (StringUtils.isEmpty(requestId)) {
            requestId = request.getParameter("requestId");
            if (StringUtils.isEmpty(requestId)) {
                requestId = UUID.randomUUID().toString().replace("-", "");
            }
        }

        if (StringUtils.isNotEmpty(username)) {
            appRuntimeEnv.setUsername(username);
        }
        // if (StringUtils.isNotEmpty(token)) {
        //     appRuntimeEnv.setToken(token);
        // }
        if (StringUtils.isNotEmpty(tenantId)) {
            appRuntimeEnv.setTenantId(tenantId);
        }
        if (StringUtils.isNotEmpty(requestId)) {
            appRuntimeEnv.setRequestId(requestId);
        }
    }

    public static <T> T getPayLoadList(Payload<T> rs) {
        try {
            if (rs != null) {
                return rs.getPayload();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    public static Object getPayLoad(Payload rs) {
        try {
            if (rs != null) {
                return rs.getPayload();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    public static void assertNull(Object obj, String s) {
        if (obj == null) {
            throw new IllegalArgumentException(s);
        }
    }

    public static void assertNull(Object obj, String msg, Object... args) {
        if (obj == null) {
            throw new IllegalArgumentException(StrUtil.format(msg, args));
        }
    }

    public static void assertTrue(boolean condition, String msg, Object... args) {
        if (condition) {
            throw new IllegalArgumentException(StrUtil.format(msg, args));
        }
    }

    /**
     * 如果str的长度<limit, 就在str之前填充fillStr,直到str长度<=limit
     */
    public static String preFill(String str, int limit, String fillStr) {
        int howManyFill = (limit - str.length()) / fillStr.length();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < howManyFill; j++) {
            sb.append(fillStr);
        }
        return sb.append(str).toString();
    }

    /**
     * 如果str的长度<limit, 就在str之后填充fillStr,直到str长度<=limit
     */
    public static String suffixFill(String str, int limit, String fillStr) {
        int howManyFill = (limit - str.length()) / fillStr.length();
        StringBuilder sb = new StringBuilder().append(str);
        for (int j = 0; j < howManyFill; j++) {
            sb.append(fillStr);
        }
        return sb.toString();
    }
}
