package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SalePickGoodsOrderDTO
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickGoodsOrderDTO extends AbstractObject implements Serializable {

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
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 销售订单id
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 提货单信息id
     */
    private Long pickGoodsInfoId;

    /**
     * 单据类型(1:普通销售单,0:直供订单,2:非标准订单;3:订货计划单)
     */
    private String orderType;

    /**
     * 商品数量
     */
    private Long goodsNumber;

    /**
     * 该订单sku金额
     */
    private BigDecimal goodsMoney;


}

