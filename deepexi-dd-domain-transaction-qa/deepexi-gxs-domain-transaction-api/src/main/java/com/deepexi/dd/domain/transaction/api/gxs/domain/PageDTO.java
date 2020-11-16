package com.deepexi.dd.domain.transaction.api.gxs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * 页码信息
 *
 * @author huanghuai
 * @version 1.0
 * @date 2020-10-15 19:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("PageDTO")
public class PageDTO extends AppIdDTO {

    private static final long serialVersionUID = 1L;

    public PageDTO() {
        // 默认页码
        this.page =  1;
        // 默认页大小
        this.size = 10;
    }

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    @Min(value = -1, message = "页码填写错误")
    private Integer page;

    /**
     * 页大小
     */
    @Min(message = "页大小填写错误错误", value = 1)
    // @Max(value = 1000,message = "单页元素最大为1000")
    @ApiModelProperty(value = "页大小",example = "10")
    private Integer size;

}
