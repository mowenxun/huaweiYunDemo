package com.deepexi.dd.system.mall.domain.vo.finance;

import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RefundReasonVO extends RefundReasonResponseDTO {
    @ApiModelProperty("退款支付流水号")
    private String refundPayCode;
}
