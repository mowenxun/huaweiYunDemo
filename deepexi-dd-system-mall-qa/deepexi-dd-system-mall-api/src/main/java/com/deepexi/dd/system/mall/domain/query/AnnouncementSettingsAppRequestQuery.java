package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * CommonAnnouncementSettingsQuery
 *
 * @author admin
 * @version 1.0
 * @date Tue Jul 07 11:05:16 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "AnnouncementSettingsAppRequestQuery")
public class AnnouncementSettingsAppRequestQuery extends BasePage implements Serializable {


    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 公告标题
     */
    @ApiModelProperty(value = "公告标题")
    private String title;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    @NotBlank(message = "租户id不能为空.")
    private String tenantId;
    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    @NotNull(message = "APP标识不能为空.")
    private Long appId;

}

