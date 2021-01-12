package com.deepexi.dd.system.mall.domain.dto.customer;

import domain.dto.BaseRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName MerchantDocumentCreateRequestDTO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantDocumentCreateAdminRequestDTO extends BaseRequestDTO {

    private static final long serialVersionUID = -5971794543241237040L;

    @NotNull(message = "客户id不能为空.")
    @ApiModelProperty(value = "客户id", required = true)
    private Long merchantId;

    @ApiModelProperty(value = "附件类型", required = true)
    @NotNull(message = "附件类型不能为空.")
    private Integer fileType;

    @ApiModelProperty(value = "附件名称", required = true)
    @NotEmpty(message = "附件名称不能为空.")
    private String fileName;

    @ApiModelProperty(value = "附件URL", required = true)
    @NotEmpty(message = "附件URL不能为空.")
    private String fileUrl;
}
