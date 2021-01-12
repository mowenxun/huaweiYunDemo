package com.deepexi.dd.system.mall.util;

import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author zhoujiawen
 * @date 2020-06-23 16:40
 */
public class GeneralConvertUtils {
    private static final DozerBeanMapper dozerMapper = new DozerBeanMapper();

    /**
     * 对象通用转换
     *
     * @param source           源对象
     * @param destinationClass 目标类
     * @param <T>
     * @return 返回得到destinationClass
     */
    public static <T> T conv(Object source, Class<T> destinationClass) {
        if(null == source){
            return null;
        }
        T convObj = dozerMapper.map(source, destinationClass);
        return convObj;
    }

    /**
     * 集合转换
     *
     * @param sourceList       源集合
     * @param destinationClass 目标类
     * @param <T>
     * @return 返回得到destinationClass的集合结果
     */
    public static <T> List<T> convert2List(List<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        if (CommonUtils.isEmpty(sourceList)) {
            return destinationList;
        }
        sourceList.forEach(source -> {
            destinationList.add(GeneralConvertUtils.conv(source, destinationClass));
        });
        return destinationList;
    }

    public static <T,M> PageBean<T> convert2PageBean(M source, Class<T> destinationClass) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, IOException {
        PageBean result = dozerMapper.map(source, PageBean.class);
        List<T> content = convert2List(result.getContent(), destinationClass);
        result.setContent(content);
        return result;
    }

    public static <T> PageBean<T> converterPageBean(Object source, Class<?> pageBeanClass, Class<T> contentClass) throws Exception {
        PageBean pageBean = conv(source, PageBean.class);
        List<T> results = convert2List(pageBean.getContent(), contentClass);
        pageBean.setContent(results);
        return convert2PageBean(pageBean, contentClass);
    }
}
