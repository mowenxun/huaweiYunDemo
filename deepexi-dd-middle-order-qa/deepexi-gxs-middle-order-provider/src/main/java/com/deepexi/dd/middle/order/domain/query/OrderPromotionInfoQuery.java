package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderPromotionInfoQuery
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderPromotionInfoQuery extends AbstractObject implements Serializable {

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
         * 促销类型
         */
        private Integer promotionType;
        /**
         * 促销ID
         */
        private Long promotionId;
        /**
         * 促销名称
         */
        private String promotionName;
        /**
         * 促销描述
         */
        private String promotionSpec;
        /**
         * 促销规则
         */
        private String promotionRule;
        /**
         * 促销开始时间
         */
        private Date startTime;
        /**
         * 促销结束时间
         */
        private Date endTime;
        /**
         * sku商品ID集合
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

