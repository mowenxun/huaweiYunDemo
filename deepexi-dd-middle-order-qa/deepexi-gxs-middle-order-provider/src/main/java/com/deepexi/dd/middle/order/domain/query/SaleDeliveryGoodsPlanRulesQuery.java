package com.deepexi.dd.middle.order.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SaleDeliveryGoodsPlanRulesQuery
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryGoodsPlanRulesQuery extends AbstractObject implements Serializable {

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
         * 创建人
         */
        private String createdBy;
        /**
         * 更新时间
         */
        private Date updatedTime;
        /**
         * 修改人
         */
        private String updatedBy;
        /**
         * 适用提货方式(0:全部;1:外仓;2:小库;3:工地)
         */
        private String deliveryWay;
        /**
         * 规则名称
         */
        private String ruleName;
        /**
         * 规则
         */
        private String rule;
        /**
         * 参数值
         */
        private Long value;
        /**
         * 是否启用(0:启用;1:停用)
         */
        private Long isEnabled;
}

