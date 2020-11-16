package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.AbstractTenantDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName : PayCallbackRequestDTO
 * @Description : 支付回调请求参数
 * @Author : yuanzaishun
 * @Date: 2020-07-21 15:52
 */
@ApiModel("支付回调请求参数")
@Data
public class PayCallbackRequestDTO extends AbstractObject implements Serializable {



    private String tenantId;


    private Long appId;


    /**
     * 支付中心订单号
     */
    private String payOrderNo;

    /**
     * 商户订单号
     */
    private String outPayOrderNo;

    /**
     * 支付中心交易流水号
     */
    private String payTradeNo;

    /**
     * 三方交易流水号
     */
    private String thirdpartyPayTradeNo;

    /**
     * 支付状态 {@link com.deepexi.middle.pay.sdk.enums.PayOrderStatusTypeEnum}
     */
    private Integer payStatus;

    /**
     * 订单金额，元
     */
    private BigDecimal amount;

    /**
     * 买家实际支付金额，元
     */
    private BigDecimal buyerPayAmount;

    /**
     * 交易创建时间
     */
    private Date tradeCreateTime;

    /**
     * 支付成功时间
     */
    private Date paySuccessTime;


    /**
     * 买家ID
     */
    private String buyerId;

    /**
     * 支付渠道code
     * ALIPAY: 支付宝
     * WX：微信
     * UNION：云闪付
     */
    private String payChannelCode;

    /**
     * 支付方式ID
     */
    private Long payMethodId;

    /**
     * 支付方式编码，支付方式ID和支付方式类型必须选填一个，若是两个都填则优先选取支付方式ID
     *
     * 支付宝 App 支付: ALIPAY_APP,  支付宝手机网站支付:ALIPAY_WAP,  支付宝扫码支付:ALIPAY_QR
     * 支付宝条码支付:ALIPAY_SCAN, 支付宝小程序支付:ALIPAY_LITE, 支付宝电脑网站支付:ALIPAY_PC_DIRECT
     * 微信 App 支付:WX_APP, 微信 JSAPI 支付:WX_PUB, 微信 Native 支付:WX_PUB_QR, 微信付款码支付:WX_PUB_SCAN
     * 微信 H5 支付:WX_WAP, 微信小程序支付:WX_LITE, 银联手机控件支付（银联 App 支付）:UPACP,
     * 银联网关支付（银联 PC 网页支付）:UPACP_PC, 银联二维码（主扫）:UPACP_QR, 银联二维码（被扫）:UPACP_SCAN
     * 银联手机网站支付:UPACP_WAP, Apple Pay:APPLEPAY_UPACP, 花呗分期App支付:PCREDIT_APP, 花呗分期手机网站支付:PCREDIT_WAP
     * 花呗分期小程序支付:PCREDIT_LITE, 花呗分期电脑网站支付:PCREDIT_PC_DIRECT
     */
    private String payMethodCode;

    /**
     * 签名
     */
    private String signature;

    /**
     * 签名方式
     */
    private String signType;


}
