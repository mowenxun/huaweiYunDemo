package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MerchantInvoiceResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantInvoiceResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = 5708829568361528642L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long merchantId;
    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String companyName;
    /**
     * 纳税人识别号
     */
    @ApiModelProperty(value = "纳税人识别号")
    private String creditCode;
    /**
     * 注册地址
     */
    @ApiModelProperty(value = "注册地址")
    private String companyAddress;
    /**
     * 注册电话
     */
    @ApiModelProperty(value = "注册电话")
    private String contactPhone;
    /**
     * 开户银行编码
     */
    @ApiModelProperty(value = "开户银行编码")
    private String bankCode;
    /**
     * 开户银行名称
     */
    @ApiModelProperty(value = "开户银行名称")
    private String bankName;
    /**
     * 银行账户
     */
    @ApiModelProperty(value = "银行账户")
    private String bankAccount;
    /**
     * 是否默认
     */
    @ApiModelProperty(value = "是否默认")
    private Boolean isDefault;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
    /**
     * 删除状态
     */
    @ApiModelProperty(value = "删除状态")
    private Boolean deleted;
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
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long orgId;
    /**
     * 所在国家
     */
    @ApiModelProperty(value = "所在国家")
    private String countryCode;
    /**
     * 所在省份
     */
    @ApiModelProperty(value = "所在省份")
    private String provinceCode;
    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;
    /**
     * 所在区/县
     */
    @ApiModelProperty(value = "所在区/县")
    private String downtownCode;
    /**
     * 区/县名称
     */
    @ApiModelProperty(value = "区/县名称")
    private String downtownName;
    /**
     * 所在城市
     */
    @ApiModelProperty(value = "所在城市")
    private String cityCode;
    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    /**
     * 所在街道
     */
    @ApiModelProperty(value = "所在街道")
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
     * 收票人姓名
     */
    @ApiModelProperty(value = "收票人姓名")
    private String addressee;
    /**
     * 收票人电话
     */
    @ApiModelProperty(value = "收票人电话")
    private String addresseeTelNum;
}
