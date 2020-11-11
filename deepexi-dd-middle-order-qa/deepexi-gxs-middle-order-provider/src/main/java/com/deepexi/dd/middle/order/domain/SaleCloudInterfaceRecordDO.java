package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;



/**
 * SaleCloudInterfaceRecordDO
 *
 * @author admin
 * @date Wed Aug 26 19:53:05 CST 2020
 * @version 1.0
 */
@TableName("sale_cloud_interface_record")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleCloudInterfaceRecordDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

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



}

