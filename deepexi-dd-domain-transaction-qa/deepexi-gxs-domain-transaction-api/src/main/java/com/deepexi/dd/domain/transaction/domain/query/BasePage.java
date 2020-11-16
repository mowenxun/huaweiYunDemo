package com.deepexi.dd.domain.transaction.domain.query;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public abstract class BasePage extends BaseQuery{

    // 分页
    @ApiParam(value = "page", example = "-1")
    private Integer page = -1;
    @ApiParam(value = "size", example = "20")
    private Integer size = 20;
}
