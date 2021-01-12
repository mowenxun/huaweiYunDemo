package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName PaymentCompletedInfoResponseDTO
 * @Description 支付完成后的支付信息
 * @Author SongTao
 * @Date 2020-07-28
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PaymentCompletedInfoResponseDTO")
public class PaymentCompletedInfoResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -1936716554158864547L;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "商品种类")
    private Long itemType;

    @ApiModelProperty(value = "商品数量")
    private Long itemToTalQuantity;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal itemTotalAmount;

    @ApiModelProperty(value="要求送达日期")
    private Date arriveDate;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "手机号码")
    private String mobile;
}
