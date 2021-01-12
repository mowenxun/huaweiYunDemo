package com.deepexi.dd.system.mall.domain.query.app;

import com.deepexi.dd.system.mall.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-09 10:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FrontCategoryTreeRequestQuery extends BaseQuery implements Serializable {

    //todo 暂时用不到(以后用到再加)
//    /**
//     * 主键ID
//     */
//    @ApiModelProperty("主键ID")
//    private Long id;
//
//    /**
//     * 类目名称
//     */
//    @ApiModelProperty("类目名称")
//    private String name;
//
//    /**
//     * 类目名称List
//     */
//    @ApiModelProperty("类目名称List")
//    private List<String> nameList;
//
//    /**
//     * 父类目的主键
//     */
//    @ApiModelProperty("父类目的主键")
//    private Long parentId;
//
//    /**
//     * 是否为叶子类目，1：是，0：否
//     */
//    @ApiModelProperty("是否为叶子类目")
//    private Boolean leaf;
//
//    /**
//     * 层级《从0开始，根目录为0，下一级为1 、2 、3
//     */
//    @ApiModelProperty("从0开始，根目录为0")
//    private Integer level;
//
//    /**
//     * 类目id数组
//     */
//    @ApiModelProperty("类目id数组")
//    private List<Long> ids;
//
//    /**
//     * 类目编号
//     */
//    @ApiModelProperty("类目编号")
//    private String code;
//
//    /**
//     * 类目编号模糊查询
//     */
//    @ApiModelProperty("类目编号模糊查询")
//    private String codeFuzzy;

}