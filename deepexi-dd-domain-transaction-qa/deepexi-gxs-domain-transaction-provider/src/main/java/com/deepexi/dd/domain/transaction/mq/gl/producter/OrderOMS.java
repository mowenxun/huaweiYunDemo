package com.deepexi.dd.domain.transaction.mq.gl.producter;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/26/18:01
 * @Description:
 */

import lombok.Data;

/**oms订单主体
 * @ClassName OrderOMS
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/26 
 * @Version V1.0
 **/
@Data
public class OrderOMS {

    //必填，第三方子单号
    private String externalOrderCode;

    //买家电话 必填
    private String buyerMobile;

    //收货人姓名
    private String name;

    //省份，必填
    private String provinceName;

    //省id,可填code
    private  Integer provinceId;

    private String cityName;

    private Integer cityId;

    private String countyName;

    private Integer countyId;

    private String townName;

    private Integer townId;

    private String detailAddress;

    private String mobile;

    private String buyerName;

    //时间格式 YYYY-MM-DD HH:MM:SS
    private String createTime;

    //订单模式 10：发货  20.自提
    private Integer orderPattern;

    //提货人
    private String consignee;

    private String consigneePhone;

    //身份证号码
    private String consigneeId;

    //提货人车牌号
    private String licensePlateNumber;

    //时间格式 YYYY-MM-DD HH:MM:SS
    private String pickupTime;

    //“订单类型”：网批订单，对应营销一体化平台订单类型字段
    private String orderTypeString;

    // “ERP业务伙伴”：对应营销一体化平台客户编码
    private String erpPartner;

    //对应营销一体化平台客户简称字段，存自定义客户名称
    private String  erpReferA;

    //“订单来源”：对应营销一体化平台客户简称字段，存自定义客户名称
    private String orderProvideName;



}
