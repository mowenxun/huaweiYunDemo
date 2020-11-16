package com.deepexi.dd.domain.transaction.enums;

public enum OrderOperationRecordEnum {

    SALE_ORDER(1,"订单"),

    //todo 提货单
    //

    REFUND_ORDER(3,"退货单");


    private Integer code;
    private String type;

    OrderOperationRecordEnum(Integer code,String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }
    public String getType() {
        return type;
    }
}
