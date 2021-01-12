package com.deepexi.dd.system.mall.domain.vo.app;

import domain.TenantDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/30 14:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ListCustomerProjectsRequestVO extends TenantDTO implements Serializable {

    @ApiModelProperty("数据隔离id")
    private String isolationId;
    @ApiModelProperty("业务伙伴id")
    private Long partnerId;
    @ApiModelProperty("项目编码")
    private String projectCode;
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiParam("page")
    private Integer page;
    @ApiParam("size")
    private Integer size;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 主键idList
     */
    @ApiModelProperty(value = "主键idList")
    private List<Long> idList;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    /**
     * 客户idList
     */
    @ApiModelProperty(value = "客户idList")
    private List<Long> customerIdList;
    /**
     * 项目投标(字典配置)
     */
    @ApiModelProperty(value = "项目投标(字典配置)")
    private Long projectBid;
    /**
     * 所属科室
     */
    @ApiModelProperty(value = "所属科室")
    private String theirOffice;
    /**
     * 信息来源(字典配置)
     */
    @ApiModelProperty(value = "信息来源(字典配置)")
    private Long messageSource;
    /**
     * 空调面积
     */
    @ApiModelProperty(value = "空调面积")
    private String airConditionerArea;
    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积")
    private String architectureArea;
    /**
     * 项目类别(字典配置)
     */
    @ApiModelProperty(value = "项目类别(字典配置)")
    private Long projectType;
    /**
     * 总部参与方式(字典配置)
     */
    @ApiModelProperty(value = "总部参与方式(字典配置)")
    private Long participationWay;
    /**
     * 工程地址
     */
    @ApiModelProperty(value = "工程地址")
    private String address;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String addressDetails;
    /**
     * 项目用途
     */
    @ApiModelProperty(value = "项目用途")
    private String projectUse;
    /**
     * 项目所在行业
     */
    @ApiModelProperty(value = "项目所在行业")
    private String projectIndustry;
    /**
     * 项目联系人
     */
    @ApiModelProperty(value = "项目联系人")
    private String projectContacts;
    /**
     * 项目联系电话
     */
    @ApiModelProperty(value = "项目联系电话")
    private String projectContactsNumber;
    /**
     * 项目联系人岗位
     */
    @ApiModelProperty(value = "项目联系人岗位")
    private String projectContactsPost;
}
