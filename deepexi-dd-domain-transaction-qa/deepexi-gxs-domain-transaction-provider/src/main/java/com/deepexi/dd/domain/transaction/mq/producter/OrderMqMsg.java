package com.deepexi.dd.domain.transaction.mq.producter;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/21/19:30
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName OrderMqMsg
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/21 
 * @Version V1.0
 **/
@Data
public class OrderMqMsg {

    //订单号
    private String orderCode;

    //0=增加；1=减少
    private Integer actionType;

    //skuId
    private Long skuId;

    //数量
    private Long num;

    @ApiModelProperty(value = "0=活动订单；1=网批直供单；3=其他类型")
    private Integer orderType;

    //活动Id
    private Long activitiesId;

    //直供单Id
    private Long directId;

}
