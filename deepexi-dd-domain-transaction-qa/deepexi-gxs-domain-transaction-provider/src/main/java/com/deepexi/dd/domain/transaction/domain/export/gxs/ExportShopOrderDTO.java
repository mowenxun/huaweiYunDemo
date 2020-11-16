package com.deepexi.dd.domain.transaction.domain.export.gxs;

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
public class ExportShopOrderDTO extends BaseRowModel implements Serializable {
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", dataType = "String")
    @ExcelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "门店名称", dataType = "String")
    @ExcelProperty(value = "门店名称")
    private String shopName;


    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商", dataType = "String")
    @ExcelProperty(value = "供应商")
    private String sellerName;

    /**
     * 提货供销社
     */
    @ApiModelProperty(value = "提货供销社", dataType = "String")
    @ExcelProperty(value = "提货供销社")
    private String deliveryDistributionName;


    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额", dataType = "BigDecimal")
    @ExcelProperty(value = "订单金额")
    private BigDecimal totalAmount;


    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", dataType = "Long")
    @ExcelProperty(value = "数量")
    private Long quantity;


    /**
     * 订单日期
     */
    @ApiModelProperty(value = "订单日期")
    @ExcelProperty(value = "订单日期")
    private String createdTime;


    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态", dataType = "Long")
    @ExcelProperty(value = "订单状态")
    private String statusName;


    /**
     * 关联分发订单号
     */
    @ApiModelProperty(value = "关联分发订单号", dataType = "String")
    @ExcelProperty(value = "关联分发订单号")
    private String supplierOrderCode;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人", dataType = "String")
    @ExcelProperty(value = "收货人")
    private String consignee;


    /**
     * 收货电话
     */
    @ApiModelProperty(value = "收货电话", dataType = "String")
    @ExcelProperty(value = "收货电话")
    private String mobile;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址", dataType = "String")
    @ExcelProperty(value = "收货地址")
    private String address;

    @Tolerate
    public ExportShopOrderDTO() {
    }

}
