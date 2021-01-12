package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yy
 * @version 1.0
 * @date 2020-08-30 15:33
 */
@Data
@ApiModel
public class UserPasswordUpdateDTO extends AbstractObject {


    @ApiModelProperty("新密码")
    private String newPassword;

    @ApiModelProperty("旧密码")
    private String oldPassword;

}
