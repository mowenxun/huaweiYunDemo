package com.deepexi.dd.system.mall.domain.query.stock;

import com.deepexi.util.pojo.AbstractObject;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StockFindAllPostAdminQuery extends AbstractObject {
    @ApiModelProperty("appId")
    private Long appId;
    @ApiModelProperty("skuId")
    private Long skuId;
    @SerializedName("tenantId")
    private String tenantId;
    @ApiModelProperty("skuCode")
    private String skuCode;

}
