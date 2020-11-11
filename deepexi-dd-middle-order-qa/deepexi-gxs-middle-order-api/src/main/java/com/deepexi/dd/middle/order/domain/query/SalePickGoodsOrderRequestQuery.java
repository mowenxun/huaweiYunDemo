package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* SalePickGoodsOrderQuery
*
* @author admin
* @date Wed Aug 12 22:17:35 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsOrderRequestQuery")
public class SalePickGoodsOrderRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * APP标识
    */
    @ApiModelProperty(value = "APP标识")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 更新人
    */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    /**
    * 销售订单id
    */
    @ApiModelProperty(value = "销售订单id")
    private Long saleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
    * 提货单信息id
    */
    @ApiModelProperty(value = "提货单信息id")
    private Long pickGoodsInfoId;
    /**
    * 提货单信息id数组
    */
    @ApiModelProperty(value = "提货单信息id数组")
    private List<Long> pickGoodsInfoIdList;
    /**
    * 单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)
    */
    @ApiModelProperty(value = "单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)")
    private String orderType;
    /**
    * 商品数量
    */
    @ApiModelProperty(value = "商品数量")
    private Long goodsNumber;
    /**
    * 该订单sku金额
    */
    @ApiModelProperty(value = "该订单sku金额")
    private BigDecimal goodsMoney;
}

