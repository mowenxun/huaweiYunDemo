package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情返回对象
 *
 * @author yuanzaishun
 * @date Wed Jun 24 11:00:03 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderInfoAppResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID",dataType = "Long")
    private Long id;


    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号",dataType = "String")
    private String code;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消",dataType = "String")
    private String status;


    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期",dataType = "Date")
    private Date ticketDate;

    /**
     * 销售总数量
     */
    @ApiModelProperty(value = "销售总数量",dataType = "Long")
    private Long quantity;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额",dataType = "BigDecimal")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "商品明细列表")
    private List<SaleOrderItemAppResponseDTO> items;

    @ApiModelProperty(value = "操作按钮")
    private List<OrderActionAppResponseDTO> actions;

    @ApiModelProperty(value = "出库单列表")
    private List<SaleOutTaskAppResponseDTO> saleOutTaskList;
}

