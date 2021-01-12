package com.deepexi.dd.system.mall.domain.dto.commodity;

import com.deepexi.sdk.commodity.model.PageShelvesItemSkuItemItemTagResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-12-25 14:21
 */
@Data
public class CommodityDTO extends AbstractObject {

    @ApiModelProperty("渠道id")
    private Long channelId;

    @ApiModelProperty("唯一键，组合itemId_releaseVersion_shopId")
    private String itemReleaseShop;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品副标题")
    private String subName;

    @ApiModelProperty("商品副标题")
    private String remark;

    @ApiModelProperty("skuCode")
    private String skuCode;

    @ApiModelProperty("商品销售价格")
    private BigDecimal salePrice;

    @ApiModelProperty("商品建议零售价")
    private BigDecimal suggestPrice;

    @ApiModelProperty("主图")
    private String majorPicture;

    @ApiModelProperty("上架版本")
    private Integer releaseVersion;

    @ApiModelProperty("店铺ID")
    private Long shopId;

    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("商品授权组织")
    private Long authorizeBy;

    private List<Long> skuIds;

    @ApiModelProperty("标签")
    private List<PageShelvesItemSkuItemItemTagResponseDTO> itemTagList;
}
