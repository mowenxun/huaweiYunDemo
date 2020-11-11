package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * SaleCloudInterfaceRecordDTO
 *
 * @author admin
 * @date Wed Aug 26 19:53:05 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleCloudInterfaceRecordDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
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
     * 0：未逻辑删除状态。1:删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 接口url
     */
    private String interfaceUrl;

    /**
     * 输入参数
     */
    private String input;

    /**
     * 输出结果
     */
    private String output;

    /**
     * 类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;


}

