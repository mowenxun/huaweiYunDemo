package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderOperationQuery
*
* @author admin
* @date Mon Jul 13 19:13:18 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderOperationRequestQuery")
public class OrderOperationRequestQuery extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

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
    * 入口类型
    */
    @ApiModelProperty(value = "入口类型")
    private String portal;
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

