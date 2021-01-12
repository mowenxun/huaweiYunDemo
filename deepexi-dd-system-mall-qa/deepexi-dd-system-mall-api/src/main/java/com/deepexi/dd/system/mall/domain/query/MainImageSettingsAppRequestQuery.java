package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 广告图管理
 * </p>
 *
 * @author zq
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MainImageSettingsQuery", description = "广告图管理")
public class MainImageSettingsAppRequestQuery extends AbstractObject {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    @NotNull(message = "APP标识不能为空")
    private Long appId;

    @ApiModelProperty("主图类型：0首页主图 ｜ 1区域主图")
    @NotNull(message = "主图类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "租户id",required = true, example = "gree")
    @NotNull(message = "租户id不能为空")
    private String tenantId;

    private String isolationId;
}