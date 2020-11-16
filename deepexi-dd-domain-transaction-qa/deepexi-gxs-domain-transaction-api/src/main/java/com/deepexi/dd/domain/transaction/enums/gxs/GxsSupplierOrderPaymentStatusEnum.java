package com.deepexi.dd.domain.transaction.enums.gxs;


import lombok.Getter;

/**
 * 供销社 supplier_order 表 payment_status 字段枚举<br>
 *
 * @author huanghuai
 * @version v1.0.0
 * @date 2020/10/13 16:04
 */
@Getter
public enum GxsSupplierOrderPaymentStatusEnum {

    /**
     * 待付款
     */
    TO_BE_PAID("1", "待付款", "to_be_paid", "待付款"),
    /**
     * 部分付款
     */
    PARTIAL_PAYMENT("2", "部分付款", "partial_payment", "部分付款"),
    /**
     * 已付清
     */
    PAID("3", "已付清", "paid", "已付清"),

    UNKNOWN("-1", "未知状态", "unknown", "未知状态"),

    ;


    private final String code;
    private final String chineseName;
    private final String englishName;
    private final String desc;

    GxsSupplierOrderPaymentStatusEnum(String code, String chineseName, String englishName, String desc) {
        this.code = code;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static GxsSupplierOrderPaymentStatusEnum fromCode(String code) {
        GxsSupplierOrderPaymentStatusEnum[] values = values();
        for (GxsSupplierOrderPaymentStatusEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public static GxsSupplierOrderPaymentStatusEnum fromCnName(String chineseName) {
        GxsSupplierOrderPaymentStatusEnum[] values = values();
        for (GxsSupplierOrderPaymentStatusEnum value : values) {
            if (value.chineseName.equals(chineseName)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum chineseName: " + chineseName + " of "
                + GxsSupplierOrderPaymentStatusEnum.class.getName());
    }


}