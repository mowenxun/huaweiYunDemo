package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* OrderReturnItemQuery
*
* @author admin
* @date Tue Jun 23 19:44:58 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderReturnItemRequestQuery")
public class OrderReturnItemRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * 应用ID
    */
    @ApiModelProperty(value = "应用ID")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Date createdTime;
    /**
    * 退单ID
    */
    @ApiModelProperty(value = "退单ID")
    private Long orderReturnId;
    /**
    * 退单编号
    */
    @ApiModelProperty(value = "退单编号")
    private String orderReturnCode;
    /**
    * 计价单位
    */
    @ApiModelProperty(value = "计价单位")
    private Long unitId;
    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
    /**
    * 商品名称
    */
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    /**
    * 商品编号
    */
    @ApiModelProperty(value = "商品编号")
    private String skuCode;
    /**
    * 商品规格
    */
    @ApiModelProperty(value = "商品规格")
    private String skuFormat;
    /**
    * 商品数量
    */
    @ApiModelProperty(value = "商品数量")
    private Long skuQuantity;
    /**
    * 销售订单ID
    */
    @ApiModelProperty(value = "销售订单ID")
    private Long saleOrderId;
    /**
    * 销售订单编号
    */
    @ApiModelProperty(value = "销售订单编号")
    private String saleOrderCode;
    /**
    * 销售订单明细ID
    */
    @ApiModelProperty(value = "销售订单明细ID")
    private Long saleOrderItem;
    /**
    * 价格政策标识
    */
    @ApiModelProperty(value = "价格政策标识")
    private Long pricePolicyId;
    /**
    * 退货数量
    */
    @ApiModelProperty(value = "退货数量")
    private Long returnQuantity;
}

