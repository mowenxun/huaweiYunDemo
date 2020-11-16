package com.deepexi.dd.domain.transaction.mq.producter;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/25/11:31
 * @Description:
 */

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName PlanOrderItemSendMqMsg
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/25 
 * @Version V1.0
 **/
@Data
@JSONType(orders={"sourceOrderLineId","setCode","orderQuantity","itemCode","itemPrice","remarks"})
public class PlanOrderItemSendMqMsg {



    //一体化平台订单行ID 必填
    private String sourceOrderLineId;

    //套机物料代码，最大长度16位 必填
    private String setCode;

    //数量 必填
    private Integer orderQuantity;

    //单机物料代码
    private  String itemCode;

    //套机单价，特销机才需要填写
    private BigDecimal itemPrice;

    private String remarks;


}
