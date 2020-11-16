package com.deepexi.dd.domain.transaction.domain.responseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by liaop on 2020/7/6.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInvoiceInfoTransactionResponseDTO extends AbstractTenantResponseDTO implements Serializable {


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

}
