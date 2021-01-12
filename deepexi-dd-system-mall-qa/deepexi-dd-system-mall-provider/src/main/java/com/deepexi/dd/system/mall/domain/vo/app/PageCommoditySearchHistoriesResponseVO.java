package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/27 16:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class PageCommoditySearchHistoriesResponseVO extends AbstractObject implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private Long appId;
    /**
     * 数据隔离id
     */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;
    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    private String keyword;
}
