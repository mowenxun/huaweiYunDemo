package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName OrderItemUpdateRequest
 * @Description OMS同步发货状态
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OMS同步发货状态")
public class OrderItemUpdateRequest extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -7519943429872190242L;

    @ApiModelProperty(value = "第三方子单号",required = true)
    @NotEmpty(message = "第三方子单号为空")
    private String subExternalOrderCode;

    @ApiModelProperty(value = "状态码",required = true)
    @NotEmpty(message = "第状态码为空")
    private String orderItemState;

    @ApiModelProperty(value = "物流名称",required = true)
    @NotEmpty(message = "物流名称为空")
    private String scac;

    @ApiModelProperty(value = "物流单号",required = true)
    @NotEmpty(message = "物流单号为空")
    private String trackingNo;

    @ApiModelProperty(value = "API接口名称",required = true)
    @NotEmpty(message = "API接口名称为空")
    private String method;

    @ApiModelProperty(value = "AppKey",required = true)
    @NotEmpty(message = "AppKey为空")
    private String appKey;

    @ApiModelProperty(value = "响应格式",required = true)
    @NotEmpty(message = "响应格式为空")
    private String format;

    @ApiModelProperty(value = "签名",required = true)
    @NotEmpty(message = "签名为空")
    private String sign;

    @ApiModelProperty(value = "签名的摘要算法为空",required = true)
    @NotEmpty(message = "签名的摘要算法为空为空")
    private String signMethod;

    @ApiModelProperty(value = "时间格式(YYYY-MM-DD hh:mm:ss)",required = true)
    @NotEmpty(message = "时间格式为空")
    private String timestamp;

    @ApiModelProperty(value = "版本号",required = true)
    @NotEmpty(message = "版本号为空")
    private String version;
}
