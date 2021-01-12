package com.deepexi.dd.system.mall.domain.query.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MerchantAccountBankAdminRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantAccountBankAdminRequestQuery extends BaseRequestDTO {

    private static final long serialVersionUID = -7000661139281565865L;

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("编号")
    private String code;
    @ApiModelProperty("开户名")
    private String name;
    @ApiModelProperty("开户行")
    private String bankName;
    @ApiModelProperty("银行全称")
    private String bankFullName;
    @ApiModelProperty("银行账号")
    private String bankAccount;
    @ApiModelProperty("类型：0银行卡、1支付宝、2微信、3聚合支付")
    private Integer type;
    @ApiModelProperty("收款码url")
    private String collectionCodeUrl;
    @ApiModelProperty("是否用于线下付款：0是、1否")
    private Integer isOffline;
    @ApiModelProperty("状态  0 启用 1 禁用")
    private Integer status;
    @ApiModelProperty("是否默认收款账户：0是、1否")
    private Integer isProceedsAccount;
    @ApiModelProperty("是否默认支付账户：0是、1否")
    private Integer isPaymentAccount;
}
