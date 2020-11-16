package com.deepexi.dd.domain.transaction.aop;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.common.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 日志统一打印切面
 *
 * @author mumu
 */
@Component
@Aspect
@Slf4j
public class LogApiAspect {

    private static final List<String> filters = Arrays.asList("exportSaleOrder");


    @Pointcut("execution (* com.deepexi.dd.domain.transaction.controller.*.*(..))")
    public void apiLogAop() {
    }

    @Around("apiLogAop()")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        if (CommonUtils.isNotEmpty(point.getSignature().getName()) && filters.contains(point.getSignature().getName())) {
            log.info("日志统一打印 ↓ ↓ ↓ ↓ ↓ ↓ {}.{}() start ↓ ↓ ↓ ↓ ↓ ↓,参数:\n{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), argsToString(point.getArgs()));
        } else {
            log.info("日志统一打印 ↓ ↓ ↓ ↓ ↓ ↓ {}.{}() start ↓ ↓ ↓ ↓ ↓ ↓,参数:\n{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), argsToString(point.getArgs()));
        }

        DateTime startTime = new DateTime();
        DateTime endTime = null;
        Interval interval = null;
        Object response = null;

        try {
            //执行该方法
            response = point.proceed();
        } catch (Exception e) {
            log.error("", e);
            endTime = new DateTime();
            interval = new Interval(startTime, endTime);
            log.info("日志统一打印 ↑ ↑ ↑ ↑ ↑ ↑ {}.{}() end ↑ ↑ ↑ ↑ ↑ ↑,响应时间:{}毫秒,响应内容:", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), interval.toDurationMillis());
            throw e;
        }
        endTime = new DateTime();
        interval = new Interval(startTime, endTime);
        String responseArgs = argsToString(response);
        log.info("日志统一打印 ↑ ↑ ↑ ↑ ↑ ↑ {}.{}() end ↑ ↑ ↑ ↑ ↑ ↑,响应时间:{}毫秒,响应内容:\n{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), interval.toDurationMillis(), responseArgs);
        return response;
    }

    private String argsToString(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            log.error("", e);
        }
        return String.valueOf(object);
    }

    private String argsToString(Object[] object) {
        try {
            if (object.length == 2) {
                return JSON.toJSONString(object[1]);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return String.valueOf(object);
    }


}
