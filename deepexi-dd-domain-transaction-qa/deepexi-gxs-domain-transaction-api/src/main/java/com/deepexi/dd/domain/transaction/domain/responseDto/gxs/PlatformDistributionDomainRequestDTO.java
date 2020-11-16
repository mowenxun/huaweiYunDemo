package com.deepexi.dd.domain.transaction.domain.responseDto.gxs;

import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 确认分发实体
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 14:53:15 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PlatformDistributionDomainRequestDTO")
public class PlatformDistributionDomainRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "已分发订单id")
    private Long id;

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
    @NotNull(message = "供货商id不能为空")
    private Long sellerId;
    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    @NotNull(message = "供货商名称不能为空")
    private String sellerName;
    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    @NotNull(message = "供货商编码不能为空")
    private String sellerCode;


    /**
     * 收货供销社编码
     */
    @ApiModelProperty(value = "收货供销社编码")
    @NotNull(message = "收货供销社编码不能为空")
    private String receiveDistributionCode;
    /**
     * 收货供销社id
     */
    @ApiModelProperty(value = "收货供销社id")
    @NotNull(message = "收货供销社id不能为空")
    private Long receiveDistributionId;
    /**
     * 收货供销社名称
     */
    @ApiModelProperty(value = "收货供销社名称")
    @NotNull(message = "收货供销社名称不能为空")
    private String receiveDistributionName;
    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    @NotNull(message = "收货地址不能为空")
    private String receiveAddress;


    //收货地址
    @ApiModelProperty(value = "收货地址")
    //@NotNull(message = "收货地址实体不能为空")
    private OrderConsigneeAddressRequestDTO orderConsigneeAddressRequestDTO;


    //发票
    @ApiModelProperty(value = "发票")
    @NotNull(message = "发票实体不能为空")
    private OrderInvoiceRequestDTO orderInvoiceRequestDTO;

    /**
     * 要货日期
     */
    @ApiModelProperty(value = "要货日期")
    @NotNull(message = "要货日期不能为空")
    private Date arriveDate;

    @ApiModelProperty(value = "备注")
    @NotNull(message = "备注不能为空")
    private String remark;

    //明细
    @NotEmpty(message = "订单明细不能为空")
    private List<PlatformDistbutionItemAddDTO> item;


    @ApiModelProperty(value = "店铺订单集合")
    @NotEmpty(message = "店铺订单集合不能为空")
    private List<ShopOrderResponseDTO> shopOrderResponseDTOS;


}

