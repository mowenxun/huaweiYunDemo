package com.deepexi.dd.domain.transaction.domain.dto;

import java.io.Serializable;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * FinanceBankAccountDTO
 *
 * @author admin
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceBankAccountDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 状态  0 启用 1 禁用
     */
    private Integer status;


}

