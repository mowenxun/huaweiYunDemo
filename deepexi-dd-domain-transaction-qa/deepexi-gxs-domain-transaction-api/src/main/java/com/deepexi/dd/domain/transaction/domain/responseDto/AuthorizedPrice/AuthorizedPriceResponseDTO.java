package com.deepexi.dd.domain.transaction.domain.responseDto.AuthorizedPrice;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/28/16:26
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName AuthorizedPriceResponseDTO
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/28
 * @Version V1.0
 **/
@Data
public class AuthorizedPriceResponseDTO {
    @ApiModelProperty(value = "商品skuId")
    private Long skuId;

    @ApiModelProperty(value = "商品对应的授权卖家顶级组织id")
    private Long sellerId;

    @ApiModelProperty(value = "授权价格")
    private BigDecimal authorizedPrice;

}
