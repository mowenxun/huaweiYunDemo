package com.deepexi.dd.system.mall.domain.query.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 搜索历史请求实体Query
 *
 * @author admin
 * @version 1.0
 * @date Thu Jul 16 18:55:40 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "搜索历史请求实体Query")
public class SearchHistoryRequestQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @NotBlank(message = "租户id不能为空")
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    /**
     * 应用id
     */
    @NotNull(message = "应用id不能为空")
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


    /**
     * 查询页码
     */
    @ApiModelProperty("查询页码")
    private Integer page;
    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private Integer size;
}

