package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class LoginAdminRequestDTO extends AbstractObject {

    @ApiModelProperty(value = "企业code，格力默认为gree",required = true)
    @NotNull(message = "企业code不能为空")
    private String enterpriseCode;

    @ApiModelProperty(value = "用户名",required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotNull(message = "密码不能为空")
    private String password;
}
