package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* 订单费用信息
*
* @author admin
* @date Wed Jun 24 09:42:04 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单费用信息")
public class OrderExpenseInfoResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 费用金额
    */
    @ApiModelProperty(value = "费用金额")
    private BigDecimal amount;
    /**
    * 费用类型
    */
//    @ApiModelProperty(value = "费用类型")
//    private Integer policyType;
    /**
    * 费用ID
    */
//    @ApiModelProperty(value = "费用ID")
//    private Long policyId;
    /**
    * 费用名称
    */
//    @ApiModelProperty(value = "费用名称")
//    private String policyName;
    /**
    * 是否可退(0,不可退,1,可退)
    */
//    @ApiModelProperty(value = "是否可退(0,不可退,1,可退)")
//    private Integer canReturn;

}

