package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;



/**
 * OrderInvoiceInfoDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@TableName("order_invoice_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInvoiceInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 开户行
     */
    private String bankName;

    /**
     * 开户名称
     */
    private String accountName;

    /**
     * 开户账号
     */
    private String accountNo;

    /**
     * 
     */
    private String taxNo;
    /**
     * 发票类型:发票类型:ordinary普通电子发票
     */
    private String invoiceType;
    /**
     * 发票内容
     */
    private String invocieContent;

    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 地址
     */
    private String address;

    @ApiModelProperty(value = "发票ID")
    private Long invoiceId;
}

