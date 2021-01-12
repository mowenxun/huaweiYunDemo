package com.deepexi.dd.system.mall.enums;


import com.deepexi.util.constant.BaseEnumType;

/**
 * 订单模块，业务编码区间： DD-60001~69999
 * Created by donh on 2019/1/8.
 */
public enum ResultEnum implements BaseEnumType {

    /**
     * 公共请求
     */
    UNKNOWN_ERROR("500", "系统出异常啦!请联系管理员!!!"),
    SUCCESS("200", "success"),

    STOCK_NOT_ENOUGH("61000", "商品库存不足！"),
    COMMODITY_STATUS_ERROR("61001", "商品未上架！"),
    COMMODITY_NOT_ENABLE("61002", "商品未启用！"),
    COMMODITY_HTTP_ERROR("61003", "商品查询异常！"),

    TOKEN_NOT_FOUND("20001","Token不存在"),
    TENANT_NOT_FOUND("20002","租户不存在"),
    ENTERPRISE_ID_NOT_FOUND("20003","企业ID不存在"),
    USERNAME_NOT_FOUND("20004","用户名不存在"),
    REQUEST_ID_NOT_FOUND("20005","请求ID不存在"),
    USER_NOT_ACTIVITY("20006","用户被禁用"),
    BUYTYPE_ERROR("60004","下单类型错误"),
    TICKETTYPE_ERROR("60005","单据类型错误"),
    STOCK_ERROR("60006","商品库存不足"),
    ORDER_NOT_FOUND("6003","订单不存在"),
    ORDER_IS_CANCELLED("6004","选择的订单已被取消"),
    ORDER_IS_RETURNING("6005","选择的订单已经发起退货操作"),
    ORDER_ITEM_NOT_FOUNT("6006","商品明细为空"),
    AFFILIATEDORGANIZATIONID("6007","查询不到组织id"),
    SHOPPING_CART_DELETE_ERROR_COMMODITY_NOT_EXISTS("61004", "删除失败，购物车不存在商品信息！"),
    COMMODITY_DIRECT_SUPPLY_IS_NULL("60008","没有获取到直供商品"),
    COMMODITY_IS_NULL("60009","没有查询到商品"),

    CONTACT_NUMBER_ERROR("200016","注册电话格式不正确."),
    COMMODITY_SOLD_OUT("10010","商品已下架")

    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
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