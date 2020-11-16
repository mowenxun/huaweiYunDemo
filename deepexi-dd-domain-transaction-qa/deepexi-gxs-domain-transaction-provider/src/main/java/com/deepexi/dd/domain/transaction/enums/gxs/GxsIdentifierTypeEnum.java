package com.deepexi.dd.domain.transaction.enums.gxs;

import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/14/15:43
 * @Description:
 */
public enum GxsIdentifierTypeEnum {

    ORDER("domain_order", "MD", 6, "店铺订单"),
    SUPPLIER_ORDER("domain_supplier_order", "XSD", 6, "已分发订单"),

    /*ORDER_ITEM("domain_order_item", "ITEM", 8, "商品明细编号"),
    ORDERRETURN("domain_order_return", "XSTH", 8, "退货单"),
    ORDER_OUT_CODE("domain_out_code", "CKD", 6, "销售出库单"),
    FINANCE_COLLECTION_CODE("finance_collection_code", "SKD", 6, "收款单"),
    FINANCE_PAYMENT_RECORD_CODE("finance_payment_record_code", "ZF", 6, "支付流水号"),
    FINANCE_BANK("finance_bank_code", "ZH", 6, "银行账户编号"),
    CUSTOMER_CONTRACT_CODE("domain_customer_contract_code", "HT", 8, "合同编号"),
    CUSTOMER_PROJECT_CODE("domain_customer_project_code", "XM", 8, "项目编码"),
    REFUND_ORDER_CODE("domain_refund_order_code", "TK", 5, "退款编码"),
    AMOUNT_CODE("domain_amount_code", "YELS", 6, "余额流水"),
    CREDIT_CODE("domain_credit_code", "XYLS", 6, "信用流水"),
    REFUND_CODE("domain_refund_code", "TKZF", 5, "信用流水"),
    CREDIT_ADJUST_CODE("domain_credit_code", "XYBG", 6, "信用变更编号"),
    AMOUNT_RECHARGE_CODE("amount_recharge_code", "CZ", 6, "余额充值单号"),*/
    CREDIT_BILL_CODE("credit_bill_code", "ZD", 5, "信用账单编号");

    private String type;
    private String prefix;
    private int len;
    private String desc;

    private GxsIdentifierTypeEnum(String type, String prefix, int len, String desc) {
        this.type = type;
        this.prefix = prefix;
        this.len = len;
        this.desc = desc;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public int getLen() {
        return this.len;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getType() {
        return this.type;
    }

    public static GxsIdentifierTypeEnum getIdentifierTypeEnum(String type) {
        GxsIdentifierTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            GxsIdentifierTypeEnum e = var1[var3];
            if (e.getType().equals(type)) {
                return e;
            }
        }

        return null;
    }
}
