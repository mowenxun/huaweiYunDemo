package com.deepexi.dd.domain.transaction.domain.dto.examine;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ExaminePickGoodsInfoRequestDTO
 * @Description 审核通过时的提货计划的信息
 * @Author SongTao
 * @Date 2020-08-13
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ExaminePickGoodsInfoRequestDTO")
public class ExaminePickGoodsInfoRequestDTO  extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 7484851830380538543L;

    @ApiModelProperty(value = "id",required = true)
    @NotNull(message = "id为空")
    private Long id;

    @ApiModelProperty(value = "提货单编号",required = true)
    @NotEmpty(message = "提货单编号为空")
    private String pickGoodsCode;

    @ApiModelProperty(value = "订单列表" , required = true)
    @Size(min = 1,message = "未选择商品信息")
    private List<ExamineOrderInfoRequestDTO> saleOrderList;
}
