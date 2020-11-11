package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SaleOmsLogisticsTrajectoryMessageRequestDTO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOmsLogisticsTrajectoryMessageRequestDTO")
public class SaleOmsLogisticsTrajectoryMessageRequestDTO  extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 6667062763260120052L;

    /**
     * 物流时间
     */
    @ApiModelProperty(value = "物流时间")
    private Date time;
    /**
     * 物流轨迹描述
     */
    @ApiModelProperty(value = "物流轨迹描述")
    private String message;
}
