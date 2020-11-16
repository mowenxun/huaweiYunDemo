package com.deepexi.dd.domain.transaction.enums;

import lombok.Getter;
/**
 * 订单来源
 *
 * @author yuanzaishun
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Getter
public enum BuyerTypeEnum {
    AGENT("ValetOrder","代客下单"),
    MALL("OnlineOrder","订货商城");

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
