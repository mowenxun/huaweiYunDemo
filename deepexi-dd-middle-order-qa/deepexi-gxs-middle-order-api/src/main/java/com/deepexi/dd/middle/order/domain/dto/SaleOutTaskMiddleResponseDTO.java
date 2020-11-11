package com.deepexi.dd.middle.order.domain.dto;

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
* SaleOutTaskDTO
*
* @author admin
* @date Wed Jun 24 09:42:06 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "SaleOutTaskMiddleResponseDTO")
public class SaleOutTaskMiddleResponseDTO extends AbstractObject implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    private Long id;
    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
    * 应用ID
    */
    @ApiModelProperty(value = "应用ID")
    private Long appId;
    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号")
    private Integer version;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
    * 0：未逻辑删除状态。1:删除
    */
    @ApiModelProperty(value = "0：未逻辑删除状态。1:删除")
    private Boolean deleted;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
     * 出库单编号
     */
    @ApiModelProperty(value = "出库单编号")
    private String code;
    /**
     * 单据类型:out 销售出库单
     */
    @ApiModelProperty(value = "单据类型:out 销售出库单")
    private String type;
    /**
    * 订单ID
    */
    @ApiModelProperty(value = "订单ID")
    private Long saleOrderId;
    /**
    * 订单编号
    */
    @ApiModelProperty(value = "订单编号")
    private String saleOrderCode;
    /**
    * 订单确认状态 0:已确认,1已作废
    */
    @ApiModelProperty(value = "订单确认状态 0:已确认,1已作废")
    private Integer status;
    /**
    * 单据日期
    */
    @ApiModelProperty(value = "单据日期")
    private Date ticketDate;
    /**
    * 计划出库总数量
    */
    @ApiModelProperty(value = "计划出库总数量")
    private Long skuQuantity;
    /**
    * 出库单类型,1原单,2蓝单,3红单
    */
    @ApiModelProperty(value = "出库单类型,1原单,2蓝单,3红单")
    private Integer taskType;
    /**
    * 红冲的原订单标识
    */
    @ApiModelProperty(value = "红冲的原订单标识")
    private Long hedgeOrder;
    /**
     * 发货物流id
     */
    @ApiModelProperty(value = "发货物流id")
    private Long orderDeliveryId;

    @ApiModelProperty(value = "商品列表")
    private List<SaleOrderItemMiddleResponseDTO> saleOrderItemList;

    //TODO 补充字段 SongTao 2020/07/06
    @ApiModelProperty(value = "客户名称")
    private String buyerName ;

    @ApiModelProperty(value = "部门名称")
    private String department;

    @ApiModelProperty(value = "经手人名称")
    private String handlerName;

    @ApiModelProperty(value = "制单时间")
    private Date saleOrderDate;
    //发货信息
    @ApiModelProperty(value = "物流公司")
    private  String deliveryName;

    @ApiModelProperty(value = "物流单号")
    private String deliveryCode;

    @ApiModelProperty(value = "物流备注")
    private String deliveryRemark;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    //TODO 结算及开票以下字段需要调用财务域接口获取
    @ApiModelProperty(value = "结算状态 0:未结算,1:已结算,2:部分结算")
    private Integer settlementStatus;

    @ApiModelProperty(value = "已结算金额")
    private BigDecimal amountSettled;

    @ApiModelProperty(value = "未结算金额")
    private BigDecimal unAmountSettled;

    @ApiModelProperty(value = "开票状态 0:未开票,1:已开票,2:部分开票")
    private String billingStatus;

    @ApiModelProperty(value = "已开票金额")
    private BigDecimal invoicedAmount;

    @ApiModelProperty(value = "未开票金额")
    private BigDecimal unvoicedAmount;

    // 补充出库单显示字段 SongTao 2020/07/08
    @ApiModelProperty(value="送货方式(logistics:物流配送)")
    private String shippingType;

    @ApiModelProperty(value = "订单备注")
    private String saleOrderRemark;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "总商品金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "促销优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "应收金额")
    private BigDecimal accrueAmount;

    @ApiModelProperty(value = "其他费用合计")
    private BigDecimal totalExpense;

    //也是制单人
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "收货地址")
    private String shippingAddress;

    /** 未找到对应字段
        分销员 待定
     */
    //2020/07/09 新加字段 SongTao
    //待定 得通过仓库ID调库存接口去取到名称
    @ApiModelProperty(value = "发货仓库ID")
    private Long deliveryWareHouseId;

    @ApiModelProperty(value = "发货仓库名称")
    private String deliveryWareHouseName;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "发货方式(0:送货；1:自提)")
    private Integer deliveryType;

    @ApiModelProperty(value = "签收状态(17:待收货；19:已签收)")
    private Integer signStatus;

    @ApiModelProperty(value = "商品出库总数量")
    private Long deliveryQuantity;
    //2020/07/10 新加字段 SongTao
    @ApiModelProperty(value = "车牌号码")
    private String licensePlate;

    @ApiModelProperty(value = "司机名称")
    private String driver;

    @ApiModelProperty(value = "身份证号（司机）")
    private String idCardNum;

    @ApiModelProperty(value = "司机电话")
    private String driverMobile;

    @ApiModelProperty(value = "签收人")
    private String signBy;

    @ApiModelProperty(value = "签收时间")
    private Date signTime;

    @ApiModelProperty(value = "发货计划ID")
    private Long saleDeliveryPlanId;

    @ApiModelProperty(value = "发货计划编号")
    private String saleDeliveryPlanCode;

    @ApiModelProperty(value = "提货计划ID")
    private Long salePickGoodsId;

    @ApiModelProperty(value = "提货计划编号")
    private String salePickGoodsCode;

    @ApiModelProperty(value = "申请数量")
    private Long skuApplyQuantity;

    //SongTao 2020/08/27 用于发货自提信息
    @ApiModelProperty(value = "(自提)提货人")
    private String saleRaisingName;

    @ApiModelProperty(value = "(自提)联系电话")
    private String telephone;

    @ApiModelProperty(value = "(自提)身份证号")
    private String idCardNumber;

    @ApiModelProperty(value = "数据隔离id")
    private String isolationId;

    @ApiModelProperty(value = "拆单所属组织")
    private Long ascriptionOrgId;


    @ApiModelProperty(value = "订单自提地址信息")
    private OrderDeliverySelfRaisingInfoRequestDTO orderDeliverySelfRaisingInfoDTO;

}

