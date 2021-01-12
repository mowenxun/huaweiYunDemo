package com.deepexi.dd.system.mall.domain.dto.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @ClassName MerchantAccountBankAdminRequestDTO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantAccountBankCreateAdminRequestDTO extends BaseRequestDTO {

    private static final long serialVersionUID = 509142538519124611L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;
    /**
     * 开户名
     */
    @ApiModelProperty(value = "开户名", required = true)
    @NotEmpty(message = "开户名不能为空.")
    private String name;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行", required = true)
    @NotEmpty(message = "开户行不能为空.")
    private String bankName;
    /**
     * 银行全称
     */
    @ApiModelProperty(value = "银行全称")
    private String bankFullName;
    /**
     * 银行账号
     */
    @ApiModelProperty(value = "银行账号", required = true)
    @NotEmpty(message = "银行账号不能为空.")
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
     * 支行地址
     */
    @ApiModelProperty(value = "支行地址")
    private String branchBankAddress;
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
    /**
     * 是否默认收款账户：0是、1否
     */
    @ApiModelProperty(value = "是否默认收款账户：0是、1否")
    private Integer isProceedsAccount;
    /**
     * 是否默认支付账户：0是、1否
     */
    @ApiModelProperty(value = "是否默认支付账户：0是、1否")
    private Integer isPaymentAccount;
}
