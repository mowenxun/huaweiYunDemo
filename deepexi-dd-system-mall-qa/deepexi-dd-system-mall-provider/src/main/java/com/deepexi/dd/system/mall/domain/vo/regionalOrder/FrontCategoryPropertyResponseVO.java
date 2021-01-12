package com.deepexi.dd.system.mall.domain.vo.regionalOrder;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName FrontCategoryPropertyResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class FrontCategoryPropertyResponseVO extends BaseResponseVO implements Serializable {

    private static final long serialVersionUID = -7753571728899601973L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("inputType")
    private Integer inputType;

    @ApiModelProperty("parentId")
    private Long parentId;

    @ApiModelProperty("数据path")
    private String path;

    @ApiModelProperty("属性编码")
    private String propertyCode;

    @ApiModelProperty("属性描述")
    private String propertyDesc;

    @ApiModelProperty("属性名称")
    private String propertyName;

    @ApiModelProperty("属性值列表")
    private List<FrontCategoryPropertyValueResponseVO> propertyValueList;
}
