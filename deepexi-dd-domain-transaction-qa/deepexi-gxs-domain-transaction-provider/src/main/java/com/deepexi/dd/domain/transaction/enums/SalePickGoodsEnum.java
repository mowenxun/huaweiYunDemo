package com.deepexi.dd.domain.transaction.enums;



/**
 * 提货单模块枚举
 */
public enum SalePickGoodsEnum{

    /**
     * 自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE
     */
    PICK_MYSELF("PICK_MYSELF","自提"),
    OUTER_WAREHOUSE("OUTER_WAREHOUSE","外部仓库"),
    BUILD_PLACE("BUILD_PLACE","工地"),
    SMALL_WAREHOUSE("SMALL_WAREHOUSE","小库");

    private String code;
    private String msg;


    SalePickGoodsEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
}