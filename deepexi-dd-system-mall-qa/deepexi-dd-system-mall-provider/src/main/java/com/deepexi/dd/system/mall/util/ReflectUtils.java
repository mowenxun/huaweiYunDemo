package com.deepexi.dd.system.mall.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author zhoujiawen
 * @date 2020-04-03 19:11
 */
@Slf4j
public class ReflectUtils {

    public static Object getFieldValueByName(String fieldName, Object object) {
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e){
            try {
                field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
                log.warn("不存在参数名:{}",fieldName);
            }
        } finally {
            if (field == null) {
                return null;
            }
            field.setAccessible(true);
            try {
                return field.get(object);
            } catch (IllegalAccessException e) {
                log.warn("获取参数值错误：{}",e);
            }
        }
        return null;
    }

    public static void setFieldValueByName(String fieldName, Object value, Object object) {
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e){
            try {
                field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
                log.warn("不存在参数名:{}",fieldName);
            }
        } finally {
            if(field == null){
                return ;
            }
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                log.warn("获取参数值错误：{}",e);
            }
        }
    }
}
