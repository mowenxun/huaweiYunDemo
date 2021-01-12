package com.deepexi.dd.system.mall.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName : FinancePaymentRecordQuery
 * @Description : 支付流水查询对象
 * @Author : yuanzaishun
 * @Date: 2020-08-18 19:16
 */
@ApiModel(value = "支付流水查询对象")
@Data
public class FinancePaymentRecordQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "订单类型1采购订单,2提货订单",required = true)
    @NotNull(message = "订单类型为空")
    private Integer type;

    @ApiModelProperty(value = "id,逗号分隔",required = true)
    @NotEmpty(message = "订单ID为空")
    private String ids;
}
