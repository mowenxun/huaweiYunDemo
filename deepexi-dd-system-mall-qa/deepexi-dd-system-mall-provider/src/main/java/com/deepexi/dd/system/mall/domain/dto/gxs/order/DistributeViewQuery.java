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
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName DistributeViewQuery
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Data
public class DistributeViewQuery {

    @ApiModelProperty(value = "租户id", required = true)
    // @NotNull(message = "租户id不能为空")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "要分发的店铺订单集合")
    @NotEmpty(message = "要分发的店铺订单集合不能为空")
    private List<Long> ids;
}
