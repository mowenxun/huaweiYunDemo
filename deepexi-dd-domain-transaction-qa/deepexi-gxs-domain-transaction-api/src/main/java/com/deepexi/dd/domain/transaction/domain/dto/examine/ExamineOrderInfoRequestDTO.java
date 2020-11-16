package com.deepexi.dd.domain.transaction.domain.dto.examine;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ExamineOrderInfoRequestDTO
 * @Description 审核通过时记录提货计划下的订单信息
 * @Author SongTao
 * @Date 2020-08-13
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ExamineOrderInfoRequestDTO")
public class ExamineOrderInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 6985773306735828949L;

    @ApiModelProperty(value = "appId",required = true)
    @NotEmpty(message = "appId为空")
    private Long appId;

    @ApiModelProperty(value = "订单编号",required = true)
    @NotEmpty(message = "订单编号为空")
    private String saleOrderCode;

    @ApiModelProperty(value = "商品sku列表", required = true)
    @Size(min = 1,message = "未选择商品信息")
    private List<ExamineSkuRequestDTO> items;
}
