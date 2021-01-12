package com.deepexi.dd.system.mall.util;

public class ConverterUtil {

    public static int toInt(Object value){
        return toInt(value,0);
    }

    /**
     * 把object类型转化成整型，当object为null的情况下返回默认值
     * @param value object对象
     * @param defaultValue 默认值
     */
    public static int toInt(Object value,int defaultValue){

        int newValue=defaultValue;

        if(value == null || value.toString().length() == 0){
            return newValue;
        }

        String sValue=value.toString();

        try {
            newValue=Integer.parseInt(sValue);
        }
        catch(Exception e){
            newValue=defaultValue;
        }

        return newValue;
    }

}
