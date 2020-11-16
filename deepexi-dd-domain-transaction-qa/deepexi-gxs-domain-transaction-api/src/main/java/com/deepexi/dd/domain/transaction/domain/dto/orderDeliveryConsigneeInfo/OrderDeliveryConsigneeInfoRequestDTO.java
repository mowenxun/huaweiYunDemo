package com.deepexi.dd.domain.transaction.domain.dto.orderDeliveryConsigneeInfo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName OrderDeliveryConsigneeInfoRequestDTO
 * @Description 出库单发货信息请求DTO
 * @Author SongTao
 * @Date 2020-07-16
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryConsigneeInfoRequestDTO")
public class OrderDeliveryConsigneeInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -8203867324689509069L;

    /**
     * 省份编号
     */
    @ApiModelProperty(value = "省份编号")
    private String provinceCode;
    /**
     * 省份名称
     */
    @ApiModelProperty(value = "省份名称",required = true)
    private String provinceName;
    /**
     * 城市编号
     */
    @ApiModelProperty(value = "城市编号")
    private String cityCode;
    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称",required = true)
    private String cityName;
    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号")
    private String areaCode;
    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称",required = true)
    private String areaName;
    /**
     * 街道编号
     */
    @ApiModelProperty(value = "街道编号")
    private String streetCode;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;
    /**
     * 街道名称
     */
    @ApiModelProperty(value = "街道名称",required = true)
    @NotEmpty(message = "收货人地址为空")
    private String streetName;
    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人",required = true)
    @NotEmpty(message = "收货人为空")
    private String consignee;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码",required = true)
    @NotEmpty(message = "收货人手机号码为空")
    private String mobile;

    @ApiModelProperty(value = "(自提)车牌号")
    private String licensePlate;

    @ApiModelProperty(value = "(自提)提货人")
    private String saleRaisingName;

    @ApiModelProperty(value = "(自提)联系电话")
    private String telephone;

    @ApiModelProperty(value = "(自提)身份证号")
    private String idCardNumber;
}
