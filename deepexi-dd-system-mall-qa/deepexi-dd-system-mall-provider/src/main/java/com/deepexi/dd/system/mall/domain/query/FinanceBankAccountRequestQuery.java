package com.deepexi.dd.system.mall.domain.query;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FinanceBankAccountQuery
 *
 * @author admin
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceBankAccountRequestQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

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
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer version;
    /**
     * 是否用于线下付款：0是、1否
     */
    @ApiModelProperty(value = "是否用于线下付款：0是、1否")
    private Integer isOffline;
    /**
     * 状态  0 启用 1 禁用
     */
    @ApiModelProperty(value = "状态  0 启用 1 禁用")
    private Integer status;
    /**
     *  是否删除
     */
    @ApiModelProperty(value = "删除标识")
    private Integer isDeleted;

}

