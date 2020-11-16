package com.deepexi.dd.domain.transaction.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * BaseDO
 *
 * @author admin
 * @date Fri May 29 10:28:27 CST 2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @TableField(value = "`id`")
    private Long id;

    /**
     * 租户id
     */
    @TableField(value = "`tenant_id`")
    private String tenantId;


    /**
     * appId
     */
    @TableField(value = "`app_id`")
    private Long appId;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(value = "`is_deleted`")
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "`created_time`", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "`updated_time`", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    /**
     * 备注
     */
    @TableField(value = "`remark`")
    private String remark;

    /**
     * 版本号
     */
    @TableField(value = "`version`")
    private Integer version;

}