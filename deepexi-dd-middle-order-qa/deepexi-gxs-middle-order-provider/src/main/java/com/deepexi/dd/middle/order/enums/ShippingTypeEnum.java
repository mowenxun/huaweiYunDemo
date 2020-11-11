package com.deepexi.dd.middle.order.enums;

import lombok.Getter;

@Getter
public enum ShippingTypeEnum {
    LOGISTICS("logistics","物流配送");
    private String value;
    private String desc;
    ShippingTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
