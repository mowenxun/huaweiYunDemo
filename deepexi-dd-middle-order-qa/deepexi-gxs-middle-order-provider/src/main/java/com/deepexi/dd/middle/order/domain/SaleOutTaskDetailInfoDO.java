package com.deepexi.dd.middle.order.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName SaleOutTaskDetailInfoDO
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-07
 * @Version 1.0
 **/
@TableName("sale_out_task_detail_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleOutTaskDetailInfoDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -3539082499433013126L;
    /**
     * 订单ID
     */
    @TableField(value = "sale_order_id")
    private Long saleOrderId;
    /**
     * 销售出库单ID
     */
    @TableField(value = "sale_out_task_id")
    private Long saleOutTaskId;
    /**
     * 商品明细ID
     */
    @TableField(value ="sale_order_item_id")
    private Long saleOrderItemId;

    /**
     * 商品本次出库数量
     */
    @TableField(value = "sku_shipment_quantity")
    private Long skuShipmentQuantity;
    /**
     * 申请数量
     */
    @TableField(value = "sku_pick_quantity")
    private Long skuPickQuantity;
    /**
     * 提货计划ID
     */
    @TableField(value = "sale_pick_goods_id")
    private Long salePickGoodsId;
    /**
     * 提货计划编号
     */
    @TableField(value = "sale_pick_goods_code")
    private String salePickGoodsCode;
}
