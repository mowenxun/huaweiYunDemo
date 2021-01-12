package com.deepexi.dd.system.mall.util;

import com.deepexi.dd.system.mall.enums.ResultEnum;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayloadUtils {

    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String SUCCESS_CODE = "0";

    public static<T> void assertSuccess(T payload){
        Object codeValue = ReflectUtils.getFieldValueByName(CODE, payload);
        if(!SUCCESS_CODE.equals(codeValue)){
            Object msgValue = ReflectUtils.getFieldValueByName(MSG, payload);
            log.error("远程接口错误：{}",payload);
            if(msgValue == null){
                throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
            }
            throw new ApplicationException(msgValue.toString());
        }
    }
}
