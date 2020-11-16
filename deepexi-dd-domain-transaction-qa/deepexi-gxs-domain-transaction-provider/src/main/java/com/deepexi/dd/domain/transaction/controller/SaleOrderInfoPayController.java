package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.transaction.domain.dto.PayCallbackRequestDTO;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : SaleOrderInfoCallbackController
 * @Description : 订单支付接口
 * @Author : yuanzaishun
 * @Date: 2020-07-21 15:56
 */
@RestController
@Api(tags = "订单支付接口")
@RequestMapping("/admin-api/v1/domain/transaction/saleOrderInfoPay")
public class SaleOrderInfoPayController {

    @Autowired
    SaleOrderInfoService saleOrderInfoService;

    @PostMapping("/callback")
    public Payload<Boolean> payCallBack(@RequestBody PayCallbackRequestDTO payCallbackRequestDTO){
        //1、验签
        try{
            return new Payload<>(saleOrderInfoService.onlinePayCallBack(payCallbackRequestDTO));
        }
        catch (Exception e){
            return new Payload(null,"500",e.getMessage());
        }
    }

}
