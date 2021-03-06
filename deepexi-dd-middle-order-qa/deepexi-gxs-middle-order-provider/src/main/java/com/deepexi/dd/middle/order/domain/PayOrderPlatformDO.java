package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 应付单  平台财务付款对账使用
 * @author huanghuai
 * @date 2020-10-14 06:53
 */
@TableName("pay_order_platform")
@Data
@EqualsAndHashCode(callSuper = false)
public class PayOrderPlatformDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 8863574664654964562L;


    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;


    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    /**
     * 来源单号
     */
    @ApiModelProperty(value = "来源单号")
    private String sourceOrderCode;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date orderTime;

    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;

    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    private String sellerCode;
    /**
     * 收款状态
     */
    @ApiModelProperty(value = "收款状态")
    private Integer collectionStatus;

    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;

    /**
     * 已支付总金额
     */
    @ApiModelProperty(value = "已支付总金额")
    private BigDecimal paidMoney;

    /**
     * 未支付总金额
     */
    @ApiModelProperty(value = "未支付总金额")
    private BigDecimal unpayMoney;
}