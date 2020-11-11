package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * SalePickGoodsConsigneeDO
 *
 * @author admin
 * @date Mon Aug 24 16:35:15 CST 2020
 * @version 1.0
 */
@TableName("sale_pick_goods_consignee")
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickGoodsConsigneeDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 提货计划订单ID
     */
    private Long pickGoodsInfoId;

    /**
     * 提货计划订单编号
     */
    private String pickGoodsInfoCode;

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

