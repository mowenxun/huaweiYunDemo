package com.deepexi.dd.system.mall.domain.query.common;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName AddressAdminRequestQuery
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-25
 * @Version 1.0
 **/
@Data
@ApiModel
public class AddressAdminRequestQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1643105903312554715L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private List<Long> ids;
    /**
     * 地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县 4-街道办-乡
     */
    @ApiModelProperty(value = "地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县 4-街道办-乡", required = true)
    @NotNull(message = "地区级别不能为空.")
    private Integer level;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 地区编码
     */
    @ApiModelProperty(value = "地区编码")
    private String code;
    /**
     * 地区编码
     */
    @ApiModelProperty(value = "地区编码")
    private List<String> codes;
    /**
     * 父地区编码
     */
    @ApiModelProperty(value = "父地区编码")
    private String parentCode;
    /**
     * 父地区编码
     */
    @ApiModelProperty(value = "父地区编码")
    private List<String> parentCodes;

    @ApiModelProperty(value = "租户id",required = true)
    @NotNull(message = "租户id不能为空.")
    private String tenantId;

    @ApiModelProperty(value = "应用id")
    private Long appId;
}
