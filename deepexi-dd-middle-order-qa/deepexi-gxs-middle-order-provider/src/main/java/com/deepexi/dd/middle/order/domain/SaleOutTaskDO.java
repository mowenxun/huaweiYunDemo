package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;



/**
 * SaleOutTaskDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@TableName("sale_out_task")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOutTaskDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出库单编号
     */
    private String code;

    /**
     * 单据类型:out 销售出库单
     */
    private String type;
    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

    /**
     * 订单确认状态 0:已确认,1已作废
     */
    private Integer status;

    /**
     * 单据日期
     */
    private Date ticketDate;

    /**
     * 计划出库总数量
     */
    private Long skuQuantity;

    /**
     * 出库单类型,1原单,2蓝单,3红单
     */
    private Integer taskType;

    /**
     * 红冲的原订单标识
     */
    private Long hedgeOrder;

    /**
     * 发货物流ID
     */
    private Long orderDeliveryId;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 发货仓库ID
     */
    private Long deliveryWareHouseId;

    /**
     * 发货仓库名称
     */
    private String deliveryWareHouseName;

    /**
     * 发货方式(0:送货；1:自提)
     */
    private Integer deliveryType;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 签收状态(17:待收货；19:已签收)
     */
    private Integer signStatus;

    /**
     * 商品出库总数量
     */
    private Long deliveryQuantity;

    /**
     * 签收人
     */
    private String signBy;

    /**
     * 签收时间
     */
    private Date signTime;

    /**
     * 发货计划ID
     */
    private Long saleDeliveryPlanId;
    /**
     * 发货计划编号
     */
    private Long saleDeliveryPlanCode;
    /**
     * 提货计划ID
     */
    private Long salePickGoodsId;
    /**
     * 提货计划编号
     */
    private String salePickGoodsCode;
    /**
     * 申请数量
     */
    private Long skuApplyQuantity;
    /**
     * 隔离标识
     */
    private String isolationId;

    /**
     * 拆单所属组织
     */
    private Long ascriptionOrgId;

}

