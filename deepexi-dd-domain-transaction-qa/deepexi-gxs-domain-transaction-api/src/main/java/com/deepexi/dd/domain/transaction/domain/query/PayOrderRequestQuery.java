package com.deepexi.dd.domain.transaction.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JeremyLian
 * @date 2020/10/24 16:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PayOrderRequestQuery")
public class PayOrderRequestQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 7888192850738771336L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "APP标识")
    private Long appId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    @ApiModelProperty("收款状态")
    private Integer collectionStatus;

    @ApiModelProperty("订单编号")
    private String orderCode;

    @ApiModelProperty("来源单号")
    private String sourceOrderCode;

    @ApiModelProperty("单据日期")
    private Date orderTime;

    @ApiModelProperty("供货商ID")
    private Long sellerId;

    @ApiModelProperty("供货商名称")
    private String sellerName;

    @ApiModelProperty("供货商编码")
    private String sellerCode;

    @ApiModelProperty("总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("已支付总金额")
    private BigDecimal paidMoney;

    @ApiModelProperty("未支付总金额")
    private BigDecimal unpayMoney;

    @ApiModelProperty(
            value = "单据日期开始",
            example = "2020-09-10 12:00:01"
    )
    private String createdTimeFrom;
    @ApiModelProperty(
            value = "单据日期结束",
            example = "2020-10-10 12:00:01"
    )
    private String createdTimeTo;


}
