package com.deepexi.dd.middle.order.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 */
@Getter
public enum SettlementStatusEnum {

    UNSETTLEMENT(0,"未结算"),
    SETTLEMENTED(1,"已结算"),
    PART_SETTLEMENTED(2,"部分结算");
    private Integer value;
    private String desc;
    SettlementStatusEnum(Integer value, String desc){
        this.value=value;
        this.desc=desc;
    }
}
