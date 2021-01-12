package com.deepexi.dd.system.mall.domain.dto.order.plan;

import com.deepexi.dd.system.mall.domain.dto.OrderConsigneeInfoAddRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.OrderExpenseInfoRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.OrderInvoiceInfoRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.add.SaleOrderItemAddRequestDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售订单添加和编辑对象
 *
 * @author admin
 * @version 1.0
 * @date Wed Jun 24 11:00:03 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "销售订货计划订单添加和编辑对象")
public class SaleOrderInfoPlanAddRequestDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 下单类型:(agent:代客下单,mall:商城下单)
     */
    @ApiModelProperty(value = "下单类型:(ValetOrder:代客下单,OnlineOrder:商城下单),当前默认填OnlineOrder", required = true)
    @NotEmpty(message = "下单类型为空")
    private String buyerType;

    /**
     * 订单类型,0直供订单,1标准订单
     */
    @ApiModelProperty(value = "订单类型,0直供订单,1标准订单，2非标准订单，3订货计划单", required = true)
    @NotNull(message = "订单类型为空")
    private Integer ticketType;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额")
    private BigDecimal totalAmount;


    @ApiModelProperty(value = "操作类型:1提交，订货计划单没有草稿状态，值默认为1", required = true)
    private Integer type = 1;

    @ApiModelProperty(value = "商品明细列表,必填", required = true)
    @Size(min = 1, message = "未选择商品信息")
    private List<SaleOrderItemAddRequestDTO> items;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    @ApiModelProperty(value = "产品线id")
    private Long productId;

    @ApiModelProperty(value = "计划月份")
    private String planMonth;

    @ApiModelProperty(value = "产品线名称")
    private String productName;

    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id")
    private Long sellerId;
    /**
     * 供应商名称
     */
    @ApiModelProperty(value = "供应商名称")
    private String sellerName;
/*
    *//**
     * 发货仓库
     *//*
    @ApiModelProperty(value = "发货仓库", required = true)
    @NotEmpty(message = "发货仓库为空")
    private Long fromStorehouse;*/
}
