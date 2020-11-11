package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;
import java.util.Date;

/**
 * SalePickOrderYunLogQuery
 *
 * @author admin
 * @date Thu Aug 27 21:37:43 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalePickOrderYunLogQuery extends AbstractObject implements Serializable {

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
         * 创建时间
         */
        private Date createdTime;
        /**
         * 创建人
         */
        private String createdBy;
        /**
         * 发送的消息体
         */
        private String sendBody;
        /**
         * 提货单号
         */
        private String pickOrderCode;
        /**
         * 0000-成功
         */
        private String resultCode;
}

