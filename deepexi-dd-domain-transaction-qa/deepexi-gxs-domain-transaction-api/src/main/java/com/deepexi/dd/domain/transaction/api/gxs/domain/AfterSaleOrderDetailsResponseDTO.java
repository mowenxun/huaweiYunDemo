package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;


/**
 * details
 *
 * @author huanghuai
 * @version 1.0
 * @date Fri Oct 16 14:17:12 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class AfterSaleOrderDetailsResponseDTO extends AppIdDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "售后单的主键id：用于编辑操作")
    private Long id;
    /**
     * 1待审核 2审核通过 3驳回 4已完成
     */
    @ApiModelProperty(value = "状态：1待审核 2审核通过 3驳回 4已完成")
    private String status;
    /**
     * 供销社名称
     */
    @ApiModelProperty(value = "售后接收方")
    private String alterReceiveName;
    /**
     * 店铺名称(下单的店铺)
     */
    @ApiModelProperty(value = "售后申请方：店铺名称(下单的店铺)")
    private String shopName;
    /**
     * 售后原因类型：0-质量问题；1-其他
     */
    @ApiModelProperty(value = "售后原因类型：0-质量问题；1-其他")
    private String reasonsType;
    /**
     * 问题描述
     */
    @ApiModelProperty(value = "售后原因：问题描述")
    private String question;
    /**
     * 图片地址,多个使用;隔开
     */
    @ApiModelProperty(value = "图片地址,多个使用;隔开")
    private String pictureUrl;

    @ApiModelProperty(value = "售后商品列表")
    private List<AfterSaleItem> afterSaleItems;
    @ApiModelProperty(value = "退货总金额")
    private BigDecimal totalAmount;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("AfterSaleOrderDetailsResponseDTOAfterSaleItem")
    public static class AfterSaleItem extends AppIdDTO {

        @ApiModelProperty(value = "售后单item的主键id：用于编辑操作")
        private Long id;
        private static final long serialVersionUID = -6010738422435696655L;
        @ApiModelProperty(value = "商品名称")
        private String skuName;
        @ApiModelProperty(value = "所属供应商")
        private String sellerName;
        @ApiModelProperty(value = "商品规格")
        private String skuFormat;
        @ApiModelProperty(value = "批发价")
        private BigDecimal price;
        @ApiModelProperty(value = "退货数量")
        private Long backNum;
        @ApiModelProperty(value = "此商品退货合计金额")
        private BigDecimal totalAmount;
        @ApiModelProperty(value = "品牌ID")
        private Long brandId;
        @ApiModelProperty(value = "品牌编码")
        private Long brandCode;
        @ApiModelProperty(value = "品牌名称")
        private Long brandName;
    }


}

