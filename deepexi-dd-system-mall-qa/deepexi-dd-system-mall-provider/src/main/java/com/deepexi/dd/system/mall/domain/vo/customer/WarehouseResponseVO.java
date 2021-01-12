package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName WarehouseResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class WarehouseResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = 5579105703757246944L;

    @ApiModelProperty(value = "仓库地址")
    private String address = null;

    @ApiModelProperty("cityId")
    private Long cityId = null;

    @ApiModelProperty("contactId")
    private Long contactId = null;

    @ApiModelProperty("contactName")
    private String contactName = null;

    @ApiModelProperty("contactPhone")
    private Long contactPhone = null;

    @ApiModelProperty("createFrom")
    private Integer createFrom = null;

    @ApiModelProperty("createFromId")
    private Integer createFromId = null;

    @ApiModelProperty("createdTime")
    private Date createdTime = null;

    @ApiModelProperty("depotNo")
    private String depotNo = null;

    @ApiModelProperty("districtId")
    private Long districtId = null;

    @ApiModelProperty("id")
    private Long id = null;

    @ApiModelProperty("isEnabled")
    private Integer isEnabled = null;

    @ApiModelProperty("name")
    private String name = null;

    @ApiModelProperty("provinceId")
    private Long provinceId = null;

    @ApiModelProperty("remark")
    private String remark = null;

    @ApiModelProperty("streetId")
    private Long streetId = null;

    @ApiModelProperty("tenantId")
    private String tenantId = null;

    @ApiModelProperty("type")
    private Integer type = null;

    @ApiModelProperty("updatedTime")
    private Date updatedTime = null;

    @ApiModelProperty("version")
    private Integer version = null;
}
