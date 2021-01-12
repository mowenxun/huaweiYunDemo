package com.deepexi.dd.system.mall.domain.dto.saleorder;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yy
 * @version 1.0
 * @date 2020-08-29 19:02
 */
@Data
@ApiModel
public class GetPaySettingRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("租户id")
    @NotNull(
            message = "租户id不能为空"
    )
    private String tenantId;
    @ApiModelProperty("应用id")
    private Long appId;
    @ApiModelProperty("表单类型")
    private String listType;
    @ApiModelProperty("表单id")
    private Long listId;
    @ApiModelProperty("链路业务id")
    private String businessCode;

    /**
     * 是否启用;1-启用;0-禁用
     */
    @ApiModelProperty(value = "是否启用;1-启用;0-禁用")
    private Integer isEnabled;
    /**
     * 业务伙伴id
     */
    @ApiModelProperty(value = "业务伙伴id")
    private Integer partnerId;
}
