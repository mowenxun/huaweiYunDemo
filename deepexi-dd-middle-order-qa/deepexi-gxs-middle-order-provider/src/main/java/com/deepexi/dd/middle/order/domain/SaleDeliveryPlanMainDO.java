package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * SaleDeliveryPlanMainDO
 *
 * @author admin
 * @date Thu Aug 13 15:26:43 CST 2020
 * @version 1.0
 */
@TableName("sale_delivery_plan_main")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryPlanMainDO extends BaseDO implements Serializable {

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
     * 编排编号
     */
    private String code;

    /**
     * 编排数量
     */
    private Long quantity;



}

