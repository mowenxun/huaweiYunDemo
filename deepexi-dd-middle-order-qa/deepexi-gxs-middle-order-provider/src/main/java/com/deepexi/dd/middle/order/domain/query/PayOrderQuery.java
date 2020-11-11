package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghuai
 * @date 2020-10-14 06:53
 */
@Data
public class PayOrderQuery extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 7888192850738771336L;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "收款状态")
    private Integer collectionStatus;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    private Long appId;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

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
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    /**
     * 来源单号
     */
    @ApiModelProperty(value = "来源单号")
    private String sourceOrderCode;
    @ApiModelProperty(value = "来源单号模糊查询")
    private String sourceOrderCodeLike;
    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date orderTime;

    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;

    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    private String sellerCode;

    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;

    /**
     * 已支付总金额
     */
    @ApiModelProperty(value = "已支付总金额")
    private BigDecimal paidMoney;

    /**
     * 未支付总金额
     */
    @ApiModelProperty(value = "未支付总金额")
    private BigDecimal unpayMoney;
}