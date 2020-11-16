package com.deepexi.dd.domain.transaction.domain.responseDto.gxs;

import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopSupplerRelationResponseDTO;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* 供应商实体
*
* @author admin
* @date Tue Oct 13 14:53:15 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "DistributionResponseDTO")
public class DistributionResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;


    /**
     *父级id
     */
    private Long parentId;

    /**
     *供销社名称
     */
    private String name;

    /**
     *门店编号
     */
    private String shopNo;

    /**
     *门店类型
     */
    private Integer shopType;

    /**
     *门店名称
     */
    private String shopName;

    /**
     *门店等级
     */
    private Integer shopLevel;

    /**
     *门店地址
     */
    private String address;

    /**
     *门店联系人
     */
    private String contact;

    /**
     *门店电话
     */
    private String telephone;

    /**
     *是否是叶子
     */
    private Integer isLeaf;

    /**
     * 主键
     */
    private Long id;

    /**
     * 租户id
     */
    private String tenantId;


    /**
     * appId
     */
    private Long appId;

    /**
     * 逻辑删除
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
     * 备注
     */
    private String remark;

    /**
     * 版本号
     */
    private Integer version;

}

