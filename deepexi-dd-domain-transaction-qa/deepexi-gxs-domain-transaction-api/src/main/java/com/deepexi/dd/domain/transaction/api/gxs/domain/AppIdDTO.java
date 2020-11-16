package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接入方信息
 *
 * @author huanghuai
 * @version 1.0
 * @date 2020-10-15 19:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("AppIdDTO")
public class AppIdDTO extends TenantDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 接入方id
     */
    @ApiModelProperty("接入方id")
    private Long appId;

}
