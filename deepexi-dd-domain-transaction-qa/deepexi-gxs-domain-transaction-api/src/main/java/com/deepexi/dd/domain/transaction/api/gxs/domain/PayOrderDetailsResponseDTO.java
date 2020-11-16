package com.deepexi.dd.domain.transaction.api.gxs.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author huanghuai
 * @date 2020-10-14 06:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class PayOrderDetailsResponseDTO extends AppIdDTO {
    private static final long serialVersionUID = 7888192850738771336L;

    @ApiModelProperty(value = "应收单编号")
    private String orderCode;
    @ApiModelProperty(value = "待收金额")
    private BigDecimal unpayMoney;

    // 来源单据信息
    @ApiModelProperty(value = "单据日期")
    private Date orderTime;
    @ApiModelProperty(value = "来源单据（订单号）")
    private String sourceOrderCode;
    @ApiModelProperty(value = "收款信息List")
    private List<PayOrderItemInfo> payOrderItemInfos;

    @EqualsAndHashCode(callSuper = false)
    @Data
    @ApiModel("PayOrderDetailsResponseVOPayOrderItemInfo")
    public static class PayOrderItemInfo extends AbstractObject implements Serializable {
        private static final long serialVersionUID = 5970508419591747563L;
        @ApiModelProperty(value = "订单金额")
        private BigDecimal totalAmount;
        @ApiModelProperty(value = "供应商收款账号")
        private String accountNo;
        @ApiModelProperty(value = "收款银行名称")
        private String bankName;

        @ApiModelProperty(value = "付款银行")
        private String payBank;
        @ApiModelProperty(value = "付款银行账号")
        private String payAccount;


        @ApiModelProperty(value = "本次收款（本次支付金额）")
        private BigDecimal payMoney;
        @ApiModelProperty(value = "本次收款时间（支付时间）")
        private Date payTime;



        @ApiModelProperty(value = "收款凭证，多个用 ; 隔开")
        private String payVoucherPicture;
        @ApiModelProperty(value = "备注")
        private String remark;
    }
}