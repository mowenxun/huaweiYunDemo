package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName OrderDeliveryConsigneeInfoMiddleRequestDTO
 * @Description 出库单送货地址请求DTO
 * @Author SongTao
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryConsigneeInfoMiddleRequestDTO")
public class OrderDeliveryConsigneeInfoMiddleRequestDTO extends AbstractTenantDTO implements Serializable {
    private static final long serialVersionUID = -5474816167590675561L;

    /**
     * 出库单ID
     */
    @ApiModelProperty(value = "出库单ID")
    private String saleOutTaskId;

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
    @NotEmpty(message = "收货人为空")
    private String consignee;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    @NotEmpty(message = "收货人手机号码为空")
    private String mobile;

    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;

    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;

    //SongTao 2020/08/27 用于发货自提信息
    @ApiModelProperty(value = "(自提)车牌号")
    private String licensePlate;

    @ApiModelProperty(value = "提货人")
    private String saleRaisingName;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "身份证号")
    private String idCardNumber;
}
