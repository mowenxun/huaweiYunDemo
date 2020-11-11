package com.deepexi.dd.middle.order.util;

import com.deepexi.util.constant.BaseEnumType;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cpb
 * @version 1.0
 * @date 2019/11/5 11:51
 */
@Slf4j
public class ExceptionUtil {
    public static void checkException(Object param, BaseEnumType baseEnumType) {
        if (null == param) {
            log.info("校验参数为空");
            throw new ApplicationException(baseEnumType);
        }
    }
}
