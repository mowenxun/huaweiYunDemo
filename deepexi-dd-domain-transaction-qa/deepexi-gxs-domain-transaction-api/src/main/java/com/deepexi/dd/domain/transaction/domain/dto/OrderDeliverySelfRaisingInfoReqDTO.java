package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderDeliverySelfRaisingInfoDTO
*
* @author admin
* @date Wed Aug 26 16:41:35 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliverySelfRaisingInfoReqDTO")
public class OrderDeliverySelfRaisingInfoReqDTO extends AbstractObject implements Serializable {

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
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 更新人
    */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    /**
    * 提货计划ID
    */
    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;
    /**
    * 提货计划编号
    */
    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;
    /**
    * 出库单ID
    */
    @ApiModelProperty(value = "出库单ID")
    private Long saleOutTaskId;
    /**
    * 出库单编号
    */
    @ApiModelProperty(value = "出库单编号")
    private String saleOutTaskCode;
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

}

