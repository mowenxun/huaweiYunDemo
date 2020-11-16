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
 * 付款凭证表(PayVoucher)表实体类
 *
 * @author makejava
 * @since 2020-10-14 10:27:04
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
public class QueryPayVoucherResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 8637690224194187918L;
    @ApiModelProperty(value = "订单编号")
    private String orderCode;
    @ApiModelProperty(value = "应收款总金额")
    private BigDecimal totalAmount;
    @ApiModelProperty(value = "收款账户")
    private String accountName;
    @ApiModelProperty(value = "收款账号")
    private String accountNo;
    @ApiModelProperty(value = "收款银行名称")
    private String bankName;
    @ApiModelProperty(value = "支行名称")
    private String branchName;

    @ApiModelProperty(value = "付款凭证列表")
    private List<PayVoucher> payVouchers;



    @Data
    @ApiModel("QueryPayVoucherResponseVOPayVoucher")
    public static class PayVoucher extends AbstractObject implements Serializable {
        private static final long serialVersionUID = 8637690224194187918L;
        @ApiModelProperty(value = "支付银行")
        private String payBank;
        //支付时间
        @ApiModelProperty(value = "支付时间")
        private Date payTime;
        //本次支付金额
        @ApiModelProperty(value = "本次支付金额")
        private BigDecimal payMoney;
        //支付凭证，多张图片用;隔开
        @ApiModelProperty(value = "支付凭证，多张图片用;隔开")
        private String payVoucherPicture;
        @ApiModelProperty(value = "备注")
        private String remark;
    }




}