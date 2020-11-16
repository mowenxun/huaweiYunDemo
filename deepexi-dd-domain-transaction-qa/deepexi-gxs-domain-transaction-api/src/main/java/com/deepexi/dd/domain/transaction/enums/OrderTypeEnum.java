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
public enum OrderTypeEnum {

    SHOP_ORDER("0", "店铺订单"),
    /**
     * 标准订单
     */
    SUPPLIER_ORDER("1", "已分发订单");

    /**
     * 枚举值
     */
    private String value;
    /**
     * 描述
     */
    private String desc;

    public static OrderTypeEnum getBuyerTypeEnum(String value) {
        for (OrderTypeEnum e : values()) {
            if (value.equals(e.value)) {
                return e;
            }
        }
        return null;
    }

    private OrderTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
