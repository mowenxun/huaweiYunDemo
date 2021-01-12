package com.deepexi.dd.system.mall.domain.dto.gxs.order;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/16:43
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**入参为id结合发公共类
 * @ClassName RequestIdsPara
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Data
public class RequestIdsPara {

    @ApiModelProperty(value = "租户id", required = true)
    // @NotNull(message = "租户id不能为空")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "id集合")
    @NotEmpty(message = "id集合不能为空")
    private List<Long> ids;
}
