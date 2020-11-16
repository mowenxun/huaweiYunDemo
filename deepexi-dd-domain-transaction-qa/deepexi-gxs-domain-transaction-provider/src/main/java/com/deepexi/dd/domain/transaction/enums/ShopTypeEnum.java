package com.deepexi.dd.domain.transaction.enums;

public enum ShopTypeEnum {
    /**
     * 直供门店
     */
    STORE(1L),

    /**
     * 直供活动门店
     */
    ACTIVITIES_STORE(3L),
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
