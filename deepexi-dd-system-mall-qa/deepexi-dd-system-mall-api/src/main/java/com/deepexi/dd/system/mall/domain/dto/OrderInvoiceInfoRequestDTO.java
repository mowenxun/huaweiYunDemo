package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

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
public class OrderInvoiceInfoRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
     * 发票抬头
     */
    @ApiModelProperty(value = "发票抬头",required = true)
    @NotEmpty(message = "发票抬头为空")
    private String invoiceTitle;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行",required = true)
    @NotEmpty(message = "开户行为空")
    private String bankName;
    /**
     * 开户名称
     */
    @ApiModelProperty(value = "开户名称",required = true)
    @NotEmpty(message = "开户名称为空")
    private String accountName;
    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号",required = true)
    @NotEmpty(message = "开户账号为空")
    private String accountNo;
    /**
     *
     */
    @ApiModelProperty(value = "纳税人识别号",required = true)
    @NotEmpty(message = "纳税人识别号为空")
    private String taxNo;

    /**
     * 发票类型:发票类型:ordinary普通电子发票
     */
    @ApiModelProperty(value = "发票类型:发票类型:ordinary普通电子发票",required = true)
    private String invoiceType;
    /**
     * 发票内容
     */
    @ApiModelProperty(value = "发票内容",required = true)
    private String invocieContent;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",required = true)
    private String mobile;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址",required = true)
    private String address;
}

