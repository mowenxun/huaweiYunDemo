package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SaleOrderInfoReceiptRequestQuery
 *
 * @author chenqili
 * @date 2020/7/15
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "按单收款页面销售订单查询参数对象")
public class SaleOrderInfoReceiptRequestQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    // 分页
    @ApiParam(value = "page", example = "-1")
    private Integer page = -1;
    @ApiParam(value = "size", example = "20")
    private Integer size = 20;

    // 时间
//    @DateTimeFormat(pattern = DateUtils.DEFAULT_DATE_TIME_FORMAT)
    private String createTimeFrom;
    //    @DateTimeFormat(pattern = DateUtils.DEFAULT_DATE_TIME_FORMAT)
    private String createTimeTo;

    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String code;


    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String partnerName;  //customerName就是partnerName


    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "支付状态 0:未付款,1:已付款,2:部分付款")
    private Integer paymentStatus;
}

