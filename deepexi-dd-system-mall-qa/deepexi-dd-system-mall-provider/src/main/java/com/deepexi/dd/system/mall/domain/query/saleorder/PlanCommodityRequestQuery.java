package com.deepexi.dd.system.mall.domain.query.saleorder;

import com.deepexi.dd.system.mall.domain.query.BaseQuery;
import com.deepexi.dd.system.mall.enums.CommodityRequestEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 订货计划入参
 */
@Data
@ApiModel
public class PlanCommodityRequestQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -1420490681701020521L;

    // 分页
    @ApiParam(value = "page", example = "1")
    private Integer page = 1;
    @ApiParam(value = "size", example = "20")
    private Integer size = 20;


    @ApiModelProperty(value = "产品线id")
    // @NotNull(message = "产品线不能为空")
    private Long productId;

    /*  @ApiModelProperty(value = "授权的商品信息集合")
      private List<CommodityAuthorized> commodityAuthorizeds;*/
    @ApiModelProperty(value = "授权的进来的只会是同一个供应商的sku集合")
    private CommodityAuthorized commodityAuthorized;

    @ApiModelProperty(value = "商品接口请求来源枚举")
    private CommodityRequestEnum commodityRequestEnum;

    @ApiModelProperty(value = "0-直供订单；1-非直供订单")
    private Integer orderType;
    @ApiModelProperty(value = "0-顶级组织，【】全部数据；1-非顶级组织【】空数组处理")
    private Integer isAllData;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "商品名称")
    private String skuName;

    @ApiModelProperty(value = "商品编码")
    private String skuCode;


}
