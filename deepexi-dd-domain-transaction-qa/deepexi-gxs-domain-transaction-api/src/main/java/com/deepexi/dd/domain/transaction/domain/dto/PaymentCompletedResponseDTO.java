package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName PaymentCompletedResponseDTO
 * @Description 完成支付后的信息
 * @Author SongTao
 * @Date 2020-07-28
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PaymentCompletedResponseDTO")
public class PaymentCompletedResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -5112289680832227422L;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "商品种类")
    private Long itemType;

    @ApiModelProperty(value = "商品数量")
    private Long itemToTalQuantity;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal itemTotalAmount;

    @ApiModelProperty(value = "要求送达日期")
    private Date arriveDate;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "付款状态【待付款:26、部分付款：27、已收讫：28】")
    private Integer status;

    @ApiModelProperty(value = "身份证号")
    private String idCardNumber;

    @ApiModelProperty(value = "车牌号")
    private String carNumberZt;

    @ApiModelProperty(value = "提货时间  自提")
    private Date pickGoodsTimeZt;

    @ApiModelProperty(value = "收货仓库Id 外仓")
    private Long receiveHouseIdWc;

    @ApiModelProperty(value = "收货仓库名称 外仓")
    private String receiveHouseNameWc;

    @ApiModelProperty(value = "工程名称")
    private String projectNameGd;

    @ApiModelProperty(value = "详细地址")
    private String detailedAddress;

    @ApiModelProperty(value = "区分标识")
    private String pickGoodsWayZt;

}
