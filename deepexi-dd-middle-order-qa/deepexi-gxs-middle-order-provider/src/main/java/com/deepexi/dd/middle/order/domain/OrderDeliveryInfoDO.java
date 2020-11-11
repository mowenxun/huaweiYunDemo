package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;



/**
 * OrderDeliveryInfoDO
 *
 * @author admin
 * @date Wed Jul 01 19:40:51 CST 2020
 * @version 1.0
 */
@TableName("order_delivery_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDeliveryInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售出库单ID
     */
    private Long saleOutTaskId;

    /**
     * 销售出库单编号
     */
    private String saleOutTaskCode;

    /**
     * 物流公司名称
     */
    private String deliveryName;

    /**
     * 物流编号
     */
    private String deliveryCode;

    /**
     * 物流投递时间
     */
    private Date deliveryTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 车牌号码
     */
    private String licensePlate;

    /**
     * 司机名称
     */
    private String driver;

    /**
     * 司机电话
     */
    private String driverMobile;
    /**
     * 提货计划id
     */
    private Long salePickGoodsId;
    /**
     * 提货计划编号
     */
    private String salePickGoodsCode;
    /**
     * 身份证号码（司机）
     */
    private String idCardNum;

}

