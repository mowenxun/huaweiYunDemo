package com.deepexi.dd.domain.transaction.domain.dto.finance;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-25 1:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "FinanceAmountPaymentReqDTO")
public class FinanceAmountPaymentReqDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 余额明细对象
     */
    @ApiModelProperty(value = "余额明细对象")
    private FinanceAmountDetailRequestDTO financeAmountDetailRequestDTO;

    /**
     * 收款单对象
     */
    @ApiModelProperty(value = "收款单对象")
    private FinanceCollectionAdminRequestDTO financeCollectionAdminRequestDTO;

    /**
     * 支付流水对象
     */
    @ApiModelProperty(value = "支付流水对象")
    private FinancePaymentRecordsRequestDTO financePaymentRecordsRequestDTO;

}
