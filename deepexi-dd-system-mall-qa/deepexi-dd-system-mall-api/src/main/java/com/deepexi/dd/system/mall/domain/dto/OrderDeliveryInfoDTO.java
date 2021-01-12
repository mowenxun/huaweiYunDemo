package com.deepexi.dd.system.mall.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-02 15:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDeliveryInfoDTO")
public class OrderDeliveryInfoDTO extends AbstractObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String remark;

    private Date createdTime;

    private String createdBy;
    /**
     * 销售出库单ID
     */
//    private Long saleOutTaskId;

    /**
     * 销售出库单编号
     */
//    private String saleOutTaskCode;

    /**
     * 物流公司名称
     */
    private String deliveryName;

    /**
     * 物流编号
     */
    private String deliveryCode;

    /**
     * 物流投递时间
     */
    private Date deliveryTime;
}
