package com.deepexi.dd.system.mall.domain.vo.orderOnline;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import com.deepexi.sdk.commodity.model.EsGoodOnlineGroupSkuResponseDTO;
import com.deepexi.sdk.commodity.model.EsGoodOnlineItemWholeResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName EsGoodOnlineResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-19
 * @Version 1.0
 **/
@Data
@ApiModel
public class EsGoodOnlineResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -7285242049472588765L;
    
    @ApiModelProperty("channelId")
    private Long channelId = null;

    @ApiModelProperty("channelName")
    private String channelName = null;

    @ApiModelProperty("createdTime")
    private Date createdTime = null;

    @ApiModelProperty("id")
    private Long id = null;

    @ApiModelProperty("itemId")
    private Long itemId = null;

    @ApiModelProperty("itemWhole")
    private EsGoodOnlineItemWholeResponseDTO itemWhole = null;

    @ApiModelProperty("priceSystemId")
    private Long priceSystemId = null;

    @ApiModelProperty("releaseVersion")
    private Integer releaseVersion = null;

    @ApiModelProperty("shopId")
    private Long shopId = null;

    @ApiModelProperty("shopName")
    private String shopName = null;

    @ApiModelProperty("sku")
    private EsGoodOnlineGroupSkuResponseDTO sku = null;

    @ApiModelProperty("skuId")
    private Long skuId = null;

    @ApiModelProperty("status")
    private Integer status = null;

    @ApiModelProperty("updatedTime")
    private Date updatedTime = null;
}
