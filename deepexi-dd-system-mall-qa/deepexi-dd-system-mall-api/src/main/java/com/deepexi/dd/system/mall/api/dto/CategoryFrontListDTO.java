package com.deepexi.dd.system.mall.api.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryFrontListDTO {

    @ApiModelProperty("类目id")
    private Long categoryFrontId;
    @ApiModelProperty("类目名称")
    private String categoryFrontName;

}
