package com.deepexi.dd.system.mall.domain.dto.User;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.Scope;
import lombok.Data;

@Data
@ApiModel
public class LoginRequestDTO extends AbstractObject {

    private String grant_type;

    private String username;

    private String password;

    private String scope;
}
