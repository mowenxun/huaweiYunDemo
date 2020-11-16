package com.deepexi.dd.domain.transaction.domain.dto.examine;

import com.deepexi.dd.domain.transaction.domain.query.WarehouseQuery;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName examineSkuRequestDTO
 * @Description 用来记录审批通过时提货计划里订单的sku商品信息
 * @Author SongTao
 * @Date 2020-08-13
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ExamineSkuRequestDTO")
public class ExamineSkuRequestDTO  extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1050055168242834125L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "商品ID", required = true)
    @NotNull(message = "商品ID为空")
    private Long skuId;

    @ApiModelProperty(value = "仓库", required = true)
    @NotEmpty(message = "仓库为空")
    private String warehouse;

    @ApiModelProperty(value = "仓库ID",required = true)
    private Long warehouseId;

    @ApiModelProperty(value = "出库数量", required = true)
    @NotNull(message = "出库数量为空")
    private Long deliveryQuantity;

    @ApiModelProperty(value = "是否加急  YES加急   NO不加急")
    private String ifEager;

    @ApiModelProperty(value = "行号")
    private String rowCode;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;


    @ApiModelProperty(value = "明细备注")
    private String remake;

    @ApiModelProperty(value = "0-基地仓；1-外仓")
    private Integer warehouseType;

    @ApiModelProperty(value = "是否上云仓，1=已上云仓；其他值=未上云仓")
    private Integer isJoin;

    private List<WarehouseQuery> items;
}
