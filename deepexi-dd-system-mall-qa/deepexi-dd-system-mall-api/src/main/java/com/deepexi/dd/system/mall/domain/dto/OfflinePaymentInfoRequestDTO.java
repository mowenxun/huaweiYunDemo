package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName OfflinePaymentInfoRequestDTO
 * @Description 保存线下付款信息
 * @Author SongTao
 * @Date 2020-07-27
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflinePaymentInfoRequestDTO")
public class OfflinePaymentInfoRequestDTO extends TenantDTO implements Serializable {
    private static final long serialVersionUID = -3070610720532586661L;

    @ApiModelProperty(value = "版本号",required = true)
    private String version;

    @ApiModelProperty(value = "订单ID",required = true)
    @NotNull(message = "订单ID为空")
    private Long saleOrderId;

    @ApiModelProperty(value = "支付金额")
    @NotNull(message = "支付金额为空")
    @Range(min = 0,message = "支付金额错误")
    private BigDecimal payAmount;
    @ApiModelProperty(value = "订单类型:0网批订单,1提货单")
    @NotNull(message = "订单类型错误")
    private Integer orderType;

    @ApiModelProperty("收款凭证url")
    private String credentialsUrl;

    @ApiModelProperty(value = "收款信息")
    OfflineCollectionsInfoDTO collectMoneyInfo;

    @ApiModelProperty(value = "付款信息")
    OfflinePaymentInfoDTO payInfo;
    @ApiModelProperty(value = "订单编号")
    @NotEmpty(message = "订单编号为空")
    private String orderCode;
}
