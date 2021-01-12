package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
* 订单送货地址添加对象
*
* @author admin
* @date Wed Jun 24 09:42:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单送货地址添加对象")
public class OrderConsigneeInfoAddRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    @NotEmpty(message = "收货人地址为空")
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

