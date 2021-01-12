package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MerchantAddressResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantAddressResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = 4912783528246047344L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "客户id")
    private Long merchantId;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "所在国家")
    private String countryCode;

    @ApiModelProperty(value = "所在省份")
    private String provinceCode;

    @ApiModelProperty(value = "所在省份名称")
    private String provinceName;

    @ApiModelProperty(value = "所在城市")
    private String cityCode;

    @ApiModelProperty(value = "所在城市名称")
    private String cityName;

    @ApiModelProperty(value = "所在区/县")
    private String downtownCode;

    @ApiModelProperty(value = "所在区/县名称")
    private String downtownName;

    @ApiModelProperty(value = "所在街道")
    private String streetCode;

    @ApiModelProperty(value = "所在街道名称")
    private String streetName;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否默认")
    private Boolean isDefault;
}
