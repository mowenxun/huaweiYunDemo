package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SaleOrderItemQuery
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderItemQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页数
     */
    private Integer size = 10;

    /**
     *
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
     *
     */
    private Date createdTime;
    /**
     *
     */
    private Date updatedTime;
    /**
     * 明细编号
     */
    private String code;
    /**
     * 订单ID
     */
    private Long saleOrderId;
    /**
     * 订单编号
     */
    private String saleOrderCode;
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
     * 税率
     */
    private BigDecimal taxRate;
    /**
     * 单价
     */
    private BigDecimal price;
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
     * 用来批量的去查询订单信息
     */
    private List<Long> idList;

    /**
     * 只显示可提货数量不为零的数据
     */
    private Boolean availablePickNumNotZero;

    /**
     * 商品名称或商品规格模糊搜索
     */
    private String likeSkuNameOrSkuFormat;
}

