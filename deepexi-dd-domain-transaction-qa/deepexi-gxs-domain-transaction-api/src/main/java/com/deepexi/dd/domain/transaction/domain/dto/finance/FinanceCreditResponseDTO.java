package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* FinanceCreditDTO
*
* @author admin
* @date Thu Aug 13 10:54:23 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinanceCreditResponseDTO")
public class FinanceCreditResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
    * 租户id
    */
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    /**
    * 应用id
    */
    @ApiModelProperty(value = "应用id")
    private Long appId;
    /**
    * 伙伴id
    */
    @ApiModelProperty(value = "伙伴id")
    private Long customerId;
    /**
    * 伙伴名称
    */
    @ApiModelProperty(value = "伙伴名称")
    private String customerName;
    /**
    * 信用额度
    */
    @ApiModelProperty(value = "信用额度")
    private BigDecimal creditLimit;
    /**
    * 已用额度
    */
    @ApiModelProperty(value = "已用额度")
    private BigDecimal creditUsed;
    /**
    * 可用额度
    */
    @ApiModelProperty(value = "可用额度")
    private BigDecimal canCredit;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 状态
    */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
    * 信用等级：1-A；2-AA；3-AAA
    */
    @ApiModelProperty(value = "信用等级：1-A；2-AA；3-AAA")
    private Integer level;
    /**
    * 乐观锁
    */
    @ApiModelProperty(value = "乐观锁")
    private Integer version;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
    /**
    * 删除标识
    */
    @ApiModelProperty(value = "删除标识")
    private Boolean deleted;
    /**
    * 数据隔离
    */
    @ApiModelProperty(value = "数据隔离")
    private String isolationId;

    /**
     * 组织id.
     */
    private Long orgId;

}

