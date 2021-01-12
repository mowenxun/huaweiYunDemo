package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.dd.system.mall.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName OfflinePaymentInfoRequestQuery
 * @Description 线下支付
 * @Author SongTao
 * @Date 2020-07-27
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflinePaymentInfoRequestQuery")
public class OfflinePaymentInfoRequestQuery extends TenantDTO implements Serializable {
    private static final long serialVersionUID = -4725375419400731458L;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "订单ID",required = true)
    @NotNull(message = "订单ID为空")
    private Long saleOrderId;
    @ApiModelProperty(value = "订单编号",required = true)
    @NotEmpty(message = "订单编号为空")
    private String orderCode;
    @ApiModelProperty(value = "订单类型",required = true)
    @NotNull(message = "订单类型为空")
    private Integer orderType;
}
