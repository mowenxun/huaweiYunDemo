package com.deepexi.dd.domain.transaction.domain.dto;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName : DepotResponseDTO
 * @Description : 仓库对象
 * @Author : yuanzaishun
 * @Date: 2020-07-22 16:59
 */
@ApiModel("仓库对象")
@Data
public class DepotResponseDTO {
    @ApiModelProperty(value = "仓库ID")
    private String id;
    @ApiModelProperty(value = "仓库编码")
    private String code;
    @ApiModelProperty(value = "仓库名称")
    private String name;

    private String address;

    private Long appId;

    private Long cityId;

    private Long contactId;

    private String contactName;

    private Long contactPhone;

    private Integer createFrom;

    private Long createFromId;

    private String depotNo;

    private Long districtId;

    private Integer isEnabled;

    private Long provinceId;

    private String remark;

    private Long streetId;

    private String tenantId;

    private Integer type;

    private String isolationId;

}
