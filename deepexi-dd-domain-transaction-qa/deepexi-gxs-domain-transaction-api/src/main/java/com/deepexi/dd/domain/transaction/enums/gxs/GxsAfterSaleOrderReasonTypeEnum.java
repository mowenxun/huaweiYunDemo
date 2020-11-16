package com.deepexi.dd.domain.transaction.enums.gxs;


import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 供销社  after_sale_orde 表 reason_type字段枚举   售后单 售后原因类型 枚举  '售后原因类型：0-质量问题；1-其他
 *
 * @author huanghuai
 * @version v1.0.0
 * @date 2020/10/13 16:04
 */
@Getter
public enum GxsAfterSaleOrderReasonTypeEnum {

    /**
     * 质量问题
     */
    QUALITY_PROBLEM("0", "质量问题", "quality_problem", "质量问题"),
    /**
     * 其他
     */
    OTHER_PROBLEM("1", "其他", "other_problem", "其他问题"),

    ;


    private final String code;
    private final String chineseName;
    private final String englishName;
    private final String desc;

    GxsAfterSaleOrderReasonTypeEnum(String code, String chineseName, String englishName, String desc) {
        this.code = code;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static GxsAfterSaleOrderReasonTypeEnum fromCode(String code) {
        GxsAfterSaleOrderReasonTypeEnum[] values = values();
        for (GxsAfterSaleOrderReasonTypeEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum code : " + code + " of "
                + GxsAfterSaleOrderReasonTypeEnum.class.getName());
    }

    public static GxsAfterSaleOrderReasonTypeEnum fromCnName(String chineseName) {
        GxsAfterSaleOrderReasonTypeEnum[] values = values();
        for (GxsAfterSaleOrderReasonTypeEnum value : values) {
            if (value.chineseName.equals(chineseName)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can not find enum chineseName: " + chineseName + " of "
                + GxsAfterSaleOrderReasonTypeEnum.class.getName());
    }

    static Map<String, GxsAfterSaleOrderReasonTypeEnum> codeToEnum
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
            throw new IllegalArgumentException(msg + "[售后原因类型]正确枚举范围：【" + simpleString + "】实际值：" + code);
        }
    }

    public static void throwIfCodeNotExist(String code) {
        throwIfCodeNotExist(code, "");
    }
}