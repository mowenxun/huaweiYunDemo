package com.deepexi.dd.system.mall.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LogisticsInfoUpdateRequest
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "LogisticsInfoUpdateRequest")
public class LogisticsInfoUpdateRequest extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -7641836756766781800L;

    @ApiModelProperty(value = "第三方子单号")
    private String subExternalOrderCode;

    @ApiModelProperty(value = "是否增量 true:增量推送;false:全量推送")
    private Boolean whetherIncrement;

    @ApiModelProperty(value = "物流轨迹")
    private List<TrackMessageOut> trackMessage;

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
