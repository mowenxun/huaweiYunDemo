package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.dd.middle.order.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
* 发票添加参数对象
*
* @author admin
* @date Wed Jun 24 09:42:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "发票添加参数对象")
public class OrderInvoiceInfoAddRequestDTO extends TenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 发票抬头
    */
    @ApiModelProperty(value = "发票抬头")
    @NotEmpty(message = "发票抬头为空")
    private String invoiceTitle;
    /**
    * 开户行
    */
    @ApiModelProperty(value = "开户行")
    @NotEmpty(message = "开户行为空")
    private String bankName;
    /**
    * 开户名称
    */
    @ApiModelProperty(value = "开户名称")
    @NotEmpty(message = "开户名称为空")
    private String accountName;
    /**
    * 开户账号
    */
    @ApiModelProperty(value = "开户账号")
    @NotEmpty(message = "开户账号为空")
    private String accountNo;
    /**
    *
    */
    @ApiModelProperty(value = "纳税人识别号")
    @NotEmpty(message = "纳税人识别号为空")
    private String taxNo;

}

