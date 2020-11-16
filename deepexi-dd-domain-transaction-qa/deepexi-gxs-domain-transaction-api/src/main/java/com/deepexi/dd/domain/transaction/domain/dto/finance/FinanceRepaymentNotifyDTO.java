package com.deepexi.dd.domain.transaction.domain.dto.finance;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName : FinanceRepaymentNotifyDTO
 * @Description : 还款通知对象
 * @Author : yuanzaishun
 * @Date: 2020-09-11 15:42
 */
@Data
public class FinanceRepaymentNotifyDTO {
    /**
     * 信用账单id
     */
    private Long creditRepaymentId;

    /**
     * 支付流水编号
     */
    private String payNo;

    /**
     * 金额
     */
    private BigDecimal amount;
}
