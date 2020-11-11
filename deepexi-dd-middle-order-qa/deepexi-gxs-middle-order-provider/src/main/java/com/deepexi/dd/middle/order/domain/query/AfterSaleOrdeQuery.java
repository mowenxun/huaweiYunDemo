package com.deepexi.dd.middle.order.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * AfterSaleOrdeQuery
 *
 * @author admin
 * @version 1.0
 * @date Fri Oct 16 14:17:12 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AfterSaleOrdeQuery extends AbstractObject implements Serializable {

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
     * 店铺上级店铺id,普通店铺的上级店铺是直营店铺，直营店铺的上一级是它自己,用于解决顶级供销社，parentId=0，需要自己处理售后单的情况
     */
    private List<Long> parentShopIds;

    /**
     * 供应商Id
     */
    private Long sellerId;

    /**
     * 供应商Name
     */
    private Long sellerName;

    @ApiModelProperty(value = "单据日期开始", example = "2020-09-10 12:00:01")
    private String createdTimeFrom;
    @ApiModelProperty(value = "单据日期结束", example = "2020-10-10 12:00:01")
    private String createdTimeTo;

    @ApiModelProperty(value = "售后单号：模糊查询")
    private String orderCodeLike;

    @ApiModelProperty(value = "供应商名称：模糊查询")
    private String sellerNameLike;
    @ApiModelProperty(value = "供销社名称：模糊查询")
    private String alterReceiveNameLike;
    /**
     * 供货商编码
     */
    private String sellerCode;
}

