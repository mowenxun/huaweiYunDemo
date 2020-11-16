package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * SaleDeliveryPlanInfoQuery
 *
 * @author admin
 * @version 1.0
 * @date Thu Aug 13 15:26:43 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "发货计划记录【查看明细(商品列表)】请求参数")
public class SaleDeliveryPlanInfoRequest extends AbstractObject implements Serializable {

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
     * 创建时间
     */
    @ApiModelProperty(value = "提货计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdStartTime;

    @ApiModelProperty(value = "提货计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pickGoodsTimeZt;

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
     * 提货单编号
     */
    @ApiModelProperty(value = "提货单编号")
    private Long pickGoodsCode;

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

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String skuName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "客户名称【暂不明确需求】")
    private String clientName;
}

