package com.deepexi.dd.domain.transaction.domain.query.export;

import com.deepexi.dd.domain.transaction.domain.query.BasePage;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SaleOrderQueryPage extends BasePage implements Serializable {
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", dataType = "String")
    private String code;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    @ApiModelProperty(value = "单据类型(ordinary:普通销售单)", dataType = "String")
    private Integer ticketType;

    /**
     * 下单类型:(agent:代客下单,mall:h5商城下单)
     */
    @ApiModelProperty(value = "下单类型:(agent:代客下单,mall:h5商城下单)", dataType = "String")
    private String buyerType;

    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户编号", dataType = "Long")
    private Long buyerId;


    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称", dataType = "String")
    private String buyerName;
    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

}
