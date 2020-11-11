package com.deepexi.dd.middle.order.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 */
@Getter
public enum PaymentStatusEnum {

    UNPAY(0,"未支付"),
    PAIED(1,"已支付"),
    PART_PAY(2,"部分支付");
    private Integer value;
    private String desc;
    PaymentStatusEnum(Integer value, String desc){
        this.value=value;
        this.desc=desc;
    }
}
