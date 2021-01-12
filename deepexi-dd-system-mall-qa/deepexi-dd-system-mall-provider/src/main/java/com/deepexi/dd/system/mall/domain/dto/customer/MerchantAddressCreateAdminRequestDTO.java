package com.deepexi.dd.system.mall.domain.dto.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName MerchantAddressCreateRequestDTO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantAddressCreateAdminRequestDTO extends BaseRequestDTO {

    private static final long serialVersionUID = 7572303092666586136L;

    @ApiModelProperty(value = "联系人", required = true)
    @NotEmpty(message = "联系人不能为空.")
    private String contacts;

    @ApiModelProperty(value = "手机号码", required = true)
    @NotEmpty(message = "手机号码不能为空.")
    private String phone;

    @ApiModelProperty(value = "所在国家", required = true)
    @NotEmpty(message = "所在国家不能为空.")
    private String countryCode;

    @ApiModelProperty(value = "所在省份", required = true)
    @NotEmpty(message = "所在省份不能为空.")
    private String provinceCode;

    @ApiModelProperty(value = "所在省份名称", required = true)
    @NotEmpty(message = "所在省份名称不能为空.")
    private String provinceName;

    @ApiModelProperty(value = "所在城市", required = true)
    @NotEmpty(message = "所在城市不能为空.")
    private String cityCode;

    @ApiModelProperty(value = "所在城市名称", required = true)
    @NotEmpty(message = "所在城市名称不能为空.")
    private String cityName;

    @ApiModelProperty(value = "所在区/县", required = true)
    @NotEmpty(message = "所在区/县不能为空.")
    private String downtownCode;

    @ApiModelProperty(value = "所在区/县名称", required = true)
    @NotEmpty(message = "所在区/县名称不能为空.")
    private String downtownName;

    @ApiModelProperty(value = "所在街道")
    private String streetCode;

    @ApiModelProperty(value = "所在街道名称")
    private String streetName;

    @ApiModelProperty(value = "详细地址", required = true)
    @NotEmpty(message = "详细地址不能为空.")
    private String detailedAddress;

    @ApiModelProperty(value = "是否默认", required = true)
    @NotNull(message = "是否默认不能为空.")
    private Boolean isDefault;

    @ApiModelProperty("组织id")
    private Long orgId;
}
