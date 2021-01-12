package com.deepexi.dd.system.mall.domain.vo.saleorder;

import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-24 12:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderRefundReasonResponseDTO")
public class RefundDetailsInfoVO extends AbstractObject implements Serializable {
    /**
     * 退款单基本信息
     */
    @ApiModelProperty(value = "退款单基本信息")
    private RefundReasonResponseDTO refundReasonInfo;

}
