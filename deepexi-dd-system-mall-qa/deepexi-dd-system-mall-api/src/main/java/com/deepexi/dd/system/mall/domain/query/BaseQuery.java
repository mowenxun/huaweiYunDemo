package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import lombok.Data;

//import com.deepexi.util.DateUtils;

@Data
public abstract class BaseQuery extends TenantDTO {

    // 时间
//    @DateTimeFormat(pattern = DateUtils.DEFAULT_DATE_TIME_FORMAT)
    private String createTimeFrom;
//    @DateTimeFormat(pattern = DateUtils.DEFAULT_DATE_TIME_FORMAT)
    private String createTimeTo;


}
