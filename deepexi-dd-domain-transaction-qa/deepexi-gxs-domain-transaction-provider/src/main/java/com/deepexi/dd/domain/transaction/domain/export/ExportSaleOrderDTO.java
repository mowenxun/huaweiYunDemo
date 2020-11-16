package com.deepexi.dd.domain.transaction.domain.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@ApiModel
public class ExportSaleOrderDTO extends BaseRowModel implements Serializable {
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", dataType = "String")
    @ExcelProperty(value = "订单编号")
    private String code;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)", dataType = "String")
    @ExcelProperty(value = "单据类型")
    private String ticketType;

    /**
     * 下单类型:(agent:代客下单,mall:h5商城下单)
     */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:h5商城下单)", dataType = "String")
    @ExcelProperty(value = "下单类型")
    private String buyerType;

   /* *//**
     * 客户ID
     *//*
    @ApiModelProperty(value = "客户ID", dataType = "Long")
    @ExcelProperty(value = "客户编号")
    private Long buyerId;*/

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", dataType = "String")
    @ExcelProperty(value = "客户名称")
    private String buyerName;
    /**
     * 商品总数
     */
    @ApiModelProperty(value = "商品总数", dataType = "Long")
    @ExcelProperty(value = "销售总数")
    private Long quantity;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额")
    @ExcelProperty(value = "订单金额")
    private BigDecimal totalAmount;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期", dataType = "Date")
    @ExcelProperty(value = "下单日期")
    private String ticketDate;
    /**
     * 订单状态:从工具域获取
     */
    @ApiModelProperty(value = "订单状态:从工具域获取", dataType = "Integer")
    @ExcelProperty(value = "订单状态")
    private String status;
    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 0:未付款,1:已付款,2:部分付款", dataType = "Integer")
    @ExcelProperty(value = "付款状态")
    private String paymentStatus;
    /**
     * 要求送达日期
     */
    @ApiModelProperty(value = "要求送达日期", dataType = "Date")
    @ExcelProperty(value = "要求送达日期")
    private String arriveDate;
    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    @ExcelProperty(value = "收货人")
    private String consignee;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "收货人电话")
    @ExcelProperty(value = "收货人电话")
    private String mobile;
    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    @ExcelProperty(value = "收货地址")
    private String adress;
    /**
     * 签收时间
     */
    @ApiModelProperty(value = "签收时间", dataType = "Date")
    @ExcelProperty(value = "签收日期")
    private String signTime;

    @Tolerate
    public ExportSaleOrderDTO() {
    }

}
