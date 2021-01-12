package com.deepexi.dd.system.mall.domain.query.saleorder;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/26/11:55
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**商品授权给订单类型实体
 * @ClassName CommodityAuthorized
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/26 
 * @Version V1.0
 **/
@Data
public class CommodityAuthorized {

    @ApiModelProperty(value = "授权商品的skuId")
    private List<Long> authorizedskuId;

    @ApiModelProperty(value = "商品授权对应的供应商顶级组织id")
    private long authorizeBy;


}
