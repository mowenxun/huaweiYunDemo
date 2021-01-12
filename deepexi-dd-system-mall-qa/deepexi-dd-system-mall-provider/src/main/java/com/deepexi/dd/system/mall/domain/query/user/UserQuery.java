package com.deepexi.dd.system.mall.domain.query.user;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Description TODO
 * @Author hujinhua
 * @Date 2020/10/14 17:20
 */
@Data
@ApiModel
public class UserQuery extends AbstractObject implements Serializable {
    @ApiModelProperty(value = "供应商名称")
    private String enterpriseName;
    @ApiModelProperty(value = "供应商编号")
    private String extend3;
    @ApiModelProperty(value = "帐号状态[0-启用,1-禁用]")
    private Integer status;
    @ApiModelProperty(value = "审核状态")
    private Integer checkStatus;
    @ApiModelProperty(value = "是否管理员")
    private Integer admin;
    @ApiModelProperty(value = "应用id")
    private Long appId;
    @ApiModelProperty(value = "租户id")
    private String tenantId;


    @ApiModelProperty(value = "当前页", notes = "非必填(默认1)")
    @Min(value = -1, message = "当前面不能小于0")
    private Integer page = 1;
    @ApiModelProperty(value = "页码大小", notes = "非必填(默认10)")
    @Min(value = 1, message = "页码不能小于1")
    private Integer size = 10;
}
