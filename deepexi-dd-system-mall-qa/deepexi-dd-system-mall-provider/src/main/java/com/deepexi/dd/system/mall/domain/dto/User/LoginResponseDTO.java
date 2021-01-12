package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LoginResponseDTO extends AbstractObject {

    @ApiModelProperty("token")
    private String access_token;

    @ApiModelProperty("tokenType")
    private String token_type;

    @ApiModelProperty("刷新token令牌")
    private String refresh_token;

    @ApiModelProperty("过期时间")
    private String expires_in;

    @ApiModelProperty("范围")
    private String scope;

    @ApiModelProperty("用户信息")
    private UserInfo userInfo;

    @ApiModelProperty("iam类型")
    private String iamType;

    @Data
    public static class UserInfo{
        @ApiModelProperty("企业code")
        private String enterpriseCode;

        @ApiModelProperty("租户id")
        private String tenantId;

        @ApiModelProperty("用户id")
        private Long userId;

        @ApiModelProperty("用户名称")
        private String username;

        @ApiModelProperty("账号id")
        private String accountId;

        @ApiModelProperty("机构id")
        private String orgId;
    }

    @ApiModelProperty("旧token")
    private String old_access_token;
    @ApiModelProperty("jtl")
    private String jti;

}
