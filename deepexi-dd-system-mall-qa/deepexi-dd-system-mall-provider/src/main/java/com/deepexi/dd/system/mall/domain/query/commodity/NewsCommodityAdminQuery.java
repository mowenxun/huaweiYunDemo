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
public class NewsCommodityAdminQuery extends AbstractObject {

    @ApiModelProperty("商品授权信息集合")
    @NotEmpty(message = "商品授权信息不能为空!")
    private List<NewsSkuInfo> skuInfos;

    @ApiModelProperty("租户id")
    private String tenantId;

    @ApiModelProperty("应用id")
    private Long appId;

    @ApiModelProperty("数据隔离id")
    private String isolationId;

    @Data
    public static class NewsSkuInfo{
        @ApiModelProperty("商品skuId")
        @NotNull(message = "商品skuId不能为空！")
        private List<Long> skuIds;

        @ApiModelProperty("商品授权的组织id")
        @NotNull(message = "商品授权组织id不能为空！")
        private Long authorizeBy;
    }
}
