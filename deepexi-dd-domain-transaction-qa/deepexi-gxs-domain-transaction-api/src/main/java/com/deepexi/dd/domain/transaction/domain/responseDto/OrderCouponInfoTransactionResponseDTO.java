package com.deepexi.dd.domain.transaction.domain.responseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liaop on 2020/7/6.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderCouponInfoTransactionResponseDTO extends AbstractTenantResponseDTO implements Serializable {

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
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
    @ApiModelProperty(value = "sku商品ids")
    private String skuIds;
    /**
     * sku名称、规格
     */
    @ApiModelProperty(value = "sku名称、规格")
    private String skuNames;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;
}
