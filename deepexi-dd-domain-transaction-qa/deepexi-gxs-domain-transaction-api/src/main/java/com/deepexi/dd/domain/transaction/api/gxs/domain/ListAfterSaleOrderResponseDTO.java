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
public class ListAfterSaleOrderResponseDTO extends AppIdDTO {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "售后单编号")
    private String orderCode;

    /**
     * 订单状态 1 待审核 2 审核通过 3 驳回 4 已完成
     */
    @ApiModelProperty(value = "订单状态 1 待审核 2 审核通过 3 驳回 4 已完成")
    private String status;

    /**
     * 店铺名称(下单的店铺)
     */
    @ApiModelProperty(value = "店铺名称(下单的店铺)")
    private String shopName;
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
     * 供销社名称
     */
    @ApiModelProperty(value = "供销社名称")
    private String alterReceiveName;
    /**
     * 图片地址,多个使用;隔开
     */
    @ApiModelProperty(value = "图片地址,多个使用;隔开")
    private String pictureUrl;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所属供应商")
    private String sellerName;

    // @ApiModelProperty(value = "供货商ID")
    // private Long sellerId;
    //
    // @ApiModelProperty(value = "供货商编码")
    // private String sellerCode;
}

