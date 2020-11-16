package com.deepexi.dd.domain.transaction.domain.dto.shoppingcart;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 购物车用户信息
 * @author yangwu
 * @date 2020/7/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "购物车用户信息")
public class ShoppingCartUserInfoDTO extends TenantDTO {
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("客户信息")
    private Map<String, List<String>> customerMap;

    @ApiModelProperty("客户信息s")
    private Map<Long, Long> customers;

    @ApiModelProperty("门店信息")
    private Map<Long, String> storeMap;

    @ApiModelProperty("1表示品牌商,0不是")
    private Integer companyType;


}
