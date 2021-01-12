package com.deepexi.dd.system.mall.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* CommonMainImageSettingsDTO
*
* @author admin
* @date Tue Jul 07 11:05:16 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "CommonMainImageSettingsAppResponseDTO")
public class CommonMainImageSettingsAppResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

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
    * 图片url
    */
    @ApiModelProperty(value = "图片url")
    private String imageUrl;
    /**
    * 排序
    */
    @ApiModelProperty(value = "排序")
    private String sort;
    /**
    * 链接
    */
    @ApiModelProperty(value = "链接")
    private String link;
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
    @TableField(value = "商品id")
    private Long goodId;
}

