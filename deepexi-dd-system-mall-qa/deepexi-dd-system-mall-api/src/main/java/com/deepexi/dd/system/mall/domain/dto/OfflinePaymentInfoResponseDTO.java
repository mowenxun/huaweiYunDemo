package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OfflinePaymentInfoResponseDTO
 * @Description 线下付款信息
 * @Author SongTao
 * @Date 2020-07-27
 * @Version 1.0
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OfflinePaymentInfoResponseDTO")
public class OfflinePaymentInfoResponseDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -339026481333723648L;


    @ApiModelProperty(value = "付款信息")
    List<OfflinePaymentInfoDTO> payInfoList;
}
