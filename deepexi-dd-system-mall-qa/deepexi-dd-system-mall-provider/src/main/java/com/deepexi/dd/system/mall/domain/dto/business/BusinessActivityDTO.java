package com.deepexi.dd.system.mall.domain.dto.business;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* BusinessActivityDTO
*
* @author admin
* @date Mon Dec 28 17:07:30 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "BusinessActivityResponseDTO")
public class BusinessActivityDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
    * 活动名称
    */
    @ApiModelProperty(value = "活动名称")
    private String name;
    /**
    * 标签名称
    */
    @ApiModelProperty(value = "标签名称")
    private String labelName;
    /**
    * 标签背景色
    */
    @ApiModelProperty(value = "标签背景色")
    private String backgroundColor;
    /**
    * 标签字体颜色
    */
    @ApiModelProperty(value = "标签字体颜色")
    private String fontColor;
    /**
    * 活动开始时间
    */
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;
    /**
    * 活动结束时间
    */
    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;
    /**
    * 活动状态：1 未开始，2 进行中，3 已结束，4 已终止
    */
    @ApiModelProperty(value = "活动状态：1 未开始，2 进行中，3 已结束，4 已终止")
    private Integer status;
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
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long itemId;

}

