package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName : SaleOrderPayNotifyDto
 * @Description : 订单支付通知对象
 * @Author : yuanzaishun
 * @Date: 2020-07-21 16:52
 */
@Data
public class SaleOrderPayNotifyDto extends AbstractObject {
    /**
     * 支付记录编号
     */
    private String payNo;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 确认,取消
     */
    private Integer type;
    /**
     * 订单类型1网批订单，2提货订单
     */
    private Integer orderType;
    /**
     * 支付金额
     */
    private BigDecimal amount;
    /**
     * 租户ID
     */
    private String tenanId;
    /**
     * 应用ID
     */
    private Long appId;
    /**
     * 订单号
     */
    private String orderCode;

}
