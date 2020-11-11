package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* ShopOrderItemQuery
*
* @author admin
* @date Tue Oct 13 15:15:24 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ShopOrderItemRequestQuery")
public class ShopOrderItemRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "商品名称：模糊查询")
    private String skuNameLike;
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号：模糊查询")
    private String skuCodeLike;
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
    private Long shopOrderId;

    @ApiModelProperty("订单id集合")
    private List<Long>shopOrderIds;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;
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

}

