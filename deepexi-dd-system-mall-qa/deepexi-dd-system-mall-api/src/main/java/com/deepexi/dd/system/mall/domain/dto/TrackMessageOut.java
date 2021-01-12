package com.deepexi.dd.system.mall.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName TrackMessageOut
 * @Description TODO
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "TrackMessageOut")
public class TrackMessageOut  extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 62720402262211109L;

    /**
     * 物流时间
     */
    @ApiModelProperty(value = "物流时间(YYYY-MM-DD hh:mm:ss)",required = true)
    @NotEmpty(message = "物流时间为空")
    private String time;
    /**
     * 物流轨迹描述
     */
    @ApiModelProperty(value = "物流轨迹描述",required = true)
    @NotEmpty(message = "物流轨迹描述为空")
    private String message;
}
