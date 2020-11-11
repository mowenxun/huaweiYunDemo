package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SaleOrderCollectionNotifyQuery
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderCollectionNotifyQuery extends AbstractObject implements Serializable {

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
         * 
         */
        private Long id;
        /**
         * 租户ID
         */
        private String tenantId;
        /**
         * APPID
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
         * 订单ID
         */
        private Long orderId;
        /**
         * 支付流水号
         */
        private String payNo;
        /**
         * 金额
         */
        private BigDecimal amount;
}

