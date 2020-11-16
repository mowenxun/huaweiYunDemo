package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huanghuai
 * @date 2020-10-14 06:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListPayOrderRequestDTO extends PageDTO {
    private static final long serialVersionUID = 7888192850738771336L;

    @ApiModelProperty(value = "单据日期开始", example = "2020-09-10 12:00:01")
    private String createdTimeFrom;
    @ApiModelProperty(value = "单据日期结束", example = "2020-10-10 12:00:01")
    private String createdTimeTo;

    /**
     * 来源单号
     */
    @ApiModelProperty(value = "来源单号模糊查询")
    private String sourceOrderCodeLike;
    /**
     * 收款状态
     */
    @ApiModelProperty(value = "收款状态:10待收款,11部分收款,12已收讫", notes = "10待收款,11部分收款,12已收讫")
    private Integer collectionStatus;


}