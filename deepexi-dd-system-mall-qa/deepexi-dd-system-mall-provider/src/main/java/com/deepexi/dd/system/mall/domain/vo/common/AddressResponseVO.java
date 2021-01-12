package com.deepexi.dd.system.mall.domain.vo.common;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AddressResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-25
 * @Version 1.0
 **/
@Data
@ApiModel
public class AddressResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -972682809852550623L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县 4-街道办-乡
     */
    @ApiModelProperty(value = "地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县 4-街道办-乡")
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
     * 父地区编码
     */
    @ApiModelProperty(value = "父地区编码")
    private String parentCode;
    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    private Boolean deleted;
}
