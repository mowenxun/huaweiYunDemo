package com.deepexi.dd.system.mall.enums;

public enum ShopTypeEnum {
    /**
     * 直供门店
     */
    STORE(1L),
    /**
     * 非直供门店
     */
    NOSTORE(2L);


    private Long type;

    ShopTypeEnum(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }
}
