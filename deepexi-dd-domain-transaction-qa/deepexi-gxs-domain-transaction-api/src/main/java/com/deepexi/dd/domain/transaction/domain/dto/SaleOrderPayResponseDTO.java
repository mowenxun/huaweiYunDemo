package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderPayResponseDTO extends AbstractObject implements Serializable{

    /**
     * 商户code
     */
    @ApiModelProperty(value = "商户code",dataType = "String")
    private String merchantCode;

//    /**
//     * 随机字符串
//     */
//    @ApiModelProperty(value = "随机字符串",dataType = "String")
//    private String nonceStr;
//
//    /**
//     * 支付中心支付订单号
//     */
//    @ApiModelProperty(value = "支付中心支付订单号",dataType = "String")
//    private String orderNo;

    /**
     * 商户订单号
     */
    @ApiModelProperty(value = "商户订单号",dataType = "String")
    private String outPayOrderNo;

    /**
     * 是否打开收银台
     */
    @ApiModelProperty(value = "是否打开收银台",dataType = "Boolean")
    private Boolean openCashier;

    @ApiModelProperty(value = "收银台的地址", dataType = "String")
    private String returnUrl;
}
