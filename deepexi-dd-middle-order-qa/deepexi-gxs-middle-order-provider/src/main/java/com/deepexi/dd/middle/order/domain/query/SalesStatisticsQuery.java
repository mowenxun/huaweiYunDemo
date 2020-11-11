package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesStatisticsQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * APP标识
     */
    private Long appId;

    /**
     * 订单状态:draft 草稿,accept 待接单,deliver 待发货,receipt 待收货,examining 接单审核中,reject 接单驳回，payment 待付款，paid 收款待确认，over 交易完成，cancel 已取消
     */
    private Integer status;

    /**
     * 供货商ID
     */
    private Long sellerId;
    /**
     * 单据类型(ordinary:普通销售单)
     */
    private Integer ticketType;
    /**
     * 支付状态 0:未付款,1:已付款,2:部分付款
     */
    private Integer paymentStatus;
    /**
     * 隔离标识
     */
    private String isolationId;

    private String createTimeFrom;

    /**
     * 创建时间结束
     */

    private String createTimeTo;

    /**
     * 过滤状态列表
     */
    private List<Integer> exceptStatusList;
    /**
     * 付款状态
     */
    private List<Integer> paymentStatusList;
    /**
     * 拆单所属组织
     */
    @ApiModelProperty("拆单所属一级组织")
    private Long ascriptionOrgId;
    /**
     * 单据类型多选
     */
    private List<Integer> ticketTypeList;
    /**
     * 订单状态:从业务链路获取
     */
    private List<Integer> statusList;
}
