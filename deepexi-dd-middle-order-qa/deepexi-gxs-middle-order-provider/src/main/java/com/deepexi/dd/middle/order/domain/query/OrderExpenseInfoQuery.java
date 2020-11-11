package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderExpenseInfoQuery
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderExpenseInfoQuery extends AbstractObject implements Serializable {

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
         * 费用金额
         */
        private BigDecimal amount;
        /**
         * 费用类型
         */
        private Integer policyType;
        /**
         * 费用ID
         */
        private Long policyId;
        /**
         * 费用名称
         */
        private String policyName;
        /**
         * 是否可退(0,不可退,1,可退)
         */
        private Integer canReturn;
}

