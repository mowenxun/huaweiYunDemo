package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情返回对象
 *
 * @author yuanzaishun
 * @date Wed Jun 24 11:00:03 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoReceiptResponseDTO  implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID",dataType = "Long")
    private Long id;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号",dataType = "Integer")
    private Integer version;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",dataType = "String")
    private String orderNo;

    /**
     * 结算单位
     */
    @ApiModelProperty(value="结算单位",dataType = "String")
    private String settlementName;


    /**
     * 结算单位ID
     */
    @ApiModelProperty(value="结算单位ID",dataType = "Long")
    private Long settlementId;

    /**
     * 往来单位ID
     */
    @ApiModelProperty(value="往来单位ID",dataType = "Long")
    private Long customerId;

    /**
     * 往来单位
     */
    @ApiModelProperty(value="往来单位",dataType = "Long")
    private String customerName;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value="单据类型(ordinary:普通销售单)",dataType = "String")
    private Integer ticketType;

    /**
     * 收款金额
     */
    @ApiModelProperty(value="收款金额",dataType = "BigDecimal")
    private BigDecimal amount;

    /**
     * 单据日期
     */
    @ApiModelProperty(value="单据日期",dataType = "Date")
    private Date receiptsTime;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value="支付状态 0:未付款,1:已付款,2:部分付款",dataType = "Integer")
    private Integer paymentStatus;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value="已支付金额",dataType = "BigDecimal")
    private BigDecimal paymentAmount;

    /**
     * 支付类型
     */
    @ApiModelProperty(value="支付类型",dataType = "String")
    private String paymentType;

    @ApiModelProperty(value = "操作按钮")
    private List<OrderActionResponseDTO> actions;

}

