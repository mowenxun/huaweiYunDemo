package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* SupplerOrderDTO
*
* @author admin
* @date Tue Oct 13 15:15:23 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SupplerOrderResponseDTO")
public class SupplerOrderResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    * 0：未逻辑删除状态。1:删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;
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

    @ApiModelProperty(value = "已分发订单关联的门店单号")
    private List<String> shopOrderCodes;

    @ApiModelProperty(value = "已分发订单关联的门店单号")
    private List<Long> shopOrderIds;

    @ApiModelProperty(value = "订单明细")
    private List<SupplerOrderItemResponseDTO> supplerOrderItemResponseDTOS;

    @ApiModelProperty(value = "发票")
    private OrderInvoiceResponseDTO orderInvoiceResponseDTO;

    @ApiModelProperty(value = "收货地址")
    private OrderConsigneeAddressResponseDTO orderConsigneeAddressResponseDTO;

    /**
     * 客户名称
     */
    @ApiModelProperty("客户名称")
    private String customerName;
}

