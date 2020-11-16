package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
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
