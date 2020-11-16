package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* 订单发票信息
*
* @author admin
* @date Wed Jun 24 09:42:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "订单发票信息")
public class OrderInvoiceInfoResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
    * 发票抬头
    */
    @ApiModelProperty(value = "发票抬头")
    private String invoiceTitle;
    /**
    * 开户行
    */
    @ApiModelProperty(value = "开户行")
    private String bankName;
    /**
    * 开户名称
    */
    @ApiModelProperty(value = "开户名称")
    private String accountName;
    /**
    * 开户账号
    */
    @ApiModelProperty(value = "开户账号")
    private String accountNo;
    /**
    * 
    */
    @ApiModelProperty(value = "纳税人识别号")
    private String taxNo;

    /**
     * 发票类型:发票类型:ordinary普通电子发票
     */
    @ApiModelProperty(value = "发票类型:发票类型:ordinary普通电子发票")
    private String invoiceType;
    /**
     * 发票内容
     */
    @ApiModelProperty(value = "发票内容")
    private String invocieContent;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String mobile;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "发票ID")
    private Long invoiceId;
}

