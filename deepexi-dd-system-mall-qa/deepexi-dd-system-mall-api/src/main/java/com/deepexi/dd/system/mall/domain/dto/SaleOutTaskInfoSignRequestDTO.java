package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SaleOutTaskInfoSignRequestDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-18
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskInfoSignRequestDTO")
public class SaleOutTaskInfoSignRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 6744143245305667485L;

    @ApiModelProperty(value = "id集合")
    @Size(min = 1,message = "集合为空")
    private List<Long> idList;
}
