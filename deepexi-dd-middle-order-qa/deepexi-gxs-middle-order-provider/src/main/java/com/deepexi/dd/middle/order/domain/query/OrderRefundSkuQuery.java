package com.deepexi.dd.middle.order.domain.query;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderRefundSkuQuery
 *
 * @author admin
 * @date Wed Aug 19 16:31:13 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderRefundSkuQuery extends AbstractObject implements Serializable {

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
         * 主键
         */
        private Long id;
        /**
         * 租户
         */
        private String tenantId;
        /**
         * 应用id
         */
        private Long appId;
        /**
         * 版本号，乐观锁
         */
        private Integer version;
        /**
         * 创建时间
         */
        private Date createdTime;
        /**
         * 最后更新时间
         */
        private Date updatedTime;
        /**
         * 创建人
         */
        private String createdBy;
        /**
         * 修改人
         */
        private String updatedBy;
        /**
         * 数据隔离id
         */
        private String isolationId;
        /**
         * 备注
         */
        private String remark;
        /**
         * 主图地址
         */
        private String majorPicture;
        /**
         * 商品名称
         */
        private String skuName;
        /**
         * 商品规格
         */
        private String skuFormat;
        /**
         * 商品编号
         */
        private String skuCode;
        /**
         * 单价
         */
        private BigDecimal price;
        /**
         * 商品数量
         */
        private Long skuQuantity;
        /**
         * 商品出库总数量
         */
        private Long skuTotalQuantity;
        /**
         * 退款单id
         */
        private Long refundOrderId;
}

