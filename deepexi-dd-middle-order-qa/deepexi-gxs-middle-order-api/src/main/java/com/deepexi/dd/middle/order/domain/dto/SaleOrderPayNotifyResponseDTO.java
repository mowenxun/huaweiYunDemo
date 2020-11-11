package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* SaleOrderPayNotifyDTO
*
* @author admin
* @date Thu Jul 23 18:17:38 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOrderPayNotifyResponseDTO")
public class SaleOrderPayNotifyResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * APPID
    */
    @ApiModelProperty(value = "APPID")
    private Long appId;
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
    * 是否删除1是，0否
    */
    @ApiModelProperty(value = "是否删除1是，0否")
    private Boolean deleted;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    /**
    * 确认类型:1确认,0取消
    */
    @ApiModelProperty(value = "确认类型:1确认,0取消")
    private Integer type;
    /**
    * 支付流水号
    */
    @ApiModelProperty(value = "支付流水号")
    private String payNo;
    /**
    * 金额
    */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

}

