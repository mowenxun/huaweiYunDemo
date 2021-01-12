package com.deepexi.dd.system.mall.domain.dto.saleorder;

import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineOrderInfoRequestDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ExaminePickGoodsInfoAdminRequestDTO extends AbstractObject {

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
