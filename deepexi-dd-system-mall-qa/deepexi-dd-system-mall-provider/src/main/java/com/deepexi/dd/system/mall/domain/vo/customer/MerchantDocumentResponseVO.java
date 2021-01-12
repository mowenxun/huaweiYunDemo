package com.deepexi.dd.system.mall.domain.vo.customer;

import com.deepexi.dd.system.mall.domain.vo.BaseResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MerchantDocumentResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@ApiModel
public class MerchantDocumentResponseVO extends BaseResponseVO {

    private static final long serialVersionUID = -3340106454519228705L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "商户/门店id")
    private Long merchantId;

    @ApiModelProperty(value = "商户类型：0业务伙伴 1客户")
    private Integer merchantType;

    @ApiModelProperty(value = "图片分类:0=资料材质 1=店铺门头照片 2=店内照片 3=授权书")
    private Integer classification;

    @ApiModelProperty(value = "附件类型")
    private Integer fileType;

    @ApiModelProperty(value = "附件名称")
    private String fileName;

    @ApiModelProperty(value = "附件URL")
    private String fileUrl;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String remark;
}
