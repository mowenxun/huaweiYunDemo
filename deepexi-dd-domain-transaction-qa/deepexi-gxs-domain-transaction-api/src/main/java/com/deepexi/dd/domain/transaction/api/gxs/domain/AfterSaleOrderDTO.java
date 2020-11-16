package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;


/**
 * AfterSaleOrdeDO
 *
 * @author huanghuai
 * @version 1.0
 * @date Fri Oct 16 14:17:12 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class AfterSaleOrderDTO extends AppIdDTO {

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
     * 订单状态 25 待审核 26 审核通过 27 驳回 28 已完成
     */
    @ApiModelProperty(value = "订单状态 25 待审核 26 审核通过 27 驳回 28 已完成")
    private String status;

    /**
     * 店铺ID(下单的店铺)
     */
    @ApiModelProperty(value = "店铺ID(下单的店铺)")
    private Long shopId;

    /**
     * 店铺名称(下单的店铺)
     */
    @ApiModelProperty(value = "店铺名称(下单的店铺)")
    private String shopName;

    /**
     * 店铺编码（下单的店铺）
     */
    @ApiModelProperty(value = "店铺编码（下单的店铺）")
    private String shopCode;

    /**
     * 商品总数
     */
    @ApiModelProperty(value = "商品总数")
    private Long quantity;

    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额")
    private BigDecimal totalAmount;

    /**
     * 售后接受方：店铺对应的供销社id
     */
    @ApiModelProperty(value = "售后接受方：店铺对应的供销社id")
    private Long alterReceiveId;

    /**
     * 供销社编码
     */
    @ApiModelProperty(value = "供销社编码")
    private String alterReceiveCode;

    /**
     * 供销社名称
     */
    @ApiModelProperty(value = "供销社名称")
    private String alterReceiveName;

    /**
     * 售后原因类型：0-质量问题；1-其他
     */
    @ApiModelProperty(value = "售后原因类型：0-质量问题；1-其他")
    private String reasonsType;

    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述")
    private String question;

    /**
     * 图片地址,多个使用;隔开
     */
    @ApiModelProperty(value = "图片地址,多个使用;隔开")
    private String pictureUrl;

    /**
     * 店铺上级店铺id,普通店铺的上级店铺是直营店铺，直营店铺的上一级是它自己
     */
    @ApiModelProperty(value = "店铺上级店铺id,普通店铺的上级店铺是直营店铺，直营店铺的上一级是它自己")
    private Long parentShopId;
}

