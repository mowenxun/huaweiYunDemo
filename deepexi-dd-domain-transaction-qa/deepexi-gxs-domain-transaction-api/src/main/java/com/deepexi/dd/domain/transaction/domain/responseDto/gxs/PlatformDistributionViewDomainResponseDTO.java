package com.deepexi.dd.domain.transaction.domain.responseDto.gxs;

import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 平台分发预览实体
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 14:53:15 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PlatformDistributionViewDomainResponseDTO")
public class PlatformDistributionViewDomainResponseDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

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
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    private Long appId;


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

    @ApiModelProperty(value = "店铺订单集合；提货供销社在这个集合里面取")
    private List<ShopOrderResponseDTO> shopOrderResponseDTOS;

    @ApiModelProperty(value = "所属供销社集合")
    private List<DistributionResponseDTO> distributionResponseDTOS;

    @ApiModelProperty(value = "订单商品明细")
    private List<ShopOrderItemResponseDTO> shopOrderItem;

    @ApiModelProperty(value = "分发订单对应的发票信息；只有重新分发预览这个才会有值")
    private OrderInvoiceResponseDTO orderInvoiceResponseDTO;

    @ApiModelProperty(value = "分发订单对应的收货信息；只有重新分发预览这个才会有值")
    private OrderConsigneeAddressResponseDTO orderConsigneeAddressResponseDTO;

    /**
     * 要货日期
     */
    @ApiModelProperty(value = "要货日期")
    @NotNull(message = "要货日期不能为空")
    private Date arriveDate;

    @ApiModelProperty(value = "备注")
    @NotNull(message = "备注不能为空")
    private String remark;


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

}

