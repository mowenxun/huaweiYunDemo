package com.deepexi.dd.domain.transaction.domain.dto.examine;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName ExamineRejectInfoRequestDTO
 * @Description 审批驳回
 * @Author SongTao
 * @Date 2020-08-13
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "ExamineRejectInfoRequestDTO")
public class ExamineRejectInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 2085235944668470391L;

    @ApiModelProperty(value = "提货单id",required = true)
    @NotNull(message = "提货单id为空")
    private Long id;

    @ApiModelProperty(value = "备注",required = true)
    @NotEmpty(message = "备注为空")
    private String remark;


}
