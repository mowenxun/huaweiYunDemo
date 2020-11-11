package com.deepexi.dd.middle.order.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Component;

/**
 * 日志统一打印切面
 *
 * @author mumu
 */
@Component
@Aspect
@Slf4j
public class LogApiAspect {


    @Pointcut("execution (* com.deepexi.dd.middle.order.api..*.*(..))")
    public void apiLogAop() {
    }

    @Around("apiLogAop()")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        String requestArgs = argsToString(point.getArgs());
        log.info("日志统一打印 ↓ ↓ ↓ ↓ ↓ ↓ {}.{}() start ↓ ↓ ↓ ↓ ↓ ↓,参数:\n{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), requestArgs);
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


}
