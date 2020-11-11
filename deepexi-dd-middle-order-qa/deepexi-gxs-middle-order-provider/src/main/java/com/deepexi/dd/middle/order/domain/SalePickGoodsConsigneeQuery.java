package com.deepexi.dd.middle.order.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SalePickGoodsConsigneeQuery
 *
 * @author admin
 * @date Mon Aug 24 16:35:15 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickGoodsConsigneeQuery extends AbstractObject implements Serializable {

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
         * 创建时间
         */
        private Date createdTime;
        /**
         * 更新时间
         */
        private Date updatedTime;
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

    /**
     * 是否删除,0否,1是
     */
    private Boolean isDeleted;
}

