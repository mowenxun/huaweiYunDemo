package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Leon Wong
 * @version V1.0
 * @date 2020/10/17
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SubordinateShopAfterSaleOrderRequestQuery")
public class SubordinateShopAfterSaleOrderRequestQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    public SubordinateShopAfterSaleOrderRequestQuery() {
        // 默认页码
        this.page =  1;
        // 默认页大小
        this.size = 10;
    }

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    @Min(value = -1, message = "页码填写错误")
    private Integer page;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数", example = "10")
    @Min(message = "页大小填写错误错误", value = 1)
    @Max(value = 1000,message = "单页元素最大为1000")
    private Integer size;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = true)
    @NotBlank(message = "租户信息不能为空")
    private String tenantId;
    /**
     * APP标识
     */
    @ApiModelProperty(value = "APP标识")
    private Long appId;
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态状态：1待审核 2审核通过 3驳回 4已完成")
    private String status;
    /**
     * 店铺ID(下单的店铺)
     */
    @ApiModelProperty(value = "店铺ID(下单的店铺)")
    private Long shopId;
    /**
     * 店铺名称(下单的店铺)
     */
    @ApiModelProperty(value = "店铺名称(下单的店铺)")
    private String shopName;
    /**
     * 店铺编码（下单的店铺）
     */
    @ApiModelProperty(value = "店铺编码（下单的店铺）")
    private String shopCode;

    /**
     * 店铺上级店铺id,普通店铺的上级店铺是直营店铺，直营店铺的上一级是它自己
     * 数据隔离，下属门店必须带上直营门店id进行查询
     */
    @ApiModelProperty(value = "店铺上级店铺id,普通店铺的上级店铺是直营店铺，直营店铺的上一级是它自己", required = true)
    @NotNull(message = "数据隔离，直营店铺id不能为空")
    private Long parentShopId;

    @ApiModelProperty(value = "单据日期开始", example = "2020-09-10 12:00:01")
    private String createdTimeFrom;
    @ApiModelProperty(value = "单据日期结束", example = "2020-10-10 12:00:01")
    private String createdTimeTo;

    /**
     * 商品总数
     */
    @ApiModelProperty(value = "商品总数")
    private Long quantity;
    /**
     * 总商品金额
     */
    @ApiModelProperty(value = "总商品金额")
    private BigDecimal totalAmount;
    /**
     * 售后接受方：店铺对应的供销社id
     */
    @ApiModelProperty(value = "售后接受方：店铺对应的供销社id")
    private Long alterReceiveId;
    /**
     * 供销社编码
     */
    @ApiModelProperty(value = "供销社编码")
    private String alterReceiveCode;
    /**
     * 供销社名称
     */
    @ApiModelProperty(value = "供销社名称")
    private String alterReceiveName;
    /**
     * 售后原因类型：0-质量问题；1-其他
     */
    @ApiModelProperty(value = "售后原因类型：0-质量问题；1-其他")
    private String reasonsType;
    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述")
    private String question;
    /**
     * 图片地址,多个使用;隔开
     */
    @ApiModelProperty(value = "图片地址,多个使用;隔开")
    private String pictureUrl;

    @ApiModelProperty(value = "售后单号：模糊查询")
    private String orderCodeLike;

    @ApiModelProperty(value = "供应商名称：模糊查询")
    private String sellerNameLike;

    @ApiModelProperty(value = "供销社名称：模糊查询")
    private String alterReceiveNameLike;

}
