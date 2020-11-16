package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.dd.domain.tool.domain.dto.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yp
 * @version 1.0
 * @date 2020-08-18 10:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "RuleControl")
public class RuleControl extends BaseVO implements Serializable {
    /**
     * 是否启用(0:启用;1:停用)
     */
    @ApiModelProperty(value = "是否启用(0:启用;1:停用)")
    @Min(value = 0, message = "操作异常")
    @Max(value = 1, message = "操作异常")
    private Long isEnabled;

    /**
     * 查询（0），修改状态（1）
     */
//    @ApiModelProperty(value = "查询操作（0），修改操作（1）")
//    @NotNull(message = "操作类型不能为空")
//    @Min(value = 0, message = "操作异常")
//    @Max(value = 1, message = "操作异常")
//    private Long type;
//
//    /**
//     * 参数值
//     */
//    @ApiModelProperty(value = "参数值【订货周期范围】,如每月08日-31日，前端需处理成831传入")
//    @Length(min = 3, max = 4, message = "orderCycle值异常")
//    @Min(value = 101, message = "操作异常")
//    @Max(value = 3131, message = "操作异常")
//    private String orderCycle;

}
