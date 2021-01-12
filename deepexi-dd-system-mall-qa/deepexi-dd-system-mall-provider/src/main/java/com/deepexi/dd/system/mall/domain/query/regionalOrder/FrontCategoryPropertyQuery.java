package com.deepexi.dd.system.mall.domain.query.regionalOrder;

import com.deepexi.dd.system.mall.domain.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName FrontCategoryPropertyQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class FrontCategoryPropertyQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -311033839015426564L;

    @ApiModelProperty(value = "类目ids")
    private List<Long> categoryIds;
}
