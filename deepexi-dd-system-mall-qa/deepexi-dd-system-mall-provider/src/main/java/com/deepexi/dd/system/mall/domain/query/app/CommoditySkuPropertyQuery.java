package com.deepexi.dd.system.mall.domain.query.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CommoditySkuPropertiesQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-21
 * @Version 1.0
 **/
@Data
@ApiModel
public class CommoditySkuPropertyQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 5188297597347164769L;

    @ApiModelProperty("属性id")
    private Long propertyId;

    @ApiModelProperty("属性名称")
    private String propertyName ;

    @ApiModelProperty("属性值列表")
    private List<CommoditySkuPropertyValueQuery> propertyValueList;
}
