package com.deepexi.dd.domain.transaction.enums;

import lombok.Getter;

/**
 * 店铺订单平台端状态
 *
 * @author yuanzaishun
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Getter
public enum ShopOrderPlatStatusEnum {
    PAYMENT("1","待付款"),
    DISTRIBUTE("2","待分发"),
    OVER_DISTRIBUTE("3","已分发"),
    DELIVER("4","待发货"),
    ALREADY_DELIVER("5","已发货"),
    REPLACE_SIGN("6","代签收"),
    OVER("7","已完成"),
    CANCEL("8","已取消"),
    REFUSE("9","已拒单");
    private String value;
    private String desc;

    public static ShopOrderPlatStatusEnum getOrderStatusEnum(String value){
        for(ShopOrderPlatStatusEnum e:values()){
            if(value.equals(e.value)){
                return e;
            }
        }
        return null;
    }

    private ShopOrderPlatStatusEnum(String value, String desc){
        this.value=value;
        this.desc=desc;
    }

    public String toString() {
        return "["+value+"("+desc+")"+"]";
    }
}
