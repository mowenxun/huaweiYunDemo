package com.deepexi.dd.system.mall.domain.query.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName MerchantInvoiceRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantInvoiceAdminRequestQuery extends BaseRequestDTO {

    private static final long serialVersionUID = 1402552686630831446L;
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "客户id")
    private Long merchantId;

    @ApiModelProperty(value = "开票单位名称")
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号")
    private String creditCode;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "联系电话")
    private String contactPhone;

    @ApiModelProperty(value = "开户银行编码")
    private String bankCode;

    @ApiModelProperty(value = "开户银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行账户")
    private String bankAccount;

    @ApiModelProperty(value = "是否默认")
    private Boolean isDefault;

    @ApiModelProperty("是否为客户的银行账号")
    private Boolean isCustomer;

    @ApiModelProperty("组织id")
    private Long orgId;

}
