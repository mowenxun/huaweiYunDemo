package com.deepexi.dd.system.mall.enums;

import lombok.Getter;

@Getter
public enum BuyerTypeEnum {
    DRAFT("agent","代客下单"),
    ACCEPT("mall","APP或H5在线订购");

    private String value;
    private String desc;

    public static BuyerTypeEnum getBuyerTypeEnum(String value){
        for(BuyerTypeEnum e:values()){
            if(value.equals(e.value)){
                return e;
            }
        }
        return null;
    }

    private BuyerTypeEnum(String value,String desc){
        this.value=value;
        this.desc=desc;
    }
}
