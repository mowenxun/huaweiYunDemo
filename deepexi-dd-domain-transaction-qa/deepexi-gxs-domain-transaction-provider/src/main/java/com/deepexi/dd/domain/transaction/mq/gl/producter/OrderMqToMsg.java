package com.deepexi.dd.domain.transaction.mq.gl.producter;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/26/17:51
 * @Description:
 */

import lombok.Data;

import java.util.List;

/**
 * @ClassName OrderMqToMsg
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/26
 * @Version V1.0
 **/
@Data
public class OrderMqToMsg {

    private OrderOMS Order;

    private List<OrderItemOMS> orderItemList;


}
