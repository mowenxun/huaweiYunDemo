package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 订单费用添加对象
*
* @author admin
* @date Wed Jun 24 09:42:04 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单费用添加对象")
public class OrderExpenseInfoRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;


    /**
    * 费用金额
    */
    @ApiModelProperty(value = "费用金额",required = true)
    @NotNull(message = "费用金额为空")
    @Range(min = 0,message = "费用金额错误")
    private BigDecimal amount;
    /**
    * 费用类型
    */
    @ApiModelProperty(value = "费用类型")
    private Integer policyType;
    /**
    * 费用ID
    */
    @ApiModelProperty(value = "费用ID",required = true)
    @NotNull(message = "费用ID为空")
    private Long policyId;
    /**
    * 费用名称
    */
    @ApiModelProperty(value = "费用名称")
    private String policyName;
    /**
    * 是否可退(0,不可退,1,可退)
    */
    @ApiModelProperty(value = "是否可退(0,不可退,1,可退)")
    private Integer canReturn;

}

