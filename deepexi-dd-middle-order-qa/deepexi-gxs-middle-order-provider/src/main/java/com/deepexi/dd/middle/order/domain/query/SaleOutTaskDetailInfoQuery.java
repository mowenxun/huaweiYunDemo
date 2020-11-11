package com.deepexi.dd.middle.order.domain.query;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;
import java.util.Date;

/**
 * SaleOutTaskDetailInfoQuery
 *
 * @author admin
 * @date Thu Sep 17 17:01:09 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOutTaskDetailInfoQuery extends AbstractObject implements Serializable {

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
         * 订单ID
         */
        private Long saleOrderId;
        /**
         * 提货单ID
         */
        private Long salePickGoodsId;
        /**
         * 提货单编号
         */
        private String salePickGoodsCode;
        /**
         * 发货计划ID
         */
        private Long saleDeliveryPlanId;
        /**
         * 发货计划编号
         */
        private String saleDeliveryPlanCode;
        /**
         * 销售出库单ID
         */
        private Long saleOutTaskId;
        /**
         * 商品明细ID
         */
        private Long saleOrderItemId;
        /**
         * 商品本次出库数量
         */
        private Long skuShipmentQuantity;
        /**
         * 商品本次申请数量
         */
        private Long skuPickQuantity;
}

