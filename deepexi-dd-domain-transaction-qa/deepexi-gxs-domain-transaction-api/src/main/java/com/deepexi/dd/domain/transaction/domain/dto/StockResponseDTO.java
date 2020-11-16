package com.deepexi.dd.domain.transaction.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-24 14:14
 */
@ApiModel("实时仓库库存")
@Data
public class StockResponseDTO implements Serializable {

    @ApiModelProperty(value = "")
    private Long appId;

    @ApiModelProperty(value = "可用库存数量")
    private Integer availableStockQty;

    @ApiModelProperty(value = "商品批次号")
    private String commodityBatchNo;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "仓库编码")
    private String depotNo;

    @ApiModelProperty(value = "仓库名称")
    private String name;

    @ApiModelProperty(value = "仓库ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "锁定库存数量")
    private Integer lockStockQty;

    @ApiModelProperty(value = "已出库库存数量")
    private Integer outboundStockQty;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "")
    private Long skuId;

    @ApiModelProperty(value = "sku 编码")
    private String skuNo;

    @ApiModelProperty(value = "")
    private Integer stockType;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "不可用库存")
    private Integer unavailableStockQty;

    @ApiModelProperty(value = "联合ID,仓库id+skuId")
    private String unionId;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "是否上云仓 1 云仓")
    private Integer isJoin;

    @ApiModelProperty(value = "1 启用  0 禁用")
    private Integer isEnabled;

    @ApiModelProperty(value = "仓库类型")
    private List<Integer> typeList;
}
