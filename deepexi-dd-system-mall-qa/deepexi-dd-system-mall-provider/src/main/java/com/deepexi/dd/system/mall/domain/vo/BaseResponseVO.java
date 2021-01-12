package com.deepexi.dd.system.mall.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BaseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class BaseResponseVO extends TeantVO {

    private static final long serialVersionUID = 7644639765104362687L;

    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;
}
