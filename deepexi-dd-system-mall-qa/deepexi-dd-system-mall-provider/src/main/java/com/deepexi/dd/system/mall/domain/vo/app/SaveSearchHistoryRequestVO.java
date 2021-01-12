package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/27 15:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class SaveSearchHistoryRequestVO extends AbstractObject implements Serializable {

    /**
     * 租户id
     */
    @NotBlank(message = "租户id不能为空")
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空")
    @ApiModelProperty(value = "应用id")
    private Long appId;
    /**
     * 数据隔离id
     */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;
    /**
     * 关键字
     */
    @NotBlank(message = "关键字不能为空")
    @ApiModelProperty("关键字")
    private String keyword;
    @ApiModelProperty("创建人")
    private String createdBy;
    @ApiModelProperty("修改人")
    private String updatedBy;
}
