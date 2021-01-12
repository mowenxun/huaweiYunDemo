package com.deepexi.dd.system.mall.enums;


import com.deepexi.util.constant.BaseEnumType;

/**
 * 订单模块，业务编码区间： DD-60001~69999
 * Created by donh on 2019/1/8.
 */
public enum GxsSysResultEnum implements BaseEnumType {

    /**
     * 公共请求
     */
    UNKNOWN_ERROR("500", "系统出异常啦!请联系管理员!!!"),
    SUCCESS("200", "success"),


    TOKEN_NOT_FOUND("20001", "Token不存在"),
    SHOP_ID_MATCH("20002", "前后端店铺Id不一致"),
    SHOP_FINACE_NOT_EXIST("20003", "门店没有设置信用额度，请先去设置再进行支付"),
    SUPPLIER_ID_NOT_EXIST("20004", "根据供应商id查询不到对应的供应商信息"),
    GROUP_ID_NOT_SUPPLIER("20005", "根据组织id查询不到对应的供应商"),
    COMMODITY_SOLD_OUT("10010", "商品已下架");

    private String code;

    private String msg;

    GxsSysResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}