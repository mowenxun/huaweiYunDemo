package com.deepexi.dd.system.mall.remote.shop.order;

import com.deepexi.dd.domain.transaction.api.gxs.ReceiptOrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/20:43
 * @Description:
 */
@FeignClient(value = "${eureka.client.transaction.name}")
public interface ReceiptOrderSysRemote extends ReceiptOrderApi {
}
