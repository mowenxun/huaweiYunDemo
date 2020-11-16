package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


/**
 * AfterSaleOrdeDO
 *
 * @author huanghuai
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class CreateAfterSaleOrderVO extends AppIdDTO{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "售后单的主键id：用于编辑操作")
    private Long id;
    @ApiModelProperty(value = "组织id（groupId）")
    @NotNull(message = "组织id不能为空")
    private Long orgId;
    //
    // /**
    //  * 供销社名称
    //  */
    // @ApiModelProperty(value = "售后接收方")
    // private String alterReceiveName;
    // /**
    //  * 售后接受方：店铺对应的供销社id
    //  */
    // @ApiModelProperty(value = "售后接受方：店铺对应的供销社id")
    // private Long alterReceiveId;
    // /**
    //  * 供销社编码
    //  */
    // @ApiModelProperty(value = "供销社编码")
    // private String alterReceiveCode;
    //
    // /**
    //  * 店铺名称(下单的店铺)
    //  */
    // @ApiModelProperty(value = "售后申请方：店铺名称(下单的店铺)")
    // private String shopName;
    // /**
    //  * 店铺ID(下单的店铺)
    //  */
    // @ApiModelProperty(value = "店铺ID(下单的店铺)")
    // private Long shopId;
    // /**
    //  * 店铺编码（下单的店铺）
    //  */
    // @ApiModelProperty(value = "店铺编码（下单的店铺）")
    // private String shopCode;
    @ApiModelProperty(value = "退货总金额")
    @NotNull(message = "退货总金额不能为空")
    private BigDecimal totalAmount;
    /**
     * 售后原因类型：0-质量问题；1-其他
     */
    @ApiModelProperty(value = "售后原因类型：0-质量问题；1-其他")
    @NotEmpty(message = "售后原因类型不能为空")
    private String reasonsType;
    /**
     * 问题描述
     */
    @ApiModelProperty(value = "售后原因：问题描述")
    @NotEmpty(message = "问题描述不能为空")
    private String question;
    /**
     * 图片地址,多个使用;隔开
     */
    @ApiModelProperty(value = "图片地址,多个使用;隔开")
    private String pictureUrl;

    @ApiModelProperty(value = "售后商品列表")
    @NotEmpty(message = "售后商品列表不能为空")
    private List<AfterSaleItem> afterSaleItems;

    @Valid
    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("CreateAfterSaleOrderVOAfterSaleItem")
    public static class AfterSaleItem extends AppIdDTO {
        private static final long serialVersionUID = -6010738422435696655L;

        @ApiModelProperty(value = "售后单item的主键id：用于编辑操作")
        private Long id;
        @ApiModelProperty(value = "商品名称")
        @NotEmpty(message = "商品名称不能为空")
        private String skuName;

        @ApiModelProperty(value = "所属供应商")
        @NotEmpty(message = "所属供应商不能为空")
        private String sellerName;

        @ApiModelProperty(value = "商品规格")
        @NotEmpty(message = "商品规格不能为空")
        private String skuFormat;

        @ApiModelProperty(value = "供货商ID")
        @NotNull(message = "供货商ID不能为空")
        private Long sellerId;

        @ApiModelProperty(value = "供货商编码")
        @NotEmpty(message = "供货商编码不能为空")
        private String sellerCode;

        @ApiModelProperty(value = "批发价")
        @NotNull(message = "批发价不能为空")
        private BigDecimal price;

        @ApiModelProperty(value = "退货数量")
        @NotNull(message = "退货数量不能为空")
        private Long backNum;

        @ApiModelProperty(value = "金额小计:退货合计金额")
        @NotNull(message = "退货合计金额不能为空")
        private BigDecimal totalAmount;

    }
}

