package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 搜索历史返回实体DTO
 *
 * @author admin
 * @version 1.0
 * @date Thu Jul 16 18:55:40 CST 2020
 */
@Data
@ApiModel(description = "搜索历史DTO")
public class CommoditySearchHistoryResponseVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

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

