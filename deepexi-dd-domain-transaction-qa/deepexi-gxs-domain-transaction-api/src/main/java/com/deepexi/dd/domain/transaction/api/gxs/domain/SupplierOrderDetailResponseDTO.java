package com.deepexi.dd.domain.transaction.api.gxs.domain;

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
 * 供应商订单详情vo
 * @author huanghuai
 * @date 2020/10/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class SupplierOrderDetailResponseDTO extends AbstractObject implements Serializable {


    private static final long serialVersionUID = 8637690224194187918L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderCode;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 单据日期（就是创建时间）
     */
    @ApiModelProperty(value = "单据日期：使用创建时间")
    private Date createdTime;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态:1待接单,2待发货,3已发货,4已签收,5已拒单,6已撤销 ",example = "2")
    private String status;
    /**
     * 付款状态 0:未付款,1:已付款,2:部分付款
     */
    @ApiModelProperty(value = "付款状态 1待付款,2部分付款,3已付清",example = "1")
    private Integer paymentStatus;
    /**
     * 订单商品列表
     */
    @ApiModelProperty(value = "订单商品列表")
    private List<SupplierOrderItemResponseDTO> itemList;
    /**
     * 合计商品数量
     */
    @ApiModelProperty(value = "合计商品数量",example = "100")
    private Long quantity;
    /**
     * 商品总金额&合计总金额
     */
    @ApiModelProperty(value = "商品总金额&合计总金额",example = "1000.00")
    private BigDecimal totalAmount;

    // 发票相关
    @ApiModelProperty(value = "发票信息")
    private SupplierOrderDetailDTO.Invoice invoice;
    // 地址信息
    @ApiModelProperty(value = "地址信息")
    private ConsigneeAddress consigneeAddress;


    /**
     * 要求到货日期
     */
    @ApiModelProperty(value = "要求到货日期")
    private Date arriveDate;

    /**
     * 订单备注
     */
    @ApiModelProperty(value = "订单备注")
    private String remark;


    /**
     * 收货供销社
     */
    @ApiModelProperty(value = "收货供销社",example = "草海镇供销社")
    private String receiveDistributionName;



    @Data
    @ApiModel("SupplierOrderDetailResponseVOInvoice")
    public static class Invoice extends AbstractObject implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 发票抬头
         */
        @ApiModelProperty(value = "发票抬头")
        private String invoiceTitle;
        /**
         * 纳税人识别号
         */
        @ApiModelProperty(value = "纳税人识别号")
        private String taxNo;
        /**
         * 开户银行
         */
        @ApiModelProperty(value = "开户银行")
        private String bankName;
        /**
         * 开户银行账号
         */
        @ApiModelProperty(value = "开户银行账号")
        private String accountNo;
        /**
         * 电话
         */
        @ApiModelProperty(value = "电话")
        private String mobile;
        /**
         * 地址
         */
        @ApiModelProperty(value = "地址")
        private String address;
    }

    @Data
    @ApiModel("SupplierOrderDetailResponseVOConsigneeAddress")
    public static class ConsigneeAddress extends AbstractObject implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 详细地址
         */
        @ApiModelProperty(value = "收货地址")
        private String detailedAddress;
        /**
         * 收货人
         */
        @ApiModelProperty(value = "联系人")
        private String consignee;
        /**
         * 手机号码
         */
        @ApiModelProperty(value = "联系电话")
        private String mobile;
        /**
         * 收货供销社名称
         */
        @ApiModelProperty(value = "收货供销社名称")
        private String receiveDistributionName;

    }

}