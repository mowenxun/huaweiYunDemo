package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 供应商注册返回结果对象
 * @Author hujinhua
 * @Date 2020/10/13 20:24
 */
@Data
@ApiModel
public class UserRegisterResultDTO extends AbstractObject {
    @ApiModelProperty(value = "账号ID")
    private Long id;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "账号名字")
    private String username;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "账号类型")
    private String accountType;
    @ApiModelProperty(value = "账号状态")
    private Integer status;
    @ApiModelProperty(value = "企业ID")
    private String enterpriseCode;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户组ID")
    private Long groupId;
}
