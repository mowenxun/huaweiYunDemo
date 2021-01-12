package com.deepexi.dd.system.mall.domain.vo.regionalOrder;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FrontCategoryPropertyValueResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class FrontCategoryPropertyValueResponseVO extends BaseResponseVO implements Serializable {

    @ApiModelProperty("属性id")
    private Long id;

    @ApiModelProperty("属性值的id")
    private Long propertyId;

    @ApiModelProperty("属性值的数据值")
    private String propertyValue;
}
