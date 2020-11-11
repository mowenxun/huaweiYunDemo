package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* OrderRefundSkuDTO
*
* @author admin
* @date Wed Aug 19 16:31:13 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderRefundSkuResponseDTO")
public class OrderRefundSkuResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
    * 租户
    */
    @ApiModelProperty(value = "租户")
    private String tenantId;
    /**
    * 应用id
    */
    @ApiModelProperty(value = "应用id")
    private Long appId;
    /**
    * 版本号，乐观锁
    */
    @ApiModelProperty(value = "版本号，乐观锁")
    private Integer version;
    /**
    * 删除标记
    */
    @ApiModelProperty(value = "删除标记")
    private Boolean deleted;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 最后更新时间
    */
    @ApiModelProperty(value = "最后更新时间")
    private Date updatedTime;
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
    * 数据隔离id
    */
    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 主图地址
    */
    @ApiModelProperty(value = "主图地址")
    private String majorPicture;
    /**
    * 商品名称
    */
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    /**
    * 商品规格
    */
    @ApiModelProperty(value = "商品规格")
    private String skuFormat;
    /**
    * 商品编号
    */
    @ApiModelProperty(value = "商品编号")
    private String skuCode;
    /**
    * 单价
    */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    /**
    * 商品数量
    */
    @ApiModelProperty(value = "商品数量")
    private Long skuQuantity;
    /**
    * 商品出库总数量
    */
    @ApiModelProperty(value = "商品出库总数量")
    private Long skuTotalQuantity;
    /**
    * 退款单id
    */
    @ApiModelProperty(value = "退款单id")
    private Long refundOrderId;

}

