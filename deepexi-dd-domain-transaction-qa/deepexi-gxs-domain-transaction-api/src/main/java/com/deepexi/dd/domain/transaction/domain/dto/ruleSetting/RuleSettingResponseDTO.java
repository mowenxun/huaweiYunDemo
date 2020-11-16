package com.deepexi.dd.domain.transaction.domain.dto.ruleSetting;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName ruleSettingResponseDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-20
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryConsigneeInfoRequestDTO")
public class RuleSettingResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -8751073388332345167L;

    @ApiModelProperty("规则code")
    private String code;

    @ApiModelProperty("规则value")
    private String value;

}
