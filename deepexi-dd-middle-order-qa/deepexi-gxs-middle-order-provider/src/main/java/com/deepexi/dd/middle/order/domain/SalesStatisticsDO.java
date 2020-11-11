package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


@TableName("sale_order_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class SalesStatisticsDO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @TableField(value = "`id`")
    private Long id;

    private Integer status;

    /**
     * 应收金额
     */
    private BigDecimal accrueAmount;

    /**
     * 已支付金额
     */
    private BigDecimal payAmount;

}