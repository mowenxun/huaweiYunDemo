package com.deepexi.dd.system.mall.domain.dto.customer;

import com.deepexi.dd.domain.common.constant.CommonConstant;
import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @ClassName MerchantInvoiceCreateRequestDTO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantInvoiceCreateAdminRequestDTO extends BaseRequestDTO {

    private static final long serialVersionUID = -6182190899310727720L;

    @ApiModelProperty(value = "单位名称", required = true)
    @NotEmpty(message = "单位名称不能为空.")
    private String companyName;

    @ApiModelProperty(value = "纳税人识别号", required = true)
    @NotEmpty(message = "纳税人识别号不能为空.")
    private String creditCode;

    @ApiModelProperty(value = "注册地址")
    private String companyAddress;

    @ApiModelProperty(value = "注册电话")
    private String contactPhone;

    @ApiModelProperty(value = "开户银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行账户")
    @Pattern(regexp = CommonConstant.NUMBER_PATTERN, message = "银行账号只能为数字.")
    private String bankAccount;

    @ApiModelProperty(value = "是否默认")
    private Boolean isDefault;

    @ApiModelProperty("组织id")
    private Long orgId;
}
