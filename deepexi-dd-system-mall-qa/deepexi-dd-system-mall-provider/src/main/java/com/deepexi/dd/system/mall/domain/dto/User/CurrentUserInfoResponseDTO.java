package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CurrentUserInfoResponseDTO extends AbstractObject {

    @ApiModelProperty("工号")
    private String extend3;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别（0：男，1：女）")
    private Integer gender;

    @ApiModelProperty("职位")
    private String extend2;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

}
