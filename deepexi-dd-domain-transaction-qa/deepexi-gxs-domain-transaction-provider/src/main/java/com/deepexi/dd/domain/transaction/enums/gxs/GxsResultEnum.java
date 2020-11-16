package com.deepexi.dd.domain.transaction.enums.gxs;


import com.deepexi.util.constant.BaseEnumType;

/**
 * Created by donh on 2019/1/8.
 */
public enum GxsResultEnum implements BaseEnumType {

    /**
     * 公共请求
     */
    UNKNOWN_ERROR("500", "系统出异常啦!请联系管理员!!!"),
    SUCCESS("200", "success"),
    REQUEST_PARA_ERRO("10001", "请求参数错误"),
    REQUEST_PARA_IS_NULL("10003", "请求id不能为空"),
    NOT_FOUND_SHOP("10008", "根据组织id找不到供销社（店铺）信息"),
    NOT_FOUND_SHOP_BY_ID("10009", "根据店铺id获得不到对应的供销社"),
    SKU_PRICE_NOT_FOUND("10010", "查询不到商品的售卖价格"),
    SKU_PRICE_NOT_SAME("10011", "商品售卖价格不一致"),
    ORDER_TOTAL_AMOUNT_NOT_SAME("10012", "订单总金额计算错误"),
    NOT_DATA("10015", "没有数据可导出"),
    COMMODITY_NOT_SHOP("10015", "订单中有未上架的商品，不能提交"),
    SUPPLIER_ORDER_NOT_EXITS("10016", "已分发订单不存再"),
    SUPPLIER_SHOP_ORDER_NOT_EXITS("10017", "根据已分发订单找不到对应的店铺订单"),
    SUPPLIER_ID_NOT_EXITS("10018", "已分发订单id不能为空"),
    INVOICE_ID_NOT_EXITS("10019", "发票信息Id不能为空"),
    ITEM_ID_NOT_EXITS("10020", "明细id不能为空"),
    GROUP_ID_NOT_SUPPLIER("20005", "根据组织id查询不到对应的供应商"),
    SUP_SHOPORDER_NOT_FOUND("10002", "该分发订单找不到对应的店铺订单，请确认分发订单是否正确");

    private String code;

    private String msg;

    GxsResultEnum(String code, String msg) {
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