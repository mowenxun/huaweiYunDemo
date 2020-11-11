package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* AfterSaleOrderItemDTO
*
* @author admin
* @date Fri Oct 16 14:17:13 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "AfterSaleOrderItemRequestDTO")
public class AfterSaleOrderItemRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    * 0：未逻辑删除状态。1:删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;
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
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long afterSaleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String afterSaleOrderCode;
    /**
    * 单位id
    */
    @ApiModelProperty(value = "单位id")
    private Long unitId;
    /**
    * 单位名称
    */
    @ApiModelProperty(value = "单位名称")
    private String unitName;
    /**
    * 商品ID
    */
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
    /**
    * 店铺ID
    */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
    * 店铺名称
    */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
    * 店铺编码
    */
    @ApiModelProperty(value = "店铺编码")
    private String shopCode;
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
    * 商品名称
    */
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    /**
    * 商品编号
    */
    @ApiModelProperty(value = "商品编号")
    private String skuCode;
    /**
    * 商品规格
    */
    @ApiModelProperty(value = "商品规格")
    private String skuFormat;
    /**
    * 商品数量
    */
    @ApiModelProperty(value = "商品数量")
    private Long skuQuantity;
    /**
    * 税率
    */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
    /**
    * 单价
    */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    /**
    * 金额小计
    */
    @ApiModelProperty(value = "金额小计")
    private BigDecimal totalAmount;
    /**
    * 成本价
    */
    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;
    /**
    * 主图地址
    */
    @ApiModelProperty(value = "主图地址")
    private String majorPicture;
    /**
    * 签收数量
    */
    @ApiModelProperty(value = "签收数量")
    private Long signQuantity;
    /**
    * 建议零售价
    */
    @ApiModelProperty(value = "建议零售价")
    private BigDecimal proposePrice;
    /**
    * 县级门店批发价
    */
    @ApiModelProperty(value = "县级门店批发价")
    private BigDecimal countyPrice;
    /**
    * 镇级门店批发价
    */
    @ApiModelProperty(value = "镇级门店批发价")
    private BigDecimal townPrice;
    /**
    * 村级门店批发价
    */
    @ApiModelProperty(value = "村级门店批发价")
    private BigDecimal villagePrice;
    /**
    * 团购级门店批发价
    */
    @ApiModelProperty(value = "团购级门店批发价")
    private BigDecimal groupPurchasePrice;
    /**
    * 集采价格
    */
    @ApiModelProperty(value = "集采价格")
    private BigDecimal collectPurchasePrice;
    /**
    * 退货数量
    */
    @ApiModelProperty(value = "退货数量")
    private Long backNum;


    @ApiModelProperty(value = "品牌ID")
    private Long brandId;

    @ApiModelProperty(value = "品牌编码")
    private Long brandCode;

    @ApiModelProperty(value = "品牌名称")
    private Long brandName;

}

