package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OfflineCollectionInfoResponseDTO
 * @Description 线下收款信息
 * @Author SongTao
 * @Date 2020-08-05
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflineCollectionInfoResponseDTO")
public class OfflineCollectionInfoResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 306927788817169380L;

    @ApiModelProperty(value = "收款信息")
    List<OfflineCollectionsInfoDTO> collectMoneyInfoList;
}
