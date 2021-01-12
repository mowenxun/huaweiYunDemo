package com.deepexi.dd.system.mall.domain.dto.financeaccount;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhaochongsen
 * @date 2020-09-08 11:22
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrgInfoRequestQuery")
public class OrgInfoRequestQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 业务伙伴id
     */
    @ApiModelProperty(value = "业务伙伴id")
    private Long partnerId;

}

