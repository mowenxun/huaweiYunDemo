package com.deepexi.dd.domain.transaction.domain.dto.saleOutTask;

import com.deepexi.dd.domain.transaction.domain.dto.OrderDeliverySelfRaisingInfoReqDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.AbstractTenantResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderDeliverySelfRaisingInfoRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaleOutTaskDetailInfoResponseDTO
 * @Description 销售出库单详情
 * @Author SongTao
 * @Date 2020-07-11
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskDetailResponseDTO")
public class SaleOutTaskDetailResponseDTO extends AbstractTenantResponseDTO implements Serializable {
    private static final long serialVersionUID = -7113166338170671058L;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

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

    //2020/07/28 补充字段 SongTao
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "签收人")
    private String signBy;

    @ApiModelProperty(value = "签收时间")
    private Date signTime;

    //供app调用 增加订单id和code
    @ApiModelProperty(value = "订单id")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    //SongTao 2020/08/27 用于发货自提信息
//    @ApiModelProperty(value = "(自提)车牌号")
//    private String selfLicensePlate;f

    @ApiModelProperty(value = "(自提)提货人")
    private String saleRaisingName;

    @ApiModelProperty(value = "(自提)联系电话")
    private String telephone;

    @ApiModelProperty(value = "(自提)身份证号")
    private String idCardNumber;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提；2:小库；3:外仓；4:工地)")
    private Integer deliveryType;

    @ApiModelProperty(value = "订单自提地址信息")
    private OrderDeliverySelfRaisingInfoRequestDTO orderDeliverySelfRaisingInfoDTO;
}
