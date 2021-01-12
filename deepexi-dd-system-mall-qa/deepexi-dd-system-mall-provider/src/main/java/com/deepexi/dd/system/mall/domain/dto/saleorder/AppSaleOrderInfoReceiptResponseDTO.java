package com.deepexi.dd.system.mall.domain.dto.saleorder;

import com.deepexi.dd.domain.transaction.domain.dto.OrderActionResponseDTO;
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
 * @version 1.0
 * @date Wed Jun 24 11:00:03 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppSaleOrderInfoReceiptResponseDTO implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID", dataType = "Long")
    private Long id;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", dataType = "Integer")
    private Integer version;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", dataType = "String")
    private String orderNo;

    /**
     * 结算单位
     */
    @ApiModelProperty(value = "结算单位", dataType = "String")
    private String settlementName;


    /**
     * 结算单位ID
     */
    @ApiModelProperty(value = "结算单位ID", dataType = "Long")
    private Long settlementId;

    /**
     * 往来单位ID
     */
    @ApiModelProperty(value = "往来单位ID", dataType = "Long")
    private Long customerId;

    /**
     * 往来单位
     */
    @ApiModelProperty(value = "往来单位", dataType = "Long")
    private String customerName;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)", dataType = "String")
    private Integer ticketType;

    /**
     * 收款金额
     */
    @ApiModelProperty(value = "收款金额", dataType = "BigDecimal")
    private BigDecimal amount;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期", dataType = "Date")
    private Date receiptsTime;

    /**
     * 支付状态，查字典表
     */
    @ApiModelProperty(value = "支付状态，查字典表", dataType = "Integer")
    private Integer paymentStatus;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value = "已支付金额", dataType = "BigDecimal")
    private BigDecimal paymentAmount;

    /**
     * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
     */
    @ApiModelProperty(value = "支付类型:1线下支付,2在线支付,3信用支付,4余额支付", dataType = "String")
    private String paymentType;

    /**
     * 操作按钮
     */
    @ApiModelProperty(value = "操作按钮")
    private List<OrderActionResponseDTO> actions;

    /**
     * 支付状态,对应字典表的值
     */
    @ApiModelProperty(value = "支付状态,对应字典表的值")
    private String paymentStatusName;

}
