package com.deepexi.dd.domain.transaction.annotation;

import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;

import java.lang.annotation.*;

/**
 * @ClassName : ActionCodeAnnotation
 * @Description : 环节编码注解
 * @Author : yuanzaishun
 * @Date: 2020-08-05 10:32
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionCodeAnnotation {
    ListTypeEnum listType() ;
    String code() ;
}
