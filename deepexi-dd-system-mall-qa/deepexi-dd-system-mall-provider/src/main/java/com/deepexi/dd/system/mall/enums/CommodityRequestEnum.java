package com.deepexi.dd.system.mall.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/20/17:20
 * @Description:
 */
@Getter
public enum CommodityRequestEnum {

    /**
     * 订单修改
     */
    PLAN_ORDER_ADD(0, "订货单新增商品选择"),
    ORDER_UPDATE(1, "订单修改商品选择"),
    ;

    private Integer type;
    private String description;

    CommodityRequestEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public static CommodityRequestEnum getByType(Integer type) {
        if (Objects.isNull(type)) {
            return null;
        }
        for (CommodityRequestEnum commodityRequestEnum : CommodityRequestEnum.values()) {
            if (Objects.equals(commodityRequestEnum.getType(), type)) {
                return commodityRequestEnum;
            }
        }
        return null;
    }
}
