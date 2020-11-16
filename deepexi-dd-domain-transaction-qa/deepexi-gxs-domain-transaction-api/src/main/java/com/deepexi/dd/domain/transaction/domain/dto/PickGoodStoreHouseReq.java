package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* PickGoodStoreHouseReq
*
* @author admin
* @date Wed Aug 12 19:18:27 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PickGoodStoreHouseReq")
public class PickGoodStoreHouseReq extends AbstractObject implements Serializable {
    /**
     * 产品线ID
     */
    @ApiModelProperty(value = "产品线ID")
    private Long productId;


    /**
     * 卖家ID，顶层组织ID（供应商id）
     */
    @ApiModelProperty(value = "卖家ID，顶层组织ID（供应商id）")
    private Long sellerId;


    /**
     * 买家ID，顶层组织
     */
    @ApiModelProperty(value = "买家ID，顶层组织")
    private Long buyerId;


    /**
     * 提货单号
     */
    @ApiModelProperty(value = "提货单号")
    private String pickGoodsCode;


    /**
     * 调用该接口的场景：在线订购申请：pickApply,代提货申请replaceApply,发货sendGoods
     */
    @ApiModelProperty(value = "调用该接口的场景：在线订购申请：pickApply,代提货申请replaceApply,审核时选择发货仓库sendGoods")
    private String conditionType;




}

