package com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName offlinePayInfoRequestDTO
 * @Description 保存线下付款信息
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflinePayInfoRequestDTO")
public class OfflinePayInfoRequestDTO extends TenantDTO  implements Serializable {
    private static final long serialVersionUID = -6051796585103799214L;

    @ApiModelProperty(value = "版本号",required = true)
    private String version;

    @ApiModelProperty(value = "订单ID",required = true)
    @NotNull(message = "订单ID为空")
    private Long saleOrderId;

    @ApiModelProperty(value = "支付金额")
    @NotNull(message = "支付金额为空")
    @Range(min = 0,message = "支付金额错误")
    private BigDecimal payAmount;

    @ApiModelProperty("收款凭证url")
    private String credentialsUrl;

    @ApiModelProperty(value = "收款信息")
    OfflineCollectionInfoDTO collectMoneyInfo;

    @ApiModelProperty(value = "付款信息")
    OfflinePayInfoDTO payInfo;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

}

