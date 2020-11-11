package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * SaleDeliveryPlanInfoDO
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@TableName("sale_delivery_plan_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryPlanInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 发货计划编号
     */
    private String code;

    /**
     * 发货计划状态1成功,0失败
     */
    private Integer status;

    /**
     * 执行结果描述
     */
    private String reason;

    /**
     * 编排主表ID
     */
    private Long saleDeliveryPlanMaid;

    /**
     * 编排主表编号
     */
    private String saleDeliveryPlanCode;



}

