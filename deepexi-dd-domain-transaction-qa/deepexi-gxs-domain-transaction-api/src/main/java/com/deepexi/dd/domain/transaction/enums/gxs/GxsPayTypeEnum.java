package com.deepexi.dd.domain.transaction.enums.gxs;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/21/17:59
 * @Description:
 */
public enum GxsPayTypeEnum {

    OFFLINE(1, "线下支付"),
    ONLINE(2, "线上付款"),
    CREDIT(3, "信用支付"),
    AMOUNT(4, "余额支付");

    private Integer code;
    private String msg;

    private GxsPayTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
