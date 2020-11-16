package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-26 19:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单自提地址添加对象")
public class OrderSelfRaisingInfoAddDTO extends AbstractObject implements Serializable {
    /**
     * 省份编号
     */
    @ApiModelProperty(value = "省份编号")
    private String provinceCode;
    /**
     * 省份名称
     */
    @ApiModelProperty(value = "省份名称")
    private String provinceName;
    /**
     * 城市编号
     */
    @ApiModelProperty(value = "城市编号")
    private String cityCode;
    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号")
    private String areaCode;
    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String areaName;
    /**
     * 街道编号
     */
    @ApiModelProperty(value = "街道编号")
    private String streetCode;
    /**
     * 街道名称
     */
    @ApiModelProperty(value = "街道名称")
    private String streetName;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;
    /**
     * 仓库联系人
     */
    @ApiModelProperty(value = "仓库联系人")
    private String warehouseContac;
    /**
     * 仓库联系电话
     */
    @ApiModelProperty(value = "仓库联系电话")
    private String warehouseMobile;
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String carNumber;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String identityCard;
    /**
     * 提货人
     */
    @ApiModelProperty(value = "提货人")
    private String saleRaisingName;
    /**
     * 提货人手机号码
     */
    @ApiModelProperty(value = "提货人手机号码")
    private String mobile;

    /**
     * 发货仓库
     */
    @ApiModelProperty(value = "发货仓库", required = true)
    private Long fromStorehouse;

    @ApiModelProperty(value = "提货仓库Id")
    private Long deliveryWareHouseId;

    /**
     * 提货仓库名称
     */
    @ApiModelProperty(value = "提货仓库名称")
    private String deliveryWareHouseName;
}
