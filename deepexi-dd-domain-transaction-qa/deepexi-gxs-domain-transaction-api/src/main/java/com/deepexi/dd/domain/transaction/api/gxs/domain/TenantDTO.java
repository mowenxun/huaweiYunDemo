package com.deepexi.dd.domain.transaction.api.gxs.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 租户信息
 *
 * @author huanghuai
 * @version 1.0
 * @date 2020-10-15 19:02
 */
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel("TenantDTO")
public class TenantDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id", required = true)
    @NotBlank(message = "租户id不能为空")
    private String tenantId;

}
