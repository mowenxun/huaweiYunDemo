package com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OflineCollectionResponseDTO
 * @Description 线下收款信息
 * @Author SongTao
 * @Date 2020-08-05
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflineCollectionResponseDTO")
public class OfflineCollectionResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -8050295425445251432L;

    @ApiModelProperty(value = "收款信息")
    List<OfflineCollectionInfoDTO> collectMoneyInfoList;
}
