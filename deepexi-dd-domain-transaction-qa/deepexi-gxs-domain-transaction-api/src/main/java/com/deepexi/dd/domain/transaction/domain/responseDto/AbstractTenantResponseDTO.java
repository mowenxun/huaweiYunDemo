package com.deepexi.dd.domain.transaction.domain.responseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName : AbstractTenantVO
 * @Description : 抽象租户父类
 * @Author : liaopushu
 * @Date: 2020-07-3 18:01
 */
@Data
public class AbstractTenantResponseDTO {
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 0：未逻辑删除状态。1:删除
     */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
}
