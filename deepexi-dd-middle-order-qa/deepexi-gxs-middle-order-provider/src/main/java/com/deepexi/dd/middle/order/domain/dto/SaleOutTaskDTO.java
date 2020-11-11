package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SaleOutTaskDTO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOutTaskDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
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
     * 发货计划ID
     */
    private Long saleDeliveryPlanId;
    /**
     * 发货计划编号
     */
    private String saleDeliveryPlanCode;
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

