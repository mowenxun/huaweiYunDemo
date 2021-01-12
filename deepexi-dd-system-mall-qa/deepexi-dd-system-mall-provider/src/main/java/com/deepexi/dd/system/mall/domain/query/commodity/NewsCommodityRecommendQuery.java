package com.deepexi.dd.system.mall.domain.query.commodity;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel
public class NewsCommodityRecommendQuery extends AbstractObject {

    @ApiModelProperty("租户id")
    private String tenantId;

    @ApiModelProperty("应用id")
    private Long appId;

    @ApiModelProperty("数据隔离id")
    private String isolationId;
}
