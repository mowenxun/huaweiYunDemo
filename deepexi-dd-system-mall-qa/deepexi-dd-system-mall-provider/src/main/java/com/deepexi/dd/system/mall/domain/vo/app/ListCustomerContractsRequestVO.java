package com.deepexi.dd.system.mall.domain.vo.app;

import domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/30 14:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ListCustomerContractsRequestVO extends TenantDTO implements Serializable {

    @ApiModelProperty("合同编码")
    private String contractCode;
    @ApiModelProperty("合同名称")
    private String contractName;
    @ApiModelProperty("客户id")
    private Long customerId;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiModelProperty("关联项目id")
    private Long relatedProjectsId;
    @ApiModelProperty("关联项目名称")
    private String relatedProjectsName;
    @ApiModelProperty("业务伙伴id")
    private Long partnerId;
}
