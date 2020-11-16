package com.deepexi.dd.domain.transaction.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author yuanzaishun
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Getter
public enum ShopOrderStatusEnum {
    PAYMENT("1","待付款"),
    DELIVER("2","待发货"),
    ALREADY_DELIVER("3","已发货"),
    REPLACE_SIGN("4","代签收"),
    OVER("5","已完成"),
    CANCEL("6","已取消"),
    UNKNOWN("7","未知状态");
    private String value;
    private String desc;

    public static ShopOrderStatusEnum getOrderStatusEnum(String value){
        for(ShopOrderStatusEnum e:values()){
            if(value.equals(e.value)){
                return e;
            }
        }
        return UNKNOWN;
    }

    private ShopOrderStatusEnum(String value, String desc){
        this.value=value;
        this.desc=desc;
    }

    public String toString() {
        return "["+value+"("+desc+")"+"]";
    }
}
