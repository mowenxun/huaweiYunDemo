package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* OrderReturnInfoQuery
*
* @author admin
* @date Tue Jun 23 19:44:57 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderReturnInfoRequestQuery")
public class OrderReturnInfoAppRequestQuery extends BasePage implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 退单编号
    */
    @ApiModelProperty(value = "退单编号")
    private String code;
    /**
     * 来源订单
     */
    @ApiModelProperty(value = "来源订单")
    private String saleOrderCode;
    /**
    * 退单状态
    */
    @ApiModelProperty(value = "退单状态")
    private String status;
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

