package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * SaleDeliveryGoodsPlanRulesDO
 *
 * @author admin
 * @date Wed Aug 12 14:26:43 CST 2020
 * @version 1.0
 */
@TableName("sale_delivery_goods_plan_rules")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryGoodsPlanRulesDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

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

