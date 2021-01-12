package com.deepexi.dd.system.mall.domain.query.app;

import com.deepexi.util.pojo.AbstractObject;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CommoditySkuPropertyValueQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-21
 * @Version 1.0
 **/
@Data
@ApiModel
public class CommoditySkuPropertyValueQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 6968235072635418842L;

    @SerializedName("属性值")
    private String propertyValue;

    @SerializedName("属性值id")
    private Long propertyValueId;
}
