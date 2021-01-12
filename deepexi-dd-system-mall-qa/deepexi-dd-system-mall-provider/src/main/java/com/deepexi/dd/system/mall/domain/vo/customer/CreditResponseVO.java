package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CreditResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class CreditResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -6016003047366657210L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 信用额度
     */
    @ApiModelProperty(value = "信用额度")
    private BigDecimal creditLimit;
    /**
     * 已用额度
     */
    @ApiModelProperty(value = "已用额度")
    private BigDecimal creditUsed;
    /**
     * 可用额度
     */
    @ApiModelProperty(value = "可用额度")
    private BigDecimal canCredit;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 信用等级：1-A；2-AA；3-AAA
     */
    @ApiModelProperty(value = "信用等级：1-A；2-AA；3-AAA")
    private Integer level;
    /**
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
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
