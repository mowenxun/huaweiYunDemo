package com.deepexi.dd.domain.transaction.mq.producter;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/25/11:21
 * @Description:
 */

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PlanOrderSendMqMsg
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/25
 * @Version V1.0
 **/
@Data
@JSONType(orders = {"sourceOrderNo", "orderType", "businessType", "inventoryType", "supplyOrgCode", "saleDepartment",
        "consumerNo", "brokerCode",
        "sourceWarehouseCode", "orderDate", "originOrderDate", "pickupMethod", "consigneeName", "consigneePhone",
        "addressProvince", "addressCity", "addressCounty", "addressTown", "detailAddress", "pickUpGoodsName",
        "pickUpGoodsMobile", "idCard", "consumerRemark", "refA", "refB", "licenseNumber", "orderItems"})
public class PlanOrderSendMqMsg {

    //一体化平台订单号 必填
    private String sourceOrderNo;

    //销售类型，取ERP中的销售类型表 必填
    private String orderType;

    //单据业务类型，1-销售单;2-调拨单;3-退货单 必填
    private Integer businessType;

    //库存类型（1客户寄存，0常规库存） 必填
    private String inventoryType;

    //卖家组织代码 必填
    private String supplyOrgCode;

    //销售部门 新增（与ERP部门编码一致，如JJB-家经部）
    private String saleDepartment;

    //业务伙伴，ERP中销售公司编码 必填
    private String consumerNo;

    //经销商代码，没有时填写销售公司编码 必填
    private String brokerCode;

    //出库仓库（代码）
    private String sourceWarehouseCode;

    //订单时间，系统时间格式：yyyy-MM-dd hh:mm:ss ,必填
    private String orderDate;

    //内部销售代表对应ERP的财务日期，值为：9-30 必填
    private String originOrderDate;

    //自提标志，0-自提；1-外请
    private String pickupMethod;

    //收货人姓名
    private String consigneeName;

    //收货人电话
    private String consigneePhone;

    private String addressProvince;

    private String addressCity;

    private String addressCounty;

    private String addressTown;

    //收货人地址
    private String detailAddress;

    //提货人姓名
    private String pickUpGoodsName;

    private String pickUpGoodsMobile;

    //提货人身份证
    private String idCard;

    private String consumerRemark;

    //参考A订单寻源仓库代码
    private String refA;

    //参考B订单寻源仓库名称
    private String refB;
    //提货车牌号
    private String licenseNumber;

    //订单明细 必填
    private List<PlanOrderItemSendMqMsg> orderItems;

}
