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
* 订单优惠券信息
*
* @author admin
* @date Wed Jun 24 09:42:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单优惠券信息")
public class OrderCouponInfoResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
    * 优惠券ID
    */
    @ApiModelProperty(value = "优惠券ID")
    private Long couponId;
    /**
    * 优惠券编码
    */
    @ApiModelProperty(value = "优惠券编码")
    private String couponCode;
    /**
    * 优惠券类型
    */
    @ApiModelProperty(value = "优惠券类型")
    private Integer couponType;
    /**
    * 优惠券名称
    */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;
    /**
    * 优惠券数量
    */
    @ApiModelProperty(value = "优惠券数量")
    private Integer couponQuntity;
    /**
    * 面值金额
    */
    @ApiModelProperty(value = "面值金额")
    private BigDecimal faceValue;
    /**
    * 优惠规则
    */
    @ApiModelProperty(value = "优惠规则")
    private String couponRule;
    /**
    * 优惠券过期时间
    */
    @ApiModelProperty(value = "优惠券过期时间")
    private Date expiredTime;
    /**
    * sku商品ids
    */
    @ApiModelProperty(value = "适用商品ids,逗号分隔")
    private String skuIds;
    /**
    * sku名称、规格
    */
    @ApiModelProperty(value = "适用商品名称,逗号分隔")
    private String skuNames;
    /**
    * 优惠金额
    */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

}

