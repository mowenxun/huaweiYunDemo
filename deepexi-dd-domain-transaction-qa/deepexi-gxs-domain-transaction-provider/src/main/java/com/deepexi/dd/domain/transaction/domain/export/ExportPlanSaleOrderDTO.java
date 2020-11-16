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

@Data
@Builder
@ApiModel
public class ExportPlanSaleOrderDTO extends BaseRowModel implements Serializable {
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "计划订单编号", dataType = "String")
    @ExcelProperty(value = "计划订单编号")
    private String code;

    @ApiModelProperty(value = "计划月份", dataType = "String")
    @ExcelProperty(value = "计划月份")
    private String planMonth;


    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", dataType = "String")
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 客户编码
     */
    @ApiModelProperty(value = "客户编码", dataType = "String")
    @ExcelProperty(value = "客户编码")
    private String customerCode;


    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", dataType = "String")
    @ExcelProperty(value = "商品编码")
    private String skuCode;


    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", dataType = "String")
    @ExcelProperty(value = "商品名称")
    private String skuName;


    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    @ExcelProperty(value = "商品规格")
    private String skuFormat;


    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量", dataType = "Long")
    @ExcelProperty(value = "商品数量")
    private Long quantity;


    /**
     * 单价
     */
    @ApiModelProperty(value = "单价", dataType = "String")
    @ExcelProperty(value = "单价")
    private BigDecimal price;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", dataType = "String")
    @ExcelProperty(value = "金额")
    private BigDecimal totalAmount;


    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商", dataType = "String")
    @ExcelProperty(value = "供应商")
    private String sellerName;

    /**
     * 供应商编码
     */
    @ApiModelProperty(value = "供应商编码", dataType = "String")
    @ExcelProperty(value = "供应商编码")
    private String sellerCode;

    /**
     * 供应商编码
     */
    @ApiModelProperty(value = "下单时间", dataType = "String")
    @ExcelProperty(value = "下单时间")
    private String createdTime;

    @ApiModelProperty(value = "下单人", dataType = "String")
    @ExcelProperty(value = "下单人")
    private String createdBy;







    @Tolerate
    public ExportPlanSaleOrderDTO() {
    }

}
