package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName StoreResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-18
 * @Version 1.0
 **/
@Data
@ApiModel
public class StoreResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -8260005625468466130L;

    /**
     * 门店id
     */
    @ApiModelProperty(value = "门店id")
    private Long id;
    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String name;
    /**
     * 组织id(业务伙伴的组织id)
     */
    @ApiModelProperty(value = "组织id(业务伙伴的组织id)")
    private Long orgId;
    /**
     * 业务伙伴id
     */
    @ApiModelProperty(value = "业务伙伴id")
    private Long partnerId;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;
    /**
     * 删除状态 0 未删除 1删除
     */
    @ApiModelProperty(value = "删除状态 0 未删除 1删除")
    private Boolean deleted;
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "门店类型")
    private Integer type;
}
