package com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/05/12:42
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName DirectSkuPriceRequestDTO
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/9/5 
 * @Version V1.0
 **/
@Data
public class DirectSkuPriceRequestDTO {
    @ApiModelProperty("租户id")
    private String tenantId;
    @ApiModelProperty("应用id")
    private Long appId;
    @ApiModelProperty("商品skuIdList")
    private List<String> skuIdList;
    @ApiModelProperty("直供上架状态（0未上架 1已上架 2已下架）")
    private Integer onStatus;
}
