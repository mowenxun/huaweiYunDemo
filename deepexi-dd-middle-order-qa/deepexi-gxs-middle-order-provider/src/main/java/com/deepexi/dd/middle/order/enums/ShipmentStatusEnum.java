package com.deepexi.dd.middle.order.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 */
@Getter
public enum ShipmentStatusEnum {

    UNSHIPMENT(0,"未发货"),
    SHIPMENTED(1,"已发货");
    private Integer value;
    private String desc;
    ShipmentStatusEnum(Integer value, String desc){
        this.value=value;
        this.desc=desc;
    }
}
