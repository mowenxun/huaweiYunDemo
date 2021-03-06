package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderPayBackQuery
 *
 * @author admin
 * @date Wed Jul 22 16:27:51 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderPayBackQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页数
     */
    private Integer size = 10;

        /**
         * ID
         */
        private Long id;
        /**
         * 租户ID
         */
        private String tenantId;
        /**
         * APP标识
         */
        private Long appId;
        /**
         * 版本号
         */
        private Integer version;
        /**
         * 备注
         */
        private String remark;
        /**
         * 创建时间
         */
        private Date createdTime;
        /**
         * 更新时间
         */
        private Date updatedTime;
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
         * 支付状态
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

