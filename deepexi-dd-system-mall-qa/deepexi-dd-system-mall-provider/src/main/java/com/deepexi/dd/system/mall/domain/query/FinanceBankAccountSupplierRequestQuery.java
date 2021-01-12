package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel(
        description = "FinanceBankAccountSupplierRequestQuery"
)
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceBankAccountSupplierRequestQuery extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("租户id")
    private String tenantId;
    @ApiModelProperty("应用id")
    private Long appId;
    @ApiModelProperty("数据隔离id")
    private String isolationId;
    @ApiModelProperty("供应商id")
    private Long supplierId;

}

