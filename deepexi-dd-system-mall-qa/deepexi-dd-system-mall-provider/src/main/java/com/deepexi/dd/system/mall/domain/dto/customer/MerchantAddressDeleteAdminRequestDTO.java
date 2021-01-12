package com.deepexi.dd.system.mall.domain.dto.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName MerchantAddressDeleteRequestDTO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantAddressDeleteAdminRequestDTO extends BaseRequestDTO {

    private static final long serialVersionUID = 4456991710058264592L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private Long id;
}
