package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* SalePickGoodsConsigneeDTO
*
* @author admin
* @date Mon Aug 24 16:35:15 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickGoodsConsigneeResponseDTO")
public class SalePickGoodsConsigneeResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * 应用ID
    */
    @ApiModelProperty(value = "应用ID")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 0：未逻辑删除状态。1：删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1：删除")
    private Boolean deleted;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 提货计划订单ID
    */
    @ApiModelProperty(value = "提货计划订单ID")
    private Long pickGoodsInfoId;
    /**
    * 提货计划订单编号
    */
    @ApiModelProperty(value = "提货计划订单编号")
    private String pickGoodsInfoCode;
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
    @ApiModelProperty(value = "收货人")
    private String consignee;
    /**
    * 手机号码
    */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

}

