package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SaleOmsLogisticsTrajectoryInfoRequestDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOmsLogisticsTrajectoryInfoRequestDTO")
public class SaleOmsLogisticsTrajectoryInfoRequestDTO  extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 6371171605256799303L;

    /**
     * 第三方子单号
     */
    @ApiModelProperty(value = "第三方子单号")
    private String subExternalOrderCode;
    /**
     * 是否增量 0:增量推送;1:全量推送
     */
    @ApiModelProperty(value = "是否增量 true:增量推送;false:全量推送")
    private Boolean whetherIncrement;

    @ApiModelProperty(value = "物流轨迹")
    private List<SaleOmsLogisticsTrajectoryMessageInfoRequestDTO> trackMessage;
}
