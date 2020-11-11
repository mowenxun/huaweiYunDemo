package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SaleOrderPayNotifyDTO
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOrderPayNotifyDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * APPID
     */
    private Long appId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除1是，0否
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 确认类型:1确认,0取消
     */
    private Integer type;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 订单类型1网批订单，2提货订单
     */
    @ApiModelProperty(value ="订单类型1网批订单，2提货订单" )
    private Integer orderType;

    /**
     * 订单号
     */
    @ApiModelProperty(value ="订单号" )
    private String orderCode;

}

