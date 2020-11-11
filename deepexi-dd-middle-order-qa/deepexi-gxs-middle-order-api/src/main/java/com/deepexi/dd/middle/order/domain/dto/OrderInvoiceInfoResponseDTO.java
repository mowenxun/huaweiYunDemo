package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* OrderInvoiceInfoDTO
*
* @author admin
* @date Wed Jun 24 09:42:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderInvoiceInfoResponseDTO")
public class OrderInvoiceInfoResponseDTO extends AbstractTenantDTO implements Serializable {

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
     * 发票抬头
     */
    @ApiModelProperty(value = "发票抬头")
    private String invoiceTitle;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行")
    private String bankName;
    /**
     * 开户名称
     */
    @ApiModelProperty(value = "开户名称")
    private String accountName;
    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号")
    private String accountNo;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String taxNo;

    @ApiModelProperty(value = "联系电话")
    private String mobile;
    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "发票ID")
    private Long invoiceId;
}

