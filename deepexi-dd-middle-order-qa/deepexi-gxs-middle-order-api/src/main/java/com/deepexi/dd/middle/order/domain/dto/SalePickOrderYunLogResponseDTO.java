package com.deepexi.dd.middle.order.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* SalePickOrderYunLogDTO
*
* @author admin
* @date Thu Aug 27 21:37:43 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SalePickOrderYunLogResponseDTO")
public class SalePickOrderYunLogResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * APP标识
    */
    @ApiModelProperty(value = "APP标识")
    private Long appId;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
    * 发送的消息体
    */
    @ApiModelProperty(value = "发送的消息体")
    private String sendBody;
    /**
    * 提货单号
    */
    @ApiModelProperty(value = "提货单号")
    private String pickOrderCode;
    /**
    * 0000-成功
    */
    @ApiModelProperty(value = "0000-成功")
    private String resultCode;

}

