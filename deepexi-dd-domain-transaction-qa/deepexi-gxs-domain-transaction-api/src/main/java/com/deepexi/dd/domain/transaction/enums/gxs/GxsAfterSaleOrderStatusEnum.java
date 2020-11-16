package com.deepexi.dd.domain.transaction.enums.gxs;


import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 供销社 after_sale_orde 表  status 枚举  售后单状态 <br> 1待审核 2审核通过 3驳回 4已完成',
 *
 * @author huanghuai
 * @version v1.0.0
 * @date 2020/10/13 16:04
 */
@Getter
public enum GxsAfterSaleOrderStatusEnum {

    /**
     * 待审核
     */
    TO_BE_REVIEWED("1", "待审核", "to_be_reviewed", "待审核"),
    /**
     * 审核通过
     */
    APPROVED("2", "审核通过", "approved", "审核通过"),
    /**
     * 驳回
     */
    REJECTED("3", "驳回", "rejected", "驳回"),
    /**
     * 已完成
     */
    COMPLETED("4", "已完成", "completed", "已完成"),

    ;


    private final String code;
    private final String chineseName;
    private final String englishName;
    private final String desc;

    GxsAfterSaleOrderStatusEnum(String code, String chineseName, String englishName, String desc) {
        this.code = code;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static GxsAfterSaleOrderStatusEnum fromCode(String code) {
        GxsAfterSaleOrderStatusEnum[] values = values();
        for (GxsAfterSaleOrderStatusEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum code : " + code + " of "
                + GxsAfterSaleOrderStatusEnum.class.getName());
    }

    public static GxsAfterSaleOrderStatusEnum fromCnName(String chineseName) {
        GxsAfterSaleOrderStatusEnum[] values = values();
        for (GxsAfterSaleOrderStatusEnum value : values) {
            if (value.chineseName.equals(chineseName)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum chineseName: " + chineseName + " of "
                + GxsAfterSaleOrderStatusEnum.class.getName());
    }


    static Map<String, GxsAfterSaleOrderStatusEnum> codeToEnum
            = Arrays.stream(values()).collect(Collectors.toMap(e -> e.getCode(), e -> e));

    public static boolean containsCode(String code) {
        return codeToEnum.containsKey(code);
    }

    static String simpleString = Arrays.stream(values()).map(e -> e.getCode() + "(" + e.getChineseName() + ")").collect(Collectors.joining(","));

    public static String toSimpleString() {
        return simpleString;
    }

    public static void throwIfCodeNotExist(String code, String msg) {
        if (!containsCode(code)) {
            throw new IllegalArgumentException(msg + "[售后单状态枚举]正确枚举范围：【" + simpleString + "】实际值：" + code);
        }
    }
    public static void throwIfCodeNotExist(String code) {
        throwIfCodeNotExist(code, "");
    }

}