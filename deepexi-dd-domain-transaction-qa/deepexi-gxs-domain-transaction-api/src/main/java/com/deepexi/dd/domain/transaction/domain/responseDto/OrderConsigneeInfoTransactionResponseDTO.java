package com.deepexi.dd.domain.transaction.domain.responseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by liaop on 2020/7/6.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderConsigneeInfoTransactionResponseDTO extends AbstractTenantResponseDTO implements Serializable {


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
     * 收货人
     */
    @ApiModelProperty(value = "收货人",required = true)
    private String consignee;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
}
