package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName OrderItemUpdateRequestDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderItemUpdateRequestDTO")
public class OrderItemUpdateRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 3415143444141387359L;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
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
     * 第三方子单号
     */
    @ApiModelProperty(value = "第三方子单号")
    private String subExternalOrderCode;
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private String orderItemState;
    /**
     * 物流名称
     */
    @ApiModelProperty(value = "物流名称")
    private String scac;
    /**
     * 物流单号
     */
    @ApiModelProperty(value = "物流单号")
    private String trackingNo;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "出库单号")
    private String saleOutTaskCode;

}
