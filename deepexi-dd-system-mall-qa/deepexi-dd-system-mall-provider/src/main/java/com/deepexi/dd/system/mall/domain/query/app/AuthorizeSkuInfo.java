package com.deepexi.dd.system.mall.domain.query.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName AuthorizeSkuInfo
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-09-09
 * @Version 1.0
 **/
@Data
public class AuthorizeSkuInfo implements Serializable {

    private static final long serialVersionUID = 6211269634934117214L;

    @ApiModelProperty("商品skuId")
    private List<Long> skuIds;

    @ApiModelProperty("商品授权的组织id")
    private Long authorizeBy;
}
