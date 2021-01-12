package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/30 14:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ListCustomerContractsResponseVO extends AbstractObject implements Serializable {

    /**
     * 租户
     */
    @ApiModelProperty(value = "租户")
    private String tenantId;

    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private Long appId;

    /**
     * 数据隔离id
     */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;

    /**
     * 合同编码
     */
    @ApiModelProperty(value = "合同编码")
    private String contractCode;

    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    private String contractName;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long customerId;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;

    /**
     * 关联项目id
     */
    @ApiModelProperty(value = "关联项目id")
    private Long relatedProjectsId;

    /**
     * 关联项目名称
     */
    @ApiModelProperty(value = "关联项目名称")
    private String relatedProjectsName;

    /**
     * 业务伙伴id
     */
    @ApiModelProperty(value = "业务伙伴id")
    private Long partnerId;

}
