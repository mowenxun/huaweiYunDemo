package com.deepexi.dd.system.mall.domain.query.commodity;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-12-25 14:15
 */
@Data
@ApiModel
public class CategoryCommodityRecommendQuery extends AbstractObject {

    @ApiModelProperty("租户id")
    private String tenantId;

    @ApiModelProperty("应用id")
    private Long appId;

    @ApiModelProperty("数据隔离id")
    private String isolationId;

}
