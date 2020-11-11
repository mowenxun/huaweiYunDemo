package com.deepexi.dd.middle.order.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Utils
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-03
 * @Version 1.0
 **/
public class Utils {
    /**
     * @Description:  列表不为空校验.
     * @Param: [collection]
     * @return: boolean
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

    /**
     * @Description:  列表为空校验.
     * @Param: [collection]
     * @return: boolean
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * @Description:  校验Map为空.
     * @Param: [map]
     * @return: boolean
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (null == map || map.size() == 0);
    }

    /**
     * @Description: 校验Map不为空.
     * @Param: [map]
     * @return: boolean
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return (null != map && map.size() > 0);
    }

    /**
     * @Description: 校验字符串为空.
     * @Param: [map]
     * @return: boolean
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static boolean isEmpty(String value){
        return StringUtils.isBlank(value);
    }

    /**
     * @Description: 校验字符串不为空.
     * @Param: [map]
     * @return: boolean
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static boolean isNotEmpty(String value){
        return !isEmpty(value);
    }
    /**
     * @Description:  获取列表中的第一个值.
     * @Param: [list]
     * @return: T
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static <T> T selectOne(List<T> list) {
        if (isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * @Description:  去掉字符串中的空格.
     * @Param: [value]
     * @return: java.lang.String
     * @Author: SongTao
     * @Date: 2020/7/3
     */
    public static String trim(String value) {
        if (isEmpty(value)) {
            return value;
        }
        return value.trim();
    }
}
