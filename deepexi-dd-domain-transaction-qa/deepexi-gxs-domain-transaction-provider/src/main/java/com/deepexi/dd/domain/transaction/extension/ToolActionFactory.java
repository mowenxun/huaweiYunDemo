package com.deepexi.dd.domain.transaction.extension;

import com.deepexi.dd.domain.transaction.annotation.ActionCodeAnnotation;
import com.deepexi.dd.domain.transaction.manager.AbstractToolAction;
import com.deepexi.iam.security.autoconfiguration.spring.SpringContextUtils;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : ToolActionFactory
 * @Description : 获取链路环节工厂
 * @Author : yuanzaishun
 * @Date: 2020-08-05 12:22
 */
@Component
public class ToolActionFactory<T> {
    @Autowired
    private SpringApplicationContext springApplicationContext;
    private static Map<String,AbstractToolAction> toolActionMap=new HashMap<>();
    @PostConstruct
    private void init(){
       Map<String,AbstractToolAction> beanMap= springApplicationContext.getBeansOfType(AbstractToolAction.class);
        for(Map.Entry<String,AbstractToolAction> entry:beanMap.entrySet()){
            if(entry.getValue().getClass().isAnnotationPresent(ActionCodeAnnotation.class)){
                ActionCodeAnnotation actionCodeAnnotation=   entry.getValue().getClass().getAnnotation(ActionCodeAnnotation.class);
                toolActionMap.put(actionCodeAnnotation.listType().name()+"@_@"+actionCodeAnnotation.code(),entry.getValue());
            }
        }
    }

    /**
     * 根据单据类型和环节编号获取环节端详
     * @param actionCode
     * @param listType
     * @return
     */
    public  AbstractToolAction<T> getToolAction(String actionCode, String listType){
        return toolActionMap.get(listType+"@_@"+actionCode);
    }
}
