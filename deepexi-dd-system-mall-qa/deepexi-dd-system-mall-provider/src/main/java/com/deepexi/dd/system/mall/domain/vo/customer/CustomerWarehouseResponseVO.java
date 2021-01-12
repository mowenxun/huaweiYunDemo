package com.deepexi.dd.system.mall.domain.vo.customer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerWarehouseResponseVO {
    @ApiModelProperty("仓库id")
    private String warehouseId;
    @ApiModelProperty("仓库所属组织id")
    private Long warehouseOrgId;
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @ApiModelProperty("仓库编码")
    private String warehouseCode;
    @ApiModelProperty("类型")
    private Integer type;
    @ApiModelProperty("仓库类型")
    private List<Integer> typeList;

    private String address;
    private Long appId;
    private Long cityId;
    private Long contactId;
    private String contactName;
    private String contactPhone;
    private Integer createFrom;
    private Long createFromId;
    private Date createdTime;
    private String depotNo;
    private Long districtId;
    private String id;
    private Integer isEnabled;
    private Integer isJoin;
    private String isolationId;
    private String name;
    private Long provinceId;
    private String remark;
    private Long streetId;
    private String tenantId;
    private Date updatedTime;
    private Integer version;
}
