package com.deepexi.dd.system.mall.util;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName Utils
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-16
 * @Version 1.0
 **/
public class Utils {

    /**
     * 列表不为空.
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

    /**
     * 列表不为空.
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 校验Map为空.
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (null == map || map.size() == 0);
    }

    /**
     * 校验Map不为空.
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return (null != map && map.size() > 0);
    }

    /**
     * 判断字符串中是否有空.
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return (null == value ||value.length() == 0 );
    }

    /**
     * 判断字符串不能为空.
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return (null != value && value.length() > 0);
    }

    /**
     * @Description: 获取指定层级的组织id.
     * @Param:
     * @return:
     * @Author: JiangHaoWei
     * @Date: 2020/7/6
     */
    public static Long getOrgByLevel(String orgIdPath, int level) {
        if (level == 0 || isEmpty(orgIdPath) || orgIdPath.indexOf("/") == -1) {
            return null;
        }
        String[] args = orgIdPath.split("/");
        if ((level - 1) >= args.length) {
            return null;
        }
        return Arrays.asList(args).stream().filter(Utils::isNotEmpty).map(Long::parseLong).collect(Collectors.toList()).get((level - 1));
    }

    public static <T> T findFist(List<T> datas) {
        if (isNotEmpty(datas)) {
            return datas.get(0);
        }
        return null;
    }
}
