package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderOperationDTO
*
* @author admin
* @date Mon Jul 13 19:13:18 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderOperationRequestDTO")
public class OrderOperationRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Integer id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
    * url
    */
    @ApiModelProperty(value = "url")
    private String api;
    /**
    * 类型
    */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
    * 启用
    */
    @ApiModelProperty(value = "启用")
    private Boolean enable;
    /**
    * 中文名
    */
    @ApiModelProperty(value = "中文名")
    private String chineseName;
    /**
    * 请求方式
    */
    @ApiModelProperty(value = "请求方式")
    private String method;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * 应用ID
    */
    @ApiModelProperty(value = "应用ID")
    private Long appId;
    /**
    * 是否删除,0否,1是
    */
    @ApiModelProperty(value = "是否删除,0否,1是")
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
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 更新人
    */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

}

