package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SaleOutTaskQuery
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOutTaskMiddleQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页数
     */
    private Integer size = 10;

        /**
         * 
         */
        private Long id;
        /**
         * 租户ID
         */
        private String tenantId;
        /**
         * 应用ID
         */
        private Long appId;
        /**
         * 版本号
         */
        private Integer version;
        /**
         * 备注
         */
        private String remark;
        /**
         * 创建时间
         */
        private Date createdTime;
        /**
         * 更新时间
         */
        private Date updatedTime;
        /**
         * 出库单编号
         */
        private String code;
        /**
         * 单据类型:out 销售出库单
         */
        private String type;
        /**
         * 订单ID
         */
        private Long saleOrderId;
        /**
         * 订单编号
         */
        private String saleOrderCode;
        /**
         * 订单确认状态 0:已确认,1已作废
         */
        private Integer status;
        /**
         * 单据日期
         */
        private Date ticketDate;
        /**
         * 计划出库总数量
         */
        private Long skuQuantity;
        /**
         * 出库单类型,1原单,2蓝单,3红单
         */
        private Integer taskType;
        /**
         * 红冲的原订单标识
         */
        private Long hedgeOrder;
        /**
         * 发货物流ID
         */
        private Long orderDeliveryId;

    private String buyerName ;

}

