package com.deepexi.dd.system.mall.domain.vo.app;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dengWenShun
 * @version 1.0
 * @date 2020/8/27 15:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class SaveSearchHistoryResponseVO extends AbstractObject implements Serializable {

    /**
     * 新增的id
     */
    @ApiModelProperty("新增的id")
    private Long id;
}
