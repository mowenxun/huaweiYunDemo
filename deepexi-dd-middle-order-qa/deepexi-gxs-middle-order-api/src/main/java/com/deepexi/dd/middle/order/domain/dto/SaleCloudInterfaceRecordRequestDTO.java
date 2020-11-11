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
* SaleCloudInterfaceRecordDTO
*
* @author admin
* @date Wed Aug 26 19:53:05 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleCloudInterfaceRecordRequestDTO")
public class SaleCloudInterfaceRecordRequestDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * APP标识
    */
    @ApiModelProperty(value = "APP标识")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 0：未逻辑删除状态。1:删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
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
    * 接口名称
    */
    @ApiModelProperty(value = "接口名称")
    private String interfaceName;
    /**
    * 接口url
    */
    @ApiModelProperty(value = "接口url")
    private String interfaceUrl;
    /**
    * 输入参数
    */
    @ApiModelProperty(value = "输入参数")
    private String input;
    /**
    * 输出结果
    */
    @ApiModelProperty(value = "输出结果")
    private String output;
    /**
    * 类型
    */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;

}

