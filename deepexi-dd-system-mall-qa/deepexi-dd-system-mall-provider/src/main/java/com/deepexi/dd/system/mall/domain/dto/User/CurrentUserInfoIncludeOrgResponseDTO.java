package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import java.math.BigDecimal;

@Data
public class CurrentUserInfoIncludeOrgResponseDTO extends AbstractObject {

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

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("企业名称")
    private String partnerName;

    @ApiModelProperty("企业code")
    private String partnerCode;

    @ApiModelProperty(value = "企业Id")
    private Long partnerId;

    @ApiModelProperty("余额")
    private BigDecimal amount;

    @ApiModelProperty("信用额度")
    private BigDecimal creditLimit;

    @ApiModelProperty("隔离Id")
    private String isolationId;

}
