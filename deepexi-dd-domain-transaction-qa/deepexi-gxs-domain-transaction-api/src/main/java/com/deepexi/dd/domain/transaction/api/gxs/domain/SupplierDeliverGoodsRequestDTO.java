package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商发货入参
 * @author huanghuai
 * @date 2020/10/13
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
public class SupplierDeliverGoodsRequestDTO extends AppIdDTO {
    private static final long serialVersionUID = 8637690224194187918L;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @NotBlank(message = "订单编号不能为空")
    private String orderCode;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不能为空")
    private Long orderId;
    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    @NotNull(message = "发货时间不能为空")
    private Date sendTime;
    /**
     * 发货方式
     */
    @ApiModelProperty(value = "发货方式'0-干线快运;1-零担物流；2-快递", example = "0")
    @NotBlank(message = "发货方式不能为空")
    private String sendType;
    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    @NotBlank(message = "收货地址不能为空")
    private String receiveAddress;
    /**
     * 物流公司
     */
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;
    /**
     * 物流单号
     */
    @ApiModelProperty(value = "物流单号")
    private String logisticsCode;
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String licensePlateNumber;

    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    private String driverName;
    /**
     * 司机电话
     */
    @ApiModelProperty(value = "司机电话")
    private String driverPhone;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "收货供销社编码:发货操作回传",notes = "列表查询时已经多传了这个字段")
    @NotBlank(message = "收货供销社编码不能为空")
    private String receiveDistributionCode;

    @ApiModelProperty(value = "收货供销社id:发货操作回传",notes = "列表查询时已经多传了这个字段")
    @NotNull(message = "收货供销社id不能为空")
    private Long receiveDistributionId;

    @ApiModelProperty(value = "收货供销社名称:发货操作回传",notes = "列表查询时已经多传了这个字段")
    @NotBlank(message = "收货供销社名称不能为空")
    private String receiveDistributionName;

    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    @NotNull(message = "供货商ID不能为空")
    private Long sellerId;
    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    @NotBlank(message = "供货商名称不能为空")
    private String sellerName;

    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    @NotBlank(message = "供货商编码不能为空")
    private String sellerCode;

    @ApiModelProperty(value = "订单金额")
    @NotNull(message = "订单金额不能为空")
    private BigDecimal totalAmount;
    /**
     * 来自门店订单
     */
    @ApiModelProperty(value = "来自门店订单")
    @NotBlank(message = "来自门店订单不能为空: 由列表展示回传")
    private String shopOrderCodes;

    /**
     * 货运部
     */
    @ApiModelProperty(value = "货运部")
    private String department;
}