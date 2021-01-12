package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName OrderDeliveryInfoRequestDTO
 * @Description 出库单物流记录生成DTO
 * @Author SongTao
 * @Date 2020-08-06
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryInfoRequestDTO")
public class OrderDeliveryInfoRequestDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 4552641917814845259L;

    @ApiModelProperty(value = "物流公司名称",required = true)
    private String deliveryName;

    @ApiModelProperty(value = "物流编号",required = true)
    private String deliveryCode;

    @ApiModelProperty(value = "物流投递时间",required = true)
    @NotNull(message = "发货时间为空")
    private Date deliveryTime;

    @ApiModelProperty(value = "物流备注")
    private String remark;

    @ApiModelProperty(value = "车牌号码",required = true)
    private String licensePlate;

    @ApiModelProperty(value = "司机名称",required = true)
    private String driver;

    @ApiModelProperty(value = "司机电话",required = true)
    private String driverMobile;
}
