package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * OrderConsigneeAddressDO
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@TableName("order_consignee_address")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderConsigneeAddressDO extends BaseDO implements Serializable {

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
     * 0-店铺订单；1-已分发的订单
     */
    private String orderType;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 省份编号
     */
    private String provinceCode;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 城市编号
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 区域编号
     */
    private String areaCode;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 街道编号
     */
    private String streetCode;

    /**
     * 街道名称
     */
    private String streetName;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号码
     */
    private String mobile;



}

