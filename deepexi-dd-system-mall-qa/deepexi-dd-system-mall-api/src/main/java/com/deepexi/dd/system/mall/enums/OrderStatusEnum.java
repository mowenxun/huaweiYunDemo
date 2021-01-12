package com.deepexi.dd.system.mall.enums;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderStatusEnum {
    DRAFT("draft","草稿"),
    ACCEPT("accept","待接单"),
    DELIVER("deliver","待发货"),
    RECEIPT("receipt","待收货"),
    EXAMINING("examining","接单审核中"),
    REJECT("reject","接单被驳回" ),
    PAYMENT("payment","待付款"),
    PAID("paid","收款待确认"),
    OVER("over","交易完成"),
    CANCEL("cancel","已取消");
    private String value;
    private String desc;

    public static OrderStatusEnum getOrderStatusEnum(String value){
        for(OrderStatusEnum e:values()){
            if(value.equals(e.value)){
                return e;
            }
        }
        return null;
    }

    private OrderStatusEnum(String value,String desc){
        this.value=value;
        this.desc=desc;
    }
}
