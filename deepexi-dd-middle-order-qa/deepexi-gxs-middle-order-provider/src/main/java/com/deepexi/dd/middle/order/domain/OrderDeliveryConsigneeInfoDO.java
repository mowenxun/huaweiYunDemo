package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName orderDeliveryConsigneeInfoDO
 * @Description 出库单发货地址DTO
 * @Author SongTao
 * @Date 2020-07-15
 * @Version 1.0
 **/
@TableName("order_delivery_consignee_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDeliveryConsigneeInfoDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 2984133658837160037L;

    /**
     * 出库单ID
     */
    private Long saleOutTaskId;

    /**
     * 出库单编号
     */
    private String saleOutTaskCode;

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
     * 收货人
     */
    private String consignee;

    /**
     * 手机号码
     */
    private String mobile;
    //2020/07/18 SongTao 补充创建人和更新人
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 详细地址
     */
    private String detailedAddress;
    /**
     * 提货计划id
     */
    private Long salePickGoodsId;
    /**
     * 提货计划编号
     */
    private String salePickGoodsCode;

    //SongTao 2020/08/27 用于发货自提信息
    /**
     * 车牌号
     */
    private String licensePlate;
    /**
     * 提货人
     */
    private String saleRaisingName;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 身份证号
     */
    private String idCardNumber;
}
