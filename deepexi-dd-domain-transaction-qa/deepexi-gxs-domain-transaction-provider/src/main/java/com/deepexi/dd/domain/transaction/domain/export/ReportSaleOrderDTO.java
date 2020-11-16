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
public class ReportSaleOrderDTO extends BaseRowModel implements Serializable {
    @ApiModelProperty(value = "id", dataType = "Long")
    private Long id;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "单据编号", dataType = "String")
    @ExcelProperty(value = "单据编号")
    private String code;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期", dataType = "Date")
    @ExcelProperty(value = "下单时间")
    private String ticketDate;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "订单类型", dataType = "String")
    @ExcelProperty(value = "订单类型")
    private String ticketType;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", dataType = "String")
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 下单类型:(agent:代客下单,mall:h5商城下单)
     */
    @ApiModelProperty(value = "订单来源:(agent:代客下单,mall:h5商城下单)", dataType = "String")
    @ExcelProperty(value = "订单来源")
    private String buyerType;

    /**
     * 订单状态:从工具域获取
     */
    @ApiModelProperty(value = "订单状态:从工具域获取", dataType = "Integer")
    @ExcelProperty(value = "订单状态")
    private String status;


    @ApiModelProperty(value = "制单人", dataType = "String")
    @ExcelProperty(value = "制单人")
    private String handlerName;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value = "已支付金额")
    @ExcelProperty(value = "已付金额")
    private BigDecimal payAmount;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额")
    @ExcelProperty(value = "订单金额")
    private BigDecimal totalAmount;

    @Tolerate
    public ReportSaleOrderDTO() {
    }
}
