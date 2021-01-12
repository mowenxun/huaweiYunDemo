package com.deepexi.dd.system.mall.domain.vo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "FinanceBankAccountResponseVO")
public class FinanceBankAccountResponseVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;
    /**
     * 开户名
     */
    @ApiModelProperty(value = "开户名")
    private String name;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行")
    private String bankName;
    /**
     * 银行全称
     */
    @ApiModelProperty(value = "银行全称")
    private String bankFullName;
    /**
     * 银行账号
     */
    @ApiModelProperty(value = "银行账号")
    private String bankAccount;
    /**
     * 类型：0银行卡、1支付宝、2微信、3聚合支付
     */
    @ApiModelProperty(value = "类型：0银行卡、1支付宝、2微信、3聚合支付")
    private Integer type;
    /**
     * 收款码url
     */
    @ApiModelProperty(value = "收款码url")
    private String collectionCodeUrl;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
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
     * 是否用于线下付款：0是、1否
     */
    @ApiModelProperty(value = "是否用于线下付款：0是、1否")
    private Integer isOffline;
    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Boolean deleted;
    /**
     * 状态  0 启用 1 禁用
     */
    @ApiModelProperty(value = "状态  0 启用 1 禁用")
    private Integer status;
}
