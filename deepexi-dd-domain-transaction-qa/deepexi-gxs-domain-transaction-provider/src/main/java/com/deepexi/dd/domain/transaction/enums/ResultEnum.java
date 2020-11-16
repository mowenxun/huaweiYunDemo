package com.deepexi.dd.domain.transaction.enums;


import com.deepexi.util.constant.BaseEnumType;
import org.springframework.stereotype.Component;

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
    COMMODITY_LOCK_STOCK_ERROR("61004", "商品锁定库存失败！"),
    COMMODITY_UNLOCK_STOCK_ERROR("61005", "商品增加已售库存失败！"),
    COMMODITY_ADD_STOCK_ERROR("61006", "商品增加可售库存失败！"),
    ORDER_CREATE_ERROR("61007", "订单创建失败！"),
    TOKEN_NOT_FOUND("20001","Token不存在"),
    TENANT_NOT_FOUND("20002","租户不存在"),
    ENTERPRISE_ID_NOT_FOUND("20003","企业ID不存在"),
    USERNAME_NOT_FOUND("20004","用户名不存在"),
    REQUEST_ID_NOT_FOUND("20005","请求ID不存在"),
    USER_NOT_ACTIVITY("20006","用户被禁用"),
    BUYTYPE_ERROR("60004","下单类型错误"),
    ORDER_ORGID_ERROR("60007","下单类型错误,一个订单中只能存在一个供应商"),
    ORDER_SUPPLIER_ERROR("60008","商品供应商错误"),
    ORDER_QUERY_ERROR("60009","订单查询失败"),
    TICKETTYPE_ERROR("60005","单据类型错误"),
    STOCK_ERROR("60006","商品库存不足"),
    ORDER_NOT_FOUND("60003","订单不存在"),
    ORDER_IS_CANCELLED("60004","选择的订单已被取消"),
    ORDER_IS_RETURNING("60005","选择的订单已经发起退货操作"),
    ORDER_ITEM_NOT_FOUNT("60006","商品明细为空"),
    ORDER_ITEM_NOT_AUTH("60010","订单商品未被授权"),
    SHOPPING_CART_DELETE_ERROR_COMMODITY_NOT_EXISTS("61004", "删除失败，购物车不存在商品信息！"),
    PENDING_ORDERS("60011","当前订单状态不是待接单"),
    PAY_RECORD_ERROR("60012","支付记录已经确认"),
    PAY_RECORD_FAIL("60013","取消订单金额失败"),
    COLLECTION_RECORD_FAIL("60014","按单付款金额失败"),
    COMMODITY_NULL_ERROR("60015","未选择任何商品"),
    COMMODITY_SHUTDOWN_ERROR("60016","商品已下架"),
    ORDER_ID_IS_NULL("60017","ID不能为空"),
    ORDER_STATUS_IS_NOT_DRAFT("60018","订单只有在待接单状态下才能删除"),
    ToBeReviewed_ORDERS("60021","当前状态不是待审核状态"),
    ORDER_RELATION_IS_NULL("60019","提货单查询不到订单关系记录"),
    ORDER_SKU_IS_NULL("60020","SKU明细不存在"),
    CODE_ERROR("60030","编码重复"),
    INSERT_ERROR("60031","新增失败"),
    REFUND_IS_NULL("60032","查询退款单为空"),
    REFUND_SKU_IS_NULL("60032","查询退款单明细为空"),
    CURRENT_USER_NOT_PARTNER("60033","当前用户没有所属业务伙伴"),
    ORDER_CUSTOMER_ID_IS_NULL("60034","提货单的客户id为空"),
    ORDER_CUSTOMER_IS_NULL("60034","提货单的客户不存在"),
    ORDER_SELLER_IS_NULL("60034","卖家信息不存在"),
    NOT_FOUND_STORE("60034","找不到对应店铺信息"),
    NOT_FOUND_ACTITIES("60034","找不到对应活动商品"),
    MORE_SHOPPING_NUMBER("60035","购买的商品超过限购数量"),
    ACTIVITIES_NOT_COMMODITY("60036","活动库存不足!"),
    NOT_FOUND_ORG("60036","找不到订单对应的一级组织"),
    CUSTOMER_NOT_AUTHRISE("60037","该客户没有商品权限"),
    PLAN_STATUS_NOT_ToTakeOrder("60038","订单只有在待接单状态或者已驳回状态下才能修改提交订单"),
    GROUP_ID_NOT_SUPPLIER("20005", "根据组织id查询不到对应的供应商"),
    BUSINESS_HTTP_ERROR("60039","查询确定等级sku价格失败");

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