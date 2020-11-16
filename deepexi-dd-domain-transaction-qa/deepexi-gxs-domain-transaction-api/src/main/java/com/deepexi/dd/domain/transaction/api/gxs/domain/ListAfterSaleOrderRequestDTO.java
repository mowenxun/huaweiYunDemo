package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * AfterSaleOrdeDO
 *
 * @author huanghuai
 * @version 1.0
 * @date Fri Oct 16 14:17:12 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ListAfterSaleOrderRequestDTO extends PageDTO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "组织id（groupId）",required = true)
    @NotNull(message = "组织id不能为空")
    private Long orgId;
    @ApiModelProperty(value = "单据日期开始2020-09-10 12:00:01")
    private String createdTimeFrom;
    @ApiModelProperty(value = "单据日期结束")
    private String createdTimeTo;

    @ApiModelProperty(value = "售后单号：模糊查询")
    private String orderCodeLike;

    @ApiModelProperty(value = "供应商名称：模糊查询 废弃，请使用 alterReceiveNameLike")
    private String sellerNameLike;
    @ApiModelProperty(value = "供销社名称：模糊查询")
    private String alterReceiveNameLike;
    @ApiModelProperty(value = "状态：1待审核 2审核通过 3驳回 4已完成'")
    private String status;

}

