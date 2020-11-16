package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("仓库对象")
@Data
public class DepotPostResponseDTODepot extends AbstractObject implements Serializable {
    private String address = null;

    private Long appId = null;

    private Long cityId = null;

    private Long contactId = null;

    private String contactName = null;

    private Long contactPhone = null;

    private Integer createFrom = null;

    private Long createFromId = null;

    private Date createdTime = null;

    private String depotNo = null;

    private Long districtId = null;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id = null;

    private Integer isEnabled = null;

    private Integer isJoin = null;

    private String isolationId = null;

    private String name = null;

    private Long provinceId = null;

    private String remark = null;

    private Long streetId = null;

    private String tenantId = null;

    private Integer type = null;

    private List<Integer> typeList = null;

    private Date updatedTime = null;

    private Integer version = null;
}
