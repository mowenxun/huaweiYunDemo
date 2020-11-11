package com.deepexi.dd.middle.order.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author mumu
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdTime", new Date(), metaObject);
        setFieldValByName("updatedTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedTime", new Date(), metaObject);
    }

}