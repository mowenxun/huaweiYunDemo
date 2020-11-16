package com.deepexi.dd.domain.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("出入库明细")
@Data
public class StockDetailDTO implements Serializable {
    @ApiModelProperty(value = "")
    private Integer changeStockQty;
    @ApiModelProperty(value = "createdTime")
    private Date createdTime;
    @ApiModelProperty("depotId")
    private Long depotId ;
    @ApiModelProperty("depotName")
    private String depotName ;
    @ApiModelProperty("depotNo")
    private String depotNo ;
    @ApiModelProperty("extendNo")
    private String extendNo ;
    @ApiModelProperty("extendType")
    private Integer extendType ;
    @ApiModelProperty("id")
    private Long id ;
    @ApiModelProperty("postStockQty")
    private Integer postStockQty ;
    @ApiModelProperty("preStockQty")
    private Integer preStockQty ;
    @ApiModelProperty("remark")
    private String remark ;
    @ApiModelProperty("skuId")
    private Long skuId ;
    //    @ApiModelProperty("skuItem")
//    private ListSkuItemResponseDTO skuItem ;
    @ApiModelProperty("stockType")
    private Integer stockType ;
    @ApiModelProperty("tenantId")
    private String tenantId ;
    @ApiModelProperty("type")
    private Integer type ;
}
