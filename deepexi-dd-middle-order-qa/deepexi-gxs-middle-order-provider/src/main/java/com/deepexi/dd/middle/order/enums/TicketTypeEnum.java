package com.deepexi.dd.middle.order.enums;

public enum TicketTypeEnum {
    LOGISTICS("logistics","物流配送");
    private String value;
    private String desc;
    TicketTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
