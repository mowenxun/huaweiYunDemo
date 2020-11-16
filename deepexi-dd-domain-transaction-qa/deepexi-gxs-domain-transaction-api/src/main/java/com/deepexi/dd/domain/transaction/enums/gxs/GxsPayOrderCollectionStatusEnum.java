package com.deepexi.dd.domain.transaction.enums.gxs;


import lombok.Getter;

/**
 * 供销社  pay_order 表 collection_status 字段枚举<br>
 *
 * @author huanghuai
 * @version v1.0.0
 * @date 2020/10/13 16:04
 */
@Getter
public enum GxsPayOrderCollectionStatusEnum {

    /**
     * 待收款
     */
    TO_BE_RECEIVED("10", "待收款", "to_be_received", "待收款"),
    /**
     * 部分收款
     */
    PARTIAL_RECEIVED("11", "部分收款", "partial_received", "部分收款"),
    /**
     * 已收讫
     */
    RECEIVED("12", "已收讫", "received", "已收讫"),

    ;


    private final String code;
    private final String chineseName;
    private final String englishName;
    private final String desc;

    GxsPayOrderCollectionStatusEnum(String code, String chineseName, String englishName, String desc) {
        this.code = code;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static GxsPayOrderCollectionStatusEnum fromCode(String code) {
        GxsPayOrderCollectionStatusEnum[] values = values();
        for (GxsPayOrderCollectionStatusEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum code : " + code + " of "
                + GxsPayOrderCollectionStatusEnum.class.getName());
    }

    public static GxsPayOrderCollectionStatusEnum fromCnName(String chineseName) {
        GxsPayOrderCollectionStatusEnum[] values = values();
        for (GxsPayOrderCollectionStatusEnum value : values) {
            if (value.chineseName.equals(chineseName)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum chineseName: " + chineseName + " of "
                + GxsPayOrderCollectionStatusEnum.class.getName());
    }


}