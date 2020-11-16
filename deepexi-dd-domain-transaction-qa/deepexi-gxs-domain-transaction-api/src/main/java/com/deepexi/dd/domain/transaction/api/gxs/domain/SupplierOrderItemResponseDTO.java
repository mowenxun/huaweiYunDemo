package com.deepexi.dd.domain.transaction.api.gxs.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 供应商订单的商品详情
 * @author huanghuai
 * @date 2020/10/13
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
public class SupplierOrderItemResponseDTO extends AbstractObject implements Serializable {


    private static final long serialVersionUID = 8637690224194187918L;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String majorPicture;
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号")
    private String skuCode;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String skuFormat;
    /**
     * 集采价
     */
    @ApiModelProperty(value = "集采价")
    private BigDecimal collectPurchasePrice;
    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    private Long skuQuantity;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unitName;

    /**
     * 金额小计
     */
    @ApiModelProperty(value = "金额小计")
    private BigDecimal totalAmount;





}