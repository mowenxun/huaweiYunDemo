package com.deepexi.dd.middle.order.domain.dto;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SaleDeliveryPlanItemDTO
 *
 * @author admin
 * @date Thu Aug 13 16:42:15 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryPlanItemDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * APP标识
     */
    private Long appId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0：未逻辑删除状态。1:删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 发货计划ID
     */
    private Long saleDeliveryPlanId;

    /**
     * 发货计划编号
     */
    private String saleDeliveryPlanCode;

    /**
     * 提货订单id
     */
    private Long pickGoodsInfoId;

    /**
     * 提货单编号
     */
    private Long pickGoodsOrderId;

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 客户编号
     */
    private String customerCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 主图地址
     */
    private String majorPicture;

    /**
     * 商品ID
     */
    private Long skuId;

    /**
     * 商品编号
     */
    private String skuCode;

    /**
     * 商品规格
     */
    private String skuFormat;

    /**
     * 单价
     */
    private BigDecimal skuPrice;

    /**
     * 计划数量
     */
    private Long planQuantity;

    /**
     * 特价价格
     */
    private BigDecimal specialPrice;

    /**
     * 特价价格编号
     */
    private String specialPriceCode;

    /**
     * 仓库编号
     */
    private Long warehouseCode;

    /**
     * 部门
     */
    private Long department;

    /**
     * ZT/WQ
     */
    private Long ztWq;

    @ApiModelProperty(value = "商品名称")
    private String skuName;
}

