package com.deepexi.dd.domain.transaction.domain.query;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName OrderDeliveryRecordRequestQuery
 * @Description 发货记录
 * @Author SongTao
 * @Date 2020-08-07
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryRecordRequestQuery")
public class OrderDeliveryRecordRequestQuery extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1097723624092449209L;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "页数")
    private Integer size = 10;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "应用ID")
    private Long appId;

    @ApiModelProperty(value = "签收状态")
    private Integer signStatus;

    @ApiModelProperty(value = "搜索名称")
    private String searchName;

    @ApiModelProperty(value = "订单类型")
    private String orderType;
}
