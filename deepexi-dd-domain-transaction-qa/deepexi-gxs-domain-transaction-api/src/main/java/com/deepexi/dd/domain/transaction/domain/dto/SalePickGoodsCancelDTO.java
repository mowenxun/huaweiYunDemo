package com.deepexi.dd.domain.transaction.domain.dto;

public class SalePickGoodsCancelDTO {

    //一体化系统订单号
    private String sourceOrderNo;

    //单据业务类型，1-销售单;2-调拨单
    private Integer businessType;


    public String getSourceOrderNo() {
        return sourceOrderNo;
    }

    public void setSourceOrderNo(String sourceOrderNo) {
        this.sourceOrderNo = sourceOrderNo;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
}
