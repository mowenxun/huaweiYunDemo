package com.deepexi.dd.domain.transaction.enums;

import lombok.Getter;

/**
 * 订单类型枚举
 *
 * @author yuanzaishun
 * @version 1.0
 * @date Fri Jun 19 17:38:17 CST 2020
 */
@Getter
public enum TicketTypeEnum {

    DIRECT(0, "直供单"),
    /**
     * 标准订单
     */
    ORDINARY(1, "标准订单"),
    /**
     * 非标准订单
     */
    UNORDINARY(2, "非标准订单"),

    /**
     * 订货计划单
     */
    PLAN(3, "订货计划单");

    /**
     * 枚举值
     */
    private Integer value;
    /**
     * 描述
     */
    private String desc;

    public static TicketTypeEnum getBuyerTypeEnum(Integer value) {
        for (TicketTypeEnum e : values()) {
            if (value.equals(e.value)) {
                return e;
            }
        }
        return null;
    }

    private TicketTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
