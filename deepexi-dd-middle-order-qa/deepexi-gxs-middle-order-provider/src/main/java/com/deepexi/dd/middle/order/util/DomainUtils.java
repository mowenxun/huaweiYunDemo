package com.deepexi.dd.middle.order.util;

import com.deepexi.dd.middle.order.domain.BaseDO;

import java.util.Date;

/**
 * DomainUtils
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 12 20:24:31 CST 2020
 */
public class DomainUtils {
    private DomainUtils() {
    }

    public static void initUpdateTimeInfo(BaseDO data) {
        Date now = new Date();
        data.setUpdatedTime(now);
    }

    public static void initDomainCreatedInfo(BaseDO body) {
        Date now = new Date();
        body.setDeleted(false);
        body.setVersion(1);
        body.setCreatedTime(now);
        body.setUpdatedTime(now);
    }
}
