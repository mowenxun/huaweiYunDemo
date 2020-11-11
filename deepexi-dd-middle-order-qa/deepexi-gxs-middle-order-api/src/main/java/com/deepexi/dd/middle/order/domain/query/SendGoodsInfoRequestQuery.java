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
* SendGoodsInfoQuery
*
* @author admin
* @date Tue Oct 13 15:15:24 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SendGoodsInfoRequestQuery")
public class SendGoodsInfoRequestQuery extends AbstractObject implements Serializable {

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
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
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
    * 修改人
    */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
    * 订单号
    */
    @ApiModelProperty(value = "订单号")
    private String orderCode;
    /**
    * 订单id
    */
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    /**
    * 发货时间
    */
    @ApiModelProperty(value = "发货时间")
    private Date sendTime;
    /**
    * 0-干线快运;1-零担物流；2-快递
    */
    @ApiModelProperty(value = "0-干线快运;1-零担物流；2-快递")
    private String sendType;
    /**
    * 收货供销社编码
    */
    @ApiModelProperty(value = "收货供销社编码")
    private String receiveDistributionCode;
    /**
    * 收货供销社id
    */
    @ApiModelProperty(value = "收货供销社id")
    private Long receiveDistributionId;
    /**
    * 收货供销社名称
    */
    @ApiModelProperty(value = "收货供销社名称")
    private String receiveDistributionName;
    /**
    * 收货地址
    */
    @ApiModelProperty(value = "收货地址")
    private String receiveAddress;
    /**
    * 收货电话
    */
    @ApiModelProperty(value = "收货电话")
    private String receiveMobile;
    /**
    * 物流公司
    */
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;
    /**
    * 物流单号
    */
    @ApiModelProperty(value = "物流单号")
    private String logisticsCode;
    /**
    * 车牌号
    */
    @ApiModelProperty(value = "车牌号")
    private String licensePlateNumber;
    /**
    * 司机名称
    */
    @ApiModelProperty(value = "司机名称")
    private String driverName;
    /**
    * 司机电话
    */
    @ApiModelProperty(value = "司机电话")
    private String driverPhone;
    /**
    * 货运部
    */
    @ApiModelProperty(value = "货运部")
    private String department;
}

