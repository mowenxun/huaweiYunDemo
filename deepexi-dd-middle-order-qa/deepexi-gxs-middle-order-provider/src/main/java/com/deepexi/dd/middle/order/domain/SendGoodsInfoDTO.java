package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * SendGoodsInfoDTO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SendGoodsInfoDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * APP标识
     */
    private Long appId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0：未逻辑删除状态。1:删除
     */
    private Boolean deleted;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 0-干线快运;1-零担物流；2-快递
     */
    private String sendType;

    /**
     * 收货供销社编码
     */
    private String receiveDistributionCode;

    /**
     * 收货供销社id
     */
    private Long receiveDistributionId;

    /**
     * 收货供销社名称
     */
    private String receiveDistributionName;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 收货电话
     */
    private String receiveMobile;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 物流单号
     */
    private String logisticsCode;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 司机电话
     */
    private String driverPhone;

    /**
     * 货运部
     */
    private String department;


}

