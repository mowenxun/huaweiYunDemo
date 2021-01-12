package com.deepexi.dd.system.mall.domain.vo.saleorder;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OrderInfoResponseVO
 * @Description TODO
 * @Author JiangHaoWei
 * @Date 2020-08-05
 * @Version 1.0
 **/
@Data
@ApiModel
public class OrderInfoResponseVO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 8685197549434412838L;

    @ApiModelProperty(value = "供货商名称")
    private String supplierName;

    @ApiModelProperty(value = "供货商id")
    private Long supplierId;

    @ApiModelProperty(value = "订单类型值")
    private Integer ticketType;

    @ApiModelProperty(value = "订单类型")
    private String ticketTypeName;
}
