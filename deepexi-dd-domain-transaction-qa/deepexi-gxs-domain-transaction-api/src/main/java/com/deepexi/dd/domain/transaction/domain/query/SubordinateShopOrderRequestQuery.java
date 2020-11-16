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
import java.util.Date;

/**
* 下属门店订单查询类
*
* @author Leon Wong
* @date Tue Oct 16 14:53:15 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SubordinateShopOrderRequestQuery")
public class SubordinateShopOrderRequestQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    public SubordinateShopOrderRequestQuery() {
        // 默认页码
        this.page =  1;
        // 默认页大小
        this.size = 10;
    }

    /**
    * 页码
    */
    @ApiModelProperty(value = "页码")
    @Min(value = -1, message = "页码填写错误")
    private Integer page;

    /**
    * 页数
    */
    @ApiModelProperty(value = "页数")
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
    @ApiModelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "下单开始时间,YYYY-MM-DD hh:mm:ss 前端在天后面拼接 00:00:00")
    private String createTimeFrom;

    @ApiModelProperty(value = "下单结束时间， YYYY-MM-DD hh:mm:ss 前端在天后面拼接 23:59:59")
    private String createTimeTo;

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

    /**
    * 提货供销社编码
    */
    @ApiModelProperty(value = "提货供销社编码")
    private String deliveryDistributionCode;

    /**
    * 提货供销社id
    */
    @ApiModelProperty(value = "提货供销社id")
    private Long deliveryDistributionId;

    /**
    * 提货供销社名称
    */
    @ApiModelProperty(value = "提货供销社名称")
    private String deliveryDistributionName;

    /**
    * 提货地址
    */
    @ApiModelProperty(value = "提货地址")
    private String deliveryAddress;

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
    * 支付类型:1线下支付,2在线支付,3信用支付,4余额支付
    */

    @ApiModelProperty(value = "支付类型:1线下支付,2在线支付,3信用支付,4余额支付")
    private Integer paymentType;

    /**
    * 支付状态
    */
    @ApiModelProperty(value = "支付状态")
    private Integer paymentStatus;

    /**
    * 要货日期
    */
    @ApiModelProperty(value = "要货日期")
    private Date arriveDate;

    /**
    * 已支付金额
    */
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal payAmount;

    /**
    * 支付流水关联订单号 支付后才会生成
    */
    @ApiModelProperty(value = "支付流水关联订单号 支付后才会生成")
    private String payOrderCode;

    @ApiModelProperty(value = "商品编码")
    private String skuCode;

    @ApiModelProperty(value = "商品名称")
    private String skuName;
}

