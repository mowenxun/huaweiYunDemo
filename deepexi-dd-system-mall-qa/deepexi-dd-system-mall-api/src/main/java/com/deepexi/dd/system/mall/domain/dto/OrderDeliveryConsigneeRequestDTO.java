package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName OrderDeliveryConsigneeRequestDTO
 * @Description 出库单发货信息请求DTO
 * @Author SongTao
 * @Date 2020-08-06
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryConsigneeRequestDTO")
public class OrderDeliveryConsigneeRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 7458909512966011410L;

    @ApiModelProperty(value = "省份编号")
    private String provinceCode;

    @ApiModelProperty(value = "省份名称",required = true)
    private String provinceName;

    @ApiModelProperty(value = "城市编号")
    private String cityCode;

    @ApiModelProperty(value = "城市名称",required = true)
    private String cityName;

    @ApiModelProperty(value = "区域编号")
    private String areaCode;

    @ApiModelProperty(value = "区域名称",required = true)
    private String areaName;

    @ApiModelProperty(value = "街道编号")
    private String streetCode;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "街道名称",required = true)
    @NotEmpty(message = "收货人地址为空")
    private String streetName;

    @ApiModelProperty(value = "收货人",required = true)
    @NotEmpty(message = "收货人为空")
    private String consignee;

    @ApiModelProperty(value = "手机号码",required = true)
    @NotEmpty(message = "收货人手机号码为空")
    private String mobile;
}
