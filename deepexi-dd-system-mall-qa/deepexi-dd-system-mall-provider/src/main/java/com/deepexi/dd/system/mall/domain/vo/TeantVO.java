package com.deepexi.dd.system.mall.domain.vo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseTeantVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class TeantVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = -7692902221003048704L;

    @ApiModelProperty(value = "租户id")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;
}
