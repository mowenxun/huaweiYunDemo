package com.deepexi.dd.domain.transaction.domain.responseDto;


import com.deepexi.dd.domain.transaction.domain.dto.OrderActionResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskInfoResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqili on 2020/7/21.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoTransactionOLResponseDTO extends AbstractTenantResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID",dataType = "Long")
    private Long id;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号",dataType = "Integer")
    private Integer version;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",dataType = "String")
    private String code;

    /**
     * 订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消
     */
    @ApiModelProperty(value = "订单状态:从业务链路获取",dataType = "Integer")
    private Integer status;

    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value="单据类型(ordinary:普通销售单)",dataType = "String")
    private Integer ticketType;


    /**
     * 单据日期
     */
    @ApiModelProperty(value="单据日期",dataType = "Date")
    private Date ticketDate;

    /**
     * 商品总数
     */
    @ApiModelProperty(value="商品总数",dataType = "Long")
    private Long quantity;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value="总商品金额")
    private BigDecimal totalAmount;

    /**
     * 卖家id
     */
    @ApiModelProperty(value="卖家id")
    private Long sellerId;


    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value="支付状态 0:未付款,1:已付款,2:部分付款",dataType = "Integer")
    private Integer paymentStatus;


    @ApiModelProperty(value = "商品明细列表")
    private List<SaleOrderItemResponseDTO> items;

    @ApiModelProperty(value = "出库单数量")
    private Integer saleOutTaskAmount;

    @ApiModelProperty(value = "出库单")
    private List<SaleOutTaskInfoResponseDTO> saleOutTaskList;

    @ApiModelProperty(value = "操作按钮")
    private List<OrderActionResponseDTO> actions;

    @ApiModelProperty(value = "支付流水code")
    private String payOrderCode;

    /**
     * 已支付金额
     */
    @ApiModelProperty(value="已支付金额",dataType = "BigDecimal")
    private BigDecimal payAmount;

    /**
     * 可退金额
     */
    @ApiModelProperty(value="可退金额",dataType = "BigDecimal")
    private BigDecimal refundableAmount;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value="供货商名称",dataType = "String")
    private String sellerName;
}
