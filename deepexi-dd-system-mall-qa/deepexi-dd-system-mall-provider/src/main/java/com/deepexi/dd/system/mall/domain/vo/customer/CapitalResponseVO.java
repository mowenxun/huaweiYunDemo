package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CapitalResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class CapitalResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -810400069437660060L;

    /**
     * 信用额度
     */
    @ApiModelProperty(value = "信用额度")
    private BigDecimal creditLimit;

    /**
     * 信用账户id
     */
    @ApiModelProperty(value = "信用账户id")
    private Long creditId;
    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private Long appId;
    /**
     * 业务伙伴id
     */
    @ApiModelProperty(value = "业务伙伴id")
    private Long relationId;
    /**
     * 业务伙伴名称
     */
    @ApiModelProperty(value = "业务伙伴名称")
    private String relationName;
    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private BigDecimal amount;
    /**
     * 已用金额
     */
    @ApiModelProperty(value = "已用金额")
    private BigDecimal amountUsed;
    /**
     * 可用金额
     */
    @ApiModelProperty(value = "可用金额")
    private BigDecimal canAmount;

    private String remark;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Boolean deleted;
    /**
     * 组织id.
     */
    private Long orgId;
}
