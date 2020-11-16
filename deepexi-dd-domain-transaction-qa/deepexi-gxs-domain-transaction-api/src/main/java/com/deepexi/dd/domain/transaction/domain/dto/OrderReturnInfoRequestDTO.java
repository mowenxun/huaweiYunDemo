package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import com.deepexi.dd.domain.transaction.domain.dto.add.OrderReturnItemAddRequestDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-06-30 14:10
 */
@Data
public class OrderReturnInfoRequestDTO extends TenantDTO implements Serializable {
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id",required = true)
    @NotNull(message = "客户id为空")
    private Long buyerId;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)",required = true)
    @NotNull(message = "单据类型为空")
    private Integer ticketType;
    /**
     * 入库仓库
     */
    @ApiModelProperty(value = "入库仓库",required = true)
    @NotNull(message = "入库仓库为空")
    private Long toStorehouse;
    /**
     * 经手人
     */
    @ApiModelProperty(value = "经手人")
    private Long handler;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期",required = true)
    @NotNull(message = "单据日期为空")
    private Date ticketDate;
    /**
     * 收货日期
     */
    @ApiModelProperty(value = "收货日期")
    private Date expectDate;
    /**
     * 退货方式。0：按单退货。1：按商品退货。
     */
    @ApiModelProperty(value = "退货方式。0：按单退货。1：按商品退货。",required = true)
    @NotNull(message = "退货方式为空")
    private Integer returnMode;
    /**
     * 原销售订单ID
     */
    @ApiModelProperty(value = "原销售订单ID")
    private Long saleOrderId;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型。0草稿，1提交")
    private Integer type;


    @ApiModelProperty(value = "退货明细列表")
    private List<OrderReturnItemAddRequestDTO> items;

    @ApiModelProperty(value = "订单费用信息列表")
    private List<OrderExpenseInfoRequestDTO> orderExpenseInfo;
}
