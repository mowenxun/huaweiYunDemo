package com.deepexi.dd.system.mall.domain.dto.commodity;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-12-25 14:14
 */
@Data
public class CategoryCommodityResponseDTO extends AbstractObject {

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


    @ApiModelProperty("商品集合")
    List<CommodityDTO> commodityDTOS;

}
