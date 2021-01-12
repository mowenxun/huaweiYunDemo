package com.deepexi.dd.system.mall.domain.query.customer;

import domain.query.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerPickUpWarehouseAdminRequestQuery extends BasePage {
//    @ApiModelProperty("仓库编号")
//    private String warehouseCode;
//    @ApiModelProperty("仓库名称")
//    private String warehouseName;
    @ApiModelProperty("顶级组织id")
    @NotNull(message = "顶级组织id不能为空")
    private Long topOrgId;
    @ApiModelProperty("组织id")
    @NotNull(message = "组织id不能为空")
    private Long orgId;
    @ApiModelProperty("伙伴id")
    @NotNull(message = "伙伴id不能为空")
    private Long partnerId;
    @ApiModelProperty("订单类型")
    private Long orderType;
}
