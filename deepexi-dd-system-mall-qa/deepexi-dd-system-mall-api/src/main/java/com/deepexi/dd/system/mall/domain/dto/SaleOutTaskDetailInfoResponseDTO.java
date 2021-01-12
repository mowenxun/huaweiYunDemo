package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import com.deepexi.dd.system.mall.domain.AbstractTenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaleOutTaskDetailInfoResponseDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-21
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailInfoResponseDTO")
public class SaleOutTaskDetailInfoResponseDTO extends AbstractTenantDTO implements Serializable {
    private static final long serialVersionUID = -4329942345297157400L;

    //也是制单人
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "出库单号")
    private String code;

    @ApiModelProperty(value = "收货地址")
    private String shippingAddress;

    //物流信息 SongTao 2020-07-11
    @ApiModelProperty(value = "物流公司")
    private String deliveryName;

    @ApiModelProperty(value = "物流单号")
    private String deliveryCode;

    @ApiModelProperty(value = "车牌号码")
    private String licensePlate;

    @ApiModelProperty(value = "司机名称")
    private String driver;

    @ApiModelProperty(value = "司机电话")
    private String driverMobile;

    @ApiModelProperty(value = "身份证号（司机）")
    private String idCardNum;

    @ApiModelProperty(value = "签收状态(17:待收货；19:已签收)")
    private Integer signStatus;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "签收人")
    private String signBy;

    @ApiModelProperty(value = "签收时间")
    private Date signTime;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "订单类型=0:直供单,1:标准订单,2:非标准订单,3:订货计划单",dataType = "Integer")
    private Integer ticketType;

    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", dataType = "String")
    private String buyerName;

    @ApiModelProperty(value = "订单自提地址信息")
    private OrderDeliverySelfRaisingInfoRequestDTO orderDeliverySelfRaisingInfoDTO;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提；2:小库；3:外仓；4:工地)")
    private Integer deliveryType;
}
