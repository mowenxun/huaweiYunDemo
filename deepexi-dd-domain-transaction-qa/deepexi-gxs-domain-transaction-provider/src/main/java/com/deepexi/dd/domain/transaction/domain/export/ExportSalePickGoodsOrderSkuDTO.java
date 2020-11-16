package com.deepexi.dd.domain.transaction.domain.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.deepexi.dd.domain.transaction.domain.dto.SaleDeliveryPlanInfoResDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleDeliveryPlanItemRespDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-17 7:56
 */
@Data
@Builder
@ApiModel
public class ExportSalePickGoodsOrderSkuDTO extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 提货单编号
     */
    @ApiModelProperty(value = "提货单编号")
    @ExcelProperty(value = "提货单编号")
    private String pickGoodsCode;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @ExcelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号【暂不维护】")
    @ExcelProperty(value = "批次号")
    private String batchNumber;
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号")
    @ExcelProperty(value = "商品编号")
    private String skuCode;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String skuName;
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    @ExcelProperty(value = "商品规格")
    private String skuFormat;
    /**
     * 提货单要求发货时间
     */
    @ApiModelProperty(value = "提货单要求发货时间")
    @ExcelProperty(value = "提货单要求发货时间")
    private Date requiredTime;
    /**
     * 提货数量
     */
    @ApiModelProperty(value = "提货数量")
    @ExcelProperty(value = "提货数量")
    private Long pickNum;
    /**
     * 自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE
     */
    @ApiModelProperty(value = "自提PICK_MYSELF，外部仓库OUTER_WAREHOUSE，工地BUILD_PLACE，小库SMALL_WAREHOUSE")
    @ExcelProperty(value = "提货类型")
    private String pickGoodsWayZt;

    //2020/08/13 新加字段 SongTao 用于审批通过流程
    @ApiModelProperty(value = "仓库")
    @ExcelProperty(value = "仓库")
    private String warehouse;
    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    @ExcelProperty(value = "收货人")
    private String consignee;

    /**
     * 收货地址(工地、小库)
     */
    @ApiModelProperty(value = "收货地址(工地、小库)")
    @ExcelProperty(value = "收货地址")
    private String receiveAddress;

    @ApiModelProperty(value = "收货仓库名称(外仓)")
    @ExcelProperty(value = "外仓提货仓库")
    private String receiveHouseNameWc;

    @ApiModelProperty(value = "是否加急  YES加急   NO不加急")
    @ExcelProperty(value = "是否加急")
    private String ifEager;

    @ApiModelProperty(value = "客户编码")
    @ExcelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "客户名称")
    @ExcelProperty(value = "客户名称")
    private String customerName;

    @Tolerate
    public ExportSalePickGoodsOrderSkuDTO() {
    }

}
