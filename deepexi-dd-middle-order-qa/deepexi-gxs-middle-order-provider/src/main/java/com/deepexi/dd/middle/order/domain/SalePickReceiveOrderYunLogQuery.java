package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;

import java.util.Date;

/**
 * SalePickReceiveOrderYunLogQuery
 *
 * @author admin
 * @version 1.0
 * @date Wed Sep 23 13:47:55 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickReceiveOrderYunLogQuery extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 页数
     */
    private Integer size = 10;

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
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private String updatedTime;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 接收的消息体
     */
    private String receiveBody;
    /**
     * 提货单号
     */
    private String pickOrderCode;
    /**
     * 0000-成功
     */
    private String resultCode;

    /**
     * 操作类型  1 云仓取消订单 2 云仓发货
     */
    private Integer operationType;
}

