package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/30 14:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ListCustomerProjectsResponseVO extends AbstractObject implements Serializable {

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
     * 项目编码
     */
    @ApiModelProperty(value = "项目编码")
    private String projectCode;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 业务伙伴id
     */
    @ApiModelProperty(value = "业务伙伴id")
    private Long partnerId;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
}
