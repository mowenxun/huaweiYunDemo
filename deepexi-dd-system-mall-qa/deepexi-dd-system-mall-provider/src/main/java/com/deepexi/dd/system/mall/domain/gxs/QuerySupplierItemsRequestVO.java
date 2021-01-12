package com.deepexi.dd.system.mall.domain.gxs;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * SupplerOrderItemQuery
 *
 * @author admin
 * @version 1.0
 * @date Tue Oct 13 15:15:23 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "QuerySupplierItemsRequestVO")
public class QuerySupplierItemsRequestVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "组织id（groupId）")
    @NotNull(message = "组织id不能为空")
    @ApiParam(value = "组织id",required = true)
    private Long orgId;
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
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    private Long appId;

    // /**
    // * 供货商ID
    // */
    // @ApiModelProperty(value = "供货商ID")
    // private Long sellerId;
    // /**
    // * 供货商名称
    // */
    // @ApiModelProperty(value = "供货商名称")
    // private String sellerName;
    // /**
    // * 供货商编码
    // */
    // @ApiModelProperty(value = "供货商编码")
    // private String sellerCode;
    @ApiModelProperty(value = "商品名称：模糊查询")
    private String skuNameLike;
    // @ApiModelProperty(value = "商品名称")
    // private String skuName;
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号：模糊查询")
    private String skuCodeLike;
    // @ApiModelProperty(value = "商品编号")
    // private String skuCode;
}

