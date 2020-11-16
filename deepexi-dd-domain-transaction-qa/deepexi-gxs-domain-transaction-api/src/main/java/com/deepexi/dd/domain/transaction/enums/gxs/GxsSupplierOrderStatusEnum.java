package com.deepexi.dd.domain.transaction.enums.gxs;


import lombok.Getter;

/**
 * 供销社  supplier_order 表的 status字段枚举 <br>
 *
 * @author huanghuai
 * @version v1.0.0
 * @date 2020/10/13 16:04
 */
@Getter
public enum GxsSupplierOrderStatusEnum {

    /**
     * 待接单
     */
    ORDER_TO_BE_RECEIVED("1", "待接单", "order_to_be_received", "待接单"),
    /**
     * 待发货
     */
    TO_BE_DELIVERED("2", "待发货", "to_be_delivered", "待发货"),
    /**
     * 已发货
     */
    DELIVERED("3", "已发货", "delivered", "已发货"),
    /**
     * 已签收
     */
    SIGNED("4", "已签收", "signed", "已签收"),
    /**
     * 已拒单
     */
    REJECTED("5", "已拒单", "rejected", "已拒单"),
    /**
     * 已撤销
     */
    RESCINDED("6", "已撤销", "rescinded", "已撤销"),

    UNKNOWN("-1", "未知状态", "unknown", "未知状态"),

    ;


    private final String code;
    private final String chineseName;
    private final String englishName;
    private final String desc;

    GxsSupplierOrderStatusEnum(String code, String chineseName, String englishName, String desc) {
        this.code = code;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static GxsSupplierOrderStatusEnum fromCode(String code) {
        GxsSupplierOrderStatusEnum[] values = values();
        for (GxsSupplierOrderStatusEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public static GxsSupplierOrderStatusEnum fromCnName(String chineseName) {
        GxsSupplierOrderStatusEnum[] values = values();
        for (GxsSupplierOrderStatusEnum value : values) {
            if (value.chineseName.equals(chineseName)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum chineseName: " + chineseName + " of "
                + GxsSupplierOrderStatusEnum.class.getName());
    }


    @Override
    public String toString() {
        return "["+code+"("+chineseName+")"+"]";
    }
}