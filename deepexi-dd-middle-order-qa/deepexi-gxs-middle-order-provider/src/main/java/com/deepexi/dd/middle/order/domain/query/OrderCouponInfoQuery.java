package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderCouponInfoQuery
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderCouponInfoQuery extends AbstractObject implements Serializable {

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
         * 应用ID
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
        private Long saleOrderId;
        /**
         * 订单编号
         */
        private String saleOrderCode;
        /**
         * 优惠券ID
         */
        private Long couponId;
        /**
         * 优惠券编码
         */
        private String couponCode;
        /**
         * 优惠券类型
         */
        private Integer couponType;
        /**
         * 优惠券名称
         */
        private String couponName;
        /**
         * 优惠券数量
         */
        private Integer couponQuntity;
        /**
         * 面值金额
         */
        private BigDecimal faceValue;
        /**
         * 优惠规则
         */
        private String couponRule;
        /**
         * 优惠券过期时间
         */
        private Date expiredTime;
        /**
         * sku商品ids
         */
        private String skuIds;
        /**
         * sku名称、规格
         */
        private String skuNames;
        /**
         * 优惠金额
         */
        private BigDecimal discountAmount;
}

