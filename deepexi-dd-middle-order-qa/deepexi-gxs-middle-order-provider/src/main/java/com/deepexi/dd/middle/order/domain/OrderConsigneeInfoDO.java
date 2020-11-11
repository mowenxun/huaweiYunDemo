package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;



/**
 * OrderConsigneeInfoDO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
@TableName("order_consignee_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderConsigneeInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 订单编号
     */
    private String saleOrderCode;

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

