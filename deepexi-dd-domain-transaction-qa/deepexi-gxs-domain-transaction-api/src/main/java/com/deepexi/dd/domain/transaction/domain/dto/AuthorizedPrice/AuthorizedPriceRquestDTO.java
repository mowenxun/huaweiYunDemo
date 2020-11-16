package com.deepexi.dd.domain.transaction.domain.dto.AuthorizedPrice;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/16:23
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**获得授权价格的入参
 * @ClassName AuthorizedPriceRquestDTO
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/28 
 * @Version V1.0
 **/
@Data
public class AuthorizedPriceRquestDTO {

    private Long appId;

    private String tenantId;

    @ApiModelProperty(value = "商品skuId")
    private Long skuId;

    @ApiModelProperty(value = "商品对应的授权卖家顶级组织id")
    private Long sellerId;

}
