package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderCollectionNotifyDto;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOrderPayNotifyDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单支付接口
 * @author yuanzaishun
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@RequestMapping("/open-api/v1/domain/transaction/orderPay")
public interface OrderPayApi {
    /**
     * 交易流水确认
     * @param saleOrderPayNotifyDto
     */
    @PostMapping("/paySure")
    void paySure(@RequestBody  SaleOrderPayNotifyDto saleOrderPayNotifyDto);

    /**
     * 按单收款
     */
    @PostMapping("/paymentCollection")
    void paymentCollection(@RequestBody SaleOrderCollectionNotifyDto saleOrderCollectionNotifyDto);
}
