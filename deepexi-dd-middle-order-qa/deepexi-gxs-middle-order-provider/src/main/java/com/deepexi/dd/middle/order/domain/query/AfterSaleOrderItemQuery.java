package com.deepexi.dd.middle.order.domain.query;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AfterSaleOrderItemQuery
 *
 * @author admin
 * @date Fri Oct 16 14:17:13 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AfterSaleOrderItemQuery extends AbstractObject implements Serializable {

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
         * 创建人
         */
        private String createdBy;
        /**
         * 创建时间
         */
        private Date createdTime;
        /**
         * 更新时间
         */
        private Date updatedTime;
        /**
         * 修改人
         */
        private String updatedBy;
        /**
         * 订单ID
         */
        private Long afterSaleOrderId;
        /**
         * 订单编号
         */
        private String afterSaleOrderCode;
        /**
         * 单位id
         */
        private Long unitId;
        /**
         * 单位名称
         */
        private String unitName;
        /**
         * 商品ID
         */
        private Long skuId;
        /**
         * 店铺ID
         */
        private Long shopId;
        /**
         * 店铺名称
         */
        private String shopName;
        /**
         * 店铺编码
         */
        private String shopCode;
        /**
         * 供货商ID
         */
        private Long sellerId;
        /**
         * 供货商名称
         */
        private String sellerName;
        /**
         * 供货商编码
         */
        private String sellerCode;
        /**
         * 商品名称
         */
        private String skuName;
        /**
         * 商品编号
         */
        private String skuCode;
        /**
         * 商品规格
         */
        private String skuFormat;
        /**
         * 商品数量
         */
        private Long skuQuantity;
        /**
         * 税率
         */
        private BigDecimal taxRate;
        /**
         * 单价
         */
        private BigDecimal price;
        /**
         * 金额小计
         */
        private BigDecimal totalAmount;
        /**
         * 成本价
         */
        private BigDecimal costPrice;
        /**
         * 主图地址
         */
        private String majorPicture;
        /**
         * 签收数量
         */
        private Long signQuantity;
        /**
         * 建议零售价
         */
        private BigDecimal proposePrice;
        /**
         * 县级门店批发价
         */
        private BigDecimal countyPrice;
        /**
         * 镇级门店批发价
         */
        private BigDecimal townPrice;
        /**
         * 村级门店批发价
         */
        private BigDecimal villagePrice;
        /**
         * 团购级门店批发价
         */
        private BigDecimal groupPurchasePrice;
        /**
         * 集采价格
         */
        private BigDecimal collectPurchasePrice;
        /**
         * 退货数量
         */
        private Long backNum;

    /**
     *品牌ID
     */
    private Long brandId;

    /**
     * 品牌编码
     */
    private Long brandCode;

    /**
     * 品牌名称
     */
    private Long brandName;
}

