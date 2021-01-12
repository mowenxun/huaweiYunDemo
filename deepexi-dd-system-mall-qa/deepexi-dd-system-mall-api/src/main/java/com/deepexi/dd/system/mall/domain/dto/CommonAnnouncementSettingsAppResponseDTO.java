package com.deepexi.dd.system.mall.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* CommonAnnouncementSettingsDTO
*
* @author admin
* @date Tue Jul 07 11:05:16 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "CommonAnnouncementSettingsAppResponseDTO")
public class CommonAnnouncementSettingsAppResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    * 公告详情
    */
    @ApiModelProperty(value = "公告详情")
    private String details;
    /**
    * 生效时间
    */
    @ApiModelProperty(value = "生效时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectiveTime;
    /**
    * 失效时间
    */
    @ApiModelProperty(value = "失效时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expirationTime;
    /**
    * 是否启用 1:启用, 0:禁用
    */
    @ApiModelProperty(value = "是否启用 1:启用, 0:禁用")
    private Integer isEnabled;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

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
    * 数据隔离id
    */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;

}

