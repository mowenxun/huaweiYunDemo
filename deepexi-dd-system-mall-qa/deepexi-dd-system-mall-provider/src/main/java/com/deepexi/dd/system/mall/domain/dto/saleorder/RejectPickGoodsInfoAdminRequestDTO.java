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
public class RejectPickGoodsInfoAdminRequestDTO extends AbstractObject {

    @ApiModelProperty(value = "提货单id",required = true)
    @NotNull(message = "提货单id为空")
    private Long id;

    @ApiModelProperty(value = "备注",required = true)
    @NotEmpty(message = "备注为空")
    private String remark;
}
