package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author hujinhua
 * @Date 2020/10/14 17:15
 */
@Data
@ApiModel
public class GysUserDTO extends AbstractObject {
    @ApiModelProperty("租户id")
    private String tenantId;
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("帐号名")
    private String username;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("帐号状态[0-启用,1-禁用]")
    private Integer status;
    @ApiModelProperty("姓名")
    private String nickname;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("性别（0：男，1：女）")
    private Integer gender;
    @ApiModelProperty("账号ID")
    private Long accountId;
    @ApiModelProperty("是否主账号")
    private Boolean isMain;
    /**
     * 扩展字段1
     */
    @ApiModelProperty(value = "扩展字段1")
    private Integer extend1;

    /**
     * 扩展字段2
     */
    @ApiModelProperty(value = "扩展字段2")
    private String extend2;

    /**
     * 扩展字段3
     */
    @ApiModelProperty(value = "扩展字段3")
    private String extend3;

    @ApiModelProperty(value = "组织ID")
    private Long groupId;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "企业名称")
    private Integer checkStatus;
}
