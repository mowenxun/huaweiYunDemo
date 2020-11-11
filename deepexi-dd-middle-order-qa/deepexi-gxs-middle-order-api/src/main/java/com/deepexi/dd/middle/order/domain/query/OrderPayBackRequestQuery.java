package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderPayBackQuery
*
* @author admin
* @date Wed Jul 22 16:27:51 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderPayBackRequestQuery")
public class OrderPayBackRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * APP标识
    */
    @ApiModelProperty(value = "APP标识")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 支付中心订单号
    */
    @ApiModelProperty(value = "支付中心订单号")
    private String payOrderNo;
    /**
    * 商户订单号
    */
    @ApiModelProperty(value = "商户订单号")
    private String outPayOrderNo;
    /**
    * 支付中心交易流水号
    */
    @ApiModelProperty(value = "支付中心交易流水号")
    private String payTradeNo;
    /**
    * 三方交易流水号
    */
    @ApiModelProperty(value = "三方交易流水号")
    private String thirdpartyPayTradeNo;
    /**
    * 支付状态
    */
    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;
    /**
    * 订单金额，元
    */
    @ApiModelProperty(value = "订单金额，元")
    private BigDecimal amount;
    /**
    * 买家实际支付金额，元
    */
    @ApiModelProperty(value = "买家实际支付金额，元")
    private BigDecimal buyerPayAmount;
    /**
    * 交易创建时间
    */
    @ApiModelProperty(value = "交易创建时间")
    private Date tradeCreateTime;
    /**
    * 支付成功时间
    */
    @ApiModelProperty(value = "支付成功时间")
    private Date paySuccessTime;
    /**
    * 买家ID
    */
    @ApiModelProperty(value = "买家ID")
    private String buyerId;
    /**
    * 支付渠道code
     * ALIPAY: 支付宝
     * WX：微信
     * UNION：云闪付
    */
    @ApiModelProperty(value = "支付渠道code * ALIPAY: 支付宝 * WX：微信 * UNION：云闪付")
    private String payChannelCode;
    /**
    * 支付方式ID
    */
    @ApiModelProperty(value = "支付方式ID")
    private Long payMethodId;
    /**
    * 支付方式编码，支付方式ID和支付方式类型必须选填一个，若是两个都填则优先选取支付方式ID
    */
    @ApiModelProperty(value = "支付方式编码，支付方式ID和支付方式类型必须选填一个，若是两个都填则优先选取支付方式ID")
    private String payMethodCode;
    /**
    * 签名
    */
    @ApiModelProperty(value = "签名")
    private String signature;
    /**
    * 签名方式
    */
    @ApiModelProperty(value = "签名方式")
    private String signType;
}

