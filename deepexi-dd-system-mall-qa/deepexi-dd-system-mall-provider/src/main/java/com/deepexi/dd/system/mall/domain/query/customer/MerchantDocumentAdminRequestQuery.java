package com.deepexi.dd.system.mall.domain.query.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName MerchantDocumentRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantDocumentAdminRequestQuery extends BaseRequestDTO {

    private static final long serialVersionUID = -3971662740529096008L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "商户/门店id", required = true)
    @NotNull(message = "客户id不能为空")
    private Long merchantId;
    @ApiModelProperty(value = "附件类型")
    private Integer fileType;
    @ApiModelProperty(value = "附件名称")
    private String fileName;
    @ApiModelProperty(value = "附件URL")
    private String fileUrl;
}
