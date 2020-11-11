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
* OrderDeliveryInfoQuery
*
* @author admin
* @date Wed Jul 01 19:40:51 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryInfoRequestQuery")
public class OrderDeliveryInfoRequestQuery extends AbstractObject implements Serializable {

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
    private Long id;
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
    * 销售出库单ID
    */
    @ApiModelProperty(value = "销售出库单ID")
    private Long saleOutTaskId;
    /**
    * 销售出库单编号
    */
    @ApiModelProperty(value = "销售出库单编号")
    private String saleOutTaskCode;
    /**
    * 物流公司名称
    */
    @ApiModelProperty(value = "物流公司名称")
    private String deliveryName;
    /**
    * 物流编号
    */
    @ApiModelProperty(value = "物流编号")
    private String deliveryCode;
    /**
    * 物流投递时间
    */
    @ApiModelProperty(value = "物流投递时间")
    private Date deliveryTime;
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

