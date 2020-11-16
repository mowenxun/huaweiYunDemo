package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-08 19:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderReturnInfoQuery extends BasePage implements Serializable {


    /**
     * 退单编号
     */
    @ApiModelProperty(value = "退单编号")
    private String code;
    /**
     * 来源订单编号
     */
    @ApiModelProperty(value = "来源订单编号")
    private String saleOrderCode;
    /**
     * 退单状态
     */
    @ApiModelProperty(value = "退单状态")
    private Integer status;
    /**
     * 客户编号
     */
    @ApiModelProperty(value = "客户编号")
    private Long buyerNo;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String buyerName;
    /**
     * 退货渠道
     */
    @ApiModelProperty(value = "退货渠道")
    private String buyerType;
}
