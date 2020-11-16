package com.deepexi.dd.domain.transaction.aop;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import com.deepexi.util.ArrayUtils;
import com.deepexi.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


//@Component
//@Aspect
//@Slf4j
public class TenantAspect {
    private static final String TENTANT_ID="tenantId";
    private static final String APPID="appId";
    @Value("${appId}")
    private Long appId;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Pointcut("execution (* com.deepexi.dd.domain.transaction.service.impl.*.*(..))")
    public void tenantAop() {
    }

    /**
     * 前置通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Before("tenantAop()")
    public void setTentant(JoinPoint point) throws Throwable {
       Object[] args= point.getArgs();
       if(!ArrayUtils.isEmpty(args)){
           for(Object param:args){
                //设置属性值
               setFieldTentanId(param);
           }
       }

    }

    private void setFieldTentanId(Object args) throws InvocationTargetException, IllegalAccessException {
        if(args == null){
            return;
        }
        Map<String,Method> methodMap=getMethod(args.getClass());
        Map<String,Field> fieldMap=getAllFiedls(args.getClass());
        //获取所有List字段信息
        List<Field>fields= getAllListFiedls(args.getClass());
        List<Object> tenantDtos= getTentantFields(args);
        //设置对象内 所有TenanDTO类型字段的TenanId
        if(CollectionUtil.isNotEmpty(tenantDtos)){
            for(Object obj:tenantDtos){
                setFieldTentanId(obj);
            }
        }
        //设置所有集合对象的值
        for(Field field:fields){
                String methodName="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
               Method method= methodMap.get(methodName);
              List fieldValues= (List) method.invoke(args,new Object[]{});
              if(CollectionUtil.isNotEmpty(fieldValues)){
                  for(int i=0;i<fieldValues.size();i++){
                      setFieldTentanId(fieldValues.get(i));
                  }
              }
        }
        if(fieldMap.get(TENTANT_ID)!=null){
            Method setTenantIdMethod=   methodMap.get("setTenantId");
            setTenantIdMethod.invoke(args,appRuntimeEnv.getTenantId());
        }
        if(fieldMap.get(APPID)!=null){
            Method setAppIdMethod=   methodMap.get("setAppId");
            Method getAppIdMethod=   methodMap.get("getAppId");
            if(getAppIdMethod.invoke(args)==null){
                setAppIdMethod.invoke(args,appId);
            }

        }
    }
    private Map<String,Method> getMethod(Class cls){
        Class classz=cls;
        Map<String,Method> methodMap=new HashedMap();
        while(classz!=null && !classz.getName().toLowerCase().equals("java.lang.object")){
            Method[] methods= classz.getDeclaredMethods();
            for(Method method:methods){
                methodMap.put(method.getName(),method);
            }
            if(classz.getName().equals(TenantDTO.class.getName())){
                classz=null;
                break;
            }else {
                classz = classz.getSuperclass();
            }
        }

        return methodMap;
    }

    /**
     * 获取所有字段包括父类
     * @param cls
     * @return
     */
    private Map<String,Field> getAllFiedls(Class cls){
        Class classz=cls;
        Map<String,Field> fieldMap=new HashedMap();
        while(classz!=null && !classz.getName().toLowerCase().equals("java.lang.object")){
            Field[] fields= classz.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                fieldMap.put(field.getName(),field);
            }
            if(classz.getName().equals(TenantDTO.class.getName())){
                classz=null;
                break;
            }else {
                classz = classz.getSuperclass();
            }
        }

        return fieldMap;
    }

    /**
     * 获取所有List集合字段
     * @param cls
     * @return
     */
    private List<Field> getAllListFiedls(Class cls){
        Class classz=cls;
        List<Field> fieldList=new ArrayList<>();
        while(classz!=null && !classz.getName().toLowerCase().equals("java.lang.object")){
            Field[] fields= classz.getDeclaredFields();
            for(Field field:fields){
                if(field.getType().getName().equals("java.util.List")) {
                    field.setAccessible(true);
                    fieldList.add(field);
                }
            }
            if(classz.getName().equals(TenantDTO.class.getName())){
                classz=null;
                break;
            }else {
                classz = classz.getSuperclass();
            }
        }

        return fieldList;
    }

    /**
     * 获取当前对象下属性是TentantDTO的属性
     * @param args
     * @return
     */
    private List<Object> getTentantFields(Object args) throws InvocationTargetException, IllegalAccessException {
        Map<String,Field> feldList=getAllFiedls(args.getClass());
        Map<String,Method> methodMap= getMethod(args.getClass());
        List<Object> list=new ArrayList<>();
        for(Map.Entry<String,Field> entry:feldList.entrySet()){
            Field field=entry.getValue();
            //如果是TenantDTO 子类
            if(!"java.util.List".equals(field.getType().getName()) && TenantDTO.class.isAssignableFrom(field.getType())){
                String methodName="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
               Method method= methodMap.get(methodName);
               if(method!=null){
                  list.add( method.invoke(args));
               }
            }
        }
        return list;
    }

}
