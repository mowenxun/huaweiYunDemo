package com.deepexi.dd.domain.transaction.mq.gl.producter;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/08/26/21:14
 * @Description:
 */

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName OrderItemOMS
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/8/26
 * @Version V1.0
 **/
@Data
public class OrderItemOMS {

    //行号
    private String subExternalOrderCode;
    //商品编码
    private String sellerSkuCode;

    //实付金额
    private BigDecimal payPriceTotal;

    private Integer num;

    private String skuName;

    private Integer categoryId;

}
