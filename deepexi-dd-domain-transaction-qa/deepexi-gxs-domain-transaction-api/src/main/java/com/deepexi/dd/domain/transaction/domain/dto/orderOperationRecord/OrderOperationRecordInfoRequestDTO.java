package com.deepexi.dd.domain.transaction.domain.dto.orderOperationRecord;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName OrderOperationRecordInfoRequestDTO
 * @Description 操作记录
 * @Author SongTao
 * @Date 2020-08-06
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderOperationRecordInfoRequestDTO")
public class OrderOperationRecordInfoRequestDTO  extends AbstractObject implements Serializable {
    private static final long serialVersionUID = -3189665871954218854L;

    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;

    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "动作")
    private String operation;

    @ApiModelProperty(value = "取消原因")
    private String actionCode;
}
