package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "业务链路当前状态口参数")
public class LinkBusinessRequestDTO extends TenantDTO {

    //开发环境 appId:720 tenantId:gree ruleData:需要穿json格式的字符串 例如："{"deliveryGoodsQuantity":200}" actionCode：对应业务操作
    @ApiModelProperty(value = "订单id",required = true)
    @Min(value = 1,message = "订单ID错误")
    private Long id;

    @ApiModelProperty(value = "actionCode")
    private String actionCode;

    @ApiModelProperty(value = "备注",required = false)
    private String remark;

    @ApiModelProperty(value = "规则表达式数据(json字符串)")
    private String ruleData;

    @ApiModelProperty(value = "单据类型")
    private String listType;

    @ApiModelProperty(value = "版本号")
    private String version;
    @ApiModelProperty("环节编号")
    private String businessCode;


}
