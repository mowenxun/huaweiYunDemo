package com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/05/15:07
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SkuStockRequestDTO
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/9/5 
 * @Version V1.0
 **/
@Data
public class SkuStockRequestDTO {
    @ApiModelProperty("租户id")
    private String tenantId;
    @ApiModelProperty("应用id")
    private Long appId;
    @ApiModelProperty("商品skuIdList")
    private List<Long> skuIdList;
    @ApiModelProperty("")
    private Long shopId;
}
