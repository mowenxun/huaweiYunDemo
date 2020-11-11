package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
* OrderInvoiceInfoDTO
*
* @author admin
* @date Wed Jun 24 09:42:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderInvoiceInfoRequestDTO")
public class OrderInvoiceInfoRequestDTO extends AbstractTenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

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


    /**
     * 发票类型:发票类型:ordinary普通电子发票
     */
    @ApiModelProperty(value = "发票类型:发票类型:ordinary普通电子发票")
    private String invoiceType;
    /**
     * 发票内容
     */
    @ApiModelProperty(value = "发票内容")
    private String invocieContent;

    @ApiModelProperty(value = "联系方式")
    private String mobile;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "发票ID")
    private Long invoiceId;
    
}

