package com.deepexi.dd.domain.transaction.domain.query.gxs;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SupplerOrderQuery
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 15:15:23 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SupplerOrderQuery")
public class SupplerOrderDomainQuery extends AbstractObject implements Serializable {

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
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
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
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String status;
    /**
     * 收货供销社编码
     */
    @ApiModelProperty(value = "收货供销社编码")
    private String receiveDistributionCode;
    /**
     * 收货供销社id
     */
    @ApiModelProperty(value = "收货供销社id")
    private Long receiveDistributionId;
    /**
     * 收货供销社名称
     */
    @ApiModelProperty(value = "收货供销社名称")
    private String receiveDistributionName;
    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String receiveAddress;
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
     * 商品总数
     */
    @ApiModelProperty(value = "商品总数")
    private Long quantity;
    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额")
    private BigDecimal totalAmount;
    /**
     * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
     */
    @ApiModelProperty(value = "支付类型:1线下支付,2在线支付,3信用支付,4余额支付")
    private Integer paymentType;
    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    private Integer paymentStatus;
    /**
     * 要货日期
     */
    @ApiModelProperty(value = "要货日期")
    private Date arriveDate;
    /**
     * 已支付金额
     */
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal payAmount;
    /**
     * 支付流水关联订单号 支付后才会生成
     */
    @ApiModelProperty(value = "支付流水关联订单号 支付后才会生成")
    private String payOrderCode;

    /**
     * 创建时间from
     */
    @ApiModelProperty(value = "创建时间from")
    private String createdTimeFrom;
    /**
     * 创建时间To
     */
    @ApiModelProperty(value = "创建时间To")
    private String createdTimeTo;
}

