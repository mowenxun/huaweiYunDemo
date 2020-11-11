package com.deepexi.dd.middle.order.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * FinanceBankAccountDO
 *
 * @author admin
 * @date Fri Jun 19 17:38:17 CST 2020
 * @version 1.0
 */
@TableName("finance_bank_account")
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceBankAccountDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 是否用于线下付款：0是、1否
     */
    private Integer isOffline;

    /**
     * 状态  0 启用 1 禁用
     */
    private Integer status;



}

