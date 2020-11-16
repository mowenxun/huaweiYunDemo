package com.deepexi.dd.domain.transaction.domain.dto.saleOutTask;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SaleOutTaskSignRequestDTO
 * @Description 签收请求DTO
 * @Author SongTao
 * @Date 2020-07-13
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskSignRequestDTO")
public class SaleOutTaskSignRequestDTO  extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 2528868667934956189L;

    @ApiModelProperty(value = "id集合")
    @Size(min = 1,message = "集合为空")
    private List<Long> idList;
}
