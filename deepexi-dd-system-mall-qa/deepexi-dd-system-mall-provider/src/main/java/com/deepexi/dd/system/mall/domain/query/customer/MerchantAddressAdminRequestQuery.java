package com.deepexi.dd.system.mall.domain.query.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MerchantAddressRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantAddressAdminRequestQuery extends BaseRequestDTO {

    private static final long serialVersionUID = 3643956238819323663L;

    @ApiModelProperty(value = "客户id")
    private Long merchantId;

    @ApiModelProperty("组织id")
    private Long orgId;
}
