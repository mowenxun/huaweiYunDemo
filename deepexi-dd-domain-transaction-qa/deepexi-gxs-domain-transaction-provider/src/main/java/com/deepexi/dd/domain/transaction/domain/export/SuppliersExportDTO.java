package com.deepexi.dd.domain.transaction.domain.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-10-15 16:10
 */
@Data
@Builder
@ApiModel
public class SuppliersExportDTO extends BaseRowModel implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    @ExcelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "供货商名称")
    @ExcelProperty(value = "供应商")
    private String sellerName;

    @ApiModelProperty(value = "收货供销社名称")
    @ExcelProperty(value = "收货供销社")
    private String receiveDistributionName;

    @ApiModelProperty(value = "总商品金额")
    @ExcelProperty(value = "订单金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "商品总数")
    @ExcelProperty(value = "数量")
    private Long quantity;

    @ApiModelProperty(value = "创建时间")
    @ExcelProperty(value = "下单日期")
    private String createdTime;

    @ApiModelProperty(value = "订单状态")
    @ExcelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "支付状态")
    @ExcelProperty(value = "支付状态")
    private String paymentStatus;

    @ApiModelProperty(value = "已分发订单关联的门店单号")
    @ExcelProperty(value = "已分发订单关联的门店单号")
    private String shopOrderCodes;

    @Tolerate
    public SuppliersExportDTO() {
    }
}
