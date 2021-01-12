package com.deepexi.dd.system.mall.domain.gxs;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Data
@ApiModel
public class ExportListSupplierOrderResponseDTO extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = -409259186063825437L;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",dataType = "String")
    @ExcelProperty(value = "订单编号")
    private String orderCode;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称",dataType = "String")
    @ExcelProperty(value = "客户名称")
    private String customerName;
    /**
     * 订单金额，元
     */
    @ApiModelProperty(value = "订单金额，元",dataType = "String")
    @ExcelProperty(value = "订单金额")
    private BigDecimal totalAmount;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量",dataType = "String")
    @ExcelProperty(value = "数量")
    private Long quantity;
    /**
     * 订单日期（就是创建时间）
     */
    @ApiModelProperty(value = "订单日期：使用创建时间",dataType = "String")
    @ExcelProperty(value = "订单日期")
    private Date createdTime;
    /**
     * 来自门店订单
     */
    @ApiModelProperty(value = "来自门店订单",dataType = "String")
    @ExcelProperty(value = "来自门店订单")
    private String shopOrderCodes;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:1待接单,2待发货,3已发货,4已签收,5已拒单,6已撤销 ",example = "2",dataType = "String")
    @ExcelProperty(value = "订单状态")
    private String status;

    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 1待付款,2部分付款,3已付清",example = "1",dataType = "Integer")
    @ExcelProperty(value = "支付状态")
    private String paymentStatus;
    /**
     * 收货组织（导出使用）
     */
    @ApiModelProperty(value = "收货供销社",example = "草海镇供销社",dataType = "String")
    @ExcelProperty(value = "收货供销社")
    private String receiveDistributionName;

}