package com.deepexi.dd.domain.transaction.domain.query;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.deepexi.util.pojo.AbstractObject;
import java.util.Date;

/**
 * FinanceBankAccountQuery
 *
 * @author admin
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceBankAccountQuery extends AbstractObject implements Serializable {

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
     * id
     */
    private Long id;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * 应用id
     */
    private Long appId;
    /**
     * 编号
     */
    private String code;
    /**
     * 开户名
     */
    private String name;
    /**
     * 开户行
     */
    private String bankName;
    /**
     * 银行全称
     */
    private String bankFullName;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 类型：0银行卡、1支付宝、2微信、3聚合支付
     */
    private Integer type;
    /**
     * 收款码url
     */
    private String collectionCodeUrl;
    /**
     * 备注
     */
    private String remark;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 是否用于线下付款：0是、1否
     */
    private Integer isOffline;
    /**
     * 状态  0 启用 1 禁用
     */
    private Integer status;
    /**
     *  是否删除
     */
    private Integer isDeleted;

}

