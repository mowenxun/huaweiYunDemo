package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description TODO
 * @Author hujinhua
 * @Date 2020/10/13 20:19
 */
@Data
@ApiModel
public class UserRegisterDTO extends AbstractObject {
    @ApiModelProperty(value = "租户ID", required = true)
    @NotBlank(message = "租户ID不能为空")
    private String tenantId;
    @NotBlank(message = "账号名称不能为空")
    @ApiModelProperty(value = "账号名字", required = true)
    private String username;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "账号状态")
    private Integer status;
    @ApiModelProperty(value = "企业ID", required = true)
    @NotBlank(message = "企业ID不能为空")
    private String enterpriseCode;
    @ApiModelProperty(value = "是否为主账号")
    private Boolean isMain = Boolean.FALSE;
    @ApiModelProperty("性别[0-男, 1-女]")
    private Integer gender;
    @ApiModelProperty(value = "群组id")
    private List<Long> groupIds;

    @ApiModelProperty(value = "用户类型")
    private Integer type;

    @ApiModelProperty(value = "是否管理员")
    private Integer admin;

    @ApiModelProperty(value = "验证码")
    private String smsCode;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;
}
