package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AfterSaleOrdeDTO
 *
 * @author admin
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AfterSaleOrdeDTO extends AbstractObject implements Serializable {

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
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0：未逻辑删除状态。1:删除
     */
    private Boolean deleted;

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
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 店铺ID(下单的店铺)
     */
    private Long shopId;

    /**
     * 店铺名称(下单的店铺)
     */
    private String shopName;

    /**
     * 店铺编码（下单的店铺）
     */
    private String shopCode;

    /**
     * 商品总数
     */
    private Long quantity;

    /**
     * 总商品金额
     */
    private BigDecimal totalAmount;

    /**
     * 售后接受方：店铺对应的供销社id
     */
    private Long alterReceiveId;

    /**
     * 供销社编码
     */
    private String alterReceiveCode;

    /**
     * 供销社名称
     */
    private String alterReceiveName;

    /**
     * 售后原因类型：0-质量问题；1-其他
     */
    private String reasonsType;

    /**
     * 问题描述
     */
    private String question;

    /**
     * 图片地址,多个使用;隔开
     */
    private String pictureUrl;

    /**
     * 店铺上级店铺id,普通店铺的上级店铺是直营店铺，直营店铺的上一级是它自己
     */
    private Long parentShopId;
    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID")
    private Long sellerId;
    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称")
    private String sellerName;
    /**
     * 供货商编码
     */
    @ApiModelProperty(value = "供货商编码")
    private String sellerCode;

}

