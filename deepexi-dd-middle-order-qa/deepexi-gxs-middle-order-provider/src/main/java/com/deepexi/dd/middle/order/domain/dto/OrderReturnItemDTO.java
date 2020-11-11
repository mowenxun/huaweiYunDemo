package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderReturnItemDTO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderReturnItemDTO extends AbstractObject implements Serializable {

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
     * 应用ID
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
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 退单ID
     */
    private Long orderReturnId;

    /**
     * 退单编号
     */
    private String orderReturnCode;

    /**
     * 计价单位
     */
    private Long unitId;

    /**
     * 商品ID
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品编号
     */
    private String skuCode;

    /**
     * 商品规格
     */
    private String skuFormat;

    /**
     * 商品数量
     */
    private Long skuQuantity;

    /**
     * 销售订单ID
     */
    private Long saleOrderId;

    /**
     * 销售订单编号
     */
    private String saleOrderCode;

    /**
     * 销售订单明细ID
     */
    private Long saleOrderItem;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 金额小计（含税）
     */
    private BigDecimal totalAmount;

    /**
     * 优惠后金额
     */
    private BigDecimal accrueAmount;

    /**
     * 优惠分摊金额
     */
    private BigDecimal discountAmount;

    /**
     * 成本价（单价)
     */
    private BigDecimal costPrice;

    /**
     * 价格政策标识
     */
    private Long pricePolicyId;

    /**
     * 退货数量
     */
    private Long returnQuantity;


}

