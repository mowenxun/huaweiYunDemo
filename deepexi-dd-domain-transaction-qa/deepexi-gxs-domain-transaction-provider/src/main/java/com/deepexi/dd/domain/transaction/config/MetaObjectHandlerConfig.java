package com.deepexi.dd.domain.transaction.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 *
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