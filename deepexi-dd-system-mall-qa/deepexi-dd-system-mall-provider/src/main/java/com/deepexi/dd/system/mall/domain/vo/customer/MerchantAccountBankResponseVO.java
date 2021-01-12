package com.deepexi.dd.system.mall.domain.vo.customer;

import domain.dto.BaseResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MerchantAccountBankResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-15
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantAccountBankResponseVO extends BaseResponseDTO {

    private static final long serialVersionUID = 2785704698117798019L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("账户名称")
    private String name;

    @ApiModelProperty("开户银行")
    private String bankName;

    @ApiModelProperty("银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "支行地址")
    private String branchBankAddress;

    @ApiModelProperty("是否用于线下付款：0是、1否")
    private Integer isOffline;

    @ApiModelProperty("类型：0银行卡、1支付宝、2微信、3聚合支付")
    private Integer type;

    @ApiModelProperty("收款码url")
    private String collectionCodeUrl;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("乐观锁")
    private Integer version;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("修改时间")
    private Date updatedTime;

    @ApiModelProperty("删除标识")
    private Boolean deleted;

    @ApiModelProperty("状态  0 启用 1 禁用")
    private Integer status;

    @ApiModelProperty("是否默认收款账户：0是、1否")
    private Integer isProceedsAccount;

    @ApiModelProperty("是否默认支付账户：0是、1否")
    private Integer isPaymentAccount;
}
