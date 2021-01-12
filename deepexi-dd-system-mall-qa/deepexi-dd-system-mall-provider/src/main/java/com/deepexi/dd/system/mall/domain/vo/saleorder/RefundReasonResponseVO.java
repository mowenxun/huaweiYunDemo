package com.deepexi.dd.system.mall.domain.vo.saleorder;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderRefundReasonDTO
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderRefundReasonResponseDTO")
public class RefundReasonResponseVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据编号
     */
    @ApiModelProperty(value = "单据编号")
    private String orderCode;
    /**
     * 申请金额
     */
    @ApiModelProperty(value = "申请金额")
    private BigDecimal applyAmount;


}

