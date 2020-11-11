package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* OrderDeliveryInfoDTO
*
* @author admin
* @date Wed Jul 01 19:40:51 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryInfoResponseDTO")
public class OrderDeliveryInfoResponseDTO extends AbstractObject implements Serializable {
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

    @ApiModelProperty(value = "物流备注")
    private String remark;

    @ApiModelProperty(value = "车牌号码")
    private String licensePlate;

    @ApiModelProperty(value = "司机名称")
    private String driver;

    @ApiModelProperty(value = "司机电话")
    private String driverMobile;

    @ApiModelProperty(value = "身份证号码")
    private String idCardNum;
}

