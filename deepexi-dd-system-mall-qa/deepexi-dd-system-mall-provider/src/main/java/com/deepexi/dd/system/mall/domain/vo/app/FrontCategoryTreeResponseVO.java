package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author asus
 * @version 1.0
 * @date 2020-07-09 11:45
 */
@Data
@ApiModel(description = "FinanceBankAccountResponseVO")
public class FrontCategoryTreeResponseVO extends AbstractObject implements Serializable {

    private String code;

    private String msg;

    /**
     * 类目 id
     */
    @ApiModelProperty("类目 id")
    private Long id;
    /**
     * 类目名称
     */
    @ApiModelProperty("类目名称")
    private String name;
    /**
     * 父类目的主键
     */
    @ApiModelProperty("父类目的主键")
    private Long parentId;
    /**
     * 类目图标路径
     */
    @ApiModelProperty("类目图标路径")
    private String iconPath;
    /**
     * 是否为叶子类目，1：是，0：否
     */
    @ApiModelProperty("是否为叶子类目，1：是，0：否")
    private Boolean leaf;
    /**
     * 层级《从0开始，根目录为0，下一级为1 、2、3
     */
    @ApiModelProperty("层级《从0开始，根目录为0，下一级为1 、2、3")
    private Integer level;
    /**
     * 是否启用 1:启用, 0:不启用
     */
    @ApiModelProperty("是否启用 1:启用, 0:不启用")
    private Boolean enabled;
    /**
     * 叶子节点
     */
    @ApiModelProperty("叶子节点")
    private List<FrontCategoryTreeResponseVO> children;

}