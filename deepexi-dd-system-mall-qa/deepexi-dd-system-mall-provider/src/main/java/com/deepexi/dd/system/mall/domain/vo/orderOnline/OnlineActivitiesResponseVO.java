package com.deepexi.dd.system.mall.domain.vo.orderOnline;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import com.deepexi.dd.system.mall.domain.vo.app.CommodityTypeResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName OnlineActivitiesResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-19
 * @Version 1.0
 **/
@Data
@ApiModel
public class OnlineActivitiesResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -968500788458588893L;

    @ApiModelProperty("活动id")
    private Long id;

    @ApiModelProperty("活动名称")
    private String name;

    @ApiModelProperty("开始时间")
    private Date startDate;

    @ApiModelProperty(name = "结束时间")
    private Date endDate;

    @ApiModelProperty("图片")
    private String imageUrl;

    @ApiModelProperty("状态|0:未开始|1:进行中|2:已结束|3:已终止")
    private Integer status;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("修改时间")
    private Date updatedTime;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("修改人")
    private String updatedBy;

    @ApiModelProperty("商品数量")
    private Integer goodNum;

    @ApiModelProperty("活动中的规则商品")
    private List<OnlineActivitiesGoodResponseVO> goodList;

    @ApiModelProperty("活动中的商品信息")
    private List<CommodityTypeResponseVO> commodityList;
}
