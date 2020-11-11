package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * OrderInvoiceDTO
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInvoiceDTO extends AbstractObject implements Serializable {

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
     * 0-店铺订单；1-已分发的订单
     */
    private String orderType;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 公司单位名称
     */
    private String unitName;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 开户行
     */
    private String bankName;

    /**
     * 开户名称
     */
    private String accountName;

    /**
     * 开户账号
     */
    private String accountNo;

    /**
     * 纳税人识别号（税号）
     */
    private String taxNo;

    /**
     * 发票类型:ordinary普通电子发票
     */
    private String invoiceType;

    /**
     * 发票内容
     */
    private String invocieContent;

    /**
     * 联系电话（注册电话）
     */
    private String mobile;

    /**
     * 联系地址（注册地址）
     */
    private String address;

    /**
     * 发票ID
     */
    private Long invoiceId;


}

