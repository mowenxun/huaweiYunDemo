package com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OfflinePayInfoResponseDTO
 * @Description 线下付款信息
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflinePayInfoResponseDTO")
public class OfflinePayInfoResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 5949079983764841702L;

    @ApiModelProperty(value = "付款信息")
    List<OfflinePayInfoDTO> payInfoList;


}
