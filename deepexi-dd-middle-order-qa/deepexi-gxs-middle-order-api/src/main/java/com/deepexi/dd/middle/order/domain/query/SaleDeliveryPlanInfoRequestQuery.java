package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
* SaleDeliveryPlanInfoQuery
*
* @author admin
* @date Thu Aug 13 15:26:43 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleDeliveryPlanInfoRequestQuery")
public class SaleDeliveryPlanInfoRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
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
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private String createdTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
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
    * 发货计划编号
    */
    @ApiModelProperty(value = "发货计划编号")
    private String code;
    /**
    * 发货计划状态1成功,0失败
    */
    @ApiModelProperty(value = "发货计划状态1成功,0失败")
    private Integer status;
    /**
    * 执行结果描述
    */
    @ApiModelProperty(value = "执行结果描述")
    private String reason;
    /**
    * 编排主表ID
    */
    @ApiModelProperty(value = "编排主表ID")
    private Long saleDeliveryPlanMaid;
    /**
    * 编排主表编号
    */
    @ApiModelProperty(value = "编排主表编号")
    private String saleDeliveryPlanCode;

    /**
     * 提货计划开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "提货计划开始时间")
    private String createdStartTime;

    @ApiModelProperty(value = "提货计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pickGoodsTimeZt;

    /**
     * 提货单编号
     */
    @ApiModelProperty(value = "提货单编号")
    private Long pickGoodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String skuName;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    /**
     * ZT/WQ
     */
    @ApiModelProperty(value = "ZT/WQ")
    private Long ztWq;

}

